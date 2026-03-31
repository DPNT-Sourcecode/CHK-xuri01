package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FreeBWithEPromotionTest {

    private FreeBWithEPromotion promotion;

    @BeforeEach
    void setUp() {
        promotion = new FreeBWithEPromotion();
    }

    @Test
    void ShouldNotChangeBWhenNoE() {
        Map<String, Integer> items = new HashMap<>();
        items.put("B", 2);
        promotion.apply(items);
        assertEquals(2, items.get("B"));
    }

    @Test
    void shouldGiveOneFreeBForTwoE() {
        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        items.put("B", 1);

        promotion.apply(items);
        assertEquals(0, items.get("B"));
    }

    @Test
    void shouldReduceBPartiallyWhenMoreBThanFreeItems() {
        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        items.put("B", 3);

        promotion.apply(items);
        assertEquals(2, items.get("B"));
    }

    @Test
    void shouldNotAllowBNegativeValues() {
        Map<String, Integer> items = new HashMap<>();
        items.put("E", 4);
        items.put("B", 1);

        promotion.apply(items);
        assertEquals(0, items.get("B"));
    }

    @Test
    void shouldHandleNoBInBasket(){
        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        promotion.apply(items);
        assertEquals(0, items.getOrDefault("B", 0));
    }
}
