/**
 * Represents a single skill trigger. Stores the skill trigger's name
 *     and the level at which it is held.
 */
public class SkillTrigger {
    String name;
    int value;

    public SkillTrigger() {
        name = "";
        value = 0;
    }
    public SkillTrigger(String newName, int newValue) {
        name = newName;
        value = newValue;
    }
}
