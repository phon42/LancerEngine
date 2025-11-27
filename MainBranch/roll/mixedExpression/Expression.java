package MainBranch.roll.mixedExpression;

import MainBranch.Roll;

public class Expression {
    // Protected properties
    protected String value;
    protected boolean containsKeep = false;
    protected boolean containsX = false;
    protected boolean containsZ = false;
    protected int rollNum;
    protected int maxRoll;
    protected int keep;
    protected int keepNum;
    protected boolean containsD;

    public Expression(String input) {
        this.containsD = false;
        this.value = input;
        evaluate(this.value);
    }

    protected void evaluate(String input) {
        toExpression(input);
    }
    /**
     * Takes in a String and sets this Expression object's properties to their
     *     appropriate values based on the input String.
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
     * - "X"
     * @param expression a String which must be an expression as defined above.
     *     Cannot be null.
     */
    protected void toExpression(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Cannot convert null to an"
                + " Expression");
        }
        if (expression.equals("")) {
            throw new IllegalArgumentException("Cannot convert \"\" to an"
                + " Expression");
        }
        // determining whether the expression contains any of several key
        //     components
        // splitting everything into two types: 'contains "d"' (pretty much
        //     everything) and 'doesn't contain "d"' ("X")
        this.containsD = expression.indexOf("d") != -1;
        // now to actually evaluate the expression
        if (this.containsD) {
            toDiceExpression(expression);
        } else {
            // String is of the form "X" (hopefully)
            try {
                this.rollNum = Integer.parseInt(expression);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Unable to extract a rollNum"
                    + " value from value: \"" + expression + "\"");
            }
        }
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
            this.rollNum = 1;
            this.maxRoll = 1;
            this.keep = 0;
            this.keepNum = 1;
            // splitting 'contains "d"' into two types: 'contains "kh" or "kl"'
            //     and 'doesn't contain "kh" or "kl"'
            containsKeepHighest = diceExpression.indexOf("kh") != -1;
            containsKeepLowest = diceExpression.indexOf("kl") != -1;
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
            // rollNum
            if (this.containsX) {
                // String is of the form "XdYkhZ" or "XdYklZ" or "XdYkh" or
                //     "XdYkl"
                try {
                    this.rollNum = Integer.parseInt(substring[0]);
                } catch (NumberFormatException exception) {
                    throw new IllegalArgumentException("Unable to extract a"
                        + " rollNum value from value: \"" + substring[0]
                        + "\"");
                }
            } else {
                // String is of the form "dYkhZ" or "dYklZ" or "dYkh" or "dYkl"
                this.rollNum = 1;
            }
            // keep
            if (containsKeepHighest) {
                substring2 = substring[1].split("kh");
                this.keep = +1;
            } else {
                substring2 = substring[1].split("kl");
                this.keep = -1;
            }
            // maxRoll
            try {
                this.maxRoll = Integer.parseInt(substring2[0]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Unable to extract a maxRoll"
                    + " value from value: \"" + substring2[0] + "\"");
            }
            // keepNum
            if (this.containsZ) {
                try {
                    this.keepNum = Integer.parseInt(substring2[1]);
                } catch (NumberFormatException exception) {
                    throw new IllegalArgumentException("Unable to extract a"
                        + " keepNum value from value: \"" + substring2[1]
                        + "\"");
                }
            } else {
                this.keepNum = 1;
            }
        } else if (this.containsX) {
            // String is of the form "XdY"
            try {
                this.rollNum = Integer.parseInt(substring[0]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Unable to extract a rollNum"
                    + " value from value: \"" + substring[0] + "\"");
            }
            try {
                this.maxRoll = Integer.parseInt(substring[1]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Unable to extract a maxRoll"
                    + " value from value: \"" + substring[1] + "\"");
            }
        } else {
            // String is of the form "dY"
            try {
                this.maxRoll = Integer.parseInt(
                    substring[substring.length - 1]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Unable to extract a maxRoll"
                    + " value from value: \"" + substring[substring.length - 1]
                    + "\"");
            }
        }
        if (this.rollNum < 1) {
            throw new IllegalArgumentException("Cannot create a DiceExpression"
                + " with a rollNum value of: " + this.rollNum + " which is <"
                + " 1");
        }
        // checking for invalid values
        if (this.maxRoll < 2) {
            throw new IllegalArgumentException("Cannot create a DiceExpression"
                + " with a maxRoll value of: " + this.maxRoll + " which is <"
                + " 2");
        }
        if (this.keepNum < 1) {
            throw new IllegalArgumentException("Cannot create a DiceExpression"
                + " with a keepNum value of: " + this.keepNum + " which is <"
                + " 1");
        }
        if (this.rollNum < this.keepNum) {
            throw new IllegalArgumentException("Cannot create a DiceExpression"
                + " with a rollNum value (" + this.rollNum + ") that is less"
                + " than its keepNum value (" + this.keepNum + ")");
        }
    }

    @Override
    public String toString() {
        String output = "";

        if (! containsD) {
            // "X"
            output += this.rollNum;

            return output;
        }
        // "d"
        output += "d";
        // "dY"
        output += this.maxRoll;
        if (containsX) {
            // "XdY"
            output = "" + this.rollNum + output;
        }
        if (this.containsKeep) {
            // adding "kh"/"kl" to the end
            output += (this.keep == 1 ? "kh" : "kl");
            if (this.containsZ) {
                // adding "Z"
                output += this.keepNum;
            }
        }

        return output;
    }
    /**
     * Checks whether the provided String is a valid expression. In other words,
     *     if it is in one of the following forms:
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
     * - "X"
     * @param expression a String which must be an expression as defined above.
     *     Cannot be null.
     * @return a boolean containing the result of the check.
     */
    public static boolean isValid(String expression) {
        try {
            new Expression(expression);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
    public int roll() {
        if (containsD) {
            return Roll.roll(this.rollNum, this.maxRoll, this.keep,
                this.keepNum);
        } else {
            return this.rollNum;
        }
    }
}
