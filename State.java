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
    protected State[] causes;

    /**
     * The duration of this state (i.e. "1 round").
     * Must be a valid String as defined by State.allowedDurations.
     * Case-insensitive and stored in lowercase.
     */
    protected String duration;
    /**
     * Contains an array of possible values for this.duration.
     * - "lifetime" - The life time of the source 
     * Case-insensitive and stored in lowercase.
     */
    protected static final String[] allowedDurations = new String[] {"round",
        "turn", "lifetime"};

    public State(String type, String source, String duration) {
        setType(type);
        setSource(source);
        setCausedBy(null);
        setCauses(new State[0]);
        setDuration(duration);
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
    public State[] getCauses() {
        return causes;
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
        this.causedBy = causedBy;
    }
    /**
     * Sets this.causes to the provided value.
     * @param causes a String[] which cannot be null, be of length 0, or contain
     *     null elements.
     * @throws IllegalArgumentException if causes is null, of length 0, or
     *     contains null elements.
     */
    public void setCauses(State[] causes) {
        if (causes == null) {
            throw new IllegalArgumentException("New causes value is null");
        }
        for (State cause : causes) {
            if (cause == null) {
                throw new IllegalArgumentException("New causes array includes"
                    + " a null element");
            }
        }
        this.causes = causes;
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
}
