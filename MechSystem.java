/**
 * Contains a single system for a mech. Stores information about that system
 *     such as its name and its tags.
 * Safety: This class has placeholder values but cannot be a placeholder. None
 *     of its properties have allowed values of null.
 */
public class MechSystem extends Equipment {
    // TODO: fill out

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