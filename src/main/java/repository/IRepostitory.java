package repository;

import entity.Book;

import java.sql.Struct;
import java.util.List;

public interface IRepostitory <T>{
    List<T> findAll();
    boolean add(T t);
    boolean delete(int id);
    boolean update(T t);
    boolean search(T t);
    Book findById(int id);
}
