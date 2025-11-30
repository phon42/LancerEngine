package Packages.CoreTypes.EntityMechanics.NPCs.Verified;

import MainBranch.HelperMethods;

public class VerifiedNPCBase {
    // TODO: fill out
    // Required properties
    /**
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;
    /**
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;

    protected VerifiedNPCBase(String id, String name) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    // Required properties
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
}
