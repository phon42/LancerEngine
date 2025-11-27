package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.WeaponSize;

public class MountType {
    // Static properties
    private static int maxID = 0;

    // Required properties
    /**
     * The mount type's int id (i.e. 0 for regular mount).
     * Can be any int.
     * 
     * Currently used values:
     *     0 - Aux
     *         Accepts a single Aux weapon.
     *     1 - Aux/Aux
     *         Accepts two Aux weapons.
     *     2 - Main
     *         Accepts a single Main weapon.
     *     3 - Flex
     *         Accepts EITHER a single Main weapon OR two Aux weapons.
     *     4 - Main/Aux
     *         Accepts a single Main weapon and a single Aux weapon.
     *     5 - Heavy
     *         Accepts a single Heavy weapon.
     *     6 - Integrated Mount
     *         Accepts a single weapon of any size.
     *     7 - Integrated Weapon
     *         Accepts a single weapon of any size.
     *     8 - Integrated Weapon Core Bonus
     *         Accepts a single Aux weapon.
     *     9 - Improved Armament Core Bonus
     *         Accepts EITHER a single Main weapon OR two Aux weapons.
     */
    private int id;
    /**
     * The mount type's name (i.e. "aux").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase. Cannot be null.
     * 
     * Use mountType.getName() to get the raw value and mountType.outputName()
     *     to obtain it properly formatted.
     * 
     * Currently used values:
     *     "aux", "aux/aux", "main", "flex", "main/aux", "heavy",
     *     "integrated mount", "integrated weapon",
     *     "integrated weapon core bonus",
     *     "improved armament core bonus"
     */
    private String name;

    // Optional properties
    /**
     * An array of weapon sizes that can be mounted on this mount; essentially,
     *     a whitelist.
     * Can be any WeaponSize[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     * 
     * When this.allowedSizes and this.restrictedSizes are both null, this mount
     *     type does not accept any weapon sizes.
     * When this.allowedSizes is non-null and this.restrictedSizes is null, this
     *     mount type accepts only the weapon sizes listed in this.allowedSizes.
     * When this.allowedSizes is null and this.restrictedSizes is non-null, this
     *     mount type accepts any weapon size except the weapon sizes listed in
     *     this.restrictedSizes.
     */
    private WeaponSize[] allowedSizes;
    /**
     * An array of weapon sizes that can be mounted on this mount; essentially,
     *     a blacklist.
     * Can be any WeaponSize[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     * 
     * When this.allowedSizes and this.restrictedSizes are both null, this mount
     *     type does not accept any weapon sizes.
     * When this.allowedSizes is non-null and this.restrictedSizes is null, this
     *     mount type accepts only the weapon sizes listed in this.allowedSizes.
     * When this.allowedSizes is null and this.restrictedSizes is non-null, this
     *     mount type accepts any weapon size except the weapon sizes listed in
     *     this.restrictedSizes.
     */
    private WeaponSize[] restrictedSizes;

    public MountType(int id, String name, WeaponSize[] allowedSizes,
        WeaponSize[] restrictedSizes) {
        HelperMethods.verifyConstructor();
        // Required properties
        setID(id);
        setName(name);
        // Optional properties
        setAllowedSizes(allowedSizes);
        setRestrictedSizes(restrictedSizes);
    }
    public MountType(int id, String name) {
        this(id, name, null, null);
    }

    // Static properties
    public static int getMaxID() {
        return maxID;
    }
    // Required properties
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    // Optional properties
    public WeaponSize[] getAllowedSizes() {
        if (allowedSizes != null) {
            return HelperMethods.copyOf(allowedSizes);
        }

        return allowedSizes;
    }
    public WeaponSize[] getRestrictedSizes() {
        if (restrictedSizes != null) {
            return HelperMethods.copyOf(restrictedSizes);
        }

        return restrictedSizes;
    }
    // Static properties
    private static void setMaxID(int maxID) {
        MountType.maxID = maxID;
    }
    // Required properties
    private void setID(int id) {
        this.id = id;
        setMaxID(Math.max(MountType.maxID, this.id));
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    // Optional properties
    private void setAllowedSizes(WeaponSize[] allowedSizes) {
        HelperMethods.checkObjectArrayAlt("allowedSizes",
            allowedSizes);
        if (allowedSizes != null) {
            allowedSizes = HelperMethods.copyOf(allowedSizes);
        }
        this.allowedSizes = allowedSizes;
    }
    private void setRestrictedSizes(WeaponSize[] restrictedSizes) {
        HelperMethods.checkObjectArrayAlt("restrictedSizes",
            restrictedSizes);
        if (restrictedSizes != null) {
            restrictedSizes = HelperMethods.copyOf(restrictedSizes);
        }
        this.restrictedSizes = restrictedSizes;
    }

    public String outputName() {
        return HelperMethods.capitalizeFirst(name);
    }
}
