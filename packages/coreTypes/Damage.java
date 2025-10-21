package packages.coreTypes;

import main.HelperMethods;
import main.Roll;

/**
 * See pgs. 67 and 104.
 */
public class Damage {
    /**
     * The Damage's type (i.e. "kinetic").
     * Must be an allowed type as defined by Damage.allowedTypes. Cannot be
     *     null.
     */
    protected String type;
    /**
     * Contains an array of allowed damage types.
     * Case-insensitive and stored in lowercase.
     * See pgs. 67 and 104.
     */
    public static final String[] allowedTypes = new String[] {"kinetic",
        "explosive", "energy", "burn"};
    /**
     * The amount of dice damage dealt (i.e. "1d6", representing the "1d6" in
     *     "1d6+2").
     * Can be any String that contains a valid dice expression as defined by
     *     Roll.isValidExpression(). Can be "" as long as this.flatValue is not
     *     0. Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String diceValue;
    /**
     * The amount of flat damage dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of 1. Can be 0 as long as this.diceValue is a valid
     *     dice expression as defined by Roll.isValidExpression().
     */
    protected int flatValue;

    public Damage(String damageType, String damageDice, int damageFlatAmount) {
        setType(damageType);
        setDiceValue(damageDice);
        setFlatValue(damageFlatAmount);
        checkValidity();
    }
    public Damage(Damage damage) {
        setType(damage.type);
        setDiceValue(damage.diceValue);
        setFlatValue(damage.flatValue);
        checkValidity();
    }
    
    public String getType() {
        return type;
    }
    public String getDiceValue() {
        return diceValue;
    }
    public int getFlatValue() {
        return flatValue;
    }
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        // TODO: create methods like this for all the checkable final arrays
        if (! Damage.isValidType(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }
    /**
     * Warning: this mutator DOES NOT protect against this.diceValue being set
     *     to "" while this.flatValue is 0. It is assumed that this will be
     *     taken care of in Damage() by calling Damage.checkValidity()
     *     immediately after everything has been set.
     * @param diceValue a String which must be "" or a valid dice expression as
     *     defined by Roll.isValidExpression().
     */
    protected void setDiceValue(String diceValue) {
        boolean isBlank = false;
        boolean isValidExpression = false;

        if (diceValue == null) {
            throw new IllegalArgumentException("diceValue is null");
        }
        diceValue = diceValue.toLowerCase();
        isBlank = diceValue.equals("");
        isValidExpression = Roll.isValidExpression(diceValue);
        if ((! isBlank) && (! isValidExpression)) {
            // diceValue is neither "" nor a valid dice expression - not allowed
            throw new IllegalArgumentException("Cannot set diceValue to a value"
                + " of: \"" + diceValue + "\" (neither \"\" nor a valid dice"
                + " expression)");
        }
        this.diceValue = diceValue;
    }
    protected void setFlatValue(int flatValue) {
        if (flatValue < 0) {
            throw new IllegalArgumentException("flat value value: " + flatValue
                + " is < 0");
        }
        if (flatValue == 0) {
            // diceValue must be a valid dice expression
            if (! Roll.isValidExpression(this.diceValue)) {
                throw new IllegalArgumentException("Cannot set flatValue to a"
                    + " value of 0 while Damage.diceValue is: \""
                    + this.diceValue + "\" (an invalid dice expression)");
            }
        } else {
            // diceValue must be "" or a valid dice expression, which we have
            //     already taken care of through Damage.setDiceValue()'s filters
            // so no checking is necessary
        }
        this.flatValue = flatValue;
    }

    /**
     * Checks whether a provided String is a valid Damage.type value.
     * @param type a String that cannot be null.
     * @return a boolean containing the result of the check.
     */
    public static boolean isValidType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        for (int i = 0; i < Damage.allowedTypes.length; i++) {
            if (type.equals(Damage.allowedTypes[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values. Throws an IllegalArgumentException if not.
     * @throws IllegalArgumentException if this.diceValue and this.flatValue are
     *     set to an invalid combination of values.
     */
    private void checkValidity() {
        if (! isValid()) {
            throw new IllegalArgumentException("Cannot create a Damage object"
                + " with a Damage.diceValue value of: \"" + this.diceValue
                + "\" and a Damage.flatValue of: " + this.flatValue);
        }
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values.
     * @return a boolean containing the result of the check.
     */
    private boolean isValid() {
        boolean isValidExpression = false;

        isValidExpression = Roll.isValidExpression(this.diceValue);
        // this.diceValue must be a valid dice expression OR this.flatValue must
        //     not be 0 (in which case this.diceValue can be "").
        if (this.flatValue == 0) {
            // this.diceValue must be a valid dice expression
            if (! isValidExpression) {
                return false;
            }
        } else {
            // this.diceValue must be "" or a valid dice expression
            // any in-between cases are taken care of by Damage.setDiceValue
            // therefore we know that this.diceValue is EITHER "" or a valid
            //     dice expression
            // so no checking is necessary
        }
        return true;
    }
    /**
     * Evaluates the amount of damage listed to be dealt by this Damage object.
     * @return an int containing the result.
     */
    public int roll() {
        // damage is being rolled here
        return Roll.roll(this.diceValue) + Roll.roll(this.flatValue);
    }
}