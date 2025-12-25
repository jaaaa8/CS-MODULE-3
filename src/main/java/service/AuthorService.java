package service;

import entity.Author;
import repository.AuthorRepository;
import repository.impl.IAuthorRepository;
import service.impl.IAuthorService;

import java.util.List;

public class AuthorService implements IAuthorService {
    IAuthorRepository authorRepository = new AuthorRepository();
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
