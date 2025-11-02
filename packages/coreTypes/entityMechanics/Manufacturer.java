package packages.CoreTypes.entityMechanics;

import main.HelperMethods;
import packages.CoreTypes.ColorData;

/**
 * Represents a single manufacturer. Contains information about the
 *     manufacturer's id, name, logo, light color, dark color, slogan, and
 *     description.
 * 
 * Requires a manufacturer id, name, logo, light color, dark color, slogan, and
 *     description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Manufacturer {
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
     * The file path of the manufacturer's logo (mostly unused in this program).
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String logo;
    /**
     * This manufacturer's light-mode color.
     * Can be any ColorData. Cannot be null.
     */
    private ColorData light;
    /**
     * This manufacturer's dark-mode color.
     * Can be any ColorData. Cannot be null.
     */
    private ColorData dark;
    /**
     * The manufacturer's slogan.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String quote;
    /**
     * The manufacturer's description.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    public Manufacturer(String id, String name, String logo, ColorData light,
        ColorData dark, String quote, String description) {
        setID(id);
        setName(name);
        setLogo(logo);
        setLight(light);
        setDark(dark);
        setQuote(quote);
        setDescription(description);
    }
    public Manufacturer(Manufacturer manufacturer) {
        setID(manufacturer.id);
        setName(manufacturer.name);
        setLogo(manufacturer.logo);
        setLight(manufacturer.light);
        setDark(manufacturer.dark);
        setQuote(manufacturer.quote);
        setDescription(manufacturer.description);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLogo() {
        return logo;
    }
    public ColorData getLight() {
        return light;
    }
    public ColorData getDark() {
        return dark;
    }
    public String getQuote() {
        return quote;
    }
    public String getDescription() {
        return description;
    }
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toUpperCase();
        this.id = id;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toUpperCase();
        this.name = name;
    }
    public void setLogo(String logo) {
        HelperMethods.checkObject("logo", logo);
        this.logo = logo;
    }
    public void setLight(ColorData light) {
        HelperMethods.checkObject("light", light);
        light = new ColorData(light);
        this.light = light;
    }
    public void setDark(ColorData dark) {
        HelperMethods.checkObject("dark", dark);
        dark = new ColorData(dark);
        this.dark = dark;
    }
    public void setQuote(String quote) {
        HelperMethods.checkString("quote", quote);
        this.quote = quote;
    }
    public void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
}
