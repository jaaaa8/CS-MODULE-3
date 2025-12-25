package repository;

import dto.OrdersDto;
import entity.Orders;
import repository.impl.IOrderRepository;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersRepository implements IOrderRepository {
    private final String SELECT_ALL_ORDERS = "select o.order_id, c.name as customer_name, o.status, o.total, o.created_at, a.username as confirmed_by_name from Orders o join customer c on o.customer_id = c.customer_id left join account a on o.confirmed_by = a.account_id;";
    private final String UPDATE_ORDER = "UPDATE orders SET customer_id = ?, order_date = ?, status = ? WHERE order_id = ?";
    private final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    private final String FIND_CART_BY_CUSTOMER_ID = "SELECT * FROM orders WHERE customer_id = ? AND status = 'CART' ORDER BY order_id DESC LIMIT 1";
    private final String CREATE_CART_FOR_CUSTOMER = "INSERT INTO orders (customer_id, status) VALUES (?, 'CART')";
    private final String UPDATE_ORDER_STATUS = "UPDATE orders SET total = ?,status = ? WHERE order_id = ?";
    private final String CONFIRM_ORDER = "UPDATE orders SET status = 'CONFIRMED', confirmed_by = ? WHERE order_id = ? AND status = 'PENDING'";

    @Override
    public List<OrdersDto> findAllOrders() {
        List<OrdersDto> ordersList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int orderId = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String status = rs.getString("status");
                double total = rs.getDouble("total");
                Date createdAt = rs.getDate("created_at");
                String confirmedBy = rs.getString("confirmed_by_name");
                OrdersDto order = new OrdersDto(orderId, customerName, status, total, createdAt, confirmedBy);
                ordersList.add(order);
            }
        }catch (SQLException e){
            System.err.println("LOI GET ALL!");
        }
        return ordersList;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM OrderItem WHERE order_id = ?");
            ps1.setInt(1, orderId);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Orders WHERE order_id = ?");
            ps2.setInt(1, orderId);
            int rows = ps2.executeUpdate();

            conn.commit();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
        return false;
    }




    @Override
    public Orders findOrderById(int orderId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID);){
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                int customerId = rs.getInt("customer_id");
                String status = rs.getString("status");
                double total = rs.getDouble("total");
                Date orderDate = rs.getDate("order_date");
                int confirmedBy = rs.getInt("confirmed_by");
                return new Orders(orderId, customerId, status, total, orderDate, confirmedBy);
            }
        } catch (SQLException e) {
            System.err.println("Error finding order by ID!");
        }
        return null;
    }

    @Override
    public boolean confirmOrder(int orderId, int adminId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_ORDER);){
            preparedStatement.setInt(1, adminId);
            preparedStatement.setInt(2, orderId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error confirming order!");
        }
        return false;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);){
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating order status!");
        }
        return false;
    }

    @Override
    public Orders findCartByCustomerId(int customerId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CART_BY_CUSTOMER_ID);){
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                int orderId = rs.getInt("order_id");
                String status = rs.getString("status");
                double total = rs.getDouble("total");
                Date orderDate = rs.getDate("created_at");
                int confirmedBy = rs.getInt("confirmed_by");
                return new Orders(orderId, customerId, status, total, orderDate, confirmedBy);
            }
        } catch (SQLException e) {
            System.err.println("Error finding cart by customer ID!");
        }
        return null;
    }

    @Override
    public int createCartForCustomer(int customerId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CART_FOR_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS);){
            preparedStatement.setInt(1, customerId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Tạo cart thất bại.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Tạo cart thất bại.");
                }
            }catch (SQLException e) {
                System.err.println("Không lấy được auto_increment id");
            }
        } catch (SQLException e) {
            System.err.println("Error creating cart for customer!");
        }
        return 0;
    }

    @Override
    public boolean updateOrderStatusAndTotalPrice(int orderId, double totalPrice, String status) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);){
            preparedStatement.setDouble(1, totalPrice);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, orderId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating order status and total price!");
        }
        return false;
    }
    public boolean updateStatusAndTotal(int orderId, double total) {
        String sql = "UPDATE orders SET status = 'PENDING', total = ? WHERE order_id = ?";
        try (java.sql.Connection conn = util.ConnectDB.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, total);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
