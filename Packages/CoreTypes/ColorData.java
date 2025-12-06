package Packages.CoreTypes;

import java.util.ArrayList;
import MainBranch.HelperMethods;
import MainBranch.UserPreferences;

/**
 * See java.awt.Color.
 */
public class ColorData {
    // Required properties
    /**
     * This color's red value.
     * Must be between 0 and ColorData.maxValue (inclusive).
     */
    private int red;
    /**
     * This color's green value.
     * Must be between 0 and ColorData.maxValue (inclusive).
     */
    private int green;
    /**
     * This color's blue value.
     * Must be between 0 and ColorData.maxValue (inclusive).
     */
    private int blue;
    /**
     * This color's alpha value (controls transparency).
     * Must be between 0 and ColorData.maxValue (inclusive).
     */
    private int alpha;

    // Static properties
    /**
     * The resolution of ColorData objects' properties, stored as the maximum
     *     value a ColorData object's property (i.e. red, green, or blue) can
     *     have.
     * Must be a minimum of 1.
     */
    private static int maxValue;
    // TODO: one issue with this is that it doesn't know when the color is no
    //     longer in use so the object never gets garbage collected and the
    //     ColorData.colors property will just endlessly grow
    private static ArrayList<ColorData> colors;

    static {
        calcMax();
        ColorData.colors = new ArrayList<>();
    }

    public ColorData(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(blue);
        addColor(this);
    }
    public ColorData(String colorString) {
        parseString(colorString);
        addColor(this);
    }

    // Required properties
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
    public int getAlpha() {
        return alpha;
    }
    // Required properties
    private void setRed(int red) {
        setProperty("red", red);
    }
    private void setGreen(int green) {
        setProperty("green", green);
    }
    private void setBlue(int blue) {
        setProperty("blue", blue);
    }
    private void setAlpha(int alpha) {
        setProperty("alpha", alpha);
    }
    // Static properties
    private static void setMaxValue(int maxValue) {
        maxValue = Math.max(maxValue, 1);
        ColorData.maxValue = maxValue;
    }

    private void setProperty(String property, int value) {
        if (value < 0) {
            throw new IllegalArgumentException(property + " value: " + value
                + " is < 0");
        } else if (value > ColorData.maxValue) {
            throw new IllegalArgumentException(property + " value: " + value
                + " is > " + ColorData.maxValue);
        }
        if (property.equals("red")) {
            this.red = value;
        } else if (property.equals("green")) {
            this.green = value;
        } else if (property.equals("blue")) {
            this.blue = value;
        } else if (property.equals("alpha")) {
            this.alpha = value;
        }
    }
    private static void calcMax() {
        setMaxValue(255 * UserPreferences.getColorResolution());
    }
    private static void addColor(ColorData newColor) {
        HelperMethods.checkObject("newColor", newColor);
        ColorData.colors.add(newColor);
    }
    private void parseString(String colorString) {
        // Stores the original query
        String original = colorString;
        // Constants
        final String rgb = "rgb(";
        final String rgba = "rgba(";
        // Helpful for determining which type to expect
        boolean containsHashtag;
        boolean containsRGB;
        boolean containsRGBA;
        // Checks whether multiple types are present in the input
        int total = 0;
        // For hashtag-format parsing
        String dataSnippet = null;
        String newColorString = null;
        int colorProperty = -1;
        // For rgb- (and rgba-) format parsing
        String[] rgbStrings;
        boolean containsHexFormat = false;
        String[] rgbStringsNew;


        HelperMethods.checkObject("colorString", colorString);
        colorString = colorString.toLowerCase();
        containsHashtag = colorString.indexOf("#") != -1;
        containsRGB = colorString.indexOf(rgb) != -1;
        containsRGBA = colorString.indexOf(rgba) != -1;
        if (containsHashtag == containsRGB && containsRGB == containsRGBA) {
            // neither of these cases is allowed
            throw new IllegalArgumentException("Cannot parse a color from: \""
                + original + "\"");
        }
        total = (containsHashtag ? 1 : 0) + (containsRGB ? 1 : 0)
            + (containsRGBA ? 1 : 0);
        if (total > 1) {
            throw new IllegalArgumentException("Cannot parse a color from: \""
                + original + "\"");
        }
        // at this point only one of containsHashtag, containsRGB, containsRGBA
        //     can be 1
        if (containsHashtag) {
            // parse hex strings
            if (colorString.length() == 1) {
                // colorString is "#"
                throw new IllegalArgumentException("Cannot parse a color from:"
                    + " \"" + original + "\"");
            }
            if (colorString.length() > 9) {
                // longer than "#RRGGBBAA"
                throw new IllegalArgumentException("Cannot parse a color from:"
                    + " \"" + original + "\"");
            }
            if (colorString.length() == 2) {
                // colorString is "#C" (translates to #C0C0C0ff)
                dataSnippet = colorString.substring(1) + "0";
                colorString = "#" + dataSnippet.repeat(3);
                colorString += "ff";
            } else if (colorString.length() == 3) {
                // colorString is "#CD" (translates to #CDCDCDff)
                dataSnippet = colorString.substring(1);
                colorString = "#" + dataSnippet.repeat(3);
                colorString += "ff";
            } else if (colorString.length() == 4) {
                // colorString is "#RGB" (translates to #R0G0B0ff)
                newColorString = "#";
                for (int i = 0; i < 6; i += 2) {
                    dataSnippet = colorString.substring(1 + i, 2 + i) + "0";
                    newColorString += dataSnippet;
                }
                colorString = newColorString;
                colorString += "ff";
            } else if (colorString.length() == 5) {
                // colorString is "#RGBA" (translates to #R0G0B0A0)
                newColorString = "#";
                for (int i = 0; i < 8; i += 2) {
                    dataSnippet = colorString.substring(1 + i, 2 + i) + "0";
                    newColorString += dataSnippet;
                }
                colorString = newColorString;
            } else if (colorString.length() == 6) {
                // colorString is "#RGBAA" (translates to #R0G0B0AA)
                newColorString = "#";
                for (int i = 0; i < 6; i += 2) {
                    dataSnippet = colorString.substring(1 + i, 2 + i) + "0";
                    newColorString += dataSnippet;
                }
                newColorString += colorString.substring(4);
                colorString = newColorString;
            } else if (colorString.length() == 7) {
                // colorString is "#RRGGBB" (alpha value is 255)
                colorString += "FF";
            } else if (colorString.length() == 8) {
                // colorString is "#RRGGBBA" (translates to #RRGGBBA0)
                colorString += "0";
            }
            if (colorString.length() == 9) {
                // colorString is "#RRGGBBAA"
                // get red
                dataSnippet = colorString.substring(1, 3);
                colorProperty = Integer.parseInt(dataSnippet, 16);
                setRed(colorProperty * UserPreferences.getColorResolution());
                // get green
                dataSnippet = colorString.substring(3, 5);
                colorProperty = Integer.parseInt(dataSnippet, 16);
                setGreen(colorProperty * UserPreferences.getColorResolution());
                // get blue
                dataSnippet = colorString.substring(5, 7);
                colorProperty = Integer.parseInt(dataSnippet, 16);
                setBlue(colorProperty * UserPreferences.getColorResolution());
                // get alpha
                dataSnippet = colorString.substring(7);
                colorProperty = Integer.parseInt(dataSnippet, 16);
                setAlpha(colorProperty * UserPreferences.getColorResolution());
            }

            return;
        } else if (containsRGB) {
            // parse "rgb(...)"
            if (colorString.length() < 5) {
                throw new IllegalArgumentException("colorString is too short at"
                    + " length: " + colorString);
            }
            colorString = colorString.substring(colorString.indexOf(rgb) + 4);
            colorString = colorString.substring(0,
                colorString.lastIndexOf(")"));
        } else {
            // parse "rgba(...)"
            if (colorString.length() < 6) {
                throw new IllegalArgumentException("colorString is too short at"
                    + " length: " + colorString);
            }
            colorString = colorString.substring(colorString.indexOf(rgba) + 5);
            colorString = colorString.substring(0,
                colorString.lastIndexOf(")"));
        }
        rgbStrings = colorString.split(",");
        for (String property : rgbStrings) {
            if (HelperMethods.indexOf(property, "[a-f]") != -1) {
                containsHexFormat = true;
                break;
            }
        }
        for (String property : rgbStrings) {
            if (! property.equals("")) {
                total++;
            }
        }
        total = 0;
        rgbStringsNew = new String[total];
        for (int i = 0; i < rgbStrings.length; i++) {
            rgbStringsNew[total] = rgbStrings[i];
            if (! rgbStrings[i].equals("")) {
                total++;
            }
        }
        rgbStrings = rgbStringsNew;
        if (rgbStrings.length == 0) {
            throw new IllegalStateException("Unable to extract enough data for"
                + " a color from: \"" + original + "\"");
        } else if (rgbStrings.length == 1) {
            colorProperty = parse(rgbStrings[0], containsHexFormat);
            setRed(colorProperty);
            setGreen(colorProperty);
            setBlue(colorProperty);
            if (containsRGBA) {
                setAlpha(colorProperty);
            }
        } else if (rgbStrings.length == 2) {
            colorProperty = parse(rgbStrings[0], containsHexFormat);
            setRed(colorProperty);
            setGreen(colorProperty);
            setBlue(colorProperty);
            colorProperty = parse(rgbStrings[1], containsHexFormat);
            if (containsRGBA) {
                setAlpha(colorProperty);
            }
        } else if (rgbStrings.length == 3) {
            if (containsRGBA) {
                throw new IllegalStateException("Unable to extract enough data"
                    + " for a color of the format \"rgba(...)\" from: \""
                    + original + "\"");
            }
            colorProperty = parse(rgbStrings[0], containsHexFormat);
            setRed(colorProperty);
            colorProperty = parse(rgbStrings[1], containsHexFormat);
            setGreen(colorProperty);
            colorProperty = parse(rgbStrings[2], containsHexFormat);
            setBlue(colorProperty);
        } else if (rgbStrings.length >= 4 && containsRGB && ! containsRGBA) {
            throw new IllegalStateException("Color string: \"" + original + "\""
                + " contains too much data to extract a color of the format"
                + " \"rgb(...)\"");
        } else if (rgbStrings.length == 4) {
            colorProperty = parse(rgbStrings[0], containsHexFormat);
            setRed(colorProperty);
            colorProperty = parse(rgbStrings[1], containsHexFormat);
            setGreen(colorProperty);
            colorProperty = parse(rgbStrings[2], containsHexFormat);
            setBlue(colorProperty);
            colorProperty = parse(rgbStrings[3], containsHexFormat);
            setAlpha(colorProperty);
        } else {
            throw new IllegalStateException("Color string: \"" + original + "\""
                + " contains too much data to extract a color of the format"
                + " \"rgba(...)\"");
        }
    }
    private static int parse(String input, boolean useHex) {
        HelperMethods.checkObject("input", input);
        if (useHex) {
            return HelperMethods.parseInt(input, 16);
        }

        return HelperMethods.parseInt(input);
    }
    public static void changeResolution(int oldResolution, int newResolution) {
        int oldMax = ColorData.maxValue;

        calcMax();
        for (int i = 0; i < ColorData.colors.size(); i++) {
            ColorData.colors.get(i).updateResolution(oldMax);
        }
    }
    private void updateResolution(int oldMax) {
        this.red = HelperMethods.div(this.red * ColorData.maxValue, oldMax);
        this.green = HelperMethods.div(this.green * ColorData.maxValue, oldMax);
        this.blue = HelperMethods.div(this.blue * ColorData.maxValue, oldMax);
        this.alpha = HelperMethods.div(this.alpha * ColorData.maxValue, oldMax);
    }
}
