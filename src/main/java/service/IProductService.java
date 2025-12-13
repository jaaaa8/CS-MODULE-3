package service;

import dto.ProductDto;

import java.util.List;

public interface IProductService {
 List<ProductDto> findAll();
 List<ProductDto> search(String searchTitle, String bookId, String searchAuthorName, String authorId);
}
