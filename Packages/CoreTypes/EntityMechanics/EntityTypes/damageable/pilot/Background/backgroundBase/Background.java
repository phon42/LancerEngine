package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase;

import MainBranch.HelperMethods;
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
public class Background {
    // Required properties
    /**
     * The ID of this background (i.e. "pbg_celebrity").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of this background (i.e. "Celebrity").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The description of this background (too large to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    // Optional property
    /**
     * The skills that might be appropriate to choose for this background (i.e.
     *     a SkillData[] containing Charm, Pull Rank, Lead or Inspire, and
     *     Threaten).
     * Can be any SkillData[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private SkillData[] skills;

    public Background(String id, String name, String description,
        SkillData[] skills) {
        // Required properties
        setID(id);
        setName(name);
        setDescription(description);
        // Optional property
        setSkills(skills);
    }
    public Background(String id, String name, String description) {
        this(id, name, description, null);
    }
    public Background(Background background) {
        // Required properties
        setID(background.id);
        setName(background.name);
        setDescription(background.description);
        // Optional property
        setSkills(background.skills);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Optional property
    public SkillData[] getSkills() {
        if (skills != null) {
            return HelperMethods.copyOf(skills);
        }

        return skills;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
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
