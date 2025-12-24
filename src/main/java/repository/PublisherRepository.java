package repository;

import entity.Publisher;
import repository.impl.IPublisherRepository;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherRepository implements IPublisherRepository {
    @Override
    public List<Publisher> findAll() {
        List<Publisher> publisherList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnection() ){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from publisher;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("publisher_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                Publisher publisher = new Publisher(id,name,address,phone);
                publisherList.add(publisher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return publisherList;
    }
}
