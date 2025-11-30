package Packages.CoreTypes.counterBase;

import java.util.Iterator;
import MainBranch.HelperMethods;
import Packages.CoreTypes.CounterBase;

public class CounterData extends CounterBase implements Iterable<Integer> {
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

    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        int maximumValue, int defaultValue) {
        super(minimumValue, maximumValue, defaultValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        int maximumValue) {
        super(minimumValue, maximumValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, int minimumValue,
        Object maximumValue, int defaultValue) {
        super(minimumValue, null, defaultValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, int minimumValue) {
        super(minimumValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        int maximumValue, int defaultValue) {
        super(null, maximumValue, defaultValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        int maximumValue) {
        super(null, maximumValue);
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(String id, String name, Object minimumValue,
        Object maximumValue, int defaultValue) {
        super(null, null, defaultValue);
        setID(id);
        setName(name);
    }
    public CounterData(String id, String name) {
        super();
        setID(id);
        setName(name);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }

    public Counter toCounter() {
        if (this.defaultValuePresent) {
            return new Counter(this.min, this.max, this.defaultValue);
        } else {
            return new Counter(this.min, this.max);
        }
    }
    public Iterator<Integer> iterator() {
        return toCounter().iterator();
    }
}
