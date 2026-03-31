package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FreeBWithEPromotionTest {

    private FreeBWithEPromotion promotion;

    @BeforeEach
    void setUp() {
        promotion = new FreeBWithEPromotion();
    }

    void ShouldNotChangeBWhenNoE() {
        Map<String, Integer> items = new HashMap<>();
        items.put("B", 2);
        promotion.apply(items);
        assertEquals(2, items.get("B"));
    }

    void shouldGiveOneFreeBForTwoE(){
        Map<String, Integer> items = new HashMap<>();
        items.put("E", 2);
        items.put("B", 1);

        promotion.apply(items);
        assertEquals(2, items.get("B"));
    }

}