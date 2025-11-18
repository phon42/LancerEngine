package Packages.CoreTypes.EntityMechanics;

import MainBranch.Database;
import MainBranch.HelperMethods;

/**
 * Represents an activation type. Contains information about that activation
 *     type's type and a helper property related to it.
 * 
 * Requires an activation type type to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class ActivationType {
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
    private String value;

    // Helper property
    /**
     * Exactly the same as this.value most of the time; is "free" when
     *     this.value is an unsorted action.
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String type;

    public ActivationType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
        calculateType();
    }
    public ActivationType(String value, String type) {
        HelperMethods.verifyConstructor();
        setValue(value);
        setType(type);
    }

    public String getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        this.type = type;
    }

    private void calculateType() {
        boolean isNull = false;
        ActivationType activationType;
        int index = 0;
        boolean hasBeenFound = false;

        while (! isNull) {
            // this is essentially a for loop but we don't know the length of
            //     Database.activationTypes
            isNull = false;
            try {
                activationType = Database.getActivationTypeByIndex(index);
                // if it didn't throw an exception (the index is valid):
                if (this.value.equals(activationType.getValue())) {
                    setType(activationType.getType());
                    hasBeenFound = true;
                    break;
                }
            } catch (IllegalArgumentException exception) {
                // needed so we can exit the loop once we reach the end of
                //     Database.activationTypes
                isNull = true;
            }
            index++;
        }
        if (! hasBeenFound) {
            setType("free");
        }
    }
}
