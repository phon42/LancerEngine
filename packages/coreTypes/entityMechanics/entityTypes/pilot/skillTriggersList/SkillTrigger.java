package packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList;

import main.HelperMethods;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.skillTrigger.Skill;

/**
 * See pg. 24 - 27.
 */
/**
 * Represents a single skill trigger. Contains the skill trigger's name and the
 *     level at which it is held.
 * 
 * Requires a skill trigger name and a skill trigger level to be instantiated.
 * 
 * Used in SkillTriggersList.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class SkillTrigger {
    /**
     * The name of the skill trigger (i.e. "Apply Fists to Faces").
     * Can be any String except "". Cannot be null.
     */
    private String name;
    /**
     * The level at which the skill trigger is held.
     * Must be one of the following values:
     *     2, 4, 6
     */
    private int level;

    /**
     * Creates a new SkillTrigger with the provided skill trigger name and skill
     *     trigger level.
     * @param skillName a String which cannot be null or "".
     * @param skillLevel an int which must be 2, 4, or 6.
     */
    public SkillTrigger(String skillName, int skillLevel) {
        setName(skillName);
        setLevel(skillLevel);
    }
    /**
     * Creates a copy of the provided SkillTrigger.
     * @param skillTrigger a SkillTrigger to be copied.
     * @return a SkillTrigger copy of the provided SkillTrigger.
     */
    public SkillTrigger(SkillTrigger skillTrigger) {
        this(skillTrigger.name, skillTrigger.level);
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
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