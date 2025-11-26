package Packages.CoreTypes.EntityMechanics.HarmSystem.damage.harm;

import MainBranch.HelperMethods;

public class HarmType {
    private String value;

    public HarmType(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }

    public String outputValue() {
        return HelperMethods.capitalizeFirst(value);
    }
}
