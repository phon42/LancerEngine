package packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment;

import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.PilotEquipment;
import packages.coreTypes.entityMechanics.harmSystem.Harm;
import packages.coreTypes.entityMechanics.RangeTag;

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
    private RangeTag[] range;
    private Harm[] damage;
}
