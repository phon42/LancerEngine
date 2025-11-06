package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;

public class ActivationType {
    /**
     * The value of this activation type (i.e. "free").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase. Cannot be null.
     * Anything that is not one of the standard values is automatically counted as a free action.
     * 
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
    private String value;

    public ActivationType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
    }
    public ActivationType(ActivationType activationType) {
        setValue(activationType.value);
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }
}
