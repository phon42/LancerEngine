package packages.coreTypes.entityMechanics.stateSystem;

import main.HelperMethods;

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
     * This State's name (i.e. "Immobilized" or "Danger Zone").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;

    /**
     * The source of this state (i.e. "Taro's mech").
     * Can be any String except "". Cannot be null.
     */
    protected String source;

    /**
     * The State (if there is one) that caused this State to exist.
     * Can be any State. Can be null.
     */
    protected State causedBy;
    /**
     * Any States which this State causes to exist.
     * Can be any State[] that does not contain null elements. Cannot be null.
     */
    protected State[] effects;

    /**
     * The duration of this state (i.e. "1 round").
     * Must be a valid String as defined by State.allowedDurations.
     * Case-insensitive and stored in lowercase.
     */
    protected String duration;
    /**
     * Contains an array of possible values for this.duration.
     * - "source" - The life time of the source 
     * Case-insensitive and stored in lowercase.
     */
    protected static final String[] allowedDurations = new String[] {"1 round",
        "1 turn", "permanent", "until removed", "source"};

    public State(String name, String source, String duration) {
        setName(name);
        setSource(source);
        setCausedBy(null);
        setEffects(new State[0]);
        setDuration(duration);
    }
    public State(State state) {
        setName(state.name);
        setSource(state.source);
        setCausedBy(state.causedBy);
        setEffects(state.effects);
        setDuration(state.duration);
    }

    public String getName() {
        return name;
    }
    public String getSource() {
        return source;
    }
    public State getCausedBy() {
        return causedBy;
    }
    public State[] getEffects() {
        return effects;
    }
    public String getDuration() {
        return duration;
    }
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    protected void setSource(String source) {
        HelperMethods.checkString("source", source);
        this.source = source;
    }
    protected void setCausedBy(State causedBy) {
        if (causedBy != null) {
            causedBy = new State(causedBy);
        }
        this.causedBy = causedBy;
    }
    /**
     * Sets this.effects to the provided value.
     * @param effects a State[] which cannot be null, be of length 0, or contain
     *     null elements.
     * @throws IllegalArgumentException if effects is null, of length 0, or
     *     contains null elements.
     */
    protected void setEffects(State[] effects) {
        if (effects == null) {
            throw new IllegalArgumentException("New effects value is null");
        }
        for (State effect : effects) {
            if (effect == null) {
                throw new IllegalArgumentException("New effects array includes"
                    + " a null element");
            }
        }
        if (effects.length > 0) {
            effects = HelperMethods.copyOf(effects);
        } else {
            effects = new State[0];
        }
        this.effects = effects;
    }
    /**
     * Sets this.duration to the provided value.
     * @param duration a String which cannot be null and cannot be an invalid
     *     duration, as defined by State.allowedDurations.
     * @throws IllegalArgumentException if status is duration or an invalid
     *     value as defined by State.allowedDurations.
     */
    protected void setDuration(String duration) {
        HelperMethods.checkString("New duration", duration);
        duration = duration.toLowerCase();
        for (String allowedDuration : State.allowedDurations) {
            if (duration.equals(allowedDuration)) {
                this.duration = duration;
                return;
            }
        }
        throw new IllegalArgumentException("New duration value is an invalid"
            + " value: \"" + name + "\"");
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
        if (! this.name.equals(state.getName())) {
            return false;
        }
        if (! this.source.equals(state.getSource())) {
            return false;
        }
        if (this.causedBy == null) {
            if (state.getCausedBy() != null) {
                return false;
            }
        } else if (! this.causedBy.equals(state.getCausedBy())) {
            return false;
        }
        if (this.effects.length != state.getEffects().length) {
            return false;
        } else {
            for (int i = 0; i < this.effects.length; i++) {
                if (! this.effects[i].equals(state.getEffects()[i])) {
                    return false;
                }
            }
        }
        if (! this.duration.equals(state.getDuration())) {
            return false;
        }
        
        return true;
    }
    /**
     * Adds a provided State effect to this.effects.
     * @param effect a State which cannot be null.
     * @throws IllegalArgumentException if effect is null.
     */
    public void addEffect(State effect) {
        if (effect == null) {
            throw new IllegalArgumentException("effect is null");
        }
        setEffects(HelperMethods.append(this.effects, effect));
    }
    public State removeEffect(int index) {
        State removedEffect;
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
        removedEffect = this.effects[index];
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

        return removedEffect;
    }
    /**
     * Recursively checks whether any of the States this State has caused, or
     *     any of the States they have caused, and so on, are of State.name
     *     stateName.
     * @param stateName a String containing a State.name value to search for.
     *     Cannot be "" or null.
     * @return a boolean containing the result of the check.
     */
    public boolean hasState(String stateName) {
        boolean isPresent = false;

        if (stateName == null) {
            throw new IllegalArgumentException("stateName is null");
        }
        // TODO: fill out once Database has an array of States set up
        // stateName is a valid State
        for (State state : this.effects) {
            if (state.getName().equals(stateName)) {
                isPresent = true;
                break;
            }
            isPresent = isPresent || state.hasState(stateName);
        }

        return isPresent;
    }
}
