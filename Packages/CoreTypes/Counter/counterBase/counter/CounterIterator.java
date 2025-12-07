package Packages.CoreTypes.Counter.counterBase.counter;

import java.util.Iterator;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.Counter;
import Packages.CoreTypes.Counter.counterBase.CounterData;

public class CounterIterator implements Iterator<Integer> {
    // Required property
    private Counter counter;
    private boolean iterateIncreasing;

    public CounterIterator(Counter counter, boolean iterateIncreasing) {
        this(iterateIncreasing);
        setCounter(counter);
    }
    public CounterIterator(Counter counter) {
        this(counter, true);
    }
    public CounterIterator(CounterData counterData, boolean iterateIncreasing) {
        this(iterateIncreasing);
        setCounter(counterData);
    }
    public CounterIterator(CounterData counterData) {
        this(counterData, true);
    }
    private CounterIterator(boolean iterateIncreasing) {
        setIterateIncreasing(iterateIncreasing);
    }

    // Required property
    public Counter getCounter() {
        return new Counter(counter);
    }
    public boolean isIterateIncreasing() {
        return iterateIncreasing;
    }
    // Required property
    private void setCounter(Counter counter) {
        HelperMethods.checkObject("counter", counter);
        counter = new Counter(counter);
        this.counter = counter;
    }
    private void setIterateIncreasing(boolean iterateIncreasing) {
        this.iterateIncreasing = iterateIncreasing;
    }

    @Override
    public boolean hasNext() {
        return ! counter.isMax();
    }
    @Override
    public Integer next() {
        counter.increment();

        return counter.getCurrent();
    }
    private void setCounter(CounterData counterData) {
        Counter counter;

        HelperMethods.checkObject("counterData", counterData);
        counter = counterData.toCounter();
        if (! iterateIncreasing) {
            counter.setToMax();
        }
        setCounter(counter);
    }
}
