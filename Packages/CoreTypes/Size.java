package Packages.CoreTypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import MainBranch.HelperMethods;

public final class Size {
    // Required property
    /**
     * The size value stored by this object.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be a minimum of 1 OR (a minimum of 2 AND (this.value % 2 == 0)).
     */
    private int value;

    // Reference property
    private static final BigDecimal TWO = new BigDecimal(2);

    public Size(int value) {
        setValue(value);
    }
    public Size(BigDecimal value) {
        calculateValue(value);
    }

    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("Size value: " + value + " is <"
                + " 1");
        }
        if (value != 1) {
            if (value % 2 != 0) {
                throw new IllegalArgumentException("Size value: " + value
                    + " is odd (value % 2 != 0)");
            }
        }
        this.value = value;
    }

    @Override
    /**
     * A helper method which outputs the stored size, formatted properly so that
     *     it is human-readable.
     * @return a String containing the requested output.
     */
    public String toString() {
        return "SIZE " + output();
    }
    private void calculateValue(BigDecimal value) {
        BigInteger integer;

        HelperMethods.checkObject("value", value);
        value = value.multiply(Size.TWO);
        // performing this intermediate step for now
        integer = value.toBigInteger();
        setValue(integer.intValue());
    }
    /**
     * A helper method which outputs the stored size, formatted properly so that
     *     it is human-readable.
     * @return a String containing the requested output.
     */
    public String output() {
        if (value == 1) {
            return "1/2";
        }
        if (value > 1) {
            return Integer.toString(value / 2);
        }
        return Integer.toString(value);
    }
}
