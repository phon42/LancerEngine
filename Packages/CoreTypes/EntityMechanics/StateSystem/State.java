package Packages.CoreTypes.EntityMechanics.StateSystem;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Duration;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.StateData;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;

/**
 * See pgs. 77 - 78.
 */
/**
 * Represents a single status or condition on a mech or pilot. Contains
 *     information about the status or condition's name, origin, duration, and
 *     effects.
 * 
 * Requires a name, source, and duration to be instantiated.
 * 
 * Used in and extended by Condition and Status.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class State {
    /**
     * TODO: Add documentation
     */
    protected StateData data;
    /**
     * The State (if there is one) that caused this State to exist.
     * Can be any State. Can be null.
     */
    protected State causedBy;
    /**
     * The source of this state (i.e. "Taro's mech").
     * Can be any String except "". Can be null if this.causedBy is null.
     */
    protected String source;
    /**
     * The duration of this State (i.e. a Duration representing 1 round).
     * Can be any Duration. Cannot be null.
     */
    protected Duration duration;

    public State(String name, String source, Duration duration) {
        setCausedBy(null);
        setSource(source);
        setDuration(duration);
    }
    public State(State state) {
        setCausedBy(state.causedBy);
        setSource(state.source);
        setDuration(state.duration);
    }

    public State getCausedBy() {
        return causedBy;
    }
    public String getSource() {
        return source;
    }
    public Duration getDuration() {
        return duration;
    }
    protected void setCausedBy(State causedBy) {
        if (causedBy != null) {
            causedBy = new State(causedBy);
        }
        this.causedBy = causedBy;
    }
    protected void setSource(String source) {
        HelperMethods.checkString("source", source);
        this.source = source;
    }
    /**
     * Sets this.duration to the provided value.
     * @param duration a Duration which can be any Duration. Cannot be null.
     * @throws IllegalArgumentException if status is duration or an invalid
     *     value as defined by State.allowedDurations.
     */
    protected void setDuration(Duration duration) {
        HelperMethods.checkObject("New duration", duration);
        this.duration = duration;
    }

    /**
     * Compares this State object and obj. If they are instances of the same
     *     class, returns true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof State)) {
            return false;
        }
        return equals((State) obj);
    }
    /**
     * Compares this State object and state. If they have the same property
     *     values, returns true.
     * @param state a State to be compared to.
     * @return a boolean representing whether the two States are the same.
     */
    public boolean equals(State state) {
        // TODO: fill out
        return true;
    }
    public Condition toCondition() {
        // TODO: fill out, throw an Exception if it can't be converted
        return null;
    }
    public Status toStatus() {
        // TODO: fill out, throw an Exception if it can't be converted
        return null;
    }
}
