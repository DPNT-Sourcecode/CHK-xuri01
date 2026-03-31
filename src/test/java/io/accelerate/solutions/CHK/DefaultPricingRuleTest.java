package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void shouldCalculatePriceForMultipleItem(){
        items.put("C", 3);
        int result = pricingRule.calculate(items);
        assertEquals(60, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 20",
            "3, 60"
    })
    void shouldCalculate(int count, int expected){
        items.put("C", count);
        assertEquals(expected, pricingRule.calculate());
    }

    @Test
    void shouldIgnoreOtherSkus(){
        items.put("A", 5);
        items.put("B", 2);
        int result = pricingRule.calculate(items);
        assertEquals(0, result);
    }

    @Test
    void shouldWorkWithDifferentSkuAndPrice(){
        DefaultPricingRule rule = new DefaultPricingRule("D", 15);
        items.put("D", 4);

        int result = rule.calculate(items);
        assertEquals(60, result);
    }
}
