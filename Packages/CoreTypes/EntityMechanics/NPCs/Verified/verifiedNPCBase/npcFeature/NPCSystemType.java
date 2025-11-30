package Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.npcFeature;

import MainBranch.HelperMethods;

public class NPCSystemType {
    // Required property
    /**
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String value;

    public NPCSystemType(String value) {
        setValue(value);
    }

    // Required property
    public String getValue() {
        return value;
    }
    // Required property
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
}
