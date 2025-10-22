package main.roll;

import main.Roll;

public class Expression extends DiceExpression {
    private boolean containsD = false;

    public Expression(String input) {
        super("1d20");
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
    private void toExpression(String expression) {
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
            // String is of the form "X"
            this.rollNum = Integer.parseInt(expression);
        }
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
        boolean isDiceExpression;
        int result;

        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        expression = expression.toLowerCase();
        if (expression.equals("")) {
            return false;
        }
        Roll.checkRolls(expression);
        expression = Roll.formatExpression(expression);
        expression = Roll.removeParen(expression);
        isDiceExpression = DiceExpression.isValid(expression);
        if (! isDiceExpression) {
            // last chance for redemption - expression could be of the form "X"
            try {
                result = Integer.parseInt(expression);
                if (result < 0) {
                    // expressions of the form "X" where X < 0 are not allowed
                    return false;
                }
                // X >= 0 so this is allowed
                return true;
            } catch (NumberFormatException e) {
                // expression is of some weird format
                return false;
            }
        }

        // expression is a valid expression
        return true;
    }

    public int roll() {
        if (containsD) {
            return super.roll();
        } else {
            return this.rollNum;
        }
    }
}
