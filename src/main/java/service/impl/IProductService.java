package service.impl;

import dto.ProductDto;
import entity.Book;

import java.util.List;

public interface IProductService {
  boolean add(Book book);
  boolean delete(int id);
  boolean update(Book book);
  List<ProductDto> findAll();
  List<ProductDto> search(String keyword);
  ProductDto findById(int id);
  List<ProductDto> filter(String name, Integer categoryId, Integer authorId, Integer publisherId);
  void updateStock(int bookId, int quantityChange);
}
