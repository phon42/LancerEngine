package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;

/**
 * See pgs. 35 and 90 - 103.
 */
/**
 * Represents a single talent of the pilot. Contains the talent's data, as well
 *     as the level at which it is held.
 * 
 * Requires a talent data as well as a talent level to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Talent {
    /**
     * The talent's data (i.e. a TalentData object representing the Ace talent).
     * Can be any TalentData. Cannot be null.
     */
    private TalentData data;
    /**
     * Must be between 1 and 3 (inclusive).
     */
    private int level;

    /**
     * Creates a new Talent with the provided talent data and talent level.
     * @param data a TalentData which cannot be null.
     * @param level an int which must be between 1 and 3 (inclusive).
     */
    public Talent(TalentData data, int level) {
        setData(data);
        setLevel(level);
    }

    public TalentData getData() {
        return data;
    }
    public int getLevel() {
        return level;
    }
    private void setData(TalentData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
    /**
     * Sets this.level to the provided value.
     * @param level an int which must be 1, 2, or 3.
     * @throws IllegalArgumentException if level is not 1, 2, or 3.
     */
    private void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("New level value: " + level
                + " is < 1");
        }
        if (level > 3) {
            throw new IllegalArgumentException("New level value: " + level
                + " is > 3");
        }
        this.level = level;
    }

    /**
     * Generates a String representation of this Talent.
     * @return a String containing a representation of this Talent.
     */
    @Override
    public String toString() {
        return data.getName() + " " + getLevel();
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

        return equals((Talent) obj);
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
        if (! talent.getData().equals(this.data)) {
            return false;
        }
        if (talent.getLevel() != this.level) {
            return false;
        }

        return true;
    }
}