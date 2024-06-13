package org.codingdojo.yatzy1.calculator.facade;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CalculatorSmallStraight {
    public int getScoreStraightValue(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dicesScores = Stream.of(d1, d2, d3, d4, d5).sorted().toList();
        boolean areEqual = Arrays.equals(Stream.of(1, 2, 3, 4, 5).toArray(), dicesScores.toArray());
        return areEqual ? dicesScores.stream().mapToInt(Integer::intValue).sum() : 0;
    }
}