package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;

/**
 * See pg. 24 - 27.
 */
/**
 * Represents a single skill trigger. Contains the skill's id, its name, the
 *     level at which it is held, and the data of the originating skill.
 * 
 * Requires a skill trigger identifier (either its id or name) and a skill
 *     trigger level to be instantiated.
 * 
 * Used in SkillTriggersList.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Skill {
    /**
     * The id of the skill trigger (i.e. "sk_act_unseen_or_unheard").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of the skill trigger (i.e. "Apply Fists to Faces").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The level at which the skill trigger is held.
     * Must be one of the following values:
     *     2, 4, 6
     */
    private int level;
    /**
     * The actual skill that this Skill object contains (i.e. a SkillData
     *     representing Apply Fists to Faces).
     * Can be any SkillData. Cannot be null.
     */
    private SkillData skillData;

    /**
     * Creates a new Skill with the provided skill trigger identifier and skill
     *     trigger level.
     * @param skillIdentifier a String which cannot be null or "". Can be the
     *     skill's id or name.
     * @param skillLevel an int which must be 2, 4, or 6.
     */
    public Skill(String skillIdentifier, int skillLevel) {
        SkillData data;

        try {
            data = Database.getSkillDataByName(skillIdentifier);
            // skillIdentifier was the skill name
        } catch (NoSuchElementException exception) {
            // skillIdentifier was not the skill name
            // so let's see if it's a skill id
            try {
                data = Database.getSkillDataByID(skillIdentifier);
                // skillIdentifier was the skill id
            } catch (NoSuchElementException exception2) {
                throw new IllegalArgumentException("skillIdentifier value: \""
                    + skillIdentifier + "\" could not be translated into a"
                    + " valid skill id or skill name");
            }
        }
        setSkillData(data);
        setID(data.getID());
        setName(data.getName());
    }
    /**
     * Creates a copy of the provided Skill.
     * @param skill a Skill to be copied.
     * @return a Skill copy of the provided Skill.
     */
    public Skill(Skill skill) {
        HelperMethods.checkObject("skill", skill);
        setName(skill.name);
        setLevel(skill.level);
        setSkillData(skill.skillData);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public SkillData getSkill() {
        return new SkillData(skillData);
    }
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    /**
     * Sets this.level to the provided value.
     * @param level an int which must be 2, 4, or 6.
     * @throws IllegalArgumentException if level is not 2, 4, or 6.
     */
    private void setLevel(int level) {
        if (level != 2 && level != 4 && level != 6) {
            throw new IllegalArgumentException("New level value: " + level
                + "is not one of the following valid values: 2, 4, 6");
        }
        this.level = level;
    }
    /**
     * Sets this.skillData to the provided value.
     * @param skillData a SkillData which cannot be null.
     * @throws IllegalArgumentException if skillData is null.
     */
    private void setSkillData(SkillData skillData) {
        HelperMethods.checkObject("skillData", skillData);
        skillData = new SkillData(skillData);
        this.skillData = skillData;
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