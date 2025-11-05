package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

public class ActivationType {
    /**
     * The value of this activation type (i.e. "free").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String value;

    public ActivationType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
    }
    public ActivationType(ActivationType activationType) {
        setValue(activationType.value);
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }
}
