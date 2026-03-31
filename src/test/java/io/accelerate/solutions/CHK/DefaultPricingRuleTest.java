package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPricingRuleTest {

    private DefaultPricingRule pricingRule;
    private Map<String, Integer> items;

    @BeforeEach
    void setup(){
        pricingRule = new DefaultPricingRule("C", 20);
        items = new HashMap<>();
    }

    @Test
    void shouldCalculatePriceForSingleItem(){
        items.put("C", 1);
        int result = pricingRule.calculate(items);
        assertEquals(20, result);
    }
}