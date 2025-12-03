package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.SystemType;

public final class MechSystemType extends SystemType {
    // Required properties
    // TODO: figure out a way to override the documentation from SystemType
    /**
     * The broad category this mech system type falls into.
     * Must be "Mech System".
     */
    // protected String category;
    /**
     * This mech system type's exact value (i.e. "AI").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "AI", "Deployable", "Drone", "Flight System", "Shield", "System",
     *         "Tech"
     */
    private String value;

    public MechSystemType(String value) {
        super("Mech System");
        setValue(value);
    }

    // Required properties
    public String getValue() {
        return value;
    }
    // Required properties
    @Override
    protected void setCategory(String category) {
        this.category = "Mech System";
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
}
