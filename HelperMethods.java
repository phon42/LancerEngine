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
public final class HelperMethods {
    /**
     * Contains an array of allowed damage types. Case-insensitive and stored in
     *     lowercase.
     */
    public static final String[] allowedDamageTypes = new String[] {"kinetic",
        "explosive", "energy", "burn"};

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
     * Computes (dividend) divided by (divisor), or dividend / divisor, rounded
     *     up (according to the second golden rule of Lancer - pg. 12).
     * As a reminder, in 3 / 6, 3 is the dividend and 6 is the divisor.
     * @param dividend an int which can be any int.
     * @param divisor an int which cannot be 0.
     * @return an int containing the result of the operation.
     * @throws IllegalArgumentException if divisor is 0.
     */
    public static int div(int dividend, int divisor) {
        int dividendSign;
        int divisorSign;
        int resultSign;

        if (divisor == 0) {
            throw new IllegalArgumentException("divisor is 0. Cannot compute"
                + " because x / 0 is undefined according to the laws of math."
                + " Also it would throw an ArithmeticException");
        }
        if (dividend == 0) {
            return 0;
        }
        // Essentially, we are about to convert (dividend) / (divisor) to three
        //     operations:
        // 1. result sign = (dividend sign) / (divisor sign)
        // 2. |result| = |dividend| / |divisor|
        // 3. result = (result sign) * |result|
        // We will compute (1) here, then call div(int, int, int) for (2) and
        //     (3), then return that result

        // neither dividend or divisor can be 0 at this point
        // dividendSign is 1 if dividend is > 0 and -1 if dividend is < 0
        dividendSign = dividend > 0 ? 1 : -1;
        // divisorSign is 1 if divisor is > 0 and -1 if divisor is < 0
        divisorSign = divisor > 0 ? 1 : -1;
        // this is (result sign), aka step (1)
        resultSign = dividendSign / divisorSign;

        // this is part of step (2) - we convert dividend and divisor to
        //     |dividend| and |divisor|, then feed that to div(int, int, int)
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        // this is the rest of step (2) as well as step (3)
        return div(dividend, divisor, resultSign);
    }
    /**
     * A helper method for div(int, int). Computes (dividend) divided by
     *     (divisor), or dividend / divisor, rounded up (according to the second
     *     golden rule of Lancer - pg. 12). Assumes both dividend and divisor
     *     are positive and non-zero.
     * @param dividend an int which must be > 0.
     * @param divisor an int which must be > 0.
     * @param resultSign an int which must be +1 or -1.
     * @return an int containing the result of the operation.
     */
    private static int div(int dividend, int divisor, int resultSign) {
        double divisionResult;
        int roundedResult;
        int result;

        if (resultSign != 1 && resultSign != -1) {
            throw new IllegalArgumentException("resultSign: " + resultSign
                + " is not +1 or -1");
        }
        // these next two lines are the rest of step (2) as defined in
        //     HelperMethods.div(int, int)
        divisionResult = dividend / (double) divisor;
        roundedResult = (int) Math.round(divisionResult);
        // this is step (3) as defined in HelperMethods.div(int, int)
        result = resultSign * roundedResult;

        return result;
    }
    /**
     * Checks a provided String to see if it is null or "". Ironically, needs to
     *     perform the same function on propertyName beforehand.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input a String which cannot be "". Cannot be null.
     * @throws IllegalArgumentException if propertyName or input are "" or null.
     */
    public static void checkString(String propertyName, String input) {
        if (propertyName == null) {
            throw new IllegalArgumentException("propertyName is null");
        }
        if (propertyName.equals("")) {
            throw new IllegalArgumentException("propertyName is \"\"");
        }
        if (input == null) {
            throw new IllegalArgumentException(propertyName + " is null");
        }
        if (input.equals("")) {
            throw new IllegalArgumentException(propertyName + " is \"\"");
        }
    }
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
    /**
     * Appends the given EventListener element to the end of an existing
     *     EventListener[].
     * @param array an EventListener[] that cannot be null.
     * @param newElement an EventListener that cannot be null to append to the
     *     end of array.
     * @return an EventListener[] consisting of array with newElement appended
     *     to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static EventListener[] append(EventListener[] array,
        EventListener newElement) {
        if (array == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(EventListener[], EventListener) with"
                + " null in the place of the EventListener[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(EventListener[], EventListener) with"
                + " null in the place of the EventListener");
        }
        EventListener[] newArray = new EventListener[array.length + 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < array.length) {
                newArray[i] = array[i];
                continue;
            }
            newArray[i] = newElement;
        }
        
        return newArray;
    }
    /**
     * Appends the given Frame element to the end of an existing Frame[].
     * @param array a Frame[] that cannot be null.
     * @param newElement a Frame that cannot be null to append to the end of
     *     array.
     * @return a Frame[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Frame[] append(Frame[] array, Frame newElement) {
        if (array == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(Frame[], Frame) with null in the place"
                + " of the Frame[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(Frame[], Frame) with null in the"
                + " place of the Frame");
        }
        Frame[] newArray = new Frame[array.length + 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < array.length) {
                newArray[i] = array[i];
                continue;
            }
            newArray[i] = newElement;
        }
        
        return newArray;
    }
    /**
     * Appends the given MechSystem element to the end of an existing
     *     MechSystem[].
     * @param array a MechSystem[] that cannot be null.
     * @param newElement a MechSystem that cannot be null to append to the end
     *     of array.
     * @return a MechSystem[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static MechSystem[] append(MechSystem[] array, MechSystem newElement)
        {
        if (array == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(MechSystem[], MechSystem) with null in"
                + " the place of the MechSystem[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(MechSystem[], MechSystem) with null in"
                + " the place of the MechSystem");
        }
        MechSystem[] newArray = new MechSystem[array.length + 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < array.length) {
                newArray[i] = array[i];
                continue;
            }
            newArray[i] = newElement;
        }
        
        return newArray;
    }
    /**
     * Appends the given State element to the end of an existing State[].
     * @param stateArray a State[] that cannot be null.
     * @param newElement a State that cannot be null to append to the end of
     *     stateArray.
     * @return a State[] consisting of stateArray with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if stateArray or newElement is null.
     */
    public static State[] append(State[] stateArray, State newElement) {
        if (stateArray == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(String[], String) with null in the"
                + " place of the String[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(String[], String) with null in the"
                + " place of the String");
        }
        State[] newStateArray = new State[stateArray.length + 1];

        for (int i = 0; i < newStateArray.length; i++) {
            if (i < stateArray.length) {
                newStateArray[i] = stateArray[i];
                continue;
            }
            newStateArray[i] = newElement;
        }
        
        return newStateArray;
    }
    /**
     * Appends the given Weapon element to the end of an existing Weapon[].
     * @param array a Weapon[] that cannot be null.
     * @param newElement a Weapon that cannot be null to append to the end of
     *     array.
     * @return a Weapon[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Weapon[] append(Weapon[] array, Weapon newElement) {
        if (array == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(Weapon[], Weapon) with null in the"
                + " place of the Weapon[]");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.append(Weapon[], Weapon) with null in the"
                + " place of the Weapon");
        }
        Weapon[] newArray = new Weapon[array.length + 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < array.length) {
                newArray[i] = array[i];
                continue;
            }
            newArray[i] = newElement;
        }
        
        return newArray;
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
            copy[i] = new License(original[i]);
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
            copy[i] = new MechSystem(original[i]);
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
            copy[i] = new Mount(original[i]);
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
            copy[i] = new SkillTrigger(original[i]);
        }

        return copy;
    }
    public static State[] copyOf(State[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(State[]) with null in the place of the"
                + " State[]");
        }
        State[] copy = new State[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new State(original[i]);
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
            copy[i] = new Talent(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Tag[] that cannot be null.
     * @return a Tag[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Tag[] copyOf(Tag[] original) {
        if (original == null) {
            throw new IllegalArgumentException("Called"
                + " HelperMethods.copyOf(Tag[]) with null in the place of the"
                + " Tag[]");
        }
        Tag[] copy = new Tag[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Tag(original[i]);
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
