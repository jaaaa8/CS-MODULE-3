package repository;

import dto.ProductDto;

import java.util.List;

public interface IProductRepository {
    List<ProductDto> findAll();
    List<ProductDto> search(String keyword);
    ProductDto findById(int id);

}
