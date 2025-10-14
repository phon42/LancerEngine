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
}
