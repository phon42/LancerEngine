package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class HTMLString implements Comparable<HTMLString>, CharSequence {
    protected String rawValue;

    public HTMLString() {
        setRawValue("");
    }
    public HTMLString(String input) {
        setRawValue(input);
    }
    public HTMLString(HTMLString htmlString) {
        HelperMethods.checkObject("htmlString", htmlString);
        setRawValue(htmlString.rawValue);
    }
    public HTMLString(StringBuffer buffer) {
        this(new String(buffer));
    }
    public HTMLString(StringBuilder builder) {
        this(new String(builder));
    }

    public String getRawValue() {
        return rawValue;
    }
    protected void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
        // TODO: fill out
    }

    @Override
    public String toString() {
        return getRawValue();
    }
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (! (object instanceof HTMLString)) {
            return false;
        }

        return equals((HTMLString) object);
    }
    public boolean equals(HTMLString htmlString) {
        if (htmlString == null) {
            return false;
        }

        return this.getRawValue().equals(htmlString.getRawValue());
    }
    public boolean rawEquals(Object object) {
        return equals(object);
    }
    public boolean rawEquals(HTMLString htmlString) {
        return equals(htmlString);
    }
    public CharSequence subSequence(int beginIndex, int endIndex) {
        return getRawValue().subSequence(beginIndex, endIndex);
    }
    public int compareTo(HTMLString anotherHTMLString) {
        HelperMethods.checkObject("anotherHTMLString",
            anotherHTMLString);

        return getRawValue().compareTo(anotherHTMLString.getRawValue());
    }
    public int length() {
        return getRawValue().length();
    }
    public char charAt(int index) {
        return getRawValue().charAt(index);
    }
    public HTMLString[] split(String regex) {
        String[] stringArray;
        HTMLString[] result;

        stringArray = getRawValue().split(regex);
        result = new HTMLString[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            result[i] = new HTMLString(stringArray[i]);
        }

        return result;
    }
}
