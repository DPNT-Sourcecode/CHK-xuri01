package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp(){
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutSingleItems(){
        assertEquals(50, checkoutSolution.checkout("A"), "Single A should cost 50");
        assertEquals(30, checkoutSolution.checkout("B"), "Single B should cost 30");
        assertEquals(20, checkoutSolution.checkout("C"), "Single C should cost 20");
        assertEquals(15, checkoutSolution.checkout("D"), "Single D should cost 15");
    }

    @Test
    public void testSpecialOffers(){
        assertEquals(130, checkoutSolution.checkout("AAA"), "3A offer should cost 130");
        assertEquals(45, checkoutSolution.checkout("BB"), "2B should offer cost 45");
    }

    @Test
    public void testMultipleItemsWithOffers(){
        assertEquals(160, checkoutSolution.checkout("AAAB"), "3A+B should cost 160");
        assertEquals(260, checkoutSolution.checkout("AAABBD"), "6A should cost 260");
        assertEquals(90, checkoutSolution.checkout("BBBB"), "48 should cost 90");

    }

}
