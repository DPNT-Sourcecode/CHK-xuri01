package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @ParameterizedTest(name = "Single item {0} -> {1} ")
    @CsvSource({"A, 50", "B, 30", "C, 20", "D, 15", "E, 40"})
    public void checkoutSingleItems(String input, int expected) {
        assertEquals(expected, checkoutSolution.checkout(input));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({"AB, 80", "CD, 35", "ABCD, 115"})
    public void testMultipleItemsWithOffers(String items, int expected) {
        assertEquals(expected, checkoutSolution.checkout(items));
    }

    @Test
    public void checkoutInvalidItem() {
        assertEquals(-1, checkoutSolution.checkout("AX"), "Any invalid item returns -1 ");
    }

    @Test
    public void checkoutEmptyOrNull() {
        assertEquals(0, checkoutSolution.checkout(""), "Empty String should cost 0 ");
        assertEquals(0, checkoutSolution.checkout(null), "Null String should cost 0 ");

    }

    @Test
    public void checkoutSpecialOffersA() {
        assertEquals(130, checkoutSolution.checkout("AAA"), "3A should cost 130");
        assertEquals(200, checkoutSolution.checkout("AAAAA"), "5A should cost 200");
        ;
    }

    @Test
    public void checkoutSpecialOffersB() {
        assertEquals(45, checkoutSolution.checkout("BB"), "3A should cost 130");
    }


    @Test
    public void checkoutPromotionEFreeB() {
        assertEquals(80, checkoutSolution.checkout("EE"), "2E should cost 80 (no B to discount)");
        assertEquals(80, checkoutSolution.checkout("EEB"), "2E + 1B + B is free ");
        assertEquals(110, checkoutSolution.checkout("EEBB"), "2E + 2B -> Only 1 B is free ");
        assertEquals(160, checkoutSolution.checkout("EEEEBB"), "4E + 2  free B ");
    }

    @ParameterizedTest(name = "F count {0} -> {1} ")
    @CsvSource({
            "F, 10",
            "FF, 20",
            "FFF, 20",
            "FFFF, 30",
            "FFFFFF, 40"
    })
    void shouldApplyFPromotion(String items, int expected) {
        assertEquals(expected, checkoutSolution.checkout(items));
    }
}


