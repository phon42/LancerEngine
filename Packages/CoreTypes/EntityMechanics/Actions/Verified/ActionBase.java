package Packages.CoreTypes.EntityMechanics.Actions.Verified;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBaseBase;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.frequency.FrequencyType;
import Packages.CoreTypes.TriState;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson
 *     and https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents a single verified action of any type. Contains information about
 *     the action's name, activation speed, and a detailed description of what
 *     it does, among other properties.
 * 
 * Requires an action name, activation speed, and a detailed description to be
 *     instantiated.
 * 
 * Used in Action and IActionData.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class ActionBase extends ActionBaseBase {
    // Required property
    /**
     * The activation type of this action (i.e. an ActivationType representing a
     *     free action).
     * Can be any ActivationType. Cannot be null.
     */
    protected ActivationType activation;

    // Conditionally required property
    /**
     * The frequency with which this action can be used, if there is one (i.e. a
     *     Frequency that represents "1/round").
     * Required when this.activation is a reaction.
     * When required:
     *     Can be any Frequency. Cannot be null.
     * When not required:
     *     Default value: 1/round if this.ignoreUsed is false and unlimited if
     *         it's true.
     *     Must be a Frequency that is either 1/round or unlimited. Cannot be
     *         null.
     * Can be any Frequency with the restrictions described above. Cannot be
     *     null.
     */
    protected Frequency frequency;

    // Optional property
    /**
     * An array of locations that this action is connected to (i.e. a
     *     SynergyLocation[] containing a SynergyLocation representing
     *     "overcharge").
     * Can be any SynergyLocation[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    private SynergyLocation[] synergyLocations;

    protected ActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger, Callable method,
        String requiredInitialConditions,
        // Required property
        ActivationType activation,
        // Conditionally required property
        Frequency frequency,
        // Optional property
        SynergyLocation[] synergyLocations
    ) {
        super(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, method, requiredInitialConditions);
        // Required property
        setActivation(activation);
        // Conditionally required property
        setFrequency(frequency);
        // Optional property
        setSynergyLocations(synergyLocations);
        // Helper property
        setType(calculateType());
    }
    protected ActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger,
        // Required property
        ActivationType activation,
        // Conditionally required property
        Frequency frequency
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, null, null, activation,
            frequency, null);
    }
    protected ActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive,
        // Required property
        ActivationType activation
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            null, null, null,
            activation, null, null);
    }
    protected ActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription, String trigger,
        // Required property
        ActivationType activation,
        // Conditionally required property
        Frequency frequency
    ) {
        this(name, detailedDescription, null, null, null,
            null, trigger, null,
            null, activation, frequency,
            null);
    }
    protected ActionBase(
        // ActionBaseBase properties
        String name, String detailedDescription,
        // Required property
        ActivationType activation
    ) {
        this(name, detailedDescription, null, null, null,
            null, null, null,
            null, activation, null,
            null);
    }

    // Required property
    public ActivationType getActivation() {
        return activation;
    }
    // Conditionally required property
    public Frequency getFrequency() {
        return frequency;
    }
    // Optional property
    public SynergyLocation[] getSynergyLocations() {
        if (synergyLocations == null) {
            return synergyLocations;
        }

        return HelperMethods.copyOf(synergyLocations);
    }
    // Required property
    protected void setActivation(ActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        this.activation = activation;
    }
    // Conditionally required property
    protected void setFrequency(Frequency frequency) {
        FrequencyType type;

        if (frequency == null) {
            try {
                type = Database.getFrequencyType("X/round");
                frequency = new Frequency(type, 1);
            } catch (NoSuchElementException exception) {}
        }
        HelperMethods.checkObject("frequency", frequency);
        this.frequency = frequency;
    }
    // Optional property
    private void setSynergyLocations(SynergyLocation[] synergyLocations) {
        HelperMethods.checkObjectArrayAlt("synergyLocations",
            synergyLocations);
        if (synergyLocations != null) {
            synergyLocations = HelperMethods.copyOf(synergyLocations);
        }
        this.synergyLocations = synergyLocations;
    }

    @Override
    protected void verifyProperties() {
        boolean isValidFreq1;
        boolean isValidFreq2;

        if (this.activation.getType().equals("reaction")) {
            // do nothing about this.frequency, because this.frequency cannot be
            //     null.
            if (this.trigger == null) {
                throw new IllegalStateException("this.trigger cannot be null"
                    + " when this.activation is a reaction");
            }
            // do nothing further about this.trigger because it cannot be ""
        } else {
            isValidFreq1 =
                this.frequency.getType().getValue().equals("X/round");
            isValidFreq2 = this.frequency.getType().getValue()
                .equals("Unlimited");
            if (! (isValidFreq1 || isValidFreq2)) {
                throw new IllegalStateException("this.frequency.type.value"
                    + " must be either \"X/round\" or \"Unlimited\" when"
                    + " this.activation.type is \"reaction\"");
            }
            if (this.trigger != null) {
                throw new IllegalStateException("this.trigger must be null"
                    + " when this.activation is not a reaction");
            }
        }
    }
    /**
     * Calculates the correct value for the helper property ActionBase.type.
     * @return a String containing the correct value.
     */
    @Override
    protected String calculateType() {
        String activationType;
        String result;

        activationType = this.activation.getType();
        if (this.name.equals("Move")
            || this.name.equals("Boost")) {
            result = "move";
        } else {
            if (ActionBaseBase.typeMap.containsKey(activationType)) {
                result = ActionBaseBase.typeMap.get(activationType);
            } else {
                throw new IllegalStateException("this.activation.type is: \""
                    + activationType + "\" which could not be found within"
                    + " ActionBaseBase.typeMap");
            }
        }

        return result;
    }
}
