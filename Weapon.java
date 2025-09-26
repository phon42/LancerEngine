/**
 * Represents a single weapon to be mounted on a mount.
 */
public class Weapon {
    // TODO: fill out
    // The weapon's name
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
        this.name = name;
    }

    public boolean hasPlaceholders() {
        if (getName().equals("")) {
            return true;
        }

        return false;
    }
    public boolean isPlaceholder() {
        if (! getName().equals("")) {
            return false;
        }

        return true;
    }
}