package io.accelerate.solutions.CHK;

import java.util.Map;

public class CheckoutContext {
    private final Map<String, Integer> itemCount;
    private int total = 0;

    public CheckoutContext(Map<String, Integer> itemCount) {
        this.itemCount = itemCount;
    }

    public Map<String, Integer> getItemCounts(){
        return itemCounts;
    }
}

