package org.codingdojo.yatzy1.calculator.template;

import java.util.List;

// calculator of Ones, Twos, Threes, Fours, Fives, Sixes using template design pattern
public abstract class CalculatorTemplate {
    public final int calculateTotal(List<Integer> dices) {
        return dices.stream()
            .mapToInt(d -> d == getValueToSum() ? getValueToSum() : 0)
            .sum();
    }

    protected abstract int getValueToSum();
}
