/**
 * Represents a single state on a mech or pilot. Contains information about the
 *     state's type, origin, duration, and effects.
 * 
 * Requires a type, source, and duration to be instantiated.
 * 
 * Used in and extended by Condition and Status.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class State {
    /**
     * This State's type (i.e. "immobilized" or "danger zone").
     * Must be a valid String as defined by State.allowedTypes.
     * Case-insensitive and stored in lowercase.
     */
    protected String type;
    /**
     * Contains an array of valid values for this.type.
     */
    private static final String[] allowedTypes = new String[] {
        "immobilized", "impaired", "jammed", "lock on", "shredded", "slowed",
        "stunned", "danger zone", "engaged", "exposed", "hidden", "invisible",
        "prone", "shut down", "down and out"};

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
    protected static final String[] allowedDurations = new String[] {"round",
        "turn", "permanent", "until removed", "source"};

    public State(String type, String source, String duration) {
        setType(type);
        setSource(source);
        setCausedBy(null);
        setEffects(new State[0]);
        setDuration(duration);
    }
    public State(State state) {
        setType(state.type);
        setSource(state.source);
        setCausedBy(state.causedBy);
        setEffects(state.effects);
        setDuration(state.duration);
    }

    public String getType() {
        return type;
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
    /**
     * Sets this.type to the provided value.
     * @param type a String which cannot be null and cannot be an invalid type,
     *     as defined by State.allowedTypes.
     * @throws IllegalArgumentException if type is null or an invalid value as
     *     defined by State.allowedTypes.
     */
    protected void setType(String type) {
        boolean isValid = false;

        if (type == null) {
            throw new IllegalArgumentException("New type value is null");
        }
        type = type.toLowerCase();
        for (String allowedType : State.allowedTypes) {
            if (type.equals(allowedType)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("New type value is \"" + type
                + "\"");
        }
        this.type = type;
    }
    protected void setSource(String source) {
        this.source = source;
    }
    protected void setCausedBy(State causedBy) {
        causedBy = new State(causedBy);
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
        effects = HelperMethods.copyOf(effects);
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
        boolean isValid = false;

        if (duration == null) {
            throw new IllegalArgumentException("New duration value is null");
        }
        duration = duration.toLowerCase();
        for (String allowedDuration : State.allowedDurations) {
            if (duration.equals(allowedDuration)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("New duration value is an"
                + " invalid value: \"" + type + "\"");
        }
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
        return equals((Status) obj);
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
        if (! this.type.equals(state.getType())) {
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
     * Adds a provided Status effect to this.effects.
     * @param effect a Status which cannot be null.
     * @throws IllegalArgumentException if effect is null.
     */
    public void addEffect(Status effect) {
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
}
