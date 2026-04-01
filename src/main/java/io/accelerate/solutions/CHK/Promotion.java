package io.accelerate.solutions.CHK;

import java.util.Map;

public interface Promotion {

    void apply(Map<String, Integer> itemCounts);

    /*default int apply(Map<String, Integer> itemCounts, int currentTotal) {
        apply(itemCounts);
        return currentTotal;
    }*/


}

