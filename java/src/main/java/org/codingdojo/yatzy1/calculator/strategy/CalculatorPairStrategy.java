package org.codingdojo.yatzy1.calculator.strategy;

/**
 * Calculator of pair of Twos, Threes, Fours using design pattern Strategy
 */
interface CalculatorPairStrategy {
    int calculateScoreForPairOfDice(int d1, int d2, int d3, int d4, int d5);
}
