package Packages.CoreTypes.EntityMechanics.StateSystem;

import java.util.NoSuchElementException;

import MainBranch.Database;
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

    // Optional properties
    /**
     * The State (if there is one) that caused this State to exist.
     * Can be any State. Can be null.
     */
    protected State causedBy;
    /**
     * The States (if there are any) that this State causes to exist.
     * Can be any State[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    protected State[] effects;

    public State(StateData data, Duration duration, State causedBy,
        State[] effects) {
        // Required properties
        setData(data);
        setDuration(duration);

        // Optional properties
        setCausedBy(causedBy);
        setEffects(effects);
    }
    public State(StateData data, Duration duration, String source,
        State[] effects) {
        // Required properties
        setData(data);
        setDuration(duration);

        // Conditionally required property
        setSource(source);

        // Optional property
        setEffects(effects);
    }
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
    // Optional properties
    public State getCausedBy() {
        return causedBy;
    }
    public State[] getEffects() {
        return HelperMethods.copyOf(effects);
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
    // Optional properties
    protected void setCausedBy(State causedBy) {
        if (causedBy != null) {
            causedBy = new State(causedBy);
        }
        this.causedBy = causedBy;
    }
    protected void setEffects(State[] effects) {
        HelperMethods.checkObjectArrayAlt("effects", effects);
        effects = HelperMethods.copyOf(effects);
        this.effects = effects;
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
    /**
     * Adds a provided State effect to this.effects.
     * @param effects a State which cannot be null.
     * @throws IllegalArgumentException if effect is null.
     */
    public void addEffect(State effects) {
        HelperMethods.checkObject("effects", effects);
        setEffects(HelperMethods.append(this.effects, effects));
    }
    public State removeStateEffect(int index) {
        State removedStateEffect;
        State[] effects;

        if (this.effects.length == 0) {
            throw new IllegalArgumentException("Attempted to call"
                + " State.removeEffect() when this.effects.length is 0");
        }
        if (index < 0 || index > this.effects.length) {
            throw new IllegalArgumentException("index value: " + index + " is"
                + " out of bounds for a State[] of length: "
                + this.effects.length);
        }
        removedStateEffect = this.effects[index];
        effects = new State[this.effects.length - 1];
        for (int i = 0; i < effects.length; i++) {
            if (i < index) {
                effects[i] = this.effects[i];
                continue;
            }
            if (i > index) {
                effects[i] = this.effects[i - 1];
            }
        }
        setEffects(effects);

        return removedStateEffect;
    }
    /**
     * Recursively checks whether any of the States this State has caused, or
     *     any of the States they have caused, and so on, are of State.name
     *     stateName.
     * @param stateName a String containing a State.name value to search for.
     *     Cannot be null.
     * @return a boolean containing the result of the check.
     */
    public boolean hasState(String stateName) {
        boolean isPresent = false;

        HelperMethods.checkObject("stateName", stateName);
        try {
            Database.getState(stateName);
        } catch (NoSuchElementException exception) {
            return false;
        }
        // stateName is a valid State
        for (State state : this.effects) {
            if (state.getData().getName().equals(stateName)) {
                isPresent = true;
                break;
            }
            isPresent = isPresent || state.hasState(stateName);
        }

        return isPresent;
    }
}
