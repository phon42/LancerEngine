package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;

public class DeployableStatblock {
    // Required property
    /**
     * Which of the deployable's stats were actually provided on construction.
     * Can be any boolean[] that is of length 10. Cannot be null.
     */
    private boolean[] stats;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties (Main properties)
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

    // Semi-required property (Secondary property)
    /**
     * Must be a minimum of 0.
     */
    private int eDefense;

    // Semi-required properties (Extra properties)
    /**
     * Must be a minimum of 1.
     */
    private int heatCapacity;
    /**
     * Must be a minimum of 0.
     */
    private int repairCapacity;
    /**
     * Must be a minimum of 0.
     */
    private int sensors;
    /**
     * Can be any int.
     */
    private int techAttack;
    /**
     * Must be a minimum of 0.
     */
    private int saveTarget;
    /**
     * Must be a minimum of 0.
     */
    private int speed;

    public DeployableStatblock(
        // Semi-required properties
        int hp, int armor, int evasion,
        // Semi-required property
        int eDefense,
        // Semi-required properties
        int heatCap, int repairCap, int sensors, int techAttack, int saveTarget,
        int speed
    ) {
        HelperMethods.verifyConstructor();
        // Required property
        setStats(new boolean[10]);
        // Semi-required properties
        setHP(hp);
        setArmor(armor);
        setEvasion(evasion);
        // Semi-required property
        setEDefense(eDefense);
        // Semi-required properties
        setHeatCapacity(heatCap);
        setRepairCapacity(repairCap);
        setSensors(sensors);
        setTechAttack(techAttack);
        setSaveTarget(saveTarget);
        setSpeed(speed);
    }
    public DeployableStatblock(int hp, int armor, int evasion, int eDefense) {
        this(hp);
        setArmor(armor);
        setEvasion(evasion);
        setEDefense(eDefense);
        this.stats[1] = true;
        this.stats[2] = true;
        this.stats[3] = true;
    }
    public DeployableStatblock(Size size, int armor, int evasion, int eDefense)
    {
        this(size, evasion, eDefense);
        setArmor(armor);
        this.stats[1] = true;
    }
    public DeployableStatblock(Size size, int evasion, int eDefense) {
        this(size, evasion);
        setEDefense(eDefense);
        this.stats[3] = true;
    }
    public DeployableStatblock(Size size, int evasion) {
        this(size);
        setEvasion(evasion);
        this.stats[2] = true;
    }
    public DeployableStatblock() {
        this(new Size(2));
    }
    public DeployableStatblock(Size size) {
        this(10 / 2 * size.getValue());
        this.stats[0] = true;
    }
    public DeployableStatblock(int hp) {
        // default values from pg. 68 and from the Everest frame
        this(
            // Semi-required properties
            hp, 0, 5,
            // Semi-required property
            10,
            // Semi-required properties
            6, 5, 10, 0,
            10, 0
        );
        this.stats[0] = true;
    }

    // Required property
    public boolean[] getStats() {
        return HelperMethods.copyOf(stats);
    }
    // Semi-required properties (Main properties)
    public int getHP() {
        return hp;
    }
    public int getArmor() {
        return armor;
    }
    public int getEvasion() {
        return evasion;
    }
    // Semi-required property (Secondary property)
    public int getEDefense() {
        return eDefense;
    }
    // Semi-required properties (Extra properties)
    public int getHeatCapacity() {
        return heatCapacity;
    }
    public int getRepairCapacity() {
        return repairCapacity;
    }
    public int getSensors() {
        return sensors;
    }
    public int getTechAttack() {
        return techAttack;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public int getSpeed() {
        return speed;
    }
    // Required property
    private void setStats(boolean[] stats) {
        HelperMethods.checkObject("stats", stats);
        if (stats.length != 10) {
            throw new IllegalArgumentException("stats array is of length: "
                + stats.length + " which is not 10");
        } 
        this.stats = stats;
    }
    // Semi-required properties (Main properties)
    /**
     * Sets this.hp to the provided value.
     * @param hp an int which cannot be < 1.
     * @throws IllegalArgumentException if hp is < 1.
     */
    private void setHP(int hp) {
        checkMin("hp", hp, 1);
        this.hp = hp;
    }
    private void setArmor(int armor) {
        checkMin("armor", armor, 0);
        this.armor = armor;
    }
    private void setEvasion(int evasion) {
        checkMin("evasion", evasion, 0);
        this.evasion = evasion;
    }
    // Semi-required property (Secondary property)
    private void setEDefense(int eDefense) {
        checkMin("eDefense", eDefense, 0);
        this.eDefense = eDefense;
    }
    // Semi-required properties (Extra properties)
    private void setHeatCapacity(int heatCapacity) {
        checkMin("heatCapacity", heatCapacity, 1);
        this.heatCapacity = heatCapacity;
    }
    private void setRepairCapacity(int repairCapacity) {
        checkMin("repairCapacity", repairCapacity, 0);
        this.repairCapacity = repairCapacity;
    }
    private void setSensors(int sensors) {
        checkMin("sensors", sensors, 0);
        this.sensors = sensors;
    }
    private void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    private void setSaveTarget(int saveTarget) {
        checkMin("saveTarget", saveTarget, 0);
        this.saveTarget = saveTarget;
    }
    private void setSpeed(int speed) {
        checkMin("speed", speed, 0);
        this.speed = speed;
    }

    private static void checkMin(String propertyName, int value, int minimum)
        throws IllegalArgumentException {
        if (value < minimum) {
            throw new IllegalArgumentException("New " + propertyName
                + " value: " + value + " is < " + minimum);
        }
    }
}
