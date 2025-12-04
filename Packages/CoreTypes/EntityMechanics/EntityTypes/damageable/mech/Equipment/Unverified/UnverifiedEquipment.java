package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Unverified;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.Equipment;

public class UnverifiedEquipment
    implements UnverifiedData<UnverifiedEquipment, Equipment> {
    @Override
    public Equipment verify() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Class<UnverifiedEquipment> getUnverifiedType() {
        return UnverifiedEquipment.class;
    }
    @Override
    public Class<Equipment> getVerifiedType() {
        return Equipment.class;
    }
}
