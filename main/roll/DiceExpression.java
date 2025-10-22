package main.roll;

import main.Roll;

public class DiceExpression {
    protected String value;
    protected boolean containsKeep = false;
    protected boolean containsX = false;
    protected boolean containsZ = false;
    protected int rollNum;
    protected int maxRoll;
    protected int keep;
    protected int keepNum;

    public DiceExpression(String input) {
        this.value = input;
        toDiceExpression(input);
    }
    /**
     * Takes in a String and sets this DiceExpression object's properties to
     *     their appropriate values based on the input String.
     * - "XdYkhZ"
     * - "XdYklZ"
     * - "dYkhZ"
     * - "dYklZ"
     * - "dYkh"
     * - "dYkl"
     * - "XdYkh"
     * - "XdYkl"
     * - "XdY"
     * - "dY"
     * Note: "X" is NOT allowed because as a constant, it is an expression, not
     *     a dice expression.
     * @param diceExpression a String which must be a dice expression as defined
     *     above. Cannot be null.
     */
    protected void toDiceExpression(String diceExpression) {
        boolean containsKeepHighest = false;
        boolean containsKeepLowest = false;
        boolean containsD = false;
        String[] substring;
        String[] substring2 = new String[0];

        if (diceExpression == null) {
            throw new IllegalArgumentException("Cannot convert null to a"
                + " DiceExpression");
        }
        diceExpression = diceExpression.toLowerCase();
        Roll.checkRolls(diceExpression);
        diceExpression = Roll.formatExpression(diceExpression);
        diceExpression = Roll.removeParen(diceExpression);
        if (diceExpression.equals("")) {
            throw new IllegalArgumentException("Cannot convert \"\" to a"
                + " DiceExpression");
        }
        // determining whether the expression contains any of several key
        //     components
        // splitting everything into two types: 'contains "d"' (all valid
        //     DiceExpressions) and 'doesn't contain "d"' (an invalid
        //     DiceExpression)
        containsD = diceExpression.indexOf("d") != -1;
        if (containsD) {
            // splitting 'contains "d"' into two types: 'contains "kh" or "kl"'
            //     and 'doesn't contain "kh" or "kl"'
            containsKeepHighest = (diceExpression.indexOf("kh") != -1);
            containsKeepLowest = (diceExpression.indexOf("kl") != -1);
            this.containsKeep = containsKeepHighest || containsKeepLowest;
            // could be of the form "XdY" or "dY" (for either one, what's after
            //     Y doesn't matter), and we need to know which
            substring = diceExpression.split("d");
            this.containsX = ! substring[0].equals("");
        } else {
            throw new IllegalArgumentException("Cannot convert value: \""
                + diceExpression + "\" to a DiceExpression");
        }
        if (containsKeepHighest) {
            if (diceExpression.split("kh").length > 1) {
                this.containsZ = true;
            }
        } else if (containsKeepLowest) {
            if (diceExpression.split("kl").length > 1) {
                this.containsZ = true;
            }
        }
        // now to actually evaluate the expression
        // String is of the form "XdYkhZ" or "XdYklZ" or "dYkhZ" or "dYklZ" or
        //     "dYkh" or "dYkl" or "XdYkh" or "XdYkl" or "XdY" or "dY"
        substring = diceExpression.split("d");
        if (this.containsKeep) {
            // String is of the form "XdYkhZ" or "XdYklZ" or "dYkhZ" or "dYklZ"
            //     or "dYkh" or "dYkl" or "XdYkh" or "XdYkl"
            if (this.containsX) {
                // String is of the form "XdYkhZ" or "XdYklZ" or "XdYkh" or
                //     "XdYkl"
                this.rollNum = Integer.parseInt(substring[0]);
            } else {
                // String is of the form "dYkhZ" or "dYklZ" or "dYkh" or "dYkl"
                this.rollNum = 1;
            }
            if (containsKeepHighest) {
                substring2 = substring[1].split("kh");
                this.keep = +1;
            } else if (containsKeepLowest) {
                substring2 = substring[1].split("kl");
                this.keep = -1;
            } else {
                throw new IllegalArgumentException();
            }
            this.maxRoll = Integer.parseInt(substring2[0]);
            if (this.containsZ) {
                this.keepNum = Integer.parseInt(substring2[1]);
            } else {
                this.keepNum = 1;
            }
        } else if (this.containsX) {
            // String is of the form "XdY"
            rollNum = Integer.parseInt(substring[0]);
            maxRoll = Integer.parseInt(substring[1]);
        } else {
            // String is of the form "dY"
            maxRoll = Integer.parseInt(substring[substring.length - 1]);
        }
    }
    /**
     * Checks whether the provided String is a valid dice expression. In other
     *     words, if it is in one of the following forms:
     * - "XdYkhZ"
     * - "XdYklZ"
     * - "dYkhZ"
     * - "dYklZ"
     * - "dYkh"
     * - "dYkl"
     * - "XdYkh"
     * - "XdYkl"
     * - "XdY"
     * - "dY"
     * Note: "X" is NOT allowed because as a constant, it is an expression, not
     *     a dice expression.
     * @param diceExpression a String which must be a dice expression as defined
     *     above. Cannot be null.
     * @return a boolean containing the result of the check.
     */
    public static boolean isValid(String diceExpression) {
        try {
            new DiceExpression(diceExpression);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public int roll() {
        if (containsKeep) {
            // one of the following: "XdYkhZ" or "XdYklZ" or "XdYkh" or "XdYkl"
            if (containsZ) {
                // either "XdYkhZ" or "XdYklZ"
                return Roll.roll(this.rollNum, this.maxRoll, this.keep,
                    this.keepNum);
            } else {
                // either "XdYkh" or "XdYkl"
                return Roll.roll(this.rollNum, this.maxRoll, this.keep,
                    1);
            }
        } else {
            // either "XdY" or "dY"
            if (containsX) {
                // "XdY"
                return Roll.roll(this.rollNum, this.maxRoll, 0, 0);
            } else {
                // "dY"
                return Roll.roll(this.maxRoll);
            }
        }
    }
}
