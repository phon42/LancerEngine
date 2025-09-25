/**
 * Stores a set of the skill triggers a pilot has.
 */
public class SkillTriggersList {
    private SkillTrigger[] skillTriggers;

    public SkillTrigger[] getSkillTriggers() {
        return skillTriggers;
    }
    public void setSkillTriggers(SkillTrigger[] skillTriggers) {
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers value was null");
        }
        if (skillTriggers.length != 0) {
            for (SkillTrigger skillTrigger : skillTriggers) {
                if (skillTrigger == null) {
                    throw new IllegalArgumentException("New skill triggers value included a null element");
                }
            }
        }
        this.skillTriggers = skillTriggers;
    }

    public SkillTriggersList() {
        setSkillTriggers(new SkillTrigger[0]);
    }

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
    public boolean equals(SkillTriggersList skillTriggersList) {
        if (skillTriggersList == null) {
            return false;
        }
        if (skillTriggersList.getSkillTriggers().length == skillTriggers.length
            && skillTriggers.length == 0) {
            return true;
        }
        if (skillTriggersList.getSkillTriggers().length == skillTriggers.length) {
            for (int i = 0; i < skillTriggers.length; i++) {
                if (! skillTriggers[i].equals(skillTriggersList.getSkillTriggers()[i])) {
                    return false;
                }
            }
            return true;
        }
        
        return false;
    }
    public String generateOutput() {
        String outputString = "";

        if (skillTriggers.length == 0) {
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
            if (i + 2 < skillTriggers.length) {
                outputString += "\n";
            }
        }

        return outputString;
    }
    public boolean isValid() {
        if (skillTriggers == null) {
            return false;
        }
        if (skillTriggers.length == 0) {
            return true;
        }
        for (SkillTrigger skillTrigger : skillTriggers) {
            if (skillTrigger.getName().equals("")) {
                return false;
            }
            if (skillTrigger.getValue() == -1) {
                return false;
            }
        }

        return true;
    }
}
