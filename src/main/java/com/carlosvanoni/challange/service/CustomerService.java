package com.carlosvanoni.challange.service;

import com.carlosvanoni.challange.model.Customer;
import com.carlosvanoni.challange.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CharacterRefactor refactor;


    public List<Customer> getList() {
        return repository.getList();
    }

    public int getCustomerAmountString() {
        return getList().size();
    }

    public void processData (String[] lineSplited) {
        String customerName = refactor.removeCedilhaOnPersonName(lineSplited);
        String customerArea = lineSplited[lineSplited.length - 1];
        Customer customer = new Customer(Long.parseLong(lineSplited[0]), customerName, customerArea);
        if (!repository.getList().contains(customer))
            repository.save(customer);
    }
}