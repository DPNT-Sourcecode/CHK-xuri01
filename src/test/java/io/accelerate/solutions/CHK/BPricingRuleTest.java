package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BPricingRuleTest {

    private BPricingRule pricingRule;
    private Map<String, Integer> items;

    @BeforeEach
    void setup() {
        pricingRule = new BPricingRule();
        items = new HashMap<>();
    }

    @ParameterizedTest(name = "B count {0} -> {1}")
    @CsvSource({
            "0,0",
            "1, 30",
            "2, 45",
            "3, 75",
            "4, 90",
            "5, 120"
    })
    void shouldCalculateBPricesCorrectly(int count, int expected) {
        items.put("B", count);
        int result = pricingRule.calculate(items);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    void shouldIgnoreOtherSkus(){

    }

}