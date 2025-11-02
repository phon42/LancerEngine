package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.Damageable;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Damage;
import Packages.CoreTypes.EntityMechanics.HarmSystem.damage.Harm;
import Packages.CoreTypes.Size;

/**
 * See pgs. 58, 67, 105 - 106
 */
public class Drone implements Damageable {
    /**
     * The drone's size.
     * Can be any Size. Cannot be null.
     * Use Drone.getSize() to get the raw value and Drone.outputSize()
     *     to obtain it properly formatted.
     */
    private Size size;
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
        this(new Size(1), 5, 0, 10);
    }
    public Drone(Size size, int maxHP, int armor, int evasion) {
        // max always before current
        setSize(size);
        setMaxHP(maxHP);
        setCurrentHP(maxHP);
        setArmor(armor);
        setEvasion(evasion);
    }

    public Size getSize() {
        return new Size(size);
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
     * @param size a Size which can be any Size. Cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        HelperMethods.checkObject("New size", size);
        size = new Size(size);
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
            HelperMethods.warn("[ WARNING ] maxHP value provided: " + maxHP
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
        return size.output();
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
        Damage damage;

        HelperMethods.checkObject("harm", harm);
        if (harm.getType().equals("variable")) {
            throw new IllegalArgumentException("harm value has a Harm.type"
                + " value of \"variable\"");
        }
        if (harm.getDiceValue() == null && harm.getFlatValue() == 0) {
            throw new IllegalArgumentException("harm.diceValue is \"\" and"
                + " harm.flatValue value: " + harm.getFlatValue() + " is < 1");
        }
        damage = harm.toDamage();
        if (harm.getType().equals("heat")) {
            receiveHeat(damage);
        } else if (harm.getType().equals("burn")) {
            receiveBurn(damage);
        } else {
            receiveDamage(damage);
        }
    }
    /**
     * Deals damage to this Drone.
     * @param damage a Damage containing the damage to deal. Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveDamage(Damage damage) {
        // TODO: fill out with damage mitigation - armor, resistance etc
        int damageAmount;
        int damageToTake;
        int newCurrentHP;

        HelperMethods.checkObject("damage", damage);
        // damage is being rolled here
        damageAmount = damage.getDiceValue().roll();
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
     * @param heat a Damage containing the heat to deal. Cannot be null. Must
     *     have a Damage.type value of "heat".
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveHeat(Damage heat) {
        // TODO: fill out with mitigation, resistance etc
        // when taking Heat, convert to Energy damage (see pg. 67)
        Damage damage;

        HelperMethods.checkObject("heat", heat);
        if (! heat.getType().equals("heat")) {
            throw new IllegalArgumentException("heat has a Damage.type value"
                + " of: \"" + heat.getType() + "\"");
        }
        damage = new Damage(heat.getType(), heat.getDiceValue(),
            heat.getFlatValue());
        receiveDamage(damage);
    }
    /**
     * Deals burn to this Drone.
     * @param burn a Damage containing the burn to deal. Cannot be null. Must
     *     have a Damage.type value of "burn".
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveBurn(Damage burn) {
        // TODO: fill out with mitigation, resistance etc
        int burnAmount;

        HelperMethods.checkObject("burn", burn);
        if (! burn.getType().equals("burn")) {
            throw new IllegalArgumentException("burn has a Damage.type value"
                + " of: \"" + burn.getType() + "\"");
        }
        // burn is being rolled here
        burnAmount = burn.roll();
        // TODO: fill out - do Drones receive burn?
    }
    public void destroy() {
        System.out.println("This Drone has been destroyed");
    }
}
