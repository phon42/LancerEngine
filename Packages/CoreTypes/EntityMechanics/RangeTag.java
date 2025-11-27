package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

/**
 * See pgs. 64, 104, and 116.
 */
public class RangeTag {
    /**
     * The RangeTag's type (i.e. a RangeType representing "Range").
     * Can be any RangeType. Cannot be null.
     */
    private RangeType type;
    private int value;

    public RangeTag(RangeType rangeType, int value) {
        setType(rangeType);
        setValue(value);
    }
    public RangeTag(RangeTag rangeTag) {
        setType(rangeTag.type);
        setValue(rangeTag.value);
    }

    public RangeType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    private void setType(RangeType type) {
        HelperMethods.checkObject("type", type);
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
}