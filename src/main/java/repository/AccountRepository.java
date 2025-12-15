package repository;

import dto.AccountDto;
import entity.Account;
import repository.impl.IAccountRepository;
import util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IAccountRepository {
    private final String SELECT_ALL_ACCOUNTS = "SELECT * FROM account";
    private final String INSERT_ACCOUNT = "INSERT INTO account(username, password, role) VALUES (?, ?, ?)";
    private final String UPDATE_PASSWORD = "UPDATE account SET password = ? WHERE account_id = ?";
    private final String DELETE_ACCOUNT = "DELETE FROM account WHERE account_id = ?";
    private final String FIND_ACCOUNT_BY_USERNAME = "SELECT * FROM account WHERE username = ?";

    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accountList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("account_id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                accountList.add(new AccountDto(id,username, role));
            }
        } catch (SQLException e) {
            System.err.println("LOI GET ALL!");
        }
        return accountList;
    }

    @Override
    public boolean addNewAccount(Account account) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getRole());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("LOI ADD!");
        }
        return false;
    }

    @Override
    public boolean updatePassword(int accountId, String newPassword) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, accountId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.err.println("LOI UPDATE");
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int accountId) {
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT);) {
            preparedStatement.setInt(1, accountId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.err.println("LOI DELETE!");
        }
        return false;
    }

    @Override
    public Account findAccountByUsername(String username) {
        Account account = null;
        try(Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_USERNAME);) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("account_id");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Date createdAt = rs.getDate("created_at");
                account = new Account(username,password, role, createdAt);
                account.setId(id);
            }
            return account;
        } catch (SQLException e) {
            System.err.println("LOI FIND!");
        }
        return null;
    }
}
