/**
 * Stores a set of the skill triggers a pilot has.
 */
public class SkillTriggersList {
    private SkillTrigger[] skillTriggers;

    public SkillTrigger[] getSkillTriggers() {
        return skillTriggers;
    }
    /**
     * Sets this.skillTriggers to the provided value.
     * @param skillTriggers a SkillTrigger[] which cannot be null or contain an
     *     element which is null.
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
        this.skillTriggers = skillTriggers;
    }

    public SkillTriggersList() {
        setSkillTriggers(new SkillTrigger[0]);
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
            for (int ii = i; ii < Math.min(i + 2, skillTriggers.length); ii++) {
                if (ii != i) {
                    outputString += " ";
                }
                outputString += skillTriggers[ii].getName() + " (+"
                    + skillTriggers[ii].getValue() + ")";
                if (ii + 1 < skillTriggers.length) {
                    outputString += ",";
                }
            }
            outputString += "\n";
        }

        return outputString;
    }
    /**
     * Tests whether this SkillTriggersList object's properties have any
     *     placeholder values.
     *     The SkillTriggersList doesn't have any placeholder values if the
     *     object's SkillTriggers array does not contain any SkillTriggers with
     *     name and value properties set to placeholder values.
     * @return a boolean representing whether this SkillTriggersList object
     *     has placeholders.
     */
    public boolean hasPlaceholders() {
        if (skillTriggers.length == 0) {
            return false;
        }
        for (SkillTrigger skillTrigger : skillTriggers) {
            if (skillTrigger.hasPlaceholders()) {
                return true;
            }
        }

        return false;
    }
}
