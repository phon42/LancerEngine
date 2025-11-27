package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag;

import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.iTagData.ITagDataUnhidden;

/**
 * See
 *     https://github.com/massif-press/lancer-data/blob/master/README.md#tags-tagsjson.
 */
/**
 * Represents a tag on a JSON file for a mech system or weapon (i.e. the tags
 *     "AI" or "Grenade"). These tags are not always publicly visible. Contains
 *     information about the tag's id, name, and description, among other
 *     properties.
 * 
 * Requires a data tag id, name, description, whether it is a hidden data tag,
 *     and whether it is ignored in filters to be instantiated.
 * 
 * Used in Database and ITagData.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class ITagData {
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
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    protected VueHTMLString description;
    /**
     * Whether this data tag requires a value of some kind.
     * Is TriState.TRUE if this.id is present in DataTag.valueIDs.
     * Is TriState.FALSE if this.id is not present in DataTag.valueIDs but
     *     present in DataTag.dataTagIDs.
     * Is TriState.UNSET if this.id is not present in DataTag.dataTagIDs.
     * Can be any TriState. Cannot be null.
     */
    protected TriState acceptsValue;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Whether this data tag is always hidden.
     */
    protected boolean hidden;
    /**
     * The default value for DataTag.hidden.
     */
    private static final boolean hiddenDefault = false;
    /**
     * Whether filtering algorithms should hide this data tag.
     */
    protected boolean filterIgnore;
    /**
     * The default value for DataTag.filterIgnore.
     */
    private static final boolean filterIgnoreDefault = false;

    // Helper properties
    /**
     * Contains an array of values for this.id for which this data tag requires
     *     an accompanying value of some kind.
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
     * Contains an array of known data tag IDs.
     * Elements are case-insensitive and stored in lowercase.
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

    public ITagData(String id, String name, String description,
        TriState filterIgnore, TriState hidden) {
        HelperMethods.verifyConstructor();
        // Required properties
        setID(id);
        setName(name);
        setDescription(description);
        setAcceptsValue(calculateAcceptsValue());
        // Semi-required properties
        setFilterIgnore(filterIgnore);
        setHidden(hidden);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public VueHTMLString getDescription() {
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
    protected void setDescription(VueHTMLString description) {
        HelperMethods.checkVueHTMLString("New description",
            description);
        this.description = description;
    }
    protected void setFilterIgnore(TriState filterIgnore) {
        if (filterIgnore == TriState.UNSET) {
            this.filterIgnore = ITagData.filterIgnoreDefault;
        } else {
            this.filterIgnore = filterIgnore.toBoolean();
        }
    }
    protected void setHidden(TriState hidden) {
        if (hidden == TriState.UNSET) {
            this.hidden = ITagData.hiddenDefault;
        } else {
            this.hidden = hidden.toBoolean();
        }
    }
    protected void setAcceptsValue(TriState acceptsValue) {
        HelperMethods.checkObject("acceptsValue", acceptsValue);
        this.acceptsValue = acceptsValue;
    }

    protected void setDescription(String description) {
        setDescription(new VueHTMLString(description));
    }
    private TriState calculateAcceptsValue() {
        for (String valueID : ITagData.valueIDs) {
            if (this.id.equals(valueID)) {
                return TriState.TRUE;
            }
        }
        for (String dataTagID : ITagData.dataTagIDs) {
            if (this.id.equals(dataTagID)) {
                return TriState.FALSE;
            }
        }

        return TriState.UNSET;
    }
    public ITagDataUnhidden toUnhiddenTag() throws IllegalArgumentException {
        if (this.hidden) {
            throw new IllegalArgumentException("Unable to convert an ITagData"
                + " with a ITagData.hidden value of true to a"
                + " ITagDataUnhidden");
        }

        return new ITagDataUnhidden(
            this.id, this.name, this.description.toString(),
            TriState.toTriState(this.filterIgnore)
        );
    }
    public static ITagDataUnhidden[] toUnhiddenTags(ITagData[] dataTags) {
        int numValidTags = 0;
        ITagDataUnhidden[] tags;

        HelperMethods.checkObject("dataTags", dataTags);
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            numValidTags++;
        }
        tags = new ITagDataUnhidden[numValidTags];
        numValidTags = 0;
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            tags[numValidTags] = dataTags[i].toUnhiddenTag();
            numValidTags++;
        }

        return tags;
    }
}
