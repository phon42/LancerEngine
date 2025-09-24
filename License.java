/**
 * Represents a single license. Stores information about the license,
 *     such as the frame to which the license is held, and the level
 *     of the license.
 */
public class License {
    public String frame;
    public int licenseLevel;

    public License() {
        frame = "";
        licenseLevel = 0;
    }
    public License(String newFrame, int newLicenseLevel) {
        frame = newFrame;
        licenseLevel = newLicenseLevel;
    }
}
