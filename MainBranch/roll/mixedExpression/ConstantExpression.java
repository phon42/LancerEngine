package MainBranch.roll.mixedExpression;

import MainBranch.HelperMethods;

public class ConstantExpression {
    private int value;

    public ConstantExpression(int value) {
        setValue(value);
    }
    public ConstantExpression(String input) {
        setValue(input);
    }

    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    private void setValue(String input) {
        int value;

        HelperMethods.checkString("input", input);
        value = Integer.parseInt(input);
        setValue(value);
    }
    public static boolean isValid(String constantExpression)
        throws IllegalArgumentException {
        HelperMethods.checkObject("constantExpression",
            constantExpression);
        try {
            Integer.parseInt(constantExpression);

            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
