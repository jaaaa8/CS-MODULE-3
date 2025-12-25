package repository;

import dto.OrderItemDto;
import entity.OrderItem;
import repository.impl.IOrderItemRepository;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    OrderItemRepository implements IOrderItemRepository {
    private final String ADD_NEW_ITEM = "INSERT INTO orderitem (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
    private final String UPDATE_QUANTITY = "UPDATE orderitem SET quantity = ? WHERE order_item_id = ?";
    private final String REMOVE_ITEM = "DELETE FROM orderitem WHERE order_item_id = ?";
    private final String FIND_ITEMS_BY_ORDER_ID = "SELECT * FROM orderitem WHERE order_id = ? AND book_id = ?";
    private final String CALCULATE_TOTAL_PRICE = "SELECT SUM(price * quantity) AS total_price FROM orderitem WHERE order_id = ?";
    private final String CANCEL_ITEMS_BY_ORDER_ID = "DELETE FROM orderitem WHERE order_id = ?";
    private final String FIND_BY_ORDER_AND_BOOK = "SELECT * FROM orderitem WHERE order_id = ? AND book_id = ?";
    private final String UPDATE_QUANTITY_BY_ORDER_AND_BOOK = "UPDATE orderitem SET quantity = quantity + ? WHERE order_id = ? AND book_id = ?";
    private static final String SELECT_ITEMS_BY_ORDER_ID =
            "SELECT oi.order_item_id, oi.book_id, b.title, oi.quantity, oi.price, b.stock " + // <-- Thêm oi.book_id
                    "FROM OrderItem oi " +
                    "JOIN Book b ON oi.book_id = b.book_id " +
                    "WHERE oi.order_id = ?";

    @Override
    public boolean addItem(int orderId, int bookId, int quantity, double price) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ITEM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Error adding item to order");
        }
        return false;
    }

    @Override
    public boolean updateQuantity(int orderItemId, int quantity) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)){
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, orderItemId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Error updating item quantity");
        }
        return false;
    }

    @Override
    public boolean removeItem(int orderItemId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ITEM)){
            preparedStatement.setInt(1, orderItemId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Error removing item from order");
        }
        return false;
    }

    @Override
    public List<OrderItemDto> getItemsByOrderId(int orderId) {
        List<OrderItemDto> items = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ITEMS_BY_ORDER_ID)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Đọc book_id từ ResultSet
                int bookId = rs.getInt("book_id");

                items.add(new OrderItemDto(
                        rs.getInt("order_item_id"),
                        bookId,                     // <-- Truyền bookId vào Constructor mới
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public OrderItem findItemById(int orderItemId, int bookId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEMS_BY_ORDER_ID);){
            preparedStatement.setInt(1, orderItemId);
            preparedStatement.setInt(2, bookId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                int id = rs.getInt("order_item_id");
                int orderId = rs.getInt("order_id");
                int bId = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                return new OrderItem(id, orderId, bId, quantity, price);
            }
        }catch(SQLException e){
            System.out.println("Error finding item by ID");
        }
        return null;
    }

    @Override
    public double calculateTotalPrice(int orderId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CALCULATE_TOTAL_PRICE);){
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return rs.getDouble("total_price");
            }
        }catch (SQLException e){
            System.out.println("Error calculating total price");
        }
        return 0;
    }

    @Override
    public boolean cancelItemsByOrderId(int orderId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ITEMS_BY_ORDER_ID)){
            preparedStatement.setInt(1, orderId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow >= 1;
        } catch (SQLException e) {
            System.out.println("Error canceling items by order ID");
        }
        return false;
    }

    @Override
    public boolean increaseQuantity(int orderId, int bookId, int quantity) {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_QUANTITY_BY_ORDER_AND_BOOK)) {

            ps.setInt(1, quantity);
            ps.setInt(2, orderId);
            ps.setInt(3, bookId);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existItemInOrder(int orderId, int bookId) {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ORDER_AND_BOOK)) {

            ps.setInt(1, orderId);
            ps.setInt(2, bookId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
