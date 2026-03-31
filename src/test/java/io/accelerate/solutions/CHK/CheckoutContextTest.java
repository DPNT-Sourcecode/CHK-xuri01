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

    @Test
    void shouldReturnZeroForMissingSku(){
        assertEquals(0, context.getCount("C"));
    }

    @Test
    void shouldUpdateItemCount(){
        context.setCount("A", 5);
        assertEquals(5, context.getCount("A"));
    }

    @Test
    void shouldAddNewSkuWhenSettingCount(){
        context.setCount("C", 3);
        assertEquals(3, context.getCount("C"));
    }

    @Test
    void shouldAccumulateTotal(){
        context.addToTotal(50);
        context.addToTotal(30);
        assertEquals(80, context.getTotal());
    }

    @Test
    void shouldStartWithZeroTotal(){
        assertEquals(0, context.getTotal());
    }

    @Test
    void shouldReflectExternalMapChanges(){
        items.put("A", 10);
        assertEquals(10, context.getCount("");
    }
}
