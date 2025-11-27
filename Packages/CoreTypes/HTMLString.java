package Packages.CoreTypes;

import java.util.ArrayList;
import MainBranch.HelperMethods;
import Packages.CoreTypes.htmlString.HTMLTag;

public class HTMLString implements Comparable<HTMLString>, CharSequence {
    /**
     * The raw value of the content stored in this HTMLString.
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String rawValue;
    /**
     * TODO: add documentation
     */
    private Object[] dataSequence;

    public HTMLString() {
        setRawValue("");
    }
    public HTMLString(String input) {
        setRawValue(input);
    }
    private HTMLString(HTMLString htmlString) {
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
    public Object[] getDataSequence() {
        return copyDataSequence();
    }
    private void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
        setDataSequence(calculateDataSequence());
        verify();
    }
    private void setDataSequence(Object[] dataSequence) {
        HelperMethods.checkObjectArray("dataSequence",
            dataSequence);
        // removed copying of dataSequence because this method is only ever
        //     called within this class
        this.dataSequence = dataSequence;
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
    private Object[] calculateDataSequence() {
        ArrayList<Object> sequence;
        String rawValue;
        String workingString;
        boolean isClosingTag = false;
        HTMLTag workingTag;
        Object[] result;

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
        sequence.add("");
        // At this point, rawValue can be any amount of "   abc   ..." possibly
        //     including a tag or not
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

        return result;
    }
    private void addElement(ArrayList<Object> arrayList, Object newElement) {
        Object lastElement;
        boolean lastElementType = false;
        boolean newElementType = false;
        int lastElementIndex;
        String modifiedString;

        HelperMethods.checkObject("arrayList", arrayList);
        HelperMethods.checkObject("newElement", newElement);
        lastElement = arrayList.get(arrayList.size() - 1);
        HelperMethods.checkObject("lastElement", lastElement);
        lastElementType = dataType(lastElement);
        newElementType = dataType(newElement);
        lastElementIndex = arrayList.size() - 1;
        if (! (lastElementType || newElementType)) {
            modifiedString = (String) lastElement;
            modifiedString += (String) newElement;
            arrayList.set(lastElementIndex, modifiedString);
        } else {
            arrayList.add(newElement);
        }
    }
    private static boolean dataType(Object dataElement) {
        HelperMethods.checkObject("dataElement", dataElement);
        if (dataElement instanceof String) {
            return false;
        }
        if (dataElement instanceof HTMLTag) {
            return true;
        }
        throw new IllegalStateException("dataElement is of a type that is"
            + " neither String nor HTMLTag");
    }
    private Object[] copyDataSequence() {
        Object[] copy;
        boolean type;

        copy = new Object[this.dataSequence.length];
        for (int i = 0; i < this.dataSequence.length; i++) {
            type = dataType(this.dataSequence[i]);
            if (! type) {
                // this.dataSequence[i] is a String
                copy[i] = this.dataSequence[i];
            } else {
                // this.dataSequence[i] is an HTMLTag
                copy[i] = new HTMLTag((HTMLTag) this.dataSequence[i]);
            }
        }

        return copy;
    }
    private void verify() throws IllegalArgumentException {
        // verify that all opening HTMLTags in this object have one and only one
        //     corresponding closing HTMLTag and vice versa 
        boolean containsTag = false;
        int numTags = 0;
        HTMLTag[] tags;

        for (int i = 0; i < this.dataSequence.length; i++) {
            if (dataType(this.dataSequence[i])) {
                containsTag = true;
                numTags++;
            }
        }
        if (! containsTag) {
            return;
        }
        tags = new HTMLTag[numTags];
        numTags = 0;
        for (int i = 0; i < this.dataSequence.length; i++) {
            if (! dataType(this.dataSequence[i])) {
                continue;
            }
            tags[numTags] = (HTMLTag) this.dataSequence[i];
            numTags++;
        }
        HTMLTag.checkTagSequence(tags);
    }
    public HTMLString replaceElements(Object... args) {
        return replaceElements(this, args);
    }
    public static HTMLString replaceElements(HTMLString htmlString,
        Object... args) {
        HTMLString newHTMLString;
        Object[] newDataSequence;
        String newRawValue = "";

        HelperMethods.checkObject("htmlString", htmlString);
        HelperMethods.checkObject("args", args);
        newHTMLString = new HTMLString(htmlString);
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                continue;
            }
            if (dataType(args[i]) != dataType(args[i])) {
                throw new IllegalArgumentException("Object at index: " + i
                    + " is of a type that is different from the type of the"
                    + " element of this HTMLString in that position");
            }
        }
        newDataSequence = newHTMLString.copyDataSequence();
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                continue;
            }
            newDataSequence[i] = args[i];
            newRawValue += newDataSequence[i].toString();
        }
        newHTMLString.setRawValue(newRawValue);

        return newHTMLString;
    }
}
