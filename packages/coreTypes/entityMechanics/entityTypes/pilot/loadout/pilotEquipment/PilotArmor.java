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
     * Case-sensitive.
     */
    private String description;
    /**
     * Optional
     */
    private Bonus[] bonuses;
    /**
     * Optional
     * Case-sensitive.
     */
    private String effect;
    /**
     * Optional
     */
    private Action[] actions;

    public PilotArmor(String id, String name, DataTag[] dataTags,
        String description, Bonus[] bonuses, String effect, Action[] actions) {
        super(id, name, "armor", dataTags);
        setDescription(description);
        setBonuses(bonuses);
        setEffect(effect);
        setActions(actions);
    }
    public PilotArmor(PilotArmor pilotArmor) {
        this(pilotArmor.id, pilotArmor.name, pilotArmor.dataTags,
            pilotArmor.description, pilotArmor.bonuses, pilotArmor.effect,
            pilotArmor.actions);
    }

    public String getDescription() {
        return description;
    }
    public Bonus[] getBonuses() {
        return HelperMethods.copyOf(bonuses);
    }
    public Action[] getActions() {
        return HelperMethods.copyOf(actions);
    }
    public String getEffect() {
        return effect;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    private void setBonuses(Bonus[] bonuses) {
        bonuses = HelperMethods.copyOf(bonuses);
        this.bonuses = bonuses;
    }
    private void setActions(Action[] actions) {
        actions = HelperMethods.copyOf(actions);
        this.actions = actions;
    }
    private void setEffect(String effect) {
        this.effect = effect;
    }
}
