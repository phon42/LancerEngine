package MainBranch;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import MainBranch.database.FileOperations;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;
import Packages.CoreTypes.lcpInfo.LCPDependency;

// TODO: Use polymorphic methods or generic types or something to combine all
//     these methods together
// TODO: update anything that uses a HelperMethods.append method to create a
//     copy of the object being appended first, otherwise it's a security
//     vulnerability
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
    // Prevent user from instantiating this class
    private HelperMethods() {}

    // TODO: fill out with a helper method that outputs a properly formatted
    //     String from an array; there are bits of code that perform this
    //     function all over this project:
    //         Loadout.generateOutput() line 224, Mech.outputWeapons()
    //         Mech.outputSystems(), Pilot.outputLicenses() line 860
    //         Pilot.outputCoreBonuses() line 961, Pilot.outputTalents() line
    //         910, SkillTriggersList.generateOutput()
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
        // can remove the "int"s from the return type, parameter types, the
        //     casts of abs(dividend) and divisor, and finally, the return
        //     statement at the bottom, if you want to divide long variables
        int dividendSign;
        int divisorSign;
        int resultSign;

        if (divisor == 0) {
            throw new IllegalArgumentException("divisor is 0. Cannot compute"
                + " because " + dividend + " / 0 is undefined according to the"
                + " laws of math. Also it would throw an ArithmeticException");
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
        dividend = (int) abs(dividend);
        divisor = (int) abs(divisor);

        // this is the rest of step (2) as well as step (3)
        return (int) div(dividend, divisor, resultSign);
    }
    private static long abs(long input) {
        return input >= 0 ? input : -input;
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
    private static long div(long dividend, long divisor, int resultSign) {
        long newDividend = dividend;
        long result = 0;
        long[] stepResult;
        long powerOf10 = 0;

        // this is the rest of step (2) as defined in HelperMethods.div(int,
        //     int)
        if (dividend == divisor) {
            return 1;
        }
        while (powerOf10 < 2) {
            if (newDividend < 0) {
                throw new IllegalArgumentException("something went wrong");
            } else if (newDividend == 0) {
                powerOf10 += 1;
                newDividend *= 10;
                result *= 10;
                result += 0;
                continue;
            }
            stepResult = divisionStep(newDividend, divisor);
            powerOf10 += stepResult[1];
            newDividend *= pow10(log10(stepResult[0]) + stepResult[1]);
            newDividend -= divisor * stepResult[0];
            result *= pow10(log10(stepResult[0]) + stepResult[1]);
            result += stepResult[0];
        }
        result = (result / 100) + (result % 100 >= 50 ? 1 : 0);
        // this is step (3) as defined in HelperMethods.div(int, int)
        result *= resultSign;

        return result;
    }
    private static long[] divisionStep(long dividend, long divisor) {
        long scale = 0;
        long result;
        
        while (dividend < divisor) {
            // i.e. 4 / 6
            dividend *= 10;
            scale++;
        }
        result = dividend / divisor;

        return new long[] {result, scale};
    }
    private static long pow10(long exponent) {
        long result = 1;

        if (exponent < 0) {
            throw new IllegalArgumentException("exponent value: " + exponent
                + " is < 0");
        }
        for (int i = 0; i < exponent; i++) {
            result *= 10;
        }

        return result;
    }
    private static long log10(long input) {
        // returns Math.floor(Math.log10(input)), assuming input is >= 1
        long log = 1;
        long num = 0;

        if (input <= 0) {
            throw new IllegalArgumentException("input value: " + input
                + " <= 0");
        }
        while (log <= input) {
            log *= 10;
            num++;
        }
        
        return num - 1;
    }
    public static int parseInt(String input) throws IllegalArgumentException,
        IllegalStateException {
        Number result;

        result = parseNumber(input);

        return result.intValue();
    }
    public static Number parseNumber(String input)
        throws IllegalArgumentException, IllegalStateException {
        checkObject("input", input);
        try {
            return NumberFormat.getInstance().parse(input);
        } catch (ParseException exception) {
            throw new IllegalStateException("Attempting to parse: \"" + input
                + "\" threw a ParseException");
        }
    }
    /**
     * Checks a provided String[] to see if it is null, contains null elements,
     *     or elements that are "".
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input a String[] which cannot be null, contain null elements, or
     *     elements that are "".
     * @throws IllegalArgumentException if propertyName or input are null,
     *     propertyName is "", or input contains null elements or elements that
     *     are "".
     */
    public static void checkStringArray(String propertyName, String[] input) {
        checkObjectArray(propertyName, input);
        for (String string : input) {
            if (string.equals("")) {
                throw new IllegalArgumentException(propertyName + " array"
                    + " contains an element that is \"\"");
            }
        }
    }
    /**
     * Checks a provided Object[] to see if it is null or contains null
     *     elements.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input an Object[] which cannot be null or contain null elements.
     * @throws IllegalArgumentException if propertyName or input are null,
     *     propertyName is "", or input contains null elements.
     */
    public static void checkObjectArray(String propertyName, Object[] input) {
        checkString("propertyName", propertyName);
        checkObject(propertyName + " value", input);
        for (Object object : input) {
            if (object == null) {
                throw new IllegalArgumentException(propertyName + " array"
                    + " contains a null element");
            }
        }
    }
    /**
     * Checks a provided Object to see if it is null.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input an Object which cannot be null.
     * @throws IllegalArgumentException if propertyName or input are null or
     *     propertyName is "".
     */
    public static void checkObject(String propertyName, Object input) {
        checkString("propertyName", propertyName);
        if (input == null) {
            throw new IllegalArgumentException(propertyName + " is null");
        }
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
     * Checks a provided HTMLString to see if it is null or represents the
     *     String "".
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input an HTMLString which cannot represent the String "". Cannot
     *     be null.
     * @throws IllegalArgumentException if propertyName or input are null,
     *     propertyName is "", or input represents the String "".
     */
    public static void checkHTMLString(String propertyName, HTMLString input) {
        checkObject(propertyName, input);
        checkString(propertyName, input.getRawValue());
    }
    /**
     * Checks a provided VueHTMLString to see if it is null or represents the
     *     String "".
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input a VueHTMLString which cannot represent the String "". Cannot
     *     be null.
     * @throws IllegalArgumentException if propertyName or input are null,
     *     propertyName is "", or input represents the String "".
     */
    public static void checkVueHTMLString(String propertyName,
        VueHTMLString input) {
        checkObject(propertyName, input);
        checkString(propertyName, input.getRawValue());
    }
    /**
     * An alternate check to perform on an object array compared to
     *     checkStringArray() that instead checks if the provided String[] is of
     *     length 0, contains null elements, or elements that are "". Null
     *     values for input are allowed.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input a String[] which cannot be of length 0, contain null
     *     elements, or elements that are "".
     * @throws IllegalArgumentException if propertyName is null or "", input is
     *     of length 0, or input contains null elements or elements that are "".
     */
    public static void checkStringArrayAlt(String propertyName, String[] input)
    {
        if (input != null) {
            if (input.length == 0) {
                throw new IllegalArgumentException(propertyName + " array is of"
                    + " length 0");
            }
            checkStringArray(propertyName, input);
        }
    }
    /**
     * An alternate check to perform on an object array compared to
     *     checkObjectArray() that instead checks if the provided Object[] is of
     *     length 0 or contains null elements. Null values for input are
     *     allowed.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input an Object[] which cannot be of length 0 or contain null
     *     elements.
     * @throws IllegalArgumentException if propertyName is null or "", or input
     *     is of length 0 or contains null elements.
     */
    public static void checkObjectArrayAlt(String propertyName, Object[] input)
    {
        checkString("propertyName", propertyName);
        if (input != null) {
            if (input.length == 0) {
                throw new IllegalArgumentException(propertyName + " array is of"
                    + " length 0");
            }
            checkObjectArray(propertyName, input);
        }
    }
    /**
     * Checks to make sure the current code is being called while Database is
     *     open. Throws an Exception otherwise. Used in several constructors.
     * @throws IllegalStateException if Database.isOpen() returns false.
     */
    public static void verifyConstructor() {
        if (! Database.isOpen()) {
            throw new IllegalStateException("Cannot call this constructor"
                + " while Database is closed");
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
        checkObject("intArray", intArray);
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
    @SuppressWarnings("unchecked")
    /**
     * Appends the given Type element to the end of an existing Type[].
     * @param array a Type[] that cannot be null.
     * @param newElement a Type that cannot be null to append to the end of
     *     array.
     * @return a Type[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static <Type> Type[] append(Type[] array, Type newElement) {
        Type[] newArray;

        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        newArray = (Type[]) new Object[array.length + 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < array.length) {
                newArray[i] = array[i];
                continue;
            }
            newArray[i] = newElement;
        }
        newArray = copyOf(newArray);
        
        return newArray;
    }
    @SuppressWarnings("unchecked")
    /**
     * Adds the given Type element to an existing Type[] at some specified
     *     index.
     * @param array a Type[] that cannot be null.
     * @param newElement a Type that cannot be null to add to array.
     * @param index an int containing the index at which to insert newElement.
     * @return a Type[] consisting of array with newElement added to it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static <Type> Type[] add(Type[] array, Type newElement, int index) {
        Type[] newArray;

        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        newArray = (Type[]) new Object[array.length + 1];
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("index value: " + index + " is"
                + " out of range for an array of length: " + newArray.length);
        }
        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = array[i];
                continue;
            }
            if (i == index) {
                newArray[i] = newElement;
                continue;
            }
            newArray[i] = array[i - 1];
        }
        newArray = copyOf(newArray);

        return newArray;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a BigDecimal that cannot be null.
     * @return a BigDecimal deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static BigDecimal copyOf(BigDecimal original) {
        checkObject("original", original);

        return new BigDecimal(original.unscaledValue(), original.scale());
    }
    /**
     * Returns a deep copy of original.
     * @param original a Boolean that cannot be null.
     * @return a Boolean deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Boolean copyOf(Boolean original) {
        checkObject("original", original);

        return Boolean.valueOf(original);
    }
    /**
     * Returns a deep copy of original.
     * @param original an Integer that cannot be null.
     * @return an Integer deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Integer copyOf(Integer original) {
        checkObject("original", original);

        return Integer.valueOf(original);
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Callable that cannot be null.
     * @return a Callable deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Callable copyOf(Callable original) {
        Callable copy;

        checkObject("original", original);
        copy = new Callable() {
            @Override
            public void run() {
                original.run();
            }
        };

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * Specific to the JSON package used. Update if that package is changed or
     *     swapped out for a different one.
     * @param original a JSONObject that cannot be null.
     * @return a JSONObject deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static JSONObject copyOf(JSONObject original) {
        checkObject("original", original);

        return new JSONObject(original.toString());
    }
    /**
     * Returns a deepest copy of original.
     * Specific to the JSON package used. Update if that package is changed or
     *     swapped out for a different one.
     * @param original a JSONArray that cannot be null.
     * @return a JSONArray deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static JSONArray copyOf(JSONArray original) {
        checkObject("original", original);

        return new JSONArray(original.toString());
    }
    /**
     * Returns a deep copy of original.
     * @param original a boolean[] that cannot be null.
     * @return a boolean[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static boolean[] copyOf(boolean[] original) {
        checkObject("original", original);
        boolean[] copy = new boolean[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deep copy of original.
     * @param original an int[] that cannot be null.
     * @return an int[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static int[] copyOf(int[] original) {
        checkObject("original", original);
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    @SuppressWarnings("unchecked")
    /**
     * Returns a deep copy of original.
     * @param original a Type[] that cannot be null.
     * @return a Type[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static <Type> Type[] copyOf(Type[] original) {
        Type[] copy;

        checkObject("original", original);
        copy = (Type[]) new Object[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an Action[] that cannot be null.
     * @return an Action[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Action[] copyOf(Action[] original) {
        checkObject("original", original);
        Action[] copy = new Action[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Action(original[i]);
        }

        return copy;
    }
    // TODO: check if this is needed, delete if not
    /**
     * Returns a deepest copy of original.
     * @param original a Condition[] that cannot be null.
     * @return a Condition[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Condition[] copyOf(Condition[] original) {
        checkObject("original", original);
        Condition[] copy = new Condition[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Condition(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Deployable[] that cannot be null.
     * @return a Deployable[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Deployable[] copyOf(Deployable[] original) {
        checkObject("original", original);
        Deployable[] copy = new Deployable[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Deployable(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an LCPDependency[] that cannot be null.
     * @return an LCPDependency[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static LCPDependency[] copyOf(LCPDependency[] original) {
        checkObject("original", original);
        LCPDependency[] copy = new LCPDependency[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new LCPDependency(original[i]);
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
        checkObject("original", original);
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
     * @param original a Mount[] that cannot be null.
     * @return a Mount[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Mount[] copyOf(Mount[] original) {
        checkObject("original", original);
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
     * @param original a RangeTag[] that cannot be null.
     * @return a RangeTag[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static RangeTag[] copyOf(RangeTag[] original) {
        checkObject("original", original);
        RangeTag[] copy = new RangeTag[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new RangeTag(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a SkillData[] that cannot be null.
     * @return a SkillData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static SkillData[] copyOf(SkillData[] original) {
        checkObject("original", original);
        SkillData[] copy = new SkillData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new SkillData(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a State[] that cannot be null.
     * @return a State[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static State[] copyOf(State[] original) {
        checkObject("original", original);
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
    public static Status[] copyOf(Status[] original) {
        checkObject("original", original);
        Status[] copy = new Status[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Status(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a URL[] that cannot be null.
     * @return a URL[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static URL[] copyOf(URL[] original) {
        String url;
        URL[] copy;

        checkObject("original", original);
        copy = new URL[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            url = original[i].toExternalForm();
            copy[i] = FileOperations.toURLCaught(url);
        }

        return copy;
    }
    public static String[] arraySlice(String[] array, int startIndex,
        int endIndex) {
        int length;
        String[] result;

        HelperMethods.checkObject("array", array);
        checkIndex(startIndex, array.length);
        checkIndex(endIndex, array.length);
        if (endIndex < startIndex) {
            throw new IllegalArgumentException("startIndex: " + startIndex
                + " is < endIndex: " + endIndex);
        }
        length = endIndex - startIndex;
        result = new String[length];
        for (int i = startIndex; i < endIndex; i++) {
            result[i] = array[i];
        }

        return result;
    }
    public static String[] arraySlice(String[] array, int startIndex) {
        HelperMethods.checkObject("array", array);

        return arraySlice(array, startIndex, array.length - 1);
    }
    private static void checkIndex(int index, int arrayLength) throws
        IllegalArgumentException {
        if (index < 0 || index >= arrayLength) {
            throw new IllegalArgumentException("Index: " + index + " is out of"
                + " bounds for an array of length " + arrayLength);
        }
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

        checkObject("input", input);
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
        String outputString;

        checkObject("input", input);
        if (input.length() <= 1) {
            return input.toUpperCase();
        }
        outputString = input.substring(0, 1);
        outputString = outputString.toUpperCase();
        outputString += input.substring(1);

        return outputString;
    }
    public static void warn(String warningText) {
        System.out.println("[ WARNING ]: " + warningText);
    }
    public static void alert(String alertText) {
        System.out.println("[ ALERT ]: " + alertText);
    }
    @SuppressWarnings("unchecked")
    public static <
        UnvType extends UnverifiedData<UnvType, VerType>,
        VerType
    > VerType[] verifyArray(UnvType[] input) {
        ArrayList<VerType> result;

        checkObjectArray("input", input);
        result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            result.add(verify(input[i]));
        }

        // let's see how well this works
        return result.toArray((VerType[]) new Object[input.length]);
    }
    public static <
        UnvType extends UnverifiedData<UnvType, VerType>,
        VerType
    > VerType verify(UnvType input) {
        checkObject("input", input);

        return input.verify();
    }
}
