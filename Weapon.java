/**
 * Represents a single weapon to be mounted on a mount.
 */
public class Weapon {
    // TODO: fill out
    /**
     * The weapon's name (i.e. "anti-materiel rifle").
     * Case-insensitive and stored in lowercase. Can be any String, though "" is
     *     a placeholder. Cannot be null.
     */
    private String name;

    public Weapon() {
        this.name = "";
    }
    public Weapon(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New name is \"\"");
        }
        name = name.toLowerCase();
        this.name = name;
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