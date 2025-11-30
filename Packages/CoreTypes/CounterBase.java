package Packages.CoreTypes;

public class CounterBase {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * The minimum value of this counter (i.e. 1).
     * Must be between Integer.MIN_VALUE and Integer.MAX_VALUE (inclusive).
     * Default value: Integer.MIN_VALUE.
     */
    protected int min;
    protected static final int minDefault = Integer.MIN_VALUE;
    /**
     * The maximum value of this counter (i.e. 6).
     * Must be between this.min and Integer.MAX_VALUE (inclusive).
     * Default value: Integer.MAX_VALUE.
     */
    protected int max;
    protected static final int maxDefault = Integer.MAX_VALUE;

    // Optional property
    /**
     * The default value of this counter (i.e. 6).
     * Must be between this.min and this.max (inclusive).
     * Default value: this.min.
     * 
     * Is only used when this.defaultValuePresent is true.
     */
    protected int defaultValue;

    // Helper property
    /**
     * Whether this.defaultValue should be used.
     */
    protected boolean defaultValuePresent;

    public CounterBase() {
        // Semi-required properties
        this.min = CounterBase.minDefault;
        this.max = CounterBase.maxDefault;
        // Optional property
        this.defaultValue = 0;
        // Helper property
        setDefaultValuePresent(false);
    }

    // Helper property
    protected void setDefaultValuePresent(boolean defaultValuePresent) {
        this.defaultValuePresent = defaultValuePresent;
    }

    protected int bound(int min, int input, int max) {
        if (min < max) {
            throw new IllegalArgumentException("min: " + min + " is < max: "
                + max);
        }
        input = Math.max(min, input);
        input = Math.min(max, input);

        return input;
    }
}
