package Packages.CoreTypes.EntityMechanics.ActivationTypeSystem;

import MainBranch.HelperMethods;

/**
 * Represents an activation type, either verified or unverified. Contains
 *     information about that activation type's value.
 * 
 * Requires an activation type value to be instantiated.
 * 
 * Used in ActivationType and UnverifiedActivationType.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class ActivationTypeBase {
    // Required property
    /**
     * The value of this activation type (i.e. "free").
     * =========================================================================
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
     * =========================================================================
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase. Cannot be null.
     * Anything that is not one of the standard values is automatically counted
     *     as a free action.
     */
    protected String value;

    public ActivationTypeBase(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
    }

    // Required property
    public String getValue() {
        return value;
    }
    // Required property
    protected void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }
}
