package MainBranch;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import MainBranch.database.FileOperations;
import MainBranch.database.LCPCorrection;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.JSONTypeTree;
import Packages.CoreTypes.LCPInfo;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.RangeType;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.Tag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.dataTag.ITagData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.dataTag.iTagData.ITagDataUnhidden;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.SystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.MechSystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.WeaponType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.weapon.IWeaponProfile;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount.MountType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Talent;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.UnverifiedCoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.Background;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.UnverifiedBackground;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond.BondPower;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond.BondQuestion;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.talentData.TalentRank;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus.CoreBonus;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.frequency.FrequencyType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserve.ReserveData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserve.reserveData.ReserveType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.Skill;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.HarmType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.FrameLicense;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;
import Packages.CoreTypes.counterBase.CounterData;
import Packages.CoreTypes.lcpInfo.LCPDependency;
import Packages.EventSystem.event.EventListener;

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
     * Checks a provided String to see if it is "". Null values for input are
     *     allowed.
     * @param propertyName a String which cannot be "". Cannot be null.
     * @param input a String which cannot be "". Can be null.
     * @throws IllegalArgumentException if propertyName or input are "" or
     *     propertyName is null.
     */
    public static void checkStringAlt(String propertyName, String input) {
        HelperMethods.checkString("propertyName", propertyName);
        if (input != null) {
            HelperMethods.checkString(propertyName, input);
        }
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
     * Appends the given Action element to the end of an existing Action[].
     * @param array an Action[] that cannot be null.
     * @param newElement an Action that cannot be null to append to the end of
     *     array.
     * @return an Action[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Action[] append(Action[] array, Action newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Action[] newArray = new Action[array.length + 1];

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
     * Appends the given ActivationType element to the end of an existing
     *     ActivationType[].
     * @param array an ActivationType[] that cannot be null.
     * @param newElement an ActivationType that cannot be null to append to the
     *     end of array.
     * @return an ActivationType[] consisting of array with newElement appended
     *     to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static ActivationType[] append(ActivationType[] array,
        ActivationType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        ActivationType[] newArray = new ActivationType[array.length + 1];

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
     * Appends the given Background element to the end of an existing
     *     Background[].
     * @param array a Background[] that cannot be null.
     * @param newElement a Background that cannot be null to append to the end
     *     of array.
     * @return a Background[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Background[] append(Background[] array, Background newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Background[] newArray = new Background[array.length + 1];

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
     * Appends the given Bond element to the end of an existing Bond[].
     * @param array a Bond[] that cannot be null.
     * @param newElement a Bond that cannot be null to append to the end of
     *     array.
     * @return a Bond[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Bond[] append(Bond[] array, Bond newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Bond[] newArray = new Bond[array.length + 1];

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
     * Appends the given CoreBonus element to the end of an existing
     *     CoreBonus[].
     * @param array a CoreBonus[] that cannot be null.
     * @param newElement a CoreBonus that cannot be null to append to the end of
     *     array.
     * @return a CoreBonus[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static CoreBonus[] append(CoreBonus[] array, CoreBonus newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        CoreBonus[] newArray = new CoreBonus[array.length + 1];

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
     * Appends the given DamageType element to the end of an existing
     *     DamageType[].
     * @param array a DamageType[] that cannot be null.
     * @param newElement a DamageType that cannot be null to append to the end
     *     of array.
     * @return a DamageType[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static DamageType[] append(DamageType[] array, DamageType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        DamageType[] newArray = new DamageType[array.length + 1];

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
     * Appends the given DataTag element to the end of an existing DataTag[].
     * @param array a DataTag[] that cannot be null.
     * @param newElement a DataTag that cannot be null to append to the end of
     *     array.
     * @return a DataTag[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static DataTag[] append(DataTag[] array, DataTag newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        DataTag[] newArray = new DataTag[array.length + 1];

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
     * Appends the given Environment element to the end of an existing
     *     Environment[].
     * @param array an Environment[] that cannot be null.
     * @param newElement an Environment that cannot be null to append to the end
     *     of array.
     * @return an Environment[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Environment[] append(Environment[] array,
        Environment newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Environment[] newArray = new Environment[array.length + 1];

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
     * Appends the given FrequencyType element to the end of an existing
     *     FrequencyType[].
     * @param array a FrequencyType[] that cannot be null.
     * @param newElement a FrequencyType that cannot be null to append to the
     *     end of array.
     * @return a FrequencyType[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static FrequencyType[] append(FrequencyType[] array,
        FrequencyType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        FrequencyType[] newArray = new FrequencyType[array.length + 1];

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
     * Appends the given HarmType element to the end of an existing HarmType[].
     * @param array a HarmType[] that cannot be null.
     * @param newElement a HarmType that cannot be null to append to the end of
     *     array.
     * @return a HarmType[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static HarmType[] append(HarmType[] array, HarmType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        HarmType[] newArray = new HarmType[array.length + 1];

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
     * Appends the given IActionData element to the end of an existing
     *     IActionData[].
     * @param array an IActionData[] that cannot be null.
     * @param newElement an IActionData that cannot be null to append to the end
     *     of array.
     * @return an IActionData[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static IActionData[] append(IActionData[] array,
        IActionData newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        IActionData[] newArray = new IActionData[array.length + 1];

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
     * Appends the given ITagData element to the end of an existing ITagData[].
     * @param array an ITagData[] that cannot be null.
     * @param newElement an ITagData that cannot be null to append to the end of
     *     array.
     * @return an ITagData[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static ITagData[] append(ITagData[] array, ITagData newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        ITagData[] newArray = new ITagData[array.length + 1];

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
     * Appends the given ITagDataUnhidden element to the end of an existing
     *     ITagDataUnhidden[].
     * @param array an ITagDataUnhidden[] that cannot be null.
     * @param newElement an ITagDataUnhidden that cannot be null to append to
     *     the end of array.
     * @return an ITagDataUnhidden[] consisting of array with newElement
     *     appended to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static ITagDataUnhidden[] append(ITagDataUnhidden[] array,
        ITagDataUnhidden newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        ITagDataUnhidden[] newArray = new ITagDataUnhidden[array.length + 1];

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
     * Appends the given FrameLicense element to the end of an existing
     *     FrameLicense[].
     * @param array a FrameLicense[] that cannot be null.
     * @param newElement a FrameLicense that cannot be null to append to the end
     *     of array.
     * @return a FrameLicense[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static FrameLicense[] append(FrameLicense[] array,
        FrameLicense newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        FrameLicense[] newArray = new FrameLicense[array.length + 1];

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
     * Appends the given LCPInfo element to the end of an existing LCPInfo[].
     * @param array an LCPInfo[] that cannot be null.
     * @param newElement an LCPInfo that cannot be null to append to the end of
     *     array.
     * @return an LCPInfo[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static LCPInfo[] append(LCPInfo[] array, LCPInfo newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        LCPInfo[] newArray = new LCPInfo[array.length + 1];

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
     * Appends the given LCPCorrection element to the end of an existing
     *     LCPCorrection[].
     * @param array an LCPCorrection[] that cannot be null.
     * @param newElement an LCPCorrection that cannot be null to append to the
     *     end of array.
     * @return an LCPCorrection[] consisting of array with newElement appended
     *     to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static LCPCorrection[] append(LCPCorrection[] array,
        LCPCorrection newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        LCPCorrection[] newArray = new LCPCorrection[array.length + 1];

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
     * Appends the given Manufacturer element to the end of an existing
     *     Manufacturer[].
     * @param array a Manufacturer[] that cannot be null.
     * @param newElement a Manufacturer that cannot be null to append to the end
     *     of array.
     * @return a Manufacturer[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Manufacturer[] append(Manufacturer[] array,
        Manufacturer newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Manufacturer[] newArray = new Manufacturer[array.length + 1];

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
     * Appends the given MountType element to the end of an existing
     *     MountType[].
     * @param array a MountType[] that cannot be null.
     * @param newElement a MountType that cannot be null to append to the end of
     *     array.
     * @return a MountType[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static MountType[] append(MountType[] array, MountType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        MountType[] newArray = new MountType[array.length + 1];

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
     * Appends the given NPCFeature element to the end of an existing
     *     NPCFeature[].
     * @param array an NPCFeature[] that cannot be null.
     * @param newElement an NPCFeature that cannot be null to append to the end
     *     of array.
     * @return an NPCFeature[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static NPCFeature[] append(NPCFeature[] array, NPCFeature newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        NPCFeature[] newArray = new NPCFeature[array.length + 1];

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
     * Appends the given NPCTemplate element to the end of an existing
     *     NPCTemplate[].
     * @param array an NPCTemplate[] that cannot be null.
     * @param newElement an NPCTemplate that cannot be null to append to the end
     *     of array.
     * @return an NPCTemplate[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static NPCTemplate[] append(NPCTemplate[] array,
        NPCTemplate newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        NPCTemplate[] newArray = new NPCTemplate[array.length + 1];

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
     * Appends the given PilotArmor element to the end of an existing
     *     PilotArmor[].
     * @param array a PilotArmor[] that cannot be null.
     * @param newElement a PilotArmor that cannot be null to append to the end of
     *     array.
     * @return a PilotArmor[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static PilotArmor[] append(PilotArmor[] array, PilotArmor newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        PilotArmor[] newArray = new PilotArmor[array.length + 1];

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
     * Appends the given PilotGear element to the end of an existing
     *     PilotGear[].
     * @param array a PilotGear[] that cannot be null.
     * @param newElement a PilotGear that cannot be null to append to the end of
     *     array.
     * @return a PilotGear[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static PilotGear[] append(PilotGear[] array, PilotGear newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        PilotGear[] newArray = new PilotGear[array.length + 1];

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
     * Appends the given PilotWeapon element to the end of an existing
     *     PilotWeapon[].
     * @param array a PilotWeapon[] that cannot be null.
     * @param newElement a PilotWeapon that cannot be null to append to the end
     *     of array.
     * @return a PilotWeapon[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static PilotWeapon[] append(PilotWeapon[] array,
        PilotWeapon newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        PilotWeapon[] newArray = new PilotWeapon[array.length + 1];

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
     * Appends the given RangeType element to the end of an existing
     *     RangeType[].
     * @param array a RangeType[] that cannot be null.
     * @param newElement a RangeType that cannot be null to append to the end of
     *     array.
     * @return a RangeType[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static RangeType[] append(RangeType[] array, RangeType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        RangeType[] newArray = new RangeType[array.length + 1];

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
     * Appends the given ReserveData element to the end of an existing
     *     ReserveData[].
     * @param array a ReserveData[] that cannot be null.
     * @param newElement a ReserveData that cannot be null to append to the end
     *     of array.
     * @return a ReserveData[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static ReserveData[] append(ReserveData[] array, ReserveData newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        ReserveData[] newArray = new ReserveData[array.length + 1];

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
     * Appends the given ReserveType element to the end of an existing
     *     ReserveType[].
     * @param array a ReserveType[] that cannot be null.
     * @param newElement a ReserveType that cannot be null to append to the end
     *     of array.
     * @return a ReserveType[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static ReserveType[] append(ReserveType[] array,
        ReserveType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        ReserveType[] newArray = new ReserveType[array.length + 1];

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
     * Appends the given Rule element to the end of an existing Rule[].
     * @param array a Rule[] that cannot be null.
     * @param newElement a Rule that cannot be null to append to the end of
     *     array.
     * @return a Rule[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Rule[] append(Rule[] array, Rule newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Rule[] newArray = new Rule[array.length + 1];

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
     * Appends the given SkillData element to the end of an existing Skill[].
     * @param array a SkillData[] that cannot be null.
     * @param newElement a SkillData that cannot be null to append to the end of
     *     array.
     * @return a SkillData[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static SkillData[] append(SkillData[] array, SkillData newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        SkillData[] newArray = new SkillData[array.length + 1];

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
     * Appends the given Sitrep element to the end of an existing Sitrep[].
     * @param array a Sitrep[] that cannot be null.
     * @param newElement a Sitrep that cannot be null to append to the end of
     *     array.
     * @return a Sitrep[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Sitrep[] append(Sitrep[] array, Sitrep newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Sitrep[] newArray = new Sitrep[array.length + 1];

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
     * Appends the given StateData element to the end of an existing
     *     StateData[].
     * @param array a StateData[] that cannot be null.
     * @param newElement a StateData that cannot be null to append to the end of
     *     array.
     * @return a StateData[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static StateData[] append(StateData[] array, StateData newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        StateData[] newArray = new StateData[array.length + 1];

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
     * Appends the given Status element to the end of an existing Status[].
     * @param array a Status[] that cannot be null.
     * @param newElement a Status that cannot be null to append to the end of
     *     array.
     * @return a Status[] consisting of array with newElement appended to the
     *     end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Status[] append(Status[] array, Status newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Status[] newArray = new Status[array.length + 1];

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
     * Appends the given SynergyLocation element to the end of an existing
     *     SynergyLocation[].
     * @param array a SynergyLocation[] that cannot be null.
     * @param newElement a SynergyLocation that cannot be null to append to the
     *     end of array.
     * @return a SynergyLocation[] consisting of array with newElement appended
     *     to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static SynergyLocation[] append(SynergyLocation[] array,
        SynergyLocation newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        SynergyLocation[] newArray = new SynergyLocation[array.length + 1];

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
     * Appends the given MechSystemType element to the end of an existing
     *     MechSystemType[].
     * @param array a MechSystemType[] that cannot be null.
     * @param newElement a MechSystemType that cannot be null to append to the
     *     end of array.
     * @return a MechSystemType[] consisting of array with newElement appended
     *     to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static MechSystemType[] append(MechSystemType[] array,
        MechSystemType newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        MechSystemType[] newArray = new MechSystemType[array.length + 1];

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
     * Appends the given SystemType element to the end of an existing
     *     SystemType[].
     * @param array a SystemType[] that cannot be null.
     * @param newElement a SystemType that cannot be null to append to the end
     *     of array.
     * @return a SystemType[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static SystemType[] append(SystemType[] array, SystemType newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        SystemType[] newArray = new SystemType[array.length + 1];

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
     * Appends the given Table element to the end of an existing Table[].
     * @param array a Table[] that cannot be null.
     * @param newElement a Table that cannot be null to append to the end of
     *     array.
     * @return a Table[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Table[] append(Table[] array, Table newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Table[] newArray = new Table[array.length + 1];

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
     * Appends the given Tag element to the end of an existing Tag[].
     * @param array a Tag[] that cannot be null.
     * @param newElement a Tag that cannot be null to append to the end of
     *     array.
     * @return a Tag[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Tag[] append(Tag[] array, Tag newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Tag[] newArray = new Tag[array.length + 1];

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
     * Appends the given Talent element to the end of an existing Talent[].
     * @param array a Talent[] that cannot be null.
     * @param newElement a Talent that cannot be null to append to the end of
     *     array.
     * @return a Talent[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Talent[] append(Talent[] array, Talent newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Talent[] newArray = new Talent[array.length + 1];

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
     * Appends the given TalentData element to the end of an existing
     *     TalentData[].
     * @param array a TalentData[] that cannot be null.
     * @param newElement a TalentData that cannot be null to append to the end
     *     of array.
     * @return a TalentData[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static TalentData[] append(TalentData[] array, TalentData newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        TalentData[] newArray = new TalentData[array.length + 1];

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
     * Appends the given Term element to the end of an existing Term[].
     * @param array a Term[] that cannot be null.
     * @param newElement a Term that cannot be null to append to the end of
     *     array.
     * @return a Term[] consisting of array with newElement appended to the end
     *     of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static Term[] append(Term[] array, Term newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        Term[] newArray = new Term[array.length + 1];

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
     * Appends the given UnverifiedBackground element to the end of an existing
     *     UnverifiedBackground[].
     * @param array an UnverifiedBackground[] that cannot be null.
     * @param newElement an UnverifiedBackground that cannot be null to append
     *     to the end of array.
     * @return an UnverifiedBackground[] consisting of array with newElement
     *     appended to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static UnverifiedBackground[] append(UnverifiedBackground[] array,
        UnverifiedBackground newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        UnverifiedBackground[] newArray =
            new UnverifiedBackground[array.length + 1];

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
     * Appends the given UnverifiedCoreBonus element to the end of an existing
     *     UnverifiedCoreBonus[].
     * @param array an UnverifiedCoreBonus[] that cannot be null.
     * @param newElement an UnverifiedCoreBonus that cannot be null to append to
     *     the end of array.
     * @return an UnverifiedCoreBonus[] consisting of array with newElement
     *     appended to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static UnverifiedCoreBonus[] append(UnverifiedCoreBonus[] array,
        UnverifiedCoreBonus newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        UnverifiedCoreBonus[] newArray =
            new UnverifiedCoreBonus[array.length + 1];

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
     * Appends the given UnverifiedDataTag element to the end of an existing
     *     UnverifiedDataTag[].
     * @param array an UnverifiedDataTag[] that cannot be null.
     * @param newElement an UnverifiedDataTag that cannot be null to append to
     *     the end of array.
     * @return an UnverifiedDataTag[] consisting of array with newElement
     *     appended to the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static UnverifiedDataTag[] append(UnverifiedDataTag[] array,
        UnverifiedDataTag newElement) {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        UnverifiedDataTag[] newArray =
            new UnverifiedDataTag[array.length + 1];

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
    /**
     * Appends the given WeaponSize element to the end of an existing
     *     WeaponSize[].
     * @param array a WeaponSize[] that cannot be null.
     * @param newElement a WeaponSize that cannot be null to append to the end
     *     of array.
     * @return a WeaponSize[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static WeaponSize[] append(WeaponSize[] array, WeaponSize newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        WeaponSize[] newArray = new WeaponSize[array.length + 1];

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
     * Appends the given WeaponType element to the end of an existing
     *     WeaponType[].
     * @param array a WeaponType[] that cannot be null.
     * @param newElement a WeaponType that cannot be null to append to the end
     *     of array.
     * @return a WeaponType[] consisting of array with newElement appended to
     *     the end of it.
     * @throws IllegalArgumentException if array or newElement is null.
     */
    public static WeaponType[] append(WeaponType[] array, WeaponType newElement)
    {
        HelperMethods.checkObject("array", array);
        HelperMethods.checkObject("newElement", newElement);
        WeaponType[] newArray = new WeaponType[array.length + 1];

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
     * @param original a BondPower[] that cannot be null.
     * @return a BondPower[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static BondPower[] copyOf(BondPower[] original) {
        checkObject("original", original);
        BondPower[] copy = new BondPower[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a BondQuestion[] that cannot be null.
     * @return a BondQuestion[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static BondQuestion[] copyOf(BondQuestion[] original) {
        checkObject("original", original);
        BondQuestion[] copy = new BondQuestion[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Counter[] that cannot be null.
     * @return a Counter[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static CounterData[] copyOf(CounterData[] original) {
        checkObject("original", original);
        CounterData[] copy = new CounterData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a DamageType[] that cannot be null.
     * @return a DamageType[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static DamageType[] copyOf(DamageType[] original) {
        checkObject("original", original);
        DamageType[] copy = new DamageType[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
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
     * @param original an IActionData[] that cannot be null.
     * @return an IActionData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static IActionData[] copyOf(IActionData[] original) {
        checkObject("original", original);
        IActionData[] copy = new IActionData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an IDeployableData[] that cannot be null.
     * @return an IDeployableData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static IDeployableData[] copyOf(IDeployableData[] original) {
        checkObject("original", original);
        IDeployableData[] copy = new IDeployableData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an ITagData[] that cannot be null.
     * @return an ITagData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static ITagData[] copyOf(ITagData[] original) {
        checkObject("original", original);
        ITagData[] copy = new ITagData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an IWeaponProfile[] that cannot be null.
     * @return an IWeaponProfile[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static IWeaponProfile[] copyOf(IWeaponProfile[] original) {
        checkObject("original", original);
        IWeaponProfile[] copy = new IWeaponProfile[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a JSONTypeTree[] that cannot be null.
     * @return a JSONTypeTree[] deep copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static JSONTypeTree[] copyOf(JSONTypeTree[] original) {
        checkObject("original", original);
        JSONTypeTree[] copy = new JSONTypeTree[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
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
            copy[i] = original[i];
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
            copy[i] = original[i];
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
     * @param original a RangeType[] that cannot be null.
     * @return a RangeType[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static RangeType[] copyOf(RangeType[] original) {
        checkObject("original", original);
        RangeType[] copy = new RangeType[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
     * @param original a Skill[] that cannot be null.
     * @return a Skill[] deepest copy of original.
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a Synergy[] that cannot be null.
     * @return a Synergy[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static ISynergyData[] copyOf(ISynergyData[] original) {
        checkObject("original", original);
        ISynergyData[] copy = new ISynergyData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a SynergyLocation[] that cannot be null.
     * @return a SynergyLocation[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static SynergyLocation[] copyOf(SynergyLocation[] original) {
        checkObject("original", original);
        SynergyLocation[] copy = new SynergyLocation[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
    /**
     * Returns a deepest copy of original.
     * @param original a StateData[] that cannot be null.
     * @return a StateData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static StateData[] copyOf(StateData[] original) {
        checkObject("original", original);
        StateData[] copy = new StateData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a TalentRank[] that cannot be null.
     * @return a TalentRank[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static TalentRank[] copyOf(TalentRank[] original) {
        checkObject("original", original);
        TalentRank[] copy = new TalentRank[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an UnverifiedDataTag[] that cannot be null.
     * @return an UnverifiedDataTag[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static UnverifiedDataTag[] copyOf(UnverifiedDataTag[] original) {
        checkObject("original", original);
        UnverifiedDataTag[] copy = new UnverifiedDataTag[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original an UnverifiedIDeployableData[] that cannot be null.
     * @return an UnverifiedIDeployableData[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static UnverifiedIDeployableData[] copyOf(
        UnverifiedIDeployableData[] original) {
        checkObject("original", original);
        UnverifiedIDeployableData[] copy = 
            new UnverifiedIDeployableData[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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
    /**
     * Returns a deepest copy of original.
     * @param original a WeaponType[] that cannot be null.
     * @return a WeaponType[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static WeaponType[] copyOf(WeaponType[] original) {
        checkObject("original", original);
        WeaponType[] copy = new WeaponType[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
        }

        return copy;
    }
    /**
     * Returns a deepest copy of original.
     * @param original a WeaponSize[] that cannot be null.
     * @return a WeaponSize[] deepest copy of original.
     * @throws IllegalArgumentException if original is null.
     */
    public static WeaponSize[] copyOf(WeaponSize[] original) {
        checkObject("original", original);
        WeaponSize[] copy = new WeaponSize[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] == null) {
                copy[i] = original[i];
                continue;
            }
            copy[i] = original[i];
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