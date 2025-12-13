package repository;

import dto.ProductDto;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final String SELECT_ALL_PRODUCT = """
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
                LEFT JOIN Category c ON b.category_id = c.category_id
                JOIN Author a ON b.author_id = a.author_id
                JOIN Publisher p ON b.publisher_id = p.publisher_id;""";

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> products = new ArrayList<>();

        try(Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                String authorName = resultSet.getString("authorName");
                String publisherName = resultSet.getString("publisherName");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String imgURL = resultSet.getString("imgURL");

                ProductDto productDto = new ProductDto(id, categoryName, authorName, publisherName, title, description, price, stock, imgURL);
                products.add(productDto);
            }
            return products;
        } catch (SQLException e) {
            System.out.println("lỗi do truy vấn dữ liệu");
        }
        return null;
    }

    @Override
    public List<ProductDto> search(String searchTitle, String bookId, String searchAuthorName, String authorId) {
    List<ProductDto> productList = new ArrayList<>();
        // Kết nối DB
        try(Connection connection = ConnectDB.getConnection()) {
            PreparedStatement preparedStatement= null;
            // Xây dựng câu lệnh truy vấn động
            StringBuilder query = new StringBuilder(SELECT_ALL_PRODUCT + " WHERE 1=1");
            if (searchTitle != null && !searchTitle.isEmpty()) {
                query.append(" AND b.title LIKE ?");
            }
            if (bookId != null && !bookId.isEmpty()) {
                query.append(" AND b.book_id = ?");
            }
            if (searchAuthorName != null && !searchAuthorName.isEmpty()) {
                query.append(" AND a.name LIKE ?");
            }
            if (authorId != null && !authorId.isEmpty()) {
                query.append(" AND a.author_id = ?");
            }
            preparedStatement = connection.prepareStatement(query.toString());

            // Thiết lập tham số cho PreparedStatement
            int paramIndex = 1;
            if (searchTitle != null && !searchTitle.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + searchTitle + "%");
            }
            if (bookId != null && !bookId.isEmpty()) {
                preparedStatement.setInt(paramIndex++, Integer.parseInt(bookId));
            }
            if (searchAuthorName != null && !searchAuthorName.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + searchAuthorName + "%");
            }
            if (authorId != null && !authorId.isEmpty()) {
                preparedStatement.setInt(paramIndex++, Integer.parseInt(authorId));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                String authorName = resultSet.getString("authorName");
                String publisherName = resultSet.getString("publisherName");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String imgURL = resultSet.getString("imgURL");

                ProductDto productDto = new ProductDto(id, categoryName, authorName, publisherName, title, description, price, stock, imgURL);
                productList.add(productDto);
            }
        } catch (SQLException e) {
            System.out.println("lỗi do truy vấn dữ liệu");
        }
        return productList;
    }


}
