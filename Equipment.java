public class Equipment {
    // TODO: fill out
    /**
     * The equipment's name (i.e. "Armament Redundancy" or "Anti-Materiel
     *     Rifle").
     * Can be any String except "". Cannot be null.
     */
    protected String name;
    /**
     * Contains an array of all of this equipment's tags (i.e. "AI" or "Limited
     *     X"). Cannot be null or contain null elements.
     */
    protected EquipmentTag[] tags;
    
    protected Equipment(String equipmentName) {
        setName(equipmentName);
        setTags(new EquipmentTag[0]);
    }
    public Equipment(String equipmentName, EquipmentTag[] equipmentTags) {
        setName(equipmentName);
        setTags(equipmentTags);
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
     */
    public void setTags(EquipmentTag[] tags) {
        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (EquipmentTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("New tags value includes a"
                    + " null value");
            }
        }
        tags = HelperFunctions.copyOf(tags);
        this.tags = tags;
    }
    
    /**
     * Checks whether this object has all of its properties set to placeholder
     *     values.
     * @return a boolean representing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! getName().equals("")) {
            return false;
        }
        if (this.tags.length != 0) {
            return false;
        }

        return true;
    }
    /**
     * Returns a deep copy of this object.
     * @return an Equipment deep copy of this object.
     */
    public Equipment copyOf() {
        Equipment copy = new Equipment(this.name, this.tags);

        return copy;
    }
}