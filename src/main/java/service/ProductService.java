package service;

import dto.ProductDto;
import repository.IProductRepository;
import repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository productRepository = new ProductRepository();


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDto> search(String keyword) {
        return productRepository.search(keyword);
    }
    @Override
    public ProductDto findById(int id) {
        return productRepository.findById(id);
    }
}
