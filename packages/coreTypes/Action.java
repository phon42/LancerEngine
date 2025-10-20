package packages.coreTypes;

import main.HelperMethods;

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
     * The name of the action (i.e. "Turret Attack").
     * Can be any String except "". Cannot be null.
     */
    private String name;
    /**
     * The general type of the action (i.e. "move").
     * Must be a valid type as defined by Action.allowedTypes.
     * Case-insensitive and stored in lowercase. Cannot be null.

     * Meaning of each value:
     * "STANDARD MOVE
     * Movement up to a character’s maximum SPEED.
     * QUICK ACTION
     * Actions that take a few moments, such as quickly firing a weapon,
     *     activating a system, or moving further.
     * REACTION
     * Reactions are special actions that can be made outside of the usual turn
     *     order as responses to incoming attacks, enemy movement, and other
     *     events.
     * FULL ACTION
     * Actions that require full attention (e.g. firing a barrage, performing
     *     field repairs on a mech).
     * FREE ACTION
     * Free actions are special actions granted by character traits, like mech
     *     systems and talents."
     * - pg. 61
     * -------------------------------------------------------------------------
     * "Unlike the standard action types, there is no limit to how many free
     *     actions and reactions a character can take per round. Characters can
     *     even take reactions outside of their turn."
     * - pg. 61
     */
    private String type;
    /**
     * Stores an array of valid values for this.type.
     */
    private static final String[] allowedTypes = {"move", "quick", "full",
        "free", "protocol", "reaction"};
    /**
     * The more detailed type of the action (i.e. "quick tech").
     * Must be a valid type as defined by Action.allowedDetailedTypes.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String detailedType;
    /**
     * Stores an array of valid values for this.detailedType.
     */
    private static final String[] allowedDetailedTypes = {"move", "boost",
        "quick", "quick tech", "invade", "full", "free", "protocol",
        "reaction"};
    /**
     * The speed at which the action takes place (i.e. "full").
     * Must be a valid type as defined by Action.allowedSpeeds.
     * Case-insensitive and stored in lowercase. Cannot be null.
     * 
     * Equivalent of every valid value for this.type:
     * - "move"     -> "free"
     * - "quick"    -> "quick"
     * - "full"     -> "full"
     * - "free"     -> "free"
     * - "protocol" -> "free"
     * - "reaction" -> "reaction"
     */
    private String speed;
    /**
     * Contains an array of valid values for this.speed.
     */
    private static final String[] allowedSpeeds = {"full", "quick", "free",
        "reaction"};
    // TODO: "Unlike other quick actions, QUICK TECH can be taken more than once
    //     per turn; however, a different option must be chosen every time,
    //     unless specified otherwise or granted as a free action."
    // - pg. 69
    // Based on this, should Quick Tech be an element in Action.baseActions or
    //     only its options?
    // TODO: can you invade multiple times a turn with different options?
    // TODO: same for activate (quick)
    // TODO: when you seperate Boost into several movements, does the second+
    //     movement also trigger systems or traits that work off of Boosting?
    // TODO: what speed are reactions?
    /**
     * Contains an array of valid names of base actions.
     */
    private static final String[] baseActionNames = {"boost", "continue boost",
        "grapple", "hide", "quick tech", "ram", "search", "skirmish", "barrage",
        "disengage", "full tech", "improvised attack", "stabilize",
        "activate (quick)",
        "activate (full)", "boot up", "mount", "dismount", "eject", "prepare", "drop prepare",
        "self-destruct", "shut down", "skill check", "overcharge", "reaction",
        "brace", "overwatch", "fight", "jockey", "reload", "transfer control"};
    /**
     * Contains an array of base actions.
     */
    private static final Action[] baseActions = {
        new Action("boost", "quick", "boost",
            "quick"),
        new Action("continue boost", "move", "boost",
            "free"),
        new Action("grapple", "quick", "quick",
            "quick"),
        new Action("hide", "quick", "quick",
            "quick"),
        new Action("quick tech", "quick", "quick tech",
            "quick"),
        new Action("ram", "quick", "quick",
            "quick"),
        new Action("search", "quick", "quick",
            "quick"),
        new Action("skirmish", "quick", "quick",
            "quick"),
        new Action("barrage", "full", "full",
            "full"),
        new Action("disengage", "full", "full",
            "full"),
        new Action("full tech", "full", "full",
            "full"),
        new Action("improvised attack", "full", "full",
            "full"),
        new Action("stabilize", "full", "full",
            "full"),
        new Action("activate (quick)", "quick", "quick",
            "quick"),
        new Action("activate (full)", "full", "full",
            "full"),
        new Action("boot up", "full", "full",
            "full"),
        new Action("mount", "full", "full",
            "full"),
        new Action("dismount", "full", "full",
            "full"),
        new Action("eject", "quick", "quick", 
            "quick"),
        new Action("prepare", "quick", "quick",
            "quick"),
        new Action("drop prepare", "free", "free",
            "free"),
        new Action("self-destruct", "quick", "quick",
            "quick"),
        new Action("shut down", "quick", "quick",
            "quick"),
        new Action("skill check", "full", "full",
            "full"),
        new Action("overcharge", "free", "free",
            "free"),
        // TODO: what speed are reactions?
        new Action("reaction", "reaction", "reaction",
            "reaction"),
        new Action("brace", "reaction", "reaction",
            "reaction"),
        new Action("overwatch", "reaction", "reaction",
            "reaction"),
        new Action("fight", "full", "full",
            "full"),
        new Action("jockey", "full", "full",
            "full"),
        new Action("reload", "quick", "quick",
            "quick"),
        new Action("transfer control", "protocol",
            "protocol", "free"),
    };
    
    public Action(String name, String type, String detailedType, String speed) {
        setName(name);
        setType(type);
        setDetailedType(detailedType);
        setSpeed(speed);
    }
    /**
     * Creates one of the 34 total base actions detailed within pgs. 69 - 75 (30
     *     mixed mech/pilot actions, 3 pilot actions, and the ability to hand
     *     control over to an NHP, if you have one, as a protocol).
     * @param actionTemplate a String which must be a valid base action name as
     *     defined by Action.baseActionNames.
     */
    public Action(String actionTemplate) {
        HelperMethods.checkString("actionTemplate",
            actionTemplate);
        if (! Action.isValidBaseAction(actionTemplate)) {
            throw new IllegalArgumentException("actionTemplate value: \""
                + actionTemplate + "\" is not a valid base action name");
        }
        for (Action action : Action.baseActions) {
            if (actionTemplate.equals(action.getName())) {
                setName(action.name);
                setType(action.type);
                setDetailedType(action.detailedType);
                setSpeed(action.speed);
                break;
            }
        }
    }
    public Action(Action action) {
        setName(action.name);
        setType(action.type);
        setDetailedType(action.detailedType);
        setSpeed(action.speed);
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDetailedType() {
        return detailedType;
    }
    public String getSpeed() {
        return speed;
    }
    /**
     * Sets this.type to the provided value.
     * @param type a String which cannot be "". Cannot be null.
     */
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    /**
     * Sets this.type to the provided value.
     * @param type a String which must be a valid type as defined by
     *     Action.allowedTypes. Cannot be null.
     */
    public void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        if (! Action.isValidType(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }
    /**
     * Sets this.detailedType to the provided value.
     * @param type a String which must be a valid detailedType as defined by
     *     Action.allowedDetailTypes. Cannot be null.
     */
    public void setDetailedType(String detailedType) {
        HelperMethods.checkString("detailedType", detailedType);
        detailedType = detailedType.toLowerCase();
        if (! Action.isValidDetailedType(detailedType)) {
            throw new IllegalArgumentException("detailedType value: \""
                + detailedType + "\" is an invalid detailedType");
        }
        this.detailedType = detailedType;
    }
    /**
     * Sets this.speed to the provided value.
     * @param type a String which must be a valid speed as defined by
     *     Action.allowedSpeeds. Cannot be null.
     */
    public void setSpeed(String speed) {
        HelperMethods.checkString("speed", speed);
        speed = speed.toLowerCase();
        if (! Action.isValidSpeed(speed)) {
            throw new IllegalArgumentException("speed value: \"" + speed + "\""
                + " is an invalid type");
        }
        this.speed = speed;
    }

    public static boolean isValidType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        for (String validType : Action.allowedTypes) {
            if (type.equals(validType)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidDetailedType(String detailedType) {
        if (detailedType == null) {
            throw new IllegalArgumentException("detailedType is null");
        }
        for (String validDetailedType : Action.allowedDetailedTypes) {
            if (detailedType.equals(validDetailedType)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidSpeed(String speed) {
        if (speed == null) {
            throw new IllegalArgumentException("speed is null");
        }
        for (String validSpeed : Action.allowedSpeeds) {
            if (speed.equals(validSpeed)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidBaseAction(String baseAction) {
        if (baseAction == null) {
            throw new IllegalArgumentException("baseAction is null");
        }
        for (String validBaseAction : Action.baseActionNames) {
            if (baseAction.equals(validBaseAction)) {
                return true;
            }
        }
        return false;
    }
}
