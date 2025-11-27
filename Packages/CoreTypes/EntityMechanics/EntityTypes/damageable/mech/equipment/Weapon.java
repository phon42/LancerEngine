package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.WeaponType;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.weapon.IWeaponProfile;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;

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
public final class Weapon extends Equipment {
    // TODO: fill out with some kind of way to attack
    // TODO: fucking deal with Ushabti Omnigun somehow
    // Required properties
    /**
     * The weapon's size (i.e. a WeaponSize representing a main weapon).
     * Can be any WeaponSize. Cannot be null.
     */
    private WeaponSize size;
    /**
     * The weapon's type (i.e. a WeaponType representing a CQB weapon).
     * Can be any WeaponType. Cannot be null.
     */
    private WeaponType type;

    // Semi-required properties
    private boolean barrage;
    private boolean skirmish;
    private boolean noAttack;
    private boolean noMods;
    private boolean noCoreBonus;
    private boolean noBonus;
    private boolean noSynergy;

    // Optional properties
    /**
     * Must be a minimum of 0.
     */
    private int cost;
    /**
     * The weapon's damage, including heat and burn (i.e. a Harm[] containing
     *     a Harm representing 10 Kinetic, 2 Heat, or 3 Burn).
     * Can be any Harm[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    private Harm[] harm;
    // TODO: make sure to account for multiple possible ranges
    /**
     * The weapon's range (i.e. a RangeTag[] containing a RangeTag representing
     *     Range 10).
     * Can be any RangeTag[] that is not of length 0 or contains null elements.
     *     Can be null.
     */
    private RangeTag[] range;
    private int systemPoints;
    private VueHTMLString effect;
    private VueHTMLString onAttack;
    private VueHTMLString onHit;
    private VueHTMLString onCrit;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private IDeployableData[] deployables;
    private Counter[] counters;
    private String[] integrated;
    private String[] specialEquipment;
    private IWeaponProfile[] profiles;

    // Helper properties
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
     * Creates a new Weapon given a weapon name, manufacturer, the license the
     *     weapon belongs to, a weapon size, type, and the amount of harm dealt.
     * @param name a String which cannot be null or "".
     * @param manufacturer a String which must be a valid manufacturer name as
     *     defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param size a WeaponSize which can be any WeaponSize. Cannot be null.
     * @param type a WeaponType which can be any WeaponType. Cannot be null.
     * @param harmDealt a Harm[] which cannot be null or contain null elements.
     */
    public Weapon(String name, String manufacturer, License weaponLicense,
        WeaponSize size, WeaponType type, Harm[] harmDealt) {
        super(name, manufacturer, weaponLicense);
        setSize(size);
        setType(type);
        setHarm(harmDealt);
        setRange(new RangeTag[] {
            new RangeTag("threat", 1)
        });
    }
    /**
     * Creates a new Weapon given a weapon name, manufacturer, the license the
     *     weapon belongs to, a weapon size, type, the amount of harm dealt, and
     *     the weapon's range.
     * @param name a String which cannot be null or "".
     * @param manufacturer a String which must be a valid manufacturer name as
     *     defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param size a WeaponSize which can be any WeaponSize. Cannot be null.
     * @param type a WeaponType which can be any WeaponType. Cannot be null.
     * @param harmDealt a Harm[] which cannot be null or contain null elements.
     * @param range a RangeTag[] which cannot be null or contain null elements.
     */
    public Weapon(String name, String manufacturer, License weaponLicense,
        WeaponSize size, WeaponType type, Harm[] harmDealt, RangeTag[] range) {
        this(name, manufacturer, weaponLicense, size, type, harmDealt);
        setRange(range);
    }
    /**
     * Creates a new Weapon given a weapon name, manufacturer, the license the
     *     weapon belongs to, a weapon size, type, the amount of harm dealt, the
     *     weapon's range, and an array of data tags associated with it.
     * @param name a String which cannot be null or "".
     * @param manufacturer a String which must be a valid manufacturer name as
     *     defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param size a WeaponSize which can be any WeaponSize. Cannot be null.
     * @param type a WeaponType which can be any WeaponType. Cannot be null.
     * @param harmDealt a Harm[] which cannot be null or contain null elements.
     * @param range a RangeTag[] which cannot be null or contain null elements.
     * @param dataTags a DataTag[] which cannot be null, contain null elements,
     *     or elements with invalid DataTag.name values, as defined by
     *     Weapon.allowedNames.
     */
    public Weapon(String name, String manufacturer, License weaponLicense,
        WeaponSize size, WeaponType type, Harm[] harmDealt, RangeTag[] range,
        DataTag[] dataTags) {
        this(name, manufacturer, weaponLicense, size, type, harmDealt, range);
        setDataTags(dataTags);
    }

    public WeaponSize getSize() {
        return size;
    }
    public WeaponType getType() {
        return type;
    }
    public Harm[] getHarm() {
        return harm;
    }
    public RangeTag[] getRange() {
        return range;
    }
    private void setSize(WeaponSize size) {
        HelperMethods.checkObject("size", size);
        this.size = size;
    }
    private void setType(WeaponType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    public void setHarm(Harm[] harm) {
        HelperMethods.checkObjectArray("harm", harm);
        if (harm.length < 1) {
            throw new IllegalArgumentException("harm.length value: "
                + harm.length + " is < 1");
        }
        harm = HelperMethods.copyOf(harm);
        this.harm = harm;
    }
    public void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        if (range.length < 1) {
            throw new IllegalArgumentException("range.length value: "
                + range.length + " is < 1");
        }
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
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
}