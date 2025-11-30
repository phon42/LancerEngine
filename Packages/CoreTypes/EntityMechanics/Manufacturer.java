package Packages.CoreTypes.EntityMechanics;

import java.net.URL;
import java.nio.file.Path;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import Packages.CoreTypes.ColorData;
import Packages.CoreTypes.VueHTMLString;

/**
 * Represents a single manufacturer. Contains information about the
 *     manufacturer's id, name, logo, light color, dark color, slogan, and
 *     description.
 * 
 * Requires a manufacturer id, name, logo, light color, dark color, slogan, and
 *     description to be instantiated.
 * 
 * Used in equipment classes (i.e. children of Equipment), as well as Frame.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Manufacturer {
    // Required properties
    /**
     * The id of the manufacturer (i.e. "GMS").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in uppercase.
     */
    private String id;
    /**
     * The name of the manufacturer (i.e. "GENERAL MASSIVE SYSTEMS").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in uppercase.
     */
    private String name;
    /**
     * The file path of the manufacturer's logo (mostly unused in this program)
     *     represented as a Path (i.e. a Path representing "gms").
     * Can be any Path. Cannot be null.
     */
    private Path logo;
    /**
     * The file path of the manufacturer's logo (mostly unused in this program)
     *     represented as a String (i.e. "gms").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String logoRaw;
    /**
     * This manufacturer's light-mode color (i.e. a ColorData that represents
     *     #991E2A).
     * Can be any ColorData. Cannot be null.
     */
    private ColorData light;
    /**
     * This manufacturer's dark-mode color (i.e. a ColorData that represents
     *     #DB1A2D).
     * Can be any ColorData. Cannot be null.
     */
    private ColorData dark;
    /**
     * The manufacturer's slogan.
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    private VueHTMLString quote;

    // Optional properties
    /**
     * A URL to the manufacturer's logo (unable to provide an example).
     * Can be any URL. Can be null.
     */
    private URL logoURL;
    /**
     * A URL to the manufacturer's logo represented as a String (unable to
     *     provide an example).
     * Can be any String except "". Can be null.
     */
    private String logoURLRaw;
    /**
     * The manufacturer's description.
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString description;

    public Manufacturer(String id, String name, String logo, String light,
        String dark, String quote, String logoURL, String description) {
        // Required properties
        setID(id);
        setName(name);
        // setLogo removed because it's automatically set by setLogoRaw
        setLogoRaw(logo);
        setLight(light);
        setDark(dark);
        setQuote(quote);
        // Optional properties
        // setLogoURL removed because it's automatically set by setLogoURLRaw
        setLogoURLRaw(logoURL);
        setDescription(description);
    }
    public Manufacturer(Manufacturer manufacturer) {
        // Required properties
        setID(manufacturer.id);
        setName(manufacturer.name);
        // setLogo removed because it's automatically set by setLogoRaw
        setLogoRaw(manufacturer.logoRaw);
        setLight(manufacturer.light);
        setDark(manufacturer.dark);
        setQuote(manufacturer.quote);
        // Optional properties
        // setLogoURL removed because it's automatically set by setLogoURLRaw
        setLogoURLRaw(manufacturer.logoURLRaw);
        setDescription(manufacturer.description);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Path getLogo() {
        return logo.normalize();
    }
    public String getLogoRaw() {
        return logoRaw;
    }
    public ColorData getLight() {
        return light;
    }
    public ColorData getDark() {
        return dark;
    }
    public VueHTMLString getQuote() {
        return quote;
    }
    // Optional properties
    public URL getLogoURL() {
        return logoURL;
    }
    public String getLogoURLRaw() {
        return logoURLRaw;
    }
    public VueHTMLString getDescription() {
        return description;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toUpperCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toUpperCase();
        this.name = name;
    }
    private void setLogo(Path logo) {
        HelperMethods.checkObject("logo", logo);
        logo = logo.normalize().toAbsolutePath();
        this.logo = logo;
    }
    private void setLogoRaw(String logoRaw) {
        HelperMethods.checkString("logoRaw", logoRaw);
        this.logoRaw = logoRaw;
        setLogo(Path.of(logoRaw));
    }
    private void setLight(ColorData light) {
        HelperMethods.checkObject("light", light);
        light = new ColorData(light);
        this.light = light;
    }
    private void setDark(ColorData dark) {
        HelperMethods.checkObject("dark", dark);
        dark = new ColorData(dark);
        this.dark = dark;
    }
    private void setQuote(VueHTMLString quote) {
        HelperMethods.checkVueHTMLString("quote", quote);
        this.quote = quote;
    }
    // Optional properties
    private void setLogoURL(URL logoURL) {
        this.logoURL = logoURL;
    }
    private void setLogoURLRaw(String logoURLRaw) {
        URL url = null;

        if (logoURLRaw != null) {
            HelperMethods.checkString("logoURLRaw", logoURLRaw);
        }
        this.logoURLRaw = logoURLRaw;
        url = FileOperations.toURLCaught(logoURLRaw);
        setLogoURL(url);
    }
    private void setDescription(VueHTMLString description) {
        HelperMethods.checkVueHTMLString("description",
            description);
        this.description = description;
    }

    // Required properties
    private void setLight(String light) {
        this.light = new ColorData(light);
    }
    private void setDark(String dark) {
        this.dark = new ColorData(light);
    }
    private void setQuote(String quote) {
        setQuote(new VueHTMLString(quote));
    }
    // Optional property
    private void setDescription(String description) {
        setDescription(new VueHTMLString(description));
    }
}
