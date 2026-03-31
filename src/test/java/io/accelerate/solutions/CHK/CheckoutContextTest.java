package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutContextTest {

    private Map<String, Integer>  items;
    private CheckoutContext context;

    @BeforeEach
    void setUp(){
        items = new HashMap<>();
        items.put("A", 2);
        items.put("B", 2);
        context = new CheckoutContext(items);
    }

    void shouldReturnCorrectItemCounts(){
        
    }

}