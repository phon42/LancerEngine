/**
 * Represents a piece of mech equipment of any kind, such as a mech system or a
 *     mech weapon. Contains information about the equipment's name, origin, and
 *     tags.
 * 
 * Requires an equipment name to be instantiated.
 * 
 * Used in and extended by MechSystem and Weapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Equipment {
    // TODO: add some way to add actions for equipments that provide them (i.e
    //     GMS Pattern-A Jericho Deployable Cover and its Deploy Jericho
    //     Deployable Cover action for MechSystem, or IPS-N D/D 288 and its
    //     Charge D/D 288 action for Weapon)
    // TODO: add some way for Tags to be automatically added based on an
    //     equipment's contents
    /**
     * The equipment's name (i.e. "Armament Redundancy" or "Anti-Materiel
     *     Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * The manufacturer providing this equipment (i.e. "GMS").
     * Must be a valid manufacturer as defined by Database.manufacturerList.
     *     Cannot be null.
     * Case-insensitive and stored in uppercase.
     */
    protected String manufacturer;
    /**
     * Contains an array of all of this equipment's tags (i.e. Tag elements
     *     representing an "AI" or "Limited X" tag).
     * Can be any Tag[]. Cannot be null or contain null elements.
     */
    protected Tag[] tags;
    
    /**
     * Creates a new Equipment from a given equipment name and sets up all
     *     Equipment's other properties. Used in Equipment's children's
     *     constructors as the mandatory super() constructor.
     * @param equipmentName a String containing the Equipment's name.
     * @param equipmentManufacturer a String containing the Equipment's
     *     manufacturer. Must be a valid manufacturer as defined by
     *     Database.manufacturerList. Cannot be null.
     */
    protected Equipment(String equipmentName, String equipmentManufacturer) {
        setName(equipmentName);
        setManufacturer(equipmentManufacturer);
        setTags(new Tag[0]);
    }

    public String getName() {
        return name;
    }
    public String getManufacturer() {
        return manufacturer;
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

    /**
     * Searches for a specified tag. Returns whether the search was successful.
     * @param tagName a String containing the name of the tag to be searched
     *     for.
     * @return a boolean containing the result of the search.
     */
    public boolean hasTag(String tagName) {
        for (Tag tag : this.tags) {
            if (tag.getName().equals(tagName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * Searches for a specified tag. If the tag is present, returns the value
     *     attached to the requested tag. This value only makes sense for
     *     certain tags, such as "Limited X", and will not make sense for
     *     others, such as "AI". Since int's default value is 0, this method
     *     will return an int whether it is valid data or not, so it is best to
     *     check the tag name first.
     * @param tagName a String containing the name of the tag to be searched
     *     for.
     * @return an int containing the value attached to the requested tag.
     * @throws IllegalArgumentException if the requested tag could not be found.
     */
    public int getTag(String tagName) {
        for (Tag tag : this.tags) {
            if (tag.getName().equals(tagName)) {
                return tag.getValue();
            }
        }

        throw new IllegalArgumentException("Requested tag: \"" + tagName + "\""
            + " could not be found.");
    }
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        if (name.equals("")) {
            throw new IllegalArgumentException("New name is \"\"");
        }
        this.name = name;
    }
    /**
     * Is overridden in all of Equipment's children.
     * @throws IllegalArgumentException if tags is null or includes a null
     *     element.
     */
    protected void setTags(Tag[] tags) {
        // This will throw an exception if tags is invalid
        checkTagsArray(tags);
        tags = HelperMethods.copyOf(tags);
        this.tags = tags;
    }
    /**
     * Checks a Tag[] to see if it is a valid value for Equipment.tags. Throws
     *     an IllegalArgumentException if not. Used in Equipment's children's
     *     overridden setTags() methods.
     * @param tags a Tag[] to be checked.
     * @return a boolean containing the result of the check.
     * @throws IllegalArgumentException if the Tag[] is invalid or contains an
     *     invalid value for any number of reasons.
     */
    protected void checkTagsArray(Tag[] tags) {
        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (Tag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("New tags array includes a"
                    + " null element");
            }
            // Checking for tag.name being valid isn't necessary because it's
            //     already done in Tag's constructors
        }
    }
}