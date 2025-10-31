package packages.coreTypes.entityMechanics.licenseSystem.frameLicense;

import packages.coreTypes.entityMechanics.License;
import packages.coreTypes.entityMechanics.Manufacturer;

/**
 * Represents anything that can be put into a FrameLicense, such as a frame,
 *     mech system, mech weapon, or weapon modification. Contains nothing by
 *     default.
 * 
 * Requires nothing to be instantialized.
 * 
 * Unused but extended by many classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class LicenseContent {
    protected String id;
    /**
     * The license content's name (i.e. "everest" or "Armament Redundancy" or
     *     "Anti-Materiel Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * The manufacturer providing this license content (i.e. a Manufacturer that
     *     represents "GMS").
     * Can be any Manufacturer. Cannot be null.
     */
    protected Manufacturer source;
    /**
     * The name of the license that this license content originates from (i.e.
     *     "Nelson").
     * Can be any String. Cannot be null.
     */
    protected String license;
    protected int licenseLevel;
    protected String licenseID;
    /**
     * The origin license for this license content (i.e. a License representing
     *     "Blackbeard, rank II"). Uses a License to represent an ACTUAL license
     *     instead of the frame name and rank to which a pilot holds a license.
     * For GMS license content, is set to null.
     * Can be any License.
     */
    protected License originLicense;
    protected String description;

    protected LicenseContent() {}

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Manufacturer getSource() {
        return source;
    }
    public String getLicense() {
        return license;
    }
    public int getLicenseLevel() {
        return licenseLevel;
    }
    public String getLicenseID() {
        return licenseID;
    }
    public License getOriginLicense() {
        return originLicense;
    }
    public void setID(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSource(Manufacturer source) {
        this.source = source;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public void setLicenseLevel(int licenseLevel) {
        this.licenseLevel = licenseLevel;
    }
    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }
    public void setOriginLicense(License originLicense) {
        if (license == null && ! this.source.getName().equals("GMS")) {
            throw new IllegalArgumentException("New license is null and"
                + " this.source is the Manufacturer: \"" + this.source.getName()
                + "\"");
        }
        if (originLicense != null) {
            originLicense = new License(originLicense);
        }
        this.originLicense = originLicense;
    }
}
