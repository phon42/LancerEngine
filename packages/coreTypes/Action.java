package packages.coreTypes;

import main.HelperMethods;
import packages.eventSystem.event.eventListener.Callable;

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
    /**
     * Whether or not this Action can be used by a Mech.
     */
    private boolean mechUseable;
    /**
     * Whether or not this Action can be used by a Pilot.
     */
    private boolean pilotUseable;
    /**
     * The method associated with this Action.
     */
    private Callable method;
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
        // TODO: can you boost as a mech, eject, overcharge, then boost as a
        //     pilot?
        // TODO: if so, different mech- and pilot-exclusive actions need to be
        //     created
        new Action("boost", "quick", "boost",
            "quick", true, true),
        new Action("continue boost", "move", "boost",
            "free", true, true),
        new Action("grapple", "quick", "quick",
            "quick", true, false),
        new Action("hide", "quick", "quick",
            "quick", true, true),
        new Action("quick tech", "quick", "quick tech",
            "quick", true, false),
        new Action("ram", "quick", "quick",
            "quick", true, false),
        new Action("search", "quick", "quick",
            "quick", true, true),
        new Action("skirmish", "quick", "quick",
            "quick", true, false),
        new Action("barrage", "full", "full",
            "full", true, false),
        new Action("disengage", "full", "full",
            "full", true, true),
        new Action("full tech", "full", "full",
            "full", true, false),
        new Action("improvised attack", "full", "full",
            "full", true, false),
        new Action("stabilize", "full", "full",
            "full", true, false),
        new Action("activate (quick)", "quick", "quick",
            "quick", true, true),
        new Action("activate (full)", "full", "full",
            "full", true, true),
        new Action("boot up", "full", "full",
            "full", true, false),
        // TODO: you can mount or dismount willing allied mechs or vehicles, add
        //     this
        new Action("mount", "full", "full",
            "full", false, true),
        new Action("dismount", "full", "full",
            "full", true, false),
        new Action("eject", "quick", "quick", 
            "quick", true, false),
        // TODO: can you Prepare a full action? a movement? a free action? a
        //     reaction, even? (lmao)
        // TODO: cannot take reactions while holding a prepared action,
        //     implement this
        new Action("prepare", "quick", "quick",
            "quick", true, true),
        new Action("drop prepare", "free", "free",
            "free", true, true),
        new Action("self-destruct", "quick", "quick",
            "quick", true, false),
        new Action("shut down", "quick", "quick",
            "quick", true, false),
        new Action("skill check", "full", "full",
            "full", true, true),
        new Action("overcharge", "free", "free",
            "free", true, false),
        // TODO: what speed are reactions?
        new Action("reaction", "reaction", "reaction",
            "reaction", true, false),
        new Action("brace", "reaction", "reaction",
            "reaction", true, false),
        // TODO: uses the fight action instead for pilots, add this
        new Action("overwatch", "reaction", "reaction",
            "reaction", true, true),
        new Action("fight", "full", "full",
            "full", false, true),
        new Action("jockey", "full", "full",
            "full", false, true),
        new Action("reload", "quick", "quick",
            "quick", false, true),
        new Action("transfer control", "protocol",
            "protocol", "free", true,
            false),
    };

    public Action(String name, String type, String detailedType, String speed,
        boolean mechUseable, boolean pilotUseable) {
        setName(name);
        setType(type);
        setDetailedType(detailedType);
        setSpeed(speed);
        setMechUseable(mechUseable);
        setPilotUseable(pilotUseable);
    }
    public Action(String name, String type, String detailedType, String speed) {
        this(name, type, detailedType, speed, true,
            true);
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
                setMechUseable(action.mechUseable);
                setPilotUseable(action.pilotUseable);
                break;
            }
        }
    }
    public Action(Action action) {
        setName(action.name);
        setType(action.type);
        setDetailedType(action.detailedType);
        setSpeed(action.speed);
        setMechUseable(action.mechUseable);
        setPilotUseable(action.pilotUseable);
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
    public boolean isMechUseable() {
        return mechUseable;
    }
    public boolean isPilotUseable() {
        return pilotUseable;
    }
    public Callable getMethod() {
        return method;
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
    public void setPilotUseable(boolean pilotUseable) {
        this.pilotUseable = pilotUseable;
    }
    public void setMechUseable(boolean mechUseable) {
        this.mechUseable = mechUseable;
    }
    /**
     * Sets this.method to the provided value.
     * @param method a Callable which cannot be null.
     */
    public void setMethod(Callable method) {
        Callable copy;

        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }
        copy = new Callable() {
            @Override
            public void run() {
                method.run();
            }
        };
        this.method = copy;
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
