package io.accelerate.solutions.CHK;

import java.util.Map;

public class FreeMWithNPromotion implements Promotion{
    @Override
    public void apply(Map<String, Integer> itemCounts) {

    }

    @Override
    public int apply(Map<String, Integer> itemCounts, int currentTotal) {
        return Promotion.super.apply(itemCounts, currentTotal);
    }
}

