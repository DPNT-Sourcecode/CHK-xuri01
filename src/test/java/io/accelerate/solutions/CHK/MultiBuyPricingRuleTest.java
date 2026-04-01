package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultiBuyPricingRuleTest {

    void shouldApplyMultipleOffersForA() {
        var rule = new MultiBuyPricingRule("A", 50, Map.of(5, 200, 3, 130));
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 5);
        assertEquals(200, rule.calculate(items));
    }

    void should

}