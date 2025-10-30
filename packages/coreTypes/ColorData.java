package packages.coreTypes;

import main.HelperMethods;
import main.UserPreferences;

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

        if (newColor == null) {
            throw new IllegalArgumentException("newColor is null");
        }
        for (int i = 0; i < newColors.length; i++) {
            if (i == ColorData.colors.length) {
                newColors[i] = newColor;
                continue;
            }
            newColors[i] = ColorData.colors[i];
        }
        ColorData.colors = newColors;
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
