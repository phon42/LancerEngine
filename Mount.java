// TODO: change Mount.weapon to allow null and alter documentation to compensate
/**
 * Represents a single mount on a mech. Contains information such as what type
 *     this mount is, what weapon is mounted on it (if there is one), and its
 *     modifications, core bonuses, and talents.
 * 
 * Requires a mount type to be instantiated.
 * 
 * Used in Frame and Mech.
 * 
 * Safety: This class does not have placeholder values. At least one of its
 *     properties has an allowed value of null.
 */
public class Mount {
    /**
     * The mount's type.
     * Must be one of the following values:
     *     "aux", "aux/aux", "flex", "heavy", "main", "main/aux",
     *     "integrated mount", "integrated weapon",
     *     "integrated weapon core bonus",
     *     "improved armament core bonus"
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String mountType;
    /**
     * Contains an array of all possible allowed mount types.
     * Details about the base mount types:
     *     "flex" - a weapon mount which can take one main weapon OR two
     *         auxilary weapons.
     * 
     * See the documentation on Weapon.size for more information.
     * 
     * Details about the non-base mount types:
     *     "integrated mount" - for integrated weapons such as Caliban's Flayer
     *         Shotgun.
     *     "integrated weapon" - for integrated weapons such as the Prototype
     *         weapon from the Engineer talent.
     *     "integrated weapon core bonus" - for the Integrated Weapon core
     *         bonus. Essentially the same as "aux".
     *     "improved armament core bonus" - for the Improved Armament core
     *         bonus. Essentially the same as "flex".
     */
    private static final String[] allowedMountTypes = {
        "aux", "aux/aux", "flex", "heavy", "main", "main/aux",
        "integrated mount", "integrated weapon", "integrated weapon core bonus",
        "improved armament core bonus"
    };
    /**
     * Contains an array of mount types that do not require talents, core
     *     bonuses, or integrated weapons on the base frame to exist.
     */
    private static final String[] baseMountTypes = {
        "aux", "aux/aux", "flex", "heavy", "main", "main/aux"
    };
    /**
     * The mount's weapon (if it has one).
     * Can be any Weapon or null.
     */
    private Weapon weapon;
    /**
     * Whether the mount has any modifications.
     * Controlled automatically by Mount.setModification().
     */
    private boolean hasModification;
    /**
     * Any modifications applied to the mount (i.e. "molten wreathe"), if there
     *     are any.
     * Can be any String. Cannot be null. Case-insensitive and stored in
     *     lowercase.
     */
    private String modification;
    /**
     * Whether the mount is from or is affected by any core bonuses.
     * Controlled automatically by Mount.setCoreBonus().
     */
    private boolean hasCoreBonus;
    /**
     * Any core bonuses applied to the mount (i.e.
     *     "auto-stabilizing hardpoints"), if there are any.
     * Can be any String. Cannot be null. Case-insensitive and stored in
     *     lowercase.
     */
    private String coreBonus;
    /**
     * Whether the mount is from or is affected by any talents (at present this
     *     can only be Engineer or Weaponsmith).
     * Controlled automatically by Mount.setTalent().
     */
    private boolean hasTalent;
    /**
     * Any talents applied to the mount (i.e. the Engineer talent), if there are
     *     any.
     * Can be any Talent or null.
     */
    private Talent talent;

    /**
     * Creates a new Mount with the provided mountType.
     */
    public Mount(String mountType) {
        this(mountType, null, "", "",
            null);
    }
    /**
     * Creates a new Mount with the provided mountType and weapon.
     */
    public Mount(String mountType, Weapon weapon) {
        this(mountType, weapon, "", "", null);
    }
    /**
     * Creates a new Mount with the provided mountType, weapon, and
     *     modification.
     */
    public Mount(String mountType, Weapon weapon, String modification) {
        this(mountType, weapon, modification, "", null);
    }
    /**
     * Creates a new Mount with the provided mountType, weapon, modification,
     *    and coreBonus.
     */
    public Mount(String mountType, Weapon weapon, String modification,
        String coreBonus) {
        this(mountType, weapon, modification, coreBonus, null);
    }
    /**
     * Creates a new Mount with the provided mountType, weapon, modification,
     *    coreBonus, and talent.
     */
    public Mount(String mountType, Weapon weapon, String modification,
        String coreBonus, Talent talent) {
        setMountType(mountType);
        setWeapon(weapon);
        setModification(modification);
        setCoreBonus(coreBonus);
        setTalent(talent);
    }

    public String getMountType() {
        return mountType;
    }
    public Weapon getWeapon() {
        return weapon.copyOf();
    }
    public boolean hasModification() {
        return hasModification;
    }
    public String getModification() {
        return modification;
    }
    public boolean hasCoreBonus() {
        return hasCoreBonus;
    }
    public String getCoreBonus() {
        return coreBonus;
    }
    public boolean hasTalent() {
        return hasTalent;
    }
    public Talent getTalent() {
        return talent;
    }
    /**
     * Sets this.mountType to the value provided.
     * @param mountType a String that cannot be null or an invalid value, as
     *     defined by Mount.allowedMountTypes.
     * @throws IllegalArgumentException if mountType is null or an invalid
     *     value, as defined by Mount.allowedMountTypes.
     */
    public void setMountType(String mountType) {
        boolean isValidType = false;

        if (mountType == null) {
            throw new IllegalArgumentException("New mount type is null");
        }
        mountType = mountType.toLowerCase();
        for (int i = 0; i < Mount.allowedMountTypes.length; i++) {
            if (Mount.allowedMountTypes[i].equals(mountType)) {
                isValidType = true;
                break;
            }
        }
        if (! isValidType) {
            throw new IllegalArgumentException("New mount type is an invalid"
                + " role value: \"" + mountType + "\"");
        }
        this.mountType = mountType;
    }
    public void setWeapon(Weapon weapon) {
        if (weapon != null) {
            weapon = weapon.copyOf();
        }
        this.weapon = weapon;
    }
    // Mutator for hasModification removed purposefully
    /**
     * Sets this.modification to the value provided and automatically sets
     *     this.hasModification accordingly.
     * Passing an empty String will automatically set this.modification to
     *     false; anything else will set it to true.
     * @param modification a String that cannot be null.
     */
    public void setModification(String modification) {
        if (modification == null) {
            throw new IllegalArgumentException("New modification value is"
                + " null");
        }
        if (modification.equals("")) {
            hasModification = false;
        } else {
            hasModification = true;
        }
        modification = modification.toLowerCase();
        this.modification = modification;
    }
    // Mutator for hasCoreBonus removed purposefully
    /**
     * Sets this.coreBonus to the value provided and automatically sets
     *     this.hasCoreBonus accordingly.
     * Passing an empty String will automatically set this.hasCoreBonus to
     *     false; anything else will set it to true.
     * @param coreBonus a String that cannot be null.
     */
    public void setCoreBonus(String coreBonus) {
        if (coreBonus == null) {
            throw new IllegalArgumentException("New core bonus value is"
                + " null");
        }
        if (coreBonus.equals("")) {
            hasCoreBonus = false;
        } else {
            hasCoreBonus = true;
        }
        coreBonus = coreBonus.toLowerCase();
        this.coreBonus = coreBonus;
    }
    // Mutator for hasTalent removed purposefully
    /**
     * Sets this.talent to the provided value and automatically sets
     *     this.hasTalent accordingly.
     * Passing any Talent will automatically set this.hasTalent to true; passing
     *     null will set it to false.
     * @param talent a Talent that can be any Talent. Can also be null.
     */
    public void setTalent(Talent talent) {
        if (talent == null) {
            this.hasTalent = false;
        } else {
            this.hasTalent = true;
            talent = talent.copyOf();
        }
        this.talent = talent;
    }

    /**
     * Checks whether this Mount has object has its Mount.modification,
     *     Mount.coreBonus, or Mount.talent properties set to anything other
     *     than their construction values.
     * @return a boolean representing the result of the check.
     */
    public boolean isEmpty() {
        if (! modification.equals("")) {
            return false;
        }
        if (! coreBonus.equals("")) {
            return false;
        }
        if (talent != null) {
            return false;
        }
        
        return true;
    }
    /**
     * Returns a deepest copy of this Mount object.
     * @return a Mount deepest copy of this object.
     */
    public Mount copyOf() {
        // don't need to make copies of these because the mutators already do so
        Mount copy = new Mount(this.mountType, this.weapon, this.modification,
            this.coreBonus, this.talent);

        return copy;
    }
    /**
     * A helper method which generates a short snippet of text containing output
     *     about this mount used in Mech.outputWeapons(). Only returns something
     *     other than "" when outputType is "mech build" or "full".
     * Outputs a short snippet of text in the style of:
     *     "MAIN MOUNT: Assault Rifle (BOUNDER-Class Comp/Con)"
     *     " // Overpower Caliber"
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing output of the mount and any weapons mounted
     *     on it, as well as any modifications or core bonuses.
     */
    public String outputWeapon(String outputType) {
        String outputString = "";
        boolean isBaseType = false;

        outputType = outputType.toLowerCase();
        if (! (outputType.equals("mech build")
            || outputType.equals("full"))) {
            return outputString;
        }
        for (int i = 0; i < Mount.baseMountTypes.length; i++) {
            if (this.mountType.equals(Mount.baseMountTypes[i])) {
                isBaseType = true;
            }
        }
        if (isBaseType) {
            outputString += this.mountType.toUpperCase();
            outputString += " MOUNT";
        } else if (this.mountType.equals("integrated weapon")) {
            outputString += "Integrated";
        } else if (this.mountType.equals(
            "integrated weapon core bonus")) {
            outputString += "INTEGRATED WEAPON";
        } else if (this.mountType.equals(
            "improved armament core bonus")) {
            outputString += "FLEX MOUNT";
        }
        outputString += ":";
        if (this.weapon != null) {
            outputString += " ";
            outputString += this.weapon.getName();
        }
        if (this.hasModification) {
            outputString += " (" + this.modification + ")";
        }
        if (this.hasCoreBonus) {
            outputString += " // " + this.coreBonus;
        }

        return outputString;
    }
}