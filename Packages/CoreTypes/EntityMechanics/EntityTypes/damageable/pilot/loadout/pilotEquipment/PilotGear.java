package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
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
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class PilotGear extends PilotEquipment {
    // TODO: fill out
    /**
     * Optional
     */
    private IActionData[] actions;
    /**
     * Optional
     */
    private IDeployableData[] deployables;

    public PilotGear(String id, String name, DataTag[] dataTags,
        String description, String effect, IActionData[] actions,
        IDeployableData[] deployables) {
        super(id, name, "gear", dataTags, description, effect);
        setActions(actions);
        setDeployables(deployables);
    }

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
