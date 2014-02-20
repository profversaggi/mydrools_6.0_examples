package org.drools.games.numberguess;

/**
 * This is the game rules class for the game. It takes the max range and allowed guesses as inputs.
 */

public class GameRules {
    private int maxRange;
    private int allowedGuesses;

    public GameRules(int maxRange,
                     int allowedGuesses) {
        this.maxRange = maxRange;
        this.allowedGuesses = allowedGuesses;
    }

    public int getAllowedGuesses() {
        return allowedGuesses;
    }

    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public String toString() {
        return "GameRules{" +
               "maxRange=" + maxRange +
               ", allowedGuesses=" + allowedGuesses +
               '}';
    }
}// ENd Class GameRules
