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
    public MixedExpression(String input) {
        parseString(input);
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
    private void parseString(String input) {
        String[] splitInput;
        String randomComponentString;
        String constantComponentString;
        DiceExpression randomComponent;
        ConstantExpression constantComponent;

        // Input sanitization
        HelperMethods.checkString("input", input);
        // Move from a String to two String components
        if (input.indexOf("+") != -1) {
            splitInput = input.split("+");
            splitInput = processStringArray(splitInput);
            randomComponentString = processComponent(splitInput[0],
                false);
            constantComponentString = processComponent(splitInput[1],
                false);
        } else if (input.indexOf("-") != -1) {
            splitInput = input.split("-");
            splitInput = processStringArray(splitInput);
            randomComponentString = processComponent(splitInput[0],
                false);
            constantComponentString = processComponent(splitInput[1],
                true);
        } else {
            randomComponentString = input;
            if (! DiceExpression.isValid(randomComponentString)) {
                constantComponentString = input;
            } else {
                constantComponentString = "";
            }
        }
        // Move from Strings to objects
        if (DiceExpression.isValid(randomComponentString)) {
            randomComponent = new DiceExpression(randomComponentString);
        } else {
            randomComponent = null;
        }
        if (ConstantExpression.isValid(constantComponentString)) {
            constantComponent = new ConstantExpression(constantComponentString);
        } else {
            constantComponent = new ConstantExpression(0);
        }
        // Use the processed material
        setRandomComponent(randomComponent);
        setConstantComponent(constantComponent);
    }
    private static String processComponent(String input,
        boolean isNegative) {
        if (input == null) {
            return "";
        } else {
            if (isNegative) {
                input = "-" + input;
            }
        }

        return input;
    }
    private static String[] processStringArray(String[] input) {
        String[] output = new String[2];
        String randomStr;
        String constantStr;

        // Input sanitization
        HelperMethods.checkObjectArray("input", input);
        if (input.length == 0) {
            throw new IllegalArgumentException("input array is of length 0");
        }
        // Preprocessing step
        if (input[0].equals("")) {
            input = HelperMethods.arraySlice(input, 1);
        }
        // Processing
        if (input.length < 1) {
            randomStr = "";
        } else {
            randomStr = input[0];
        }
        if (input.length < 2) {
            constantStr = "";
        } else {
            constantStr = input[1];
        }
        // Prepare the output
        output[0] = randomStr;
        output[1] = constantStr;

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
