package packages.coreTypes;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 */
/**
 * Represents a single action, which may be one of the actions available to
 *     every pilot and/or mech, or a special action granted by a weapon, system,
 *     etc. Contains information about the action's speed, where it's from, and
 *     what it does.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Action {
    // TODO: reminder to add handing over control (see pg. 107)
    // TODO: remember that pilots in cascading mechs can only use the SHUT DOWN
    //     action (see pg. 107)
    // TODO: fill out
    /**
     * The general type of the action (i.e. "move").
     * Must be a valid type as defined by Action.allowedTypes.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String type;
    /**
     * Stores an array of valid values for this.type.
     */
    private static final String[] allowedTypes = {"move", "full", "quick",
        "reaction", "protocol", "free"};
    /**
     * The more detailed type of the action (i.e. "quick_tech").
     * Must be a valid type as defined by Action.allowedDetailedTypes.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String detailedType;
    /**
     * Stores an array of valid values for this.detailedType.
     */
    private static final String[] allowedDetailedTypes = {"move", "full",
        "quick", "quick_tech", "invade", "reaction", "protocol", "free"};
    /**
     * The speed at which the action takes place (i.e. "full").
     * Must be a valid type as defined by Action.allowedSpeeds.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String speed;
    /**
     * Stores an array of valid values for this.speed.
     */
    private static final String[] allowedSpeeds = {"full", "quick", "free"};
}
