package Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.ActionBase;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.Frequency;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.frequency.FrequencyType;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified.SynergyLocation;
import Packages.CoreTypes.TriState;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson.
 */
/**
 * Represents a single verified action; one of the actions available to every
 *     pilot or mech. Contains information about the action's id, name,
 *     activation speed, and what it does, among other properties.
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
    // protected static final String[] baseActionNames = {"boost", "continue boost",
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
    // Required property
    /**
     * The ID of the action (i.e. "act_move").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Whether or not to ignore the 1 use/round limit on most actions.
     * Default value: false.
     */
    protected boolean ignoreUsed;
    /**
     * The default value for Action.ignoreUsed.
     */
    protected static final boolean ignoreUsedDefault = false;
    /**
     * The heat cost associated with this action, if there is one.
     * Must be > -1.
     * Default value: 0.
     */
    protected int heatCost;
    /**
     * The default value for Action.heatCost.
     */
    protected static final int heatCostDefault = 0;

    // Optional properties
    /**
     * A very short description of what this action does (i.e. "Move your
     *     character up to its Speed in any direction.").
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    protected String terse;
    // TODO: add example
    /**
     * An optional String that will be written to the LancerCharacter's combat
     *     log when the player commits this action (i.e. INSERT EXAMPLE).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    protected String log;

    /**
     * A constructor using all possible properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:                 PROVIDED
     */
    public Action(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger, Callable method,
        String requiredInitialConditions, ActivationType activation,
        Frequency frequency, SynergyLocation[] synergyLocations,
        // Required property
        String id,
        // Semi-required properties
        TriState ignoreUsed, int heatCost,
        // Optional properties
        String terse, String log
    ) {
        // ActionBase properties
        super(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, method, requiredInitialConditions, activation, frequency,
            synergyLocations);
        // Required property
        setID(id);
        // Semi-required properties
        setIgnoreUsed(ignoreUsed);
        setHeatCost(heatCost);
        // Optional properties
        setTerse(terse);
        setLog(log);
    }
    /**
     * A constructor using all possible properties except the optional
     *     properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public Action(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger,
        ActivationType activation, Frequency frequency,
        // Required property
        String id,
        // Semi-required properties
        TriState ignoreUsed, int heatCost
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, null, null, activation,
            frequency, null, id, ignoreUsed, heatCost,
            null, null);
    }
    /**
     * A constructor using all possible properties except the optional and
     *     conditionally required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public Action(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, ActivationType activation,
        // Required property
        String id,
        // Semi-required properties
        TriState ignoreUsed, int heatCost
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            null, null, null,
            activation, null, null, id, ignoreUsed,
            heatCost, null, null);
    }
    /**
     * A constructor using all possible properties except the optional and semi-
     *     required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public Action(
        // ActionBase properties
        String name, String detailedDescription, String trigger,
        ActivationType activation, Frequency frequency,
        // Required property
        String id
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, trigger, null,
            null, activation, frequency,
            null, id, TriState.UNSET, Action.heatCostDefault,
            null, null);
    }
    /**
     * A constructor using only the required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public Action(
        // ActionBase properties
        String name, String detailedDescription, ActivationType activation,
        // Required property
        String id
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, null, null,
            null, activation, null,
            null, id, TriState.UNSET, Action.heatCostDefault,
            null, null);
    }

    // Required property
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
    public String getLog() {
        return log;
    }
    // Required property
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Semi-required properties
    protected void setIgnoreUsed(boolean ignoreUsed) {
        Frequency frequency;

        this.ignoreUsed = ignoreUsed;
        frequency = calculateFrequency();
        if (frequency != null) {
            setFrequency(frequency);
        }
    }
    protected void setHeatCost(int heatCost) {
        if (heatCost < 0) {
            this.heatCost = Action.heatCostDefault;
            return;
        }
        this.heatCost = heatCost;
    }
    // Optional properties
    protected void setTerse(String terse) {
        if (terse == null) {
            this.terse = terse;
            return;
        }
        if (terse.equals("")) {
            throw new IllegalArgumentException("terse is \"\"");
        }
        this.terse = terse;
    }
    protected void setLog(String log) {
        if (log == null) {
            this.log = log;
            return;
        }
        HelperMethods.checkString("log", log);
        this.log = log;
    }

    /**
     * If this object is a reaction, checks that this.frequency isn't null.
     * Otherwise, checks that:
     * - this.frequency is either "X/round" or "Unlimited".
     * - this.frequency is the correct value based on this.ignoreUsed.
     */
    @Override
    protected void verifyProperties() {
        boolean isValidFreq;

        super.verifyProperties();
        if (! this.activation.getType().equals("reaction")) {
            // do nothing, because this.frequency cannot be null.
            if (this.ignoreUsed) {
                isValidFreq = this.frequency.getType().getValue()
                    .equals("Unlimited");
                if (! isValidFreq) {
                    throw new IllegalStateException("this.frequency cannot"
                        + " be anything other than \"Unlimited\" when"
                        + " this.activation is not a reaction and"
                        + " this.ignoreUsed is true");
                }
            } else {
                isValidFreq = this.frequency.getType().getValue()
                    .equals("X/round");
                if (! (isValidFreq && this.frequency.getValue() == 1)) {
                    throw new IllegalStateException("this.frequency cannot"
                        + " be anything other than \"1/round\" when"
                        + " this.activation is not a reaction and"
                        + " this.ignoreUsed is false");
                }
            }
        }
    }
    protected Frequency calculateFrequency() {
        FrequencyType frequencyType;

        if (this.ignoreUsed) {
            try {
                frequencyType =
                    Database.getFrequencyType("Unlimited");

                return new Frequency(frequencyType);
            } catch (NoSuchElementException exception) {}
        } else {
            try {
                frequencyType =
                    Database.getFrequencyType("X/round");

                return new Frequency(frequencyType, 1);
            } catch (NoSuchElementException exception) {}
        }

        return null;
    }
    protected void setIgnoreUsed(TriState ignoreUsed) {
        HelperMethods.checkObject("ignoreUsed", ignoreUsed);
        if (ignoreUsed == TriState.UNSET) {
            this.ignoreUsed = Action.ignoreUsedDefault;
            return;
        }
        setIgnoreUsed(ignoreUsed.toBoolean());
    }
}