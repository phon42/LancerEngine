package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.SystemType;

public class MechSystemType extends SystemType {
    // Required properties
    // TODO: figure out a way to override the documentation from SystemType
    /**
     * This mech system type's value.
     * Must be "Mech System".
     */
    // protected String value;
    /**
     * This mech system type's detailed value (unable to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String detailedValue;

    public MechSystemType(String detailedValue) {
        super("Mech System");
        setDetailedValue(detailedValue);
    }

    // Required properties
    public String getDetailedValue() {
        return detailedValue;
    }
    // Required properties
    @Override
    protected void setValue(String value) {
        this.value = "Mech System";
    }
    private void setDetailedValue(String detailedValue) {
        HelperMethods.checkString("detailedValue", detailedValue);
        this.detailedValue = detailedValue;
    }
}