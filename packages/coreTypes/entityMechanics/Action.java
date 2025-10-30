package packages.coreTypes.entityMechanics;

import main.HelperMethods;
import packages.coreTypes.Callable;
import packages.coreTypes.entityMechanics.harmSystem.Damage;

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
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Action {
    // TODO: add an LCP that adds all the actions not covered by the base LCP:
    // private static final String[] baseActionNames = {"boost", "continue boost",
    //     "grapple", "hide", "quick tech", "ram", "search", "skirmish", "barrage",
    //     "disengage", "full tech", "improvised attack", "stabilize",
    //     "activate (quick)", "activate (full)", "boot up", "mount", "dismount",
    //     "eject", "prepare", "drop prepare", "self-destruct", "shut down",
    //     "skill check", "overcharge", "reaction", "brace", "overwatch", "fight",
    //     "jockey", "reload", "transfer control"};
    // TODO: remember that pilots in cascading mechs can only use the SHUT DOWN
    //     action (see pg. 107)
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
    // Main properties
    /**
     * The name of the action (i.e. "Move" or "Turret Attack").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The speed at which this action can be activated (i.e. "move").
     * Must be a valid value for activation as defined by
     *     Action.allowedActivations. Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String activation;
    /**
     * Contains an array of valid values for Action.activation.
     * Case-insensitive and stored in lowercase.
     */
    private static String[] allowedActivations = new String[] {
        "move", "free", "quick", "quick tech", "invade", "full", "reaction",
        "downtime", "protocol", "full tech"
    };
    /**
     * A very short description of what this action does (i.e. "Move your
     *     character up to its Speed in any direction."). For certain actions
     *     (i.e. actions attached to mech systems) it is the same as
     *     this.detail.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String terse;
    /**
     * A much more verbose description of what this action does.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String detail;
    /**
     * Whether or not this action can be used by a Pilot.
     */
    private boolean pilotUseable;
    /**
     * Whether or not this action can be used by a Mech.
     */
    private boolean mechUseable;
    // TODO: fill out
    /**
     * Unknown purpose.
     */
    private boolean hideActive;
    /**
     * An array of locations that this action is connected to (i.e. a String[]
     *     containing the String "overcharge").
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     * Elements are case-insensitive and stored in lowercase.
     */
    private String[] synergyLocations;
    /**
     * An array of lines of flavor text that can be printed out when this action
     *     is executed (i.e. a String[] containing the String ""REACTOR LIMITER"
     *     " DISABLED. CORE TEMPERATURES RISING."").
     * Can be any String[] that does not contain null elements. Cannot be null.
     * Elements are case-sensitive.
     */
    private String[] confirm;
    /**
     * Whether or not to ignore the 1 use/round limit on most actions.
     */
    private boolean ignoreUsed;
    /**
     * The heat cost associated with this action, if there is one.
     * Must be > -1.
     */
    private int heatCost;
    /**
     * Whether this action is a tech attack. Is only true if this.activation is
     *     "quick tech" or "full tech".
     */
    private boolean techAttack;
    // TODO: Brace and Overwatch are 1/round but not labeled as such in
    //     actions.json
    /**
     * The frequency with which this action can be used, if there is one. If
     *     this.activation is "reaction", the following restrictions apply:
     *     Can be any Frequency. Cannot be null.
     * Otherwise, is 1/round if this.ignoreUsed is false and unlimited if it's
     *     true. The following restrictions apply:
     *     Must be a Frequency that is either 1/round or unlimited. Cannot be
     *         null.
     * Can be any Frequency with the restrictions described above. Cannot be
     *     null.
     */
    private Frequency frequency;

    // Semi-optional data properties (optional for system actions but not for
    //     base actions)
    /**
     * The ID of the action (i.e. "act_move"). Is required for base actions, and
     *     in these cases, the following restrictions apply:
     *     Can be any String except "". Cannot be null.
     *     Case-insensitive and stored in lowercase.
     * Is not required for base actions, and in these cases, is null.
     * Overall:
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;

    // Optional data properties
    /**
     * The ranges associated with this action, if there are any.
     * Can be any RangeTag[] that does not contain elements that are null.
     *     Can be null.
     * Elements can be any RangeTag. They cannot be null.
     */
    private RangeTag[] range;
    /**
     * The damages associated with this action, if there are any.
     * Can be any Damage[] that does not contain elements that are null.
     *     Can be null.
     * Elements can be any Damage. They cannot be null.
     */
    private Damage[] damage;
    // TODO: Brace and Overwatch do not have triggers listed
    /**
     * The trigger for this action, if there is one. Is only present for actions
     *     with a this.activation property of "reaction". In this case, the
     *     following restrictions apply:
     *     Can be any String except "". Cannot be null.
     * Otherwise, the following restrictions apply:
     *     Must be null.
     * Overall:
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String trigger;
    // TODO: fill out
    /**
     * Literally only present for "Unleash SCYLLA Reaction" from the
     *     "SCYLLA-Class NHP" from Gorgon III. Genuinely might be a typo.
     */
    private String init;

    // Helper properties
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
    private static final String[] allowedTypes = {"move", "free", "quick",
        "full", "reaction", "downtime", "protocol"};
    /**
     * The method associated with this Action.
     */
    private Callable method;

    /**
     * Contains an array of valid names of base actions.
     * See pgs. 68 - 76 and 107.
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
        // set main data properties
        setName(action.name);
        setActivation(action.activation);
        setTerse(action.terse);
        setDetail(action.detail);
        setPilotUseable(action.pilotUseable);
        setMechUseable(action.mechUseable);
        setHideActive(action.hideActive);
        setSynergyLocations(action.synergyLocations);
        setConfirm(action.confirm);
        setIgnoreUsed(action.ignoreUsed);
        setHeatCost(action.heatCost);
        setTechAttack(action.techAttack);
        setFrequency(action.frequency);

        // Semi-optional data properties (optional for system actions but not
        //     for base actions)
        setID(action.id);

        // set optional data properties
        setRange(action.range);
        setDamage(action.damage);
        setTrigger(action.trigger);
        setInit(action.init);

        // set the helper properties
        setType();
        setMethod(action.method);
    }

    public String getName() {
        return name;
    }
    public String getActivation() {
        return activation;
    }
    public String getTerse() {
        return terse;
    }
    public String getDetail() {
        return detail;
    }
    public boolean isPilotUseable() {
        return pilotUseable;
    }
    public boolean isMechUseable() {
        return mechUseable;
    }
    public boolean isHideActive() {
        return hideActive;
    }
    public String[] getSynergyLocations() {
        return HelperMethods.copyOf(synergyLocations);
    }
    public String[] getConfirm() {
        return HelperMethods.copyOf(confirm);
    }
    public boolean isIgnoreUsed() {
        return ignoreUsed;
    }
    public int getHeatCost() {
        return heatCost;
    }
    public boolean isTechAttack() {
        return techAttack;
    }
    public Frequency getFrequency() {
        return new Frequency(frequency);
    }
    // Semi-optional data properties (optional for system actions but not for
    //     base actions)
    public String getID() {
        return id;
    }
    // Optional data properties
    public RangeTag[] getRange() {
        return HelperMethods.copyOf(range);
    }
    public Damage[] getDamage() {
        return HelperMethods.copyOf(damage);
    }
    public String getTrigger() {
        return trigger;
    }
    public String getInit() {
        return init;
    }
    // Helper properties
    public String getType() {
        return type;
    }
    public Callable getMethod() {
        return HelperMethods.copyOf(method);
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    /**
     * Sets this.name to the provided value.
     * @param type a String which must be a valid Action.activation value as
     *     defined by Action.allowedActivations. Cannot be null.
     */
    private void setActivation(String activation) {
        HelperMethods.checkString("activation", activation);
        for (int i = 0; i < Action.allowedActivations.length; i++) {
            if (activation.equals(Action.allowedActivations[i])) {
                break;
            }
            if (i == Action.allowedActivations.length) {
                throw new IllegalArgumentException("activation value: \""
                    + activation + "\" is not a valid value for"
                    + " Action.activation");
            }
        }
        this.activation = activation;
    }
    private void setTerse(String terse) {
        HelperMethods.checkString("terse", terse);
        this.terse = terse;
    }
    private void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        this.detail = detail;
    }
    private void setPilotUseable(boolean pilotUseable) {
        this.pilotUseable = pilotUseable;
    }
    private void setMechUseable(boolean mechUseable) {
        this.mechUseable = mechUseable;
    }
    private void setHideActive(boolean hideActive) {
        this.hideActive = hideActive;
    }
    /**
     * add documentation
     * @param synergyLocations
     */
    private void setSynergyLocations(String[] synergyLocations) {
        if (synergyLocations == null) {
            throw new IllegalArgumentException("synergyLocations is null");
        }
        for (int i = 0; i < synergyLocations.length; i++) {
            if (synergyLocations[i] == null) {
                throw new IllegalArgumentException("synergyLocations array"
                    + " contains a null element");
            }
            if (synergyLocations[i].equals("")) {
                throw new IllegalArgumentException("synergyLocations array"
                    + " contains an element that is \"\"");
            }
            synergyLocations[i] = synergyLocations[i].toLowerCase();
        }
        synergyLocations = HelperMethods.copyOf(synergyLocations);
        this.synergyLocations = synergyLocations;
    }
    /**
     * add documentation
     * @param confirm
     */
    private void setConfirm(String[] confirm) {
        if (confirm == null) {
            throw new IllegalArgumentException("confirm is null");
        }
        for (int i = 0; i < confirm.length; i++) {
            if (confirm[i] == null) {
                throw new IllegalArgumentException("confirm array contains a"
                    + " null element");
            }
            if (confirm[i].equals("")) {
                throw new IllegalArgumentException("confirm array contains an"
                    + " element that is \"\"");
            }
        }
        confirm = HelperMethods.copyOf(confirm);
        this.confirm = confirm;
    }
    private void setIgnoreUsed(boolean ignoreUsed) {
        this.ignoreUsed = ignoreUsed;
    }
    private void setHeatCost(int heatCost) {
        if (heatCost < 0) {
            throw new IllegalArgumentException("heatCost value: " + heatCost
                + " is < 0");
        }
        this.heatCost = heatCost;
    }
    private void setTechAttack(boolean techAttack) {
        if (! (this.activation.equals("quick tech")
            && this.activation.equals("full tech"))) {
            if (techAttack) {
                throw new IllegalArgumentException("techAttack is true when"
                    + " this.activation is: \"" + this.activation + "\"");
            }
        }
        this.techAttack = techAttack;
    }
    // Semi-optional data properties (optional for system actions but not for
    //     regular actions)
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Optional data properties
    private void setRange(RangeTag[] range) {
        if (range != null) {
            for (RangeTag rangeTag : range) {
                if (rangeTag == null) {
                    throw new IllegalArgumentException("range array contains a"
                        + " null element");
                }
            }
        }
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    private void setDamage(Damage[] damage) {
        if (damage != null) {
            for (Damage damageTag : damage) {
                if (damageTag == null) {
                    throw new IllegalArgumentException("damage array contains a"
                        + " null element");
                }
            }
        }
        damage = HelperMethods.copyOf(damage);
        this.damage = damage;
    }
    private void setTrigger(String trigger) {
        if (this.activation.equals("reaction")) {
            HelperMethods.checkString("trigger", trigger);
        } else {
            if (trigger != null) {
                throw new IllegalArgumentException("This.trigger must be null"
                    + " when this.activation is not \"reaction\"");
            }
        }
        this.trigger = trigger;
    }
    // TODO: genuinely have no fucking clue
    private void setInit(String init) {
        this.init = init;
    }
    // Helper properties
    private void setType() {
        // checking for part of "move"
        if (this.activation.equals("move")) {
            this.type = "move";
        }
        // checking for "free", part of "quick", part of "full", "reaction",
        //     "downtime", and "protocol"
        if (this.activation.equals("free")
            || this.activation.equals("quick")
            || this.activation.equals("full")
            || this.activation.equals("reaction")
            || this.activation.equals("downtime")
            || this.activation.equals("protocol")) {
            this.type = this.activation;
        // checking for the rest of "quick":
        } else if (this.activation.equals("quick tech")
            || this.activation.equals("invade")) {
            this.type = "quick";
        // checking for the rest of "full":
        } else if (this.activation.equals("full tech")) {
            this.type = "full";
        // checking for the rest of "move"
        } else if (this.id != null) {
            if (this.id.equals("act_boost")) {
                this.type = "move";
            }
        }
        for (int i = 0; i < Action.allowedTypes.length; i++) {
            if (this.type.equals(Action.allowedTypes[i])) {
                break;
            }
            if (i == Action.allowedTypes.length - 1) {
                throw new IllegalStateException("type value: \"" + type + "\""
                    + " is an invalid type");
            }
        }
    }
    /**
     * Sets this.method to the provided value.
     * @param method a Callable which cannot be null.
     */
    public void setMethod(Callable method) {
        if (method != null) {
            method = HelperMethods.copyOf(method);
        }
        this.method = method;
    }
}