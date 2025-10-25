package packages.stateSystem.state;

import main.HelperMethods;
import packages.stateSystem.State;

/**
 * See pg. 78.
 * "Conditions are temporary effects caused by things like damage and electronic
 *     warfare, whereas statuses are usually effects that can’t easily be
 *     cleared."
 * - pg. 77
 */
/**
 * Represents a single condition on a mech or pilot. Contains information about
 *     the condition's type, origin, duration, and effects.
 * 
 * Requires a type, source, and duration to be instantiated.
 * 
 * Used in Mech and Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Condition extends State {
    /**
     * Contains an array of valid values for a Condition.
     */
    public static final String[] allowedConditions = new String[] {
        "immobilized", "impaired", "jammed", "lock on", "shredded", "slowed",
        "stunned"};

    public Condition(String type, String source, String duration) {
        super(type, source, duration);
    }

    /**
     * Sets this.type to the provided value.
     * @param type a String which cannot be null and cannot be an invalid type,
     *     as defined by Condition.allowedConditions.
     * @throws IllegalArgumentException if type is null or an invalid value as
     *     defined by Condition.allowedConditions.
     */
    @Override
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        for (String allowedType : Condition.allowedConditions) {
            if (type.equals(allowedType)) {
                this.type = type;
                return;
            }
        }
        throw new IllegalArgumentException("New type value is an invalid value:"
            + " \"" + type + "\"");
    }
}
