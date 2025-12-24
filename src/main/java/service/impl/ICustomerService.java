package service.impl;

import dto.CustomerDto;
import entity.Customer;

import java.util.List;

public interface ICustomerService{
    List<CustomerDto> findAll();
    boolean add(Customer customer);
    boolean delete(int id);
    boolean update(Customer customer);
    List<CustomerDto> search(String name);
    Customer findById(int id);
    Customer findByAccountId(int accountId);
}
