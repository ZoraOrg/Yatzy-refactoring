package org.codingdojo;

import org.codingdojo.yatzy1.Yatzy1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {

    private static Yatzy1 yatzy1;

    @BeforeAll
    public static void init() {
        yatzy1 = new Yatzy1();
    }

    @Test
    @DisplayName("check if the player score the sum of all dices")
    public void testChanceCategory() {
        assertEquals(15, yatzy1.calculateScoreOfChanceCategory(2, 3, 4, 5, 1));
        assertEquals(16, yatzy1.calculateScoreOfChanceCategory(3, 3, 4, 5, 1));
    }

    @Test
    @DisplayName("check if the player scores 50 points when all dice have the same number")
    public void testYatzyCategory() {
        assertEquals(50, yatzy1.calculateScoreOfYatzyCategory(4, 4, 4, 4, 4));
        assertEquals(50, yatzy1.calculateScoreOfYatzyCategory(6, 6, 6, 6, 6));
        assertEquals(0, yatzy1.calculateScoreOfYatzyCategory(6, 6, 6, 6, 3));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads one equals score")
    public void testOnesCategory() {
        assertEquals(1, yatzy1.calculateScoreOfOnesCategory(1, 2, 3, 4, 5));
        assertEquals(2, yatzy1.calculateScoreOfOnesCategory(1, 2, 1, 4, 5));
        assertEquals(0, yatzy1.calculateScoreOfOnesCategory(6, 2, 2, 4, 5));
        assertEquals(4, yatzy1.calculateScoreOfOnesCategory(1, 2, 1, 1, 1));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads two equals score")
    public void testTwosCategory() {
        assertEquals(4, yatzy1.calculateScoreOfOTwosCategory(1, 2, 3, 2, 6));
        assertEquals(10, yatzy1.calculateScoreOfOTwosCategory(2, 2, 2, 2, 2));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads three equals score")
    public void testThreesCategory() {
        assertEquals(6, yatzy1.calculateScoreOfThreesCategory(1, 2, 3, 2, 3));
        assertEquals(12, yatzy1.calculateScoreOfThreesCategory(2, 3, 3, 3, 3));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads four equals score")
    public void testFoursCategory() {
        assertEquals(12, yatzy1.calculateScoreOfFoursCategory(4, 4, 4, 5, 5));
        assertEquals(8, yatzy1.calculateScoreOfFoursCategory(4, 4, 5, 5, 5));
        assertEquals(4, yatzy1.calculateScoreOfFoursCategory(4, 5, 5, 5, 5));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads five equals score")
    public void testFivesCategory() {
        assertEquals(10, yatzy1.calculateScoreOfFivesCategory(4, 4, 4, 5, 5));
        assertEquals(15, yatzy1.calculateScoreOfFivesCategory(4, 4, 5, 5, 5));
        assertEquals(20, yatzy1.calculateScoreOfFivesCategory(4, 5, 5, 5, 5));
    }

    @Test
    @DisplayName("check if the sum of the dice that reads six equals score")
    public void testSixesCategory() {
        assertEquals(0, yatzy1.calculateScoreOfSixesCategory(4, 4, 4, 5, 5));
        assertEquals(6, yatzy1.calculateScoreOfSixesCategory(4, 4, 6, 5, 5));
        assertEquals(18, yatzy1.calculateScoreOfSixesCategory(6, 5, 6, 6, 5));
    }

    @Test
    public void one_pair() {
        assertEquals(6, new Yatzy1().score_pair(3,4,3,5,6));
        assertEquals(10, new Yatzy1().score_pair(5,3,3,3,5));
        assertEquals(12, new Yatzy1().score_pair(5,3,6,6,5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy1.two_pair(3,3,5,4,5));
        assertEquals(16, Yatzy1.two_pair(3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() 
    {
        assertEquals(9, Yatzy1.three_of_a_kind(3,3,3,4,5));
        assertEquals(15, Yatzy1.three_of_a_kind(5,3,5,4,5));
        assertEquals(9, Yatzy1.three_of_a_kind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy1.four_of_a_kind(3,3,3,3,5));
        assertEquals(20, Yatzy1.four_of_a_kind(5,5,5,4,5));
        assertEquals(9, Yatzy1.three_of_a_kind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy1.smallStraight(1,2,3,4,5));
        assertEquals(15, Yatzy1.smallStraight(2,3,4,5,1));
        assertEquals(0, Yatzy1.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy1.largeStraight(6,2,3,4,5));
        assertEquals(20, Yatzy1.largeStraight(2,3,4,5,6));
        assertEquals(0, Yatzy1.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy1.fullHouse(6,2,2,2,6));
        assertEquals(0, Yatzy1.fullHouse(2,3,4,5,6));
    }
}
