package Packages.CoreTypes.Counter.counterBase;

import java.util.Iterator;

import MainBranch.HelperMethods;

public class CounterIterator implements Iterator<Integer> {
    // Required property
    private Counter counter;
    private boolean iterateIncreasing;
    private boolean reachedEnd;

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
        resetReachedEnd();
    }

    // Required property
    public Counter getCounter() {
        return new Counter(counter);
    }
    public boolean isIterateIncreasing() {
        return iterateIncreasing;
    }
    // isReachedEnd purposefully removed
    // Required property
    private void setCounter(Counter counter) {
        HelperMethods.checkObject("counter", counter);
        counter = new Counter(counter);
        this.counter = counter;
    }
    private void setIterateIncreasing(boolean iterateIncreasing) {
        this.iterateIncreasing = iterateIncreasing;
    }
    // normal version of setReachedEnd purposefully removed

    @Override
    public boolean hasNext() {
        return ! reachedEnd;
    }
    @Override
    public Integer next() {
        Integer curr = counter.getCurrent();

        if (iterateIncreasing) {
            if (counter.isMax()) {
                setReachedEnd();
            } else {
                counter.increment();
            }
        } else {
            if (counter.isMin()) {
                setReachedEnd();
            } else {
                counter.decrement();
            }
        }

        return curr;
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
    private void resetReachedEnd() {
        this.reachedEnd = false;
    }
    private void setReachedEnd() {
        this.reachedEnd = true;
    }
}
