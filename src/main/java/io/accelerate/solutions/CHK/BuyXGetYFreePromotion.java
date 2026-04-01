package io.accelerate.solutions.CHK;

import java.util.Map;

public class BuyXGetYFreePromotion implements Promotion {
    private final String triggerSku;
    private final int triggerQuantity;
    private final String freeSku;


    public BuyXGetYFreePromotion(String triggerSku, int triggerQuantity, String freeSku) {
        this.triggerSku = triggerSku;
        this.triggerQuantity = triggerQuantity;
        this.freeSku = freeSku;
    }

    @Override
    public void apply(Map<String, Integer> itemCounts) {
        int triggerCount = itemCounts.getOrDefault(triggerSku, 0);
        int freeItems = triggerCount / triggerQuantity;

        int currentFreeItemCount = itemCounts.getOrDefault(freeSku, 0);
        int chargeableItems = Math.max(0, currentFreeItemCount - freeItems);
        itemCounts.put(freeSku, chargeableItems);
    }
}

