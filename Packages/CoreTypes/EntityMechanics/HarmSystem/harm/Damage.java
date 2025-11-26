package Packages.CoreTypes.EntityMechanics.HarmSystem.harm;

import MainBranch.HelperMethods;
import MainBranch.Roll;
import MainBranch.roll.DiceExpression;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

/**
 * See pgs. 67 and 104.
 * 
 * Represents a much more concrete version of representing damage or harm than
 *     Harm. diceValue and flatValue have much stricter allowed values.
 */
public class Damage {
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
    /**
     * The amount of dice damage dealt (i.e. a DiceExpression representing
     *     "1d6", representing the "1d6" in "1d6+2").
     * Can be any DiceExpression. Can be null as long as this.flatValue is not
     *     0.
     */
    protected DiceExpression diceValue;
    /**
     * The amount of flat damage dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of 1. Can be 0 as long as this.diceValue isn't null.
     */
    protected int flatValue;

    public Damage(DamageType damageType, DiceExpression damageDice,
        int damageFlatAmount) {
        setType(damageType);
        setDiceValue(damageDice);
        setFlatValue(damageFlatAmount);
        checkValidity();
    }
    protected Damage() {}
    
    public DamageType getType() {
        return type;
    }
    public DiceExpression getDiceValue() {
        return diceValue;
    }
    public int getFlatValue() {
        return flatValue;
    }
    private void setType(DamageType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    /**
     * Warning: this mutator DOES NOT protect against this.diceValue being set
     *     to null while this.flatValue is 0. It is assumed that this will be
     *     taken care of in Damage() by calling Damage.checkValidity()
     *     immediately after everything has been set.
     * @param diceValue a DiceExpression which can be any DiceExpression. Can be
     *     null.
     */
    protected void setDiceValue(DiceExpression diceValue) {
        this.diceValue = diceValue;
    }
    protected void setFlatValue(int flatValue) {
        if (flatValue < 0) {
            throw new IllegalArgumentException("flat value value: " + flatValue
                + " is < 0");
        }
        if (flatValue == 0) {
            // diceValue must be a DiceExpression
            if (this.diceValue == null) {
                throw new IllegalArgumentException("Cannot set flatValue to a"
                    + " value of 0 while Damage.diceValue is null");
            }
        }
        this.flatValue = flatValue;
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

        isValidExpression = this.diceValue == null;
        // this.diceValue must be a DiceExpression OR this.flatValue must not be
        //     0 (in which case this.diceValue can be null).
        if (this.flatValue == 0) {
            // this.diceValue must be a DiceExpression
            if (! isValidExpression) {
                return false;
            }
        }

        return true;
    }
    /**
     * Evaluates the amount of damage listed to be dealt by this Damage object.
     * @return an int containing the result.
     */
    public int roll() {
        // damage is being rolled here
        if (this.diceValue == null) {
            return Roll.roll(this.flatValue);
        }

        return this.diceValue.roll() + Roll.roll(this.flatValue);
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
}