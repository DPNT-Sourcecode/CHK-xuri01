package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (skus == null) return -1;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;

        for (char sku : skus.toCharArray()) {
            switch (sku) {
                case 'A':
                    countA++;
                    break;
                case 'B':
                    countB++;
                    break;
                case 'C':
                    countC++;
                    break;
                case 'D':
                    countD++;
                    break;
                default:
                    return -1;
            }

            int total = 0;
            total += (countA / 3) * 130 + (countA % 3) * 50;
            total += (countB / 2) * 45 + (countB % 2) * 30;
            total += countC * 20;
            total += countD * 15;
        }


        return null;
    }
}

