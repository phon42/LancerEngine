package packages.coreTypes;

import main.Database;
import main.HelperMethods;

/**
 * See pg. 31.
 */
/**
 * Represents a single license. Contains the name of the license to which the
 *     license is held, and the level of the license.
 * 
 * Requires a license name and a license level to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class License {
    /**
     * The name of the license to which the license is held (i.e. "everest").
     * Case-insensitive and stored in lowercase. Must be a valid license name as
     *     defined by Database.licenseList. Cannot be null.
     * Use License.getName() to get the raw value and License.outputName() to
     *     obtain it properly formatted.
     */
    private String name;
    /**
     * The license's level.
     * Must be between 1 and 3 (inclusive).
     */
    private int level;

    /**
     * Creates a new License.
     * @param licenseName a String containing the license name for the new
     *     License.
     * @param licenseLevel an int containing the license level for the new
     *     License.
     */
    public License(String licenseName, int licenseLevel) {
        setName(licenseName);
        setLevel(licenseLevel);
    }
    /**
     * Creates a deep copy of the provided License.
     * @param license a License to be copied.
     * @return a License deep copy of the provided License.
     */
    public License(License license) {
        this(license.name, license.level);
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    private void setName(String name) {
        HelperMethods.checkString("New license name", name);
        name = name.toLowerCase();
        if (! name.equals("integrated weapon")
            && ! name.equals("gms")
            && ! Database.containsLicense(name)) {
            throw new IllegalArgumentException("New license name value: " + name
                + " is an invalid license name");
        }
        this.name = name;
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
        if (! license.getName().equals(this.name)) {
            return false;
        }
        if (license.getLevel() != this.level) {
            return false;
        }
        
        return true;
    }
    /**
     * A helper method for License.outputLicense(). Returns this.name, properly
     *     formatted. "swallowtail (ranger variant)" will become
     *     "Swallowtail (Ranger Variant)", and "death's head" will
     *     become "Death's Head".
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        return HelperMethods.toProperCase(this.name);
    }
    /**
     * Generates a String output of this License.
     * @return a String containing an output of this License.
     */
    public String outputLicense() {
        // Generate something of the form "IPS-N Blackbeard 1"
        String manufacturer = Database.getManufacturer(this.name);

        manufacturer = manufacturer.toUpperCase();

        return manufacturer + " " + outputName() + " " + getLevel();
    }
}