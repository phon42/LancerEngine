/**
 * Represents an event of some kind. Contains information about the event's
 *     origin and type.
 * 
 * Requires an origin and a type to be instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Event {
    /**
     * The event's origin (i.e. "Main.main()").
     * Can be any String except "". Cannot be null.
     */
    private String origin;

    /**
     * The event's type (i.e. "damage_taken").
     * Must be a valid value as defined by Event.allowedTypes.
     * Case-insensitive and stored in lowercase.
     */
    private String type;
    /**
     * Contains an array of valid values for this.type.
     */
    private static final String[] allowedTypes = new String[] {"damage_taken"};

    public Event(String origin, String type) {
        setOrigin(origin);
        setType(type);
    }

    public String getOrigin() {
        return origin;
    }
    public String getType() {
        return type;
    }
    /**
     * Sets this.origin to the provided value.
     * @param origin a String which cannot be "". Cannot be null.
     * @throws IllegalArgumentException if origin is "" or null.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
        if (origin == null) {
            throw new IllegalArgumentException("New origin value is null");
        }
        if (origin.equals("")) {
            throw new IllegalArgumentException("New origin value is \"\"");
        }
        this.origin = origin;
    }
    /**
     * Sets this.duration to the provided value.
     * @param duration a String which cannot be null and cannot be an invalid
     *     value as defined by Event.allowedTypes.
     * @throws IllegalArgumentException if status is duration or an invalid
     *     value as defined by Event.allowedTypes.
     */
    public void setType(String type) {
        boolean isValid = false;

        if (type == null) {
            throw new IllegalArgumentException("New duration value is null");
        }
        type = type.toLowerCase();
        for (String allowedType : Event.allowedTypes) {
            if (type.equals(allowedType)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("New type value is an invalid"
                + " value: \"" + type + "\"");
        }
        this.type = type;
    }
}
