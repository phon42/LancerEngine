package Packages.CoreTypes.EntityMechanics.HarmSystem;

import MainBranch.HelperMethods;
import MainBranch.roll.DiceExpression;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.HarmType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

/**
 * See pgs. 67 and 104.
 */
public class Harm extends Damage {
    /**
     * The Harm's type (i.e. a HarmType representing kinetic).
     * Can be any HarmType. Cannot be null.
     */
    private HarmType type;
    // TODO: figure out a way to override the documentation from Damage
    /**
     * The amount of dice harm dealt (i.e. a DiceExpression representing "1d6",
     *     which in turn represents the "1d6" in "1d6+2").
     * Can be any DiceExpression. Can be null.
     */
    // private DiceExpression diceValue;
    // TODO: figure out a way to override the documentation from Damage
    /**
     * The amount of flat harm dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of 1. Can be -1 if this.diceValue is null. Can be 0 if
     *     this.diceValue is a DiceExpression.
     */
    // private int flatValue;

    public Harm(HarmType harmType, DiceExpression harmDice, int harmFlatAmount)
    {
        super();
        setType(harmType);
        setDiceValue(harmDice);
        setFlatValue(harmFlatAmount);
        checkValidity();
    }

    private void setType(HarmType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    /**
     * Warning: this mutator DOES NOT protect any number of invalid cases. It is
     *     assumed that this will be taken care of in Harm() by calling
     *     Harm.checkValidity() immediately after everything has been set.
     * @param diceValue a DiceExpression which can be any DiceExpression. Can be
     *     null.
     */
    @Override
    protected void setDiceValue(DiceExpression diceValue) {
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
     * Checks whether this.diceValue and this.flatValue are set to a valid
     *     combination of values. Throws an IllegalArgumentException if not.
     * @throws IllegalArgumentException if this.diceValue and this.flatValue are
     *     set to an invalid combination of values.
     */
    private void checkValidity() {
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
    private boolean isValid() {
        boolean isNull = this.diceValue == null;

        if (isNull) {
            // this.flatValue is allowed to be -1 or any int > 0 in this case
            if (this.flatValue == 0) {
                // it is neither of those values
                return false;
            }
        } else {
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
        if (this.type.getValue().equals("variable")) {
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
        if (this.type.getValue().equals("heat")) {
            throw new IllegalStateException("Cannot convert Burn harm to a"
                + " Damage object");
        }
        try {
            return new Damage(this.type, this.diceValue, this.flatValue);
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException("Cannot convert this Harm object to"
                + " a Damage object for the following reason: "
                + exception.getMessage());
        }
    }
}