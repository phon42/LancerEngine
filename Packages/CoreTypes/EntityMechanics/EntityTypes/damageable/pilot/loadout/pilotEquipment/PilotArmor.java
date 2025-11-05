package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Actions.Action;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.PilotEquipment;

/**
 * Represents a single piece of pilot armor. Contains several optional
 *     properties, and all of the inherited properties.
 * 
 * Requires a pilot armor id, name, and an array of data tags to be
 *     instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class PilotArmor extends PilotEquipment {
    // TODO: fill out
    /**
     * Optional
     */
    private Bonus[] bonuses;
    /**
     * Optional
     */
    private Action[] actions;

    public PilotArmor(String id, String name, DataTag[] dataTags,
        String description, String effect, Bonus[] bonuses, Action[] actions) {
        super(id, name, "armor", dataTags, description, effect);
        setBonuses(bonuses);
        setActions(actions);
    }
    public PilotArmor(PilotArmor pilotArmor) {
        this(pilotArmor.id, pilotArmor.name, pilotArmor.dataTags,
            pilotArmor.description, pilotArmor.effect, pilotArmor.bonuses,
            pilotArmor.actions);
    }

    public Bonus[] getBonuses() {
        if (bonuses != null) {
            return HelperMethods.copyOf(bonuses);
        }

        return bonuses;
    }
    public Action[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    private void setBonuses(Bonus[] bonuses) {
        if (bonuses != null) {
            for (Bonus bonus : bonuses) {
                if (bonus == null) {
                    throw new IllegalArgumentException("bonuses array"
                        + " contains a null element");
                }
            }
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
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
}
