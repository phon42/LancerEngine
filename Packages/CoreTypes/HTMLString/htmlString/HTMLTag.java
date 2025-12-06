package Packages.CoreTypes.HTMLString.htmlString;

import java.util.Stack;

import MainBranch.HelperMethods;

public class HTMLTag {
    /**
     * The tag name of this tag (i.e. "p").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     * Cannot contain any whitespace.
     */
    private String name;
    /**
     * Whether this tag is a closing tag (in other words, if it's of the form
     *     "</abc>" instead of "<abc>").
     */
    private boolean closingTag;

    public HTMLTag(String tagName, boolean isClosingTag) {
        setName(tagName);
        setClosingTag(isClosingTag);
    }
    public HTMLTag(HTMLTag htmlTag) {
        setName(htmlTag.name);
        setClosingTag(htmlTag.closingTag);
    }

    public String getName() {
        return name;
    }
    public boolean isClosingTag() {
        return closingTag;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setClosingTag(boolean closingTag) {
        this.closingTag = closingTag;
    }

    @Override
    public String toString() {
        return "<" + (closingTag ? "/" : "") + name + ">";
    }
    public HTMLTag toOpeningTag() {
        return new HTMLTag(this.name, false);
    }
    public HTMLTag toClosingTag() {
        return new HTMLTag(this.name, true);
    }
    public boolean formsPairWith(HTMLTag closingTag) {
        return formsPair(this, closingTag);
    }
    public static boolean formsPair(HTMLTag openingTag, HTMLTag closingTag) {
        HelperMethods.checkObject("openingTag", openingTag);
        HelperMethods.checkObject("closingTag", closingTag);
        if (openingTag.isClosingTag() || ! closingTag.isClosingTag()) {
            return false;
        }

        return openingTag.getName().equals(closingTag.getName());
    }
    public static void checkTagSequence(HTMLTag[] sequence)
        throws IllegalArgumentException {
        Stack<HTMLTag> openingTags;
        HTMLTag tag;
        HTMLTag mostRecentOpener;

        HelperMethods.checkObjectArray("sequence", sequence);
        if (sequence.length == 0) {
            return;
        }
        if (sequence.length % 2 == 1) {
            throw new IllegalArgumentException("Tag sequence's length is odd"
                + " and therefore contains an unmatched HTMLTag");
        }
        // from this point onwards, sequence's length is always even
        openingTags = new Stack<>();
        for (int i = 0; i < sequence.length; i++) {
            tag = sequence[i];
            if (tag.isClosingTag()) {
                if (openingTags.isEmpty()) {
                    throw new IllegalArgumentException("Tag sequence element "
                        + i + ": \"" + tag + "\" is a closing tag without a"
                        + " corresponding opening tag");
                }
                mostRecentOpener = openingTags.peek();
                if (mostRecentOpener.formsPairWith(tag)) {
                    openingTags.pop();
                } else {
                    throw new IllegalArgumentException("Expected: \""
                        + mostRecentOpener.toClosingTag() + "\" at index " + i
                        + ", instead found: \"" + tag + "\"");
                }
            } else {
                openingTags.push(tag);
            }
        }
        if (! openingTags.isEmpty()) {
            throw new IllegalArgumentException("Tag: \"" + openingTags.peek()
                + "\" was never closed");
        }
    }
}
