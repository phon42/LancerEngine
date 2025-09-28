/**
 * Represents a single talent of the pilot. Stores the talent's name, as well as
 *     the level at which it is held.
 */
public class Talent {
    /**
     * Can be any String except "". Cannot be null.
     */
    public String name;
    /**
     * Must be between 1 and 3 (inclusive).
     */
    public int level;

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
    /**
     * Sets this.name to the provided value.
     * @param name a String which cannot be null or "".
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New value for name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New value for name is \"\"");
        }
        this.name = name;
    }
    /**
     * Sets this.level to the provided value.
     * @param level an int which must be 1, 2, or 3.
     */
    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("New level is < 1");
        }
        if (level > 3) {
            throw new IllegalArgumentException("New level is > 3");
        }
        this.level = level;
    }
    
    /**
     * Compares this Talent object and obj. If they are the same class, returns
     *     true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
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
    /**
     * Compares this Talent object and talent. If they have the same property
     *     values, returns true.
     * @param talent a Talent to be compared to.
     * @return a boolean representing whether the two Talents are the same.
     */
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
    public Talent copyOf() {
        Talent copy = new Talent(this.name, this.level);

        return copy;
    }
}