package Packages.CoreTypes.VueHTMLString.vueHTMLString.vueReplaceableType;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString.vueHTMLString.VueReplaceableType;

/**
 * See https://vuejs.org/guide/essentials/template-syntax#text-interpolation.
 */
public class VueMustacheTag extends VueReplaceableType {
    // Required properties
    /**
     * Can be any String. Cannot be null.
     */
    private String propertyName;
    /**
     * The number of curly braces on either side of the value.
     * Must be a minimum of 1.
     */
    private int numBraces;
    /**
     * Can be any String. Cannot be null.
     * Is set to null on construction.
     */
    private String value;

    public VueMustacheTag(String propertyName, int numBraces) {
        setPropertyName(propertyName);
        setNumBraces(numBraces);
        this.value = null;
    }
    public VueMustacheTag(VueMustacheTag vueMustacheTag) {
        setPropertyName(vueMustacheTag.propertyName);
        setNumBraces(vueMustacheTag.numBraces);
        if (vueMustacheTag.value == null) {
            this.value = null;
        } else {
            setValue(vueMustacheTag.value);
        }
    }

    // Required properties
    public String getPropertyName() {
        return propertyName;
    }
    public int getNumBraces() {
        return numBraces;
    }
    public String getValue() {
        return value;
    }
    // Required properties
    private void setPropertyName(String propertyName) {
        HelperMethods.checkObject("propertyName", propertyName);
        this.propertyName = propertyName;
    }
    private void setNumBraces(int numBraces) {
        if (numBraces < 1) {
            throw new IllegalArgumentException("numBraces: " + numBraces + " is"
                + " < 1");
        }
        this.numBraces = numBraces;
    }
    private void setValue(String value) {
        HelperMethods.checkObject("value", value);
        this.value = value;
    }

    @Override
    public VueMustacheTag replace(String replacement) {
        VueMustacheTag copy = new VueMustacheTag(this);

        HelperMethods.checkObject("replacement", replacement);
        copy.setValue(replacement);

        return copy;
    }
}
