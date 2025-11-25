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
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    private void setBonuses(Bonus[] bonuses) {
        if (bonuses != null) {
            if (bonuses.length == 0) {
                throw new IllegalStateException("bonuses array is of length"
                    + " 0");
            }
            HelperMethods.checkObjectArray("bonuses", bonuses);
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    private void setActions(IActionData[] actions) {
        if (actions != null) {
            if (actions.length == 0) {
                throw new IllegalStateException("actions array is of length"
                    + " 0");
            }
            HelperMethods.checkObjectArray("actions", actions);
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
}
