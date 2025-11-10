package Packages.CoreTypes;

public class LCPInfo {
    // Required properties
    private String name;
    private String author;
    private HTMLString description;
    private Version version;
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Unknown purpose. Only present in the base Lancer lcp, where its value is
     *     true.
     * Default value: true.
     */
    private boolean active;
    // Optional properties
    private String itemPrefix;
    private String imageURL;
    private String website;
    private LCPDependency[] dependencies;
}
