package packages.coreTypes;

import main.HelperMethods;
import main.Roll;

/**
 * See pgs. 67 and 104.
 */
public class Harm extends Damage {
    // TODO: figure out a way to override the documentation from Damage
    /**
     * The Harm's type (i.e. "kinetic").
     * Must be an allowed type as defined by Harm.allowedTypes. Cannot be null.
     */
    // private String type;
    /**
     * Contains an array of allowed harm types.
     * Case-insensitive and stored in lowercase.
     * See pgs. 67 and 104.
     */
    public static final String[] allowedTypes = new String[] {"kinetic",
        "explosive", "energy", "variable", "burn", "heat"};
    // TODO: figure out a way to override the documentation from Damage
    /**
     * The amount of dice harm dealt (i.e. "1d6", representing the "1d6" in
     *     "1d6+2").
     * Can be any String that is "" or a valid dice expression as defined by
     *     Roll.isValidExpression(). Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    // private String diceValue;
    // TODO: figure out a way to override the documentation from Damage
    /**
     * The amount of flat harm dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of 1. Can be -1 if this.diceValue is "". Can be 0 if
     *     this.diceValue is a valid dice expression as defined by
     *     Roll.isValidExpression().
     */
    // private int flatValue;

    public Harm(String harmType, String harmDice, int harmFlatAmount) {
        super("kinetic", "", 1);
        setType(harmType);
        setDiceValue(harmDice);
        setFlatValue(harmFlatAmount);
        checkValidity();
    }
    public Harm(Harm harm) {
        super(new Damage("kinetic", "",
            1));
        setType(harm.type);
        setDiceValue(harm.diceValue);
        setFlatValue(harm.flatValue);
        checkValidity();
    }

    @Override
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        // TODO: create methods like this for all the checkable final arrays
        if (! Harm.isValidType(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }
    /**
     * Warning: this mutator DOES NOT protect any number of invalid cases. It is
     *     assumed that this will be taken care of in Harm() by calling
     *     Harm.checkValidity() immediately after everything has been set.
     * @param diceValue a String which must be "" or a valid dice expression as
     *     defined by Roll.isValidExpression(). Cannot be null.
     */
    @Override
    protected void setDiceValue(String diceValue) {
        boolean isBlank = false;
        boolean isValidExpression = false;

        if (diceValue == null) {
            throw new IllegalArgumentException("diceValue is null");
        }
        diceValue = diceValue.toLowerCase();
        isBlank = diceValue.equals("");
        isValidExpression = Roll.isValidExpression(diceValue);
        // (! isBlank) && (! isValidExpression) is equivalent to the below
        //     expression
        if (! (isBlank || isValidExpression)) {
            // diceValue is neither "" nor a valid dice expression - not allowed
            throw new IllegalArgumentException("Cannot set diceValue to a value"
                + " of: \"" + diceValue + "\" (neither \"\" nor a valid dice"
                + " expression)");
        }
        this.diceValue = diceValue;
    }
    /**
     * Warning: this mutator DOES NOT protect any number of invalid cases. It is
     *     assumed that this will be taken care of in Harm() by calling
     *     Harm.checkValidity() immediately after everything has been set.
     * @param flatValue an int which cannot be < -1.
     */
    @Override
    protected void setFlatValue(int flatValue) {
        if (flatValue < -1) {
            throw new IllegalArgumentException("flat value value: " + flatValue
                + " is < -1");
        }
        this.flatValue = flatValue;
    }

    /**
     * Checks whether a provided String is a valid Harm.type value.
     * @param type a String that cannot be null.
     * @return a boolean containing the result of the check.
     */
    public static boolean isValidType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        for (int i = 0; i < Harm.allowedTypes.length; i++) {
            if (type.equals(Harm.allowedTypes[i])) {
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
            throw new IllegalArgumentException("Cannot create a Harm object"
                + " with a Harm.diceValue value of: \"" + this.diceValue + "\""
                + " and a Harm.flatValue of: " + this.flatValue);
        }
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values.
     * @return a boolean containing the result of the check.
     */
    private boolean isValid() {
        boolean isValidExpression = false;

        // isBlank must be (! isValidExpression) because (isBlank = false,
        //     isValidExpression = false) is not possible because it is guarded
        //     against in Harm.setDiceValue(), and (isBlank = true,
        //     isValidExpression = true) is not possible because a blank String
        //     is not a valid dice expression.
        // Therefore we will only be using isValidExpression in this method to
        //     determine whether this.diceValue and this.flatValue are set to a
        //     valid combination of values.
        isValidExpression = Roll.isValidExpression(this.diceValue);
        if (! isValidExpression) {
            // isValidExpression is false; therefore this.diceValue is ""
            // this.flatValue is allowed to be -1 or any int > 0 in this case
            if (this.flatValue == 0) {
                // it is neither of those values
                return false;
            }
        } else {
            // isValidExpression is true; therefore this.diceValue is something
            //     like "1d6"
            // this.flatValue is allowed to be any int > 0
            if (this.flatValue == -1) {
                // it is not
                return false;
            }
        }
        return true;
    }
    /**
     * Evaluates the amount of harm listed to be dealt by this Harm object.
     * @return an int containing the result.
     */
    public int roll() {
        if (this.type.equals("variable")) {
            throw new IllegalArgumentException("Cannot deal Variable damage."
                + " Call Harm.toDamage() on this instead or create a Damage"
                + "object with this Harm object's properties");
        }
        try {
            return this.toDamage().roll();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Could not convert to a Damage"
                + " object. Harm.roll() cannot be called on a Harm object with"
                + " these values");
        }
    }
    /**
     * Converts this Harm object to a Damage object.
     * @return a Damage object containing this Harm object's values.
     * @throws IllegalArgumentExpression if this Harm object's values are
     *     invalid for a Damage object.
     */
    public Damage toDamage() {
        return new Damage(this.type, this.diceValue, this.flatValue);
    }
}