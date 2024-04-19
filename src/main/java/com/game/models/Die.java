package com.game.models;

import java.util.List;

public class Die {
    // Fields should include numOfSides, and faceValue;

    // AllArgs constructor

    // Accessors and Mutators

    /**
     * Generate a random int between 1 and numOfSides and set it to faceValue
     */
    public void roll() {
        //TODO
    }

    /**
     * Calls {@link Die#roll()} for each Die in the list
     * @param diceList {@literal List<Die>}
     */
    public static void rollDice(List<Die> diceList) {
        //TODO
    }

    /**
     * Get a Human-Readable string of the DiceList provided
     * @param diceList {@literal List<Die>}
     * @return String concatenation of all the numbers printed in human-readable form
     */
    public static String getDiceString(List<Die> diceList) {
        //TODO
        return ""; // change this to return the correct thing
    }
}
