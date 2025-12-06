package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.CounterData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;

public class CoreSystemExtras {
    // Optional properties
    private IDeployableData[] deployables;
    private CounterData[] counters;
    private String[] integrated;
    private String[] specialEquipment;
    private DataTag[] tags;

    public CoreSystemExtras(IDeployableData[] deployables,
        CounterData[] counters, String[] integrated, String[] specialEquipment,
        DataTag[] tags) {
        setDeployables(deployables);
        setCounters(counters);
        setIntegrated(integrated);
        setSpecialEquipment(specialEquipment);
        setTags(tags);
    }

    // Optional properties
    public IDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    public CounterData[] getCounters() {
        if (counters == null) {
            return counters;
        }

        return HelperMethods.copyOf(counters);
    }
    public String[] getIntegrated() {
        if (integrated == null) {
            return integrated;
        }

        return HelperMethods.copyOf(integrated);
    }
    public String[] getSpecialEquipment() {
        if (specialEquipment == null) {
            return specialEquipment;
        }

        return HelperMethods.copyOf(specialEquipment);
    }
    public DataTag[] getTags() {
        if (tags == null) {
            return tags;
        }

        return HelperMethods.copyOf(tags);
    }
    // Optional properties
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
    private void setCounters(CounterData[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        if (counters != null) {
            counters = HelperMethods.copyOf(counters);
        }
        this.counters = counters;
    }
    private void setIntegrated(String[] integrated) {
        HelperMethods.checkStringArrayAlt("integrated",
            integrated);
        if (integrated != null) {
            integrated = HelperMethods.copyOf(integrated);
        }
        this.integrated = integrated;
    }
    private void setSpecialEquipment(String[] specialEquipment) {
        HelperMethods.checkStringArrayAlt("specialEquipment",
            specialEquipment);
        if (specialEquipment != null) {
            specialEquipment = HelperMethods.copyOf(specialEquipment);
        }
        this.specialEquipment = specialEquipment;
    }
    private void setTags(DataTag[] tags) {
        HelperMethods.checkObjectArrayAlt("tags", tags);
        if (tags != null) {
            tags = HelperMethods.copyOf(tags);
        }
        this.tags = tags;
    }
}
