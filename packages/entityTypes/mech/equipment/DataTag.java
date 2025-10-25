package packages.entityTypes.mech.equipment;

import main.HelperMethods;

/**
 * See the tags within the files under
 *     https://github.com/massif-press/lancer-data/tree/master/lib.
 */
/**
 * Represents a tag on a JSON file for a mech system or weapon (i.e. the tags
 *     "AI" or "Grenade"). These tags are not always publicly visible. Contains
 *     information about the data tag's name and value (if it has one).
 * 
 * Requires a data tag name to be instantiated.
 * 
 * Used in Database.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class DataTag {
    /**
     * The name for this data tag (i.e. "AI").
     * Must be a valid value (as defined by DataTag.allowedNames). Cannot be
     *     null.
     */
    protected String name;
    /**
     * Contains an array of allowed values for this.name. Case-sensitive.
     */
    private static final String[] allowedNames = new String[] {"AI", "Unique",
        "Limited X", "Grenade", "Mine", "Quick Action", "Deployable", "Drone",
        "Shield", "Heat X (Self)", "Protocol", "Overshield", "Full Action",
        "Quick Tech", "Invade", "Reaction", "Full Tech", "X/Round",
        "Danger Zone", "X/Turn", "Accurate", "Armor-Piercing (AP)", "Loading",
        "Ordnance", "Reliable X", "Inaccurate", "Arcing", "Smart", "Overkill",
        "Thrown X", "Knockback X", "Seeking", "Free Action"};
    /**
     * Contains an array of values for this.name for which having a value other
     *     than 0 for this.value makes sense. Case-sensitive.
     */
    private static final String[] valueNames = new String[] {"Limited X",
        "Heat X (Self)", "X/Round", "X/Turn", "Reliable X", "Thrown X",
        "Knockback X"};
    /**
     * The value for this data tag if it has one (i.e. the "X" in "Limited X").
     * Must be a minimum of 0. If this.name is one of DataTag.valueNames, must
     *     be a minimum of 1. Set to 0 on construction.
     */
    protected int value;

    /**
     * Creates a new DataTag given a data tag name.
     * @param dataTagName a String which cannot be null or an invalid value, as
     *     defined by DataTag.allowedNames.
     */
    public DataTag(String dataTagName) {
        setName(dataTagName);
        this.value = 0;
    }
    /**
     * Creates a copy of the provided DataTag.
     * @param dataTag a DataTag to be copied.
     * @return a DataTag copy of the provided DataTag.
     */
    public DataTag(DataTag dataTag) {
        if (dataTag == null) {
            throw new IllegalArgumentException("dataTag is null");
        }
        setName(dataTag.name);
        setValue(dataTag.value);
    }
    /**
     * Creates a new DataTag such as the "Limited X" data tag, given a data tag
     *     name for which having a value other than 0 for this.value makes
     *     sense, and a data tag value.
     * @param dataTagName a String which cannot be null or an invalid value, as
     *     defined by DataTag.valueNames.
     * @param dataTagValue an int which must be > 1.
     */
    public DataTag(String dataTagName, int dataTagValue) {
        setName(dataTagName);
        setValue(dataTagValue);
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
     *     DataTag.allowedNames). Cannot be null.
     * @throws IllegalArgumentException if name is null or an invalid value, as
     *     defined by DataTag.allowedNames.
     */
    public void setName(String name) {
        boolean isAllowed = false;

        HelperMethods.checkString("New name", name);
        for (String allowedName : DataTag.allowedNames) {
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
     * Sets this.value to the provided value. If this.name is a data tag for
     *     which having a value makes sense, checks whether value is < 1.
     * @param value an int containing the new value.
     * @throws IllegalArgumentException if this.name is not a data tag for which
     *     having a value makes sense, or if it is and value is < 1.
     */
    public void setValue(int value) {
        boolean isValid = false;
        
        for (String valueName : DataTag.valueNames) {
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
                + " DataTag.setValue() when name is \"" + this.name
                + "\"");
        }
    }
}
