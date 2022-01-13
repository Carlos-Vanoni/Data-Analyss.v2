package com.carlosvanoni.challange.repository;

import com.carlosvanoni.challange.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final List<Customer> customerList = new ArrayList<>();

    public CustomerRepository() {
    }

    public Boolean save(Customer customer) {
        return customerList.add(customer);
    }

    public List<Customer> getList() {
        return customerList;
    }

    public void clear() {
        customerList.clear();
    }
}