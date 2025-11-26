package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

public class WeaponType {
    private static int maxID = 0;
    /**
     * The weapon type's int id (i.e. 0 for a CQB weapon).
     * Can be any int.
     * 
     * Currently used values:
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
    private int id;
    /**
     * The weapon type's name (i.e. "CQB").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     * 
     * Currently used values:
     *     "CQB", "Cannon", "Launcher", "Melee", "Nexus", "Rifle",
     *     "Drone Weapon", "Spool Weapon", "???", "Special"
     */
    private String name;

    public WeaponType(int id, String name) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
    }

    public static int getMaxID() {
        return maxID;
    }
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    private static void setMaxID(int maxID) {
        WeaponType.maxID = maxID;
    }
    private void setID(int id) {
        this.id = id;
        setMaxID(Math.max(WeaponType.maxID, this.id));
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
}
