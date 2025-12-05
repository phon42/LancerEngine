package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonusBase;
import Packages.CoreTypes.counterBase.CounterData;

public class CoreBonus extends CoreBonusBase {
    // Required property
    /**
     * The manufacturer of this core bonus (i.e. a Manufacturer representing
     *     GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    private Manufacturer source;
    // Optional property
    private IDeployableData[] deployables;

    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, String[] integrated,
        String[] specialEquipment,
        // Required property
        Manufacturer source,
        // Optional property
        IDeployableData[] deployables
    ) {
        // CoreBonusBase properties
        super(id, name, effect, description, mountedEffect, actions, bonuses,
            synergies, counters, integrated, specialEquipment);
        // Required property
        setSource(source);
        // Optional property
        setDeployables(deployables);
    }
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, String[] integrated,
        String[] specialEquipment,
        // Required property
        Manufacturer source
    ) {
        this(id, name, effect, description, mountedEffect, actions, bonuses,
            synergies, counters, integrated, specialEquipment, source,
            null);
    }
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Required property
        Manufacturer source,
        // Optional property
        IDeployableData[] deployables
    ) {
        this(id, name, effect, description, null, null,
            null, null, null, null,
            null, source, deployables);
    }
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Required property
        Manufacturer source
    ) {
        this(id, name, effect, description, source, null);
    }

    // Required property
    public Manufacturer getSource() {
        return source;
    }
    // Optional property
    public IDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    // Required property
    private void setSource(Manufacturer source) {
        HelperMethods.checkObject("source", source);
        this.source = source;
    }
    // Optional property
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
}
