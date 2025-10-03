/**
 * Contains a single system for a mech. Stores information about that system
 *     such as its name and its tags.
 * Safety: This class has placeholder values but cannot be a placeholder. None
 *     of its properties have allowed values of null.
 */
public class MechSystem {
    // TODO: fill out
    /**
     * The mech system's name (i.e. "armament redundancy").
     * Can be any String except "". Cannot be null. Is set to "" on
     *     construction.
     */
    private String name;

    /**
     * Whether the mech system has the "Limited" tag or not.
     * Controlled automatically by MechSystem.setLimitedCharges().
     */
    private boolean limited;
    /**
     * The number of charges the mech system has, if it has the "Limited" tag.
     *     If not, set to 0.
     * Must be > 0.
     */
    private int limitedCharges;

    public MechSystem() {
        this.name = "";
        setLimitedCharges(0);
    }
    public MechSystem(String name) {
        this();
        setName(name);
    }
    public MechSystem(String name, int limitedCharges) {
        setName(name);
        setLimitedCharges(limitedCharges);
    }
    
    public String getName() {
        return name;
    }
    public boolean isLimited() {
        return limited;
    }
    public int getLimitedCharges() {
        return limitedCharges;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New value for name is"
                + " null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New name is \"\"");
        }
        this.name = name;
    }
    /**
     * Sets this.limitedCharges to the value provided and automatically sets
     *     this.limited accordingly.
     * Passing 0 will automatically set this.limited to
     *     false; anything else will set it to true.
     * @param limitedCharges an int that cannot be < 0.
     */
    public void setLimitedCharges(int limitedCharges) {
        if (limitedCharges < 0) {
            throw new IllegalArgumentException("New limited charges value is"
                + " < 0");
        }
        if (limitedCharges == 0) {
            this.limited = false;
        } else {
            this.limited = true;
        }
        this.limitedCharges = limitedCharges;
    }

    /**
     * Checks whether this object has all of its properties set to placeholder
     *     values.
     * @return a boolean representing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! getName().equals("")) {
            return false;
        }
        if (isLimited()) {
            return false;
        }
        if (getLimitedCharges() != 0) {
            return false;
        }

        return true;
    }
    /**
     * Returns a deep copy of this object.
     * @return a MechSystem deep copy of this object.
     */
    public MechSystem copyOf() {
        MechSystem copy = new MechSystem(this.name, this.limitedCharges);

        return copy;
    }
    /**
     * Outputs a printout of this specific mech system.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the printed out information of the system.
     */
    public String outputSystem(String outputType) {
        String outputString = "";

        if (outputType.equals("mech build")
            || outputType.equals("full")) {
            outputString += name;
        }

        return outputString;
    }
}