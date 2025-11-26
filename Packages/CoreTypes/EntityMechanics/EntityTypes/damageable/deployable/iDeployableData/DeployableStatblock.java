package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableData;

import Packages.CoreTypes.Size;

public class DeployableStatblock {
    /**
     * The deployable's max HP value.
     * Must be a minimum of 1.
     */
    private int hp;
    /**
     * The deployable's armor value.
     * Must be a minimum of 0.
     */
    private int armor;
    /**
     * The deployable's evasion value.
     * Must be a minimum of 0.
     */
    private int evasion;
    private int eDefense;
    private int heatCapacity;
    private int repairCapacity;
    private int sensors;
    private int techAttack;
    private int saveTarget;
    private int speed;

    public DeployableStatblock(int hp, int armor, int evasion, int eDefense,
        int heatCap, int repairCap, int sensors, int techAttack, int saveTarget,
        int speed) {
        setHP(hp);
        setArmor(armor);
        setEvasion(evasion);
        // TODO: fill out
    }
    public DeployableStatblock(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setHP(10 / 2 * size.getValue());
        setArmor(armor);
        setEvasion(5);
    }
    public DeployableStatblock() {
        this(new Size(2), 0);
    }

    public int getHP() {
        return hp;
    }
    public int getArmor() {
        return armor;
    }
    public int getEvasion() {
        return evasion;
    }
    /**
     * Sets this.hp to the provided value.
     * @param hp an int which cannot be < 1.
     * @throws IllegalArgumentException if hp is < 1.
     */
    private void setHP(int hp) {
        if (hp < 1) {
            throw new IllegalArgumentException("New hp value: " + hp + " is <"
                + " 1");
        }
        this.hp = hp;
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
}
