package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase;

import MainBranch.HelperMethods;

public class SystemType {
    // Static property
    protected static int maxID = 0;

    // Required property
    /**
     * The broad category this system type falls into (i.e. "Mech System").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "Mech System", "Weapon"
     */
    protected String category;

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

    public SystemType(String category, int id) {
        HelperMethods.verifyConstructor();
        setCategory(category);
        setID(id);
    }
    public SystemType(String category) {
        this(category, SystemType.maxID + 1);
    }

    // Required property
    public String getCategory() {
        return category;
    }
    // Semi-required property
    public int getID() {
        return id;
    }
    // Required property
    protected void setCategory(String category) {
        HelperMethods.checkString("category", category);
        this.category = category;
    }
    // Semi-required property
    protected void setID(int id) {
        this.id = id;
    }
}
