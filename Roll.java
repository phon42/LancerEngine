public final class Roll {
    // prevent user from instantiating this class
    private Roll() {}

    /**
     * At the core of many of the methods in this class. Generates a single
     *     random double between 0 (inclusive) and 1 (exclusive).
     * @return a double containing the randomly generated value.
     */
    private static double generateRandom() {
        // TODO: replace
        return 7 / 10.0; // randomly chosen by fair dice roll
    }
    /**
     * Rolls a d(maxRoll). In other words, generates a single random int between
     *     1 and maxRoll (inclusive). Prints a warning if anything other than a
     *     d2, d3, d6, or d20 is requested.
     * @param maxRoll an int containing the maximum possible value for the roll,
     *     which must be a minimum of 2.
     * @return an int containing the random result.
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
            throw new IllegalArgumentException("maxRoll is < 2");
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
        // TODO: fill out so that it records all the operations
        // String keepString;
        int[] rolls;
        int[] keptRolls;
        int total = 0;

        if (rollNum < 0) {
            throw new IllegalArgumentException("rollNum is < 0");
        }
        if (rollNum == 0) {
            throw new IllegalArgumentException("rollNum is 0");
        }
        if (maxRoll < 2) {
            throw new IllegalArgumentException("maxRoll is < 2");
        }
        if (Math.abs(keep) > 1) {
            throw new IllegalArgumentException("keep is < -1 or > 1");
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
                throw new IllegalArgumentException("keepNum is < 1");
            }
            if (keepNum > rollNum) {
                throw new IllegalArgumentException("keepNum value: " + keepNum
                    + " is > rollNum value: " + rollNum);
                /*
                keepString = keep > 0 ? "kh" : "kl";
                throw new IllegalArgumentException("Roll.rollComplex()"
                    + " parameter included a \"XdY" + keepString + "Z\" clause"
                    + " where X was " + rollNum + " and Z was " + keepNum);
                */
            }
        }
        rolls = new int[rollNum];
        keptRolls = new int[keepNum];
        for (int i = 0; i < rollNum; i++) {
            rolls[i] = roll(maxRoll);
        }
        // TODO: implement keeping a certain number of rolls if keep != 0
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
}
