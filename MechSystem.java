/**
 * Contains a single system for a mech. Stores information about
 *     that system such as its name and its tags.
 */
public class MechSystem {
    // TODO: fill out
    private String systemName;

    private boolean limited;
    private int limitedCharges;

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
        this.systemName = systemName;
    }
    public void setLimited(boolean limited) {
        this.limited = limited;
    }
    public void setLimitedCharges(int limitedCharges) {
        this.limitedCharges = limitedCharges;
    }

    /**
     * Outputs a printout of this specific mech system.
     * @param @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the printed out information of the system.
     */
    public String outputSystem(String outputType) {
        String outputString = "";

        if (outputType == "mech build" || outputType == "full") {
            outputString += systemName;
        }

        return outputString;
    }
}
