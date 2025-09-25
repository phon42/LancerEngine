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
    public void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < -1) {
            throw new IllegalArgumentException("New value for license level "
                + "was < -1");
        }
        if (licenseLevel == 0) {
            throw new IllegalArgumentException("New value for license level "
                + "was 0");
        }
        if (licenseLevel > 3) {
            throw new IllegalArgumentException("New value for license level "
                + "was > 3");
        }
        this.licenseLevel = licenseLevel;
    }
    public void setFrame(String frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New value for frame was null");
        }
        this.frame = frame;
    }
}
