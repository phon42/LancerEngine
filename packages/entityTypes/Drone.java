package packages.entityTypes;

import main.Roll;
import packages.coreTypes.Damage;
import packages.coreTypes.Harm;

/**
 * See pgs. 58, 67, 105 - 106
 */
public class Drone implements Damageable {
    /**
     * The drone's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Use Drone.getSize() to get the raw value and Drone.outputSize()
     *     to obtain it properly formatted.
     */
    private int size;
    /**
     * The drone's current HP value.
     * Must be a minimum of 0.
     */
    private int currentHP;
    /**
     * The drone's max HP value.
     * Must be a minimum of 1.
     */
    private int maxHP;
    /**
     * The drone's armor value.
     * Must be a minimum of 0.
     */
    private int armor;
    /**
     * The drone's evasion value.
     * Must be a minimum of 0.
     */
    private int evasion;

    public Drone() {
        this(1, 5, 0, 10);
    }
    public Drone(int size, int maxHP, int armor, int evasion) {
        // max always before current
        setSize(size);
        setMaxHP(maxHP);
        setCurrentHP(maxHP);
        setArmor(armor);
        setEvasion(evasion);
    }

    public int getSize() {
        return size;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getArmor() {
        return armor;
    }
    public int getEvasion() {
        return evasion;
    }
    /**
     * Sets this.size to the provided value.
     * @param size an int which must be 1, 2, 4, 6, or 8.
     * @throws IllegalArgumentException if size is not 1, 2, 4, 6, or 8.
     */
    private void setSize(int size) {
        if (size != 1 && size != 2 && size != 4 && size != 6 && size != 8) {
            throw new IllegalArgumentException("New size: " + size + " is not"
                + " one of the following valid values: 1, 2, 4, 6, or 8");
        }
        this.size = size;
    }
    /**
     * Sets this.currentHP to the provided value.
     * @param currentHP an int which cannot be < 0 or > this.maxHP.
     * @throws IllegalArgumentException if currentHP is < 0 or > this.maxHP.
     */
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            throw new IllegalArgumentException("New currentHP value: "
                + currentHP + " is < 0");
        }
        if (this.maxHP < currentHP) {
            throw new IllegalArgumentException("currentHP value provided: "
                + currentHP + " is > maxHP value: " + this.maxHP);
        }
        this.currentHP = currentHP;
    }
    /**
     * Sets this.maxHP to the provided value.
     * @param maxHP an int which cannot be < 1. Will print a warning if maxHP is
     *     < this.currentHP.
     * @throws IllegalArgumentException if maxHP is < 1.
     */
    private void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New maxHP value: " + maxHP
                + " is < 1");
        }
        if (maxHP < this.currentHP) {
            System.out.println("[ WARNING ] maxHP value provided: " + maxHP
                + " is < currentHP value: " + this.currentHP);
        }
        this.maxHP = maxHP;
    }
    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value: " + armor
                + " is < 0");
        }
        this.armor = armor;
    }
    private void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion value: " + evasion
                + " is < 0");
        }
        this.evasion = evasion;
    }

    /**
     * A helper method which outputs the drone's size, formatted properly so
     *     that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        if (size == 1) {
            return "1/2";
        }
        if (size > 1) {
            return Integer.toString(size / 2);
        }
        return Integer.toString(size);
    }

    /**
     * Deals harm to this Drone.
     * @param harm a Harm containing the harm to deal. Must have a Harm.type
     *     value that is not "variable". Must have a Harm.diceValue of something
     *     other than "" OR a Harm.flatValue that is > 0. Cannot be null.
     * @throws IllegalArgumentException if harm is null, harm's Harm.type
     *     property is "variable", or if harm's Harm.diceValue is "" AND harm's
     *     Harm.flatValue is < 1.
     */
    public void receiveHarm(Harm harm) {
        if (harm == null) {
            throw new IllegalArgumentException("harm is null");
        }
        if (harm.getType().equals("variable")) {
            throw new IllegalArgumentException("harm value has a Harm.type"
                + " value of \"variable\"");
        }
        if (harm.getDiceValue().equals("") && harm.getFlatValue() == 0)
        {
            throw new IllegalArgumentException("harm.diceValue is \"\" and"
                + " harm.flatValue value: " + harm.getFlatValue() + " is < 1");
        }
        if (harm.getType().equals("heat")) {
            receiveHeat(harm.getDiceValue(), harm.getFlatValue());
        } else if (harm.getType().equals("burn")) {
            receiveBurn(harm.getDiceValue(), harm.getFlatValue());
        } else {
            receiveDamage(new Damage(harm.getType(), harm.getDiceValue(),
                harm.getFlatValue()));
        }
    }
    /**
     * Deals damage to this Drone.
     * @param damage a Damage containing the damage to deal. Cannot be null.
     */
    private void receiveDamage(Damage damage) {
        // TODO: fill out with damage mitigation - armor, resistance etc
        int damageAmount;
        int damageToTake;
        int newCurrentHP;

        if (damage == null) {
            throw new IllegalArgumentException("damage is null");
        }
        // damage is being rolled here
        damageAmount = Roll.roll(damage.getDiceValue());
        damageAmount += damage.getFlatValue();
        damageToTake = Math.min(damageAmount, this.currentHP);
        newCurrentHP = this.currentHP - damageToTake;
        setCurrentHP(newCurrentHP);
        // if the amount of damage it's taking is greater than our drone's
        //     maximum HP, it will be destroyed no matter what its current HP is
        if (damageAmount > this.currentHP) {
            // it's about to be destroyed
            destroy();
        }
    }
    /**
     * Deals heat to this Drone.
     * @param heatDiceValue a String which must be "" or a valid dice
     *     expression. Cannot be "" if heatFlatValue is 0.
     * @param heatFlatValue an int containing the amount of flat heat to deal.
     *     Must be > -1. Cannot be 0 if heatDiceValue is "".
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveHeat(String heatDiceValue, int heatFlatValue) {
        // when taking Heat, convert to Energy damage (see pg. 67)
        // TODO: fill out with mitigation, resistance etc
        boolean isBlank = false;
        boolean isValidExpression = false;

        if (heatDiceValue == null) {
            throw new IllegalArgumentException("heatDiceValue is null");
        }
        isBlank = heatDiceValue.equals("");
        isValidExpression = Roll.isValidExpression(heatDiceValue);
        if ((! isBlank) && (! isValidExpression)) {
            throw new IllegalArgumentException("heatDiceValue value: \""
                + heatDiceValue + "\" is neither \"\" nor a valid dice"
                + " expression");
        }
        if (heatFlatValue == 0) {
            if (isBlank) {
                throw new IllegalArgumentException("heatDiceValue is \"\" and"
                    + " heatFlatValue is 0");
            }
        }
        receiveDamage(new Damage("energy", heatDiceValue,
            heatFlatValue));
    }
    /**
     * Deals burn to this Drone.
     * @param burnDiceValue a String which must be "" or a valid dice
     *     expression. Cannot be "" if burnFlatValue is 0.
     * @param burnFlatValue an int containing the amount of flat burn to deal.
     *     Must be > -1. Cannot be 0 if burnDiceValue is "".
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveBurn(String burnDiceValue, int burnFlatValue) {
        // TODO: fill out with mitigation, resistance etc
        boolean isBlank = false;
        boolean isValidExpression = false;

        if (burnDiceValue == null) {
            throw new IllegalArgumentException("burnDiceValue is null");
        }
        isBlank = burnDiceValue.equals("");
        isValidExpression = Roll.isValidExpression(burnDiceValue);
        if ((! isBlank) && (! isValidExpression)) {
            throw new IllegalArgumentException("burnDiceValue value: \""
                + burnDiceValue + "\" is neither \"\" nor a valid dice"
                + " expression");
        }
        if (burnFlatValue == 0) {
            if (isBlank) {
                throw new IllegalArgumentException("burnDiceValue is \"\" and"
                    + " burnFlatValue is 0");
            }
        }
        // TODO: fill out - do Drones receive burn?
    }
    public void destroy() {
        System.out.println("This Drone has been destroyed");
    }
}
