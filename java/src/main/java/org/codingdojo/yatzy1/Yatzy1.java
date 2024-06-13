package org.codingdojo.yatzy1;

import org.codingdojo.YatzyCategory;
import org.codingdojo.yatzy1.calculator.facade.CalculatorStraightFacade;
import org.codingdojo.yatzy1.calculator.strategy.*;
import org.codingdojo.yatzy1.calculator.template.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class allow to calculate scores of Yatzy dice game
 */
public class Yatzy1 {

    //The player scores the sum of all dice, no matter what they read
    public int calculateScoreOfChanceCategory(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(d1, d2, d3, d4, d5).reduce(0, Integer::sum);
    }

    //If all dice have the same number, the player scores 50 points
    public int calculateScoreOfYatzyCategory(int d1, int d2, int d3, int d4, int d5) {
        return Stream.of(d1, d2, d3, d4, d5).distinct().count() == 1 ? 50 : 0;
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

    //if the dice read 1,2,3,4,5, the player scores the sum of all the dice
    public int calculateScoreOfSmallStraight(int d1, int d2, int d3, int d4, int d5) {
        CalculatorStraightFacade calculatorStraightFacade = new CalculatorStraightFacade();
        return calculatorStraightFacade.getScoreStraightValue(YatzyCategory.SMALL_STRAIGHT, d1, d2, d3, d4, d5);
    }

    //if the dice read 2,3,4,5,6, the player scores the sum of all the dice
    public int calculateScoreOfLargeStraight(int d1, int d2, int d3, int d4, int d5) {
        CalculatorStraightFacade calculatorStraightFacade = new CalculatorStraightFacade();
        return calculatorStraightFacade.getScoreStraightValue(YatzyCategory.LARGE_STRAIGHT, d1, d2, d3, d4, d5);
    }

    //If the dices are two of a kind and three of a kind, the player scores the sum of all the dice.
    public int calculateScoreOfFullHouse(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> pairs = Stream.of(d1, d2, d3, d4, d5)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (pairs.size() > 2)
            return 0;
        boolean isTwoKind = pairs.entrySet().stream().allMatch(a -> a.getValue() == 2 || a.getValue() == 3);
        return isTwoKind ? pairs.entrySet().stream().mapToInt(d -> (int) (d.getValue() * d.getKey())).sum() : 0;
    }

    //The player scores the sum of the dice that reads one, two, three, four, five or six, respectively
    public static int calculateScoreOfSpecificValue(YatzyCategory category, List<Integer> dices) {
        CalculatorTemplate calculatorTemplate;
        switch (category) {
            case ONES -> calculatorTemplate = new CalculatorOfOnesTemplate();
            case TWOS -> calculatorTemplate = new CalculatorOfTwosTemplate();
            case THREES -> calculatorTemplate = new CalculatorOfThreesTemplate();
            case FOURS -> calculatorTemplate = new CalculatorOfFoursTemplate();
            case FIVES -> calculatorTemplate = new CalculatorOfFivesTemplate();
            case SIXES -> calculatorTemplate = new CalculatorOfSixesTemplate();
            default -> throw new IllegalArgumentException("Unexpected value: " + category);
        }
        return calculatorTemplate.calculateTotal(dices);
    }

    //If there are a number pairs of dice with the same number, the player scores the sum of these dice
    public static int scorePairValue(YatzyCategory category, int d1, int d2, int d3, int d4, int d5) {
        CalculatorPair calculatorPair;
        switch (category) {
            case PAIR -> calculatorPair = new CalculatorPair(new CalculatorOnePairStrategy());
            case TWO_PAIRS -> calculatorPair = new CalculatorPair(new CalculatorTwoPairStrategy());
            case THREE_OF_A_KIND -> calculatorPair = new CalculatorPair(new CalculatorThreePairStrategy());
            case FOUR_OF_A_KIND -> calculatorPair = new CalculatorPair(new CalculatorFourPairStrategy());
            default -> throw new IllegalArgumentException("Unexpected value: " + category);
        }
        return calculatorPair.calculate(d1, d2, d3, d4, d5);
    }
}