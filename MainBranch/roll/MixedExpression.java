package MainBranch.roll;

import MainBranch.HelperMethods;
import MainBranch.roll.mixedExpression.ConstantExpression;
import MainBranch.roll.mixedExpression.expression.DiceExpression;

public class MixedExpression {
    private DiceExpression randomComponent;
    private ConstantExpression constantComponent;

    public MixedExpression(DiceExpression randomComponent,
        ConstantExpression constantExpression) {
        HelperMethods.checkObject("randomComponent",
            randomComponent);
        setRandomComponent(randomComponent);
        setConstantComponent(constantExpression);
    }
    public MixedExpression(ConstantExpression constantExpression) {
        setRandomComponent(null);
        setConstantComponent(constantExpression);
    }
    public MixedExpression(DiceExpression randomComponent) {
        setRandomComponent(randomComponent);
        setConstantComponent(new ConstantExpression(0));
    }

    public DiceExpression getRandomComponent() {
        return randomComponent;
    }
    public ConstantExpression getConstantComponent() {
        return constantComponent;
    }
    private void setRandomComponent(DiceExpression randomComponent) {
        this.randomComponent = randomComponent;
    }
    private void setConstantComponent(ConstantExpression constantComponent) {
        HelperMethods.checkObject("constantComponent",
            constantComponent);
        this.constantComponent = constantComponent;
    }

    @Override
    public String toString() {
        String output;
        String constantComponent;

        if (this.randomComponent == null) {
            output = this.constantComponent.toString();
        } else {
            output = this.randomComponent.toString();
            constantComponent = this.constantComponent.toString();
            if (this.constantComponent.getValue() < 0) {
                constantComponent = constantComponent.substring(1);
                output += " - " + constantComponent;
            } else if (this.constantComponent.getValue() > 0) {
                output += " + " + constantComponent;
            }
        }

        return output;
    }
    public int roll() {
        int randomResult = 0;

        if (this.randomComponent != null) {
            randomResult = this.randomComponent.roll();
        }

        return randomResult + this.constantComponent.getValue();
    }
}
