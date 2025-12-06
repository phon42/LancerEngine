package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame;

import java.util.ArrayList;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.Counter;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.Deployable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.ICoreSystemData;

public class CoreSystem {
    // Required property
    /**
     * Can be any ICoreSystemData. Cannot be null.
     */
    private ICoreSystemData data;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    private ArrayList<Deployable> deployables;
    private ArrayList<Counter> counters;

    public CoreSystem(ICoreSystemData iCoreSystemData) {
        setData(iCoreSystemData);
        setDeployables(null);
        setCounters(null);
    }
    public CoreSystem(CoreSystem coreSystem) {
        setData(coreSystem.data);
        setDeployables(coreSystem.getDeployables());
        setCounters(coreSystem.getCounters());
    }

    // Required property
    public ICoreSystemData getData() {
        return data;
    }
    // Semi-required properties
    public Deployable[] getDeployables() {
        return deployables.toArray(new Deployable[deployables.size()]);
    }
    public Counter[] getCounters() {
        return counters.toArray(new Counter[counters.size()]);
    }
    // Required property
    private void setData(ICoreSystemData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
    // Semi-required properties
    // setDeployables and setCounters purposefully removed

    private void setDeployables(Deployable[] deployables) {
        this.deployables = new ArrayList<>();
        if (deployables == null) {
            return;
        }
        HelperMethods.checkObjectArray("deployables",
            deployables);
        for (int i = 0; i < deployables.length; i++) {
            this.deployables.set(i, deployables[i]);
        }
    }
    private void setCounters(Counter[] counters) {
        this.counters = new ArrayList<>();
        if (counters == null) {
            return;
        }
        HelperMethods.checkObjectArray("counters", counters);
        for (int i = 0; i < counters.length; i++) {
            this.counters.set(i, counters[i]);
        }
    }
}
