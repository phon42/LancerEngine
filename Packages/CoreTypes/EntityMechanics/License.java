package Packages.CoreTypes.EntityMechanics;

import MainBranch.Database;
import MainBranch.HelperMethods;

/**
 * See pg. 31.
 */
/**
 * Represents a single license. Contains the ID of the license to which the
 *     license is held, and the level of the license.
 * 
 * Requires a license ID and a license level to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class License {
    /**
     * The ID of the frame to which the license is held (i.e.
     *     "mf_standard_pattern_i_everest").
     * Case-insensitive and stored in lowercase. Must be a valid license ID as
     *     defined by Database.licenseList. Cannot be null.
     */
    private String id;
    /**
     * The license's level.
     * Must be between 1 and 3 (inclusive).
     */
    private int level;

    /**
     * Creates a new License.
     * @param licenseID a String containing the license ID for the new License.
     * @param licenseLevel an int containing the license level for the new
     *     License.
     */
    public License(String licenseID, int licenseLevel) {
        setID(licenseID);
        setLevel(licenseLevel);
    }
    /**
     * Creates a deep copy of the provided License.
     * @param license a License to be copied.
     * @return a License deep copy of the provided License.
     */
    public License(License license) {
        this(license.id, license.level);
    }

    public String getID() {
        return id;
    }
    public int getLevel() {
        return level;
    }
    private void setID(String id) {
        HelperMethods.checkString("New license ID", id);
        id = id.toLowerCase();
        if (! id.equals("integrated weapon")
            && ! id.equals("gms")
            && ! Database.isValidLicense(id)) {
            throw new IllegalArgumentException("New license ID value: " + id
                + " is an invalid license ID");
        }
        this.id = id;
    }
    /**
     * Sets this.level to the value provided.
     * @param level an int which must be between 1 and 3 (inclusive).
     * @throws IllegalArgumentException if level is not between 1 and 3
     *     (inclusive).
     */
    private void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("New license level: " + level
                + " is < 1");
        }
        if (level > 3) {
            throw new IllegalArgumentException("New license level: " + level
                + " is > 3");
        }
        this.level = level;
    }

    /**
     * Generates a String representation of this License.
     * @return a String containing a representation of this License.
     */
    @Override
    public String toString() {
        // Generate something of the form "IPS-N Blackbeard 1"
        String manufacturer = Database.getManufacturerOfFrame(this.id);

        manufacturer = manufacturer.toUpperCase();

        return manufacturer + " " + outputID() + " " + getLevel();
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
        if (! license.getID().equals(this.id)) {
            return false;
        }
        if (license.getLevel() != this.level) {
            return false;
        }
        
        return true;
    }
}