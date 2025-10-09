/**
 * Represents a tag for a mech system or weapon, like the tags "AI" or "Limited
 *     X". Contains information about the tag's name and value (if it has one).
 * 
 * Requires a tag name and tag value to be instantiated.
 * 
 * Used in Equipment, MechSystem, and Weapon.
 * 
 * Safety: This class does not have placeholder values. None of its properties
 *     have allowed values of null.
 */
public class EquipmentTag {
    /**
     * Stores the name for this equipment tag (i.e. "AI").
     * Must be a valid value (as defined by EquipmentTag.allowedNames). Cannot
     *     be null.
     */
    private String name;
    /**
     * Contains an array of allowed values for this.name.
     */
    private static final String[] allowedNames = new String[] {"AI", "Unique",
        "Limited X", "Grenade", "Mine", "Quick Action", "Deployable", "Drone",
        "Shield", "Heat X (Self)", "Protocol", "Overshield", "Full Action",
        "Quick Tech", "Invade", "Reaction", "Full Tech", "X/Round",
        "Danger Zone", "X/Turn", "Accurate", "Armor-Piercing (AP)", "Loading",
        "Ordnance", "Reliable X", "Inaccurate", "Arcing", "Smart", "Overkill",
        "Thrown X", "Knockback X", "Seeking"};
    /**
     * Contains an array of values for this.name for which having a value other
     *     than 0 for this.value makes sense.
     */
    private static final String[] valueNames = new String[] {"Limited X",
        "Heat X (Self)", "X/Round", "X/Turn", "Reliable X", "Thrown X",
        "Knockback X"};
    /**
     * Stores the value for this equipment tag if it has one (i.e. the "X" in
     *     "Limited X").
     * Must be a minimum of 0. If this.name is one of EquipmentTag.valueNames,
     *     must be a minimum of 1. Set to 0 on construction.
     */
    private int value;

    /**
     * Creates a new EquipmentTag for use in EquipmentTag.copyOf() only.
     */
    private EquipmentTag() {
        this.name = "";
        this.value = 0;
    }
    /**
     * Creates a new EquipmentTag given an equipment tag name.
     * @param tagName a String which cannot be null or an invalid value, as
     *     defined by EquipmentTag.allowedNames.
     */
    public EquipmentTag(String tagName) {
        this.value = 0;
        setName(tagName);
    }
    /**
     * Creates a new EquipmentTag for an EquipmentTag like the "Limited X" tag,
     *     given an equipment tag name for which having a value other than 0 for
     *     this.value makes sense, and a tag value.
     * @param tagName a String which cannot be null or an invalid value, as
     *     defined by EquipmentTag.valueNames.
     * @param tagValue an int which must be > 1.
     */
    public EquipmentTag(String tagName, int tagValue) {
        setName(tagName);
        setValue(tagValue);
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    /**
     * Sets this.name to the provided value.
     * @param name a String which must be a valid value (as defined by
     *     EquipmentTag.allowedNames). Cannot be null.
     * @throws IllegalArgumentException if name is null or an invalid value, as
     *     defined by EquipmentTag.allowedNames.
     */
    public void setName(String name) {
        boolean isAllowed = false;

        if (name == null) {
            throw new IllegalArgumentException("New name is null");
        }
        for (String allowedName : EquipmentTag.allowedNames) {
            if (name.equals(allowedName)) {
                isAllowed = true;
            }
        }
        if (! isAllowed) {
            throw new IllegalArgumentException("New name: \"" + name + "\" is"
                + " not an allowed value.");
        }
        this.name = name;
    }
    /**
     * Sets this.value to the provided value. If this.name is a tag for which
     *     having a value makes sense, checks whether value is < 1.
     * @param value an int containing the new value.
     * @throws IllegalArgumentException if this.name is not a tag for which
     *     having a value makes sense, or if it is and value is < 1.
     */
    public void setValue(int value) {
        boolean isValid = false;
        
        for (String valueName : EquipmentTag.valueNames) {
            if (this.name.equals(valueName)) {
                isValid = true;
                break;
            }
        }
        if (isValid) {
            if (value < 1) {
                throw new IllegalArgumentException("Name is: \""
                    + this.name + "\" and new value: " + value + " is < 1");
            }
            this.value = value;
        } else {
            throw new IllegalArgumentException("Attempted to call"
                + " EquipmentTag.setValue() when name is \"" + this.name
                + "\"");
        }
    }

    /**
     * Returns a copy of this EquipmentTag object.
     * @return an EquipmentTag copy of this object.
     */
    public EquipmentTag copyOf() {
        EquipmentTag copy = new EquipmentTag();
        
        copy.setName(this.name);
        copy.value = this.value;

        return copy;
    }
}
