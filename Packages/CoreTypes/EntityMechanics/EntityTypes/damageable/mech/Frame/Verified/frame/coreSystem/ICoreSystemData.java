package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.ActivationType.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemActive;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemExtras;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData.CoreSystemPassive;

/**
 * See pg. 33.
 */
public class ICoreSystemData {
    // Required properties
    private CoreSystemData basicData;
    private CoreSystemActive active;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    private CoreSystemPassive passive;
    private CoreSystemExtras extraData;

    public ICoreSystemData(
        // Required properties
        CoreSystemData basicData, CoreSystemActive active,
        // Semi-required properties
        CoreSystemPassive passive, CoreSystemExtras extraData
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setBasicData(basicData);
        setActive(active);
        // Semi-required properties
        setPassive(passive);
        setExtraData(extraData);
    }
    public ICoreSystemData(
        // Required properties
        CoreSystemData basicData, CoreSystemActive active
    ) {
        this(basicData, active, null, null);
    }
    public ICoreSystemData(
        // Required properties
        String coreSystemName, String activeName, String activeEffect,
        ActivationType activeActivationType
    ) {
        HelperMethods.verifyConstructor();
        setBasicData(coreSystemName);
        setActive(activeName, activeEffect, activeActivationType);
    }

    // Required properties
    public CoreSystemData getBasicData() {
        return basicData;
    }
    public CoreSystemActive getActive() {
        return active;
    }
    // Semi-required properties
    public CoreSystemPassive getPassive() {
        return passive;
    }
    public CoreSystemExtras getExtraData() {
        return extraData;
    }
    // Required properties
    private void setBasicData(CoreSystemData basicData) {
        HelperMethods.checkObject("basicData", basicData);
        this.basicData = basicData;
    }
    private void setActive(CoreSystemActive active) {
        HelperMethods.checkObject("basicData", basicData);
        this.active = active;
    }
    // Semi-required properties
    private void setPassive(CoreSystemPassive passive) {
        this.passive = passive;
    }
    private void setExtraData(CoreSystemExtras extraData) {
        this.extraData = extraData;
    }

    private void setBasicData(String name) {
        setBasicData(new CoreSystemData(name));
    }
    private void setActive(String name, String effect,
        ActivationType activation) {
        setActive(new CoreSystemActive(name, effect, activation));
    }
}
