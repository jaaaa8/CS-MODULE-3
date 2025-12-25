package service;

import entity.Category;
import repository.CategoryRepository;
import repository.impl.ICategoryRepository;
import service.impl.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    ICategoryRepository categoryRepository = new CategoryRepository();
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
