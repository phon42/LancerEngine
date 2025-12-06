package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond;

import MainBranch.HelperMethods;

public class BondIDBase {
    // Required property
    /**
     * The bond id's value (i.e. "ktb-bond-harlequin").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;

    protected BondIDBase(String id) {
        setID(id);
    }

    // Required property
    public String getID() {
        return id;
    }
    // Required property
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != BondIDBase.class) {
            return false;
        }

        return equals((BondIDBase) obj);
    }
    public boolean equals(BondIDBase bondIDBase) {
        if (bondIDBase == null) {
            return false;
        }
        if (! bondIDBase.getID().equals(this.id)) {
            return false;
        }

        return true;
    }
}
