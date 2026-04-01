package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyNGetOneFreeSameSkuPricingRuleTest {

    @Test
    void shouldApplyFreeItem() {
        var rule = new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3);
        var items = new HashMap<String, Integer>();
        items.put("F", 3);
        assertEquals(20, rule.calculate(items));
    }

    @Test
    void shouldApplyMultipleFreeItems(){
        var rule = new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3);
        var items = new HashMap<String, Integer>();
        items.put("F", 6);
        assertEquals(40, rule.calculate(items));
    }

    @Test
    void shouldHandleNoFreeItems(){
        var rule = new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3);
        var items = new HashMap<String, Integer>();
        items.put("F", 2);
        assertEquals(20, rule.calculate(items));
    }
}