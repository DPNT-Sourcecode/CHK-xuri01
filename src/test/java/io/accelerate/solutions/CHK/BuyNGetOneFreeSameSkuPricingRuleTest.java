package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyNGetOneFreeSameSkuPricingRuleTest {

    @Test
    void shouldApplyFreeItem(){
        var rule = new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3);
        var items = new HashMap<String, Integer>();
        
    }

}