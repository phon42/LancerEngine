package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

public class RangeType {
    private static int maxID = 0;
    /**
     * The range type's int id (i.e. 0 for regular range).
     * Can be any int.
     * 
     * Currently used values:
     *     0 - Range
     *     1 - Threat
     *     2 - Line
     *     3 - Cone
     *     4 - Blast
     *     5 - Burst
     *     6 - Thrown
     */
    private int id;
    /**
     * The range type's name (i.e. "range").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     * 
     * Use RangeType.getName() to get the raw value and RangeType.outputName()
     *     to obtain it properly formatted.
     * 
     * Currently used values:
     *     "range", "threat", "line", "cone", "blast", "burst", "thrown"
     */
    private String name;

    public RangeType(int id, String name) {
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
        RangeType.maxID = maxID;
    }
    private void setID(int id) {
        this.id = id;
        setMaxID(Math.max(RangeType.maxID, this.id));
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
