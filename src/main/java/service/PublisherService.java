package service;

import entity.Publisher;
import repository.PublisherRepository;
import repository.impl.IPublisherRepository;
import service.impl.IPublisherService;

import java.util.List;

public class PublisherService implements IPublisherService {
    IPublisherRepository publisherRepository = new PublisherRepository();
    @Override
    public List<Publisher> findAll() {
        return  publisherRepository.findAll();
    }
}
