/**
 * Represents a single skill trigger. Stores the skill trigger's name and the
 *     level at which it is held.
 */
public class SkillTrigger {
    /**
     * Can be any String except "". Cannot be null.
     */
    private String name;
    /**
     * Must be between 2 and 6 (inclusive). Cannot be 3 or 5.
     */
    private int value;

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
    /**
     * Sets this.name to the provided value.
     * @param name a String which cannot be null or "".
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name value is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New name value is \"\"");
        }
        this.name = name;
    }
    /**
     * Sets this.value to the provided value.
     * @param value an int which must be 2, 4, or 6.
     */
    public void setValue(int value) {
        if (value < 2) {
            throw new IllegalArgumentException("New value is < 2");
        }
        if (value > 6) {
            throw new IllegalArgumentException("New value is > 6");
        }
        if (value == 3 || value == 5) {
            throw new IllegalArgumentException("New value is an invalid"
                + " value");
        }
        this.value = value;
    }

    /**
     * Compares this SkillTrigger object and obj. If they are the same class,
     *     returns true.
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
     * Compares this SkillTrigger object and skillTrigger. If they have the same
     *     property values, returns true.
     * @param skillTrigger a SkillTrigger to be compared to.
     * @return a boolean representing whether the two SkillTriggers are the
     *     same.
     */
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