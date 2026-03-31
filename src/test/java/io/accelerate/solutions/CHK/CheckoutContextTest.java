package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutContextTest {

    private Map<String, Integer> items;
    private CheckoutContext context;

    @BeforeEach
    void setUp() {
        items = new HashMap<>();
        items.put("A", 2);
        items.put("B", 2);
        context = new CheckoutContext(items);
    }

    @Test
    void shouldReturnCorrectItemCounts() {
        assertEquals(2, context.getCount("A"));
        assertEquals(1, context.getCount("B"));
    }

    void shouldUpdateItem(){
        
    }

}