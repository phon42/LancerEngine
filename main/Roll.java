package main;
import java.util.Random;

import packages.characterTypes.pilot.skillTriggersList.SkillTrigger;

public final class Roll {
    /**
     * Contains the random number generator for this class.
     */
    private static final Random randomGenerator = new Random();

    // prevent user from instantiating this class
    private Roll() {}

    // TODO: see if any of this can be consolidated
    /**
     * Takes in a String of almost any form, which can be filled with spaces,
     *     punctuation, and random letters and still work. Each die/dice clause
     *     must be of the form XdYkhZ, where X is optional and will be assumed
     *     to be 1, Y is mandatory, "kh" is optional and can be "kh" or "kl",
     *     representing "keep highest" or "keep lowest" respectively, and Z is
     *     mandatory if "kh" or "kl" is present, and represents the number of
     *     rolls to keep.
     * @param rollString a String containing the expression to evaluate.
     * @param throwOnIllegal a boolean representing whether or not to throw an
     *     IllegalArgumentException if rollString contains anything other than
     *     the digits 0-9, "k", "h", "l", "(", ")", "+", "-", "*", "/", or
     *     spaces.
     * @return an int containing the result of the expression.
     */
    public static int roll(String rollString, boolean throwOnIllegal) {
        // Check for mismatched parentheses and a null roleString
        checkRolls(rollString);
        // remove trash like random letters and all spaces
        rollString = formatRolls(rollString, throwOnIllegal);

        // actually evaluate the expression
        return parseRolls(rollString);
    }
    /**
     * A helper method for roll(String, boolean). Allows users to call the
     *     method as roll(String) with a default value of true for the
     *     boolean.
     * @param rollString a String containing the expression to evaluate.
     * @return an int containing the result of the expression.
     */
    public static int roll(String rollString) {
        return roll(rollString, true);
    }
    /**
     * Checks rollString to ensure it makes sense syntactically and isn't null.
     * @param rollString a String which cannot be null.
     * @throws IllegalArgumentException if rollString is null, contains unclosed
     *     parentheses, or at any point in the expression contains more closed
     *     parentheses than open ones.
     */
    private static void checkRolls(String rollString) {
        int height = 0;
        String rollChar = "";

        if (rollString == null) {
            throw new IllegalArgumentException("rollString is null");
        }
        for (int i = 0; i < rollString.length(); i++) {
            rollChar = rollString.substring(i, i + 1);
            if (rollChar.equals("(")) {
                height++;
            }
            if (rollChar.equals(")")) {
                height--;
            }
            if (height < 0) {
                throw new IllegalArgumentException("Called Roll.checkRolls()"
                    + " with an illegal expression - Nesting level went below"
                    + " 0");
            }
        }
        if (height > 0) {
            throw new IllegalArgumentException("Called Roll.checkRolls()"
                + " with an illegal expression - contained unclosed"
                + " parentheses");
        }
    }
    /**
     * Formats a provided rollString, removing any invalid characters and all
     *     spaces. rollString is assumed to have been run through
     *     Roll.checkRolls().
     * @param rollString a String which can be any String.
     * @return a String containing the formatted String.
     */
    private static String formatRolls(String rollString,
        boolean throwOnIllegal) {
        // TODO: have it replace any combination of "d", "k", "h", and "l"
        //     that isn't "d", "kh", and "kl" exactly (i.e. "dd")
        String[] allowedChars = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "d", "k", "h", "l", "(", ")", "+", "-", "*", "/"};
        String newRollString = "";
        String rollChar = "";
        boolean isAllowed = false;

        rollString = rollString.toLowerCase();
        for (int i = 0; i < rollString.length(); i++) {
            isAllowed = false;
            for (int j = 0; j < allowedChars.length; j++) {
                rollChar = rollString.substring(i, i + 1);
                if (rollChar.equals(allowedChars[j])) {
                    newRollString += rollString.substring(i, i + 1);
                    isAllowed = true;
                }
            }
            if (! isAllowed && throwOnIllegal) {
                throw new IllegalArgumentException("rollString value: \""
                    + rollString + "\" contained an invalid character: '"
                    + rollChar + "'");
            }
        }
        rollString = newRollString;

        return rollString;
    }
    /**
     * Evaluates a provided expression containing math and dice rolls.
     *     rollString is assumed to have been run through Roll.checkRolls() and
     *     Roll.formatRolls().
     * @param rollString a String which can be any String.
     * @return an int containing the result of the expression.
     */
    private static int parseRolls(String rollString) {
        boolean parentheses = (rollString.indexOf("(") != -1)
            || (rollString.indexOf(")") != -1);
        boolean multiplicative = (rollString.indexOf("*") != -1)
            || (rollString.indexOf("/") != -1);
        boolean additive = (rollString.indexOf("+") != -1)
            || (rollString.indexOf("-") != -1);
        
        // TODO: fill out
        if (parentheses) {
            rollString = removeParen(rollString);
            // ((2)) ((2))
            // ((2)   (2))

        } else if (multiplicative) {

        } else if (additive) {

        }

        // At this point, the String is assumed to be of the form "XdYkhZ",
        //     where X is optional and assumed to be 1 if not present, "kh" can
        //     be "kh" or "kl" and is optional, and Z is present if and only if
        //     the "kh" parameter is.
        return makeRoll(rollString);
    }
    public static String removeParen(String rollString) {
        // convert "(((9)))" to "9" and "((2) ((2)))" to "(2) ((2))"
        int minLevel = -1;
        int localMinimum = 0;
        int height = 0;
        String rollChar = "";

        if (rollString.indexOf("(") == -1
            && rollString.indexOf(")") == -1) {
            return rollString;
        }
        for (int i = 0; i < rollString.length(); i++) {
            rollChar = rollString.substring(i, i + 1);
            if (rollChar.equals("(")) {
                height++;
            } else if (rollChar.equals(")")) {
                height--;
            } else {
                localMinimum = height;
                if (minLevel == -1) {
                    minLevel = localMinimum;
                } else {
                    minLevel = Math.min(minLevel, localMinimum);
                }
            }
        }
        if (minLevel == 0) {
            return rollString;
        }
        rollString = rollString.substring(minLevel);
        rollString = rollString.substring(rollString.length() - minLevel,
            rollString.length());

        return rollString;
    }
    // TODO: change from int to int[] with the result in index 0 and all the
    //     rolls made trailing that to allow users to see what was rolled
    /**
     * Takes in a String which is assumed to be of the form "XdYkhZ" or "XdYklZ"
     *     or "dYkhZ" or "dYklZ" or "dYkh" or "dYkl" or "XdYkh" or "XdYkl"
     *     or "XdY" or "dY" or "X" and makes the dice roll(s), if any, dictated
     *     by that String.
     * @param rollString a String which can be any String of the forms described
     *     above.
     * @return an int containing the result of the expression.
     */
    private static int makeRoll(String rollString) {
        boolean containsKeepHighest = false;
        boolean containsKeepLowest = false;
        boolean containsKeep = false;
        boolean containsX = false;
        boolean containsZ = false;
        boolean containsD = false;
        String[] substring;
        String[] substring2 = new String[0];
        int rollNum;
        int maxRoll;
        int keep;
        int keepNum;

        // determining whether the expression contains any of several key
        //     components
        // splitting everything into two types: 'contains "d"' (pretty much
        //     everything) and 'doesn't contain "d"' ("X")
        containsD = (rollString.indexOf("d") != -1);
        if (containsD) {
            // splitting 'contains "d"' into two types: 'contains "kh" or "kl"'
            //     and 'doesn't contain "kh" or "kl"'
            containsKeepHighest = (rollString.indexOf("kh") != -1);
            containsKeepLowest = (rollString.indexOf("kl") != -1);
            containsKeep = containsKeepHighest || containsKeepLowest;
            // could be of the form "XdY" or "dY" (for either one, what's after
            //     Y doesn't matter), and we need to know which
            substring = rollString.split("d");
            if (! substring[0].equals("")) {
                containsX = true;
            }
        }
        if (containsKeepHighest) {
            if (rollString.split("kh").length > 1) {
                containsZ = true;
            }
        } else if (containsKeepLowest) {
            if (rollString.split("kl").length > 1) {
                containsZ = true;
            }
        }
        // now to actually evaluate the expression
        if (containsD) {
            // String is of the form "XdYkhZ" or "XdYklZ" or "dYkhZ" or "dYklZ"
            //     or "dYkh" or "dYkl" or "XdYkh" or "XdYkl" or "XdY" or "dY"
            substring = rollString.split("d");
            if (containsKeep) {
                // String is of the form "XdYkhZ" or "XdYklZ" or "dYkhZ" or
                //     "dYklZ" or "dYkh" or "dYkl" or "XdYkh" or "XdYkl"
                if (containsX) {
                    // String is of the form "XdYkhZ" or "XdYklZ" or "XdYkh" or
                    //     "XdYkl"
                    rollNum = Integer.parseInt(substring[0]);
                } else {
                    // String is of the form "dYkhZ" or "dYklZ" or "dYkh" or
                    //     "dYkl"
                    rollNum = 1;
                }
                if (containsKeepHighest) {
                    substring2 = substring[1].split("kh");
                    keep = +1;
                } else if (containsKeepLowest) {
                    substring2 = substring[1].split("kl");
                    keep = -1;
                } else {
                    throw new IllegalArgumentException();
                }
                maxRoll = Integer.parseInt(substring2[0]);
                if (containsZ) {
                    keepNum = Integer.parseInt(substring2[1]);
                } else {
                    keepNum = 1;
                }

                return roll(rollNum, maxRoll, keep, keepNum);
            } else if (containsX) {
                // String is of the form "XdY"
                rollNum = Integer.parseInt(substring[0]);
                maxRoll = Integer.parseInt(substring[1]);

                return roll(rollNum, maxRoll);
            } else {
                // String is of the form "dY"
                maxRoll = Integer.parseInt(substring[substring.length - 1]);

                return roll(maxRoll);
            }
        } else {
            // String is of the form "X"
            return Integer.parseInt(rollString);
        }
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
        if (keep == 0) {
            keptRolls = rolls;
            rolls = new int[rollNum + 1];
        } else {
            keptRolls = keepRolls(rolls, keep, keepNum);
            rolls = new int[keepNum + 1];
        }
        for (int i = 0; i < rolls.length - 1; i++) {
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
     * Helper method for rollComplex(int, int, int, int). Allows the method to
     *     be called with a default value of 0 for the last two ints. Rolls
     *     (rollNum)d(maxRoll)s.
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
     * Rolls a d(maxRoll). In other words, generates a single random int between
     *     1 and maxRoll (inclusive). Prints a warning if anything other than a
     *     d2, d3, d6, or d20 is requested.
     * @param maxRoll an int containing the maximum possible value for the roll.
     *     Must be a minimum of 2.
     * @return an int containing the random result.
     * @throws IllegalArgumentException if maxRoll is < 2.
     */
    private static int roll(int maxRoll) {
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
     * At the core of many of the methods in this class. Generates a single
     *     random double between 0 (inclusive) and 1 (exclusive).
     * @return a double containing the randomly generated value.
     */
    private static double generateRandom() {
        return randomGenerator.nextDouble();
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
                // find the maximum/minimum value in rolls (OPPOSITE of what
                //     it's supposed to be)
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
    /**
     * Rolls (rollNum)d(maxRoll)s.
     * @param rollNum an int containing the number of dice to roll. Must be a
     *     minimum of 1.
     * @param maxRoll an int containing the maximum roll on each die. Must be a
     *     minimum of 2.
     * @return an int containing the total result.
     */
    private static int roll(int rollNum, int maxRoll) {
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
    private static int roll(int rollNum, int maxRoll, int keep, int keepNum) {
        return rollComplex(rollNum, maxRoll, keep, keepNum)[0];
    }
    /**
     * Rolls the proper number of accuracy and difficulty dice based on the
     *     provided accuracy and difficulty values.
     * @param accuracy an int containing the number of accuracy stacks. Must be
     *     a minimum of 0.
     * @param difficulty an int containing the number of difficulty stacks. Must
     *     be a minimum of 0.
     * @return an int containing the result of the roll.
     */
    public static int rollAccDiff(int accuracy, int difficulty) {
        int totalModifier = accuracy - difficulty;
        // we don't have to worry about totalModifier being 0 because it's taken
        //     care of later
        int resultSign = totalModifier > 0 ? +1 : -1;
        int result;

        if (accuracy == difficulty) {
            return 0;
        }
        totalModifier = Math.abs(totalModifier);
        result = roll(totalModifier, 6);

        return result * resultSign;
    }
    /**
     * Rolls a skill trigger skill check with the provided parameters.
     * isDifficult, upon being true, which adds +1 difficulty to the total roll.
     * backgroundInvoked adds +1 accuracy or +1 difficulty if its value is +1 or
     *     -1, respectively.
     * teamwork, upon being true, adds +1 accuracy to the total roll.
     * @param skillTrigger a SkillTrigger which provides the bonus for the roll.
     *     Cannot be null.
     * @param accuracy an int containing the number of accuracy dice applied.
     *     Must be a minimum of 0.
     * @param difficulty an int containing the number of difficulty dice
     *     applied. Must be a minimum of 0.
     * @param isDifficult a boolean representing whether the GM has marked the
     *     roll as Difficult.
     * @param backgroundInvoked an int containing whether the pilot's
     *     background was invoked, and if so, to what effect. Must be between -1
     *     and 1 (inclusive).
     * @param teamwork a boolean representing whether teamwork was involved in
     *     the check.
     * @return an int containing the result of the check.
     * @throws IllegalArgumentException if any parameters are invalid.
     */
    public static int check(SkillTrigger skillTrigger, int accuracy,
        int difficulty, boolean isDifficult, int backgroundInvoked,
        boolean teamwork) {
        // See pgs. 13, 45 - 47
        int bonus;

        if (skillTrigger == null) {
            throw new IllegalArgumentException("skillTrigger is null");
        }
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy value: " + accuracy
                + " is < 0");
        }
        if (difficulty < 0) {
            throw new IllegalArgumentException("difficulty value: " + difficulty
                + " is < 0");
        }
        if (Math.abs(backgroundInvoked) > 1) {
            throw new IllegalArgumentException("absolute value of"
                + " backgroundInvoked value: " + backgroundInvoked + " is > 1");
        }
        bonus = skillTrigger.getLevel();
        difficulty += isDifficult ? 1 : 0;
        accuracy += backgroundInvoked == +1 ? 1 : 0;
        difficulty += backgroundInvoked == -1 ? 1 : 0;
        accuracy += teamwork ? 1 : 0;

        return roll(bonus, accuracy, difficulty);
    }
    /**
     * Helper method for check(SkillTrigger, int, int, boolean, int, boolean).
     *     Allows the method to be called with default values of false, 0, false
     *     for the fourth parameter onward.
     * @return an int containing the result of the check.
     */
    public static int check(SkillTrigger skillTrigger, int accuracy,
        int difficulty) {
            return check(skillTrigger, accuracy, difficulty, false,
                0, false);
    }
    /**
     * Determines whether the provided result of a skill trigger skill check was
     *     a success, failure, or partial success. Returns whether the check
     *     succeeded (1 - or more, in the case of Risky rolls) or failed (0).
     *     For Risky rolls, returns 1 if the result was at least 10 but less
     *     than 20, and 2 if the result was at least 20.
     * riskiness' 2s digit represents whether the roll is Heroic,
     *     which increases the number required for a success to 20. Its 1s digit
     *     represents whether the roll is Risky, which adds consequences on a
     *     roll of less than 20.
     * @param result an int containing the result of the check to be evaluated.
     *     Can be any int.
     * @param riskiness an int containing whether the GM has marked the roll as
     *     Heroic, Risky, or neither. Must be between 0 and 2 (inclusive).
     * @return an int containing the result of the check.
     */
    public static int evaluateCheck(int result, int riskiness) {
        // See pgs. 13, 45 - 47
        if (riskiness < 0) {
            throw new IllegalArgumentException("riskiness value: " + riskiness
                + " is < 0");
        }
        if (riskiness > 2) {
            throw new IllegalArgumentException("riskiness value: " + riskiness
                + " is > 2");
        }
        if (riskiness == 2) {
            // the roll is Heroic
            return (int) (result >= 20 ? 1 : 0);
        } else if (riskiness == 1) {
            // the roll is Risky
            if (result < 20) {
                return (int) (result >= 10 ? 1 : 0);
            }
            return 2;
        } else {
            // the roll is neither Heroic nor Risky
            return (int) (result >= 10 ? 1 : 0);
        }
    }
    /**
     * Rolls a mech skill skill check with the provided parameters.
     * @param skillIndex an int containing the index of the mech skill to use as
     *     a bonus for this check. Must be between 0 and 3 (inclusive).
     * @param mechSkills an int[] containing the mech skills of the Pilot
     *     associated with this check. Must be an int[] of length 4. Each
     *     element must be between 0 and 6 (inclusive).
     * @param accuracy an int containing the number of accuracy dice applied.
     *     Must be a minimum of 0.
     * @param difficulty an int containing the number of difficulty dice
     *     applied. Must be a minimum of 0.
     * @return an int containing the result of the check.
     * @throws IllegalArgumentException if any parameters are invalid.
     */
    public static int check(int skillIndex, int[] mechSkills, int accuracy,
        int difficulty) {
        // See pg. 13
        int bonus;

        if (skillIndex < 0) {
            throw new IllegalArgumentException("skillIndex value: " + skillIndex
                + " is < 0");
        }
        if (skillIndex > 3) {
            throw new IllegalArgumentException("skillIndex value: " + skillIndex
                + " is > 3");
        }
        if (mechSkills == null) {
            throw new IllegalArgumentException("mechSkills is null");
        }
        if (mechSkills.length != 4) {
            throw new IllegalArgumentException("mechSkills is of length: "
                + mechSkills.length + " which is not 4");
        }
        for (int mechSkill : mechSkills) {
            if (mechSkill < 0) {
                throw new IllegalArgumentException("mechSkills array contains a"
                    + " value of: " + mechSkill + " which is < 0");
            }
            if (mechSkill > 6) {
                throw new IllegalArgumentException("mechSkills array contains a"
                    + " value of: " + mechSkill + " which is > 6");
            }
        }
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy value: " + accuracy
                + " is < 0");
        }
        if (difficulty < 0) {
            throw new IllegalArgumentException("difficulty value: " + difficulty
                + " is < 0");
        }
        bonus = mechSkills[skillIndex];

        return roll(bonus, accuracy, difficulty);
    }
    /**
     * Helper method for check(int, int[], int, int). Allows the method to be
     *     called with default values of 0, 0 from the third parameter onward.
     * @return an int containing the result of the check.
     */
    public static int check(int skillIndex, int[] mechSkills) {
        return check(skillIndex, mechSkills, 0, 0);
    }
    /**
     * Determines whether the provided result of a mech skills skill check was
     *     a success or failure.
     * @param result an int which can be any int.
     * @return a boolean containing the result of the check.
     */
    public static boolean evaluateCheck(int result) {
        return (result >= 10);
    }
    /**
     * Rolls a contested skill trigger skill check. Returns the identity of the
     *     winner as an int: +1 = Attacker (Participant #1) won, -1 = Defender
     *     (Participant #2) won.
     * @return an int containing the identity of the winner.
     */
    public static int contestedCheck(SkillTrigger skillTrigger1,
        SkillTrigger skillTrigger2, int accuracy1, int difficulty1,
        int accuracy2, int difficulty2) {
        // See pg. 13
        int result1 = check(skillTrigger1, accuracy1, difficulty1);
        int result2 = check(skillTrigger2, accuracy2, difficulty2);

        // If it's a tie (result1 == result2), the result is +1
        return result1 >= result2 ? +1 : -1;
    }
    public static int contestedCheck(SkillTrigger skillTrigger1,
        SkillTrigger skillTrigger2) {
        return contestedCheck(skillTrigger1, skillTrigger2, 0,
            0, 0, 0);
    }
    /**
     * Rolls a contested mech skills skill check. Returns the identity of the
     *     winner as an int: +1 = Attacker (Participant #1) won, -1 = Defender
     *     (Participant #2) won.
     * @return an int containing the identity of the winner.
     */
    public static int contestedCheck(int skillIndex1, int[] mechSkills1,
        int skillIndex2, int[] mechSkills2, int accuracy1, int difficulty1,
        int accuracy2, int difficulty2) {
        // See pg. 13
        int result1 = check(skillIndex1, mechSkills1, accuracy1, difficulty1);
        int result2 = check(skillIndex2, mechSkills2, accuracy2, difficulty2);

        // If it's a tie (result1 == result2), the result is +1
        return result1 >= result2 ? +1 : -1;
    }
    public static int contestedCheck(int skillIndex1, int[] mechSkills1,
        int skillIndex2, int[] mechSkills2) {
        return contestedCheck(skillIndex1, mechSkills1, skillIndex2,
            mechSkills2, 0, 0, 0,
            0);
    }
    /**
     * Rolls an attack roll.
     * @param bonus an int containing the bonus to add to the roll. Can be any
     *     int.
     * @param accuracy an int containing the number of accuracy dice applied.
     *     Must be a minimum of 0.
     * @param difficulty an int containing the number of difficulty dice
     *     applied. Must be a minimum of 0.
     * @return an int containing the result of the roll.
     * @throws IllegalArgumentException if any parameters are invalid.
     */
    public static int attack(int bonus, int accuracy, int difficulty) {
        // See pg. 13
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy value: " + accuracy
                + " is < 0");
        }
        if (difficulty < 0) {
            throw new IllegalArgumentException("difficulty value: " + difficulty
                + " is < 0");
        }

        return roll(bonus, accuracy, difficulty);
    }
    /**
     * Rolls a save.
     * @param skillIndex an int containing the index of the mech skill to use as
     *     a bonus for this check. Must be between 0 and 3 (inclusive).
     * @param mechSkills an int[] containing the mech skills of the Pilot
     *     associated with this check. Must be an int[] of length 4. Each
     *     element must be between 0 and 6 (inclusive).
     * @param accuracy an int containing the number of accuracy dice applied.
     *     Must be a minimum of 0.
     * @param difficulty an int containing the number of difficulty dice
     *     applied. Must be a minimum of 0.
     * @return an int containing the result of the roll.
     * @throws IllegalArgumentException if any parameters are invalid.
     */
    public static int save(int skillIndex, int[] mechSkills, int accuracy,
        int difficulty) {
        // See pg. 13
        // TODO: reminder that you can always choose to fail this kind of roll
        int bonus;

        if (skillIndex < 0) {
            throw new IllegalArgumentException("skillIndex value: " + skillIndex
                + " is < 0");
        }
        if (skillIndex > 3) {
            throw new IllegalArgumentException("skillIndex value: " + skillIndex
                + " is > 3");
        }
        if (mechSkills == null) {
            throw new IllegalArgumentException("mechSkills is null");
        }
        if (mechSkills.length != 4) {
            throw new IllegalArgumentException("mechSkills is of length: "
                + mechSkills.length + " which is not 4");
        }
        for (int mechSkill : mechSkills) {
            if (mechSkill < 0) {
                throw new IllegalArgumentException("mechSkills array contains a"
                    + " value of: " + mechSkill + " which is < 0");
            }
            if (mechSkill > 6) {
                throw new IllegalArgumentException("mechSkills array contains a"
                    + " value of: " + mechSkill + " which is > 6");
            }
        }
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy value: " + accuracy
                + " is < 0");
        }
        if (difficulty < 0) {
            throw new IllegalArgumentException("difficulty value: " + difficulty
                + " is < 0");
        }
        bonus = mechSkills[skillIndex];

        return roll(bonus, accuracy, difficulty);
    }
    /**
     * Rolls a check, attack roll, or save.
     * @param bonus an int containing the bonus value to apply to the roll. Can
     *     be any int.
     * @param accuracy an int containing the number of accuracy dice to apply to
     *     the roll. Must be a minimum of 0. Assumed to be valid.
     * @param difficulty an int containing the number of difficulty dice to
     *     apply to the roll. Must be a minimum of 0. Assumed to be valid.
     * @return an int containing the result of the check.
     */
    private static int roll(int bonus, int accuracy, int difficulty) {
        // See pg. 13
        int result = roll("d20");

        result += bonus;
        result += rollAccDiff(accuracy, difficulty);

        return result;
    }
}
