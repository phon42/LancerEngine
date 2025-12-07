package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;
import Packages.CoreTypes.Counter.CounterBase;
import Packages.CoreTypes.Counter.counterBase.counter.CounterIterator;

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
    }
    /**
     * CounterBase.min:              PROVIDED
     * CounterBase.max:              PROVIDED
     * CounterBase.defaultValue:     PROVIDED
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
        return String.format("Curr: %d\n(Min: %d - Max: %d)", current,
            min, max);
    }
    protected String toString(int min, int current, int max, int defaultValue) {
        return String.format("Curr: %d\n(Min: %d - Max: %d - Def: %d)",
            current, min, max, defaultValue);
    }
    @Override
    public Iterator<Integer> iterator() {
        return new CounterIterator(this);
    }
    protected void setCurrent() {
        setCurrent(min);
    }
    // Special mutators
    public void increment() {
        increment(1);
    }
    public void increment(int amount) {
        setCurrent(current + amount);
    }
    public void decrement() {
        decrement(1);
    }
    public void decrement(int amount) {
        setCurrent(current - amount);
    }
    public void setToMin() {
        setCurrent(min);
    }
    public void setToMax() {
        setCurrent(max);
    }
    public void setToDefault() {
        if (! hasDefault) {
            throw new IllegalStateException("Called Counter.setToDefault()"
                + " when this.hasDefault is false");
        }
        setCurrent(defaultValue);
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
}
