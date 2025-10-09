/**
 * Represents a single weapon to be mounted on a mount. Contains information
 *     about that weapon such as its name, size, and tags.
 * 
 * Requires a weapon name and weapon size to be instantiated.
 * 
 * Used in Mount.
 * 
 * Safety: This class has placeholder values and can be a placeholder. None of
 *     its properties have allowed values of null.
 */
public class Weapon extends Equipment {
    // TODO: fill out with some kind of way to attack
    // TODO: add weapon type (add Bligh, Prototype Weapon,
    // TODO: add a Superheavy weapon size (for Empaakai and Lycan)
    // TODO: add a Ship-Class weapons size (for Barbarossa)
    // TODO: fucking deal with Ushabti Omnigun somehow
    public Weapon(String name) {
        super(name);
    }
    public Weapon(String name, EquipmentTag[] equipmentTags) {
        super(name, equipmentTags);
    }
    /**
     * Sets this.tags to the provided value.
     * @param tags an EquipmentTag[] which cannot be null, contain null
     *     elements, or contain EquipmentTags with invalid EquipmentTag.name
     *     values as defined by Weapon.allowedNames.
     * @throws IllegalArgumentException if tags includes an element with an
     *     invalid EquipmentTag.name for a Weapon, as defined by
     *     Weapon.allowedNames.
     */
    @Override
    public void setTags(EquipmentTag[] tags) {
        boolean isValid = false;

        // Throws an IllegalArgumentException if tags is null or contains null
        //     elements
        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (EquipmentTag tag : tags) {
            isValid = false;
            for (String allowedTag : EquipmentTag.allowedWeaponNames) {
                if (tag.getName().equals(allowedTag)) {
                    isValid = true;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes an"
                    + " EquipmentTag with an invalid tag name for a Weapon: \""
                    + tag.getName() + "\"");
            }
        }
        tags = HelperFunctions.copyOf(tags);
        this.tags = tags;
    }

    @Override
    /**
     * Returns a deep copy of this Weapon object.
     * @return a Weapon deep copy of this object.
     */
    public Weapon copyOf() {
        // don't need to make copies of this.tags because the mutator
        //     (Equipment.setTags()) called by Weapon(String, int,
        //     EquipmentTag[]) already does so
        Weapon copy = new Weapon(this.name, this.size, this.tags);

        return copy;
    }
}