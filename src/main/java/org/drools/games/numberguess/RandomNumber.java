package org.drools.games.numberguess;

import java.util.Random;

/**
 * This is a random number generator class for the game.
 */

public class RandomNumber {
    private int randomNumber;

    public RandomNumber(int v) {
        this.randomNumber = new Random().nextInt( v );
    }

    public int getValue() {
        return this.randomNumber;
    }

    @Override
    public String toString() {
        return "RandomNumber{" +
               "randomNumber=" + randomNumber +
               '}';
    }
} // End of Class RandomNumber
