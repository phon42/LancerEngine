package Packages.CoreTypes;

public class Counter extends CounterBase {
    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(int minimumValue, int maximumValue, int defaultValue) {
        super(minimumValue, maximumValue, defaultValue);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(int minimumValue, int maximumValue) {
        super(minimumValue, maximumValue);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(int minimumValue, Object maximumValue, int defaultValue) {
        super(minimumValue, null, defaultValue);
    }
    /**
     * Counter.min:              PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(int minimumValue) {
        super(minimumValue);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(Object minimumValue, int maximumValue, int defaultValue) {
        super(null, maximumValue, defaultValue);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:              PROVIDED
     * Counter.defaultValue: NOT PROVIDED
     */
    public Counter(Object minimumValue, int maximumValue) {
        super(null, maximumValue);
    }
    /**
     * Counter.min:          NOT PROVIDED
     * Counter.max:          NOT PROVIDED
     * Counter.defaultValue:     PROVIDED
     */
    public Counter(Object minimumValue, Object maximumValue, int defaultValue) {
        super(null, null, defaultValue);
    }
    public Counter() {
        super();
    }
    public Counter(Counter counter) {
        setMin(counter.min);
        setMax(counter.max);
        setDefaultValue(counter.defaultValue);
        setDefaultValuePresent(counter.defaultValuePresent);
    }
}
