package com.corporate.billing;

import com.corporate.billing.service.DiscountService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DiscountServiceTest {
    private DiscountService discountService = new DiscountService();

    @Test
    public void getAllDiscounts() {
        Map<String, List<Slab>> expectedSlabs = createExpectedSlabs();

        Map<String, List<Slab>> actualDiscountSlabs = discountService.loadAllDiscounts();

        assertEquals(expectedSlabs, actualDiscountSlabs);
    }

    private Map<String, List<Slab>> createExpectedSlabs() {
        Map<String, List<Slab>> expectedDiscounts = new HashMap<>();
        List<Slab> discountSlabs = getRegularCustomerSlabs();
        List<Slab> premiumCustomerSlabs = getPremiumCustomerSlabs();
        expectedDiscounts.put(CustomerType.REGULAR, discountSlabs);
        expectedDiscounts.put(CustomerType.PREMIUM, premiumCustomerSlabs);
        return expectedDiscounts;
    }

    private List<Slab> getPremiumCustomerSlabs() {
        List<Slab> premiumCustomerSlabs = new ArrayList<>();
        premiumCustomerSlabs.add(new Slab(0.0, 4000.0, 10.0));
        premiumCustomerSlabs.add(new Slab(4000.0, 8000.0, 15.0));
        premiumCustomerSlabs.add(new Slab(8000.0, 12000.0, 20.0));
        premiumCustomerSlabs.add(new Slab(12000.0, new Double(Integer.MAX_VALUE), 30.0));
        return premiumCustomerSlabs;
    }


    @Test
    public void getDiscountsForRegularCustomer() {
        List<Slab> discountSlabs = getRegularCustomerSlabs();

        List<Slab> actualDiscountSlabs = discountService.getDiscountFor(CustomerType.REGULAR);

        assertEquals(discountSlabs, actualDiscountSlabs);
    }

    private List<Slab> getRegularCustomerSlabs() {
        List<Slab> discountSlabs = new ArrayList<>();
        discountSlabs.add(new Slab(0.0, 5000.0, 0.0));
        discountSlabs.add(new Slab(5000.0, 10000.0, 10.0));
        discountSlabs.add(new Slab(10000.0, new Double(Integer.MAX_VALUE), 20.0));
        return discountSlabs;
    }

    @Test
    public void getDiscountsForPremiumCustomer() {
        List<Slab> discountSlabs = getPremiumCustomerSlabs();

        List<Slab> actualDiscountSlabs = discountService.getDiscountFor(CustomerType.PREMIUM);

        assertEquals(discountSlabs, actualDiscountSlabs);
    }

    @Test
    public void getDiscountsForCategoryWhichIsNotYetDefined() {
        List<Slab> actualDiscountSlabs = discountService.getDiscountFor("UnknownCategory");

        assertTrue(actualDiscountSlabs.isEmpty());
    }


}