package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.BondBase;
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
public class Bond extends BondBase {
    // Required properties
    /**
     * The bond's id (i.e. "ktb-bond-harlequin").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
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

    public Bond(
        // BondBase properties
        String name, String[] majorIdeals, String[] minorIdeals,
        // Required properties
        String id, BondQuestion[] questions, BondPower[] powers
    ) {
        // BondBase properties
        super(name, majorIdeals, minorIdeals);
        // Required properties
        setID(id);
        setQuestions(questions);
        setPowers(powers);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public BondQuestion[] getQuestions() {
        return HelperMethods.copyOf(questions);
    }
    public BondPower[] getPowers() {
        return HelperMethods.copyOf(powers);
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
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
