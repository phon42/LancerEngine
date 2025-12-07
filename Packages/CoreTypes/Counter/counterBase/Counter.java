package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;
import Packages.CoreTypes.Counter.CounterBase;

/**
 * Represents a counter. Contains information about that counter's current
 *     value, maximum and minimum bounds, as well as an optional default value,
 *     id, and name.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in CoreSystem.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 * 
 * This class is mutable; it is necessary to create copies of objects of this
 *     class to prevent them from being modified through other references to the
 *     same object.
 */
public class Counter extends CounterBase implements Iterable<Integer> {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * The current value of this counter.
     * Can be any int between this.min and this.max (inclusive).
     * 
     * Default value: this.min.
     */
    protected int current;
    // currentDefault purposefully removed because the relevant value is a
    //     variable

    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        int minimumValue, int maximumValue, int defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue, int defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue, int defaultValue, String id,
        String name
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, defaultValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        int minimumValue, int maximumValue, Object defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue, Object defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue, String id, String name
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(minimumValue, maximumValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        int minimumValue, Object maximumValue, int defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, Object maximumValue, int defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, Object maximumValue, int defaultValue, String id,
        String name
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, Object maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(minimumValue, null, defaultValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        int minimumValue, Object maximumValue, Object defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(minimumValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, Object maximumValue, Object defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(minimumValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue, Object maximumValue, Object defaultValue, String id,
        String name
    ) {
        // CounterBase properties
        super(minimumValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        int minimumValue
    ) {
        // CounterBase properties
        super(minimumValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        Object minimumValue, int maximumValue, int defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue, int defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue, int defaultValue, String id,
        String name
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(null, maximumValue, defaultValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        Object minimumValue, int maximumValue, Object defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(null, maximumValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue, Object defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(null, maximumValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue, String id, String name
    ) {
        // CounterBase properties
        super(null, maximumValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, int maximumValue
    ) {
        // CounterBase properties
        super(null, maximumValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties (part 1)
        Object minimumValue, Object maximumValue, int defaultValue,
        // Semi-required property
        int current,
        // CounterBase properties (part 2)
        String id, String name
    ) {
        // CounterBase properties
        super(null, null, defaultValue, id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, Object maximumValue, int defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super(null, null, defaultValue);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, Object maximumValue, int defaultValue, String id,
        String name
    ) {
        // CounterBase properties
        super(null, null, defaultValue, id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue:     PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, Object maximumValue, int defaultValue
    ) {
        // CounterBase properties
        super(null, null, defaultValue);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // Semi-required property
        int current,
        // CounterBase properties
        String id, String name
    ) {
        // CounterBase properties
        super(id, name);
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:              PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter(
        // CounterBase properties
        Object minimumValue, Object maximumValue, Object defaultValue,
        // Semi-required property
        int current
    ) {
        // CounterBase properties
        super();
        // Semi-required property
        setCurrent(current);
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:               PROVIDED
     * CounterBase.name:             PROVIDED
     */
    public Counter(
        // CounterBase properties
        String id, String name
    ) {
        // CounterBase properties
        super(id, name);
        // Semi-required property
        setCurrent();
    }
    /**
     * CounterBase.min:          NOT PROVIDED
     * CounterBase.max:          NOT PROVIDED
     * CounterBase.defaultValue: NOT PROVIDED
     * Counter.current:          NOT PROVIDED
     * CounterBase.id:           NOT PROVIDED
     * CounterBase.name:         NOT PROVIDED
     */
    public Counter() {
        // CounterBase properties
        super();
        // Semi-required property
        setCurrent();
    }
    public Counter(Counter counter) {
        // CounterBase properties
        super(counter);
        // Semi-required property
        setCurrent(counter.current);
    }

    // Semi-required property
    public int getCurrent() {
        return current;
    }
    // Semi-required property
    protected void setCurrent(int current) {
        if (current < this.min) {
            throw new IllegalArgumentException("current: " + current + " is <"
                + " this.min: " + this.min);
        }
        if (current > this.max) {
            throw new IllegalArgumentException("current: " + current + " is <"
                + " this.max: " + this.max);
        }
        this.current = current;
    }

    @Override
    public String toString() {
        if (hasDefault) {
            return toString(min, current, max, defaultValue);
        } else {
            return toString(min, current, max);
        }
    }
    @Override
    protected String toString(int min, int current, int max) {
        String outputString = "Curr: %d\n(Min: %d - Max: %d)";
        String name = "Name: \"%s\"\n";
        String id = "ID: \"%s\"\n";

        outputString = String.format(outputString, current, min, max);
        if (hasName()) {
            name = String.format(name, this.name);
            outputString = name + outputString;
        }
        if (hasID()) {
            id = String.format(id, this.id);
            outputString = id + outputString;
        }

        return outputString;
    }
    protected String toString(int min, int current, int max, int defaultValue) {
        return String.format("Curr: %d\n(Min: %d - Max: %d - Def: %d)",
            current, min, max, defaultValue);
    }
    @Override
    public boolean hasID() {
        return super.hasID();
    }
    @Override
    public boolean hasName() {
        return super.hasName();
    }
    @Override
    public Iterator<Integer> iterator() {
        return iteratorIncreasing();
    }
    protected Iterator<Integer> iteratorIncreasing() {
        return new CounterIterator(this, true);
    }
    protected Iterator<Integer> iteratorDecreasing() {
        return new CounterIterator(this, false);
    }
    protected void setCurrent() {
        setCurrent(min);
    }
    public Iterable<Integer> iterableIncreasing() {
        return new CounterIterable(this, true);
    }
    public Iterable<Integer> iterableDecreasing() {
        return new CounterIterable(this, false);
    }
    // Special accessors
    public boolean isMin() {
        return current == min;
    }
    public boolean isMax() {
        return current == max;
    }
    public boolean isDefault() {
        return hasDefault && current == defaultValue;
    }
    // Special mutators
    public int increment() {
        return increment(1);
    }
    public int increment(int amount) {
        setCurrent(current + amount);

        return this.current;
    }
    public int decrement() {
        return decrement(1);
    }
    public int decrement(int amount) {
        setCurrent(current - amount);

        return this.current;
    }
    public int setToMin() {
        setCurrent(min);

        return this.current;
    }
    public int setToMax() {
        setCurrent(max);

        return this.current;
    }
    public int setToDefault() {
        if (! hasDefault) {
            throw new IllegalStateException("Called Counter.setToDefault()"
                + " when this.hasDefault is false");
        }
        setCurrent(defaultValue);

        return this.current;
    }
}
