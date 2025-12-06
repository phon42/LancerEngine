package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.bondBase.BondPower;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.bondBase.BondQuestion;

/**
 * Represents a single bond. Contains information about the bond's id, name,
 *     ideals, questions, and powers.
 * 
 * Requires a bond id, name, an array of major ideals, an array of minor ideals,
 *     an array of questions, and an array of powers to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class Bond {
    // Required properties
    /**
     * The bond's id (i.e. "ktb-bond-harlequin").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The bond's name (i.e. "The Harlequin").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * An array of the bond's major ideals (i.e. a String[] containing "I"
     *     " addressed challenges with cunning, subterfuge, or deceit.", "I"
     *     " expressed my heritage, background, or beliefs through my actions.",
     *     and "I struggled with issues from my burdens or background.").
     * Can be any String[] that does not null elements. Cannot be null.
     */
    private String[] majorIdeals;
    /**
     * An array of the bond's minor ideals (i.e. a String[] containing "I"
     *     " struggled against or humiliated the powerful.", "I pretended to be"
     *     " someone else for a time.", "I charmed my way out of a charged"
     *     " situation.", "I obtained a guarded, hidden, or secret object or"
     *     " piece of information.").
     * Can be any String[] that does not null elements. Cannot be null.
     */
    private String[] minorIdeals;
    /**
     * This bond's question (i.e. a BondQuestion representing "What gives you"
     *     " your powers?").
     * Can be any BondQuestion[] that does not contain null elements. Cannot be
     *     null.
     */
    private BondQuestion[] questions;
    /**
     * This bond's powers (i.e. a BondPower representing "Masquerade").
     * Can be any BondPower[] that does not contain null elements. Cannot be
     *     null.
     */
    private BondPower[] powers;

    public Bond(String id, String name, String[] majorIdeals,
        String[] minorIdeals, BondQuestion[] questions, BondPower[] powers) {
        setID(id);
        setName(name);
        setMajorIdeals(majorIdeals);
        setMinorIdeals(minorIdeals);
        setQuestions(questions);
        setPowers(powers);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String[] getMajorIdeals() {
        return HelperMethods.copyOf(majorIdeals);
    }
    public String[] getMinorIdeals() {
        return HelperMethods.copyOf(minorIdeals);
    }
    public BondQuestion[] getQuestions() {
        return HelperMethods.copyOf(questions);
    }
    public BondPower[] getPowers() {
        return HelperMethods.copyOf(powers);
    }
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setMajorIdeals(String[] majorIdeals) {
        HelperMethods.checkStringArray("majorIdeals",
            majorIdeals);
        majorIdeals = HelperMethods.copyOf(majorIdeals);
        this.majorIdeals = majorIdeals;
    }
    private void setMinorIdeals(String[] minorIdeals) {
        HelperMethods.checkStringArray("minorIdeals",
            minorIdeals);
        majorIdeals = HelperMethods.copyOf(minorIdeals);
        this.minorIdeals = minorIdeals;
    }
    private void setQuestions(BondQuestion[] questions) {
        HelperMethods.checkObjectArray("questions", questions);
        questions = HelperMethods.copyOf(questions);
        this.questions = questions;
    }
    private void setPowers(BondPower[] powers) {
        HelperMethods.checkObjectArray("powers", powers);
        powers = HelperMethods.copyOf(powers);
        this.powers = powers;
    }
}
