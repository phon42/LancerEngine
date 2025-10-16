/**
 * Represents a single status on a mech or pilot. Contains information about the
 *     status' type, origin, duration, and effects.
 * 
 * Requires a type, source, and duration to be instantiated.
 * 
 * Used in Mech and Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Status extends State {
    /**
     * Contains an array of valid values for a Status.
     */
    private static final String[] allowedStatuses = new String[] {
        "danger zone", "engaged", "exposed", "hidden", "invisible", "prone",
        "shut down", "down and out"};
    /**
     * Contains an array of valid values for elements of Mech.statuses.
     *     Case-insensitive and stored in lowercase.
     */
    public static final String[] allowedMechStatuses = new String[] {
        "danger zone", "engaged", "exposed", "hidden", "invisible", "prone",
        "shut down"};
    /**
     * Contains an array of valid values for elements of Pilot.statuses.
     *     Case-insensitive and stored in lowercase.
     */
    public static final String[] allowedPilotStatuses = new String[] {
        "down and out", "engaged", "hidden", "invisible", "prone"};

    public Status(String type, String source, String duration) {
        super(type, source, duration);
    }

    /**
     * Sets this.type to the provided value.
     * @param type a String which cannot be null and cannot be an invalid type,
     *     as defined by Status.allowedStatuses.
     * @throws IllegalArgumentException if type is null or an invalid value as
     *     defined by Status.allowedStatuses.
     */
    @Override
    protected void setType(String type) {
        boolean isValid = false;

        if (type == null) {
            throw new IllegalArgumentException("New type value is null");
        }
        type = type.toLowerCase();
        for (String allowedType : Status.allowedStatuses) {
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
