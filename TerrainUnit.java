// see pg. 68
public class TerrainUnit implements Damageable {
    /**
     * The terrain unit's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Use TerrainUnit.getSize() to get the raw value and
     *     TerrainUnit.outputSize() to obtain it properly formatted.
     * Notes on size - From pg. 59: "By default, each space is equivalent to 10
     *     feet (or 3 meters), but the scale can be changed to represent
     *     different types of encounters."
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
     * Deals (damageAmount) damage of type (damageType) to this TerrainUnit.
     * @param damageAmount an int containing the amount of damage to deal. Must
     *     be > 0.
     * @param damageType a String containing the type of the damage to deal.
     *     Must be a valid damage type as defined by Damage.allowedTypes.
     * @throws IllegalArgumentException if damageAmount is < 1.
     */
    public void receiveHarm(int damageAmount, String damageType) {
        if (damageAmount < 1) {
            throw new IllegalArgumentException("damageAmount value: "
                + damageAmount + " is < 1");
        }
        HelperMethods.checkString("damageType", damageType);
        if (! Damage.isValid(damageType)) {
            throw new IllegalArgumentException("damageType value: \""
                + damageType + "\" is an invalid damage type");
        }
        if (damageType.equals("heat")) {
            receiveHeat(damageAmount);
        } else if (damageType.equals("burn")) {
            receiveBurn(damageAmount);
        } else {
            receiveDamage(damageAmount, damageType);
        }
    }
    /**
     * Deals (damageAmount) damage of type (damageType) to this TerrainUnit.
     * @param damageAmount an int containing the amount of damage to deal. Must
     *     be > 0.
     * @param damageType a String containing the type of the damage to deal.
     *     Must be a valid damage type as defined by Damage.allowedTypes. Cannot
     *     be "heat".
     * @throws IllegalArgumentException if damageAmount is < 1.
     */
    private void receiveDamage(int damageAmount, String damageType) {
        // TODO: fill out with damage mitigation - armor, resistance etc
        int damageToTake;
        int newCurrentHP;

        if (damageAmount < 1) {
            throw new IllegalArgumentException("damageAmount value: "
                + damageAmount + " is < 1");
        }
        HelperMethods.checkString("damageType", damageType);
        if (! Damage.isValid(damageType)) {
            throw new IllegalArgumentException("damageType value: \""
                + damageType + "\" is an invalid damage type");
        }
        if (damageType.equals("heat")) {
            throw new IllegalArgumentException("damageType value: \""
                + damageType + "\" is a non-allowed damage type");
        }
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
     */
    private void receiveHeat(int heatAmount) {
        // do nothing, according to Petrichor
    }
    /**
     * Deals (burnAmount) burn to this TerrainUnit.
     * @param burnAmount an int containing the amount of burn to deal. Must be >
     *     0.
     * @throws IllegalArgumentException if burnAmount is < 1.
     */
    private void receiveBurn(int burnAmount) {
        receiveDamage(burnAmount, "energy");
    }
    public void destroy() {
        System.out.println("This TerrainUnit has been destroyed");
    }
}
