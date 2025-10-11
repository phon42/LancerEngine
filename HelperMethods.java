// TODO: Use polymorphic methods or generic types or something to combine all
//     these methods together
/**
 * A helper class containing useful methods.
 * 
 * Cannot be instantiated. All its methods are static.
 * 
 * Used in myriad other classes.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public class HelperMethods {
    // Prevent user from instantiating this class
    private HelperMethods() {}

    // TODO: fill out with a helper method that outputs a properly formatted
    //     String from an array; there are bits of code that perform this
    //     function all over this project:
    //         Loadout.generateOutput() line 224, Mech.outputWeapons()
    //         Mech.outputSystems(), Pilot.outputLicenses() line 860
    //         Pilot.outputCoreBonuses() line 961, Pilot.outputTalents() line
    //         910, SkillTriggersList.generateOutput()
    // TODO: add null checks to all the HelperMethods methods
    /**
     * Appends the given int element to the end of an existing int[].
     * @param intArray an int[] that cannot be null.
     * @param newElement an int to append to the end of intArray.
     * @return an int[] consisting of the intArray with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if intArray is null.
     */
    public static int[] append(int[] intArray, int newElement) {
        if (intArray == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(int[], int) with null in the place"
                + " of the int[]");
        }
        int[] newIntArray = new int[intArray.length + 1];

        for (int i = 0; i < newIntArray.length; i++) {
            if (i < intArray.length) {
                newIntArray[i] = intArray[i];
                continue;
            }
            newIntArray[i] = newElement;
        }
        
        return newIntArray;
    }
    /**
     * Appends the given String element to the end of an existing String[].
     * @param stringArray a String[] that cannot be null.
     * @param newElement a String that cannot be null to append to the end of
     *     stringArray.
     * @return a String[] consisting of stringArray with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if stringArray or newElement is null.
     */
    public static String[] append(String[] stringArray, String newElement) {
        if (stringArray == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(String[], String) with null in the"
                + " place of the String[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(String[], String) with null in the"
                + " place of the String");
        }
        String[] newStringArray = new String[stringArray.length + 1];

        for (int i = 0; i < newStringArray.length; i++) {
            if (i < stringArray.length) {
                newStringArray[i] = stringArray[i];
                continue;
            }
            newStringArray[i] = newElement;
        }
        
        return newStringArray;
    }
    // TODO: maybe add a HelperMethods.add(Mount[], Mount) version of this
    //     method that automatically calls either HelperMethods.add(Mount[],
    //     Mount, 0) or HelperMethods.add(Mount[], Mount, Mount[].length)?
    /**
     * Adds the given Mount element to an existing Mount[] at some specified
     *     index.
     * @param mountArray a Mount[] that cannot be null.
     * @param newElement a Mount that cannot be null to add to mountArray.
     * @param index an int containing the index at which to insert newElement.
     * @return a Mount[] consisting of mountArray with newElement added to it.
     * @throws IllegalArgumentException if mountArray or newElement is null.
     */
    public static Mount[] add(Mount[] mountArray, Mount newElement, int index) {
        if (mountArray == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.add(Mount[], Mount) with null in the"
                + " place of the Mount[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.add(Mount[], Mount) with null in the"
                + " place of the Mount");
        }
        Mount[] newMountArray = new Mount[mountArray.length + 1];

        for (int i = 0; i < newMountArray.length; i++) {
            if (i < index) {
                newMountArray[i] = mountArray[i];
                continue;
            }
            if (i == index) {
                newMountArray[i] = newElement;
                continue;
            }
            newMountArray[i] = mountArray[i - 1];
        }

        return newMountArray;
    }
    /**
     * Returns a deep copy of original.
     * @param original an int[] that cannot be null.
     * @return an int[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static int[] copyOf(int[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(int[]) with null in the place of"
                + " the int[]");
        }
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deep copy of original.
     * @param original a String[] that cannot be null.
     * @return a String[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static String[] copyOf(String[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(String[]) with null in the place of"
                + " the String[]");
        }
        String[] copy = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a License[] that cannot be null.
     * @return a License[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static License[] copyOf(License[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(License[]) with null in the place of"
                + " the License[]");
        }
        License[] copy = new License[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a MechSystem[] that cannot be null.
     * @return a MechSystem[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static MechSystem[] copyOf(MechSystem[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(MechSystem[]) with null in the place"
                + " of the MechSystem[]");
        }
        MechSystem[] copy = new MechSystem[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Mount[] that cannot be null.
     * @return a Mount[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Mount[] copyOf(Mount[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(Mount[]) with null in the place of"
                + " the Mount[]");
        }
        Mount[] copy = new Mount[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a SkillTrigger[] that cannot be null.
     * @return a SkillTrigger[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static SkillTrigger[] copyOf(SkillTrigger[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(SkillTrigger[]) with null in the"
                + " place of the SkillTrigger[]");
        }
        SkillTrigger[] copy = new SkillTrigger[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Talent[] that cannot be null.
     * @return a Talent[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Talent[] copyOf(Talent[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(Talent[]) with null in the place of"
                + " the Talent[]");
        }
        Talent[] copy = new Talent[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an EquipmentTag[] that cannot be null.
     * @return an EquipmentTag[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static EquipmentTag[] copyOf(EquipmentTag[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(EquipmentTag[]) with null in the"
                + " place of the EquipmentTag[]");
        }
        EquipmentTag[] copy = new EquipmentTag[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    /**
     * Converts a String to proper case, in which the first letter of every word
     *     is capitalized.
     * @param input a String to be converted that cannot be null.
     * @return a String containing the converted text.
     */
    public static String toProperCase(String input) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String[] stringArr = new String[] {""};
        String substring;
        boolean isLetter;
        boolean lastIsLetter;
        // had to add an entire variable just because of one singular mech. fml
        char lastLetter = ' ';
        
        if (input.length() <= 1) {
            return input.toUpperCase();
        }
        isLetter = (alphabet.indexOf(input.substring(0, 1))
            != -1);
        lastIsLetter = isLetter;
        for (int i = 0; i < input.length(); i++) {
            substring = input.substring(i, i + 1);
            isLetter = (alphabet.indexOf(substring) != -1);
            // this is literally just to stop death's head from fucking
            //     everything up
            if (lastLetter == '\'') {
                stringArr[stringArr.length - 1] += substring;
            } else if (isLetter == lastIsLetter) {
                stringArr[stringArr.length - 1] += substring;
            } else {
                stringArr = HelperMethods.append(stringArr, substring);
            }
            lastIsLetter = isLetter;
            lastLetter = substring.charAt(0);
        }
        String string1;
        for (int i = 0; i < stringArr.length; i++) {
            string1 = stringArr[i].substring(
                0, 1).toUpperCase();
            if (stringArr[i].length() > 1) {
                string1 += stringArr[i].substring(1);
            }
            stringArr[i] = string1;
        }
        input = String.join("", stringArr);

        return input;
    }
    /**
     * Capitalizes the first character of a provided String.
     * @param input a String to be formatted.
     * @return a String containing the formatted String.
     */
    public static String capitalizeFirst(String input) {
        String outputString = "";

        outputString += input.substring(0, 1);
        outputString = outputString.toUpperCase();
        outputString += input.substring(1);

        return outputString;
    }
}
