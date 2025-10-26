package packages.coreTypes.entityMechanics.entityTypes.pilot.loadout;

import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;

/**
 * Represents a single piece of pilot equipment of any kind (be that armor,
 *     weapon, or gear). Contains information about that pilot equipment's id,
 *     name, type, data tags, and tags.
 * 
 * Requires a pilot equipment id, name, type, and array of data tags to be
 *     instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class PilotEquipment {
    private String id;
    private String name;
    private String type;
    private DataTag[] dataTags;
    private Tag[] tags;
}
