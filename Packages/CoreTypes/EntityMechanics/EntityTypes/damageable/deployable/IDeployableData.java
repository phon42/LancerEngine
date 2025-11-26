package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;

/**
 * See pgs. 58 and 68.
 */
public class IDeployableData {
    /**
     * The deployable's size.
     * Can be any Size. Cannot be null.
     * Use IDeployableData.getSize() to get the raw value and
     *     IDeployableData.outputSize() to obtain it properly formatted.
     */
    private Size size;
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

    public IDeployableData() {
        this(new Size(2), 0);
    }
    public IDeployableData(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setHP(10 / 2 * size.getValue());
        setArmor(armor);
        setEvasion(5);
    }

    public Size getSize() {
        return size;
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
     * Sets this.size to the provided value.
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        HelperMethods.checkObject("New size", size);
        this.size = size;
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

    /**
     * A helper method which outputs the deployable's size, formatted properly
     *     so that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        return size.output();
    }
}
