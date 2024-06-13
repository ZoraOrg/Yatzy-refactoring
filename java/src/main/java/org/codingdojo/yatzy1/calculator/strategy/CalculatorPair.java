package org.codingdojo.yatzy1.calculator.strategy;

//Calculator of pair of Twos, Threes, Fours using design pattern Strategy
public class CalculatorPair {
    private final CalculatorPairStrategy calculatorPairStrategy;

    public CalculatorPair(CalculatorPairStrategy calculatorFrequencyStrategy) {
        this.calculatorPairStrategy = calculatorFrequencyStrategy;
    }

    public int calculate(int d1, int d2, int d3, int d4, int d5) {
        return calculatorPairStrategy.calculateScoreForPairOfDice(d1, d2, d3, d4, d5);
    }
}
