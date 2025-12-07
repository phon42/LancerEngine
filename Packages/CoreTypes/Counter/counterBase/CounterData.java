package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.CounterBase;

public class CounterData extends CounterBase implements Iterable<Integer> {
    // Required properties
    // TODO: figure out a way to override the documentation from CounterBase
    /**
     * The id of this counter (i.e. "ctr_brawler").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    // protected String id;
    // TODO: figure out a way to override the documentation from CounterBase
    /**
     * The name of this counter (i.e. "Brawler Die").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    // protected String name;

    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, int minimumValue, int maximumValue,
        int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue, id, name);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, int minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, id, name);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, int minimumValue, Object maximumValue,
        int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue, id, name);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, int minimumValue
    ) {
        // CounterBase properties
        super(minimumValue, id, name);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, Object minimumValue, int maximumValue,
        int defaultValue
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue, id, name);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, Object minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(null, maximumValue, id, name);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name, Object minimumValue, Object maximumValue,
        int defaultValue
    ) {
        // CounterBase properties
        super(null, null, defaultValue, id, name);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     */
    public CounterData(
        // CounterBase properties
        String id, String name
    ) {
        // CounterBase properties
        super(id, name);
    }

    // Required properties
    @Override
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    @Override
    protected void setName(String name) {
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
        return iteratorIncreasing();
    }
    public Iterator<Integer> iteratorIncreasing() {
        return toCounter().iteratorIncreasing();
    }
    public Iterator<Integer> iteratorDecreasing() {
        return toCounter().iteratorDecreasing();
    }
    public Iterable<Integer> iterableIncreasing() {
        return new CounterIterable(this, true);
    }
    public Iterable<Integer> iterableDecreasing() {
        return new CounterIterable(this, false);
    }
    public Counter toCounter() {
        return toCounter(true);
    }
    public Counter toCounter(boolean includeIdentifiers) {
        if (this.hasDefault) {
            if (includeIdentifiers) {
                return new Counter(this.min, this.max, this.defaultValue,
                    this.id, this.name);
            } else {
                return new Counter(this.min, this.max, this.defaultValue);
            }
        } else {
            if (includeIdentifiers) {
                return new Counter(this.min, this.max, this.id, this.name);
            } else {
                return new Counter(this.min, this.max);
            }
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
