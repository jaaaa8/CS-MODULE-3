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

public class OrderItemRepository implements IOrderItemRepository {
    private final String ADD_NEW_ITEM = "INSERT INTO order_item (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
    private final String UPDATE_QUANTITY = "UPDATE order_item SET quantity = ? WHERE order_item_id = ?";
    private final String REMOVE_ITEM = "DELETE FROM order_item WHERE order_item_id = ?";
    private final String FIND_ITEMS_BY_ORDER_ID = "SELECT * FROM orderitem WHERE order_id = ? AND book_id = ?";
    private final String CALCULATE_TOTAL_PRICE = "SELECT SUM(price * quantity) AS total_price FROM order_item WHERE order_id = ?";
    private final String CANCEL_ITEMS_BY_ORDER_ID = "DELETE FROM orderitem WHERE order_id = ?";
    private final String SHOW_ALL_ITEMS_IN_ORDERS = "SELECT oi.order_id,b.title AS book_name,oi.quantity,oi.price FROM orderitem oi JOIN book b ON oi.book_id = b.book_id WHERE oi.order_id = ?";

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
        List<OrderItemDto> itemsList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_ITEMS_IN_ORDERS);){
            preparedStatement.setInt(1, orderId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String bookName = rs.getString("book_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                OrderItemDto item = new OrderItemDto(orderId, bookName, quantity, price);
                itemsList.add(item);
            }
        }catch (SQLException e){
            System.err.println("Error getting items by order ID!");
        }
        return itemsList;
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
        return -1;
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
}
