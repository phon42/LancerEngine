/**
 * Contains a single system for a mech. Stores information about that system
 *     such as its name and its tags.
 */
public class MechSystem {
    // TODO: fill out
    /**
     * The system name.
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String name;

    private boolean limited;
    /**
     * Must be > -1.
     */
    private int limitedCharges;

    public MechSystem() {
        setName("");
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
        this.name = name;
    }
    private void setLimited(boolean limited) {
        this.limited = limited;
    }
    public void setLimitedCharges(int limitedCharges) {
        if (limitedCharges < 0) {
            throw new IllegalArgumentException("New limited charges value is"
                + " < 0");
        }
        if (limitedCharges == 0) {
            setLimited(false);
        } else {
            setLimited(true);
        }
        this.limitedCharges = limitedCharges;
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