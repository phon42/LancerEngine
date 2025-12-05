package Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified;

import MainBranch.HelperMethods;

// TODO: fill out
public class SynergyLocation {
    // Required property
    /**
     * This synergy location's id (i.e. "active_effects").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;

    // Optional property
    /**
     * A short description for developers of what this synergy location actually
     *     means (i.e. "The Active Effects panel near the top of the Active"
     *     " Mode view").
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String description;

    public SynergyLocation(String id, String description) {
        HelperMethods.verifyConstructor();
        setID(id);
        setDescription(description);
    }
    public SynergyLocation(String id) {
        this(id, null);
    }

    // Required property
    public String getID() {
        return id;
    }
    // Optional property
    public String getDescription() {
        return description;
    }
    // Required property
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Optional property
    public void setDescription(String description) {
        HelperMethods.checkStringAlt("description", description);
        this.description = description;
    }
}
