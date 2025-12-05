package Packages.CoreTypes.EntityMechanics.SynergyLocationSystem;

import MainBranch.HelperMethods;

// TODO: fill out
public class SynergyLocationBase {
    // Required property
    /**
     * This synergy location's id (i.e. "active_effects").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;

    protected SynergyLocationBase(String id) {
        HelperMethods.verifyConstructor();
        setID(id);
    }

    // Required property
    public String getID() {
        return id;
    }
    // Required property
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
}
