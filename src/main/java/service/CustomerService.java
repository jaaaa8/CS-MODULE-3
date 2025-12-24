package service;

import dto.CustomerDto;
import entity.Customer;
import repository.CustomerRepository;
import repository.impl.ICustomerRepostitory;
import service.impl.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {
    ICustomerRepostitory customerRepostitory = new CustomerRepository();
    @Override
    public List<CustomerDto> findAll() {
        return customerRepostitory.findAll();
    }

    @Override
    public boolean add(Customer customer) {
        return customerRepostitory.add(customer);
    }

    @Override
    public boolean delete(int id) {
        return customerRepostitory.delete(id);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepostitory.update(customer);
    }

    @Override
    public List<CustomerDto> search(String name) {
        return customerRepostitory.search(name);
    }

    @Override
    public Customer findById(int id) {
        return customerRepostitory.findById(id);
    }

    @Override
    public Customer findByAccountId(int accountId) {
        return customerRepostitory.findByAccountId(accountId);
    }
}
