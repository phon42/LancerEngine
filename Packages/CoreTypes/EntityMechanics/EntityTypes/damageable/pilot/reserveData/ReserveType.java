package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserveData;

import MainBranch.HelperMethods;

public class ReserveType {
    private static int maxID = 0;
    /**
     * The reserve type's int id (i.e. 0 for a mech reserve).
     * Can be any int.
     * 
     * Currently used values:
     *     0 - mech
     *     1 - tactical
     *     2 - resource
     *     3 - bonus
     */
    private int id;
    /**
     * The reserve type's name (i.e. "mech").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     * Use ReserveType.getName() to get the raw value and
     *     ReserveType.outputName() to obtain it properly formatted.
     * 
     * Currently used values:
     *     "mech", "tactical", "resource", "bonus"
     */
    private String name;

    public ReserveType(int id, String name) {
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
        ReserveType.maxID = maxID;
    }
    private void setID(int id) {
        this.id = id;
        setMaxID(Math.max(ReserveType.maxID, this.id));
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }

    public String outputName() {
        return HelperMethods.capitalizeFirst(name);
    }
}
