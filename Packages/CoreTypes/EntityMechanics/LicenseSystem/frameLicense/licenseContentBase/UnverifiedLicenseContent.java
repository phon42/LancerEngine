package Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.licenseContentBase;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.LicenseContentBase;

/**
 * Represents anything that can be put into a FrameLicense, such as a frame,
 *     mech system, mech weapon, or weapon modification. Contains nothing by
 *     default.
 * 
 * Requires a content ID, name, manufacturer, license ID, license level, and
 *     description to be instantialized.
 * 
 * Unused but extended by many classes.
 * 
 * At least one of this class' properties has an allowed value of null.
 */
public class UnverifiedLicenseContent extends LicenseContentBase
    implements UnverifiedData<UnverifiedLicenseContent, LicenseContent> {
    // Required property
    /**
     * The manufacturer providing this license content (i.e. "GMS").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String manufacturer;

    protected UnverifiedLicenseContent(String id, String name,
        String manufacturer, String licenseID, String license, int licenseLevel,
        String description) {
        super(id, name, licenseID, license, licenseLevel, description);
        setManufacturer(manufacturer);
        if (license.equals("")) {
            if (this.manufacturer.equals("GMS")) {
                setLicense(this.manufacturer);
            } else {
                setLicense(this.name);
            }
        }
    }
    /**
     * An abbreviated constructor for GMS content.
     */
    protected UnverifiedLicenseContent(String id, String name, String licenseID,
        String license, String description) {
        this(id, name, "GMS", licenseID, license, 0,
            description);
    }

    // Required property
    public String getManufacturer() {
        return manufacturer;
    }
    // Required property
    protected void setManufacturer(String manufacturer) {
        HelperMethods.checkString("manufacturer", manufacturer);
        this.manufacturer = manufacturer;
    }
    @Override
    protected void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < 0) {
            throw new IllegalArgumentException("licenseLevel value: "
                + licenseLevel + " is < 0");
        }
        if (licenseLevel > 3) {
            throw new IllegalArgumentException("licenseLevel value: "
                + licenseLevel + " is < 3");
        }
        if (! this.manufacturer.equals("GMS")) {
            if (licenseLevel == 0) {
                throw new IllegalArgumentException("licenseLevel value is"
                    + " 0");
            }
        }
        this.licenseLevel = licenseLevel;
    }

    @Override
    public Class<UnverifiedLicenseContent> getUnverifiedType() {
        return UnverifiedLicenseContent.class;
    }
    @Override
    public Class<LicenseContent> getVerifiedType() {
        return LicenseContent.class;
    }
    @Override
    public LicenseContent verify() {
        Manufacturer manufacturer;

        manufacturer = Database.getManufacturer(this.manufacturer);

        return new LicenseContent(id, name, manufacturer, licenseID, license,
            licenseLevel, description);
    }
}
