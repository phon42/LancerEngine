package packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.License;
import packages.CoreTypes.EntityMechanics.Manufacturer;

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
    // TODO: fill out
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
     * The origin license for this license content (i.e. a License representing
     *     "Blackbeard, rank II"). Uses a License to represent an ACTUAL license
     *     instead of the frame name and rank to which a pilot holds a license.
     * For GMS license content, is set to GMS 0.
     * Can be any License. Cannot be null.
     */
    protected License originLicense;
    /**
     * The name of the license that this license content originates from (i.e.
     *     "Blackbeard").
     * For GMS content, is set to "GMS".
     * Can be any String except "". Cannot be null.
     */
    protected String license;
    /**
     * The ID of the license that this license content originates from (i.e.
     *     "mf_blackbeard").
     * For GMS content, is set to null.
     * Can be any String except "". Can be null.
     */
    protected String licenseID;
    /**
     * The license level for this license content (i.e. 1).
     * For GMS license content, is set to 0. For non-GMS license content, must
     *     be a minimum of 1.
     * Must be a minimum of 0.
     */
    protected int licenseLevel;
    /**
     * The description for this license content (too large to provide an
     *     example).
     * Can be any String except "". Cannot be null.
     */
    protected String description;

    protected LicenseContent(String id, String name, Manufacturer source,
        License originLicense, String license, int licenseLevel,
        String licenseID, String description) {
        setID(id);
        setName(name);
        setSource(source);
        if (source.getName().equals("GMS")) {
            setOriginLicense(new License(null, 0));
            setLicense("GMS");
            setLicenseLevel(0);
            setLicenseID(null);
        } else {
            setOriginLicense(originLicense);
            setLicense(license);
            setLicenseLevel(licenseLevel);
            setLicenseID(licenseID);
        }
        setDescription(description);
    }
    protected LicenseContent(String id, String name, Manufacturer source,
        String description) {
        this(id, name, source, null, null,
            0, null, null);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Manufacturer getSource() {
        return new Manufacturer(source);
    }
    /**
     * Can be "GMS"
     * Case-sensitive.
     */
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
    public String getDescription() {
        return description;
    }
    protected void setID(String id) {
        this.id = id;
    }
    protected void setName(String name) {
        this.name = name;
    }
    protected void setSource(Manufacturer source) {
        this.source = source;
    }
    protected void setLicense(String license) {
        this.license = license;
    }
    protected void setLicenseLevel(int licenseLevel) {
        this.licenseLevel = licenseLevel;
    }
    protected void setLicenseID(String licenseID) {
        if (this.source.getID().equals("GMS")) {
            // licenseID must be null
            if (licenseID != null) {
                throw new IllegalArgumentException("this.source is GMS and"
                    + " licenseID value is: \"" + licenseID + "\" which is not"
                    + " the null value it's required to be");
            }
        } else {
            // licenseID cannot be null or ""
            HelperMethods.checkString("licenseID", licenseID);
        }
        this.licenseID = licenseID;
    }
    protected void setOriginLicense(License originLicense) {
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
    public void setDescription(String description) {
        this.description = description;
    }
}
