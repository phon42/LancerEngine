package Packages.CoreTypes;

import java.util.ArrayList;
import MainBranch.HelperMethods;
import Packages.CoreTypes.htmlString.HTMLTag;

public class HTMLString implements Comparable<HTMLString>, CharSequence {
    /**
     * TODO: add documentation
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
    public HTMLString(HTMLString htmlString) {
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
        return copyDataSequence(dataSequence);
    }
    private void setRawValue(String rawValue) {
        HelperMethods.checkObject("rawValue", rawValue);
        this.rawValue = rawValue;
        setDataSequence(calculateDataSequence());
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
    // TODO: remove?
    public boolean rawEquals(Object object) {
        return super.equals(object);
    }
    // TODO: remove?
    public boolean rawEquals(HTMLString htmlString) {
        return super.equals(htmlString);
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

        return result;
    }
    private void addElement(ArrayList<Object> arrayList, Object newElement) {
        Object lastElement;
        boolean isLastElementString = false;
        boolean isNewElementString = false;
        int lastElementIndex;
        String modifiedString;

        HelperMethods.checkObject("arrayList", arrayList);
        HelperMethods.checkObject("newElement", newElement);
        lastElement = arrayList.get(arrayList.size() - 1);
        HelperMethods.checkObject("lastElement", lastElement);
        if (lastElement instanceof String) {
            isLastElementString = true;
        }
        if (newElement instanceof String) {
            isNewElementString = true;
        }
        lastElementIndex = arrayList.size() - 1;
        if (isLastElementString && isNewElementString) {
            modifiedString = (String) lastElement;
            modifiedString += (String) newElement;
            arrayList.set(lastElementIndex, modifiedString);
        } else {
            arrayList.add(newElement);
        }
    }
    private Object[] copyDataSequence(Object[] original) {
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
}
