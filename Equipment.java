/**
 * Represents a piece of mech equipment of any kind, such as a mech system or a
 *     mech weapon. Contains information about the equipment's name and tags.
 * 
 * Requires an equipment name to be instantiated.
 * 
 * Used in and extended by MechSystem and Weapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Equipment {
    // TODO: add some way to add actions for systems that provide them
    // TODO: add some way for EquipmentTags to be automatically added based on
    //     an equipment's contents
    /**
     * The equipment's name (i.e. "Armament Redundancy" or "Anti-Materiel
     *     Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * Contains an array of all of this equipment's tags (i.e. EquipmentTag
     *     elements representing an "AI" or "Limited X" tag).
     * Can be any EquipmentTag[]. Cannot be null or contain null elements.
     */
    protected EquipmentTag[] tags;
    
    /**
     * Creates a new Equipment from a given equipment name and sets up all
     *     Equipment's other properties. Used in Equipment's children's
     *     constructors as the mandatory super() constructor.
     * @param equipmentName a String containing the Equipment's name.
     */
    protected Equipment(String equipmentName) {
        setName(equipmentName);
        setTags(new EquipmentTag[0]);
    }

    public String getName() {
        return name;
    }
    /**
     * Searches for a specified tag. Returns whether the search was successful.
     * @param tagName a String containing the name of the tag to be searched
     *     for.
     * @return a boolean containing the result of the search.
     */
    public boolean hasTag(String tagName) {
        for (EquipmentTag tag : this.tags) {
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
        for (EquipmentTag tag : this.tags) {
            if (tag.getName().equals(tagName)) {
                return tag.getValue();
            }
        }

        throw new IllegalArgumentException("Requested tag: \"" + tagName + "\""
            + " could not be found.");
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name is null");
        }
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
    public void setTags(EquipmentTag[] tags) {
        // This will throw an exception if tags is invalid
        checkTagsArray(tags);
        tags = HelperMethods.copyOf(tags);
        this.tags = tags;
    }
    /**
     * Checks an EquipmentTag[] to see if it is a valid value for
     *     Equipment.tags. Throws an IllegalArgumentException if not. Used in
     *     Equipment's children's overridden setTags() methods.
     * @param tags an EquipmentTag[] to be checked.
     * @return a boolean containing the result of the check.
     * @throws IllegalArgumentException if the EquipmentTag[] is invalid or
     *     contains an invalid value for any number of reasons.
     */
    protected void checkTagsArray(EquipmentTag[] tags) {
        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (EquipmentTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("New tags array includes a"
                    + " null element");
            }
            // Checking for tag.name being valid isn't necessary because it's
            //     already done in EquipmentTag's constructors
        }
    }

    /**
     * Returns a deep copy of this Equipment object. Is overridden in all of
     *     Equipment's children.
     * @return an Equipment deep copy of this object.
     */
    public Equipment copyOf() {
        // don't need to make copies of this.tags because the mutator
        //     (Equipment.setTags()) called by Equipment(String, EquipmentTag[])
        //     already does so
        Equipment copy = new Equipment(this.name);

        copy.setTags(this.tags);

        return copy;
    }
}