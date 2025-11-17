package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond;

import MainBranch.HelperMethods;

/**
 * Represents a single bond power. Contains information about the bond power's
 *     name and description.
 * 
 * Requires a bond power name and description to be instantiated.
 * 
 * Used in Bond.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class BondPower {
    /**
     * This bond power's name (i.e. "Masquerade").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * This bond power's description (i.e. "You can always tell if someone is"
     *     " lying, though you don’t know the exact nature or extent of their"
     *     " lie.").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    public BondPower(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
}
