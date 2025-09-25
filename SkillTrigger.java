/**
 * Represents a single skill trigger. Stores the skill trigger's name and the
 *     level at which it is held.
 */
public class SkillTrigger {
    /**
     * Can be any String (though "" is a placeholder). Cannot be null.
     */
    private String name;
    /**
     * Must be between -1 and 6 (inclusive). Cannot be 0, 1, 3, or 5.
     */
    private int value;

    public SkillTrigger() {
        setName("");
        setValue(-1);
    }
    public SkillTrigger(String name, int value) {
        setName(name);
        setValue(value);
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name value is null");
        }
        this.name = name;
    }
    public void setValue(int value) {
        if (value < -1) {
            throw new IllegalArgumentException("New value is < -1");
        }
        if (value > 6) {
            throw new IllegalArgumentException("New value is > 6");
        }
        if (value == 0 || value == 1 || value == 3 || value == 5) {
            throw new IllegalArgumentException("New value is an invalid value");
        }
        this.value = value;
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
    public boolean equals(SkillTrigger skillTrigger) {
        if (skillTrigger == null) {
            return false;
        }
        if (! skillTrigger.getName().equals(getName())) {
            return false;
        }
        if (skillTrigger.getValue() != getValue()) {
            return false;
        }
        
        return true;
    }
}
