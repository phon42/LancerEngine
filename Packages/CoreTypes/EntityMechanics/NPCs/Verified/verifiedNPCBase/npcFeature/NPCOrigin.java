package Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.npcFeature;

import MainBranch.HelperMethods;

public class NPCOrigin {
    // Required properties
    /**
     * Can be any String. Cannot be null.
     */
    private String type;
    /**
     * Can be any String. Cannot be null.
     */
    private String name;
    private boolean base;

    public NPCOrigin(String type, String name, boolean base) {
        setType(type);
        setName(name);
        setBase(base);
    }

    // Required properties
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public boolean isBase() {
        return base;
    }
    // Required properties
    private void setType(String type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    private void setName(String name) {
        HelperMethods.checkObject("name", name);
        this.name = name;
    }
    private void setBase(boolean base) {
        this.base = base;
    }
}
