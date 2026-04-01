package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FreeMWithNPromotionTest {
    private FreeMWithNPromotion promotion;

    @BeforeEach
    void setUp(){
        promotion = new FreeMWithNPromotion();
    }

    @Test
    void shouldNotChangeMWhenLessThanThreeN(){
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("N", 2);
        itemCounts.put("M", 3);

        promotion.apply(itemCounts);
        assertEquals(3, itemCounts.get("M"));

    }

    @Test
    void shouldApplyOneFreeMWhenThreeN(){
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("N", 3);
        itemCounts.put("M", 2);

        promotion.apply(itemCounts);
        assertEquals(1, itemCounts.get("M"));

    }

    @Test
    void shouldApplyMultipleFreeM(){
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("N", 6);
        itemCounts.put("M", 5);

        promotion.apply(itemCounts);
        assertEquals(3, itemCounts.get("M"));
    }

    @Test
    void shouldNotGoBelowZero(){
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("N", 6);
        itemCounts.put("M", 1);

        promotion.apply(itemCounts);
        assertEquals(0, itemCounts.get("M"));
    }

    @Test
    void shouldHandleMissingN(){
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("M", 4);

        promotion.apply(itemCounts);
        assertEquals(4, itemCounts.get("M"));
    }

}