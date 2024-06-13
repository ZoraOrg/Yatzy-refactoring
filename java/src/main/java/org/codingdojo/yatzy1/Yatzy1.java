package org.codingdojo.yatzy1;

import org.codingdojo.YatzyCategory;
import org.codingdojo.yatzy1.calculator.strategy.*;
import org.codingdojo.yatzy1.calculator.template.*;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class allow to calculate scores of Yatzy dice game
 */
public class Yatzy1 {

    //The player scores the sum of all dice, no matter what they read
    public int calculateScoreOfChanceCategory(int d1, int d2, int d3, int d4, int d5) {
        IntStream stream = IntStream.of(d1, d2, d3, d4, d5);
        return stream.reduce(0, (a, b) -> a + b);
    }

    //If all dice have the same number, the player scores 50 points
    public int calculateScoreOfYatzyCategory(int d1, int d2, int d3, int d4, int d5) {
        Long count = Stream.of(d1, d2, d3, d4, d5).distinct().count();
        if (count == 1) return 50;
        else return 0;
    }

    //The player scores the sum of the dice that reads one
    public int calculateScoreOfOnesCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.ONES, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the dice that reads two
    public int calculateScoreOfOTwosCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.TWOS, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the dice that reads three
    public int calculateScoreOfThreesCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.THREES, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the dice that reads four
    public int calculateScoreOfFoursCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.FOURS, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the dice that reads five
    public int calculateScoreOfFivesCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.FIVES, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the dice that reads six
    public int calculateScoreOfSixesCategory(int d1, int d2, int d3, int d4, int d5) {
        return calculateScoreOfSpecificValue(YatzyCategory.SIXES, List.of(d1, d2, d3, d4, d5));
    }

    //The player scores the sum of the two highest matching dice
    public int calculateScoreOfPairCategory(int d1, int d2, int d3, int d4, int d5) {
        return scorePairValue(YatzyCategory.PAIR, d1, d2, d3, d4, d5);
    }

    //If there are two pairs of dice with the same number, the player scores the sum of these dice
    public int calculateScoreOfTwoPairCategory(int d1, int d2, int d3, int d4, int d5) {
        return scorePairValue(YatzyCategory.TWO_PAIRS, d1, d2, d3, d4, d5);
    }

    //If there are three dice with the same number, the player scores the sum of these dice
    public int calculateScoreOfThreeOfAKindCategory(int d1, int d2, int d3, int d4, int d5) {
        return scorePairValue(YatzyCategory.THREE_OF_A_KIND, d1, d2, d3, d4, d5);
    }

    //If there are four dice with the same number, the player scores the sum of these dice.
    public int calculateScoreOfFourOfAKindCategory(int d1, int d2, int d3, int d4, int d5) {
        return scorePairValue(YatzyCategory.FOUR_OF_A_KIND, d1, d2, d3, d4, d5);
    }

    //The player scores the sum of the dice that reads one, two, three, four, five or six, respectively
    public static int calculateScoreOfSpecificValue(YatzyCategory category, List<Integer> dices) {
        CalculatorTemplate calculatorTemplate = null;
        switch (category) {
            case ONES -> calculatorTemplate = new CalculatorOfOnesTemplate();
            case TWOS -> calculatorTemplate = new CalculatorOfTwosTemplate();
            case THREES -> calculatorTemplate = new CalculatorOfThreesTemplate();
            case FOURS -> calculatorTemplate = new CalculatorOfFoursTemplate();
            case FIVES -> calculatorTemplate = new CalculatorOfFivesTemplate();
            case SIXES -> calculatorTemplate = new CalculatorOfSixesTemplate();
        }
        return calculatorTemplate.calculateTotal(dices);
    }

    //If there are a number pairs of dice with the same number, the player scores the sum of these dice
    public static int scorePairValue(YatzyCategory category, int d1, int d2, int d3, int d4, int d5) {
        CalculatorPair calculatorPair = null;
        switch (category) {
            case PAIR -> calculatorPair = new CalculatorPair(new CalculatorOnePairStrategy());
            case TWO_PAIRS -> calculatorPair = new CalculatorPair(new CalculatorTwoPairStrategy());
            case THREE_OF_A_KIND -> calculatorPair = new CalculatorPair(new CalculatorThreePairStrategy());
            case FOUR_OF_A_KIND -> calculatorPair = new CalculatorPair(new CalculatorFourPairStrategy());
        }
        return calculatorPair.calculate(d1, d2, d3, d4, d5);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



