package packages.CoreTypes.entityMechanics.stateSystem.state;

import main.HelperMethods;
import packages.CoreTypes.entityMechanics.stateSystem.State;

/**
 * See pg. 77.
 * "Conditions are temporary effects caused by things like damage and electronic
 *     warfare, whereas statuses are usually effects that can’t easily be
 *     cleared."
 * - pg. 77
 */
/**
 * Represents a single status on a mech or pilot. Contains information about the
 *     status' name, origin, duration, and effects.
 * 
 * Requires a name, source, and duration to be instantiated.
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

    public Status(String name, String source, String duration) {
        super(name, source, duration);
    }
    public Status(Status status) {
        // TODO: update
        super(status);
    }

    /**
     * Sets this.name to the provided value.
     * @param name a String which cannot be null and cannot be an invalid name,
     *     as defined by Status.allowedStatuses.
     * @throws IllegalArgumentException if name is null or an invalid value as
     *     defined by Status.allowedStatuses.
     */
    @Override
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        name = name.toLowerCase();
        for (String allowedName : Status.allowedStatuses) {
            if (name.equals(allowedName)) {
                this.name = name;
                return;
            }
        }
        throw new IllegalArgumentException("New name value is an invalid value:"
            + " \"" + name + "\"");
    }
}
