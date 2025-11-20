package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.frame;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;

public class FrameStatblock {
    /**
     * The frame's size.
     * Can be any Size. Cannot be null.
     * 
     * "Depending on their chassis, mechs stand anything from 3 to 15 meters
     *     tall."
     * - pg. 30
     * "SIZE doesn’t always represent a precise height and width – it describes
     *     an area of influence. Not all characters are physically as tall as
     *     the space they can control around them. For example, most SIZE 1
     *     mechs are taller than 10 feet."
     * - pg. 59
     * 
     * See pgs. 30, 32, 59.
     */
    private Size size;

    // health and structure
    /**
     * The frame's max structure value.
     * Must be a minimum of 1.
     */
    private int structure;
    /**
     * The frame's max HP value.
     * Must be a minimum of 1.
     */
    private int HP;
    /**
     * The frame's armor value.
     * Must be a minimum of 0.
     */
    private int armor;

    // heat and stress
    /**
     * The frame's max stress value.
     * Must be a minimum of 1.
     */
    private int stress;
    /**
     * The frame's max heat capacity.
     * Must be a minimum of 1.
     */
    private int heatCapacity;

    // evasion and speed
    /**
     * The frame's evasion value.
     * Must be a minimum of 0.
     */
    private int evasion;
    /**
     * The frame's speed value.
     * Must be a minimum of 0.
     */
    private int speed;

    // e-defense and tech attack
    /**
     * The frame's e-defense value.
     * Must be a minimum of 0.
     */
    private int eDefense;
    /**
     * The frame's tech attack value.
     * Can be any int.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * The frame's sensors value.
     * Must be a minimum of 0.
     */
    private int sensors;
    /**
     * The frame's max repair capacity value.
     * Must be a minimum of 0.
     */
    private int repairCapacity;

    // save target and system points
    /**
     * The frame's save target value.
     * Must be a minimum of 0.
     */
    private int saveTarget;
    /**
     * The frame's system points value.
     * Must be a minimum of 0.
     * 
     * See pg. 33.
     */
    private int systemPoints;

    /**
     * Creates a new FrameStatblock using every possible property.
     */
    public FrameStatblock(Size size, int structure, int HP, int armor,
        int stress, int heatCapacity, int evasion, int speed, int eDefense,
        int techAttack, int sensors, int repairCapacity, int saveTarget,
        int systemPoints) {
        setSize(size);
        setStructure(structure);
        setHP(HP);
        setArmor(armor);
        setStress(stress);
        setHeatCapacity(heatCapacity);
        setEvasion(evasion);
        setSpeed(speed);
        setEDefense(eDefense);
        setTechAttack(techAttack);
        setSensors(sensors);
        setRepairCapacity(repairCapacity);
        setSaveTarget(saveTarget);
        setSystemPoints(systemPoints);
    }
    /**
     * Creates a new FrameStatblock using every property except Structure and
     *     Stress, which it automatically sets to 4. Helpful for player frames.
     */
    public FrameStatblock(Size size, int HP, int armor, int heatCapacity,
        int evasion, int speed, int eDefense, int techAttack, int sensors,
        int repairCapacity, int saveTarget, int systemPoints) {
        setSize(size);
        setStructure(4);
        setHP(HP);
        setArmor(armor);
        setStress(4);
        setHeatCapacity(heatCapacity);
        setEvasion(evasion);
        setSpeed(speed);
        setEDefense(eDefense);
        setTechAttack(techAttack);
        setSensors(sensors);
        setRepairCapacity(repairCapacity);
        setSaveTarget(saveTarget);
        setSystemPoints(systemPoints);
    }
    public FrameStatblock(FrameStatblock frameStatblock) {
        this(frameStatblock.size, frameStatblock.structure, frameStatblock.HP,
            frameStatblock.armor, frameStatblock.stress,
            frameStatblock.heatCapacity, frameStatblock.evasion,
            frameStatblock.speed, frameStatblock.eDefense,
            frameStatblock.techAttack, frameStatblock.sensors,
            frameStatblock.repairCapacity, frameStatblock.saveTarget,
            frameStatblock.systemPoints);
    }

    public Size getSize() {
        return new Size(size);
    }
    public int getStructure() {
        return structure;
    }
    public int getHP() {
        return HP;
    }
    public int getArmor() {
        return armor;
    }
    public int getStress() {
        return stress;
    }
    public int getHeatCapacity() {
        return heatCapacity;
    }
    public int getEvasion() {
        return evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public int getTechAttack() {
        return techAttack;
    }
    public int getSensors() {
        return sensors;
    }
    public int getRepairCapacity() {
        return repairCapacity;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    /**
     * Sets this.size to the value provided.
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        HelperMethods.checkObject("New size", size);
        size = new Size(size);
        this.size = size;
    }
    private void setStructure(int structure) {
        if (structure < 1) {
            throw new IllegalArgumentException("New structure value: "
                + structure + " is < 1");
        }
        this.structure = structure;
    }
    private void setHP(int HP) {
        if (HP < 1) {
            throw new IllegalArgumentException("New HP value: " + HP + " is <"
                + " 1");
        }
        this.HP = HP;
    }
    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value: " + armor
                + " is < 0");
        }
        this.armor = armor;
    }
    private void setStress(int stress) {
        if (stress < 1) {
            throw new IllegalArgumentException("New stress value: " + stress
                + " is < 1");
        }
        this.stress = stress;
    }
    private void setHeatCapacity(int heatCapacity) {
        if (heatCapacity < 1) {
            throw new IllegalArgumentException("New heat capacity value: "
                + heatCapacity + " is < 1");
        }
        this.heatCapacity = heatCapacity;
    }
    private void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion value: " + evasion
                + " is < 0");
        }
        this.evasion = evasion;
    }
    private void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed value: " + speed
                + " is < 0");
        }
        this.speed = speed;
    }
    private void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException("New e-defense value: "
                + eDefense + " is < 0");
        }
        this.eDefense = eDefense;
    }
    private void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    private void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors value: " + sensors
                + " is < 0");
        }
        this.sensors = sensors;
    }
    private void setRepairCapacity(int repairCapacity) {
        if (repairCapacity < 0) {
            throw new IllegalArgumentException("New repair capacity value is: "
                + repairCapacity + " < 0");
        }
        this.repairCapacity = repairCapacity;
    }
    private void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target value: "
                + saveTarget + " is < 0");
        }
        this.saveTarget = saveTarget;
    }
    private void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points value: "
                + systemPoints + " is < 0");
        }
        this.systemPoints = systemPoints;
    }
}
