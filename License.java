/**
 * Represents a single license. Stores the frame to which the license is held,
 *     and the level of the license.
 * Requires a frameName and a licenseLevel to be instantiated.
 */
public class License {
    /**
     * The frame name.
     * Can be any String except "". Cannot be null.
     */
    private String name;
    /**
     * The license level.
     * Must be between 1 and 3 (inclusive).
     */
    private int level;

    public License(String frameName, int licenseLevel) {
        setName(frameName);
        setLevel(licenseLevel);
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    /**
     * Sets this.name to the value provided.
     * @param name a String which cannot be null or "".
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        this.name = name;
    }
    /**
     * Sets this.level to the value provided.
     * @param level an int which must be between 1 and 3 (inclusive) and cannot
     *     be null.
     */
    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("New value for license level "
                + "is < 1");
        }
        if (level > 3) {
            throw new IllegalArgumentException("New value for license level "
                + "is > 3");
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
        if (! license.getName().equals(getName())) {
            return false;
        }
        if (license.getLevel() != getLevel()) {
            return false;
        }
        
        return true;
    }
    /**
     * Returns a deep copy of this object.
     * @return a License deep copy of this object.
     */
    public License copyOf() {
        License copy = new License(this.name, this.level);

        return copy;
    }
}