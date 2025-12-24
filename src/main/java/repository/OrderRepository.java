package repository;

import entity.Orders;
import service.impl.IOrderRepository;
import util.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final String SELECT_ALL = "select * from orders";
    private final String INSERT_INTO = "insert into orders(customer_id,status,total,created_at,confirmed_by) values (?,?,?,?,?)";
    private final String DELETE = "delete from book where order_id=?";
    private final String UPDATE = "update orders set customer_id,status,total,created_at,confirmed_by";
    private final String UPDATE_STATUS_CONFIRMED_SQL = "UPDATE Orders SET status = ?, confirmed_by = ?, updated_at = CURRENT_TIMESTAMP() WHERE order_id = ?";
    private final String UPDATE_STATUS_OTHER_SQL = "UPDATE Orders SET status = ?, updated_at = CURRENT_TIMESTAMP() WHERE order_id = ?";

    @Override
    public List<Orders> findAll() {
        List<Orders> listOrder = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int customer_id = resultSet.getInt("customer_id");
                String status = resultSet.getString("status");
                double total = resultSet.getDouble("total");
                Date created_at = resultSet.getDate("created_at");
                int confirmed_by = resultSet.getInt("confirmed_by");

                Orders order = new Orders(order_id, customer_id, status, total, created_at, confirmed_by);
                listOrder.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error");
            ;
        }
        return listOrder;
    }

    @Override
    public boolean add(Orders orders) {
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1, orders.getCustomerId());
            preparedStatement.setString(2, orders.getStatus());
            preparedStatement.setDouble(3, orders.getTotal());
            preparedStatement.setDate(4, (java.sql.Date) orders.getCreateAt());
            preparedStatement.setInt(5, orders.getConfirmById());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Order unsuccessfully");
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Delete order unsuccessfully");
        }
        return false;
    }

    @Override
    public boolean update(Orders orders) {
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, orders.getCustomerId());
            preparedStatement.setString(2, orders.getStatus());
            preparedStatement.setDouble(3, orders.getTotal());
            preparedStatement.setDate(4, (java.sql.Date) orders.getCreateAt());
            preparedStatement.setInt(5, orders.getConfirmById());
            preparedStatement.setInt(6, orders.getId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Update order unsuccessfully");
        }
        return false;
    }

    @Override
    public boolean search(Orders orders) {
        return false;
    }

    @Override
    public Orders findById(int id) {
        String sql = "select * from orders where order_id=?";
        try(Connection connection=ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Orders(
                resultSet.getInt("order_id"),
                resultSet.getInt("customer_id"),
                resultSet.getString("status"),
                resultSet.getDouble("total"),
                resultSet.getDate("created_ad"),
                resultSet.getInt("confirmed_by")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updateStatus(int orderId, String newStatus, int confirmedByAccountId) {
        String sql;

        // Chỉ cập nhật confirmed_by khi chuyển sang trạng thái CONFIRMED
        if ("CONFIRMED".equalsIgnoreCase(newStatus)) {
            sql = UPDATE_STATUS_CONFIRMED_SQL;
        } else {
            sql = UPDATE_STATUS_OTHER_SQL;
        }

        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newStatus);

            if ("CONFIRMED".equalsIgnoreCase(newStatus)) {
                // Tham số 2: confirmed_by
                preparedStatement.setInt(2, confirmedByAccountId);
                // Tham số 3: order_id
                preparedStatement.setInt(3, orderId);
            } else {
                // Tham số 2: order_id
                preparedStatement.setInt(2, orderId);
            }

            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;

        } catch (SQLException e) {
            System.out.println("Update status order unsuccessfully: " + e.getMessage());
            // Log chi tiết lỗi
        }
        return false;
    }

    // ------------------------------------------------------------------------
    // Các phương thức cũ (findAll, add, delete, update, findById) giữ nguyên
    // ... (Đặt code của các hàm này vào đây) ...

    // Cần sửa lại phương thức này để khớp với IRepository (nếu bạn dùng nó)
    @Override
    public boolean updateStatus(int id, String newStatus) {
        // Mặc định truyền 0 nếu không cần confirmed_by, hoặc nên dùng phương thức trên
        return updateStatus(id, newStatus, 0);
    }
}

