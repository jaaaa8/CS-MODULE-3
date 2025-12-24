package service;

import dto.ProductDto;
import entity.Book;
import repository.impl.IProductRepository;
import repository.ProductRepository;
import service.impl.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository productRepository = new ProductRepository();

    @Override
    public boolean add(Book book) {
        return productRepository.add(book);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.delete(id);
    }

    @Override
    public boolean update(Book book) {
        return productRepository.update(book);
    }

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
