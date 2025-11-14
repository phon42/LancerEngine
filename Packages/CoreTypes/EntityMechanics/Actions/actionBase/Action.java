package Packages.CoreTypes.EntityMechanics.Actions.actionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBase;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Synergy;
import Packages.CoreTypes.TriState;

/**
 * Represents a single action; one of the actions available to every pilot
 *     or mech. Contains information about the action's id, name, activation
 *     speed, and what it does, among other properties.
 * 
 * Requires an action name, activation speed, detailed description, and id to be
 *     instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class Action extends ActionBase {
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
    // TODO: can you boost as a mech, eject, overcharge, then boost as a
    //     pilot?
    // TODO: if so, different mech- and pilot-exclusive actions need to be
    //     created
    // TODO: you can mount or dismount willing allied mechs or vehicles, add
    //     this
    // TODO: can you Prepare a full action? a movement? a free action? a
    //     reaction, even? (lmao)
    // TODO: cannot take reactions while holding a prepared action,
    //     implement this
    // TODO: what speed are reactions?
    // TODO: uses the fight action instead for pilots, add this
    // Required properties
    /**
     * The ID of the action (i.e. "act_move").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Whether or not to ignore the 1 use/round limit on most actions.
     * Default value: false.
     */
    private boolean ignoreUsed;
    /**
     * The default value for Action.ignoreUsed.
     */
    private static final boolean ignoreUsedDefault = false;
    /**
     * The heat cost associated with this action, if there is one.
     * Must be > -1.
     * Default value: 0.
     */
    private int heatCost;
    /**
     * The default value for Action.heatCost.
     */
    private static final int heatCostDefault = 0;

    // Optional properties
    /**
     * A very short description of what this action does (i.e. "Move your
     *     character up to its Speed in any direction.").
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String terse;
    /**
     * An array of locations that this action is connected to (i.e. a Synergy[]
     *     containing a Synergy representing "overcharge").
     * Can be any Synergy[] that does not contain null elements. Can be null.
     */
    private Synergy[] synergyLocations;
    // TODO: add example
    /**
     * An optional String that will be written to the LancerCharacter's combat
     *     log when the player commits this action (i.e. INSERT EXAMPLE).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String log;

    public Action(
        // ActionBase properties
        String name, ActivationType activation, String detailedDescription,
        TriState pilot, TriState mech, String[] confirm, Callable method,
        // Action required properties
        String id,
        // Action semi-required properties
        TriState ignoreUsed, int heatCost,
        // Action optional properties
        String terse, Synergy[] synergyLocations, String log
    ) {
        super(name, activation, detailedDescription, pilot, mech, confirm,
            method);
        // Required properties
        setID(id);
        // Semi-required properties
        setIgnoreUsed(ignoreUsed);
        setHeatCost(heatCost);
        // Optional properties
        setTerse(terse);
        setSynergyLocations(synergyLocations);
        setLog(log);
    }
    public Action(String name, ActivationType activation,
        String detailedDescription, String id) {
        super(name, activation, detailedDescription);
        // Required properties
        setID(id);
        // Semi-required properties
        setIgnoreUsed(TriState.UNSET);
        setHeatCost(-1);
    }
    public Action(Action action) {
        super(action);
        // Required properties
        setID(action.id);
        // Semi-required properties
        setIgnoreUsed(TriState.toTriState(action.ignoreUsed));
        setHeatCost(action.heatCost);
        // Optional properties
        setTerse(action.terse);
        setSynergyLocations(action.synergyLocations);
        setLog(action.log);
    }

    // Required properties
    public String getID() {
        return id;
    }
    // Semi-required properties
    public boolean isIgnoreUsed() {
        return ignoreUsed;
    }
    public int getHeatCost() {
        return heatCost;
    }
    // Optional properties
    public String getTerse() {
        return terse;
    }
    public Synergy[] getSynergyLocations() {
        if (synergyLocations == null) {
            return synergyLocations;
        }

        return HelperMethods.copyOf(synergyLocations);
    }
    public String getLog() {
        return log;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Semi-required properties
    private void setIgnoreUsed(TriState ignoreUsed) {
        HelperMethods.checkObject("ignoreUsed", ignoreUsed);
        if (ignoreUsed == TriState.UNSET) {
            this.ignoreUsed = Action.ignoreUsedDefault;
            return;
        }
        this.ignoreUsed = ignoreUsed.toBoolean();
    }
    private void setHeatCost(int heatCost) {
        if (heatCost < 0) {
            this.heatCost = Action.heatCostDefault;
            return;
        }
        this.heatCost = heatCost;
    }
    // Optional properties
    private void setTerse(String terse) {
        if (terse == null) {
            this.terse = terse;
            return;
        }
        if (terse.equals("")) {
            throw new IllegalArgumentException("terse is \"\"");
        }
        this.terse = terse;
    }
    private void setSynergyLocations(Synergy[] synergyLocations) {
        if (synergyLocations == null) {
            this.synergyLocations = synergyLocations;
            return;
        }
        HelperMethods.checkObjectArray("synergyLocations",
            synergyLocations);
        synergyLocations = HelperMethods.copyOf(synergyLocations);
        this.synergyLocations = synergyLocations;
    }
    private void setLog(String log) {
        if (log == null) {
            this.log = log;
            return;
        }
        HelperMethods.checkString("log", log);
        this.log = log;
    }
}