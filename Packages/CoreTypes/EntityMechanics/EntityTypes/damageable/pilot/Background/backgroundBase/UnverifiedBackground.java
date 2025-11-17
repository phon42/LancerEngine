package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.BackgroundBase;

/**
 * From https://github.com/massif-press/lancer-data/blob/master/README.md#backgrounds-backgroundsjson.
 */
/**
 * Represents a single background. Contains information about that background's
 *     id, name, description, and an array of skills as Strings that might be
 *     applicable.
 * 
 * Requires a background id, name, and description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class UnverifiedBackground extends BackgroundBase {
    // Optional property
    /**
     * The skills that might be appropriate to choose for this background as
     *     Strings (i.e. a String[] containing "Charm", "Pull Rank", "Lead or"
     *     " Inspire", and "Threaten").
     * Can be any String[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private String[] skillStrings;

    public UnverifiedBackground(
        // BackgroundBase properties
        String id, String name, String description,
        // Optional property
        String[] skillStrings) {
        super(id, name, description);
        // Optional property
        setSkillStrings(skillStrings);
    }
    public UnverifiedBackground(String id, String name, String description) {
        this(id, name, description, null);
    }
    public UnverifiedBackground(UnverifiedBackground unverifiedBackground) {
        super(unverifiedBackground);
        // Optional property
        setSkillStrings(unverifiedBackground.skillStrings);
    }

    // Optional property
    public String[] getSkillStrings() {
        if (skillStrings != null) {
            return HelperMethods.copyOf(skillStrings);
        }

        return skillStrings;
    }
    // Optional property
    /**
     * Sets this.skillStrings to the provided value.
     * @param skillStrings a String[] which cannot be of length 0 or contain
     *     null elements. Can be null.
     * @throws IllegalArgumentException if skillStrings is invalid as defined
     *     above.
     */
    private void setSkillStrings(String[] skillStrings) {
        if (skillStrings != null) {
            HelperMethods.checkObjectArray("skillStrings",
                skillStrings);
            if (skillStrings.length == 0) {
                throw new IllegalArgumentException("skillStrings is of"
                    + " length 0");
            }
            skillStrings = HelperMethods.copyOf(skillStrings);
        }
        this.skillStrings = skillStrings;
    }
}
