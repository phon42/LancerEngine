/**
 * A helper class containing useful functions. Cannot be instantiated. All its
 *     methods are static.
 */
public class HelperFunctions {
    // Prevent user from instantiating this class
    private HelperFunctions() {}

    // TODO: fill out with a helper function that outputs a properly formatted
    //     String from an array
    // there are bits of code that perform this function all over this project,
    //     i.e. Mech.outputSystems()
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
                + " HelperFunctions.append(int[], int) with null in the place"
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
     * @param stringArray A String[] that cannot be null.
     * @param newElement A String that cannot be null to append to the end of
     *     stringArray.
     * @return a String[] consisting of stringArray with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if stringArray or newElement is null.
     */
    public static String[] append(String[] stringArray, String newElement) {
        if (stringArray == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.append(String[], String) with null in the"
                + " place of the String[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.append(String[], String) with null in the"
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
    public static int[] copyOf(int[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(int[]) with null in the place of"
                + " the int[]");
        }
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    public static String[] copyOf(String[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(String[]) with null in the place of"
                + " the String[]");
        }
        String[] copy = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    public static License[] copyOf(License[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(License[]) with null in the place of"
                + " the License[]");
        }
        License[] copy = new License[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    public static MechSystem[] copyOf(MechSystem[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(MechSystem[]) with null in the place"
                + " of the MechSystem[]");
        }
        MechSystem[] copy = new MechSystem[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    public static Mount[] copyOf(Mount[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(Mount[]) with null in the place of"
                + " the Mount[]");
        }
        Mount[] copy = new Mount[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    public static SkillTrigger[] copyOf(SkillTrigger[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(SkillTrigger[]) with null in the"
                + " place of the SkillTrigger[]");
        }
        SkillTrigger[] copy = new SkillTrigger[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
    public static Talent[] copyOf(Talent[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperFunctions.copyOf(Talent[]) with null in the place of"
                + " the Talent[]");
        }
        Talent[] copy = new Talent[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].copyOf();
        }

        return copy;
    }
}
