/**
 * Represents a single weapon to be mounted on a mount. Contains information
 *     about that weapon such as its name, size, and tags.
 * 
 * Requires a weapon name and weapon size to be instantiated.
 * 
 * Used in Mount.
 * 
 * Safety: This class has placeholder values and can be a placeholder. None of
 *     its properties have allowed values of null.
 */
public final class Weapon extends Equipment {
    // TODO: fill out with some kind of way to attack
    // TODO: fucking deal with Ushabti Omnigun somehow
    /**
     * Contains an array of allowed values for Weapon.tags' EquipmentTag.name
     *     values.
     */
    public static final String[] allowedNames = new String[] {"Accurate",
        "Armor-Piercing (AP)", "Loading", "Ordnance", "Reliable X",
        "Inaccurate", "Arcing", "Smart", "Overkill", "Thrown X",
        "Heat X (Self)", "Knockback X", "Limited X", "Seeking", "Unique",
        "Reaction", "Drone", "Protocol", "Full Action", "Deployable",
        "Quick Action", "X/Round", "Danger Zone", "AI"};
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
     */
    private int type;

    /**
     * Creates a new Weapon given a weapon name, weapon size, and weapon type.
     * @param weaponName a String which cannot be null or "".
     * @param weaponSize an int which must be between 0 and 4 (inclusive).
     * @param weaponType an int which must be between 0 and 9 (inclusive).
     */
    public Weapon(String weaponName, int weaponSize, int weaponType) {
        super(weaponName);
        setSize(weaponSize);
        setType(weaponType);
    }
    /**
     * Creates a new Weapon given a weapon name, weapon size, weapon type, and
     *     an array of weapon tags.
     * @param weaponName a String which cannot be null or "".
     * @param weaponSize an int which must be between 0 and 4 (inclusive).
     * @param weaponType an int which must be between 0 and 9 (inclusive).
     * @param weaponTags an EquipmentTag[] which cannot be null, contain null
     *     elements, or elements with invalid EquipmentTag.name values, as
     *     defined by Weapon.allowedNames.
     */
    public Weapon(String weaponName, int weaponSize, int weaponType,
        EquipmentTag[] weaponTags) {
        this(weaponName, weaponSize, weaponType);
        setTags(weaponTags);
    }
    /**
     * Creates a deep copy of the provided Weapon.
     * @param weapon a Weapon to be copied.
     * @return a Weapon deep copy of the provided Weapon.
     */
    public Weapon(Weapon weapon) {
        // don't need to make copies of this.tags because the mutator
        //     (Equipment.setTags()) called by Weapon(String, int,
        //     EquipmentTag[]) already does so
        this(weapon.name, weapon.size, weapon.type, weapon.tags);
    }

    public int getSize() {
        return size;
    }
    public int getType() {
        return type;
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
    /**
     * Sets this.tags to the provided value.
     * @param tags an EquipmentTag[] which cannot be null, contain null
     *     elements, or contain EquipmentTags with invalid EquipmentTag.name
     *     values as defined by Weapon.allowedNames.
     * @throws IllegalArgumentException if tags includes an element with an
     *     invalid EquipmentTag.name for a Weapon, as defined by
     *     Weapon.allowedNames.
     */
    @Override
    protected void setTags(EquipmentTag[] tags) {
        boolean isValid = false;

        // Throws an IllegalArgumentException if tags is null or contains null
        //     elements
        checkTagsArray(tags);
        for (EquipmentTag tag : tags) {
            isValid = false;
            for (String allowedTag : Weapon.allowedNames) {
                if (tag.getName().equals(allowedTag)) {
                    isValid = true;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes an"
                    + " EquipmentTag with an invalid tag name for a Weapon: \""
                    + tag.getName() + "\"");
            }
        }
        tags = HelperMethods.copyOf(tags);
        this.tags = tags;
    }
}