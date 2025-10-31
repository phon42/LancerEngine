package main;

import packages.coreTypes.Callable;
import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.Bonus;
import packages.coreTypes.entityMechanics.License;
import packages.coreTypes.entityMechanics.RangeTag;
import packages.coreTypes.entityMechanics.entityTypes.Deployable;
import packages.coreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.coreTypes.entityMechanics.entityTypes.mech.Mount;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Weapon;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotGear;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;
import packages.coreTypes.entityMechanics.harmSystem.Damage;
import packages.coreTypes.entityMechanics.harmSystem.Harm;
import packages.coreTypes.entityMechanics.stateSystem.State;
import packages.coreTypes.entityMechanics.stateSystem.state.Condition;
import packages.coreTypes.entityMechanics.stateSystem.state.Status;
import packages.eventSystem.event.EventListener;

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

        if (divisor == 0) {
            throw new IllegalArgumentException("divisor is 0. Cannot compute"
                + " because x / 0 is undefined according to the laws of math."
                + " Also it would throw an ArithmeticException");
        }
        if (dividend == 0) {
            return 0;
        }
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
    /**
     * Appends the given String element to the end of an existing String[].
     * @param array a String[] that cannot be null.
     * @param newElement a String that cannot be null to append to the end of
     *     array.
     * @return a String[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static String[] append(String[] array, String newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        String[] newArray = new String[array.length + 1];

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
     * Appends the given Condition element to the end of an existing
     *     Condition[].
     * @param array a Condition[] that cannot be null.
     * @param newElement a Condition that cannot be null to append to the end of
     *     array.
     * @return a Condition[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Condition[] append(Condition[] array, Condition newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Condition[] newArray = new Condition[array.length + 1];

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
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
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
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
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
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
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
     * @param array a State[] that cannot be null.
     * @param newElement a State that cannot be null to append to the end of
     *     array.
     * @return a State[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static State[] append(State[] array, State newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        State[] newArray = new State[array.length + 1];

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
     * Appends the given Weapon element to the end of an existing Weapon[].
     * @param array a Weapon[] that cannot be null.
     * @param newElement a Weapon that cannot be null to append to the end of
     *     array.
     * @return a Weapon[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Weapon[] append(Weapon[] array, Weapon newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
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
     * @param array a Mount[] that cannot be null.
     * @param newElement a Mount that cannot be null to add to array.
     * @param index an int containing the index at which to insert newElement.
     * @return a Mount[] consisting of array with newElement added to it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Mount[] add(Mount[] array, Mount newElement, int index) {
        Mount[] newArray;

        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        newArray = new Mount[array.length + 1];
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

        return newArray;
    }
    /**
     * Returns a deep copy of original.
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
    /**
     * Returns a deep copy of original.
     * @param original a String[] that cannot be null.
     * @return a String[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static String[] copyOf(String[] original) {
        checkObject("original", original);
        String[] copy = new String[original.length];
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
    /**
     * Returns a deepest copy of original.
     * @param original a Bonus[] that cannot be null.
     * @return a Bonus[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Bonus[] copyOf(Bonus[] original) {
        checkObject("original", original);
        Bonus[] copy = new Bonus[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Bonus(original[i]);
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
     * @param original a Damage[] that cannot be null.
     * @return a Damage[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Damage[] copyOf(Damage[] original) {
        checkObject("original", original);
        Damage[] copy = new Damage[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Damage(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a DataTag[] that cannot be null.
     * @return a DataTag[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static DataTag[] copyOf(DataTag[] original) {
        checkObject("original", original);
        DataTag[] copy = new DataTag[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new DataTag(original[i]);
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
     * @param original a Harm[] that cannot be null.
     * @return a Harm[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Harm[] copyOf(Harm[] original) {
        checkObject("original", original);
        Harm[] copy = new Harm[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Harm(original[i]);
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
     * @param original a MechSystem[] that cannot be null.
     * @return a MechSystem[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static MechSystem[] copyOf(MechSystem[] original) {
        checkObject("original", original);
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
     * @param original a PilotGear[] that cannot be null.
     * @return a PilotGear[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static PilotGear[] copyOf(PilotGear[] original) {
        checkObject("original", original);
        PilotGear[] copy = new PilotGear[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new PilotGear(original[i]);
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a PilotWeapon[] that cannot be null.
     * @return a PilotWeapon[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static PilotWeapon[] copyOf(PilotWeapon[] original) {
        checkObject("original", original);
        PilotWeapon[] copy = new PilotWeapon[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new PilotWeapon(original[i]);
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
     * @param original a SkillTrigger[] that cannot be null.
     * @return a SkillTrigger[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Skill[] copyOf(Skill[] original) {
        checkObject("original", original);
        Skill[] copy = new Skill[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = new Skill(original[i]);
        }

        return copy;
    }
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
     * @param original a Talent[] that cannot be null.
     * @return a Talent[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static Talent[] copyOf(Talent[] original) {
        checkObject("original", original);
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
        checkObject("original", original);
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
        System.out.println(warningText);
    }
    public static void alert(String alertText) {
        System.out.println(alertText);
    }
}
