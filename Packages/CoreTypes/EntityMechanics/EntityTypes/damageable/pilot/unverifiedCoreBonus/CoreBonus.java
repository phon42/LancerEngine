package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.UnverifiedCoreBonus;
import Packages.CoreTypes.counterBase.CounterData;

public class CoreBonus extends UnverifiedCoreBonus {
    // Required property
    /**
     * The manufacturer of this core bonus (i.e. a Manufacturer representing
     *     GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    private Manufacturer source;

    public CoreBonus(
        // Required properties
        String id, String name, Manufacturer source, String effect,
        String description,
        // Optional properties
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        CounterData[] counters, String[] integrated, String[] specialEquipment
    ) {
        super(id, name, "", effect, description, mountedEffect, actions,
            null, synergies, deployables, counters, integrated,
            specialEquipment);
        setSource(source);
    }
    public CoreBonus(String id, String name, Manufacturer source, String effect,
        String description) {
        this(id, name, source, effect, description, null,
            null, null, null, null,
            null, null, null);
        setSource(source);
    }

    // Required property
    public Manufacturer getSource() {
        return source;
    }
    // Required property
    private void setSource(Manufacturer source) {
        HelperMethods.checkObject("source", source);
        this.source = source;
    }
}
