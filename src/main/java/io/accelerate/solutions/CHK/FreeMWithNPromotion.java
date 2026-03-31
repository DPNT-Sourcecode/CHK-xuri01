package io.accelerate.solutions.CHK;

import java.util.Map;

public class FreeMWithNPromotion implements Promotion{
    @Override
    public void apply(Map<String, Integer> itemCounts) {
        int nCount = itemCounts.getOrDefault("N", 0);
        int freeM = nCount / 3;
        int currentM = itemCounts.getOrDefault("M", 0);
        itemCounts.put("M", Math.max(0, currentM - freeM));
    }
}




