package Packages.CoreTypes;

public class LCPManifest {
    // Required properties
    private String name;
    private String author;
    private HTMLString description;
    private Version version;
    // Optional properties
    private String itemPrefix;
    private String imageURL;
    private String website;
    private LCPDependency[] dependencies;
}
