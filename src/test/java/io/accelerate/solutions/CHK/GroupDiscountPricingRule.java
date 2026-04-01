package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<Integer> allItems = new ArrayList<>();

        for(String sku: skus){
            int count = itemCounts.getOrDefault(sku, 0);
            for(int i=0; i < count; i++){
                allItems.add(unitPrices.get(sku));
            }
        }
        allItems.sort(Comparator.reverseOrder());
        int total = 0;
        

        return 0;
    }

}



