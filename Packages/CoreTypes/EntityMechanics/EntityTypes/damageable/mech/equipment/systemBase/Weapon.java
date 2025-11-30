package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.CounterData;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.SystemBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType.WeaponType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.weapon.IWeaponProfile;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount.MountType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;

/**
 * See pgs. 33 and 104.
 */
/**
 * Represents a single weapon to be mounted on a mount. Contains information
 *     about that weapon such as its name, size, and data tags.
 * 
 * Requires a weapon name and weapon size to be instantiated.
 * 
 * Used in Mount.
 * 
 * Safety: This class has placeholder values and can be a placeholder. At least
 *     one of its properties has an allowed value of null.
 */
public final class Weapon extends SystemBase {
    // TODO: fill out with some kind of way to attack
    // TODO: fucking deal with Ushabti Omnigun somehow
    // Required properties
    private MountType mount;
    /**
     * The weapon's type (i.e. a WeaponType representing a CQB weapon).
     * Can be any WeaponType. Cannot be null.
     */
    private WeaponType weaponType;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    private int cost;
    private boolean barrage;
    private boolean skirmish;
    private boolean noAttack;
    private boolean noMods;
    private boolean noCoreBonus;
    private boolean noBonus;
    private boolean noSynergy;
    // TODO: make sure to account for multiple possible ranges
    /**
     * The weapon's range (i.e. a RangeTag[] containing a RangeTag representing
     *     Range 10).
     * Can be any RangeTag[] that is not of length 0 or contains null elements.
     *     Cannot be null.
     * Default value: a RangeTag[] containing a single RangeTag representing
     *     Threat 1.
     * 
     * If passed a RangeTag[] that is null or of length 0, sets this.range to
     *     the default value.
     */
    private RangeTag[] range;
    // rangeDefault removed because the relevant value only becomes accessible
    //     at runtime

    // Optional properties
    /**
     * The weapon's damage, including heat and burn (i.e. a Harm[] containing
     *     a Harm representing 10 Kinetic, 2 Heat, or 3 Burn).
     * Can be any Harm[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    private Harm[] damage;
    private VueHTMLString effect;
    private VueHTMLString onAttack;
    private VueHTMLString onHit;
    private VueHTMLString onCrit;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private IDeployableData[] deployables;
    private CounterData[] counters;
    private String[] integrated;
    private String[] special_equipment;
    private IWeaponProfile[] profiles;

    // Helper properties
    /**
     * The weapon's size (i.e. a WeaponSize representing a main weapon).
     * Can be any WeaponSize. Cannot be null.
     */
    private WeaponSize size;

    // Reference properties
    /**
     * Contains an array of allowed values for Weapon.dataTags' DataTag.name
     *     values.
     * Case-sensitive.
     */
    public static final String[] allowedNames = new String[] {"Accurate",
        "Armor-Piercing (AP)", "Loading", "Ordnance", "Reliable X",
        "Inaccurate", "Arcing", "Smart", "Overkill", "Thrown X",
        "Heat X (Self)", "Knockback X", "Limited X", "Seeking", "Unique",
        "Reaction", "Drone", "Protocol", "Full Action", "Deployable",
        "Quick Action", "X/Round", "Danger Zone", "AI", "Free Action"};

    /**
     * Verbose constructor for non-GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags, int spCost
    ) {
        // SystemBase properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, null, spCost);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags, int spCost
    ) {
        // SystemBase properties
        super(id, name, licenseID, licenseName, description, dataTags,
            null, spCost);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, -1);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags
    ) {
        this(id, name, licenseID, licenseName, description, dataTags, -1);
    }

    // Required properties
    public WeaponType getWeaponType() {
        return weaponType;
    }
    public Harm[] getHarm() {
        return harm;
    }
    public RangeTag[] getRange() {
        return range;
    }
    // Semi-required properties
    // Optional properties
    // Helper properties
    public WeaponSize getSize() {
        return size;
    }
    // Inherited properties
    /**
     * Sets this.dataTags to the provided value.
     * @param dataTags a DataTag[] which cannot be null, contain null elements,
     *     or contain DataTags with invalid DataTag.name values as defined by
     *     Weapon.allowedNames.
     * @throws IllegalArgumentException if dataTags includes an element with an
     *     invalid DataTag.name value for a Weapon, as defined by
     *     Weapon.allowedNames.
     */
    @Override
    protected void setDataTags(DataTag[] dataTags) {
        boolean isValid;
        String dataTagName;

        HelperMethods.checkObjectArray("dataTags", dataTags);
        for (DataTag tag : dataTags) {
            isValid = false;
            dataTagName = tag.getData().getName();
            for (String allowedTag : Weapon.allowedNames) {
                if (dataTagName.equals(allowedTag)) {
                    isValid = true;
                    break;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes a"
                    + " Tag with an invalid tag name for a Weapon: \""
                    + dataTagName + "\"");
            }
        }
        dataTags = HelperMethods.copyOf(dataTags);
        this.dataTags = dataTags;
    }
    @Override
    protected void setType(SystemType type) {
        if (type == null) {
            type = Database.getSystemType("Weapon");
        }
        this.type = type;
    }
    // Required properties
    private void setWeaponType(WeaponType weaponType) {
        HelperMethods.checkObject("weaponType", weaponType);
        this.weaponType = weaponType;
    }
    private void setHarm(Harm[] harm) {
        HelperMethods.checkObjectArray("harm", harm);
        if (harm.length < 1) {
            throw new IllegalArgumentException("harm.length value: "
                + harm.length + " is < 1");
        }
        harm = HelperMethods.copyOf(harm);
        this.harm = harm;
    }
    private void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        if (range.length < 1) {
            throw new IllegalArgumentException("range.length value: "
                + range.length + " is < 1");
        }
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    // Semi-required properties
    // Optional properties
    // Helper properties
    private void setSize(WeaponSize size) {
        HelperMethods.checkObject("size", size);
        this.size = size;
    }
}