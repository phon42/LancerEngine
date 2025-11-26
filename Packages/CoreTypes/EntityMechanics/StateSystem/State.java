package Packages.CoreTypes.EntityMechanics.StateSystem;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Duration;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;

/**
 * See pgs. 77 - 78.
 */
/**
 * Represents a single status or condition on a mech or pilot. Contains
 *     information about the state's properties, effect duration, its source,
 *     and the State which may have caused it.
 * 
 * Requires a StateData object, a duration, and either a source or a State by
 *     which it was caused to be instantiated.
 * 
 * Used in and extended by Condition and Status.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class State {
    // Required properties
    /**
     * TODO: Add documentation
     */
    protected StateData data;
    /**
     * The duration of this State (i.e. a Duration representing 1 round).
     * Can be any Duration. Cannot be null.
     */
    protected Duration duration;

    // Conditionally required property
    /**
     * The source of this state (i.e. "Taro's mech").
     * Required when this.causedBy is null.
     * When required:
     *     Can be any String except "". Cannot be null.
     * When not required:
     *     Must be null.
     * Can be any String except "". Can be null.
     */
    protected String source;

    // Optional property
    /**
     * The State (if there is one) that caused this State to exist.
     * Can be any State. Can be null.
     */
    protected State causedBy;

    public State(StateData data, Duration duration, State causedBy) {
        // Required properties
        setData(data);
        setDuration(duration);

        // Optional property
        setCausedBy(causedBy);
    }
    public State(StateData data, Duration duration, String source) {
        // Required properties
        setData(data);
        setDuration(duration);

        // Conditionally required property
        setSource(source);
    }
    public State(State state) {
        // Required properties
        setData(state.data);
        setDuration(state.duration);

        // Conditionally required property
        setSource(state.source);

        // Optional property
        setCausedBy(state.causedBy);
    }

    // Required properties
    public StateData getData() {
        return data;
    }
    public Duration getDuration() {
        return duration;
    }
    // Conditionally required property
    public String getSource() {
        return source;
    }
    // Optional property
    public State getCausedBy() {
        return causedBy;
    }
    // Required properties
    protected void setData(StateData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
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
    // Conditionally required property
    protected void setSource(String source) {
        HelperMethods.checkString("source", source);
        this.source = source;
    }
    // Optional property
    protected void setCausedBy(State causedBy) {
        if (causedBy != null) {
            causedBy = new State(causedBy);
        }
        this.causedBy = causedBy;
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
        if (state == null) {
            return false;
        }
        if (! this.data.equals(state.data)) {
            return false;
        }
        if (! this.duration.equals(state.duration)) {
            return false;
        }
        if (this.source == null) {
            if (state.source != null) {
                return false;
            }
        } else {
            if (state.source == null) {
                return false;
            }
            if (! this.source.equals(state.source)) {
                return false;
            }
        }
        if (this.causedBy == null) {
            if (state.causedBy != null) {
                return false;
            }
        } else {
            if (state.causedBy == null) {
                return false;
            }
            if (! this.causedBy.equals(state.causedBy)) {
                return false;
            }
        }

        return true;
    }
    public Condition toCondition() {
        if (data.isStatus()) {
            throw new IllegalStateException("Unable to convert this State"
                + " (which has a State.isStatus value of false) to a"
                + " Condition");
        }

        if (this.source == null) {
            return new Condition(data, duration, causedBy);
        } else {
            return new Condition(data, duration, source);
        }
    }
    public Status toStatus() {
        if (data.isStatus()) {
            if (this.source == null) {
                return new Status(data, duration, causedBy);
            } else {
                return new Status(data, duration, source);
            }
        }
        throw new IllegalStateException("Unable to convert this State (which"
            + " has a State.isStatus value of false) to a Status");
    }
}
