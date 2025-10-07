public class Equipment {
    /**
     * The equipment's name (i.e. "Armament Redundancy" or "Anti-Materiel
     *     Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * Whether the equipment has the "Limited" tag or not.
     * Controlled automatically by MechSystem.setLimitedCharges().
     */
    protected boolean limited;
    /**
     * The number of charges the equipment has, if it has the "Limited" tag.
     *     If not, set to 0.
     * Must be > 0.
     */
    protected int limitedCharges;
    
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
    // setLimited removed purposefully
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
}