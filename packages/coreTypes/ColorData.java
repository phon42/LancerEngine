package Packages.CoreTypes;

import MainBranch.HelperMethods;
import MainBranch.UserPreferences;

/**
 * See java.awt.Color.
 */
public class ColorData {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private static int maxValue;
    // TODO: one issue with this is that it doesn't know when the color is no
    //     longer in use so the object never gets garbage collected and the
    //     ColorData.colors property will just endlessly grow
    private static ColorData[] colors;

    static {
        calcMax();
        ColorData.colors = new ColorData[0];
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
    public ColorData(ColorData colorData) {
        setRed(colorData.red);
        setGreen(colorData.green);
        setBlue(colorData.blue);
        setAlpha(colorData.alpha);
        addColor(this);
    }

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

    private static void calcMax() {
        ColorData.maxValue = 255 * UserPreferences.getColorResolution();
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
    private static void addColor(ColorData newColor) {
        ColorData[] newColors = new ColorData[ColorData.colors.length + 1];

        HelperMethods.checkObject("newColor", newColor);
        for (int i = 0; i < newColors.length; i++) {
            if (i == ColorData.colors.length) {
                newColors[i] = newColor;
                continue;
            }
            newColors[i] = ColorData.colors[i];
        }
        ColorData.colors = newColors;
    }
    private void parseString(String colorString) {
        int containsHashtag = 0;
        int containsRGB = 0;
        int containsRGBA = 0;
        String dataSnippet = null;
        String newColorString = null;
        int colorProperty = -1;

        HelperMethods.checkObject("colorString", colorString);
        containsHashtag = colorString.indexOf("#") != -1 ? 1 : 0;
        containsRGB = colorString.indexOf("rgb(") != -1 ? 1 : 0;
        containsRGBA = colorString.indexOf("rgba") != -1 ? 1 : 0;
        if (containsHashtag == containsRGB && containsRGB == containsRGBA) {
            // neither of these cases is allowed
            throw new IllegalArgumentException("Cannot parse a color from: \""
                + colorString + "\"");
        }
        if (containsHashtag + containsRGB + containsRGBA > 1) {
            throw new IllegalArgumentException("Cannot parse a color from: \""
                + colorString + "\"");
        }
        // at this point only one of containsHashtag, containsRGB, containsRGBA
        //     can be 1
        if (containsHashtag == 1) {
            // parse hex strings
            if (colorString.length() == 1) {
                // colorString is "#"
                throw new IllegalArgumentException("Cannot parse a color from:"
                    + " \"" + colorString + "\"");
            }
            if (colorString.length() == 7) {
                // colorString is some weird abomination
                throw new IllegalArgumentException("Cannot parse a color from:"
                    + " \"" + colorString + "\"");
            }
            if (colorString.length() > 9) {
                // longer than "#RRGGBBAA"
                throw new IllegalArgumentException("Cannot parse a color from:"
                    + " \"" + colorString + "\"");
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
            } else if (colorString.length() == 8) {
                // colorString is "#RRGGBBA" (translates to #RRGGBBA0)
                colorString += "0";
            }
            if (colorString.length() == 9) {
                // colorString is "#RRGGBBAA"
                for (int i = 0; i < 8; i += 2) {
                    colorProperty = Integer.parseInt(dataSnippet, 16);
                }
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
        } else if (containsRGB == 1) {
            // parse "rgb(...)"
            // TODO: implement
        } else {
            // parse "rgba(...)"
            // TODO: implement
        }
    }
    public static void changeResolution(int oldResolution, int newResolution) {
        int oldMax = ColorData.maxValue;

        calcMax();
        for (int i = 0; i < ColorData.colors.length; i++) {
            ColorData.colors[i].updateResolution(oldMax);
        }
    }
    private void updateResolution(int oldMax) {
        this.red = HelperMethods.div(this.red * ColorData.maxValue, oldMax);
        this.green = HelperMethods.div(this.green * ColorData.maxValue, oldMax);
        this.blue = HelperMethods.div(this.blue * ColorData.maxValue, oldMax);
        this.alpha = HelperMethods.div(this.alpha * ColorData.maxValue, oldMax);
    }
}
