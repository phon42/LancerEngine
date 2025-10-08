public class EquipmentTag {
    /**
     * Stores the name for this equipment tag (i.e. "AI").
     * Cannot be null or an invalid value (as defined by
     *     EquipmentTag.allowedNames).
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
     * Contains an array of allowed values for this.name for a MechSystem
     *     specifically.
     */
    protected static final String[] allowedSystemNames = new String[] {"AI",
        "Unique", "Limited X", "Grenade", "Mine", "Quick Action", "Deployable",
        "Drone", "Shield", "Heat X (Self)", "Protocol", "Overshield",
        "Full Action", "Quick Tech", "Invade", "Reaction", "Full Tech",
        "X/Round", "Danger Zone", "X/Turn"};
    /**
     * Contains an array of allowed values for this.name for a Weapon
     *     specifically.
     */
    protected static final String[] allowedWeaponNames = new String[]
        {"Accurate", "Armor-Piercing (AP)", "Loading", "Ordnance", "Reliable X",
        "Inaccurate", "Arcing", "Smart", "Overkill", "Thrown X",
        "Heat X (Self)", "Knockback X", "Limited X", "Seeking", "Unique",
        "Reaction", "Drone", "Protocol", "Full Action", "Deployable",
        "Quick Action", "X/Round", "Danger Zone", "AI"};
    /**
     * Array of values for this.name for which having a value other than 0 for
     *     this.value makes sense
     */
    private static final String[] valueNames = new String[] {"Limited X",
        "Heat X (Self)", "X/Round", "X/Turn", "Reliable X", "Thrown X",
        "Knockback X"};
    /**
     * Stores the value for this equipment tag if it has one (i.e. the "X" in
     *     "Limited X").
     */
    private int value;

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
    public void setName(String name) {
        boolean isAllowed = false;

        if (name == null) {
            throw new IllegalArgumentException("New name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New name is \"\"");
        }
        for (String allowedName : allowedNames) {
            if (name.equals(allowedName)) {
                isAllowed = true;
            }
        }
        if (! isAllowed) {
            // TODO: add allowedNames as well as for all the other classes
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
                throw new IllegalArgumentException("Name is + \""
                    + this.name + "\" and new value is < 1");
            }
            this.value = value;
        } else {
            throw new IllegalArgumentException("Attempted to call"
                + " EquipmentTag.setValue() when name is \"" + this.name
                + "\"");
        }
    }

    public EquipmentTag copyOf() {
        EquipmentTag copy = new EquipmentTag(this.name, this.value);

        return copy;
    }
}
