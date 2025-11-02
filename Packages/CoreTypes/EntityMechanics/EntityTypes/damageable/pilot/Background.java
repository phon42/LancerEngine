package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skillTrigger.Skill;

/**
 * Represents a single background. Contains information about that background's
 *     id, name, description, and an array of Skills that might be applicable.
 * 
 * Requires a background id, name, description, and an array of Skills that
 *     might be applicable to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
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
    // Optional properties
    /**
     * The skills that might be appropriate to choose for this background (i.e.
     *     a Skill[] containing Charm, Pull Rank, Lead or Inspire, and
     *     Threaten).
     * Can be any Skill[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private Skill[] skills;

    public Background(String id, String name, String description,
        Skill[] skills) {
        setID(id);
        setName(name);
        setDescription(description);
        setSkills(skills);
    }
    public Background(Background background) {
        setID(background.id);
        setName(background.name);
        setDescription(background.description);
        setSkills(background.skills);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Skill[] getSkills() {
        if (skills != null) {
            return HelperMethods.copyOf(skills);
        }
        return skills;
    }
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    public void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    /**
     * Sets this.skills to the provided value.
     * @param skills a Skill[] which cannot be of length 0 or contain null
     *     elements. Can be null.
     * @throws IllegalArgumentException if skills is invalid as defined above.
     */
    public void setSkills(Skill[] skills) {
        if (skills != null) {
            if (skills.length == 0) {
                throw new IllegalArgumentException("skills is of length 0");
            }
            for (Skill skill : skills) {
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
