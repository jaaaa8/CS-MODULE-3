package repository;

import java.sql.Struct;
import java.util.List;

public interface IRepostitory <T>{
    List<T> findAll();
    boolean add(T t);
    boolean delete(String id);
    T findById(String id);
    boolean update(T t);
    boolean search(T t);
}
