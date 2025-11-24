package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import MainBranch.HelperMethods;

public class Duration {
    /**
     * The value of this Duration (i.e. "1 round").
     * Must be a valid String as defined by Duration.allowedValues. Cannot be
     *     null.
     * Case-insensitive and stored in lowercase.
     */
    protected String value;
    /**
     * Contains an array of possible values for Duration.value.
     * - "source" - The life time of the source.
     * Case-insensitive and stored in lowercase.
     */
    protected static final String[] allowedValues = new String[] {"1 round",
        "1 turn", "permanent", "until removed", "source"};

    public Duration(String duration) {
        setValue(duration);
    }

    public String getValue() {
        return value;
    }
    /**
     * Sets this.value to the provided value.
     * @param value a String which cannot be null and cannot be an invalid
     *     value, as defined by Duration.allowedValues.
     * @throws IllegalArgumentException if value an invalid value as defined by
     *     Duration.allowedValues.
     */
    protected void setValue(String value) {
        HelperMethods.checkString("New value", value);
        value = value.toLowerCase();
        for (String allowedValue : Duration.allowedValues) {
            if (value.equals(allowedValue)) {
                this.value = value;
                return;
            }
        }
        throw new IllegalArgumentException("New value is an invalid value: \""
            + value + "\"");
    }
}
