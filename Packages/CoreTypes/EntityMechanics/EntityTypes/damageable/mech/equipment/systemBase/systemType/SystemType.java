package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType;

import MainBranch.HelperMethods;

public class SystemType {
    /**
     * This system type's value (i.e. "AI").
     * Can be any String except "". Cannot be null.
     * Case-insensitive.
     * 
     * Currently used values:
     *     "AI", "Deployable", "Drone", "Flight System", "Shield", "System",
     *         "Tech"
     */
    private String value;

    public SystemType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
}
