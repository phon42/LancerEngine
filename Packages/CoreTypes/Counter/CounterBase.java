package Packages.CoreTypes.Counter;

/**
 * Represents a counter of any type. Contains information about that counter's
 *     maximum and minimum bounds, as well as an optional default value.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in and extended by Counter and CounterData.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 *     However, it includes a copy constructor because at least one of its
 *     children is not.
 */
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
    /**
     * The default value of this counter (i.e. 6).
     * Must be between this.min and this.max (inclusive).
     * Default value: this.min.
     * 
     * Is only used when this.hasDefault is true.
     */
    protected int defaultValue;
    // defaultValueDefault purposefully removed because the relevant value is a
    //     variable

    // Helper property
    /**
     * Whether this.defaultValue should be used.
     */
    protected boolean hasDefault;

    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        int minimumValue, int maximumValue, int defaultValue
    ) {
        this();
        // Semi-required properties
        setMin(minimumValue);
        setMax(maximumValue);
        setDefaultValue(defaultValue);
        // Helper property
        setHasDefault(true);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        int minimumValue, int maximumValue
    ) {
        this();
        // Semi-required properties
        setMin(minimumValue);
        setMax(maximumValue);
        setDefaultValue();
        // Helper property
        setHasDefault(false);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        int minimumValue, Object maximumValue, int defaultValue
    ) {
        this();
        // Semi-required properties
        setMin(minimumValue);
        setDefaultValue(defaultValue);
        // Helper property
        setHasDefault(true);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        int minimumValue
    ) {
        this();
        // Semi-required properties
        setMin(minimumValue);
        setDefaultValue();
        // Helper property
        setHasDefault(false);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        Object minimumValue, int maximumValue, int defaultValue
    ) {
        this();
        // Semi-required properties
        setMax(maximumValue);
        setDefaultValue(defaultValue);
        // Helper property
        setHasDefault(true);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        Object minimumValue, int maximumValue
    ) {
        this();
        // Semi-required properties
        setMax(maximumValue);
        setDefaultValue();
        // Helper property
        setHasDefault(false);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    protected CounterBase(
        // Semi-required properties
        Object minimumValue, Object maximumValue, int defaultValue
    ) {
        this();
        // Semi-required properties
        setDefaultValue(defaultValue);
        // Helper property
        setHasDefault(true);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    protected CounterBase() {
        // Semi-required properties
        setMin();
        setMax();
        setDefaultValue();
        // Helper property
        setHasDefault(false);
    }
    protected CounterBase(CounterBase counterBase) {
        // Semi-required properties
        setMin(counterBase.min);
        setMax(counterBase.max);
        setDefaultValue(counterBase.defaultValue);
        // Helper property
        setHasDefault(counterBase.hasDefault);
    }

    // Semi-required properties
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public int getDefaultValue() {
        return defaultValue;
    }
    // Helper property
    public boolean hasDefault() {
        return hasDefault;
    }
    // Semi-required properties
    protected void setMin(int min) {
        min = bound(CounterBase.minDefault, min, this.max);
        this.min = min;
    }
    protected void setMax(int max) {
        max = bound(this.min, max, CounterBase.maxDefault);
        this.max = max;
    }
    protected void setDefaultValue(int defaultValue) {
        defaultValue = bound(this.min, defaultValue, this.max);
        this.defaultValue = defaultValue;
    }
    // Helper property
    protected void setHasDefault(boolean hasDefault) {
        this.hasDefault = hasDefault;
    }

    @Override
    public String toString() {
        if (hasDefault) {
            return toString(min, max, defaultValue);
        } else {
            return toString(min, max);
        }
    }
    protected String toString(int min, int max) {
        return String.format("(Min: %d - Max: %d)", min, max);
    }
    protected String toString(int min, int max, int defaultValue) {
        return String.format("(Min: %d - Max: %d - Def: %d)", min, max,
            defaultValue);
    }
    protected void setMin() {
        setMin(CounterBase.minDefault);
    }
    protected void setMax() {
        setMax(CounterBase.maxDefault);
    }
    protected void setDefaultValue() {
        setDefaultValue(min);
    }
    protected int bound(int min, int input, int max) {
        if (max < min) {
            throw new IllegalArgumentException("min: " + min + " is < max: "
                + max);
        }
        input = Math.max(min, input);
        input = Math.min(max, input);

        return input;
    }
}
