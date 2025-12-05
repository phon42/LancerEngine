package Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.SynergyLocationBase;

// TODO: fill out
public class SynergyLocation extends SynergyLocationBase {
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
        super(id);
        setDescription(description);
    }
    public SynergyLocation(String id) {
        this(id, null);
    }

    // Optional property
    public String getDescription() {
        return description;
    }
    // Optional property
    public void setDescription(String description) {
        HelperMethods.checkStringAlt("description", description);
        this.description = description;
    }
}
