package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.WeaponType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.dataTag.Tag;
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
     * The weapon's size (i.e. a WeaponSize representing a main weapon).
     * Can be any WeaponSize. Cannot be null.
     */
    private WeaponSize size;
    /**
     * The weapon's type (i.e. a WeaponType representing a CQB weapon).
     * Can be any WeaponType. Cannot be null.
     */
    private WeaponType type;
    /**
     * The weapon's damage, including heat and burn (i.e. a Harm[] containing
     *     a Harm representing 10 Kinetic, 2 Heat, or 3 Burn).
     * Can be any Harm[] that does not contain null or null elements. Cannot be
     *     null.
     */
    private Harm[] harm;
    // TODO: make sure to account for multiple possible ranges
    /**
     * The weapon's range (i.e. a RangeTag[] containing a RangeTag representing
     *     Range 10).
     * Can be any RangeTag[] that does not contain null or null elements. Cannot
     *     be null.
     */
    private RangeTag[] range;

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
     * @param type an int which must be between 0 and 9 (inclusive).
     * @param harmDealt a Harm[] which cannot be null or contain null elements.
     * @param range a RangeTag[] which cannot be null or contain null elements.
     */
    public Weapon(String name, String manufacturer, License weaponLicense,
        WeaponSize size, int type, Harm[] harmDealt, RangeTag[] range) {
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
     * @param type an int which must be between 0 and 9 (inclusive).
     * @param harmDealt a Harm[] which cannot be null or contain null elements.
     * @param range a RangeTag[] which cannot be null or contain null elements.
     * @param dataTags a DataTag[] which cannot be null, contain null elements,
     *     or elements with invalid DataTag.name values, as defined by
     *     Weapon.allowedNames.
     */
    public Weapon(String name, String manufacturer, License weaponLicense,
        WeaponSize size, int type, Harm[] harmDealt, RangeTag[] range,
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

        HelperMethods.checkObjectArray("dataTags", dataTags);
        for (DataTag tag : dataTags) {
            isValid = false;
            for (String allowedTag : Weapon.allowedNames) {
                if (tag.getName().equals(allowedTag)) {
                    isValid = true;
                    break;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes a"
                    + " Tag with an invalid tag name for a Weapon: \""
                    + tag.getName() + "\"");
            }
        }
        dataTags = HelperMethods.copyOf(dataTags);
        this.dataTags = dataTags;
    }
}