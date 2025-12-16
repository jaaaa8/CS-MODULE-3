package repository;

import entity.Customer;
import entity.Orders;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IRepostitory<Customer> {
    private final String SELECT_ALL = "select * from customer";
    private final String INSERT_INTO ="insert into customer(account_id,name,email,phone,address) values (?,?,?,?,?)";
    private final String DELETE ="delete from customer where customer_id=?";
    private final String UPDATE ="update customer set account_id=?,name=?,email=?,phone=?,address=? where customer_id= ?";
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int customer_id = resultSet.getInt("customer_id");
                int account_id = resultSet.getInt("account_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Customer customer = new Customer(customer_id,account_id,name,email,phone,address);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public boolean add(Customer customer) {
        try(Connection connection = ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1,customer.getAccountId());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getEmail());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setString(5,customer.getAddress());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow ==1;
        } catch (SQLException e) {
            System.out.println("Insert into Customer Error");;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection=ConnectDB.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            int effectRow=preparedStatement.executeUpdate();
            return effectRow ==1;
        } catch (SQLException e) {
            System.out.println("Delete Customer Error");
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        try(Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1,customer.getAccountId());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getEmail());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setString(5,customer.getAddress());
            preparedStatement.setInt(6,customer.getId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow ==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean search(Customer customer) {
        return false;
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("account_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Orders> findByCustomerId(int customerId) {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ? ORDER BY created_at DESC";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders orders = new Orders(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getString("status"),
                        rs.getDouble("totalPrice"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("confirmed_by")
                );
                ordersList.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

}
