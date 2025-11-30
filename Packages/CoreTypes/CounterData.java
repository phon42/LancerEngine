package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class CounterData {
    // Required properties
    /**
     * The id of this counter (i.e. "ctr_brawler").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of this counter (i.e. "Brawler Die").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * The minimum value of this counter (i.e. 1).
     * Can be any int.
     */
    private int min;
    private static final int minDefault = Integer.MIN_VALUE;
    /**
     * The maximum value of this counter (i.e. 6).
     * Can be any int.
     */
    private int max;
    private static final int maxDefault = Integer.MAX_VALUE;

    // Technically optional property but since it's an int we're gonna have to
    //     do something different
    /**
     * The default value of this counter (i.e. 6).
     * Can be any int.
     * 
     * Is only used when this.defaultValuePresent is true.
     */
    private int defaultValue;

    // Helper property
    /**
     * Whether this.defaultValue should be used.
     */
    private boolean defaultValuePresent;

    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        int maximumValue, int defaultValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(minimumValue);
        setMax(maximumValue);
        // Technically optional property
        setDefaultValue(defaultValue);
        // Helper property
        setDefaultValuePresent(true);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        int maximumValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(minimumValue);
        setMax(maximumValue);
        // Technically optional property
        setDefaultValue(0);
        // Helper property
        setDefaultValuePresent(false);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        Object maximumValue, int defaultValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(minimumValue);
        setMax(CounterData.maxDefault);
        // Technically optional property
        setDefaultValue(defaultValue);
        // Helper property
        setDefaultValuePresent(true);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, int minimumValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(minimumValue);
        setMax(CounterData.maxDefault);
        // Technically optional property
        setDefaultValue(0);
        // Helper property
        setDefaultValuePresent(false);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        int maximumValue, int defaultValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(CounterData.minDefault);
        setMax(maximumValue);
        // Technically optional property
        setDefaultValue(defaultValue);
        // Helper property
        setDefaultValuePresent(true);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        int maximumValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(CounterData.minDefault);
        setMax(maximumValue);
        // Technically optional property
        setDefaultValue(0);
        // Helper property
        setDefaultValuePresent(false);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        Object maximumValue, int defaultValue) {
        // Required properties
        this(id, name);
        // Semi-required properties
        setMin(CounterData.minDefault);
        setMax(CounterData.maxDefault);
        // Technically optional property
        setDefaultValue(defaultValue);
        // Helper property
        setDefaultValuePresent(true);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name) {
        // Required properties
        setID(id);
        setName(name);
        // Semi-required properties
        setMin(CounterData.minDefault);
        setMax(CounterData.maxDefault);
        // Technically optional property
        setDefaultValue(0);
        // Helper property
        setDefaultValuePresent(false);
    }
    public CounterData(CounterData counter) {
        this(counter.id, counter.name, counter.min, counter.max,
            counter.defaultValue);
        setDefaultValuePresent(counter.defaultValuePresent);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    // Semi-required properties
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    // Technically optional property
    public int getDefaultValue() {
        return defaultValue;
    }
    // Helper property
    public boolean isDefaultValuePresent() {
        return defaultValuePresent;
    }
    // Required properties
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    // Semi-required properties
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
    // Technically optional property
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    // Helper property
    private void setDefaultValuePresent(boolean defaultValuePresent) {
        this.defaultValuePresent = defaultValuePresent;
    }
}
