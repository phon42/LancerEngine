package Packages.CoreTypes.EntityMechanics.Actions.Unverified.unverifiedActionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Actions.Unverified.UnverifiedActionBase;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Unverified.UnverifiedActivationType;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Unverified.UnverifiedFrequency;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.Frequency;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Unverified.UnverifiedSynergyLocation;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified.SynergyLocation;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson.
 */
public class UnverifiedAction extends UnverifiedActionBase
    implements UnverifiedData<UnverifiedAction, Action> {
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
    public UnverifiedAction(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger, Callable method,
        String requiredInitialConditions, UnverifiedActivationType activation,
        UnverifiedFrequency frequency,
        UnverifiedSynergyLocation[] synergyLocations,
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
    public UnverifiedAction(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger,
        UnverifiedActivationType activation, UnverifiedFrequency frequency,
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
    public UnverifiedAction(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive,
        UnverifiedActivationType activation,
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
    public UnverifiedAction(
        // ActionBase properties
        String name, String detailedDescription, String trigger,
        UnverifiedActivationType activation, UnverifiedFrequency frequency,
        // Required property
        String id
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, trigger, null,
            null, activation, frequency,
            null, id, TriState.UNSET,
            UnverifiedAction.heatCostDefault, null, null);
    }
    /**
     * A constructor using only the required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public UnverifiedAction(
        // ActionBase properties
        String name, String detailedDescription,
        UnverifiedActivationType activation,
        // Required property
        String id
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, null, null,
            null, activation, null,
            null, id, TriState.UNSET,
            UnverifiedAction.heatCostDefault, null, null);
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
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Semi-required properties
    private void setIgnoreUsed(boolean ignoreUsed) {
        this.ignoreUsed = ignoreUsed;
        setFrequency(calculateFrequency());
    }
    protected void setHeatCost(int heatCost) {
        if (heatCost < 0) {
            this.heatCost = UnverifiedAction.heatCostDefault;
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

    @Override
    public Class<UnverifiedAction> getUnverifiedType() {
        return UnverifiedAction.class;
    }
    @Override
    public Class<Action> getVerifiedType() {
        return Action.class;
    }
    @Override
    public Action verify() {
        ActivationType activation = this.activation.verify();
        Frequency frequency = null;
        SynergyLocation[] synergyLocations = null;

        if (this.frequency != null) {
            frequency = this.frequency.verify();
        }
        if (this.synergyLocations != null) {
            synergyLocations =
                new SynergyLocation[this.synergyLocations.length];
            for (int i = 0; i < this.synergyLocations.length; i++) {
                synergyLocations[i] = this.synergyLocations[i].verify();
            }
        }

        return new Action(this.name, this.name, TriState.toTriState(this.pilot),
            TriState.toTriState(this.mech), this.confirm,
            TriState.toTriState(this.hideActive), this.type, this.method,
            this.name, activation, frequency, synergyLocations, this.id,
            TriState.toTriState(this.ignoreUsed), this.heatCost, this.terse,
            this.log);
    }
    protected UnverifiedFrequency calculateFrequency() {
        if (this.ignoreUsed) {
            return new UnverifiedFrequency("Unlimited");
        } else {
            return new UnverifiedFrequency("1/round");
        }
    }
    protected void setIgnoreUsed(TriState ignoreUsed) {
        HelperMethods.checkObject("ignoreUsed", ignoreUsed);
        if (ignoreUsed == TriState.UNSET) {
            this.ignoreUsed = UnverifiedAction.ignoreUsedDefault;
            return;
        }
        setIgnoreUsed(ignoreUsed.toBoolean());
    }
}
