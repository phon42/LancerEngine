package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.CounterBase;

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

    @Override
    public String toString() {
        if (hasDefault) {
            return toString(min, max, defaultValue);
        } else {
            return toString(min, max);
        }
    }
    @Override
    protected String toString(int min, int max) {
        return String.format(
            "ID: \"%s\"\nName: \"%s\"\n(Min: %d - Max: %d)", this.id,
            this.name, min, max
        );
    }
    @Override
    protected String toString(int min, int max, int defaultValue) {
        return String.format(
            "ID: \"%s\"\nName: \"%s\"\n(Min: %d - Max: %d - Def: %d)",
            this.id, this.name, min, max, defaultValue
        );
    }
    public Counter toCounter() {
        if (this.hasDefault) {
            return new Counter(this.min, this.max, this.defaultValue);
        } else {
            return new Counter(this.min, this.max);
        }
    }
    public static Counter[] toCounter(CounterData[] input) {
        Counter[] result;

        HelperMethods.checkObjectArray("input", input);
        result = new Counter[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i].toCounter();
        }

        return result;
    }
    public Iterator<Integer> iterator() {
        return toCounter().iterator();
    }
}
