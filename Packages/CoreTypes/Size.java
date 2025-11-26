package Packages.CoreTypes;

public class Size {
    /**
     * The size value stored by this object.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     */
    private int value;

    public Size(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        if (value != 1 && value != 2 && value != 4 && value != 6 && value != 8)
        {
            throw new IllegalArgumentException("Size value: " + value + " is"
                + " not one of the following valid values: 1, 2, 4, 6, or 8");
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
