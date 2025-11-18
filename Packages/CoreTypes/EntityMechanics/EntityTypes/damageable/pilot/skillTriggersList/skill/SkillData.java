package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.skillData.SkillFamily;

/**
 * See pg. 24 - 27.
 */
/**
 * Represents a single skill trigger's data. Contains information about that
 *     skill trigger, such as its id and the conditions in which it applies.
 * 
 * Requires a skill trigger ID, name, description, a more detailed description,
 *     and the family of skills in which it fits to be instantiated.
 * 
 * Used in Skill.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class SkillData {
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
     * The family of skill triggers in which this skill trigger fits (i.e. a
     *     SkillFamily representing "dex").
     * Can be any SkillFamily. Cannot be null.
     */
    private SkillFamily family;

    public SkillData(String id, String name, String shortDescription,
        String detailedDescription, SkillFamily skillFamily) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setDescription(shortDescription);
        setDetail(detailedDescription);
        setFamily(skillFamily);
    }
    public SkillData(SkillData skillData) {
        setID(skillData.id);
        setName(skillData.name);
        setDescription(skillData.description);
        setDetail(skillData.detail);
        setFamily(skillData.family);
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
    public String getDetail() {
        return detail;
    }
    public SkillFamily getFamily() {
        return family;
    }
    private void setID(String id) {
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    private void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        this.detail = detail;
    }
    private void setFamily(SkillFamily family) {
        HelperMethods.checkObject("family", family);
        this.family = family;
    }

    /**
     * Generates a String representation of this SkillData.
     * @return a String containing a representation of this SkillData.
     */
    @Override
    public String toString() {
        return "[ " + family.toString().toUpperCase() + " ] " + name + " (\""
            + id + "\")\n  " + description;
    }
    /**
     * Compares this SkillData object and obj. If they are the same class,
     *     returns true.
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
     * Compares this SkillData object and skillData. If they have the same
     *     property values, returns true.
     * @param skillData a SkillData to be compared to.
     * @return a boolean representing whether the two SkillDatas are the same.
     */
    public boolean equals(SkillData skillData) {
        if (skillData == null) {
            return false;
        }
        if (! skillData.getID().equals(this.id)) {
            return false;
        }
        if (! skillData.getName().equals(this.name)) {
            return false;
        }
        if (! skillData.getDescription().equals(this.description)) {
            return false;
        }
        if (! skillData.getDetail().equals(this.detail)) {
            return false;
        }
        if (! skillData.getFamily().equals(this.family)) {
            return false;
        }
        
        return true;
    }
}