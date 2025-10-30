package packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment;

import main.HelperMethods;
import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.Bonus;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.PilotEquipment;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;

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
        return HelperMethods.copyOf(bonuses);
    }
    public Action[] getActions() {
        return HelperMethods.copyOf(actions);
    }
    private void setBonuses(Bonus[] bonuses) {
        bonuses = HelperMethods.copyOf(bonuses);
        this.bonuses = bonuses;
    }
    private void setActions(Action[] actions) {
        actions = HelperMethods.copyOf(actions);
        this.actions = actions;
    }
}
