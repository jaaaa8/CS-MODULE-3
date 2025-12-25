package repository;

import entity.Author;
import repository.impl.IAuthorRepository;
import repository.impl.ICategoryRepository;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements IAuthorRepository {
    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection() ){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from author;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("author_id");
                String name = resultSet.getString("name");
                String bio = resultSet.getString("bio");
                Author author = new Author(id, name, bio);
                authorList.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorList;
    }
}
