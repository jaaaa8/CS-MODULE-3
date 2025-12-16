package service;

import entity.Customer;
import repository.CustomerRepository;
import repository.IRepostitory;

import java.util.List;

public class CustomerService implements IService<Customer>{
    IRepostitory<Customer> customerRepostitory= new CustomerRepository();
    @Override
    public List<Customer> findAll() {
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
    public boolean search(Customer customer) {
        return customerRepostitory.search(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepostitory.findById(id);
    }

    public Customer findByAccountId(int accountId){
        return ((CustomerRepository) customerRepostitory).findByAccountId(accountId);
    }
}
