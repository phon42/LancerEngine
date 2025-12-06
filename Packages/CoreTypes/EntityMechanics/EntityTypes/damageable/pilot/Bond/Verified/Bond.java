package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.BondBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.Verified.BondID;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.bondBase.BondPower;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.bondBase.BondQuestion;

/**
 * Represents a single verified bond. Contains information about the bond's id,
 *     name, ideals, questions, and powers.
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
    // Required property
    /**
     * The bond's id (i.e. a BondID representing "ktb-bond-harlequin").
     * Can be any BondID. Cannot be null.
     */
    private BondID id;

    public Bond(
        // BondBase properties
        String name, String[] majorIdeals, String[] minorIdeals,
        BondQuestion[] questions, BondPower[] powers,
        // Required property
        BondID id
    ) {
        // BondBase properties
        super(name, majorIdeals, minorIdeals, questions, powers);
        // Required property
        setID(id);
    }

    // Required property
    public BondID getID() {
        return id;
    }
    // Required property
    private void setID(BondID id) {
        HelperMethods.checkObject("id", id);
        this.id = id;
    }
}
