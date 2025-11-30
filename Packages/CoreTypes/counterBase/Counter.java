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
        current = bound(this.min, current, this.max);
        this.current = current;
    }

    public Iterator<Integer> iterator() {
        return new CounterIterator(this);
    }
    public void increment() {
        setCurrent(current + 1);
    }
}
