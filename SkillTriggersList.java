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
     * Can be any SkillTrigger[]. Cannot be null or contain null elements.
     */
    private SkillTrigger[] skillTriggers;

    /**
     * Creates an empty SkillTriggersList.
     */
    public SkillTriggersList() {
        setSkillTriggers(new SkillTrigger[0]);
    }
    /**
     * Creates a new SkillTriggersList with the provided list of skill triggers.
     * @param skillTriggers a SkillTrigger[] that cannot be null or contain null
     *     elements.
     */
    public SkillTriggersList(SkillTrigger[] skillTriggers) {
        setSkillTriggers(skillTriggers);
    }

    public SkillTrigger[] getSkillTriggers() {
        return HelperMethods.copyOf(skillTriggers);
    }
    /**
     * Searches for a specified skill trigger. Returns whether the search was
     *     successful.
     * @param skillTriggerName a String containing the name of the skill trigger
     *     to be searched for.
     * @return a boolean containing the result of the search.
     */
    public boolean hasSkillTrigger(String skillTriggerName) {
        for (SkillTrigger skillTrigger : this.skillTriggers) {
            if (skillTrigger.getName().equals(skillTriggerName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * Searches for a specified skill trigger. If the skill trigger is present,
     *     returns it.
     * @param skillTriggerName a String containing the name of the skill trigger
     *     to be searched for.
     * @return a SkillTrigger containing the skill trigger that was searched
     *     for.
     * @throws IllegalArgumentException if the requested skill trigger could not
     *     be found.
     */
    public SkillTrigger getSkillTrigger(String skillTriggerName) {
        for (SkillTrigger skillTrigger : this.skillTriggers) {
            if (skillTrigger.getName().equals(skillTriggerName)) {
                return skillTrigger.copyOf();
            }
        }

        throw new IllegalArgumentException("Requested skill trigger: \""
            + skillTriggerName + "\" could not be found.");
    }
    /**
     * Sets this.skillTriggers to the provided value.
     * @param skillTriggers a SkillTrigger[] which cannot be null or contain
     *     null elements.
     * @throws IllegalArgumentException if skillTriggers is null or contains
     *     null elements.
     */
    public void setSkillTriggers(SkillTrigger[] skillTriggers) {
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers value is"
                + " null");
        }
        for (SkillTrigger skillTrigger : skillTriggers) {
            if (skillTrigger == null) {
                throw new IllegalArgumentException("New skill triggers"
                    + " value includes a null element");
            }
        }
        skillTriggers = HelperMethods.copyOf(skillTriggers);
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
     * Returns a deepest copy of this SkillTriggersList object.
     * @return a SkillTriggersList deepest copy of this object.
     */
    public SkillTriggersList copyOf() {
        // don't need to make copies of these because the mutators already do so
        SkillTriggersList copy = new SkillTriggersList(this.skillTriggers);

        return copy;
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

        if (this.skillTriggers.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        for (int i = 0; i < this.skillTriggers.length; i += 2) {
            outputString += "  ";
            for (int j = i; j < Math.min(i + 2, this.skillTriggers.length);
                j++) {
                if (j != i) {
                    outputString += " ";
                }
                outputString += this.skillTriggers[j].outputSkillTrigger();
                if (j + 1 < this.skillTriggers.length) {
                    outputString += ",";
                }
            }
            outputString += "\n";
        }

        return outputString;
    }
}