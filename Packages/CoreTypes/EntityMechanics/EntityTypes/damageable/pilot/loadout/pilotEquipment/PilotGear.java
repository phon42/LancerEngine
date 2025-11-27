package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.PilotEquipment;

/**
 * Represents a single piece of pilot gear. Contains several optional properties
 *     and all of the inherited properties.
 * 
 * Requires a pilot armor id, name, and an array of data tags to be
 *     instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class PilotGear extends PilotEquipment {
    // TODO: fill out
    // Optional properties
    /**
     * Can be any IActionData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IActionData[] actions;
    /**
     * Can be any IDeployableData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IDeployableData[] deployables;

    public PilotGear(
        // PilotEquipment properties
        String id, String name, DataTag[] dataTags, String description,
        String effect,
        // Optional properties
        IActionData[] actions, IDeployableData[] deployables
    ) {
        // PilotEquipment properties
        super(id, name, "gear", dataTags, description, effect);
        // Optional properties
        setActions(actions);
        setDeployables(deployables);
    }
    public PilotGear(
        // PilotEquipment properties
        String id, String name, DataTag[] dataTags, String description,
        String effect
    ) {
        // PilotEquipment properties
        super(id, name, "armor", dataTags, description, effect);
    }

    // Optional properties
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public IDeployableData[] getDeployables() {
        if (deployables != null) {
            HelperMethods.copyOf(deployables);
        }

        return deployables;
    }
    // Optional properties
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
}
