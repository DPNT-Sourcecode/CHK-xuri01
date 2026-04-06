package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupDiscountPricingRule implements PricingRule {

    private final List<String> skus;
    private final Map<String, Integer> unitPrices;
    private final int groupSize;
    private final int groupPrice;

    public GroupDiscountPricingRule(List<String> skus, Map<String, Integer> unitPrices, int groupSize, int groupPrice) {
        this.skus = skus;
        this.unitPrices = unitPrices;
        this.groupSize = groupSize;
        this.groupPrice = groupPrice;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        if (itemCounts == null) {
            throw new NullPointerException("itemCounts cannot be null");
        }
        List<Integer> prices = new ArrayList<>();

        for(String sku : skus){
            int count = itemCounts.getOrDefault(sku, 0);
            Integer skuPrice = unitPrices.get(sku);

            if(skuPrice == null) continue;

            for(int i =0; i < count; i++){
                prices.add(skuPrice);
            }

            //sort desc (critical)
            prices.sort((a,b) -> b - a);

            int total = 0;

            while(prices.size() >= groupSize){
                for(int i  =0; i<groupSize; i++){
                    prices.remove(0);
                }
                total += groupPrice;
            }

            //Add remaining items (FULL PRICE)
            for(int price : prices){
                total += price;
            }

            // IMPORTANT: consume all these SKUs
            for(String sku1: skus){
                itemCounts.put(sku, 0);
            }
            return total;
        }

        int total = 0;
        while (true) {
            List<String> available = skus.stream()
                    .filter(sku -> itemCounts.getOrDefault(sku, 0) > 0)
                    .sorted((a, b) -> unitPrices.get(b) - unitPrices.get(a))
                    .toList();

            if (available.size() < groupSize) {
                break;
            }

            for (int i = 0; i < groupSize; i++) {
                String sku = available.get(i);
                itemCounts.put(sku, itemCounts.get(sku) - 1);
            }
            total += groupPrice;
        }

        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);
            total += count * unitPrices.get(sku);
            itemCounts.put(sku, 0);
        }
        return total;
    }

}

