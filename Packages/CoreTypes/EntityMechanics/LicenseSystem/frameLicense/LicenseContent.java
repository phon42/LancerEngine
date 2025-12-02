package Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense;

import MainBranch.Database;
import MainBranch.HelperMethods;
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
public class LicenseContent extends LicenseContentBase {
    // Required property
    /**
     * The manufacturer providing this license content (i.e. a Manufacturer that
     *     represents GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    protected Manufacturer manufacturer;

    protected LicenseContent(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description)
    {
        super(id, name, licenseID, license, licenseLevel, description);
        setManufacturer(manufacturer);
        if (license.equals("")) {
            if (this.manufacturer.getID().equals("GMS")) {
                setLicense(this.manufacturer.getID());
            } else {
                setLicense(this.name);
            }
        }
    }
    /**
     * An abbreviated constructor for GMS content.
     */
    protected LicenseContent(String id, String name, String licenseID,
        String license, String description) {
        this(id, name, Database.getManufacturer("GMS"),
            licenseID, license, 0, description);
    }

    // Required property
    public Manufacturer getManufacturer() {
        return new Manufacturer(manufacturer);
    }
    // Required property
    protected void setManufacturer(Manufacturer manufacturer) {
        HelperMethods.checkObject("manufacturer", manufacturer);
        manufacturer = new Manufacturer(manufacturer);
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
        if (! this.manufacturer.getID().equals("GMS")) {
            if (licenseLevel == 0) {
                throw new IllegalArgumentException("licenseLevel value is"
                    + " 0");
            }
        }
        this.licenseLevel = licenseLevel;
    }
}
