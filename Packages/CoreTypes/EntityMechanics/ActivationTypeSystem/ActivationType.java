package Packages.CoreTypes.EntityMechanics.ActivationTypeSystem;

import MainBranch.Database;
import MainBranch.HelperMethods;

/**
 * Represents an activation type. Contains information about that activation
 *     type's type and a helper property related to it.
 * 
 * Requires an activation type value to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class ActivationType extends ActivationTypeBase {
    // Helper property
    /**
     * Exactly the same as this.value most of the time; is "free" when
     *     this.value is an unsorted action.
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    protected String type;

    public ActivationType(String value) {
        super(value);
        calculateType();
    }
    public ActivationType(String value, String type) {
        super(value);
        setType(type);
    }

    // Helper property
    public String getType() {
        return type;
    }
    // Helper property
    protected void setType(String type) {
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
