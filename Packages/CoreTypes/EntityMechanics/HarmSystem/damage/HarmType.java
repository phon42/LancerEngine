package Packages.CoreTypes.EntityMechanics.HarmSystem.damage;

import MainBranch.HelperMethods;

public class HarmType {
    protected String value;

    public HarmType(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    protected void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }

    public String outputValue() {
        return HelperMethods.capitalizeFirst(value);
    }
}
