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

}