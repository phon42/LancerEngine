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
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        int minimumValue, int maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        int minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        int minimumValue, Object maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:              PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        int minimumValue
    ) {
        // CounterBase properties
        super(minimumValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        Object minimumValue, int maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:              PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        Object minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(null, maximumValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue:     PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name,
        // CounterBase properties
        Object minimumValue, Object maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(null, null, defaultValue);
        // Required properties
        setID(id);
        setName(name);
    }
    /**
     * CounterData.min:          NOT PROVIDED
     * CounterData.max:          NOT PROVIDED
     * CounterData.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // Required properties
        String id, String name
    ) {
        super();
        // Required properties
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
    @Override
    public Iterator<Integer> iterator() {
        return toCounter().iterator();
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
}
