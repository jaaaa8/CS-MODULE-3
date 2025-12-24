package repository;

import dto.ProductDto;
import entity.Book;
import repository.impl.IProductRepository;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final String SELECT_ALL = """
                    SELECT b.book_id AS id,
                    c.name AS categoryName,
                    a.name AS authorName,
                    p.name AS publisherName,
                    b.title, b.description, b.price, b.stock, b.image_url AS imgURL
                    FROM Book b
                    LEFT JOIN Category c ON b.category_id = c.category_id
                    LEFT JOIN Author a ON b.author_id = a.author_id
                    LEFT JOIN Publisher p ON b.publisher_id = p.publisher_id;
                  """;

    private static final String SEARCH_BY_KEYWORD = """
                    SELECT
                    b.book_id AS id,
                    c.name AS categoryName,
                    a.name AS authorName,
                    p.name AS publisherName,
                    b.title,
                    b.description,
                    b.price,
                    b.stock,
                    b.image_url AS imgURL
                    FROM Book b
                    JOIN Author a ON b.author_id = a.author_id
                    JOIN Category c ON b.category_id = c.category_id
                    JOIN Publisher p ON b.publisher_id = p.publisher_id
                    WHERE b.title LIKE ? OR a.name LIKE ?;
                    """;
    private static final String sql = """
                    SELECT
                    b.book_id AS id,
                    b.title,
                    b.price,
                    b.description,
                    b.image_url AS imgURL,
                    a.name AS authorName,
                    c.name AS categoryName,
                    p.name AS publisherName,
                    b.stock
                    FROM Book b
                    JOIN Author a ON b.author_id = a.author_id
                    JOIN Category c ON b.category_id = c.category_id
                    JOIN Publisher p ON b.publisher_id = p.publisher_id
                    WHERE b.book_id = ?;
                    """;
    private final String INSERT_INTO ="insert into book(category_id,author_id,publisher_id,title,description,price,stock,image_url) values (?,?,?,?,?,?,?,?)";
    private final String DELETE ="delete from book where book_id=?";
    private final String UPDATE ="update book set category_id=?,author_id=?,publisher_id=?,title=?,description=?,price=?,stock=?,image_url=? where book_id= ?";
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
            System.out.println("Errol Delete Book (Foreign Key): " + e.getMessage());
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
    public List<ProductDto> findAll() {
        List<ProductDto> productList = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productList.add(new ProductDto(
                        rs.getInt("id"),
                        rs.getString("categoryName"),
                        rs.getString("authorName"),
                        rs.getString("publisherName"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("imgURL")
                ));
            }
        } catch (SQLException e) {
            System.out.println("lỗi do truỳ vấn dữ liệu");
        }
        return productList;
    }


    @Override
    public List<ProductDto> search(String keyword) {

        List<ProductDto> productList = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection()) {

            PreparedStatement ps =
                    connection.prepareStatement(SEARCH_BY_KEYWORD);

            String value = "%" + (keyword == null ? "" : keyword) + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productList.add(new ProductDto(
                        rs.getInt("id"),
                        rs.getString("categoryName"),
                        rs.getString("authorName"),
                        rs.getString("publisherName"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("imgURL")
                ));
            }

        } catch (SQLException e) {
            System.out.println("lỗi do truỳ vấn dữ liệu");
        }

        return productList;
    }

    @Override
    public ProductDto findById(int id) {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ProductDto(
                        rs.getInt("id"),
                        rs.getString("categoryName"),
                        rs.getString("authorName"),
                        rs.getString("publisherName"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("imgURL")
                );
            }
        } catch (SQLException e) {
            System.out.println("lỗi do truỳ vấn dữ liệu");
        }
        return null;
    }
}
