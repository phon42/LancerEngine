/**
 * Contains a single system for a mech. Stores information about that system
 *     such as its name and its tags.
 */
public class MechSystem {
    // TODO: fill out
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String systemName;

    private boolean limited;
    /**
     * Must be > -1.
     */
    private int limitedCharges;

    public MechSystem() {
        setSystemName("");
        setLimited(false);
        setLimitedCharges(0);
    }
    
    public String getSystemName() {
        return systemName;
    }
    public boolean isLimited() {
        return limited;
    }
    public int getLimitedCharges() {
        return limitedCharges;
    }
    public void setSystemName(String systemName) {
        if (systemName == null) {
            throw new IllegalArgumentException("New value for system name is"
                + " null");
        }
        this.systemName = systemName;
    }
    public void setLimited(boolean limited) {
        this.limited = limited;
    }
    public void setLimitedCharges(int limitedCharges) {
        if (limitedCharges < 0) {
            throw new IllegalArgumentException("New limited charges value is"
                + " < 0");
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
            outputString += systemName;
        }

        return outputString;
    }
}