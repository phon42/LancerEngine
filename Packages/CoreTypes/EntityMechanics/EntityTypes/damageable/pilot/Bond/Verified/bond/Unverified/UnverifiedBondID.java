package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.Unverified;

import MainBranch.Database;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.BondIDBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.Verified.bond.Verified.BondID;

public class UnverifiedBondID extends BondIDBase
    implements UnverifiedData<UnverifiedBondID, BondID> {
    public UnverifiedBondID(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != UnverifiedBondID.class) {
            return false;
        }

        return equals((UnverifiedBondID) obj);
    }
    @Override
    public boolean equals(BondIDBase bondIDBase) {
        if (bondIDBase == null) {
            return false;
        }
        if (bondIDBase.getClass() != UnverifiedBondID.class) {
            return false;
        }

        return equals((UnverifiedBondID) bondIDBase);
    }
    public boolean equals(UnverifiedBondID unverifiedBondID) {
        if (unverifiedBondID == null) {
            return false;
        }
        if (! unverifiedBondID.getID().equals(this.id)) {
            return false;
        }

        return true;
    }
    @Override
    public Class<UnverifiedBondID> getUnverifiedType() {
        return UnverifiedBondID.class;
    }
    @Override
    public Class<BondID> getVerifiedType() {
        return BondID.class;
    }
    @Override
    public BondID verify() {
        return Database.getBondID(id);
    }
}
