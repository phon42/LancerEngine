package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.FrameStatblock;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Talent.Talent;

/**
 * See pgs. 33 - 34.
 */
public class MechStatblock {
    // Size
    /**
     * The mech's size.
     * Can be any Size. Cannot be null.
     * Use Mech.getSize() to get the raw value and Mech.outputSize() to obtain
     *     it properly formatted.
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

    // Health and structure
    /**
     * The mech's current structure value.
     * Must be between 0 and this.maxStructure (inclusive).
     * 
     * See pg. 80.
     */
    private int currentStructure;
    /**
     * The mech's max structure value.
     * Must be a minimum of 1.
     * 
     * See pg. 80.
     */
    private int maxStructure;
    /**
     * The mech's current HP value.
     * Must be between 0 and this.maxHP (inclusive).
     */
    private int currentHP;
    /**
     * The mech's max HP value.
     * Must be a minimum of 1.
     */
    private int maxHP;
    /**
     * The mech's armor value.
     * Must be a minimum of 0.
     */
    private int armor;

    // Heat and stress
    /**
     * The mech's current stress value.
     * Must be between 0 and this.maxStress (inclusive).
     * 
     * See pg. 81.
     */
    private int currentStress;
    /**
     * The mech's max stress value.
     * Must be a minimum of 1.
     * 
     * See pg. 81.
     */
    private int maxStress;
    /**
     * The mech's current heat.
     * Must be between 0 and this.maxHeatCapacity (inclusive).
     */
    private int currentHeat;
    /**
     * The mech's max heat capacity.
     * Must be a minimum of 1.
     */
    private int maxHeatCapacity;

    // Evasion and speed
    /**
     * The mech's evasion value.
     * Must be a minimum of 0.
     */
    private int evasion;
    /**
     * The mech's speed value.
     * Must be a minimum of 0.
     */
    private int speed;

    // E-defense and tech attack
    /**
     * The mech's e-defense value.
     * Must be a minimum of 0.
     */
    private int eDefense;
    /**
     * The mech's tech attack value.
     * Can be any int.
     */
    private int techAttack;

    // Sensors and repair capacity
    /**
     * The mech's sensors value.
     * Must be a minimum of 0.
     */
    private int sensors;
    /**
     * The number of repairs this mech currently has.
     * Must be between 0 and this.maxRepairCapacity (inclusive).
     * 
     * See pg. 82.
     */
    private int currentRepairs;
    /**
     * The mech's max repair capacity value.
     * Must be a minimum of 0.
     * 
     * See pg. 82.
     */
    private int maxRepairCapacity;

    // Save target and system points
    /**
     * The mech's save target value.
     * Must be a minimum of 0.
     */
    private int saveTarget;
    /**
     * The mech's system points value.
     * Must be a minimum of 0.
     * 
     * See pg. 33.
     */
    private int systemPoints;

    // Limited systems bonus
    /**
     * The mech's limited systems bonus value.
     * Must be a minimum of 0.
     */
    private int limitedSystemsBonus;

    public MechStatblock(FrameStatblock frameStatblock) {
        calculateStats(frameStatblock);
    }
    public MechStatblock(Frame frame) {
        this(frame.getStatblock());
    }
    public MechStatblock(MechStatblock mechStatblock) {
        // Size
        setSize(mechStatblock.size);
        // Health and structure
        setMaxStructure(mechStatblock.maxStructure);
        setCurrentStructure(mechStatblock.currentStructure);
        setMaxHP(mechStatblock.maxHP);
        setCurrentHP(mechStatblock.currentHP);
        setArmor(mechStatblock.armor);
        // Heat and stress
        setMaxStress(mechStatblock.maxStress);
        setCurrentStress(mechStatblock.currentStress);
        setMaxHeatCapacity(mechStatblock.maxHeatCapacity);
        setCurrentHeat(mechStatblock.currentHeat);
        // Evasion and speed
        setEvasion(mechStatblock.evasion);
        setSpeed(mechStatblock.speed);
        // E-defense and tech attack
        setEDefense(mechStatblock.eDefense);
        setTechAttack(mechStatblock.techAttack);
        // Sensors and repair capacity
        setSensors(mechStatblock.sensors);
        setMaxRepairCapacity(mechStatblock.maxRepairCapacity);
        setCurrentRepairs(mechStatblock.currentRepairs);
        // Save target and system points
        setSaveTarget(mechStatblock.saveTarget);
        setSystemPoints(mechStatblock.systemPoints);
        // Limited systems bonus
        setLimitedSystemsBonus(mechStatblock.limitedSystemsBonus);
    }

    // Size
    public Size getSize() {
        return size;
    }
    // Health and structure
    public int getCurrentStructure() {
        return currentStructure;
    }
    public int getMaxStructure() {
        return maxStructure;
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
    // Heat and stress
    public int getCurrentStress() {
        return currentStress;
    }
    public int getMaxStress() {
        return maxStress;
    }
    public int getCurrentHeat() {
        return currentHeat;
    }
    public int getMaxHeatCapacity() {
        return maxHeatCapacity;
    }
    // Evasion and speed
    public int getEvasion() {
        return evasion;
    }
    public int getSpeed() {
        return speed;
    }
    // E-defense and tech attack
    public int getEDefense() {
        return eDefense;
    }
    public int getTechAttack() {
        return techAttack;
    }
    // Sensors and repair capacity
    public int getSensors() {
        return sensors;
    }
    public int getCurrentRepairs() {
        return currentRepairs;
    }
    public int getMaxRepairCapacity() {
        return maxRepairCapacity;
    }
    // Save target and system points
    public int getSaveTarget() {
        return saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    // Limited systems bonus
    public int getLimitedSystemsBonus() {
        return limitedSystemsBonus;
    }
    // Size
    /**
     * Sets this.size to the provided value.
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    public void setSize(Size size) {
        HelperMethods.checkObject("New size", size);
        this.size = size;
    }
    // Health and structure
    /**
     * Sets this.currentStructure to the provided value.
     * @param currentStructure an int which cannot be < 0 or >
     *     this.maxStructure.
     * @throws IllegalArgumentException if currentStructure is < 0 or >
     *     this.maxStructure.
     */
    public void setCurrentStructure(int currentStructure) {
        if (currentStructure < 0) {
            throw new IllegalArgumentException("New currentStructure value: "
                + currentStructure + " is < 0");
        }
        if (this.maxStructure < currentStructure) {
            throw new IllegalArgumentException("currentStructure value"
                + " provided: " + currentStructure + " is > maxStructure value:"
                + " " + this.maxStructure);
        }
        this.currentStructure = currentStructure;
    }
    /**
     * Sets this.maxStructure to the provided value.
     * @param maxStructure an int which cannot be < 1. Will print a warning if
     *     maxStructure is < this.currentStructure.
     * @throws IllegalArgumentException if maxStructure is < 1.
     */
    public void setMaxStructure(int maxStructure) {
        if (maxStructure < 1) {
            throw new IllegalArgumentException("New maxStructure value: "
                + maxStructure + " is < 1");
        }
        if (maxStructure < this.currentStructure) {
            HelperMethods.warn("maxStructure value provided: " + maxStructure
                + " is < currentStructure value: " + this.currentStructure);
        }
        this.maxStructure = maxStructure;
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
    public void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New maxHP value: " + maxHP
                + " is < 1");
        }
        if (maxHP < this.currentHP) {
            HelperMethods.warn("maxHP value provided: " + maxHP + " is <"
                + " currentHP value: " + this.currentHP);
        }
        this.maxHP = maxHP;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value: " + armor
                + " is < 0");
        }
        this.armor = armor;
    }
    // Heat and stress
    /**
     * Sets this.currentStress to the provided value.
     * @param currentStress an int which cannot be < 0 or > this.maxStress.
     * @throws IllegalArgumentException if currentStress is < 0 or >
     * this.maxStress.
     */
    public void setCurrentStress(int currentStress) {
        if (currentStress < 0) {
            throw new IllegalArgumentException("New currentStress value: "
                + currentStress + " is < 0");
        }
        if (this.maxStress < currentStress) {
            throw new IllegalArgumentException("currentStress value provided: "
                + currentStress + " is > maxStress value: " + this.maxStress);
        }
        this.currentStress = currentStress;
    }
    /**
     * Sets this.maxStress to the provided value.
     * @param maxStress an int which cannot be < 1. Will print a warning if
     *     maxStress is < this.currentStress.
     * @throws IllegalArgumentException if maxStress is < 1.
     */
    public void setMaxStress(int maxStress) {
        if (maxStress < 1) {
            throw new IllegalArgumentException("New maxStress value: "
                + maxStress + " is < 1");
        }
        if (maxStress < this.currentStress) {
            HelperMethods.warn("maxStress value provided: " + maxStress + " is"
                + " < currentStress value: " + this.currentStress);
        }
        this.maxStress = maxStress;
    }
    /**
     * Sets this.currentHeat to the provided value.
     * @param currentHeat an int which cannot be < 0 or > this.maxHeatCapacity.
     * @throws IllegalArgumentException if currentHeat is < 0 or >
     *     this.maxHeatCapacity.
     */
    public void setCurrentHeat(int currentHeat) {
        if (currentHeat < 0) {
            throw new IllegalArgumentException("New currentHeat value: "
                + currentHeat + " is < 0");
        }
        if (this.maxHeatCapacity < currentHeat) {
            throw new IllegalArgumentException("currentHeat value provided: "
                + currentHeat + " is > maxHeatCapacity value: "
                + this.maxHeatCapacity);
        }
        this.currentHeat = currentHeat;
    }
    /**
     * Sets this.maxHeatCapacity to the provided value.
     * @param maxHeatCapacity an int which cannot be < 1. Will print a warning
     *     if maxHeatCapacity is < this.currentHeat.
     * @throws IllegalArgumentException if maxHeatCapacity is < 1.
     */
    public void setMaxHeatCapacity(int maxHeatCapacity) {
        if (maxHeatCapacity < 1) {
            throw new IllegalArgumentException("New maxHeatCapacity value: "
                + maxHeatCapacity + " is < 1");
        }
        if (maxHeatCapacity < this.currentHeat) {
            HelperMethods.warn("maxHeatCapacity value provided: "
                + maxHeatCapacity + " is < currentHeat value: "
                + this.currentHeat);
        }
        this.maxHeatCapacity = maxHeatCapacity;
    }
    // Evasion and speed
    public void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion value: " + evasion
                + " is < 0");
        }
        this.evasion = evasion;
    }
    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed value: " + speed
                + " is < 0");
        }
        this.speed = speed;
    }
    // E-defense and tech attack
    public void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException("New e-defense value: "
                + eDefense + " is < 0");
        }
        this.eDefense = eDefense;
    }
    public void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    // Sensors and repair capacity
    public void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors value: " + sensors
                + " is < 0");
        }
        this.sensors = sensors;
    }
    /**
     * Sets this.currentRepairs to the provided value.
     * @param currentRepairs an int which cannot be < 0 or >
     *     this.maxRepairCapacity.
     * @throws IllegalArgumentException if currentRepairs is < 0 or >
     *     this.maxRepairCapacity.
     */
    public void setCurrentRepairs(int currentRepairs) {
        if (currentRepairs < 0) {
            throw new IllegalArgumentException("New currentRepairs value:"
                + currentRepairs + " is < 0");
        }
        if (this.maxRepairCapacity < currentRepairs) {
            throw new IllegalArgumentException("currentRepairs value provided: "
                + currentRepairs + " is > maxRepairCapacity value: "
                + this.maxRepairCapacity);
        }
        this.currentRepairs = currentRepairs;
    }
    /**
     * Sets this.maxRepairCapacity to the provided value.
     * @param maxRepairCapacity an int which cannot be < 0. Will print a warning
     *     if maxRepairCapacity is < this.currentRepairs.
     * @throws IllegalArgumentException if maxRepairCapacity is < 0.
     */
    public void setMaxRepairCapacity(int maxRepairCapacity) {
        if (maxRepairCapacity < 0) {
            throw new IllegalArgumentException("New maxRepairCapacity value: "
                + maxRepairCapacity + " is < 0");
        }
        if (maxRepairCapacity < this.currentRepairs) {
            HelperMethods.warn("maxRepairCapacity value provided: "
                + maxRepairCapacity + " is < currentRepairs value: "
                + this.currentRepairs);
        }
        this.maxRepairCapacity = maxRepairCapacity;
    }
    // Save target and system points
    public void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target value: "
                + saveTarget + " is < 0");
        }
        this.saveTarget = saveTarget;
    }
    public void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points value: "
                + systemPoints + " is < 0");
        }
        this.systemPoints = systemPoints;
    }
    // Limited systems bonus
    public void setLimitedSystemsBonus(int limitedSystemsBonus) {
        if (limitedSystemsBonus < 0) {
            throw new IllegalArgumentException("New limited systems bonus"
                + " value: " + limitedSystemsBonus + " is < 0");
        }
        this.limitedSystemsBonus = limitedSystemsBonus;
    }

    /**
     * Sets all of this MechStatblock object's stat properties to their correct
     *     values, calculated based off of the provided FrameStatblock's stats.
     */
    public void calculateStats(FrameStatblock statblock) {
        calculateStats(statblock, 0, new int[4], new String[0],
            new Talent[0]);
    }
    /**
     * Sets all of this MechStatblock's properties to their correct values,
     *     calculated based off of the provided FrameStatblock as well as the
     *     provided int[].
     * @param statblock a FrameStatblock containing the core stats to use
     *     in calculation.
     * @param grit an int containing the grit score of the Pilot associated with
     *     the Mech associated with this MechStatblock.
     * @param mechSkills an int[4] containing the mech skills of the Pilot
     *     associated with the Mech associated with this MechStatblock.
     * @param coreBonuses a String[] containing the core bonuses of the Pilot
     *     associated with the Mech associated with this MechStatblock.
     * @param talents a String[] containing the talents of the Pilot associated
     *     with the Mech associated with this MechStatblock.
     * @throws IllegalArgumentException if statblock is null.
     */
    public void calculateStats(FrameStatblock statblock, int grit,
        int[] mechSkills, String[] coreBonuses, Talent[] talents) {
        // Structure and stress
        setMaxStructure(statblock.getStructure());
        setCurrentStructure(getMaxStructure());
        setMaxStress(statblock.getStress());
        setCurrentStress(getMaxStress());

        // Hull
        setMaxHP(statblock.getHP() + (mechSkills[0] * 2));
        setCurrentHP(getMaxHP());
        setMaxRepairCapacity(statblock.getRepairCapacity()
            + (mechSkills[0] / 2));
        setCurrentRepairs(getMaxRepairCapacity());

        // Agility
        setEvasion(statblock.getEvasion() + mechSkills[1]);
        setSpeed(statblock.getSpeed() + (mechSkills[1] / 2));

        // Systems
        setEDefense(statblock.getEDefense() + mechSkills[2]);
        setTechAttack(statblock.getTechAttack() + mechSkills[2]);
        // Add grit to system points - see pg. 37
        setSystemPoints(statblock.getSystemPoints() + (mechSkills[2] / 2)
            + grit);

        // Engineering
        // setMaxHeatCapacity() swapped with setCurrentHeat() because the
        //     mutators may throw exceptions otherwise
        setMaxHeatCapacity(statblock.getHeatCapacity() + mechSkills[3]);
        setCurrentHeat(0);
        setLimitedSystemsBonus(mechSkills[3] / 2);

        // Everything else
        setSize(statblock.getSize());
        setArmor(statblock.getArmor());
        setSensors(statblock.getSensors());
        setSaveTarget(statblock.getSaveTarget());
    }
}
