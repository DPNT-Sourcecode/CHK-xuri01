package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuyXGetYFreePromotionTest {

    @Test
    void shouldApplyPromotion_E_gives_B(){
        var promotion = new BuyXGetYFreePromotion("E", 2, "B");

        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        items.put("B", 1);

        promotion.apply(items);
        assertEquals(0, items.get("B"));
    }

    


}