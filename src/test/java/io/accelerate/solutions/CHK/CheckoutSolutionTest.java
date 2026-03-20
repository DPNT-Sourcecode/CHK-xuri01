package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp(){
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutSingleItems(){
        Assertions.assertEquals(50, checkoutSolution.checkout("A"), "Single A should cost 50");
        Assertions.assertEquals(30, checkoutSolution.checkout("B"), "Single B should cost 30");
        Assertions.assertEquals(20, checkoutSolution.checkout("C"), "Single C should cost 20");
        Assertions.assertEquals(15, checkoutSolution.checkout("D"), "Single D should cost 15");
    }

}