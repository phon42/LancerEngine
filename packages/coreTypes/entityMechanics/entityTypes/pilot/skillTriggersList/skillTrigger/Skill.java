package packages.CoreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.skillTrigger;

import main.HelperMethods;

/**
 * See pg. 24 - 27.
 */
/**
 * Represents a single skill trigger. Contains information about that skill
 *     trigger, such as its id and the conditions in which it applies.
 * 
 * Requires a skill trigger ID, name, description, a more detailed description,
 *     and the family of skills in which it fits to be instantiated.
 * 
 * Used in SkillTrigger.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Skill {
    /**
     * The ID of the skill trigger (i.e. "sk_act_unseen_or_unheard").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of the skill trigger (i.e. "Act Unseen or Unheard"").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * A description of what the skill trigger allows you to do (i.e. "Get"
     *     " somewhere or do something without detection.").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;
    /**
     * A more detailed description of what the skill trigger allows you to do
     *     (too large to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String detail;
    /**
     * The family of skill triggers in which this skill trigger fits (i.e.
     *     "dex").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String family;

    public Skill(String id, String name, String shortDescription,
        String detailedDescription, String skillFamily) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setDescription(shortDescription);
        setDetail(detailedDescription);
        setFamily(skillFamily);
    }
    public Skill(Skill skill) {
        setID(skill.id);
        setName(skill.name);
        setDescription(skill.description);
        setDetail(skill.detail);
        setFamily(skill.family);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setID(String id) {
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }

    /**
     * Generates a String representation of this Skill.
     * @return a String containing a representation of this Skill.
     */
    @Override
    public String toString() {
        return getName() + " (+" + getLevel() + ")";
    }
    /**
     * Compares this Skill object and obj. If they are the same class, returns
     *     true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return true;
    }
    /**
     * Compares this Skill object and skill. If they have the same property
     *     values, returns true.
     * @param skill a Skill to be compared to.
     * @return a boolean representing whether the two Skills are the same.
     */
    public boolean equals(Skill skill) {
        if (skill == null) {
            return false;
        }
        if (! skill.getName().equals(this.name)) {
            return false;
        }
        if (skill.getLevel() != this.level) {
            return false;
        }
        
        return true;
    }
}