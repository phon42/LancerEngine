/**
 * Represents a single weapon to be mounted on a mount.
 * Safety: This class has placeholder values and can be a placeholder, though
 *     there is no difference between the two. None of its properties have
 *     allowed values of null.
 */
public class Weapon extends Equipment {
    public Weapon(String name) {
        super(name);
    }
    public Weapon(String name, EquipmentTag[] equipmentTags) {
        super(name, equipmentTags);
    }

    @Override
    public void setTags(EquipmentTag[] tags) {
        boolean isValid = false;

        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (EquipmentTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("New tags value includes a"
                    + " null value");
            }
            isValid = false;
            for (String allowedTag : EquipmentTag.allowedWeaponNames) {
                if (tag.equals(allowedTag)) {
                    isValid = true;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes an"
                    + " invalid tag name for a Weapon: \"" + tag + "\"");
            }
        }
        tags = HelperFunctions.copyOf(tags);
        this.tags = tags;
    }

    @Override
    /**
     * Returns a deep copy of this object.
     * @return a Weapon deep copy of this object.
     */
    public Weapon copyOf() {
        Weapon copy = new Weapon(this.name, this.tags);

        return copy;
    }
}