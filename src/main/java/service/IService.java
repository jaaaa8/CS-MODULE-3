package service;

import entity.Book;

import java.util.List;

public interface IService <T>{
    List<T> findAll();
    boolean add(T t);
    boolean delete(int id);
    boolean update(T t);
    boolean search(T t);
    T findById(int id);
}
