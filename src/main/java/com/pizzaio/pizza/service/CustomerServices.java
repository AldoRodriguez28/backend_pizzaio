package com.pizzaio.pizza.service;

import com.pizzaio.pizza.persistence.entity.CustomerEntity;
import com.pizzaio.pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    private  final CustomerRepository customerRepository;

    @Autowired
    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerEntity findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }
}
