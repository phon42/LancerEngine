/**
 * Represents a single license. Stores the frame to which the license is held,
 *     and the level of the license.
 */
public class License {
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String frame;
    /**
     * Must be between -1 (placeholder) and 3 (inclusive). Cannot be 0.
     */
    private int licenseLevel;

    public License() {
        setFrame("");
        setLicenseLevel(-1);
    }
    public License(String frame, int licenseLevel) {
        setFrame(frame);
        setLicenseLevel(licenseLevel);
    }

    public String getFrame() {
        return frame;
    }
    public int getLicenseLevel() {
        return licenseLevel;
    }
    /**
     * Sets this.frame to the value provided.
     * @param frame a String which cannot be null.
     */
    public void setFrame(String frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New value for frame is"
                + " null");
        }
        this.frame = frame;
    }
    /**
     * Sets this.licenseLevel to the value provided.
     * @param licenseLevel an int which must be between -1 and 3 (inclusive)
     *     and cannot be null.
     */
    public void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < -1) {
            throw new IllegalArgumentException("New value for license level "
                + "is < -1");
        }
        if (licenseLevel == 0) {
            throw new IllegalArgumentException("New value for license level "
                + "is 0");
        }
        if (licenseLevel > 3) {
            throw new IllegalArgumentException("New value for license level "
                + "is > 3");
        }
        this.licenseLevel = licenseLevel;
    }

    /**
     * Compares this License object and obj. If they are the same class, returns
     *     true.
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
     * Compares this License object and license. If they have the same property
     *     values, returns true.
     * @param license a License to be compared to.
     * @return a boolean representing whether the two Licenses are the same.
     */
    public boolean equals(License license) {
        if (license == null) {
            return false;
        }
        if (! license.getFrame().equals(getFrame())) {
            return false;
        }
        if (license.getLicenseLevel() != getLicenseLevel()) {
            return false;
        }
        
        return true;
    }
    /**
     * Checks whether this License object has any properties set to placeholder
     *     values.
     * @return a boolean representing whether this License object has any
     *     properties set to placeholder values.
     */
    public boolean hasPlaceholders() {
        if (getFrame().equals("")) {
            return true;
        }
        if (getLicenseLevel() == -1) {
            return true;
        }

        return false;
    }
}
