package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp(){
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutSingleItems(){
        Assertions.assertEquals(50, checkoutSolution.checkout("A"), "Single A should cost 50");
    }

}