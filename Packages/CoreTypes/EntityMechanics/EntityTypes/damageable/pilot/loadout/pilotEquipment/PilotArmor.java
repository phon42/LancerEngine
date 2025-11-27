package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.DataTag;
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
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class PilotArmor extends PilotEquipment {
    // Optional properties
    /**
     * The pilot armor's bonuses (i.e. a Bonus representing a +3 to pilot HP).
     * Can be any Bonus[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    private Bonus[] bonuses;
    /**
     * The pilot armor's actions (i.e. an IActionData representing the
     *     "Activate Stealth Hardsuit" action).
     * Can be any IActionData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IActionData[] actions;

    public PilotArmor(
        // PilotEquipment properties
        String id, String name, DataTag[] dataTags, String description,
        String effect,
        // Optional properties
        Bonus[] bonuses, IActionData[] actions
    ) {
        // PilotEquipment properties
        super(id, name, "armor", dataTags, description, effect);
        // Optional properties
        setBonuses(bonuses);
        setActions(actions);
    }
    public PilotArmor(
        // PilotEquipment properties
        String id, String name, DataTag[] dataTags, String description,
        String effect
    ) {
        // PilotEquipment properties
        super(id, name, "armor", dataTags, description, effect);
    }

    // Optional properties
    public Bonus[] getBonuses() {
        if (bonuses != null) {
            return HelperMethods.copyOf(bonuses);
        }

        return bonuses;
    }
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    // Optional properties
    private void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        if (bonuses != null) {
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
}
