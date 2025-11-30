package Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.VerifiedNPCBase;

/**
 * Represents a single NPC template. Contains information about its features and
 *     power.
 * 
 * Requires an npc template id, name, description, base features array, optional
 *     features array, and power to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class NPCTemplate extends VerifiedNPCBase {
    // TODO: fill out
    // Required properties
    /**
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;
    /**
     * Can be any int.
     */
    private int power;

    // Optional properties
    /**
     * Can be any String[] that is not of length 0 and does not contain "" or
     *     null elements. Can be null.
     * Elements are case-insensitive and stored in lowercase.
     */
    private String[] baseFeatures;
    /**
     * Can be any String[] that is not of length 0 and does not contain "" or
     *     null elements. Cannot be null.
     * Elements are case-insensitive and stored in lowercase.
     */
    private String[] optionalFeatures;

    public NPCTemplate(
        // NCPBase properties
        String id, String name,
        // Required properties
        String description, int power,
        // Optional properties
        String[] baseFeatures, String[] optionalFeatures
    ) {
        HelperMethods.verifyConstructor();
        // NCPBase properties
        super(id, name);
        // Required properties
        setDescription(description);
        setPower(power);
        // Optional properties
        setBaseFeatures(baseFeatures);
        setOptionalFeatures(optionalFeatures);
    }
    public NPCTemplate(
        // NCPBase properties
        String id, String name,
        // Required properties
        String description, int power
    ) {
        this(id, name, description, power, null,
            null);
    }

    // Required properties
    public String getDescription() {
        return description;
    }
    public int getPower() {
        return power;
    }
    // Optional properties
    public String[] getBaseFeatures() {
        if (baseFeatures != null) {
            return HelperMethods.copyOf(baseFeatures);
        }

        return baseFeatures;
    }
    public String[] getOptionalFeatures() {
        if (optionalFeatures != null) {
            return HelperMethods.copyOf(optionalFeatures);
        }

        return optionalFeatures;
    }
    // Required properties
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    private void setPower(int power) {
        this.power = power;
    }
    // Optional properties
    private void setBaseFeatures(String[] baseFeatures) {
        HelperMethods.checkStringArrayAlt("baseFeatures",
            baseFeatures);
        if (baseFeatures != null) {
            baseFeatures = HelperMethods.copyOf(baseFeatures);
            for (int i = 0; i < baseFeatures.length; i++) {
                baseFeatures[i] = baseFeatures[i].toLowerCase();
            }
        }
        this.baseFeatures = baseFeatures;
    }
    private void setOptionalFeatures(String[] optionalFeatures) {
        HelperMethods.checkStringArrayAlt("optionalFeatures",
            optionalFeatures);
        if (optionalFeatures != null) {
            optionalFeatures = HelperMethods.copyOf(optionalFeatures);
            for (int i = 0; i < optionalFeatures.length; i++) {
                optionalFeatures[i] = optionalFeatures[i].toLowerCase();
            }
        }
        this.optionalFeatures = optionalFeatures;
    }
}
