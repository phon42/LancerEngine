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
    /**
     * Returns a deep copy of this object.
     * @return a Weapon deep copy of this object.
     */
    public Weapon copyOf() {
        Weapon copy = new Weapon(this.name, this.tags);

        return copy;
    }
}