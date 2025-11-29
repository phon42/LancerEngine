package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.talentData.TalentRank;

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
    /**
     * The talent's id (i.e. "t_ace").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of the talent (i.e. "Ace").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The name of the filename for the talent's icon (i.e. "ACE").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String icon;
    /**
     * A short description of this talent (i.e. "").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String terse;
    /**
     * A detailed description of this talent (i.e. "").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;
    /**
     * The ranks of this talent (too long to provide an example).
     * Can be any TalentRank[] that is of length 3 and does not contain null
     *     elements. Cannot be null.
     */
    private TalentRank[] ranks;

    public TalentData(String id, String name, String icon,
        String shortDescription, String longDescription,
        TalentRank[] talentRanksData) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setIcon(icon);
        setTerse(shortDescription);
        setDescription(longDescription);
        setRanks(talentRanksData);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getIcon() {
        return icon;
    }
    public String getTerse() {
        return terse;
    }
    public String getDescription() {
        return description;
    }
    public TalentRank[] getRanks() {
        return HelperMethods.copyOf(ranks);
    }
    private void setID(String id) {
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setIcon(String icon) {
        HelperMethods.checkString("icon", icon);
        this.icon = icon;
    }
    private void setTerse(String terse) {
        HelperMethods.checkString("terse", terse);
        this.terse = terse;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    private void setRanks(TalentRank[] ranks) {
        HelperMethods.checkObjectArray("ranks", ranks);
        if (ranks.length != 3) {
            throw new IllegalArgumentException("ranks array is of length: "
                + ranks.length + " which != 3");
        }
        HelperMethods.copyOf(ranks);
        this.ranks = ranks;
    }

    /**
     * Generates a String representation of this TalentData.
     * @return a String containing a representation of this TalentData.
     */
    @Override
    public String toString() {
        return name + " (\"" + id + "\")\n  " + terse;
    }
    /**
     * Compares this TalentData object and obj. If they are the same class,
     *     returns true.
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
     * Compares this TalentData object and talentData. If they have the same
     *     property values, returns true.
     * @param talentData a TalentData to be compared to.
     * @return a boolean representing whether the two TalentDatas are the same.
     */
    public boolean equals(TalentData talentData) {
        TalentRank[] ranks;

        if (talentData == null) {
            return false;
        }
        if (! talentData.getID().equals(this.id)) {
            return false;
        }
        if (! talentData.getName().equals(this.name)) {
            return false;
        }
        if (! talentData.getIcon().equals(this.icon)) {
            return false;
        }
        if (! talentData.getTerse().equals(this.terse)) {
            return false;
        }
        if (! talentData.getDescription().equals(this.description)) {
            return false;
        }
        ranks = talentData.getRanks();
        for (int i = 0; i < ranks.length; i++) {
            if (! ranks[i].equals(this.ranks[i])) {
                return false;
            }
        }

        return true;
    }
}