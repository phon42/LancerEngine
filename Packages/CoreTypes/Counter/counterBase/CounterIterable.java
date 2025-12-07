package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;

import MainBranch.HelperMethods;

public class CounterIterable implements Iterable<Integer> {
    // Required property
    private Iterator<Integer> counterIterator;

    public CounterIterable(Counter counter, boolean iterateIncreasing) {
        setCounterIterator(counter, iterateIncreasing);
    }
    public CounterIterable(Counter counter) {
        this(counter, true);
    }
    public CounterIterable(CounterData counterData, boolean iterateIncreasing) {
        setCounterIterator(counterData, iterateIncreasing);
    }
    public CounterIterable(CounterData counterData) {
        this(counterData, true);
    }

    // Required property
    // getCounterIterator purposefully removed
    // Required property
    private void setCounterIterator(Iterator<Integer> counterIterator) {
        HelperMethods.checkObject("counterIterator",
            counterIterator);
        this.counterIterator = counterIterator;
    }

    @Override
    public Iterator<Integer> iterator() {
        return counterIterator;
    }
    private void setCounterIterator(Counter counter, boolean iterateIncreasing)
    {
        HelperMethods.checkObject("counter", counter);
        if (iterateIncreasing) {
            setCounterIterator(counter.iteratorIncreasing());
        } else {
            setCounterIterator(counter.iteratorDecreasing());
        }
    }
    private void setCounterIterator(CounterData counterData,
        boolean iterateIncreasing) {
        HelperMethods.checkObject("counterData", counterData);
        if (iterateIncreasing) {
            setCounterIterator(counterData.iteratorIncreasing());
        } else {
            setCounterIterator(counterData.iteratorDecreasing());
        }
    }
}
