package Packages.CoreTypes.EntityMechanics.HarmSystem.harm;

import MainBranch.HelperMethods;
import MainBranch.roll.MixedExpression;
import MainBranch.roll.mixedExpression.ConstantExpression;
import MainBranch.roll.mixedExpression.expression.DiceExpression;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

/**
 * See pgs. 67 and 104.
 * 
 * Represents a much more concrete version of representing damage or harm than
 *     Harm. diceValue and flatValue have much stricter allowed values.
 */
public class Damage extends Harm {
    // Required properties
    /**
     * The Damage's type (i.e. a DamageType representing kinetic).
     * Can be any DamageType. Cannot be null.
     * Depending on the context, a value of burn may represent receiving Burn
     *     (in other words, a type of harm which involves incrementing the
     *     number of Burn stacks on the target) or receiving Burn damage (a type
     *     of damage that is caused by Burn and does NOT increment the number of
     *     Burn stacks on the target).
     */
    private DamageType type;
    // TODO: figure out a way to override the documentation from Harm
    /**
     * The amount of damage dealt (i.e. a MixedExpression representing "1d6+2").
     * Can be any MixedExpression. Cannot be null.
     */
    // protected MixedExpression value;
    // TODO: figure out a way to override the documentation from Harm
    /**
     * The amount of flat damage dealt (i.e. a ConstantExpression representing
     *     2, representing the "2" in "1d6+2").
     * Can be any ConstantExpression with a minimum value of 0. Cannot be null.
     * Can be 0 if this.diceValue isn't null. Otherwise, must be a minimum of 1.
     */
    // protected ConstantExpression flatValue;

    // Optional properties
    // TODO: figure out a way to override the documentation from Harm
    /**
     * The amount of dice damage dealt (i.e. a DiceExpression representing
     *     "1d6", representing the "1d6" in "1d6+2").
     * Can be any DiceExpression. Can be null as long as this.flatValue is not
     *     0.
     */
    // protected DiceExpression diceValue;

    public Damage(DamageType type, MixedExpression value) {
        super(value);
        setType(type);
        checkValidity();
    }
    public Damage(DamageType type, DiceExpression diceValue,
        ConstantExpression flatValue) {
        super(diceValue, flatValue);
        setType(type);
        checkValidity();
    }

    // Required properties
    @Override
    public DamageType getType() {
        return type.toDamageType();
    }
    // Required properties
    private void setType(DamageType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    @Override
    protected void setValue(MixedExpression value) {
        HelperMethods.checkObject("value", value);
        this.value = value;
    }
    // Optional properties
    @Override
    protected void setFlatValue(ConstantExpression flatValue) {
        int value;

        HelperMethods.checkObject("flatValue", flatValue);
        value = flatValue.getValue();
        if (value < 0) {
            throw new IllegalArgumentException("Cannot set this.flatValue to"
                + " a ConstantExpression with a value: " + value + " that is <"
                + " 0");
        }
        this.flatValue = flatValue;
    }

    @Override
    public String toString() {
        String output;

        output = this.value.toString();
        if (this.diceValue != null && this.flatValue.getValue() > 0) {
            output = "(" + output + ")";
        }
        output += " " + this.type.outputValue() + " damage";

        return output;
    }
    /**
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values. Throws an IllegalArgumentException if not.
     * @throws IllegalArgumentException if this.diceValue and this.flatValue are
     *     set to an invalid combination of values.
     */
    @Override
    protected void checkValidity() {
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
    @Override
    protected boolean isValid() {
        // this.diceValue must be a DiceExpression OR this.flatValue must not be
        //     0 (in which case this.diceValue can be null).
        if (this.flatValue.getValue() == 0) {
            // this.diceValue must be a DiceExpression
            if (this.diceValue == null) {
                return false;
            }
        }

        return true;
    }
    /**
     * Evaluates the amount of damage listed to be dealt by this Damage object.
     * @return an int containing the result.
     */
    @Override
    public int roll() {
        return this.value.roll();
    }
    public static Object[] splitDamageString(String input) {
        String[] damageParameters;
        DiceExpression diceInfo = null;
        int flatInput = 0;

        damageParameters = input.split("+");
        if (! damageParameters[0].isBlank()) {
            diceInfo = new DiceExpression(damageParameters[0]);
        }
        if (damageParameters.length > 1) {
            flatInput = Integer.parseInt(damageParameters[1]);
        }

        return new Object[] {diceInfo, flatInput};
    }
    @Override
    public Damage toDamageOfType(DamageType type) {
        HelperMethods.checkObject("type", type);
        try {
            return new Damage(type, this.value);
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException("Cannot change this Damage object's"
                + " Damage.type value to \"" + type.getValue() + "\" for the"
                + " following reason: " + exception.getMessage());
        }
    }
}