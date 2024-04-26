package farkle;

import java.util.List;
import java.util.Random;

public class Die {
    private int numOfSides;
    private int faceValue;

    public Die(int numOfSides, int faceValue) {
        this.numOfSides = numOfSides;
        this.faceValue = faceValue;
    }

    public int getNumOfSides() {
        return numOfSides;
    }

    public void setNumOfSides(int numOfSides) {
        this.numOfSides = numOfSides;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * Generate a random int between 1 and {@link Die#numOfSides}
     */
    public void roll() {
        this.setFaceValue(new Random().nextInt(this.getNumOfSides()) + 1);
    }

    /**
     * Calls {@link Die#roll()} for each Die in the list
     * @param diceList {@literal List<Die>}
     */
    public static void rollDice(List<Die> diceList) {
        diceList.forEach(Die::roll);
    }

    /**
     * Get a Human-Readable string of the DiceList provided
     * @param diceList {@literal List<Die>}
     * @return String concatenation of all the numbers printed in human-readable form
     */
    public static String getDiceString(List<Die> diceList) {
        StringBuilder sb = new StringBuilder("|");

        diceList.forEach(die -> {
            sb.append(die.faceValue);
            sb.append("|");
        });

        return sb.toString();
    }
}
