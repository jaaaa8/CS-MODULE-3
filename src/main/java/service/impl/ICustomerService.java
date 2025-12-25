package service.impl;


import dto.CustomerDto;
import entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> findAll();
    boolean add(Customer customer);
    boolean delete(int id);
    boolean update(Customer customer);
    Customer findById(int id);
    Customer createNewCustomerByAccountId(int accountId, String name, String email, String phone, String address);
    Customer findByAccountId(int id);
    List<CustomerDto> search(String name);
}
