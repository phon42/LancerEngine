package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.loadout.PilotEquipment;
import Packages.CoreTypes.EntityMechanics.HarmSystem.damage.Harm;

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
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class PilotWeapon extends PilotEquipment {
    // TODO: fill out
    /**
     * Can be any RangeTag[] that does not contain null elements. Cannot be
     *     null.
     */
    private RangeTag[] range;
    /**
     * Can be any Harm[] that does not contain null elements. Cannot be null.
     */
    private Harm[] damage;

    public PilotWeapon(String id, String name, DataTag[] dataTags,
        String description, String effect, RangeTag[] range, Harm[] damage) {
        super(id, name, "weapon", dataTags, description, effect);
        setRange(range);
        setDamage(damage);
    }
    public PilotWeapon(PilotWeapon pilotWeapon) {
        this(pilotWeapon.id, pilotWeapon.name, pilotWeapon.dataTags,
            pilotWeapon.description, pilotWeapon.effect, pilotWeapon.range,
            pilotWeapon.damage);
    }

    public RangeTag[] getRange() {
        return HelperMethods.copyOf(range);
    }
    public Harm[] getDamage() {
        return HelperMethods.copyOf(damage);
    }
    public void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    public void setDamage(Harm[] damage) {
        HelperMethods.checkObjectArray("damage", damage);
        damage = HelperMethods.copyOf(damage);
        this.damage = damage;
    }
}
