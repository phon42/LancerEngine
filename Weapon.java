/**
 * Represents a single weapon to be mounted on a mount.
 * Safety: This class has placeholder values and can be a placeholder, though
 *     there is no difference between the two. None of its properties have
 *     allowed values of null.
 */
public class Weapon extends Equipment {
    // TODO: fill out
    public Weapon() {
        this.name = "";
    }
    public Weapon(String name) {
        setName(name);
    }

    /**
     * A method checking whether any of the properties of this object whose
     *     placeholder value is normally not allowed are set to their
     *     placeholder value.
     * @return a boolean representing the result of the check.
     */
    public boolean hasPlaceholders() {
        if (getName().equals("")) {
            return true;
        }

        return false;
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

        return true;
    }
    /**
     * Returns a deep copy of this object.
     * @return a Weapon deep copy of this object.
     */
    public Weapon copyOf() {
        Weapon copy = new Weapon();

        copy.name = this.name;

        return copy;
    }
}