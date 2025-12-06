package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.Verified;

import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.BondIDBase;

public class BondID extends BondIDBase {
    public BondID(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != BondID.class) {
            return false;
        }

        return equals((BondID) obj);
    }
    @Override
    public boolean equals(BondIDBase bondIDBase) {
        if (bondIDBase == null) {
            return false;
        }
        if (bondIDBase.getClass() != BondID.class) {
            return false;
        }

        return equals((BondID) bondIDBase);
    }
    public boolean equals(BondID bondID) {
        if (bondID == null) {
            return false;
        }
        if (! bondID.getID().equals(this.id)) {
            return false;
        }

        return true;
    }
}
