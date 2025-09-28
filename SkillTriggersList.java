/**
 * Stores a set of the skill triggers a pilot has.
 */
public class SkillTriggersList {
    /**
     * Can be any SkillTrigger[]. Cannot be null or contain null elements.
     */
    private SkillTrigger[] skillTriggers;

    public SkillTriggersList() {
        setSkillTriggers(new SkillTrigger[0]);
    }
    public SkillTriggersList(SkillTrigger[] skillTriggers) {
        setSkillTriggers(skillTriggers);
    }

    public SkillTrigger[] getSkillTriggers() {
        return HelperFunctions.copyOf(skillTriggers);
    }
    /**
     * Sets this.skillTriggers to the provided value.
     * @param skillTriggers a SkillTrigger[] which cannot be null or contain
     *     null elements.
     */
    public void setSkillTriggers(SkillTrigger[] skillTriggers) {
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers value is"
                + " null");
        }
        if (skillTriggers.length != 0) {
            for (SkillTrigger skillTrigger : skillTriggers) {
                if (skillTrigger == null) {
                    throw new IllegalArgumentException("New skill triggers"
                        + " value includes a null element");
                }
            }
        }
        skillTriggers = HelperFunctions.copyOf(skillTriggers);
        this.skillTriggers = skillTriggers;
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
        if (skillTriggersList.getSkillTriggers().length == skillTriggers.length
            && skillTriggers.length == 0) {
            return true;
        }
        if (skillTriggersList.getSkillTriggers().length == 
            skillTriggers.length) {
            for (int i = 0; i < skillTriggers.length; i++) {
                if (! skillTriggers[i].equals(
                    skillTriggersList.getSkillTriggers()[i])) {
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

        if (skillTriggers.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        for (int i = 0; i < skillTriggers.length; i += 2) {
            outputString += "  ";
            for (int j = i; j < Math.min(i + 2, skillTriggers.length); j++) {
                if (j != i) {
                    outputString += " ";
                }
                outputString += skillTriggers[j].getName() + " (+"
                    + skillTriggers[j].getLevel() + ")";
                if (j + 1 < skillTriggers.length) {
                    outputString += ",";
                }
            }
            outputString += "\n";
        }

        return outputString;
    }
    /**
     * Returns a deepest copy of this object.
     * @return a SkillTriggersList deepest copy of this object.
     */
    public SkillTriggersList copyOf() {
        // don't need to make copies of these because the mutators already do so
        SkillTriggersList copy = new SkillTriggersList(this.skillTriggers);

        return copy;
    }
}