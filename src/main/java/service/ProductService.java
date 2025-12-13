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
    public List<ProductDto> search(String searchTitle, String bookId, String searchAuthorName, String authorId) {
        return productRepository.search(searchTitle, bookId, searchAuthorName, authorId);
    }
}
