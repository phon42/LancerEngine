package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class VueHTMLString extends HTMLString {
    public VueHTMLString(String input) {
        super();
        // TODO: fill out
    }
    public VueHTMLString(VueHTMLString vueHTMLString) {
        super();
        // TODO: fill out
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
    public static VueHTMLString format(VueHTMLString vueHTMLString,
        Object... args) {
        HelperMethods.checkObject("vueHTMLString", vueHTMLString);

        return vueHTMLString.format(args);
    }
    public VueHTMLString format(Object... args) {
        // TODO: fill out
        return null;
    }
    @Override
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
}
