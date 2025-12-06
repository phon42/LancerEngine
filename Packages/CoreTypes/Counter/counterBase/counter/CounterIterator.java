package Packages.CoreTypes.Counter.counterBase.counter;

import java.util.Iterator;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.Counter;

public class CounterIterator implements Iterator<Integer> {
    // Required property
    private Counter counter;

    public CounterIterator(Counter counter) {
        setCounter(counter);
    }

    // Required property
    public Counter getCounter() {
        return new Counter(counter);
    }
    // Required property
    private void setCounter(Counter counter) {
        HelperMethods.checkObject("counter", counter);
        counter = new Counter(counter);
        this.counter = counter;
    }

    public boolean hasNext() {
        return ! counter.isMax();
    }
    public Integer next() {
        counter.increment();

        return counter.getCurrent();
    }
}
