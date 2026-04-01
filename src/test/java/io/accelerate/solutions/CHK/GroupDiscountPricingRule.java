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

        //1. Collect all applicable items
        for(String sku: skus){
            int count = itemCounts.getOrDefault(sku, 0);
            for(int i=0; i < count; i++){
                allItems.add(unitPrices.get(sku));
            }
        }
        //2 - Sort descending (expensive first -> best discount)
        allItems.sort(Comparator.reverseOrder());
        int total = 0;
        int index = 0;

        //3 - Apply group discount
        while(index + groupSize <= allItems.size()){
            total += groupPrice;
            index += groupSize;
        }

        //4 - Remaining items -> Normal price
        while(index < allItems.size()){
            total += allItems.get(index);
            index ++;
        }

        //5 - Zero out consumed SKUs. So they are not double counted
        for(String sku)


        return 0;
    }

}





