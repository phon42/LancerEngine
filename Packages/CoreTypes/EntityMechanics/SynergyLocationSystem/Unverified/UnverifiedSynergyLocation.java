package Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Unverified;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.SynergyLocationBase;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified.SynergyLocation;

// TODO: fill out
public class UnverifiedSynergyLocation extends SynergyLocationBase
    implements UnverifiedData<UnverifiedSynergyLocation, SynergyLocation> {
    protected UnverifiedSynergyLocation(String id) {
        super(id);
    }

    @Override
    public Class<UnverifiedSynergyLocation> getUnverifiedType() {
        return UnverifiedSynergyLocation.class;
    }
    @Override
    public Class<SynergyLocation> getVerifiedType() {
        return SynergyLocation.class;
    }
    @Override
    public SynergyLocation verify() {
        return new SynergyLocation(id);
    }
}
