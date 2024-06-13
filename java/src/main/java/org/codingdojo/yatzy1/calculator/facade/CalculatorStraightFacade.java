package org.codingdojo.yatzy1.calculator.facade;

import org.codingdojo.YatzyCategory;

/**
 * Calculator of small and large straight using design pattern Strategy
 */
public class CalculatorStraightFacade {
    private final CalculatorSmallStraight calculatorSmallStraight;
    private final CalculatorLargeStraight calculatorLargeStraight;

    public CalculatorStraightFacade() {
        this.calculatorSmallStraight = new CalculatorSmallStraight();
        this.calculatorLargeStraight = new CalculatorLargeStraight();
    }

    public int getScoreStraightValue(YatzyCategory category, int d1, int d2, int d3, int d4, int d5) {
        int straightScore = 0;
        if (category.equals(YatzyCategory.SMALL_STRAIGHT)) {
            straightScore = calculatorSmallStraight.getScoreStraightValue(d1, d2, d3, d4, d5);
        } else if (category.equals(YatzyCategory.LARGE_STRAIGHT)) {
            straightScore = calculatorLargeStraight.getScoreStraightValue(d1, d2, d3, d4, d5);
        }
        return straightScore;
    }
}