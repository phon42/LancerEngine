package Packages.CoreTypes;

import MainBranch.HelperMethods;

/**
 * See https://vuejs.org/guide/essentials/template-syntax.
 */
public class VueHTMLString implements Comparable<VueHTMLString>, CharSequence {
    /**
     * TODO: add documentation
     */
    private String rawValue;
    /**
     * TODO: add documentation
     */
    private Object[] dataSequence;

    public VueHTMLString() {
        setRawValue("");
    }
    public VueHTMLString(String input) {
        setRawValue(input);
    }
    public VueHTMLString(VueHTMLString vueHTMLString) {
        setRawValue(vueHTMLString.rawValue);
    }
    public VueHTMLString(StringBuffer buffer) {
        this(new String(buffer));
    }
    public VueHTMLString(StringBuilder builder) {
        this(new String(builder));
    }

    public String getRawValue() {
        return rawValue;
    }
    public Object[] getDataSequence() {
        return copyDataSequence(dataSequence);
    }
    private void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
        setDataSequence(calculateDataSequence());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (! (object instanceof VueHTMLString)) {
            return false;
        }

        return equals((VueHTMLString) object);
    }
    public boolean equals(VueHTMLString vueHTMLString) {
        if (vueHTMLString == null) {
            return false;
        }

        return this.getRawValue().equals(vueHTMLString.getRawValue());
    }
    public HTMLString[] split(String regex) {
        String[] stringArray;
        HTMLString[] result;

        stringArray = getRawValue().split(regex);
        result = new HTMLString[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            result[i] = new VueHTMLString(stringArray[i]);
        }

        return result;
    }
    private Object[] calculateDataSequence() {
        // TODO: fill out
        return new Object[0];
    }
    private Object[] copyDataSequence(Object[] dataSequence) {
        // TODO: fill out
        return dataSequence;
    }
    public static VueHTMLString format(VueHTMLString vueHTMLString,
        Object... args) {
        HelperMethods.checkObject("vueHTMLString", vueHTMLString);

        return vueHTMLString.format(args);
    }
    public VueHTMLString format(Object... args) {
        // TODO: fill out
        return null;
    }
}
