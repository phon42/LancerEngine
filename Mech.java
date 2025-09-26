/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its mounts, and its
 *     systems, among other statistics.
 */
public class Mech {
    // name of this mech (NOT its frame name, the name given to that specific
    //     chassis)
    private String name;
    
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

    public String getName() {
        return name;
    }
    public Frame getFrame() {
        return frame;
    }
    public Mount[] getMounts() {
        return mounts;
    }
    public MechSystem[] getSystems() {
        return systems;
    }
    public int getSize() {
        return size;
    }
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
    public int getCurrentStress() {
        return currentStress;
    }
    public int getMaxStress() {
        return maxStress;
    }
    public int getCurrentHeatCapacity() {
        return currentHeatCapacity;
    }
    public int getMaxHeatCapacity() {
        return maxHeatCapacity;
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
    public int getCurrentRepairCapacity() {
        return currentRepairCapacity;
    }
    public int getMaxRepairCapacity() {
        return maxRepairCapacity;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    public int getLimitedSystemsBonus() {
        return limitedSystemsBonus;
    }
    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMounts(Mount[] mounts) {
        this.mounts = mounts;
    }
    public void setSystems(MechSystem[] systems) {
        this.systems = systems;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setCurrentStructure(int currentStructure) {
        this.currentStructure = currentStructure;
    }
    public void setMaxStructure(int maxStructure) {
        this.maxStructure = maxStructure;
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
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setCurrentStress(int currentStress) {
        this.currentStress = currentStress;
    }
    public void setMaxStress(int maxStress) {
        this.maxStress = maxStress;
    }
    public void setCurrentHeatCapacity(int currentHeatCapacity) {
        this.currentHeatCapacity = currentHeatCapacity;
    }
    public void setMaxHeatCapacity(int maxHeatCapacity) {
        this.maxHeatCapacity = maxHeatCapacity;
    }
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setEDefense(int eDefense) {
        this.eDefense = eDefense;
    }
    public void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    public void setSensors(int sensors) {
        this.sensors = sensors;
    }
    public void setCurrentRepairCapacity(int currentRepairCapacity) {
        this.currentRepairCapacity = currentRepairCapacity;
    }
    public void setMaxRepairCapacity(int maxRepairCapacity) {
        this.maxRepairCapacity = maxRepairCapacity;
    }
    public void setSaveTarget(int saveTarget) {
        this.saveTarget = saveTarget;
    }
    public void setSystemPoints(int systemPoints) {
        this.systemPoints = systemPoints;
    }
    public void setLimitedSystemsBonus(int limitedSystemsBonus) {
        this.limitedSystemsBonus = limitedSystemsBonus;
    }

    public void calculateAttributes(int[] mechSkills) {
        // TODO: fill out
        // Calculate this mech's attributes based off of the frame template
        //     provided
        if (frame == null) {
            throw new IllegalArgumentException("calculateAttributes() was called while"
                + " frame was set to null");
        }
        // Hull
        setMaxHP(frame.getHP() + (mechSkills[0] * 2));
        setCurrentHP(maxHP);
        setMaxRepairCapacity(frame.getRepairCapacity() + (mechSkills[0] / 2));

        // Agility
        setEvasion(frame.getEvasion() + mechSkills[1]);
        setSpeed(frame.getSpeed() + (mechSkills[1] / 2));

        // Systems
        setEDefense(frame.getEDefense() + mechSkills[2]);
        setTechAttack(frame.getTechAttack() + mechSkills[2]);
        setSystemPoints(frame.getSystemPoints() + (mechSkills[2] / 2));

        // Engineering
        setMaxHeatCapacity(frame.getHeatCapacity() + mechSkills[3]);
        setCurrentHeatCapacity(0);
        setLimitedSystemsBonus(mechSkills[3] / 2);
    }
    public String generateOutput(String outputType) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            outputString += outputStats("mech build");
            outputString += "[ WEAPONS ]\n";
            outputString += outputWeapons("mech build");
            outputString += "[ SYSTEMS ]\n";
            outputString += outputSystems("mech build");
        } else if (outputType.equals("full")) {
            throw new IllegalArgumentException("Called"
                + " Mech.generateOutput(\"full\") but mech skills value was not"
                + " provided.");
        }

        return outputString;
    }
    public String generateOutput(String outputType, int[] mechSkills) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            return generateOutput(outputType);
        } else if (outputType.equals("full")) {
            outputString += "[ MECH ]\n";
            outputString += "  « " + getName() + " »\n";
            outputString += "  " + getFrame().getManufacturer() + " "
                + getFrame().getFrameName() + "\n";
            outputString += "  H:" + mechSkills[0] + " A:" + mechSkills[1]
                + " S:" + mechSkills[2] + " E:" + mechSkills[3]
                + outputStats("full");
            outputString += "[ WEAPONS ]\n";
            outputString += outputWeapons("full");
            outputString += "[ SYSTEMS ]\n";
            outputString += outputSystems("full");
        }

        return outputString;
    }
    public String outputStats(String outputType) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            outputString += "  STRUCTURE:" + getMaxStructure() + " HP:"
                + getMaxHP() + " ARMOR:" + getArmor() + "\n";
            outputString += "  STRESS:" + getMaxStress() + " HEATCAP:"
                + getMaxHeatCapacity() + " REPAIR:" + getMaxRepairCapacity()
                + "\n";
            outputString += "  TECH ATK:+" + getTechAttack() + " LIMITED:+"
                + getLimitedSystemsBonus() + "\n";
            outputString += "  SPD:" + getSpeed() + " EVA:" + getEvasion()
                + " EDEF:" + getEDefense() + " SENSE:" + getSensors() + " SAVE:"
                + getSaveTarget() + "\n";
        } else if (outputType.equals("full")) {
            throw new IllegalArgumentException("Called"
                + " Mech.outputStats(\"full\") but grit value was not"
                + " provided");
        }

        return outputString;
    }
    public String outputStats(String outputType, int grit) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            return outputStats(outputType);
        } else if (outputType.equals("full")) {
            outputString += " SIZE:" + outputSize() + "\n";
            outputString += "  STRUCTURE:" + getCurrentStructure() + "/"
                + getMaxStructure() + " HP:" + getCurrentHP() + "/" + getMaxHP()
                + " ARMOR:" + getArmor() + "\n";
            outputString += "  STRESS:" + getCurrentStress() + "/"
                + getMaxStress() + " HEAT:" + getCurrentHeatCapacity() + "/"
                + getMaxHeatCapacity() + " REPAIR:" + getCurrentRepairCapacity()
                + "/" + getMaxRepairCapacity() + "\n";
            outputString += "  ATK BONUS:" + grit + " TECH ATK:"
                + getTechAttack() + " LTD BONUS:" + getLimitedSystemsBonus()
                + "\n";
            outputString += "  SPD:" + getSpeed() + " EVA:" + getEvasion()
                + " EDEF:" + getEDefense() + " SENS:" + getSensors() + " SAVE:"
                + getSaveTarget() + "\n";
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
    public String outputSize() {
        if (getSize() == 1) {
            return "1/2";
        }
        return Integer.toString(getSize() / 2);
    }
}