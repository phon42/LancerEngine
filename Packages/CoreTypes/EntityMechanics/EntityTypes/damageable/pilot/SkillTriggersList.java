package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.Skill;

/**
 * Represents the set of skill triggers a pilot has. Stores an array of those
 *     skill triggers.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class SkillTriggersList {
    /**
     * Can be any Skill[]. Cannot be null or contain null elements.
     */
    private Skill[] skills;

    /**
     * Creates an empty SkillTriggersList.
     */
    public SkillTriggersList() {
        setSkills(new Skill[0]);
    }
    /**
     * Creates a new SkillTriggersList with the provided list of skill triggers.
     * @param skills a Skill[] that cannot be null or contain null
     *     elements.
     */
    public SkillTriggersList(Skill[] skills) {
        setSkills(skills);
    }
    /**
     * Creates a deepest copy of the provided SkillTriggersList.
     * @param skillTriggersList a SkillTriggersList to be copied.
     * @return a SkillTriggersList deepest copy of the provided
     *     SkillTriggersList.
     */
    public SkillTriggersList(SkillTriggersList skillTriggersList) {
        // don't need to make copies of these because the mutators already do so
        this(skillTriggersList.skills);
    }

    public Skill[] getSkills() {
        return HelperMethods.copyOf(skills);
    }
    /**
     * Searches for a specified skill trigger. Returns whether the search was
     *     successful.
     * @param skillName a String containing the name of the skill trigger to be
     *     searched for.
     * @return a boolean containing the result of the search.
     */
    public boolean hasSkill(String skillName) {
        for (Skill skill : this.skills) {
            if (skill.getData().getName().equals(skillName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * Searches for a specified skill trigger. If the skill trigger is present,
     *     returns it.
     * @param skillName a String containing the name of the skill trigger to be
     *     searched for.
     * @return a Skill containing the skill trigger that was searched for.
     * @throws IllegalArgumentException if the requested skill trigger could not
     *     be found.
     */
    public Skill getSkill(String skillName) {
        for (Skill skill : this.skills) {
            if (skill.getData().getName().equals(skillName)) {
                return skill;
            }
        }

        throw new IllegalArgumentException("Requested skill trigger: \""
            + skillName + "\" could not be found.");
    }
    /**
     * Sets this.skills to the provided value.
     * @param skills a Skill[] which cannot be null or contain null elements.
     * @throws IllegalArgumentException if skills is null or contains null
     *     elements.
     */
    public void setSkills(Skill[] skills) {
        HelperMethods.checkObjectArray("New skill triggers",
            skills);
        skills = HelperMethods.copyOf(skills);
        this.skills = skills;
    }

    /**
     * Compares this SkillTriggersList object and obj. If they are the same
     *     class, returns true.
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
     * Compares this SkillTriggersList object and skillTriggersList. If they
     *     have the same property values, returns true.
     * @param skillTriggersList a SkillTriggersList to be compared to.
     * @return a boolean representing whether the two SkillTriggersLists are the
     *     same.
     */
    public boolean equals(SkillTriggersList skillTriggersList) {
        if (skillTriggersList == null) {
            return false;
        }
        if (skillTriggersList.getSkills().length == skills.length
            && skills.length == 0) {
            return true;
        }
        if (skillTriggersList.getSkills().length ==  skills.length) {
            for (int i = 0; i < skills.length; i++) {
                if (! skills[i].equals(
                    skillTriggersList.getSkills()[i])) {
                    return false;
                }
            }
            return true;
        }
        
        return false;
    }
    /**
     * Outputs the skill triggers contained in this object as a String.
     * @return a String representing the output.
     */
    public String generateOutput() {
        // Output something like:
        //     "  Apply Fists to Faces (+2), Assault (+2)\n"
        //     "  Blow Something Up (+2)\n"
        String outputString = "";

        if (this.skills.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        for (int i = 0; i < this.skills.length; i += 2) {
            outputString += "  ";
            for (int j = i; j < Math.min(i + 2, this.skills.length);
                j++) {
                if (j != i) {
                    outputString += " ";
                }
                outputString += this.skills[j].toString();
                if (j + 1 < this.skills.length) {
                    outputString += ",";
                }
            }
            outputString += "\n";
        }

        return outputString;
    }
}