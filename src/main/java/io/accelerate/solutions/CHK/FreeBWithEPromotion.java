package io.accelerate.solutions.CHK;

import java.util.Map;

public class FreeBWithEPromotion  implements Promotion{
    @Override
    public void apply(Map<String, Integer> itemCounts) {
        int numberOfEElements = itemCounts.getOrDefault("E", 0);
        int freeBItems = numberOfEElements / 2;
        int currentBItems = itemCounts.getOrDefault("B", 0);
        int chargeableBItems = Math.max(0, currentBItems - freeBItems);
        itemCounts.put("B", chargeableBItems);
    }
}

