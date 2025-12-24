package repository.impl;

import dto.ProductDto;
import entity.Book;

import java.util.List;

public interface IProductRepository {
    boolean add(Book book);
    boolean delete(int id);
    boolean update(Book book);
    List<ProductDto> findAll();
    List<ProductDto> search(String keyword);
    ProductDto findById(int id);
}
