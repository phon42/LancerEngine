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

    public VueMustacheTag(String input) {
        processString(input);
    }
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
    public String toString() {
        final String leftBraces = "{".repeat(this.numBraces);
        final String rightBraces = "}".repeat(this.numBraces);
        final String content = value == null ? this.propertyName : this.value;

        return leftBraces + content + rightBraces;
    }
    @Override
    public VueMustacheTag replace(String replacement) {
        VueMustacheTag copy = new VueMustacheTag(this);

        HelperMethods.checkObject("replacement", replacement);
        copy.setValue(replacement);

        return copy;
    }
    private void processString(String input) {
        int numBraces;
        String content;

        HelperMethods.checkObject("input", input);
        if (input.indexOf("{") == -1) {
            throw new IllegalArgumentException("Unable to create a"
                + " VueMustacheTag from the String: \"" + input + "\"");
        }
        numBraces = checkNesting(input);
        content = checkFormatting(input);
        setNumBraces(numBraces);
        setPropertyName(content);
    }
    private int checkNesting(String input) {
        int nestingLevel = 0;
        int maxNestingLevel = 0;
        String character;

        HelperMethods.checkObject("input", input);
        if (input.equals("")) {
            return 0;
        }
        if (input.indexOf("{") == input.indexOf("}")
            && input.indexOf("{") == -1) {
            return 0;
        }
        for (int i = 0; i < input.length(); i++) {
            character = input.substring(i, i + 1);
            if (character.equals("{")) {
                nestingLevel++;
            } else if (character.equals("}")) {
                nestingLevel--;
            }
            if (nestingLevel < 0) {
                throw new IllegalArgumentException("input: \"" + input + "\""
                    + " contains a section with a negative level of nesting"
                    + " (more '}' characters than '{' characters)");
            }
            maxNestingLevel = Math.max(maxNestingLevel, nestingLevel);
        }
        if (nestingLevel > 0) {
            throw new IllegalArgumentException("input: \"" + input + "\""
                + " contains an unclosed curly bracket ('{')");
        }

        return maxNestingLevel;
    }
    private String checkFormatting(String input) {
        int mode = 0;
        String character;
        String content = "";

        HelperMethods.checkObject("input", input);
        if (input.equals("")) {
            return "";
        }
        if (input.indexOf("{") == input.indexOf("}")
            && input.indexOf("{") == -1) {
            return "";
        }
        for (int i = 0; i < input.length(); i++) {
            character = input.substring(i, i + 1);
            if (mode == 0) {
                // the opening "flat" portion (we should expect to only see
                //     non-"{"/"}" characters)
                // if we see "{", we advance to the next stage
                // if we see "}", we throw an Exception
            } else if (mode == 1) {
                // the "ascending" portion (we should expect to only see "{"s)
                // if we see anything other than "{", we advance to the next
                //     stage
                if (! character.equals("{")) {
                    mode++;
                }
            } else if (mode == 2) {
                // the "middle" portion (we should expect to only see
                //     non-"{"/"}" characters)
                // if we see "}", we advance to the next stage
                // if we see "{", we throw an Exception
                // otherwise, we save the current character to the content
                //     variable
                if (! character.equals("}")) {
                    mode++;
                }
                if (! character.equals("{")) {
                    throw new IllegalArgumentException("input: \"" + input
                        + "\" is an illegally formatted String");
                }
                content += character;
            } else if (mode == 3) {
                // the "descending" portion (we should expect to only see "}"s)
                // if we see any character that isn't "{" or "}", we advance to
                //     the next stage
                // if we see any "{"s, we throw an Exception
                if (character.equals("{")) {
                    throw new IllegalArgumentException("input: \"" + input
                        + "\" is an illegally formatted String");
                } else if (! character.equals("}")) {
                    mode++;
                }
            } else if (mode == 4) {
                // the closing "flat" portion (we should expect to only see
                //     non-"{"/"}" characters)
                // if we see either "{" or "}", we throw an Exception
                if (character.equals("{")
                    || character.equals("}")) {
                    throw new IllegalArgumentException("input: \"" + input
                        + "\" is an illegally formatted String");
                }
            }
        }

        return content;
    }
}
