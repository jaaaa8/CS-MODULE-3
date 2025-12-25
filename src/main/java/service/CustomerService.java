package service;

import dto.CustomerDto;
import entity.Customer;
import repository.CustomerRepository;
import repository.impl.ICustomerRepository;
import service.impl.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository = new CustomerRepository();
    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean add(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public boolean delete(int id) {
        return customerRepository.delete(id);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByAccountId(int id) {
        return customerRepository.findByAccountId(id);
    }

    @Override
    public List<CustomerDto> search(String name) {
        return customerRepository.search(name);
    }

    @Override
    public Customer createNewCustomerByAccountId(int accountId, String name, String email, String phone, String address) {
        return customerRepository.createNewCustomerByAccountId(accountId, name, email, phone, address);
    }

}
