package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem;

import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemActive;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemExtras;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemPassive;

/**
 * See pg. 33.
 */
public class ICoreSystemData {
    // Required property
    private CoreSystemData basicData;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    private CoreSystemActive active;
    private CoreSystemPassive passive;
    private CoreSystemExtras extraData;
}
