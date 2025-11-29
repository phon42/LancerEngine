package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase;

import MainBranch.HelperMethods;

public class SystemType {
    // Static property
    protected static int maxID = 0;

    // Required property
    /**
     * This system type's value (i.e. "Mech System").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "Mech System", "Weapon"
     */
    protected String value;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * The system type's int id (i.e. 0 for a mech system).
     * Can be any int.
     * 
     * Currently used values:
     *     0 - Mech System
     *     1 - Weapon
     */
    protected int id;

    public SystemType(String value, int id) {
        HelperMethods.verifyConstructor();
        setValue(value);
        setID(id);
    }
    public SystemType(String value) {
        this(value, SystemType.maxID + 1);
    }

    // Required property
    public String getValue() {
        return value;
    }
    // Semi-required property
    public int getID() {
        return id;
    }
    // Required property
    protected void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
    // Semi-required property
    protected void setID(int id) {
        this.id = id;
    }
}
