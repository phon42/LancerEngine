/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its mounts, and its
 *     systems, among other statistics.
 */
public class Mech {
    // frame that this mech is (i.e. Swallowtail)
    private Frame frame;
    
    // mounts/weapons
    private Mount[] mounts;

    // systems
    private MechSystem[] systems;

    // frame attributes - size, structure, HP, etc.
    /**
     * Represents the size of the mech.
     * Size is stored as 2 * its value (Size 1/2 would be stored as int 1).
     * Allowed values for size: -1 (placeholder), 1, 2, 4, 6, 8.
     */
    private int size;

    // health and structure
    private int currentStructure;
    private int maxStructure;
    private int currentHP;
    private int maxHP;
    private int armor;

    // heat and stress
    private int currentStress;
    private int maxStress;
    private int currentHeatCapacity;
    private int maxHeatCapacity;

    // evasion and speed
    private int evasion;
    private int speed;

    // e-defense and tech attack
    private int eDefense;
    private int techAttack;

    // sensors and repair capacity
    private int sensors;
    private int currentRepairCapacity;
    private int maxRepairCapacity;

    // save target and system points
    private int saveTarget;
    private int systemPoints;

    // limited systems bonus
    private int limitedSystemsBonus;

    public Mech() {
        setCurrentStructure(4);
        setMaxStructure(4);
        setCurrentStress(4);
        setMaxStress(4);
    }

    public Frame getFrame() {
        return frame;
    }
    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    public Mount[] getMounts() {
        return mounts;
    }
    public void setMounts(Mount[] mounts) {
        this.mounts = mounts;
    }
    public MechSystem[] getSystems() {
        return systems;
    }
    public void setSystems(MechSystem[] systems) {
        this.systems = systems;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getCurrentStructure() {
        return currentStructure;
    }
    public void setCurrentStructure(int currentStructure) {
        this.currentStructure = currentStructure;
    }
    public int getMaxStructure() {
        return maxStructure;
    }
    public void setMaxStructure(int maxStructure) {
        this.maxStructure = maxStructure;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is less than 0");
        } else if (currentHP > getMaxHP()) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is greater than maxHP value "
                + getMaxHP());
        } else {
            this.currentHP = currentHP;
        }
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public int getCurrentStress() {
        return currentStress;
    }
    public void setCurrentStress(int currentStress) {
        this.currentStress = currentStress;
    }
    public int getMaxStress() {
        return maxStress;
    }
    public void setMaxStress(int maxStress) {
        this.maxStress = maxStress;
    }
    public int getCurrentHeatCapacity() {
        return currentHeatCapacity;
    }
    public void setCurrentHeatCapacity(int currentHeatCapacity) {
        this.currentHeatCapacity = currentHeatCapacity;
    }
    public int getMaxHeatCapacity() {
        return maxHeatCapacity;
    }
    public void setMaxHeatCapacity(int maxHeatCapacity) {
        this.maxHeatCapacity = maxHeatCapacity;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public void setEDefense(int eDefense) {
        this.eDefense = eDefense;
    }
    public int getTechAttack() {
        return techAttack;
    }
    public void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    public int getSensors() {
        return sensors;
    }
    public void setSensors(int sensors) {
        this.sensors = sensors;
    }
    public int getCurrentRepairCapacity() {
        return currentRepairCapacity;
    }
    public void setCurrentRepairCapacity(int currentRepairCapacity) {
        this.currentRepairCapacity = currentRepairCapacity;
    }
    public int getMaxRepairCapacity() {
        return maxRepairCapacity;
    }
    public void setMaxRepairCapacity(int maxRepairCapacity) {
        this.maxRepairCapacity = maxRepairCapacity;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public void setSaveTarget(int saveTarget) {
        this.saveTarget = saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    public void setSystemPoints(int systemPoints) {
        this.systemPoints = systemPoints;
    }
    public int getLimitedSystemsBonus() {
        return limitedSystemsBonus;
    }
    public void setLimitedSystemsBonus(int limitedSystemsBonus) {
        this.limitedSystemsBonus = limitedSystemsBonus;
    }

    public void calculateAttributes(int[] mechSkills) throws Exception {
        // TODO: fill out
        // Calculate this mech's attributes based off of the frame template
        //     provided
        if (frame == null) {
            throw new Exception("calculateAttributes() was called while"
                + " frame was set to null");
        }
        // Hull
        maxHP = frame.getHP() + (mechSkills[0] * 2);
        currentHP = maxHP;
        maxRepairCapacity = frame.getRepairCapacity() + (mechSkills[0] / 2);

        // Agility
        evasion = frame.getEvasion() + mechSkills[1];
        speed = frame.getSpeed() + (mechSkills[1] / 2);

        // Systems
        eDefense = frame.getEDefense() + mechSkills[2];
        techAttack = frame.getTechAttack() + mechSkills[2];
        systemPoints = frame.getSystemPoints() + (mechSkills[2] / 2);

        // Engineering
        maxHeatCapacity = frame.getHeatCapacity() + mechSkills[3];
        currentHeatCapacity = 0;
        limitedSystemsBonus = mechSkills[3] / 2;
    }
    public String outputStats(String outputType) {
        // TODO: fill out
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            outputString += "  STRUCTURE:4 HP:19 ARMOR:0\n";
            outputString += "  STRESS:4 HEATCAP:6 REPAIR:7\n";
            outputString += "  TECH ATK:+6 LIMITED:+1\n";
            outputString += "  SPD:6 EVA:10 EDEF:13 SENSE:20 SAVE:15\n";
        } else if (outputType.equals("full")) {
            outputString += " SIZE:1\n";
            outputString += "  STRUCTURE:2/4 HP:19/19 ARMOR:0\n";
            outputString += "  STRESS:3/4 HEAT:0/6 REPAIR:7/7\n";
            outputString += "  ATK BONUS:5 TECH ATK:6 LTD BONUS:1\n";
            outputString += "  SPD:6 EVA:10 EDEF:13 SENS:20 SAVE:15\n";
        }

        return outputString;
    }
    public String outputWeapons(String outputType) {
        // output something along the lines of:
        // "  MAIN MOUNT: Vulture DMR // Overpower Caliber\n"
        // or "  INTEGRATED WEAPON: Nexus (Light)\n"
        String outputString = "";

        if (mounts == null || mounts.length == 0) {
            return outputString;
        }
        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")
            || outputType.equals("full")) {
            for (int i = 0; i < mounts.length; i++) {
                outputString += "  ";
                outputString += mounts[i].outputWeapon();
                outputString += "\n";
            }
        }

        return outputString;
    }
    public String outputSystems(String outputType) {
        String outputString = "";

        if (systems == null || systems.length == 0) {
            return outputString;
        }
        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            // output something like:
            //     "  Pattern-A Smoke Charges x4, Seismic Ripper,"
            //     " High-Stress Mag Clamps, ATHENA-Class NHP"
            for (int i = 0; i < systems.length; i++) {
                if (i == 0) {
                    outputString += "  ";
                }
                outputString += outputSystem(outputType, systems[i]);
                if (i != systems.length - 1) {
                    outputString += ", ";
                }
            }
        } else if (outputType.equals("full")) {
            // output something along the lines of:
            //       "  Ammo Case II, Pattern-A Smoke Charges,\n"
            //     + "  Seismic Ripper, High-Stress Mag Clamps"
            for (int i = 0; i < systems.length; i += 2) {
                outputString += "  ";
                for (int ii = i; ii < Math.min(systems.length, i + 2); ii++) {
                    outputString += outputSystem(outputType, systems[ii]);
                    if (ii == i && ii + 2 < systems.length) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < systems.length) {
                    outputString += ",\n";
                }
            }
        }

        return outputString;
    }
    public String outputSystem(String outputType, MechSystem system) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("full")) {
            // output something like "Pattern-A Smoke Charges"
            outputString += system.outputSystem(outputType);
        }
        if (outputType.equals("mech build") && system.isLimited()) {
            // add something like " x4"
            outputString += " x" + (system.getLimitedCharges()
                + limitedSystemsBonus);
        }

        return outputString;
    }
}
