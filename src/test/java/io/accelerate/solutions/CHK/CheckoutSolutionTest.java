package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutSingleItems() {
        assertEquals(50, checkoutSolution.checkout("A"), "Single A should cost 50");
        assertEquals(30, checkoutSolution.checkout("B"), "Single B should cost 30");
        assertEquals(20, checkoutSolution.checkout("C"), "Single C should cost 20");
        assertEquals(15, checkoutSolution.checkout("D"), "Single D should cost 15");
    }

    @Test
    public void testMultipleItemsWithOffers() {
        assertEquals(50 + 30, checkoutSolution.checkout("AB"), "A+B should cost 80");
        assertEquals(20 + 15, checkoutSolution.checkout("CD"), "C + D should 35");
        assertEquals(50 + 30 + 20 + 15, checkoutSolution.checkout("ABCD"), "A + B + C + D  should cost 115");

    }

    @Test
    public void checkoutInvalidItem() {
        assertEquals(-1, checkoutSolution.checkout("E"), "Invalid items should return -1 ");
        assertEquals(-1, checkoutSolution.checkout("AX"), "Any invalid item returns -1 ");
    }

    @Test
    public void checkoutEmptyOrNull(){
        assertEquals(0, checkoutSolution.checkout(""), "Empty String should cost 0 ");
        assertEquals(0, checkoutSolution.checkout(null), "Null String should cost 0 ");

    }

    @Test
    public void checkoutSpecialOffers(){
        assertEquals(130, checkoutSolution.checkout("AAA"));

    }


}


