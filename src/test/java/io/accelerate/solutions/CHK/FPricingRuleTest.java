package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FPricingRuleTest {

    private FPricingRule pricingRule;

    @BeforeEach
    void setUp() {
        pricingRule = new FPricingRule();
    }

    @Test
    void shouldCalculateReturnZeroWhenNoFItems() {
        Map<String, Integer> items = new HashMap<>();
        int result = pricingRule.calculate(items);
        assertEquals(0, result);
    }

    @Test
    void shouldCalculatePriceForSingleF() {
        Map<String, Integer> items = new HashMap<>();
        items.put("F", 1);
        int result = pricingRule.calculate(items);
        assertEquals(10, result);
    }

    @Test
    void shouldCalculatePriceForSTwoFs() {
        Map<String, Integer> items = new HashMap<>();
        items.put("F", 2);
        int result = pricingRule.calculate(items);
        assertEquals(20, result);
    }

    @Test
    void shouldApplyFreeItemForThreeFs() {
        Map<String, Integer> items = new HashMap<>();
        items.put("F", 3);
        int result = pricingRule.calculate(items);
        assertEquals(20, result);
    }

    @Test
    void shouldApplyFreeItemForThreeItems(){

    }

}
