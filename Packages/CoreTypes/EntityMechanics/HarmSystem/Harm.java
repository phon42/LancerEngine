package Packages.CoreTypes.EntityMechanics.HarmSystem;

import MainBranch.HelperMethods;
import MainBranch.roll.MixedExpression;
import MainBranch.roll.mixedExpression.ConstantExpression;
import MainBranch.roll.mixedExpression.expression.DiceExpression;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.HarmType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

/**
 * See pgs. 67 and 104.
 */
public class Harm {
    // Required properties
    /**
     * The Harm's type (i.e. a HarmType representing kinetic).
     * Can be any HarmType. Cannot be null.
     */
    private HarmType type;
    /**
     * The amount of flat harm dealt (i.e. a ConstantExpression representing 2,
     *     representing the "2" in "1d6+2").
     * Can be any ConstantExpression with a minimum value of 0. Cannot be null.
     * Can be -1 if this.diceValue is null. Otherwise, must be a minimum of 0.
     */
    protected ConstantExpression flatValue;

    // Optional properties
    /**
     * The amount of harm dealt (i.e. a MixedExpression representing "1d6+2").
     * Can be any MixedExpression. Can be null.
     * A null MixedExpression represents the "???" damage dealt by the Mimic Gun
     *     of the Pegasus license.
     */
    protected MixedExpression value;
    /**
     * The amount of dice harm dealt (i.e. a DiceExpression representing "1d6",
     *     which in turn represents the "1d6" in "1d6+2").
     * Can be any DiceExpression. Can be null.
     */
    protected DiceExpression diceValue;

    public Harm(HarmType type, MixedExpression value) {
        setType(type);
        setValue(value);
        checkValidity();
    }
    public Harm(HarmType type, DiceExpression diceValue,
        ConstantExpression flatValue) {
        setType(type);
        setDiceValue(diceValue);
        setFlatValue(flatValue);
        checkValidity();
    }
    public Harm(HarmType type, String diceValue, int flatValue) {

    }
    protected Harm(MixedExpression value) {
        setValue(value);
    }
    protected Harm(DiceExpression harmDice, ConstantExpression harmFlatAmount) {
        setDiceValue(harmDice);
        setFlatValue(harmFlatAmount);
    }
    protected Harm(String diceValue, int flatValue) {
        setDiceValue(diceValue);
        setFlatValue(flatValue);
    }
    protected Harm() {}

    // Required properties
    public HarmType getType() {
        return type;
    }
    public ConstantExpression getFlatValue() {
        return flatValue;
    }
    // Optional properties
    public MixedExpression getValue() {
        return value;
    }
    public DiceExpression getDiceValue() {
        return diceValue;
    }
    // Required properties
    private void setType(HarmType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    protected void setFlatValue(ConstantExpression flatValue) {
        int value;

        HelperMethods.checkObject("flatValue", flatValue);
        value = flatValue.getValue();
        if (value < -1) {
            throw new IllegalArgumentException("Cannot set this.flatValue to"
                + " a ConstantExpression with a value: " + value + " that is <"
                + " -1");
        }
        this.flatValue = flatValue;
    }
    // Optional properties
    protected void setValue(MixedExpression value) {
        this.value = value;
    }
    /**
     * Warning: this mutator DOES NOT protect any number of invalid cases. It is
     *     assumed that this will be taken care of in Harm() by calling
     *     Harm.checkValidity() immediately after everything has been set.
     * @param diceValue a DiceExpression which can be any DiceExpression. Can be
     *     null.
     */
    protected void setDiceValue(DiceExpression diceValue) {
        this.diceValue = diceValue;
    }

    @Override
    public String toString() {
        String output;

        output = this.value.toString();
        if (this.diceValue != null && this.flatValue.getValue() > 0) {
            output = "(" + output + ")";
        }
        output += " " + this.type.outputValue() + " harm";

        return output;
    }
    protected void setDiceValue(String diceValue) {
        if (diceValue == null) {
            this.diceValue = null;
        } else {
            setDiceValue(new DiceExpression(diceValue));
        }
    }
    /**
     * Warning: this mutator DOES NOT protect any number of invalid cases. It is
     *     assumed that this will be taken care of in Harm() by calling
     *     Harm.checkValidity() immediately after everything has been set.
     * @param flatValue an int which can be any int.
     */
    protected void setFlatValue(int flatValue) {
        setFlatValue(new ConstantExpression(flatValue));
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values. Throws an IllegalArgumentException if not.
     * @throws IllegalArgumentException if this.diceValue and this.flatValue are
     *     set to an invalid combination of values.
     */
    protected void checkValidity() {
        if (! isValid()) {
            throw new IllegalArgumentException("Cannot create a Harm object"
                + " with a Harm.diceValue value of: " + this.diceValue + " and"
                + " a Harm.flatValue of: " + this.flatValue);
        }
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values.
     * @return a boolean containing the result of the check.
     */
    protected boolean isValid() {
        boolean isNull = this.diceValue == null;

        if (isNull) {
            // this.flatValue is allowed to be -1 or any int > 0 in this case
            if (this.flatValue.getValue() == 0) {
                // it is neither of those values
                return false;
            }
        } else {
            // this.flatValue is allowed to be any int > 0
            if (this.flatValue.getValue() == -1) {
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
        if (this.type.getValue().equals("variable")) {
            throw new IllegalArgumentException("Cannot deal Variable damage."
                + " Call Harm.toDamageOfType() on this instead or create a"
                + " Damage object with this Harm object's properties");
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
        if (this.type.getValue().equals("heat")) {
            throw new IllegalStateException("Cannot convert Burn harm to a"
                + " Damage object");
        }
        try {
            return new Damage(this.type.toDamageType(), this.diceValue,
                this.flatValue);
        } catch (IllegalStateException exception) {
            throw new IllegalStateException("Cannot convert this Harm object to"
                + " a Damage object for the following reason: "
                + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException("Cannot convert this Harm object to"
                + " a Damage object for the following reason: "
                + exception.getMessage());
        }
    }
    public Damage toDamageOfType(DamageType type) {
        Damage damage;

        HelperMethods.checkObject("type", type);
        try {
            damage = this.toDamage();

            return damage.toDamageOfType(type);
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException("Cannot convert this Harm object to"
                + " a Damage object for the following reason: "
                + exception.getMessage());
        }
    }
}