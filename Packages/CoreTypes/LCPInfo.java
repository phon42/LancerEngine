package Packages.CoreTypes;

import MainBranch.HelperMethods;

public class LCPInfo {
    // Required properties
    /**
     * The name of this LCP (i.e. "LANCER Core" or "LANCER: Dustgrave").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The author of this LCP (i.e. "Massif Press" or "Ralf Ziegler").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String author;
    /**
     * The description of this LCP (i.e. an HTMLString representing "The"
     *     " official base game").
     * Can be any HTMLString except an HTMLString representing "". Cannot be
     *     null.
     * Case-sensitive.
     */
    private HTMLString description;
    private Version version;
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Unknown purpose. Only present in the base Lancer LCP, where its value is
     *     true.
     * Default value: true.
     */
    private boolean active;
    private static final boolean activeDefault = true;
    // Optional properties
    private String itemPrefix;
    private String imageURL;
    private String website;
    private LCPDependency[] dependencies;

    public LCPInfo(
        // Required properties
        String name, String author, String description, String version,
        // Semi-required properties
        TriState active,
        // Optional properties
        String itemPrefix, String imageURL, String website,
            LCPDependency[] dependencies
    ) {
        // TODO: fill out
        setName(name);
        setAuthor(author);
        setDescription(new HTMLString(description));
    }
    public LCPInfo(String name, String author, String description, String version) {
        this(name, author, description, version, TriState.UNSET,
            null, null, null, null);
    }
    public LCPInfo(LCPInfo lcpInfo) {
        // TODO: fill out
        setName(lcpInfo.name);
        setAuthor(lcpInfo.author);
        setDescription(lcpInfo.description);
    }

    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public HTMLString getDescription() {
        return description;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setAuthor(String author) {
        HelperMethods.checkString("author", author);
        this.author = author;
    }
    private void setDescription(HTMLString description) {
        HelperMethods.checkHTMLString("description", description);
        description = new HTMLString(description);
        this.description = description;
    }
}
