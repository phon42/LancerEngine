package Packages.CoreTypes.EntityMechanics.frequency;

import MainBranch.HelperMethods;

public class FrequencyType {
    // Required properties
    /**
     * Contains this frequency type's value (i.e. a VueHTMLString representing
     *     "X/round").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String value;

    // Helper properties
    /**
     * The root of this frequency type's value (i.e. "/round" if the original
     *     was "X/round").
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String root;
    /**
     * Whether this frequency type accepts a value (i.e. "Unlimited" wouldn't
     *     but "X/round" would).
     */
    private boolean acceptsValue;

    public FrequencyType(String value) {
        HelperMethods.verifyConstructor();
        setValue(value);
        processString(value);
    }

    // Required properties
    public String getValue() {
        return value;
    }
    // Helper properties
    public String getRoot() {
        return root;
    }
    public boolean acceptsValue() {
        return acceptsValue;
    }
    // Required properties
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
    // Helper properties
    private void setRoot(String root) {
        HelperMethods.checkObject("root", root);
        this.root = root;
    }
    private void setAcceptsValue(boolean acceptsValue) {
        this.acceptsValue = acceptsValue;
    }

    private void processString(String input) {
        String value;
        int index;

        index = input.indexOf("X/");
        if (index != -1) {
            value = input.substring(0, index)
                + input.substring(index + 1);
            setRoot(value);
            setAcceptsValue(true);
        } else {
            setRoot(input);
            setAcceptsValue(false);
        }
    }
}
