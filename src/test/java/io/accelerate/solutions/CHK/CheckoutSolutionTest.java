package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    public void setUp(){
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutValidInputs(){
        assertThat(checkoutSolution.checkout("A,B,C,D"))
    }

}