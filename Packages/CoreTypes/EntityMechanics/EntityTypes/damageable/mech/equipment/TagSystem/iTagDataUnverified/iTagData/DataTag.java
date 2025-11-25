package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.dataTag.Tag;

/**
 * See
 *     https://github.com/massif-press/lancer-data/blob/master/README.md#tags-tagsjson.
 */
/**
 * Represents a tag on a JSON file for a mech system or weapon (i.e. the tags
 *     "AI" or "Grenade"). These tags are not always publicly visible. Contains
 *     information about the data tag's name and value (if it has one).
 * 
 * Requires a data tag ID to be instantiated.
 * 
 * Used in Database and ITagData.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class DataTag {
    // Required properties
    /**
     * The id for this data tag (i.e. "tg_ai").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;
    /**
     * The name for this data tag (i.e. "AI").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;
    /**
     * A short description for this DataTag.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String description;
    /**
     * Whether this data tag is always hidden.
     */
    protected boolean hidden;
    /**
     * Whether filtering algorithms should hide this data tag.
     */
    protected boolean filterIgnore;
    /**
     * Whether this data tag requires a value of some kind.
     * Is TriState.TRUE if this.id is present in DataTag.valueIDs.
     * Is TriState.FALSE if this.id is not present in DataTag.valueIDs but
     *     present in DataTag.dataTagIDs.
     * Is TriState.UNSET if this.id is not present in DataTag.dataTagIDs.
     * Can be any TriState. Cannot be null.
     */
    protected TriState acceptsValue;

    // Helper properties
    /**
     * Contains an array of values for this.id for which having a value other
     *     than 0 for this.value makes sense.
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
     * Contains an array of allowed DataTag values.
     */
    private static final String[] dataTagIDs = new String[] {
        // Shared tags
        "tg_heat_self", "tg_inaccurate", "tg_limited", "tg_loading",
        "tg_ordnance", "tg_resistall", "tg_set_damage_type", "tg_smart",
        // Mech system tags
        "tg_ai", "tg_danger_zone", "tg_exotic", "tg_free_action", "tg_grenade",
        "tg_indestructible", "tg_invisible", "tg_invulnerable", "tg_no_cascade",
        "tg_overshield", "tg_quick_action", "tg_quick_tech", "tg_reaction",
        "tg_round", "tg_shield", "tg_turn", "tg_unique",
        // Deployable gear tags
        // All of Deployable's gear tags are shared by other classes
        // Pilot gear tags
        "tg_archaic", "tg_gear", "tg_personal_armor", "tg_pilot_weapon",
        "tg_sidearm",
        // Weapon tags
        "tg_accurate", "tg_ap", "tg_arcing", "tg_knockback", "tg_overkill",
        "tg_reliable", "tg_seeking", "tg_set_max_uses", "tg_thrown",
        // Currently unused
        "tg_burn", "tg_heat_target", "tg_line", "tg_cone", "tg_blast",
        "tg_burst", "tg_deployable", "tg_drone", "tg_full_action", "tg_mine",
        "tg_mod", "tg_protocol", "tg_invade", "tg_full_tech", "tg_range",
        "tg_modded", "tg_resistance", "tg_recharge", "tg_unlimited",
        "tg_set_damage_value", "tg_efficient", "tg_threat"
    };

    public DataTag(String id, String name, String description,
        boolean filterIgnore, boolean hidden) {
        setID(id);
        setName(name);
        setDescription(description);
        setFilterIgnore(filterIgnore);
        setHidden(hidden);
        setAcceptsValue(calculateAcceptsValue());
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isFilterIgnore() {
        return filterIgnore;
    }
    public boolean isHidden() {
        return hidden;
    }
    public TriState acceptsValue() {
        return acceptsValue;
    }
    /**
     * Sets this.id to the provided value.
     * @param id a String which can be any String except "". Cannot be null.
     * @throws IllegalArgumentException if id is null or "".
     */
    protected void setID(String id) {
        HelperMethods.checkString("New ID", id);
        id = id.toLowerCase();
        this.id = id;
    }
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    protected void setDescription(String description) {
        HelperMethods.checkString("New description", name);
        this.description = description;
    }
    protected void setFilterIgnore(boolean filterIgnore) {
        this.filterIgnore = filterIgnore;
    }
    protected void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    protected void setAcceptsValue(TriState acceptsValue) {
        HelperMethods.checkObject("acceptsValue", acceptsValue);
        this.acceptsValue = acceptsValue;
    }

    private TriState calculateAcceptsValue() {
        for (String valueID : DataTag.valueIDs) {
            if (this.id.equals(valueID)) {
                return TriState.TRUE;
            }
        }
        for (String dataTagID : DataTag.dataTagIDs) {
            if (this.id.equals(dataTagID)) {
                return TriState.FALSE;
            }
        }

        return TriState.UNSET;
    }
    public Tag toTag() {
        if (this.hidden) {
            throw new IllegalArgumentException("Unable to convert a DataTag"
                + " with a DataTag.hidden value of true to a Tag");
        }

        return new Tag(this.id, this.name, this.description, this.filterIgnore);
    }
    public static Tag[] toTags(DataTag[] dataTags) {
        int numValidTags = 0;
        Tag[] tags;

        HelperMethods.checkObject("dataTags", dataTags);
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            numValidTags++;
        }
        tags = new Tag[numValidTags];
        numValidTags = 0;
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            tags[numValidTags] = dataTags[i].toTag();
            numValidTags++;
        }

        return tags;
    }
}
