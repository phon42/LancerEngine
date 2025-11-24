package Packages.CoreTypes;

import java.util.ArrayList;
import MainBranch.HelperMethods;
import Packages.CoreTypes.htmlString.HTMLTag;

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
    /**
     * TODO: add documentation
     */
    private int[] typeSequence;

    public VueHTMLString() {
        setRawValue("");
    }
    public VueHTMLString(String input) {
        setRawValue(input);
    }
    private VueHTMLString(VueHTMLString vueHTMLString) {
        HelperMethods.checkObject("vueHTMLString", vueHTMLString);
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
    // getTypeSequence purposefully removed
    private void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
        calculateDataSequence();
    }
    private void setDataSequence(Object[] dataSequence) {
        HelperMethods.checkObjectArray("dataSequence",
            dataSequence);
        // removed copying of dataSequence because this method is only ever
        //     called within this class
        this.dataSequence = dataSequence;
    }
    private void setTypeSequence(int[] typeSequence) {
        HelperMethods.checkObject("typeSequence", typeSequence);
        // removed copying of typeSequence because this method is only ever
        //     called within this class
        this.typeSequence = typeSequence;
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
    // TODO: remove?
    public boolean rawEquals(Object object) {
        return super.equals(object);
    }
    // TODO: remove?
    public boolean rawEquals(VueHTMLString vueHTMLString) {
        return super.equals(vueHTMLString);
    }
    public CharSequence subSequence(int beginIndex, int endIndex) {
        return getRawValue().subSequence(beginIndex, endIndex);
    }
    public int compareTo(VueHTMLString anotherVueHTMLString) {
        HelperMethods.checkObject("anotherVueHTMLString",
            anotherVueHTMLString);

        return getRawValue().compareTo(anotherVueHTMLString.getRawValue());
    }
    public int length() {
        return getRawValue().length();
    }
    public char charAt(int index) {
        return getRawValue().charAt(index);
    }
    public VueHTMLString[] split(String regex) {
        String[] stringArray;
        VueHTMLString[] result;

        stringArray = getRawValue().split(regex);
        result = new VueHTMLString[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            result[i] = new VueHTMLString(stringArray[i]);
        }

        return result;
    }
    private void calculateDataSequence() {
        // TODO: fill out
        ArrayList<Object> sequence;
        String rawValue;
        String workingString;
        boolean isClosingTag = false;
        HTMLTag workingTag;
        Object[] result;
        int[] types;

        sequence = new ArrayList<>();
        // We will represent the possible value of rawValue through the method
        //     as follows:
        // "   " will represent some amount of whitespace of any kind, including
        //     ""
        // "abc" will represent some amount of non-whitespace characters that
        //     are not '<', '/', or '>'
        // "<" will represent itself
        // "/" will represent itself
        // ">" will represent itself
        // "..." will represent some amount of content that could contain any of
        //     the above
        rawValue = this.rawValue;
        // At this point, rawValue can be any amount of "   abc   ..." possibly
        //     including a tag or not
        sequence.add("");
        while (! rawValue.isEmpty()) {
            if (rawValue.indexOf("<") == -1) {
                // rawValue is "   abc   " (does not contain a valid tag but
                //     might contain '/' or '>')
                addElement(sequence, rawValue);
                rawValue = "";
            } else if (rawValue.indexOf("<") > 0) {
                // rawValue is "   abc   <..."
                // remove "   abc   " off the front, leaving "<..."
                // now we can add "   abc   " exactly as in the above case
                workingString = rawValue.substring(0,
                    rawValue.indexOf("<"));
                rawValue = rawValue.substring(rawValue.indexOf("<"));
                addElement(sequence, workingString);
            } else {
                // rawValue.indexOf("<") == 0
                // rawValue is "<..."
                if (rawValue.indexOf(">") == -1) {
                    // rawValue does not contain a valid tag but does contain
                    //     '<' and possibly '/'
                    // it's essentially the same as "   abc   " because there's
                    //     no valid tag here
                    // add rawValue as in the rawValue = "   abc   " case
                    addElement(sequence, rawValue);
                    rawValue = "";
                } else {
                    // rawValue is "<   abc   >..."
                    // we clip the "<   abc   >" off of rawValue and give that
                    //     to workingString
                    workingString = rawValue.substring(0,
                        rawValue.indexOf(">") + 1);
                    rawValue = rawValue.substring(rawValue.indexOf(">")
                        + 1);
                    // rawValue is now "..."
                    // workingString is now "<   abc   >"
                    // therefore workingString should in theory contain the raw
                    //     material required for an HTMLTag
                    workingString = workingString.substring(1,
                        workingString.length() - 1);
                    // workingString is now "   abc   "
                    workingString = workingString.stripLeading();
                    // workingString is now "abc   "
                    if (workingString.indexOf("/") == 0) {
                        // workingString is "/   abc   "
                        workingString = workingString.substring(1);
                        // workingString is "   abc   "
                        if (workingString.indexOf("/") != -1
                            || workingString.indexOf("<") != -1) {
                            // workingString was originally of the form
                            //     "<//>" or "</<>" which are both invalid
                            // TODO: add exception message
                            throw new IllegalStateException();
                        } else {
                            // workingString currently contains only "   " and
                            //     "abc"
                            // in other words, the original was of the form
                            //     "</   abc   >"
                            // which is valid
                        }
                        isClosingTag = true;
                    } else if (workingString.indexOf("/") > 0) {
                        // workingString is "abc/   abc   " which is invalid
                        // TODO: add exception message
                        throw new IllegalStateException();
                    } else {
                        // workingString.indexOf("/") == -1
                        // workingString contains only "   " and "abc"
                        // the original was of the form "<   abc   >" which is
                        //     valid
                        // it's not a closing tag but isClosingTag has a default
                        //     value of false so nothing needs to be done
                    }
                    workingTag = new HTMLTag(workingString, isClosingTag);
                    addElement(sequence, workingTag);
                }
            }
        }
        result = new Object[sequence.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = sequence.get(i);
        }
        types = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            types[i] = dataType(result[i]);
        }
        setDataSequence(result);
        setTypeSequence(types);
    }
    private void addElement(ArrayList<Object> arrayList, Object newElement) {
        Object lastElement;
        int lastElementType;
        int newElementType;
        int lastElementIndex;
        String modifiedString;

        HelperMethods.checkObject("arrayList", arrayList);
        HelperMethods.checkObject("newElement", newElement);
        lastElement = arrayList.get(arrayList.size() - 1);
        HelperMethods.checkObject("lastElement", lastElement);
        lastElementType = dataType(lastElement);
        newElementType = dataType(newElement);
        lastElementIndex = arrayList.size() - 1;
        if (lastElementType != newElementType) {
            arrayList.add(newElement);
        } else if (lastElementType == 0) {
            modifiedString = (String) lastElement;
            modifiedString += (String) newElement;
            arrayList.set(lastElementIndex, modifiedString);
        } // TODO: fill out
    }
    private static int dataType(Object dataElement) {
        if (dataElement instanceof String) {
            return 0;
        }
        // TODO: fill out
        throw new IllegalStateException();
    }
    private Object[] copyDataSequence(Object[] original) {
        // TODO: fill out
        Object[] copy;

        HelperMethods.checkObjectArray("original", original);
        copy = new Object[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] instanceof String) {
                copy[i] = original[i];
            } else {
                // original is an HTMLTag
                copy[i] = new HTMLTag((HTMLTag) original[i]);
            }
        }

        return copy;
    }
    public static VueHTMLString format(VueHTMLString vueHTMLString,
        Object... args) {
        HelperMethods.checkObject("vueHTMLString", vueHTMLString);

        return vueHTMLString.format(args);
    }
    public VueHTMLString format(Object... args) {
        // TODO: fill out
        VueHTMLString newVueHTMLString;

        newVueHTMLString = new VueHTMLString(this);

        return newVueHTMLString;
    }
}
