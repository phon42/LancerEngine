package Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.frequency.FrequencyType;

/**
 * Represents a verified frequency. Contains information about that frequency's
 *     type and value.
 * 
 * Requires a frequency type to be instantiated.
 * 
 * Used in ActionBase and its children, as well as BondPower.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class Frequency {
    // Required property
    /**
     * Contains this frequency's type (i.e. a FrequencyType representing
     *     "X/round").
     * Can be any FrequencyType. Cannot be null.
     */
    private FrequencyType type;

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
    private int value;

    public Frequency(String input) {
        processInput(input);
    }
    public Frequency(FrequencyType type, int value) {
        setType(type);
        setValue(value);
    }
    public Frequency(FrequencyType type) {
        setType(type);
        setValue(-1);
    }

    // Required property
    public FrequencyType getType() {
        return type;
    }
    // Conditionally required property
    public int getValue() {
        return value;
    }
    // Required property
    private void setType(FrequencyType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    // Conditionally required property
    private void setValue(int value) {
        if (value < -1) {
            throw new IllegalArgumentException("value: " + value + " is < -1");
        }
        if (value == 0) {
            throw new IllegalArgumentException("value is 0");
        }
        if ((value == -1) == this.type.acceptsValue()) {
            // value is -1 and this.type is a value for which having a value
            //     other than -1 MAKES SENSE, OR value is NOT -1 and this.type
            //     is a value for which having a value other than -1 DOES NOT
            //     MAKE SENSE
            if (value == -1) {
                // value is -1 and this.type is a value for which having a
                //     value other than -1 MAKES SENSE
                throw new IllegalStateException("value is -1 and this.type"
                    + " is: \"" + this.type + "\" which requires this.value to"
                    + " be something other than -1");
            } else {
                // value is not -1 and this.type is a value for which having a
                //     value other than -1 does not make sense
                throw new IllegalStateException("value: " + value + " is not -1"
                    + " and this.type is: \"" + this.type + "\" which requires"
                    + " this.value to be -1");
            }
        }
        this.value = value;
    }

    @Override
    public String toString() {
        if (type.acceptsValue()) {
            return value + type.getRoot();
        } else {
            return type.getValue();
        }
    }
    private void processInput(String input) {
        String type;

        HelperMethods.checkString("input", input);
        if (input.indexOf("X/") != -1) {
            // should expect a value
            type = FrequencyType.baseToRoot(input);
            setType(Database.getFrequencyType(type));
            setValue(HelperMethods.parseInt(input));
        } else {
            // no value
            setType(Database.getFrequencyType(input));
            setValue(-1);
        }
    }
}
