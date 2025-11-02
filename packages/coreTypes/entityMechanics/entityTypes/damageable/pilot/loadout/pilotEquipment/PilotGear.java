package packages.CoreTypes.EntityMechanics.EntityTypes.pilot.loadout.pilotEquipment;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.Action;
import packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.DataTag;
import packages.CoreTypes.EntityMechanics.EntityTypes.pilot.loadout.PilotEquipment;

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
    private Action[] actions;
    /**
     * Optional
     */
    private Deployable[] deployables;

    public PilotGear(String id, String name, DataTag[] dataTags,
        String description, String effect, Action[] actions,
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

    public Action[] getActions() {
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
    private void setActions(Action[] actions) {
        if (actions != null) {
            for (Action action : actions) {
                if (action == null) {
                    throw new IllegalArgumentException("actions array"
                        + " contains a null element");
                }
            }
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setDeployables(Deployable[] deployables) {
        if (deployables != null) {
            for (Deployable deployable : deployables) {
                if (deployable == null) {
                    throw new IllegalArgumentException("deployables array"
                        + " contains a null element");
                }
            }
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
}
