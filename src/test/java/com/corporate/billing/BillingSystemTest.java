package com.corporate.billing;

import com.corporate.billing.entity.Customer;
import com.corporate.billing.receipt.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingSystemTest {
    @BeforeEach
    public void before() {

    }

    @Test
    public void shouldBeAbleToCalculateBillingAmount(){
        CustomerType regularCustomer = new RegularCustomer();
        Customer customer = new Customer(123, regularCustomer);
        BillingSystem billingSystem = new BillingSystem();

        Bill bill = billingSystem.calculateBillFor(customer, 2000.00);

        assertEquals(2000.00 , bill.getBillingAmount());
    }

}
