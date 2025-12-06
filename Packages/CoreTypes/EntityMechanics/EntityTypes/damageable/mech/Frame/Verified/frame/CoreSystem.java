package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.ICoreSystemData;

/**
 * See pg. 33.
 */
public class CoreSystem {
    // Required property
    /**
     * Can be any ICoreSystemData. Cannot be null.
     */
    private ICoreSystemData data;

    public CoreSystem(ICoreSystemData iCoreSystemData) {
        setData(iCoreSystemData);
    }
    public CoreSystem(CoreSystem coreSystem) {
        setData(coreSystem.data);
    }

    // Required property
    public ICoreSystemData getData() {
        return data;
    }
    // Required property
    private void setData(ICoreSystemData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
}
