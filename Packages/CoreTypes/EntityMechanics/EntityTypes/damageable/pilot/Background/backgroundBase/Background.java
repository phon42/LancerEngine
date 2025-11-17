package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.BackgroundBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;

/**
 * From https://github.com/massif-press/lancer-data/blob/master/README.md#backgrounds-backgroundsjson.
 */
/**
 * Represents a single background. Contains information about that background's
 *     id, name, description, and an array of SkillDatas that might be
 *     applicable.
 * 
 * Requires a background id, name, and description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class Background extends BackgroundBase {
    // Optional property
    /**
     * The skills that might be appropriate to choose for this background (i.e.
     *     a SkillData[] containing Charm, Pull Rank, Lead or Inspire, and
     *     Threaten).
     * Can be any SkillData[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private SkillData[] skills;

    public Background(
        // BackgroundBase properties
        String id, String name, String description,
        // Optional property
        SkillData[] skills) {
        super(id, name, description);
        // Optional property
        setSkills(skills);
    }
    public Background(String id, String name, String description) {
        this(id, name, description, null);
    }
    public Background(Background background) {
        super(background);
        // Optional property
        setSkills(background.skills);
    }

    // Optional property
    public SkillData[] getSkills() {
        if (skills != null) {
            return HelperMethods.copyOf(skills);
        }

        return skills;
    }
    // Optional property
    /**
     * Sets this.skills to the provided value.
     * @param skills a SkillData[] which cannot be of length 0 or contain null
     *     elements. Can be null.
     * @throws IllegalArgumentException if skills is invalid as defined above.
     */
    private void setSkills(SkillData[] skills) {
        if (skills != null) {
            if (skills.length == 0) {
                throw new IllegalArgumentException("skills is of length 0");
            }
            for (SkillData skill : skills) {
                if (skill == null) {
                    throw new IllegalArgumentException("skills array contains"
                        + " a null element");
                }
            }
            skills = HelperMethods.copyOf(skills);
        }
        this.skills = skills;
    }
}
