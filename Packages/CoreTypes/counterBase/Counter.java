package Packages.CoreTypes.counterBase;

import java.util.Iterator;
import Packages.CoreTypes.CounterBase;
import Packages.CoreTypes.counterBase.counter.CounterIterator;

public class Counter extends CounterBase implements Iterable<Integer> {
    // Required property
    /**
     * The current value of this counter.
     */
    private int current;

    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(int minimumValue, int maximumValue, int defaultValue) {
        super(minimumValue, maximumValue, defaultValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(int minimumValue, int maximumValue) {
        super(minimumValue, maximumValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(int minimumValue, Object maximumValue, int defaultValue) {
        super(minimumValue, null, defaultValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(int minimumValue) {
        super(minimumValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(Object minimumValue, int maximumValue, int defaultValue) {
        super(null, maximumValue, defaultValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(Object minimumValue, int maximumValue) {
        super(null, maximumValue);
        setCurrent(this.min);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(Object minimumValue, Object maximumValue, int defaultValue) {
        super(null, null, defaultValue);
        setCurrent(this.min);
    }
    public Counter() {
        super();
        setCurrent(this.min);
    }
    public Counter(Counter counter) {
        setMin(counter.min);
        setMax(counter.max);
        setDefaultValue(counter.defaultValue);
        setDefaultValuePresent(counter.defaultValuePresent);
        setCurrent(counter.current);
    }

    // Required property
    public int getCurrent() {
        return current;
    }
    // Required property
    private void setCurrent(int current) {
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
        if (defaultValuePresent) {
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
    public Iterator<Integer> iterator() {
        return new CounterIterator(this);
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
    // Special accessors
    public boolean isMin() {
        return current == min;
    }
    public boolean isMax() {
        return current == max;
    }
}
