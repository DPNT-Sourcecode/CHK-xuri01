package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GroupDiscountPricingRuleTest {

    @Test
    void shouldApplyGroupDiscountAndRemainingItemsCorrectly() {
        var skus = List.of("A", "B", "C");
        var unitPrices = Map.of("A", 50, "B", 30, "C", 20);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 3, 100);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1);
        itemCounts.put("B", 1);
        itemCounts.put("C", 1);

        int result = rule.calculate(itemCounts);
        assertEquals(100, result);
        assertEquals(0, itemCounts.get("A"));
        assertEquals(0, itemCounts.get("B"));
        assertEquals(0, itemCounts.get("C"));
    }

    @Test
    void shouldApplyMultipleGroupsAndRemainingItems() {
        List<String> skus = List.of("A", "B", "C");

        var unitPrices = Map.of("A", 50, "B", 30, "C", 20);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 3, 100);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 2);
        itemCounts.put("B", 2);
        itemCounts.put("C", 1);

        int result = rule.calculate(itemCounts);
        assertEquals(150, result);
    }

    @Test
    void shouldHandleNoApplicableItems() {
        List<String> skus = List.of("A", "B", "C");

        var unitPrices = Map.of("A", 50, "B", 30, "C", 20);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 3, 100);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("D", 2);

        int result = rule.calculate(itemCounts);
        assertEquals(0, result);
    }

    @Test
    void shouldHandlePartialGroupOnly() {
        List<String> skus = List.of("A", "B", "C");
        var unitPrices = Map.of("A", 50, "B", 30, "C", 20);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 3, 100);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1);
        itemCounts.put("B", 1);

        int result = rule.calculate(itemCounts);
        assertEquals(0, result);
    }

    @Test
    void shouldThrowExceptionWhenUnitPriceIsMissing() {
        List<String> skus = List.of("A", "B");
        var unitPrices = Map.of("A", 50);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 2, 70);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1);
        itemCounts.put("B", 1);

        assertThrows(NullPointerException.class, () -> rule.calculate(itemCounts));
    }

    @Test
    void shouldReturnZeroForEmptyCart() {
        GroupDiscountPricingRule rule = new GroupDiscountPricingRule(List.of("A", "B"),
                Map.of("A", 50, "B", 30),
                2,
                70);
    }

    @Test
    void shouldIgnoreZeroCounts() {
        var rule = new GroupDiscountPricingRule(
                List.of("A", "B"),
                Map.of("A", 50, "B", 30),
                2,
                70
        );
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 0);
        itemCounts.put("B", 0);
        int result = rule.calculate(itemCounts);

        assertEquals(0, result);
    }

    @Test
    void shouldHandleLargeQuantitiesEfficiently() {
        var rule = new GroupDiscountPricingRule(
                List.of("A", "B"),
                Map.of("A", 10),
                5,
                40
        );
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1000);
        int result = rule.calculate(itemCounts);

        assertEquals(8000, result);
    }

    @Test
    void shouldGroupMostExpensiveItemsFirst() {
        var rule = new GroupDiscountPricingRule(
                List.of("A", "B", "C"),
                Map.of("A", 100, "B", 50, "C", 10),
                2,
                120
        );
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1);
        itemCounts.put("B", 1);
        itemCounts.put("C", 1);

        int result = rule.calculate(itemCounts);

        assertEquals(130, result);
    }

    @Test
    void shouldThrowExceptionWhenItemCountsToNull() {
        List<String> skus = List.of("A");
        var unitPrices = Map.of("A", 10);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 2, 15);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 1);
        itemCounts.put("B", 1);

        assertThrows(NullPointerException.class, () -> rule.calculate(null));
    }

    @Test
    void shouldNotThrowForValidInput() {
        List<String> skus = List.of("A");
        var unitPrices = Map.of("A", 10);
        var rule = new GroupDiscountPricingRule(skus, unitPrices, 2, 15);
        var itemCounts = new HashMap<String, Integer>();
        itemCounts.put("A", 2);

        assertDoesNotThrow(() -> rule.calculate(itemCounts));
    }


}
