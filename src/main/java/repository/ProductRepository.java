package repository;

import dto.ProductDto;
import entity.Orders;
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
