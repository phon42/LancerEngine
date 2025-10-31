package packages.coreTypes.entityMechanics.entityTypes;

import main.HelperMethods;
import packages.coreTypes.entityMechanics.harmSystem.Damage;
import packages.coreTypes.entityMechanics.harmSystem.Harm;

/**
 * See pgs. 58 - 59 and 68.
 */
public class TerrainUnit implements Damageable {
    /**
     * The terrain unit's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Use TerrainUnit.getSize() to get the raw value and
     *     TerrainUnit.outputSize() to obtain it properly formatted.
     * 
     * Note on size: "By default, each space is equivalent to 10 feet (or 3
     *     meters), but the scale can be changed to represent different types of
     *     encounters."
     * - pg. 59
     */
    private int size;
    /**
     * The terrain unit's current HP value.
     * Must be a minimum of 0.
     */
    private int currentHP;
    /**
     * The terrain unit's max HP value.
     * Must be a minimum of 1.
     */
    private int maxHP;
    /**
     * The terrain unit's armor value.
     * Must be a minimum of 0.
     */
    private int armor;
    /**
     * The terrain unit's evasion value.
     * Must be a minimum of 0.
     */
    private int evasion;

    public TerrainUnit() {
        this(2, 0);
    }
    public TerrainUnit(int size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setMaxHP(10 / 2 * size);
        setCurrentHP(10 / 2 * size);
        setArmor(armor);
        setEvasion(5);
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
     * A helper method which outputs the terrain unit's size, formatted properly
     *     so that it is human-readable.
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
     * Deals harm to this TerrainUnit.
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
     * Deals damage to this TerrainUnit.
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
        damageAmount = damage.roll();
        damageToTake = Math.min(damageAmount, this.currentHP);
        newCurrentHP = this.currentHP - damageToTake;
        setCurrentHP(newCurrentHP);
        // if the amount of damage it's taking is greater than our terrain
        //     unit's maximum HP, it will be destroyed no matter what its
        //     current HP is
        if (damageAmount > this.currentHP) {
            // it's about to be destroyed
            destroy();
        }
    }
    /**
     * Does nothing because TerrainUnits don't receive heat.
     * @param heat a Damage containing the heat to deal which must have a
     *     Damage.type value of "heat". Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveHeat(Damage heat) {
        // TODO: fill out with mitigation, resistance etc
        int heatAmount;

        HelperMethods.checkObject("heat", heat);
        if (! heat.getType().equals("heat")) {
            throw new IllegalArgumentException("heat has a Damage.type value"
                + " of: \"" + heat.getType() + "\"");
        }
        // heat is being rolled here
        heatAmount = heat.roll();
        // do nothing, according to Petrichor
    }
    /**
     * Deals burn to this Deployable.
     * @param burn a Damage containing the burn to deal which must have a
     *     Damage.type value of "burn". Cannot be null.
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
        receiveDamage(new Damage("energy", null,
            burnAmount));
    }
    public void destroy() {
        System.out.println("This TerrainUnit has been destroyed");
    }
}
