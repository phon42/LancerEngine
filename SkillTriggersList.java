/**
 * Stores a set of the skill triggers a pilot has.
 */
public class SkillTriggersList {
    private SkillTrigger[] skillTriggers;

    public SkillTrigger[] getSkillTriggers() {
        return skillTriggers;
    }
    public void setSkillTriggers(SkillTrigger[] newSkillTriggers) {
        skillTriggers = newSkillTriggers;
    }

    public String generateOutput() {
        String outputString = "";

        if (skillTriggers == null) {
            return outputString;
        } else if (skillTriggers.length == 0) {
            return outputString;
        }
        for (int i = 0; i < skillTriggers.length; i += 2) {
            outputString += "  ";
            for (int ii = i; ii < Math.min(i + 2, skillTriggers.length); ii++) {
                if (ii != i) {
                    outputString += " ";
                }
                outputString += skillTriggers[ii].name + " (+" + skillTriggers[ii].value + ")";
                if (ii + 1 < skillTriggers.length) {
                    outputString += ",";
                }
            }
            outputString += "\n";
        }

        return outputString;
    }
}
