package repository.impl;

import entity.Publisher;

import java.util.List;

public interface IPublisherRepository {
    List<Publisher> findAll();
}
