package Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.Manufacturer;

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
    /**
     * The license content's ID (i.e. "mf_standard_pattern_i_everest" or
     *     "ms_comp_con_class_assistant_unit" or "mw_anti_materiel_rifle").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;
    /**
     * The license content's name (i.e. "Everest" or "COMP/CON-Class Assistant"
     *     " Unit" or "Anti-Materiel Rifle").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;
    /**
     * The manufacturer providing this license content (i.e. a Manufacturer that
     *     represents GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    protected Manufacturer manufacturer;
    /**
     * The origin license for this license content (i.e. a License representing
     *     'Everest, rank 0' or 'Blackbeard, rank I'). Uses a License to
     *     represent an ACTUAL license instead of the frame name and rank to
     *     which a pilot holds a license.
     * LicenseContent.originLicense.id property is always determined by
     *     this.licenseID.
     * LicenseContent.originLicense.level property is always determined by
     *     this.licenseLevel.
     * For GMS license content, is set to "<the frame ID>" 0.
     * Can be any License. Cannot be null.
     */
    protected License originLicense;
    /**
     * The ID of the license that this license content originates from (i.e.
     *     "mf_standard_pattern_i_everest" or "mf_blackbeard").
     * For GMS license content, is set to "mf_standard_pattern_i_everest".
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String licenseID;
    /**
     * The name of the license that this license content originates from (i.e.
     *     "Blackbeard").
     * For GMS content, is set to "GMS".
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String license;
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
     * For frames specifically, serves as the frame description (the text at the
     *     top of the page).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String description;

    protected LicenseContent(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description)
    {
        setID(id);
        setName(name);
        setManufacturer(manufacturer);
        if (licenseID == null) {
            // hopefully something like the Chomolungma where only the id, name,
            //     manufacturer, license level, and description are present?
            HelperMethods.warn("licenseID was not provided for this Frame."
                + " Attempting to extrapolate from available data");
            if (id.substring(0, 3).equals("mf_"))
            {
                // it is in fact a Chomolungma-type situation
                licenseID = id;
                if (license == null) {
                    license = "";
                }
            } else {
                throw new IllegalArgumentException("licenseID is null");
            }
        }
        if (licenseID.equals("")) {
            setLicenseID("mf_standard_pattern_i_everest");
        } else {
            setLicenseID(licenseID);
        }
        HelperMethods.checkObject("license", license);
        if (license.equals("")) {
            if (this.manufacturer.getID().equals("GMS")) {
                setLicense(this.manufacturer.getID());
            } else {
                setLicense(this.name);
            }
        }
        setLicenseLevel(licenseLevel);
        setOriginLicense(new License(this.licenseID, licenseLevel));
        setDescription(description);
    }
    /**
     * An abbreviated constructor for GMS content.
     */
    protected LicenseContent(String id, String name, String licenseID,
        String license, String description) {
        this(id, name, Database.getManufacturer("GMS"),
            licenseID, license, 0, description);
    }
    protected LicenseContent(LicenseContent licenseContent) {
        setID(licenseContent.id);
        setName(licenseContent.name);
        setManufacturer(licenseContent.manufacturer);
        setOriginLicense(licenseContent.originLicense);
        setLicense(licenseContent.license);
        setLicenseLevel(licenseContent.licenseLevel);
        setDescription(licenseContent.description);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Manufacturer getManufacturer() {
        return new Manufacturer(manufacturer);
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
        return new License(originLicense);
    }
    public String getDescription() {
        return description;
    }
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    protected void setManufacturer(Manufacturer manufacturer) {
        HelperMethods.checkObject("manufacturer", manufacturer);
        manufacturer = new Manufacturer(manufacturer);
        this.manufacturer = manufacturer;
    }
    protected void setLicense(String license) {
        HelperMethods.checkString("license", license);
        this.license = license;
    }
    protected void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < 0) {
            throw new IllegalArgumentException("licenseLevel value: "
                + licenseLevel + " is < 0");
        }
        if (licenseLevel > 3) {
            throw new IllegalArgumentException("licenseLevel value: "
                + licenseLevel + " is < 3");
        }
        if (! this.manufacturer.getID().equals("GMS")) {
            if (licenseLevel == 0) {
                throw new IllegalArgumentException("licenseLevel value is"
                    + " 0");
            }
        }
        this.licenseLevel = licenseLevel;
    }
    protected void setLicenseID(String licenseID) {
        HelperMethods.checkString("licenseID", licenseID);
        licenseID = licenseID.toLowerCase();
        this.licenseID = licenseID;
    }
    protected void setOriginLicense(License originLicense) {
        HelperMethods.checkObject("originLicense", originLicense);
        originLicense = new License(originLicense);
        this.originLicense = originLicense;
    }
    public void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
}
