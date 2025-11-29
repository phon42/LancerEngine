package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.SystemType;

public final class WeaponType extends SystemType {
    // TODO: figure out a way to override the documentation from SystemType
    /**
     * The broad category this weapon type falls into.
     * Must be "Weapon".
     */
    // protected String category;
    /**
     * The weapon type's exact value (i.e. "CQB").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "CQB", "Cannon", "Launcher", "Melee", "Nexus", "Rifle",
     *     "Drone Weapon", "Spool Weapon", "???", "Special"
     * 
     * See pg. 33.
     */
    private String value;

    public WeaponType(String value) {
        super("Weapon");
        setValue(value);
    }

    // Required properties
    public String getValue() {
        return value;
    }
    // Required properties
    @Override
    protected void setCategory(String category) {
        this.category = "Weapon";
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
}
