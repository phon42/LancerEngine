/**
 * Represents a single talent of the pilot. Stores the talent's name, as well as
 *     the level at which it is held.
 */
public class Talent {
    /**
     * Can be any String (though "" is a placeholder). Cannot be null.
     */
    public String name;
    /**
     * Must be between -1 (placeholder) and 3 (inclusive). Cannot be 0.
     */
    public int level;

    public Talent() {
        setName("");
        setLevel(-1);
    }
    public Talent(String name, int level) {
        setName(name);
        setLevel(level);
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New value for name is null");
        }
        this.name = name;
    }
    public void setLevel(int level) {
        if (level < -1) {
            throw new IllegalArgumentException("New level is < -1");
        }
        if (level > 3) {
            throw new IllegalArgumentException("New level is > 3");
        }
        if (level == 0) {
            throw new IllegalArgumentException("New level is 0");
        }
        this.level = level;
    }
    
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
    public boolean equals(Talent talent) {
        if (talent == null) {
            return false;
        }
        if (! talent.getName().equals(getName())) {
            return false;
        }
        if (talent.getLevel() != getLevel()) {
            return false;
        }
        return true;
    }
}
