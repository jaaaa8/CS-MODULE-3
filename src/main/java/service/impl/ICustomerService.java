package service.impl;

import entity.Account;
import entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    boolean add(Customer customer);
    boolean delete(int id);
    boolean update(Customer customer);
    boolean search(Customer customer);
    Customer findById(int id);
    Customer findByAccountId(int id);
    Customer createNewCustomerByAccountId(int accountId, String name, String email, String phone, String address);
}
