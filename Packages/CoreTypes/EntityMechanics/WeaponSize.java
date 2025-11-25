package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

public class WeaponSize {
    /**
     * The weapon size's int id (i.e. 1 for a main weapon).
     * Can be any int.
     * 
     * Currently used values:
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
    private int id;
    /**
     * The weapon size's name (i.e. "Main").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "Aux", "Main", "Heavy", "Superheavy", "Ship-Class"
     */
    private String name;

    public WeaponSize(int id, String name) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
    }

    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    private void setID(int id) {
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
}
