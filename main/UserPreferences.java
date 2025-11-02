package main;

import Packages.CoreTypes.ColorData;

/**
 * Contains a set of preferences/settings for the program.
 */
public class UserPreferences {
    /**
     * Whether all rolls should be given to the user to make with physical dice.
     */
    private static boolean makeRollsManually = false;
    /**
     * Whether the user should be notified whenever some actions occur that the
     *     player can choose the order of.
     */
    private static boolean chooseOrderAutomatically = false;
    /**
     * The level of resolution of the Color class (i.e. a value of 100 will
     *     allow you to store color values with up to two decimal points).
     * Must be a minimum of 1.
     */
    private static int colorResolution = 100;

    // Prevent user from instantiating this class
    private UserPreferences() {}

    public static boolean isMakeRollsManually() {
        return makeRollsManually;
    }
    public static boolean isChooseOrderAutomatically() {
        return chooseOrderAutomatically;
    }
    public static int getColorResolution() {
        return colorResolution;
    }
    public static void setMakeRollsManually(boolean makeRollsManually) {
        UserPreferences.makeRollsManually = makeRollsManually;
    }
    public static void setChooseOrderAutomatically(
        boolean chooseOrderAutomatically) {
        UserPreferences.chooseOrderAutomatically = chooseOrderAutomatically;
    }
    public static void setColorResolution(int colorResolution) {
        if (colorResolution < 1) {
            throw new IllegalArgumentException("colorResolution value: "
                + colorResolution + " is < 1");
        }
        ColorData.changeResolution(UserPreferences.colorResolution,
            colorResolution);
        UserPreferences.colorResolution = colorResolution;
    }
}
