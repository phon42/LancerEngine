package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
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
    private IActionData[] actions;

    public PilotArmor(String id, String name, DataTag[] dataTags,
        String description, String effect, Bonus[] bonuses,
        IActionData[] actions) {
        super(id, name, "armor", dataTags, description, effect);
        setBonuses(bonuses);
        setActions(actions);
    }

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
