package repository.impl;

import entity.Account;
import entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    boolean add(Customer customer);
    boolean delete(int id);
    boolean update(Customer customer);
    boolean search(Customer customer);
    Customer findById(int id);
    Customer createNewCustomerByAccountId(Account account, String name, String email, String phone, String address);
    Customer findByAccountId(int id);
}
