package com.corporate.billing.entity;

import com.corporate.billing.CustomerType;

public class Customer {
    private int id;

    public Customer(int id, CustomerType regularCustomer) {
        this.id = id;
    }
}
