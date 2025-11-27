package MainBranch.roll.mixedExpression;

public class ConstantExpression {
    private int value;

    public ConstantExpression(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        this.value = value;
    }
}
