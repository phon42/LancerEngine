package Packages.CoreTypes.EntityMechanics.Actions.Unverified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBaseBase;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Unverified.UnverifiedActivationType;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Unverified.UnverifiedFrequency;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Unverified.UnverifiedSynergyLocation;
import Packages.CoreTypes.TriState;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson
 *     and https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents a single unverified action of any type. Contains information about
 *     the action's name, activation speed, and a detailed description of what
 *     it does, among other properties.
 * 
 * Requires an action name, activation speed, and a detailed description to be
 *     instantiated.
 * 
 * Used in UnverifiedAction and UnverifiedIActionData.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class UnverifiedActionBase extends ActionBaseBase {
    // Required property
    /**
     * The activation type of this action (i.e. an UnverifiedActivationType
     *     representing a free action).
     * Can be any UnverifiedActivationType. Cannot be null.
     */
    protected UnverifiedActivationType activation;

    // Optional properties
    /**
     * The frequency with which this action can be used, if there is one (i.e.
     *     an UnverifiedFrequency that represents "1/round").
     * Can be any UnverifiedFrequency. Cannot be null.
     */
    protected UnverifiedFrequency frequency;
    /**
     * An array of locations that this action is connected to (i.e. an
     *     UnverifiedSynergyLocation[] containing an UnverifiedSynergyLocation
     *     representing "overcharge").
     * Can be any UnverifiedSynergyLocation[] that is not of length 0 and does
     *     not contain null elements. Can be null.
     */
    protected UnverifiedSynergyLocation[] synergyLocations;

    /**
     * A constructor using all possible properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:                 PROVIDED
     */
    protected UnverifiedActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger, Callable method,
        String requiredInitialConditions,
        // Required property
        UnverifiedActivationType activation,
        // Optional properties
        UnverifiedFrequency frequency,
        UnverifiedSynergyLocation[] synergyLocations
    ) {
        super(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, method, requiredInitialConditions);
        // Required property
        setActivation(activation);
        // Optional properties
        setFrequency(frequency);
        setSynergyLocations(synergyLocations);
        // Helper property
        setType(calculateType());
    }
    /**
     * A constructor using all possible properties except the optional
     *     properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected UnverifiedActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger,
        // Required property
        UnverifiedActivationType activation,
        // Optional property
        UnverifiedFrequency frequency
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, null, null, activation,
            frequency, null);
    }
    /**
     * A constructor using all possible properties except the optional and
     *     conditionally required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected UnverifiedActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive,
        // Required property
        UnverifiedActivationType activation
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            null, null, null,
            activation, null, null);
    }
    /**
     * A constructor using all possible properties except the optional and semi-
     *     required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected UnverifiedActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, String trigger,
        // Required property
        UnverifiedActivationType activation,
        // Optional property
        UnverifiedFrequency frequency
    ) {
        this(name, detailedDescription, null, null, null,
            null, trigger, null,
            null, activation, frequency,
            null);
    }
    /**
     * A constructor using only the required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected UnverifiedActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription,
        // Required property
        UnverifiedActivationType activation
    ) {
        this(name, detailedDescription, null, null, null,
            null, null, null,
            null, activation, null,
            null);
    }

    // Required property
    public UnverifiedActivationType getActivation() {
        return activation;
    }
    // Optional properties
    public UnverifiedFrequency getFrequency() {
        return frequency;
    }
    public UnverifiedSynergyLocation[] getSynergyLocations() {
        if (synergyLocations == null) {
            return synergyLocations;
        }

        return HelperMethods.copyOf(synergyLocations);
    }
    // Required property
    protected void setActivation(UnverifiedActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        this.activation = activation;
    }
    // Optional property
    protected void setFrequency(UnverifiedFrequency frequency) {
        HelperMethods.checkObject("frequency", frequency);
        this.frequency = frequency;
    }
    protected void setSynergyLocations(
        UnverifiedSynergyLocation[] synergyLocations) {
        HelperMethods.checkObjectArrayAlt("synergyLocations",
            synergyLocations);
        if (synergyLocations != null) {
            synergyLocations = HelperMethods.copyOf(synergyLocations);
        }
        this.synergyLocations = synergyLocations;
    }

    /**
     * Calculates the correct value for the helper property ActionBaseBase.type.
     * @return a String containing the correct value.
     */
    @Override
    protected String calculateType() {
        return "free";
    }
}
