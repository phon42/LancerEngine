/**
 * Stores a set of the skill triggers a pilot has.
 */
public class SkillTriggersList {
  private String[][] skillTriggers;

  public String[][] getSkillTriggers() {
    return skillTriggers;
  }
  public void setSkillTriggers(String[][] newSkillTriggers) {
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
        outputString += skillTriggers[ii][0] + " (+" + skillTriggers[ii][1] + ")";
        if (ii + 1 < skillTriggers.length) {
          outputString += ",";
        }
      }
      outputString += "\n";
    }

    return outputString;
  }
}