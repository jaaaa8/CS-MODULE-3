package repository.impl;

import entity.Author;

import java.util.List;

public interface IAuthorRepository {
    List<Author> findAll();
}
