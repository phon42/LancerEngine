package Packages.CoreTypes.Counter.counterBase.counter;

import java.util.Iterator;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.Counter;

public class CounterIterator implements Iterator<Integer> {
    // Required property
    private Counter counter;
    private boolean iterateIncreasing;

    public CounterIterator(Counter counter, boolean iterateIncreasing) {
        setCounter(counter);
        setIterateIncreasing(iterateIncreasing);
    }
    public CounterIterator(Counter counter) {
        this(counter, true);
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

    public boolean hasNext() {
        return ! counter.isMax();
    }
    public Integer next() {
        counter.increment();

        return counter.getCurrent();
    }
}
