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
    /**
     * Sets this.name to the provided value.
     * @param name a String which cannot be null.
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name value is null");
        }
        this.name = name;
    }
    /**
     * Sets this.value to the provided value.
     * @param value an int which must be -1, 2, 4, or 6.
     */
    public void setValue(int value) {
        if (value < -1) {
            throw new IllegalArgumentException("New value is < -1");
        }
        if (value > 6) {
            throw new IllegalArgumentException("New value is > 6");
        }
        if (value == 0 || value == 1 || value == 3 || value == 5) {
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
    /**
     * Tests whether this SkillTrigger object's properties have any placeholder
     *     values.
     * @return a boolean representing whether this SkillTrigger object has any
     *     placeholder values.
     */
    public boolean hasPlaceholders() {
        if (getName().equals("")) {
            return true;
        }
        if (getValue() == -1) {
            return true;
        }

        return false;
    }
}