package org.codingdojo.yatzy1.calculator.strategy;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorThreePairStrategy implements CalculatorPairStrategy {

    @Override
    public int calculateScoreForPairOfDice(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> pairs = Stream.of(d1, d2, d3, d4, d5).sorted(Comparator.reverseOrder())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return pairs.entrySet().stream().filter(a -> a.getValue() >= 3).mapToInt(d-> d.getKey() * 3).sum();
    }
}
