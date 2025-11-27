package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.dataTag;

import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.DataTag;

/**
 * See pgs. 104 - 106.
 */
/**
 * Represents a tag for a mech system or weapon, like the tags "AI" or "Limited
 *     X". Contains information about the tag's id, name, and description, among
 *     other properties.
 * 
 * Requires a tag id, name, description, and whether it is ignored in filters to
 *     be instantiated.
 * 
 * Used in Equipment, MechSystem, and Weapon.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public final class Tag extends DataTag {
    public Tag(String id, String name, String description,
        TriState filterIgnore) {
        super(id, name, description, filterIgnore, TriState.FALSE);
    }
}
