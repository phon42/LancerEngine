package packages.coreTypes;

import main.HelperMethods;

public class RangeTag {
    /**
     * The RangeTag's type (i.e. "Blast").
     * Must be an allowed type as defined by RangeTag.allowedTypes. Cannot be
     *     null.
     */
    private String type;
    /**
     * Contains an array of allowed range types.
     * Case-insensitive and stored in lowercase.
     */
    public static final String[] allowedTypes = new String[] {"blast", "burst",
        "cone", "line", "range", "threat", "thrown"};
    private int value;

    public RangeTag(String rangeType, int value) {
        setType(rangeType);
        setValue(value);
    }
    public RangeTag(RangeTag rangeTag) {
        setType(rangeTag.type);
        setValue(rangeTag.value);
    }

    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        if (! RangeTag.isValid(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }
    private void setValue(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("value is 0");
        }
        if (value < -1) {
            throw new IllegalArgumentException("value value: " + value + " is <"
                + " -1");
        }
        this.value = value;
    }

    public static boolean isValid(String type) {
        boolean isValid = false;

        for (int i = 0; i < RangeTag.allowedTypes.length; i++) {
            if (type.equals(RangeTag.allowedTypes[i])) {
                isValid = true;
            }
        }

        return isValid;
    }
}