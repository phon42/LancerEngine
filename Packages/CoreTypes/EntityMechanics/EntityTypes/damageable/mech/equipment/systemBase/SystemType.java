package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase;

import MainBranch.HelperMethods;

public class SystemType {
    /**
     * This system type's value (i.e. "Mech System").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "Mech System", "Weapon"
     */
    protected String value;

    public SystemType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    protected void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
}
