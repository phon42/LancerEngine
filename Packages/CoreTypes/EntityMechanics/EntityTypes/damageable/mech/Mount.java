package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount.MountType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Talent.Talent;

/**
 * See pg. 32.
 */
/**
 * Represents a single mount on a mech. Contains information such as what type
 *     this mount is, what weapon is mounted on it (if there is one), and its
 *     modifications, core bonuses, and talents.
 * 
 * Requires a mount type to be instantiated.
 * 
 * Used in Frame and Mech.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public final class Mount {
    // Required properties
    /**
     * The mount's type (i.e. a MountType representing an aux mount).
     * Can be any MountType. Cannot be null.
     */
    private MountType mountType;
    /**
     * Whether the mount has any modifications.
     * Controlled automatically by Mount.setModification().
     */
    private boolean hasModification;
    /**
     * Whether the mount is from or is affected by any core bonuses.
     * Controlled automatically by Mount.setCoreBonus().
     */
    private boolean hasCoreBonus;
    /**
     * Whether the mount is from or is affected by any talents (at present this
     *     can only be Engineer or Weaponsmith).
     * Controlled automatically by Mount.setTalent().
     */
    private boolean hasTalent;

    // Optional properties
    /**
     * The mount's weapon (if it has one).
     * Can be any Weapon. Can be null.
     */
    private Weapon weapon;
    /**
     * The modifications applied to the mount (i.e. "molten wreathe"), if there
     *     are any.
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String modification;
    /**
     * The core bonuses applied to the mount (i.e.
     *     "auto-stabilizing hardpoints"), if there are any.
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String coreBonus;
    /**
     * The talents applied to the mount (i.e. the Engineer talent), if there are
     *     any.
     * Can be any Talent. Can be null.
     */
    private Talent talent;

    /**
     * Creates a new empty Mount with the provided mountType.
     */
    public Mount(
        // Required properties
        MountType mountType
    ) {
        this(mountType, null, null, null,
            null);
    }
    /**
     * Creates a new occupied Mount with the provided mountType and weapon.
     */
    public Mount(
        // Required properties
        MountType mountType,
        // Optional properties
        Weapon weapon
    ) {
        this(mountType, weapon, null, null,
            null);
        if (weapon == null) {
            throw new IllegalArgumentException("Called Mount(MountType, null)."
                + " Use Mount(MountType) instead");
        }
    }
    /**
     * Creates a new occupied Mount with the provided mountType, weapon, and
     *     modification.
     */
    public Mount(
        // Required properties
        MountType mountType,
        // Optional properties
        Weapon weapon, String modification) {
        this(mountType, weapon, modification, null, null);
        if (modification == null) {
            throw new IllegalArgumentException("Called Mount(MountType, Weapon,"
                + " null). Use Mount(MountType, Weapon) instead");
        }
    }
    /**
     * Creates a new occupied Mount with the provided mountType, weapon,
     *     modification, and coreBonus.
     */
    public Mount(
        // Required properties
        MountType mountType,
        // Optional properties
        Weapon weapon, String modification, String coreBonus) {
        this(mountType, weapon, modification, coreBonus, null);
        if (coreBonus == null) {
            throw new IllegalArgumentException("Called Mount(MountType, Weapon,"
                + " String, null). Use Mount(MountType, Weapon, String)"
                + " instead");
        }
    }
    /**
     * Creates a new occupied Mount with the provided mountType, weapon,
     *     modification, coreBonus, and talent.
     */
    public Mount(
        // Required properties
        MountType mountType,
        // Optional properties
        Weapon weapon, String modification, String coreBonus, Talent talent
    ) {
        if (talent == null) {
            throw new IllegalArgumentException("Called Mount(MountType, Weapon,"
                + " String, String, null). Use Mount(MountType, Weapon, String,"
                + " String) instead");
        }
        // Required properties
        setMountType(mountType);
        // Optional properties
        setWeapon(weapon);
        setModification(modification);
        setCoreBonus(coreBonus);
        setTalent(talent);
    }
    /**
     * Creates a deepest copy of the provided Mount.
     * @param mount a Mount to be copied.
     * @return a Mount deepest copy of the provided Mount.
     */
    public Mount(Mount mount) {
        // don't need to make copies of these because the mutators already do so
        this(mount.mountType, mount.weapon, mount.modification, mount.coreBonus,
            mount.talent);
    }

    public MountType getMountType() {
        return mountType;
    }
    public Weapon getWeapon() {
        if (weapon == null) {
            return weapon;
        }

        return weapon;
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
     * @param mountType a MountType that can be any MountType. Cannot be null.
     * @throws IllegalArgumentException if mountType is null.
     */
    public void setMountType(MountType mountType) {
        HelperMethods.checkObject("New mount type", mountType);
        this.mountType = mountType;
    }
    public void setWeapon(Weapon weapon) {
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
            hasCoreBonus = false;
        } else {
            HelperMethods.checkString("coreBonus", coreBonus);
            coreBonus = coreBonus.toLowerCase();
            this.coreBonus = coreBonus;
            hasCoreBonus = true;
        }
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
        this.hasTalent = (talent != null);
        this.talent = talent;
    }

    /**
     * Checks whether this Mount has object has its Mount.modification,
     *     Mount.coreBonus, or Mount.talent properties set to anything other
     *     than their construction values.
     * @return a boolean representing the result of the check.
     */
    public boolean isUnmodified() {
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
     * A helper method which generates a snippet of text containing output
     *     about this mount used in Mech.outputWeapons(). Only returns something
     *     other than "" when outputType is "mech build" or "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing output of the mount and any weapons mounted
     *     on it, as well as any modifications or core bonuses.
     */
    public String outputWeapon(String outputType) {
        // Outputs a snippet of text in the style of:
        //     "MAIN MOUNT: Assault Rifle (BOUNDER-Class Comp/Con) // Overpower"
        //     " Caliber"
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (! (outputType.equals("mech build")
            || outputType.equals("full"))) {
            return outputString;
        }
        if (this.mountType.isBaseType()) {
            outputString += this.mountType.getName().toUpperCase();
            outputString += " MOUNT";
        } else if (this.mountType.getName()
            .equals("integrated weapon")) {
            outputString += "Integrated";
        } else if (this.mountType.getName()
            .equals("integrated weapon core bonus")) {
            outputString += "INTEGRATED WEAPON";
        } else if (this.mountType.getName()
            .equals("improved armament core bonus")) {
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