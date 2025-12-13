package service;

import entity.Book;
import repository.BookRepository;
import repository.IRepostitory;

import java.util.List;

public class BookService implements IService<Book> {
    IRepostitory<Book> bookRepository =  new BookRepository();
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean add(Book book) {
        return bookRepository.add(book);
    }

    @Override
    public boolean delete(int id) {
        return bookRepository.delete(id);
    }

    @Override
    public boolean update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public boolean search(Book book) {
        return bookRepository.search(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }
}
