package packages.coreTypes.entityMechanics.entityTypes.pilot.loadout;

import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;

/**
 * Represents a single piece of pilot equipment of any kind (be that armor,
 *     weapon, or gear). Contains information about that pilot equipment's id,
 *     name, type, data tags, and tags.
 * 
 * Cannot be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public class PilotEquipment {
    private String id;
    private String name;
    private String type;
    private DataTag[] dataTags;
    private Tag[] tags;

    protected PilotEquipment() {}
}
