package packages.CoreTypes.EntityMechanics;

import main.HelperMethods;

public class Frequency {
    /**
     * Contains this frequency's type (i.e. "X/round").
     * Must be an allowed type as defined by Frequency.allowedTypes. Cannot be
     *     null.
     * Case-insensitive, with the exception of a starting "X/", and stored in
     *     lowercase.
     */
    private String type;
    /**
     * Contains an array of allowed values for Frequency.type.
     * Case-insensitive, with the exception of a starting "X/", and stored in
     *     lowercase.
     */
    private static final String[] allowedTypes = new String[] {
        "unlimited", "X/round", "X/encounter", "X/mission"
    };
    /**
     * Contains an array of values for Frequency.type for which having a
     *     Frequency.value other than -1 makes sense.
     * Case-insensitive, with the exception of a starting "X/", and stored in
     *     lowercase.
     */
    private static final String[] valueTypes = new String[] {
        "X/round", "X/encounter", "X/mission"
    };
    /**
     * This frequency's value, if it has one (i.e. 1, representing the "1" in
     *     "1/round").
     * If this.type is a value for which having a value makes sense, must be >
     *     0. Otherwise, must be -1.
     * Must be a minimum of -1. Cannot be 0.
     */
    private int value;

    public Frequency(String input) {
        processInput(input);
    }
    public Frequency(Frequency frequency) {
        setType(frequency.type);
        setValue(frequency.value);
    }

    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        if (type.indexOf("X/") != -1) {
            type = type.toLowerCase();
            type = HelperMethods.capitalizeFirst(type);
        } else {
            type = type.toLowerCase();
        }
        for (int i = 0; i < Frequency.allowedTypes.length; i++) {
            if (type.equals(Frequency.allowedTypes[i])) {
                break;
            }
            if (i == Frequency.allowedTypes.length - 1) {
                throw new IllegalArgumentException("type value: \"" + type
                    + "\" is an invalid type");
            }
        }
        this.type = type;
    }
    private void setValue(int value) {
        if (value < -1) {
            throw new IllegalArgumentException("value: " + value + " is < -1");
        }
        if (value == 0) {
            throw new IllegalArgumentException("value is 0");
        }
        if ((value == -1) == hasValue(this.type)) {
            // value is -1 and this.type is a value for which having a value
            //     other than -1 MAKES SENSE, OR value is NOT -1 and this.type
            //     is a value for which having a value other than -1 DOES NOT
            //     MAKE SENSE
            if (value == -1) {
                // value is -1 and this.type is a value for which having a
                //     value other than -1 MAKES SENSE
                throw new IllegalStateException("value is -1 and this.type"
                    + " is: \"" + this.type + "\" which requires this.value to"
                    + " be something other than -1");
            } else {
                // value is not -1 and this.type is a value for which having a
                //     value other than -1 does not make sense
                throw new IllegalStateException("value: " + value + " is not -1"
                    + " and this.type is: \"" + this.type + "\" which requires"
                    + " this.value to be -1");
            }
        }
        this.value = value;
    }

    private void processInput(String input) {
        int value;

        HelperMethods.checkString("input", input);
        if (input.indexOf("/") != -1) {
            // it's one of the "X/" types
            value = Integer.parseInt(input.split("/")[0]);
            setType("X/" + input.split("/")[1]);
        } else {
            value = -1;
            // we can just do a regular search
            for (int i = 0; i < Frequency.allowedTypes.length; i++) {
                if (input.equals(Frequency.allowedTypes[i])) {
                    setType(input);
                    break;
                }
                if (i == Frequency.allowedTypes.length - 1) {
                    throw new IllegalArgumentException("type value: \"" + input
                        + "\" is an invalid type");
                }
            }
        }
        setValue(value);
    }
    private boolean hasValue(String type) {
        HelperMethods.checkString("type", type);
        for (String valueType : Frequency.valueTypes) {
            if (type.equals(valueType)) {
                return true;
            }
        }

        return false;
    }
}
