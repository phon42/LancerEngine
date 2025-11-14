package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.DataTag;
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
    private Deployable[] deployables;

    public PilotGear(String id, String name, DataTag[] dataTags,
        String description, String effect, IActionData[] actions,
        Deployable[] deployables) {
        super(id, name, "gear", dataTags, description, effect);
        setActions(actions);
        setDeployables(deployables);
    }
    public PilotGear(PilotGear pilotGear) {
        this(pilotGear.id, pilotGear.name, pilotGear.dataTags,
            pilotGear.description, pilotGear.effect, pilotGear.actions,
            pilotGear.deployables);
    }

    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public Deployable[] getDeployables() {
        if (deployables != null) {
            return HelperMethods.copyOf(deployables);
        }

        return deployables;
    }
    private void setActions(IActionData[] actions) {
        if (actions != null) {
            if (actions.length == 0) {
                throw new IllegalArgumentException("actions array is of"
                    + " length 0");
            }
            HelperMethods.checkObjectArray("actions", actions);
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setDeployables(Deployable[] deployables) {
        if (deployables != null) {
            if (deployables.length == 0) {
                throw new IllegalArgumentException("deployables array is of"
                    + " length 0");
            }
            HelperMethods.checkObjectArray("deployables",
                deployables);
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
}
