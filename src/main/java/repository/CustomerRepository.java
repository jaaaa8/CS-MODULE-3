package repository;

import dto.CustomerDto;
import entity.Customer;
import repository.impl.ICustomerRepostitory;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepostitory {
    private final String SELECT_ALL = "select c.*, a.username as username from customer c left join account a on c.account_id = a.account_id";
    private final String INSERT_INTO ="insert into customer(account_id,name,email,phone,address) values (?,?,?,?,?)";
    private final String DELETE ="delete from customer where customer_id=?";
    private final String UPDATE ="update customer set account_id=?,name=?,email=?,phone=?,address=? where customer_id= ?";
    private final String FIND_BY_ACCOUNT_ID ="select * from customer where account_id=? limit 1";
    private final String SEARCH_NAME= "select c.*, a.username as username from customer c left join account a on c.account_id = a.account_id where c.name like ?";
    private final String CREATE_NEW_BY_ACCOUNT_ID ="insert into customer(account_id,name,email,phone,address) values (?,?,?,?,?)";
    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> customers = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int customer_id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String username = resultSet.getString("username");
                CustomerDto customer = new CustomerDto(customer_id,name,email,phone,address,username);
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

    public boolean delete(int customerId) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection(); // ✅ BẮT BUỘC
            conn.setAutoCommit(false);

            // 1. delete orderitem
            PreparedStatement ps1 = conn.prepareStatement(
                    "DELETE FROM orderitem WHERE order_id IN " +
                            "(SELECT order_id FROM orders WHERE customer_id = ?)"
            );
            ps1.setInt(1, customerId);
            ps1.executeUpdate();

            // 2. delete orders
            PreparedStatement ps2 = conn.prepareStatement(
                    "DELETE FROM orders WHERE customer_id = ?"
            );
            ps2.setInt(1, customerId);
            ps2.executeUpdate();

            // 3. delete customer
            PreparedStatement ps3 = conn.prepareStatement(
                    "DELETE FROM customer WHERE customer_id = ?"
            );
            ps3.setInt(1, customerId);
            int rows = ps3.executeUpdate();

            conn.commit();
            return rows > 0; // ✅ QUAN TRỌNG

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ignored) {}
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ignored) {}
        }
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
    public List<CustomerDto> search(String name) {
        List<CustomerDto> customerList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement =  connection.prepareStatement(SEARCH_NAME);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                customerList.add(new CustomerDto(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getString("username")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return customerList;
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

    @Override
    public Customer findByAccountId(int accountId) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ACCOUNT_ID)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int customer_id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                return new Customer(customer_id, accountId, name, email, phone, address);
            }
        } catch (SQLException e) {
            System.out.println("Find by Account ID Error");
        }
        return null;
    }

    @Override
    public Customer createNewCustomerByAccountId(int accountId, String name, String email, String phone, String address) {
        if (findByAccountId(accountId) != null) {
            return null;
        }
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CREATE_NEW_BY_ACCOUNT_ID);
            ps.setInt(1, accountId);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                Customer c = new Customer();
                c.setAccountId(accountId);
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setAddress(address);
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
