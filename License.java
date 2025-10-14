/**
 * Represents a single license. Contains the name of the frame to which the
 *     license is held, and the level of the license.
 * 
 * Requires a frame name and a license level to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties can be null.
 */
public final class License {
    /**
     * The name of the frame to which the license is held (i.e. "gms everest").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null.
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
     * @param frameName a String containing the frameName for the new License.
     * @param licenseLevel an int containing the licenseLevel for the new
     *     License.
     */
    public License(String frameName, int licenseLevel) {
        setName(frameName);
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
        if (name == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        name = name.toLowerCase();
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
     *     formatted. "ssc swallowtail (ranger variant)" will become
     *     "SSC Swallowtail (Ranger Variant)", and "ssc death's head" will
     *     become "SSC Death's Head".
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        String formattedName = name;
        String[] stringArr;

        // stringArr will become something like
        //     {"ssc", "swallowtail (ranger variant)"}
        stringArr = formattedName.split(" ", 2);
        // {"SSC", "swallowtail (ranger variant)"}
        stringArr[0] = stringArr[0].toUpperCase();
        // {"SSC", "Swallowtail (Ranger Variant)"}
        stringArr[1] = HelperMethods.toProperCase(stringArr[1]);
        formattedName = String.join(" ", stringArr);

        return formattedName;
    }
    /**
     * Generates a String output of this License.
     * @return a String containing an output of this License.
     */
    public String outputLicense() {
        // Generate something of the form "IPS-N Blackbeard 1"
        return outputName() + " " + getLevel();
    }
}