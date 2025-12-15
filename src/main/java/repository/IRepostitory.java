package repository;

import entity.Customer;

import java.util.List;

public interface IRepostitory <T>{
    List<T> findAll();
    boolean add(T t);
    boolean delete(int id);
    boolean update(T t);
    boolean search(T t);
    T findById(int id);
}
