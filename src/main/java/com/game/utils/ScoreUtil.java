package com.game.utils;

import com.game.models.Die;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreUtil {
    /*
     This utility will be used to determine if the dice roll scores. Here you should create methods for comparing the dice against logic to determine if it scores.
     */

    /**
     * TODO top level method to run all the rules
     *   - design it so where you receive a list of dice, and then pass back a list of winning patters and their scores
     *   - maybe utilize a Hashmap to handle the data. The key can be the pattern and the value can be the score
     * @param diceList list of die
     * @return map of winning patterns
     */
    public static Map<List<Integer>, Integer> getListOfScores(List<Die> diceList) {
        // gather all the scoring patterns from the diceList by running against rules/methods set below
        return new HashMap<>();
    }

    private static boolean isStraight(List<Die> diceList) {
        //TODO
        return false;
    }

    private static boolean hasAllSameValue(List<Die> diceList) {
        //TODO
        return false;
    }

    // etc...
}
