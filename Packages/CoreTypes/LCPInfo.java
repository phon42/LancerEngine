package Packages.CoreTypes;

import java.net.MalformedURLException;
import java.net.URL;
import MainBranch.HelperMethods;
import Packages.CoreTypes.lcpInfo.LCPDependency;
import Packages.CoreTypes.lcpInfo.Version;

/**
 * From https://github.com/massif-press/lancer-data/blob/master/README.md#lcp-structure.
 */
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
    /**
     * The version of the LCP (i.e. a Version representing 1.0.0 or 1.4.0).
     * Can be any Version. Cannot be null.
     */
    private Version version;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * Unknown purpose. Only present in the base Lancer LCP, where its value is
     *     true.
     * Can be any boolean.
     * Default value: true.
     */
    private boolean active;
    /**
     * Contains the default value for this.active.
     */
    private static final boolean activeDefault = true;

    // Optional properties
    /**
     * The prefix of this LCP (i.e. "dustgrave-").
     * In theory, this should be a prefix/infix in the id of every object
     *     contained within this LCP. In practice, I can't find anywhere within
     *     the Dustgrave LCP where this actually has an effect.
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String itemPrefix;
    /**
     * The String representation of the URL at which the image for this LCP is
     *     stored (i.e.
     *     "https://d2c79xe1p61csc.cloudfront.net/other/dustgravecover.jpg").
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String imageURLRaw;
    /**
     * The URL representation of the URL at which the image for this LCP is
     *     stored (i.e. a URL representing
     *     https://d2c79xe1p61csc.cloudfront.net/other/dustgravecover.jpg).
     * Value depends on this.imageURLRaw. If the URL is malformed, is null.
     * Can be any URL. Can be null.
     */
    private URL imageURL;
    /**
     * The String representation of the URL at which the website for this LCP is
     *     stored (i.e. "https://massif-press.itch.io/lancer-core-book").
     * Can be any String except "". Can be null.
     * Case-insensitive and stored in lowercase.
     */
    private String websiteRaw;
    /**
     * The URL representation of the URL at which the website for this LCP is
     *     stored (i.e. a URL representing
     *     https://massif-press.itch.io/lancer-core-book).
     * Can be any URL. Can be null.
     */
    private URL website;
    /**
     * A list of this LCP's dependencies (which are also LCPs) (unable to find
     *     an example).
     * Can be any LCPDependency[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private LCPDependency[] dependencies;
    /**
     * Only used for the base Lancer LCP to store the info.json "version"
     *     property. In that case, is "July 2020 Release".
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String releaseDate;

    /**
     * A constructor which takes in every possible property. Used for the base
     *     Lancer LCP.
     */
    public LCPInfo(
        // Required properties
        String name, String author, String description, String version,
        // Semi-required property
        TriState active,
        // Optional properties
        String itemPrefix, String imageURL, String website,
            LCPDependency[] dependencies, String releaseDate
    ) {
        setName(name);
        setAuthor(author);
        setDescription(new HTMLString(description));
        if (this.name.equals("LANCER Core")) {
            // the base Lancer LCP
            setVersionFromString("1.0.0");
            setReleaseDate(version);
        } else {
            setVersionFromString(version);
        }
        setActive(active);
        setItemPrefix(itemPrefix);
        setImageURLRaw(imageURL);
        // setImageURL removed because setImageURLRaw automatically calls it
        setWebsiteRaw(website);
        // setWebsite removed because setWebsiteRaw automatically calls it
        // setReleaseDate removed because it's called if needed earlier in this
        //     method
        setDependencies(dependencies);
    }
    /**
     * A constructor which takes in every possible property except
     *     LCPInfo.releaseDate. Helpful for LCPs that are not the base Lancer
     *     LCP.
     */
    public LCPInfo(
        // Required properties
        String name, String author, String description, String version,
        // Semi-required property
        TriState active,
        // Optional properties
        String itemPrefix, String imageURL, String website,
            LCPDependency[] dependencies
    ) {
        this(name, author, description, version, active, itemPrefix, imageURL,
            website, dependencies, null);
    }
    /**
     * A constructor using only the required properties.
     */
    public LCPInfo(String name, String author, String description,
        String version) {
        this(name, author, description, version, TriState.UNSET,
            null, null, null, null,
            null);
    }
    public LCPInfo(LCPInfo lcpInfo) {
        setName(lcpInfo.name);
        setAuthor(lcpInfo.author);
        setDescription(lcpInfo.description);
        setVersion(lcpInfo.version);
        setActive(TriState.toTriState(lcpInfo.active));
        setItemPrefix(lcpInfo.itemPrefix);
        setImageURLRaw(lcpInfo.imageURLRaw);
        // setImageURL removed because setImageURLRaw automatically calls it
        setWebsiteRaw(lcpInfo.websiteRaw);
        // setWebsite removed because setWebsiteRaw automatically calls it
        setReleaseDate(lcpInfo.releaseDate);
        setDependencies(lcpInfo.dependencies);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public HTMLString getDescription() {
        return description;
    }
    public Version getVersion() {
        return new Version(version);
    }
    // Semi-required property
    public boolean isActive() {
        return active;
    }
    // Optional properties
    public String getItemPrefix() {
        return itemPrefix;
    }
    public String getImageURLRaw() {
        return imageURLRaw;
    }
    public URL getImageURL() {
        if (imageURL == null) {
            return this.imageURL;
        }
        try {
            return new URL(imageURL.toString());
        } catch (MalformedURLException exception) {
            return null;
        }
    }
    public String getWebsiteRaw() {
        return websiteRaw;
    }
    public URL getWebsite() {
        if (website == null) {
            return website;
        }
        try {
            return new URL(website.toString());
        } catch (MalformedURLException exception) {
            return null;
        }
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public LCPDependency[] getDependencies() {
        if (dependencies == null) {
            return dependencies;
        }

        return HelperMethods.copyOf(dependencies);
    }
    // Required properties
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
    private void setVersion(Version version) {
        HelperMethods.checkObject("version", version);
        version = new Version(version);
        this.version = version;
    }
    // Semi-required property
    private void setActive(TriState active) {
        HelperMethods.checkObject("active", active);
        if (active == TriState.UNSET) {
            this.active = LCPInfo.activeDefault;
        } else {
            this.active = active.toBoolean();
        }
    }
    // Optional properties
    private void setItemPrefix(String itemPrefix) {
        if (itemPrefix != null) {
            HelperMethods.checkString("itemPrefix", itemPrefix);
            itemPrefix = itemPrefix.toLowerCase();
        }
        this.itemPrefix = itemPrefix;
    }
    private void setImageURLRaw(String imageURLRaw) {
        if (imageURLRaw != null) {
            HelperMethods.checkString("imageURLRaw", imageURLRaw);
            imageURLRaw = imageURLRaw.toLowerCase();
        }
        this.imageURLRaw = imageURLRaw;
        calculateImageURL();
    }
    private void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }
    private void setWebsiteRaw(String websiteRaw) {
        if (websiteRaw != null) {
            HelperMethods.checkString("websiteRaw", websiteRaw);
            websiteRaw = websiteRaw.toLowerCase();
        }
        this.websiteRaw = websiteRaw;
        calculateWebsite();
    }
    private void setWebsite(URL website) {
        this.website = website;
    }
    private void setReleaseDate(String releaseDate) {
        if (releaseDate != null) {
            HelperMethods.checkString("releaseDate", releaseDate);
        }
        this.releaseDate = releaseDate;
    }
    private void setDependencies(LCPDependency[] dependencies) {
        if (dependencies != null) {
            if (dependencies.length == 0) {
                throw new IllegalArgumentException("dependencies array is of"
                    + " length 0");
            }
            HelperMethods.checkObjectArray("dependencies",
                dependencies);
            dependencies = HelperMethods.copyOf(dependencies);
        }
        this.dependencies = dependencies;
    }

    private void setVersionFromString(String version) {
        Version versionObject;

        versionObject = new Version(version);
        setVersion(versionObject);
    }
    private void calculateImageURL() {
        URL url;

        if (imageURLRaw == null) {
            setImageURL(null);
        } else {
            try {
                url = new URL(imageURLRaw);
            } catch (MalformedURLException exception) {
                url = null;
            }
            setImageURL(url);
        }
    }
    private void calculateWebsite() {
        URL url;

        if (this.websiteRaw == null) {
            url = null;
        } else {
            try {
                url = new URL(this.websiteRaw);
            } catch (MalformedURLException exception) {
                url = null;
            }
        }
        setWebsite(url);
    }
}
