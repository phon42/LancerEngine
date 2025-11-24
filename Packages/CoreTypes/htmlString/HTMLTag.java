package Packages.CoreTypes.htmlString;

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
     * Can be any boolean.
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
}
