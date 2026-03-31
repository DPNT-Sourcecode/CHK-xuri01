package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BPricingRuleTest {

    private BPricingRule pricingRule;
    private Map<String, Integer> items;

    @BeforeEach
    void setup(){
        pricingRule = new BPricingRule();
        items = new HashMap<>();
    }

    @ParameterizedTest(name = "B count {0} -> {1}")
    @BeforeEach({
            "0,0",
            "1, 30",
            ""
    })
    void shouldCalculateBPricesCorrectly(){

    }

}