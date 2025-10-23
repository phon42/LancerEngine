package main.roll;

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
            return super.roll();
        } else {
            return this.rollNum;
        }
    }
}
