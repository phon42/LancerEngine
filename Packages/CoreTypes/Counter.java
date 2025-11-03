package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class Counter {
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
    /**
     * The default value of this counter (i.e. 6).
     * Can be any int.
     */
    private int defaultValue;
    /**
     * The minimum value of this counter (i.e. 1).
     * Can be any int.
     */
    private int min;
    /**
     * The maximum value of this counter (i.e. 6).
     * Can be any int.
     */
    private int max;

    public Counter(String id, String name, int defaultValue, int minimumValue,
        int maximumValue) {
        setID(id);
        setName(name);
        setDefaultValue(defaultValue);
        setMin(minimumValue);
        setMax(maximumValue);
    }
    public Counter(Counter counter) {
        setID(counter.id);
        setName(counter.name);
        setDefaultValue(counter.defaultValue);
        setMin(counter.min);
        setMax(counter.max);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getDefaultValue() {
        return defaultValue;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
}
