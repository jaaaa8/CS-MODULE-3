package repository;

import entity.Book;
import entity.Customer;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IRepostitory<Book>{
    private final String SELECT_ALL = "select * from book";
    private final String INSERT_INTO ="insert into book(category_id,author_id,publisher_id,title,description,price,stock,image_url) values (?,?,?,?,?,?,?,?)";
    private final String DELETE ="delete from book where book_id=?";
    private final String UPDATE ="update book set category_id=?,author_id=?,publisher_id=?,title=?,description=?,price=?,stock=?,image_url=? where book_id= ?";
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int book_id = resultSet.getInt("book_id");
                int category_id = resultSet.getInt("category_id");
                int author_id = resultSet.getInt("author_id");
                int publisher_id = resultSet.getInt("publisher_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String image_url = resultSet.getString("image_url");
                Book book = new Book(book_id, category_id,  author_id, publisher_id, title, description, price, stock, image_url);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public boolean add(Book book) {
        try(Connection connection= ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1, book.getCategoryId());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getPublisherId());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setDouble(6, book.getPrice());
            preparedStatement.setInt(7, book.getStock());
            preparedStatement.setString(8, book.getImgURL());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Error inserting Book");
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            conn.setAutoCommit(false);

            String sqlDeleteItem = "DELETE FROM OrderItem WHERE book_id = ?";
            try(PreparedStatement psItem = conn.prepareStatement(sqlDeleteItem)) {
                psItem.setInt(1, id);
                psItem.executeUpdate();
            }

            try(PreparedStatement psBook = conn.prepareStatement(DELETE)) {
                psBook.setInt(1, id);
                int rowsAffected = psBook.executeUpdate();
                conn.commit();
                return rowsAffected > 0;
            }

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi khi xóa Book (Foreign Key): " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean update(Book book) {
        try(Connection connection = ConnectDB.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, book.getCategoryId());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getPublisherId());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setDouble(6, book.getPrice());
            preparedStatement.setInt(7, book.getStock());
            preparedStatement.setString(8, book.getImgURL());
            preparedStatement.setInt(9, book.getId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean search(Book book) {
        return false;
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM book WHERE book_id = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("publisher_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        resultSet.getString("image_url")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
