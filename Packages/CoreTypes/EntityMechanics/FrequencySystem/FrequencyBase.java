package Packages.CoreTypes.EntityMechanics.FrequencySystem;

/**
 * Represents an unverified activation type. Contains information about that
 *     activation type's value.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in Frequency and UnverifiedFrequency.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class FrequencyBase {
    // Conditionally required property
    /**
     * This frequency's value, if it has one (i.e. 1, representing the "1" in
     *     "1/round").
     * Required when this.type.acceptsValue is true.
     * When required:
     *     Must be a minimum of 1.
     * When not required:
     *     Must be -1.
     * Must be a minimum of -1. Cannot be 0.
     */
    protected int value;

    protected FrequencyBase() {}

    // Conditionally required property
    public int getValue() {
        return value;
    }
    // Conditionally required property
    protected void setValue(int value) {
        if (value < -1) {
            throw new IllegalArgumentException("value: " + value + " is < -1");
        }
        if (value == 0) {
            throw new IllegalArgumentException("value is 0");
        }
    }
}
