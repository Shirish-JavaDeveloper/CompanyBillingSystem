package com.corporate.billing.service;

import com.corporate.billing.CustomerType;

public class Customer {
    private int id;
    private CustomerType customerType;

    public Customer(int id, CustomerType customerType) {
        this.id = id;
        this.customerType = customerType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }
}
