package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class HTMLString implements Comparable<HTMLString>, CharSequence {
    private String rawValue;

    public HTMLString() {
        setRawValue("");
    }
    public HTMLString(String input) {
        HelperMethods.checkObject("input", input);
    }
    public HTMLString(StringBuffer buffer) {
        this(new String(buffer));
    }
    public HTMLString(StringBuilder builder) {
        this(new String(builder));
    }
    public HTMLString(HTMLString htmlString) {
        HelperMethods.checkObject("htmlString", htmlString);
        setRawValue(htmlString.rawValue);
    }

    public String getRawValue() {
        return rawValue;
    }
    protected void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
    }

    @Override
    public String toString() {
        return getRawValue();
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
}
