package packages.coreTypes.entityMechanics.entityTypes.mech.equipment;

import packages.coreTypes.entityMechanics.entityTypes.mech.Equipment;
import packages.coreTypes.entityMechanics.Manufacturer;
import packages.coreTypes.entityMechanics.License;

/**
 * Represents a single weapon modification. Contains information about the mod
 *     such as its system point cost, the types of weapon it can be applied to,
 *     and its effect, as well as all inherited properties.
 * 
 * Requires a weapon mod id, name, system points cost, an array of the types of
 *     weapon it can be applied to, its manufacturer, the source license, the
 *     source license's rank, effect, description, and the source license's id
 *     to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Modification extends Equipment {
    private String id; // add to parent
    private String spCost;
    private String[] allowedTypes;
    // already present in parent, update version present in parent
    private Manufacturer manufacturer;
    // already present in parent, update version present in parent
    private License license;
    private String effect;
    private String description;

    public Modification() {
        // TODO: fill out
        super("", "", null);
    }
}
