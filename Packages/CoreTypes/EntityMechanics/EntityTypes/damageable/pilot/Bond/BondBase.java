package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;

import MainBranch.HelperMethods;

public class BondBase {
    // Required properties
    /**
     * The bond's name (i.e. "The Harlequin").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;
    /**
     * An array of the bond's major ideals (i.e. a String[] containing "I"
     *     " addressed challenges with cunning, subterfuge, or deceit.", "I"
     *     " expressed my heritage, background, or beliefs through my actions.",
     *     and "I struggled with issues from my burdens or background.").
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     * Elements are case-sensitive.
     */
    protected String[] majorIdeals;
    /**
     * An array of the bond's minor ideals (i.e. a String[] containing "I"
     *     " struggled against or humiliated the powerful.", "I pretended to be"
     *     " someone else for a time.", "I charmed my way out of a charged"
     *     " situation.", "I obtained a guarded, hidden, or secret object or"
     *     " piece of information.").
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     * Elements are case-sensitive.
     */
    protected String[] minorIdeals;

    protected BondBase(
        // Required properties
        String name, String[] majorIdeals, String[] minorIdeals
    ) {
        // Required properties
        setName(name);
        setMajorIdeals(majorIdeals);
        setMinorIdeals(minorIdeals);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String[] getMajorIdeals() {
        return HelperMethods.copyOf(majorIdeals);
    }
    public String[] getMinorIdeals() {
        return HelperMethods.copyOf(minorIdeals);
    }
    // Required properties
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    protected void setMajorIdeals(String[] majorIdeals) {
        HelperMethods.checkStringArray("majorIdeals",
            majorIdeals);
        majorIdeals = HelperMethods.copyOf(majorIdeals);
        this.majorIdeals = majorIdeals;
    }
    protected void setMinorIdeals(String[] minorIdeals) {
        HelperMethods.checkStringArray("minorIdeals",
            minorIdeals);
        majorIdeals = HelperMethods.copyOf(minorIdeals);
        this.minorIdeals = minorIdeals;
    }
}
