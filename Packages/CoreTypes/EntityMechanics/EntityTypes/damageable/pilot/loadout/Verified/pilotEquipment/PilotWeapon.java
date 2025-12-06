package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.PilotEquipment;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;

/**
 * Represents a single pilot weapon. Contains information about that weapon's
 *     description, range, and damage, as well as several optional properties,
 *     and all of the inherited properties.
 * 
 * Requires a pilot weapon id, name, description, range, damage, and an array of
 *     data tags to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class PilotWeapon extends PilotEquipment {
    // Optional properties
    private String effect;
    /**
     * Can be any RangeTag[] that does not contain null elements. Cannot be
     *     null.
     */
    private RangeTag[] range;
    /**
     * Can be any Harm[] that does not contain null elements. Cannot be null.
     */
    private Harm[] damage;

    public PilotWeapon(
        // PilotEquipment properties
        String id, String name, String description, DataTag[] dataTags,
        IActionData[] actions, Bonus[] bonuses, ISynergyData[] synergies,
        IDeployableData[] deployables,
        // Optional properties
        String effect, RangeTag[] range, Harm[] damage
    ) {
        super(id, name, "Weapon", description, dataTags, actions, bonuses,
            synergies, deployables);
        setEffect(effect);
        setRange(range);
        setDamage(damage);
    }
    public PilotWeapon(
        // PilotEquipment properties
        String id, String name,
        // Optional properties
        String effect, RangeTag[] range, Harm[] damage
    ) {
        this(id, name, null, null, null,
            null, null, null, effect, range,
            damage);
    }
    public PilotWeapon(
        // PilotEquipment properties
        String id, String name, String description, DataTag[] dataTags,
        IActionData[] actions, Bonus[] bonuses, ISynergyData[] synergies,
        IDeployableData[] deployables
    ) {
        super(id, name, "Weapon", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public PilotWeapon(String id, String name) {
        super(id, name, "Weapon");
    }

    // Optional properties
    public String getEffect() {
        return effect;
    }
    public RangeTag[] getRange() {
        return HelperMethods.copyOf(range);
    }
    public Harm[] getDamage() {
        return HelperMethods.copyOf(damage);
    }
    // Optional properties
    private void setEffect(String effect) {
        if (effect != null) {
            HelperMethods.checkString("effect", effect);
        }
        this.effect = effect;
    }
    private void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    private void setDamage(Harm[] damage) {
        HelperMethods.checkObjectArray("damage", damage);
        damage = HelperMethods.copyOf(damage);
        this.damage = damage;
    }
}
