package packages.CoreTypes.EntityMechanics.entityTypes.mech.equipment;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.License;
import packages.CoreTypes.EntityMechanics.RangeTag;
import packages.CoreTypes.EntityMechanics.entityTypes.mech.Equipment;
import packages.CoreTypes.EntityMechanics.entityTypes.mech.equipment.tagSystem.Tag;
import packages.CoreTypes.EntityMechanics.harmSystem.Harm;

/**
 * See pgs. 33 and 104.
 */
/**
 * Represents a single weapon to be mounted on a mount. Contains information
 *     about that weapon such as its name, size, and tags.
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
     * Contains an array of allowed values for Weapon.tags' Tag.name values.
     *     Case-sensitive.
     */
    public static final String[] allowedNames = new String[] {"Accurate",
        "Armor-Piercing (AP)", "Loading", "Ordnance", "Reliable X",
        "Inaccurate", "Arcing", "Smart", "Overkill", "Thrown X",
        "Heat X (Self)", "Knockback X", "Limited X", "Seeking", "Unique",
        "Reaction", "Drone", "Protocol", "Full Action", "Deployable",
        "Quick Action", "X/Round", "Danger Zone", "AI", "Free Action"};
    /**
     * The weapon's size (i.e. Main).
     * Must be between 0 and 4 (inclusive).
     * Details on these values:
     *     0 - an aux weapon. This can go in a Mount of Mount.mountType:
     *         "aux", "aux/aux", "flex", "heavy", "main", "main/aux",
     *             "integrated weapon core bonus", or
     *             "improved armament core bonus".
     *     1 - a main weapon. This can go in a Mount of Mount.mountType:
     *         "flex", "heavy", "main", "main/aux", or
     *             "improved armament core bonus".
     *     2 - a heavy weapon. This can go in a Mount of Mount.mountType:
     *         "heavy"
     *     3 - a superheavy weapon. This can go in a Mount of Mount.mountType:
     *         "heavy"
     *         and requires an additional mount to brace against. In doing so,
     *         it consumes the ENTIRE mount - Mounts of Mount.mountType
     *         "aux/aux", "flex", or "main/aux" cannot be used as bracing AND
     *         still hold an additional weapon.
     *     4 - a ship-class weapon. This can go in a Mount of Mount.mountType:
     *         "integrated mount"
     * 
     * See pg. 33.
     */
    private int size;
    /**
     * The weapon's type (i.e. CQB).
     * Must be between 0 and 9 (inclusive).
     * Details on these values:
     *     0 - CQB
     *     1 - Cannon
     *     2 - Launcher
     *     3 - Melee
     *     4 - Nexus
     *     5 - Rifle
     *     6 - Drone Weapon (Bligh integrated weapon only)
     *     7 - Spool Weapon (Barbarossa integrated weapon only)
     *     8 - ??? (Mimic Gun only)
     *     9 - Special (Prototype Weapon only)
     * 
     * See pg. 33.
     */
    private int type;
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
     * Creates a new Weapon given a weapon name, weapon size, weapon type, and
     *     weapon harm.
     * @param weaponName a String which cannot be null or "".
     * @param weaponManufacturer a String which must be a valid manufacturer
     *     name as defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param weaponSize an int which must be between 0 and 4 (inclusive).
     * @param weaponType an int which must be between 0 and 9 (inclusive).
     * @param weaponHarm a Harm[] which cannot be null or contain null
     *     elements.
     */
    public Weapon(String weaponName, String weaponManufacturer,
        License weaponLicense, int weaponSize, int weaponType,
        Harm[] weaponHarm) {
        super(weaponName, weaponManufacturer, weaponLicense);
        setSize(weaponSize);
        setType(weaponType);
        setHarm(weaponHarm);
        setRange(new RangeTag[] {
            new RangeTag("threat", 1)
        });
    }
    /**
     * Creates a new Weapon given a weapon name, weapon size, weapon type,
     *     weapon harm, and weapon range.
     * @param weaponName a String which cannot be null or "".
     * @param weaponManufacturer a String which must be a valid manufacturer
     *     name as defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param weaponSize an int which must be between 0 and 4 (inclusive).
     * @param weaponType an int which must be between 0 and 9 (inclusive).
     * @param weaponHarm a Harm[] which cannot be null or contain null elements.
     * @param weaponRange a RangeTag[] which cannot be null or contain null
     *     elements.
     */
    public Weapon(String weaponName, String weaponManufacturer,
        License weaponLicense, int weaponSize, int weaponType,
        Harm[] weaponHarm, RangeTag[] weaponRange) {
        super(weaponName, weaponManufacturer, weaponLicense);
        setSize(weaponSize);
        setType(weaponType);
        setHarm(weaponHarm);
        setRange(weaponRange);
    }
    /**
     * Creates a new Weapon given a weapon name, weapon size, weapon type,
     *     weapon harm, weapon range, and an array of weapon tags.
     * @param weaponName a String which cannot be null or "".
     * @param weaponManufacturer a String which must be a valid manufacturer
     *     name as defined by Database.manufacturerList. Cannot be null.
     * @param weaponLicense a License which can be any License. Cannot be null.
     * @param weaponSize an int which must be between 0 and 4 (inclusive).
     * @param weaponType an int which must be between 0 and 9 (inclusive).
     * @param weaponHarm a Harm[] which cannot be null or contain null
     *     elements.
     * @param weaponRange a RangeTag[] which cannot be null or contain null
     *     elements.
     * @param weaponTags a Tag[] which cannot be null, contain null elements, or
     *     elements with invalid Tag.name values, as defined by
     *     Weapon.allowedNames.
     */
    public Weapon(String weaponName, String weaponManufacturer,
        License weaponLicense, int weaponSize, int weaponType,
        Harm[] weaponHarm, RangeTag[] weaponRange, Tag[] weaponTags) {
        this(weaponName, weaponManufacturer, weaponLicense, weaponSize,
            weaponType, weaponHarm, weaponRange);
        setTags(weaponTags);
    }
    /**
     * Creates a deep copy of the provided Weapon.
     * @param weapon a Weapon to be copied.
     * @return a Weapon deep copy of the provided Weapon.
     */
    public Weapon(Weapon weapon) {
        // don't need to make copies of this.tags because the mutator
        //     (Equipment.setTags()) called by Weapon(String, int, Tag[])
        //     already does so
        this(weapon.name, weapon.manufacturer, weapon.license, weapon.size,
            weapon.type, weapon.harm, weapon.range, weapon.tags);
    }

    public int getSize() {
        return size;
    }
    public int getType() {
        return type;
    }
    public Harm[] getHarm() {
        return harm;
    }
    public RangeTag[] getRange() {
        return range;
    }
    /**
     * Sets this.size to the provided value.
     * @param size an int which must be between 0 and 4 (inclusive).
     * @throws IllegalArgumentException if size is not between 0 and 4
     *     (inclusive).
     */
    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("New size value: " + size + " is"
                + " < 0");
        }
        if (size > 4) {
            throw new IllegalArgumentException("New size value: " + size + " is"
                + " > 4");
        }
        this.size = size;
    }
    /**
     * Sets this.type to the provided value.
     * @param type an int which must be between 0 and 9 (inclusive).
     * @throws IllegalArgumentException if type is not between 0 and 9
     *     (inclusive).
     */
    private void setType(int type) {
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
     * Sets this.tags to the provided value.
     * @param tags a Tag[] which cannot be null, contain null elements, or
     *     contain Tags with invalid Tag.name values as defined by
     *     Weapon.allowedNames.
     * @throws IllegalArgumentException if tags includes an element with an
     *     invalid Tag.name for a Weapon, as defined by Weapon.allowedNames.
     */
    @Override
    protected void setTags(Tag[] tags) {
        boolean isValid;

        // Throws an IllegalArgumentException if tags is null or contains null
        //     elements
        checkTagsArray(tags);
        for (Tag tag : tags) {
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
        tags = HelperMethods.copyOf(tags);
        this.tags = tags;
    }
}