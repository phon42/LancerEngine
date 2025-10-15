import java.util.Random;

public final class Roll {
    /**
     * Contains the random number generator for this class.
     */
    private static final Random randomGenerator = new Random();

    // prevent user from instantiating this class
    private Roll() {}

    /**
     * At the core of many of the methods in this class. Generates a single
     *     random double between 0 (inclusive) and 1 (exclusive).
     * @return a double containing the randomly generated value.
     */
    private static double generateRandom() {
        return randomGenerator.nextDouble();
    }
    /**
     * Rolls a d(maxRoll). In other words, generates a single random int between
     *     1 and maxRoll (inclusive). Prints a warning if anything other than a
     *     d2, d3, d6, or d20 is requested.
     * @param maxRoll an int containing the maximum possible value for the roll.
     *     Must be a minimum of 2.
     * @return an int containing the random result.
     * @throws IllegalArgumentException if maxRoll is < 2.
     */
    public static int roll(int maxRoll) {
        double randomNum;
        int result;

        if (maxRoll != 2 && maxRoll != 3 && maxRoll != 6 && maxRoll != 20) {
            System.out.println("[ WARNING ]: Nonstandard maximum roll value: d"
                + maxRoll + " provided. Please ensure the correct value is"
                + " being provided.");
        }
        if (maxRoll < 2) {
            throw new IllegalArgumentException("maxRoll value: " + maxRoll
                + " is < 2");
        }
        randomNum = generateRandom() * maxRoll;
        result = (int) Math.floor(randomNum);
        result++;

        return result;
    }
    /**
     * Rolls (rollNum)d(maxRoll)s.
     * @param rollNum an int containing the number of dice to roll. Must be a
     *     minimum of 1.
     * @param maxRoll an int containing the maximum roll on each die. Must be a
     *     minimum of 2.
     * @return an int containing the total result.
     */
    public static int roll(int rollNum, int maxRoll) {
        return rollComplex(rollNum, maxRoll)[0];
    }
    /**
     * Rolls (rollNum)d(maxRoll)s, keeping the highest (or lowest, based on
     *     keep) (keepNum) rolls.
     * @param rollNum an int containing the number of dice to roll. Must be a
     *     minimum of 1.
     * @param maxRoll an int containing the maximum roll on each die. Must be a
     *     minimum of 2.
     * @param keep an int representing whether to keep only a specified number
     *     of the total dice rolled; must be -1, 0, or 1. -1 means
     *     "keep lowest", 0 means "keep all", and +1 means "keep highest".
     * @param keepNum an int containing the number of dice to keep out of the
     *     number of dice rolled. If keep is set to anything but 0, must be a
     *     minimum of 1.
     * @return an int containing the total result.
     */
    public static int roll(int rollNum, int maxRoll, int keep, int keepNum) {
        return rollComplex(rollNum, maxRoll, keep, keepNum)[0];
    }
    /**
     * Rolls (rollNum)d(maxRoll)s, keeping the highest (or lowest, based on
     *     keep) (keepNum) rolls.
     * @param rollNum an int containing the number of dice to roll. Must be a
     *     minimum of 1.
     * @param maxRoll an int containing the maximum roll on each die. Must be a
     *     minimum of 2.
     * @param keep an int representing whether to keep only a specified number
     *     of the total dice rolled; must be -1, 0, or 1. -1 means
     *     "keep lowest", 0 means "keep all", and +1 means "keep highest".
     * @param keepNum an int containing the number of dice to keep out of the
     *     number of dice rolled. If keep is set to anything but 0, must be a
     *     minimum of 1.
     * @return an int[] containing the total result in index 0, and all the
     *     rolls made to achieve the result in the following indices.
     */
    private static int[] rollComplex(int rollNum, int maxRoll, int keep,
        int keepNum) {
        // String keepString;
        int[] rolls;
        int[] keptRolls;
        int total = 0;

        if (rollNum < 0) {
            throw new IllegalArgumentException("rollNum value: " + rollNum
                + " is < 0");
        }
        if (rollNum == 0) {
            throw new IllegalArgumentException("rollNum is 0");
        }
        if (maxRoll < 2) {
            throw new IllegalArgumentException("maxRoll value: " + maxRoll
                + " is < 2");
        }
        if (Math.abs(keep) > 1) {
            throw new IllegalArgumentException("keep value: " + keep + " is <"
                + " -1 or > 1");
        }
        if (keepNum == rollNum) {
            keep = 0;
            // changing keepNum is unnecessary because it's done like 2 lines
            //     later
        }
        if (keep == 0) {
            keepNum = 0;
        } else {
            if (keepNum < 1) {
                throw new IllegalArgumentException("keepNum value: " + keepNum
                    + " is < 1");
            }
            if (keepNum > rollNum) {
                throw new IllegalArgumentException("keepNum value: " + keepNum
                    + " is > rollNum value: " + rollNum);
            }
        }
        rolls = new int[rollNum];
        keptRolls = new int[keepNum];
        for (int i = 0; i < rollNum; i++) {
            rolls[i] = roll(maxRoll);
        }
        if (keep != 0) {
            keptRolls = keepRolls(rolls, keep, keepNum);
        } else {
            keptRolls = rolls;
        }
        rolls = new int[keepNum + 1];
        for (int i = 0; i < keptRolls.length; i++) {
            total += keptRolls[i];
            if (i == 0) {
                continue;
            }
            rolls[i] = keptRolls[i - 1];
        }
        rolls[0] = total;

        return rolls;
    }
    /**
     * Rolls (rollNum)d(maxRoll)s.
     * @param rollNum an int containing the number of dice to roll. Must be a
     *     minimum of 1.
     * @param maxRoll an int containing the maximum roll on each die. Must be a
     *     minimum of 2.
     * @return an int[] containing the total result in index 0, and all the
     *     rolls made to achieve the result in the following indices.
     */
    private static int[] rollComplex(int rollNum, int maxRoll) {
        return rollComplex(rollNum, maxRoll, 0, 0);
    }
    /**
     * Take in some int[] and return an int[] of the (numToKeep) highest/lowest
     *     ints, depending on (keep).
     * @param rolls an int[] of rolls to be processed.
     * @param keep an int which can be -1 or +1, designating "keep lowest" and
     *     "keep highest", respectively.
     * @param numToKeep an int containing the number of rolls to keep from the
     *     array.
     * @return an int[] containing the (numToKeep) highest/lowest ints,
     *     depending on (keep).
     */
    private static int[] keepRolls(int[] rolls, int keep, int numToKeep) {
        int[] keptRolls = new int[numToKeep];
        int[] keptRoll = new int[2];
        int[] tempRolls;
        int numRolls;

        if (keep == 0) {
            throw new IllegalArgumentException("keep was 0");
        }
        if (numToKeep >= rolls.length) {
            throw new IllegalArgumentException("numToKeep value: " + numToKeep
                + " is > the rolls array's length: " + rolls.length);
        }
        if (rolls.length > numToKeep / 2.0) {
            // something like "8dYkh2", best strategy is to go through (2 times)
            //     and grab the ("kh" -> maximum) each time
            // For (numToKeep) times:
            //     Go through a modified version of rolls (for (rolls.length))
            //         Grab the maximum/minimum value
            for (int i = 0; i < numToKeep; i++) {
                // find the maximum/minimum value in rolls
                for (int j = 0; j < rolls.length; j++) {
                    if (j == 0 || (j != 0 && keep * rolls[j] > keptRoll[1])) {
                        keptRoll[0] = j;
                        keptRoll[1] = rolls[j];
                    }
                }
                keptRolls[i] = keptRoll[1];
                // remove the element at that index (keptRoll[0]) from rolls
                tempRolls = new int[rolls.length - 1];
                for (int j = 0; j < tempRolls.length; j++) {
                    if (j < keptRoll[0]) {
                        tempRolls[j] = rolls[j];
                    } else if (j >= keptRoll[0]) {
                        tempRolls[j] = rolls[j + 1];
                    }
                }
                rolls = tempRolls;
            }
        } else {
            // something like "8dYkh7", best strategy is to go through and grab
            //     the ("kh" -> MINIMUM - grab the opposite of whatever it is)
            //     each time
            numRolls = rolls.length;
            for (int i = 0; i < numRolls - numToKeep; i++) {
                // find the maximum/minimum value in rolls (OPPOSITE of what it's supposed to be)
                for (int j = 0; j < rolls.length; j++) {
                    if (j == 0
                        || (j != 0 && -1 * keep * rolls[j] > keptRoll[1])) {
                        keptRoll[0] = j;
                        keptRoll[1] = rolls[j];
                    }
                }
                // remove the element at that index (keptRoll[0]) from rolls
                tempRolls = new int[rolls.length - 1];
                for (int j = 0; j < tempRolls.length; j++) {
                    if (j < keptRoll[0]) {
                        tempRolls[j] = rolls[j];
                    } else if (j >= keptRoll[0]) {
                        tempRolls[j] = rolls[j + 1];
                    }
                }
                rolls = tempRolls;
            }
            keptRolls = rolls;
        }


        return keptRolls;
    }
}
