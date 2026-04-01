package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuyXGetYFreePromotionTest {

    @Test
    void shouldApplyPromotion_E_gives_B() {
        var promotion = new BuyXGetYFreePromotion("E", 2, "B");

        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        items.put("B", 1);

        promotion.apply(items);
        assertEquals(0, items.get("B"));
    }

    @Test
    void shouldApplyPromotion_N_Gives_M() {
        var promotion = new BuyXGetYFreePromotion("N", 3, "M");

        Map<String, Integer> items = new HashMap<>();
        items.put("N", 3);
        items.put("M", 2);

        promotion.apply(items);
        assertEquals(1, items.get("M"));
    }

    @Test
    void shouldApplyPromotion_R_Gives_Q(){
        var promotion = new BuyXGetYFreePromotion("N", 3, "M");

        Map<String, Integer> items = new HashMap<>();
        items.put("R", 3);
        items.put("Q", 2);

        promotion.apply(items);
        assertEquals(1, items.get("M"));
    }

}