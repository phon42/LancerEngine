package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.dataTag;

import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;

/**
 * See pgs. 104 - 106.
 */
/**
 * Represents a tag for a mech system or weapon, like the tags "AI" or "Limited
 *     X". Contains information about the tag's name and value (if it has one).
 * 
 * Requires a tag name to be instantiated.
 * 
 * Used in Equipment, MechSystem, and Weapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Tag extends DataTag {
    // Helper properties
    /**
     * Contains an array of values for this.id for which this tag requires an
     *     accompanying value of some kind.
     * Elements are case-insensitive and stored in lowercase.
     */
    private static final String[] valueIDs = new String[] {
        // Shared tags
        "tg_heat_self", "tg_limited",
        // Mech system tags
        "tg_round", "tg_turn",
        // Weapon tags
        "tg_knockback", "tg_reliable", "tg_thrown",
        // Currently unused
        "tg_burn", "tg_heat_target", "tg_line", "tg_cone", "tg_blast",
        "tg_burst", "tg_range", "tg_recharge", "tg_threat"
    };
    /**
     * Contains an array of known tag IDs.
     * Elements are case-insensitive and stored in lowercase.
     */
    private static final String[] tagIDs = new String[] {
        // Shared tags
        "tg_heat_self", "tg_inaccurate", "tg_limited", "tg_loading",
        "tg_ordnance", "tg_resistall", "tg_smart",
        // Mech system tags
        "tg_ai", "tg_danger_zone", "tg_exotic", "tg_free_action", "tg_grenade",
        "tg_indestructible", "tg_invisible", "tg_invulnerable", "tg_overshield",
        "tg_quick_action", "tg_quick_tech", "tg_reaction", "tg_round",
        "tg_shield", "tg_turn", "tg_unique",
        // Deployable gear tags
        // All of Deployable's gear tags are shared by other classes
        // Pilot gear tags
        "tg_archaic", "tg_gear", "tg_personal_armor", "tg_pilot_weapon",
        "tg_sidearm",
        // Weapon tags
        "tg_accurate", "tg_ap", "tg_arcing", "tg_knockback", "tg_overkill",
        "tg_reliable", "tg_seeking", "tg_thrown",
        // Currently unused
        "tg_burn", "tg_heat_target", "tg_line", "tg_cone", "tg_blast",
        "tg_burst", "tg_deployable", "tg_drone", "tg_full_action", "tg_mine",
        "tg_mod", "tg_protocol", "tg_invade", "tg_full_tech", "tg_range",
        "tg_modded", "tg_resistance", "tg_recharge", "tg_unlimited",
        "tg_set_damage_value", "tg_efficient", "tg_threat"
    };

    public Tag(String id, String name, String description,
        boolean filterIgnore) {
        super(id, name, description, filterIgnore, false);
    }
}
