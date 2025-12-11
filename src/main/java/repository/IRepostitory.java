package repository;

import java.sql.Struct;
import java.util.List;

public interface IRepostitory <T>{
    List<T> findAll();
    boolean add(T t);
    boolean delete(String id);
    boolean update(T t);
}
