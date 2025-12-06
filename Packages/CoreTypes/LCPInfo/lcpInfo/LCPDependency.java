package Packages.CoreTypes.LCPInfo.lcpInfo;

import java.net.URL;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import Packages.CoreTypes.LCPInfo.lcpInfo.lcpDependency.SemverVersion;

/**
 * From https://github.com/massif-press/lancer-data/blob/master/README.md#lcp-dependencies.
 */
public class LCPDependency {
    // Required properties
    /**
     * TODO: add example
     * The name of this dependency (i.e. "INSERT EXAMPLE").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * TODO: add example
     * The minimum accepted version of this dependency (i.e. a SemverVersion
     *     representing INSERT EXAMPLE).
     * TODO: update requirements
     * Can be any SemverVersion. Cannot be null.
     */
    private SemverVersion version;

    // Optional properties
    /**
     * TODO: add example
     * Link to the dependency's website as a String (i.e. a String representing
     *     "INSERT EXAMPLE").
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String linkRaw;
    /**
     * TODO: add example
     * Link to the dependency's website as a URL (i.e. a URL representing INSERT
     *     EXAMPLE).
     * Value is based on this.linkRaw.
     * Can be any URL. Can be null.
     */
    private URL link;

    public LCPDependency(String targetLCPName,
        SemverVersion minimumAcceptedVersion, String link) {
        // Required properties
        setName(targetLCPName);
        setVersion(minimumAcceptedVersion);
        // Optional property
        setLinkRaw(link);
    }
    public LCPDependency(String targetLCPName,
        SemverVersion minimumAcceptedVersion) {
        this(targetLCPName, minimumAcceptedVersion, null);
    }
    public LCPDependency(LCPDependency lcpDependency) {
        // Required properties
        setName(lcpDependency.name);
        setVersion(lcpDependency.version);
        // Optional property
        setLinkRaw(lcpDependency.linkRaw);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public SemverVersion getVersion() {
        return new SemverVersion(version);
    }
    // Optional properties
    public String getLinkRaw() {
        return linkRaw;
    }
    public URL getLink() {
        return link;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setVersion(SemverVersion version) {
        HelperMethods.checkObject("version", version);
        version = new SemverVersion(version);
        this.version = version;
    }
    // Optional properties
    private void setLinkRaw(String linkRaw) {
        if (linkRaw != null) {
            HelperMethods.checkString("linkRaw", linkRaw);
            linkRaw = linkRaw.toLowerCase();
        }
        this.linkRaw = linkRaw;
        calculateLink();
    }
    private void setLink(URL link) {
        this.link = link;
    }

    private void calculateLink() {
        URL url = null;

        if (this.linkRaw != null) {
            url = FileOperations.toURLCaught(this.linkRaw);
        }
        setLink(url);
    }
}
