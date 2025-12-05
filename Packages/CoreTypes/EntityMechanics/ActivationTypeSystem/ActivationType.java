package Packages.CoreTypes.EntityMechanics.ActivationTypeSystem;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;

/**
 * Represents an activation type. Contains information about that activation
 *     type's type and a helper property related to it.
 * 
 * Requires an activation type value to be instantiated.
 * 
 * Used in ActionBase, IDeployableData and its children.
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
        setType(calculateType());
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

    protected String calculateType() {
        try {
            return Database.getActivationType(this.value).getType();
        } catch (NoSuchElementException exception) {
            return "free";
        }
    }
}
