/**
 * Represents a single mount on a mech. Contains information such as what type
 *     this mount is, what weapon is mounted on it (if there is one), whether it
 *     has any modifications and whether there is a core bonus attached to it.
 */
public class Mount {
    private final String[] allowedMountTypes = {
        "aux", "aux/aux", "flex", "heavy", "integrated weapon", "main",
        "main/aux"
    };
    /**
     * String with allowed values:
     *     "aux", "aux/aux", "flex", "heavy", "integrated weapon", "main",
     *     "main/aux"
     * Cannot be null.
     */
    private String mountType;
    /**
     * The mount's weapon (if it has one). Can be any Weapon, though
     *     new Weapon() is a placeholder. Cannot be null.
     */
    private Weapon weapon;
    /**
     * Whether the mount has any modifications. Controlled automatically by
     *     Mount.setModification().
     */
    private boolean hasModification;
    /**
     * Can be any String. Cannot be null.
     */
    private String modification;
    private boolean hasCoreBonus;
    /**
     * Can be any String. Cannot be null.
     */
    private String coreBonus;

    private Mount() {
        this.mountType = "";
        setWeapon(new Weapon());
        setModification("");
        setCoreBonus("");
    }
    public Mount(Weapon weapon) {
        this();
        setWeapon(weapon);
    }
    public Mount(Weapon weapon, String modification) {
        this(weapon, modification, "");
    }
    public Mount(Weapon weapon, String modification, String coreBonus) {
        this.mountType = "";
        setWeapon(weapon);
        setModification(modification);
        setCoreBonus(coreBonus);
    }
    public Mount(String mountType, Weapon weapon) {
        this();
        setMountType(mountType);
        setWeapon(weapon);
    }
    public Mount(String mountType, Weapon weapon, String modification) {
        this(mountType, weapon, modification, "");
    }
    public Mount(String mountType, Weapon weapon, String modification,
        String coreBonus) {
        setMountType(mountType);
        setWeapon(weapon);
        setModification(modification);
        setCoreBonus(coreBonus);
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
    /**
     * Sets this.mountType to the value provided.
     * @param mountType a String that cannot be null or be anything outside of
     *     this.allowedMountTypes.
     */
    public void setMountType(String mountType) {
        boolean isValidType = false;
        String exceptionMessage = "Given mount type is not one of the allowed"
            + " mount types:\n\"aux\", \"aux/aux\", \"flex\", \"heavy\","
            + " \"integrated weapon\", \"main\", \"main/aux\"";

        if (mountType == null) {
            throw new IllegalArgumentException("New mount type is null");
        }
        mountType = mountType.toLowerCase();
        for (int i = 0; i < allowedMountTypes.length; i++) {
            if (allowedMountTypes[i].equals(mountType)) {
                isValidType = true;
                break;
            }
        }
        if (! isValidType) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.mountType = mountType;
    }
    /**
     * Sets this.weapon to the value provided.
     * @param weapon a Weapon that cannot be null and must EITHER be a
     *     placeholder or not have any placeholders.
     */
    public void setWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("New weapon is null");
        }
        if (! weapon.isPlaceholder() && weapon.hasPlaceholders()) {
            throw new IllegalArgumentException("New weapon value is not a"
                + " placeholder but has some properties set to placeholder"
                + " values");
        }
        weapon = weapon.copyOf();
        this.weapon = weapon;
    }
    // Setters for hasModification and hasCoreBonus removed purposefully
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
        this.modification = modification;
    }
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
        this.coreBonus = coreBonus;
    }

    /**
     * Outputs a short snippet of text in the style of:
     *     "MAIN MOUNT: Assault Rifle (BOUNDER-Class Comp/Con)"
     *     " // Overpower Caliber"
     * @return a String containing output of the mount and any weapons mounted
     *     on it, as well as any modifications or core bonuses.
     */
    public String outputWeapon() {
        String outputString = "";

        outputString += mountType.toUpperCase();
        if (! mountType.equals("integrated weapon")) {
            outputString += " MOUNT";
        }
        outputString += ":";
        if (! weapon.isPlaceholder()) {
            outputString += " ";
            outputString += weapon.getName();
        }
        if (hasModification) {
            outputString += " (" + modification + ")";
        }
        if (hasCoreBonus) {
            outputString += " // " + coreBonus;
        }

        return outputString;
    }
    /**
     * Checks whether this object has all of its properties set to placeholder
     *     values.
     * @return a boolean representing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! getMountType().equals("")) {
            return false;
        }
        if (! getWeapon().isPlaceholder()) {
            return false;
        }
        if (hasModification()) {
            return false;
        }
        if (! getModification().equals("")) {
            return false;
        }
        if (hasCoreBonus()) {
            return false;
        }
        if (! getCoreBonus().equals("")) {
            return false;
        }
        
        return true;
    }
    /**
     * Returns a deepest copy of this object.
     * @return a Mount deepest copy of this object.
     */
    public Mount copyOf() {
        // don't need to make copies of these because the mutators already do so
        Mount copy = new Mount(this.mountType, this.weapon, this.modification,
            this.coreBonus);

        return copy;
    }
}