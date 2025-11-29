package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.SystemType;

public final class WeaponType extends SystemType {
    // TODO: figure out a way to override the documentation from SystemType
    /**
     * This weapon type's value.
     * Must be "Weapon".
     */
    // protected String value;
    /**
     * The weapon type's detailed value (i.e. "CQB").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "CQB", "Cannon", "Launcher", "Melee", "Nexus", "Rifle",
     *     "Drone Weapon", "Spool Weapon", "???", "Special"
     * 
     * See pg. 33.
     */
    private String detailedValue;

    public WeaponType(String detailedValue) {
        super("Weapon");
        setDetailedValue(detailedValue);
    }

    // Required properties
    public String getDetailedValue() {
        return detailedValue;
    }
    // Required properties
    @Override
    protected void setValue(String value) {
        this.value = "Weapon";
    }
    private void setDetailedValue(String detailedValue) {
        HelperMethods.checkString("detailedValue", detailedValue);
        this.detailedValue = detailedValue;
    }
}
