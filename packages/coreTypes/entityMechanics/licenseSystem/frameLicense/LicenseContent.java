package packages.coreTypes.entityMechanics.licenseSystem.frameLicense;

import main.Database;
import main.HelperMethods;
import packages.coreTypes.entityMechanics.License;

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
     * The license content's name (i.e. "everest" or "Armament Redundancy" or
     *     "Anti-Materiel Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * The origin license for this license content (i.e. a License representing
     *     "Blackbeard, rank II"). Uses a License to represent an ACTUAL license
     *     instead of the frame name and rank to which a pilot holds a license.
     * For GMS license content, is set to null.
     * Can be any License.
     */
    protected License license;
    /**
     * The manufacturer providing this license content (i.e. "GMS").
     * Must be a valid manufacturer as defined by Database.manufacturerList.
     *     Cannot be null.
     * Case-insensitive and stored in uppercase.
     */
    protected String manufacturer;

    public String getName() {
        return name;
    }
    public License getLicense() {
        return license;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    /**
     * Sets this.license to the provided value.
     * @param license a License which can be any License. Cannot be null.
     */
    protected void setLicense(License license) {
        if (license == null) {
            throw new IllegalArgumentException("New license is null");
        }
        this.license = license;
    }
    /**
     * Sets this.manufacturer to the provided value.
     * @param manufacturer a String which must be a valid manufacturer as
     *     defined by Database.manufacturerList. Cannot be null.
     * @throws IllegalArgumentException if manufacturer is an invalid
     *     manufacturer.
     */
    protected void setManufacturer(String manufacturer) {
        manufacturer = manufacturer.toUpperCase();
        if (! Database.isValidManufacturer(manufacturer)) {
            throw new IllegalArgumentException("New manufacturer value: "
                + manufacturer + " is an invalid manufacturer");
        }
        this.manufacturer = manufacturer;
    }
}
