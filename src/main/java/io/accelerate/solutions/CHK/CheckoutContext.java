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

    public int getCount(String sku){
        return getItemCounts().getOrDefault(sku, 0);
    }

    public void setCount(String sku, int value){
        itemCounts.put(sku, value)
    }
}



