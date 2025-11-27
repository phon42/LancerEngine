package MainBranch.roll.mixedExpression.expression;

import MainBranch.roll.mixedExpression.Expression;

public class DiceExpression extends Expression {
    public DiceExpression(String input) {
        super(input);
    }

    @Override
    protected void evaluate(String input) {
        toDiceExpression(input);
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
}
