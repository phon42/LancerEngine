package packages.coreTypes.entityMechanics.entityTypes.pilot.talent;

import main.HelperMethods;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
/**
 * See pgs. 35 and 90 - 103.
 */
/**
 * Represents a single talent of the pilot. Contains the talent's name, as well
 *     as the level at which it is held.
 * 
 * Requires a talent name as well as a talent level to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class TalentData {
    private String id;
    /**
     * The name of the talent (i.e. "ace").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null.
     * Use Talent.getName() to get the raw value and Talent.outputName() to
     *     obtain it properly formatted.
     */
    private String name;
    private String icon;
    private String terse;
    private String description;
    private TalentRank[] ranks;

    /**
     * Creates a new Talent with the provided talent name and talent level.
     * @param talentName a String which cannot be null or "".
     * @param talentLevel an int which must be between 1 and 3 (inclusive).
     */
    public TalentData(String talentName, int talentLevel) {
        setName(talentName);
        setLevel(talentLevel);
    }
    /**
     * Creates a copy of the provided Talent.
     * @param talent a Talent to be copied.
     * @return a Talent copy of the provided Talent.
     */
    public TalentData(TalentData talent) {
        this(talent.name, talent.level);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setID(String id) {
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        name = name.toLowerCase();
        this.name = name;
    }

    /**
     * Generates a String representation of this Talent.
     * @return a String containing a representation of this Talent.
     */
    @Override
    public String toString() {
        return outputName() + " " + getLevel();
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
        if (! talent.getName().equals(this.name)) {
            return false;
        }
        if (talent.getLevel() != this.level) {
            return false;
        }
        return true;
    }
    /**
     * Returns this.name, properly formatted (i.e. "combined arms" becomes
     *     "Combined Arms").
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        return HelperMethods.toProperCase(name);
    }
}