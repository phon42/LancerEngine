package main;

import java.util.Random;

import main.roll.Expression;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.skillTrigger.Skill;

/**
 * See pgs. 12 - 14 and 45 - 47.
 */
public final class Roll {
    /**
     * Contains the random number generator for this class.
     */
    private static final Random randomGenerator = new Random();

    // prevent user from instantiating this class
    private Roll() {}

    // TODO: see if any of this can be consolidated
    // TODO: add documentation to this to specify in more detail what rollString
    //     can be
    /**
     * Takes in a String of almost any form, which can be filled with spaces,
     *     punctuation, and random letters and still work. Each die/dice clause
     *     must be of the form XdYkhZ, where X is optional and will be assumed
     *     to be 1, Y is mandatory, "kh" is optional and can be "kh" or "kl",
     *     representing "keep highest" or "keep lowest" respectively, and Z is
     *     mandatory if "kh" or "kl" is present, and represents the number of
     *     rolls to keep.
     * @param rollString a String containing the expression to evaluate. Cannot
     *     be "". Cannot be null.
     * @param throwOnIllegal a boolean representing whether or not to throw an
     *     IllegalArgumentException if rollString contains anything other than
     *     the digits 0-9, "k", "h", "l", "(", ")", "+", "-", "*", "/", or
     *     spaces.
     * @return an int containing the result of the expression.
     */
    public static int roll(String rollString, boolean throwOnIllegal) {
        // Check for mismatched parentheses and a null or "" roleString
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
     * Checks rollString to ensure it makes sense syntactically and isn't "" or
     *     null.
     * @param rollString a String which cannot be "" or null.
     * @throws IllegalArgumentException if rollString is "", null, contains
     *     unclosed parentheses, or at any point in the expression contains more
     *     closed parentheses than open ones.
     */
    public static void checkRolls(String rollString) {
        int height = 0;
        String rollChar = "";

        HelperMethods.checkString("rollString", rollString);
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
     * rollString is a roll string, meaning that it can be of a form such as
     *     "1d6 + 3"; in other words, multiple single expressions joined by any
     *     number of operators, with parentheses and junk characters sprinkled
     *     throughout.
     * @param rollString a String which can be any String. Is assumed to not be
     *     "" or null.
     * @return a String containing the formatted String.
     */
    private static String formatRolls(String rollString, boolean throwOnIllegal)
    {
        final String[] allowedChars = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "d", "k", "h", "l", "(", ")", "+", "-", "*", "/"};
        boolean isAllowed = false;
        String rollChar = "";
        String newRollString = "";

        rollString = rollString.toLowerCase();
        // Clear every character that isn't present within allowedChars (spaces,
        //     for example)
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

        return newRollString;
    }
    /**
     * Evaluates a provided expression containing math and dice rolls.
     *     rollString is assumed to have been run through Roll.checkRolls() and
     *     Roll.formatRolls().
     * @param rollString a String which can be any String. Is assumed to not be
     *     "" or null.
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

        // At this point, the String is assumed to be of one of the forms
        //     allowed by makeRoll().
        return new Expression(rollString).roll();
    }
    public static String removeParen(String rollString) {
        // convert "(((9)))" to "9" and "((2) ((2)))" to "(2) ((2))"
        // Additionally, remove empty parentheses (i.e. "()")
        int minLevel = -1;
        int localMinimum = 0;
        int height = 0;
        String rollChar = "";

        // if there are no parentheses to remove do nothing
        if (rollString.indexOf("(") == -1
            && rollString.indexOf(")") == -1) {
            return rollString;
        }
        // Remove any double parentheses (something of the form "abcd()efg")
        rollString = rollString.replaceAll("\\(\\)", "");

        // Track the level of nesting throughout the String
        // For example:
        // (abcde(fghijk))
        //       ^
        // ^     Nesting level at this point is +2
        // Nesting level at this point is +1
        // If there is a minimum level of nesting that is greater than 0, that
        //     signifies that there is some number of parentheses that are
        //     layered around some inner section of text like this:
        //     "((abcd(efg)))"
        // Therefore, there is some number of parentheses on each side that can
        //     be removed without changing meaning
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
            // For example, something of the form "((abcde))fghi"
            return rollString;
        }
        // minLevel cannot be < 0 because this was already checked for in
        //     Roll.checkRolls()
        // Therefore, minLevel must be > 0 at this point
        // So there is some number of parentheses (minLevel) on each side which
        //     can be removed
        // Remove them
        rollString = rollString.substring(minLevel);
        rollString = rollString.substring(0, rollString.length()
            - minLevel);

        return rollString;
    }
    // TODO: change from int to int[] with the result in index 0 and all the
    //     rolls made trailing that to allow users to see what was rolled
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
    public static int roll(int maxRoll) {
        double randomNum;
        int result;

        if (maxRoll != 2 && maxRoll != 3 && maxRoll != 6 && maxRoll != 20) {
            HelperMethods.warn("[ WARNING ]: Nonstandard maximum roll value: d"
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
    public static int roll(int rollNum, int maxRoll, int keep, int keepNum) {
        return rollComplex(rollNum, maxRoll, keep, keepNum)[0];
    }
    /**
     * Rolls the proper number of accuracy and difficulty dice based on the
     *     provided accuracy and difficulty values.
     * See pg. 13.
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
    public static int check(Skill skillTrigger, int accuracy,
        int difficulty, boolean isDifficult, int backgroundInvoked,
        boolean teamwork) {
        // See pgs. 13, 45 - 47
        int bonus;

        HelperMethods.checkObject("skillTrigger", skillTrigger);
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
    public static int check(Skill skillTrigger, int accuracy,
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
        HelperMethods.checkObject("mechSkills", mechSkills);
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
    public static int contestedCheck(Skill skillTrigger1,
        Skill skillTrigger2, int accuracy1, int difficulty1,
        int accuracy2, int difficulty2) {
        // See pg. 13
        int result1 = check(skillTrigger1, accuracy1, difficulty1);
        int result2 = check(skillTrigger2, accuracy2, difficulty2);

        // If it's a tie (result1 == result2), the result is +1
        return result1 >= result2 ? +1 : -1;
    }
    public static int contestedCheck(Skill skillTrigger1,
        Skill skillTrigger2) {
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
        HelperMethods.checkObject("mechSkills", mechSkills);
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
    /**
     * Formats a provided expression, cleaning out any unnecessary characters.
     *     expression is assumed to have been run through Roll.checkRolls(),
     *     then Roll.formatRolls(), then Roll.removeParen(), then
     *     Roll.parseRolls().
     * Therefore, it is assumed to not contain any characters other than the
     *     digits 0 - 9, the letters "d", "k", "h", and "l", and parentheses. At
     *     this point, characters such as spaces, operators ("+", "-", "*",
     *     "/"), and other junk characters have been removed. Additionally,
     *     expression is entirely in lowercase.
     * @param expression a String which can be any String. Is assumed to not be
     *     "" or null. Is assumed to be entirely in lowercase.
     * @return a String containing the formatted String.
     */
    public static String formatExpression(String expression) {
        // TODO: fill out
        // TODO: have it replace any combination of "d", "k", "h", and "l"
        //     that isn't "d", "kh", and "kl" exactly (i.e. "dd")
        final String[] allowedLetters = {"d", "k", "h", "l"};
        String rollChar = "";
        String newRollString = "";

        // At this point, newRollString does not contain any characters that
        //     aren't listed in allowedChars. rollString is unchanged.
        // Now, we will attempt to strip any combinations of "d", "k", "h", and
        //     "l" that lead to an irregular pattern (for example, "dd", or
        //     "dkd"). These Strings are technically composed of allowed
        //     characters, but the characters are used in a syntactically
        //     nonsensical way.
        // We will begin by sectioning the String into runs of letters and
        //     digits. Letters will be left as-is, and digits will be replaced
        //     with a "#" symbol.
        for (int i = 0; i < allowedLetters.length; i++) {
            for (int j = 0; j < allowedLetters.length; j++) {
                rollChar = allowedLetters[i];
                rollChar += allowedLetters[j];
                if (rollChar.equals("kh")
                    || rollChar.equals("kl")) {
                    continue;
                }
                newRollString = newRollString.replaceAll(rollChar,
                    allowedLetters[i]);
            }
        }

        return expression;
    }
}
