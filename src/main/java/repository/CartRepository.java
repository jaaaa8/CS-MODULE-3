//package repository;
//
//import dto.CartItemDto;
//import util.ConnectDB;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import java.sql.*;
//
//
//public class CartRepository implements ICartRepository {
//
//    private static final String FIND_CART =
//            "SELECT order_id FROM Orders WHERE customer_id=? AND status='CART'";
//
//    private static final String CREATE_CART =
//            "INSERT INTO Orders(customer_id, status, total) VALUES (?, 'CART', 0)";
//
//    private static final String CHECK_ITEM =
//            "SELECT order_item_id FROM OrderItem WHERE order_id=? AND book_id=?";
//
//    private static final String ADD_ITEM =
//            "INSERT INTO OrderItem(order_id, book_id, quantity, price) " +
//                    "SELECT ?, book_id, ?, price FROM Book WHERE book_id=?";
//
//    private static final String UPDATE_QTY =
//            "UPDATE OrderItem SET quantity = quantity + ? WHERE order_id=? AND book_id=?";
//
//    private static final String GET_CART_ITEMS =
//            """
//            SELECT oi.order_item_id, b.book_id, b.title, b.image_url, oi.price, oi.quantity
//            FROM Orders o
//            JOIN OrderItem oi ON o.order_id = oi.order_id
//            JOIN Book b ON oi.book_id = b.book_id
//            WHERE o.customer_id=? AND o.status='CART'
//            """;
//
//    @Override
//    public Integer findCartIdByCustomer(int customerId) {
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(FIND_CART)) {
//
//            ps.setInt(1, customerId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt("order_id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int createCart(int customerId) {
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(CREATE_CART, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setInt(1, customerId);
//            ps.executeUpdate();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new RuntimeException("Không tạo được CART");
//    }
//
//    @Override
//    public boolean existsItem(int cartId, int bookId) {
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(CHECK_ITEM)) {
//
//            ps.setInt(1, cartId);
//            ps.setInt(2, bookId);
//
//            ResultSet rs = ps.executeQuery();
//            return rs.next();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public void addItem(int cartId, int bookId, int quantity) {
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(ADD_ITEM)) {
//
//            ps.setInt(1, cartId);
//            ps.setInt(2, quantity);
//            ps.setInt(3, bookId);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateQuantity(int cartId, int bookId, int quantity) {
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(UPDATE_QTY)) {
//
//            ps.setInt(1, quantity);
//            ps.setInt(2, cartId);
//            ps.setInt(3, bookId);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<CartItemDto> getCartItems(int customerId) {
//        List<CartItemDto> list = new ArrayList<>();
//
//        try (Connection conn = ConnectDB.getConnection();
//             PreparedStatement ps = conn.prepareStatement(GET_CART_ITEMS)) {
//
//            ps.setInt(1, customerId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list.add(new CartItemDto(
//                        rs.getInt("order_item_id"),
//                        rs.getInt("book_id"),
//                        rs.getString("title"),
//                        rs.getString("image_url"),
//                        rs.getDouble("price"),
//                        rs.getInt("quantity")
//                ));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//}
//
//
