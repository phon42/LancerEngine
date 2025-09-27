/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its stats, its mounts, and
 *     its systems, among other statistics.
 * A Frame object is required to create a non-placeholder Mech object. The Frame
 *     object's stats, traits, and mounts serve as the base on top of which mech
 *     skills, weapons, systems, and other modifications are added.
 */
public class Mech {
    /**
     * The name of this mech (NOT its frame name, the name given to this
     *     specific chassis). Can be any non-"" String. Cannot be null. Set to
     *     "" on construction from Mech().
     */
    private String name;
    
    /**
     * The frame that this mech is patterned after (i.e. Swallowtail) as a Frame
     *     object. Cannot be a placeholder Frame. Set to a placeholder Frame on
     *     construction from Mech().
     */
    private Frame frame;

    /**
     * The operator notes attached to this mech. Can be any String. Cannot be
     *     null.
     */
    private String operatorNotes;
    
    // mounts/weapons
    /**
     * The mech's weapon mounts. Can be any Mount[] that does not contain null.
     *     Cannot be null.
     */
    private Mount[] mounts;

    // systems
    private MechSystem[] systems;

    // frame attributes - size, structure, HP, etc.
    /**
     * Represents the size of the mech.
     * Size is stored as 2 * its value (Size 1/2 would be stored as int 1).
     * Allowed values for size: 1, 2, 4, 6, 8.
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

    /**
     * Creates a placeholder Mech.
     */
    public Mech() {
        this.name = "";
        this.frame = new Frame();
        this.operatorNotes = "";
        this.mounts = new Mount[0];
        this.systems = new MechSystem[0];
        this.size = -1;
        this.currentStructure = 4;
        this.maxStructure = 4;
        this.currentHP = -1;
        this.maxHP = -1;
        this.armor = -1;
        this.currentStress = 4;
        this.maxStress = 4;
        this.currentHeatCapacity = -1;
        this.maxHeatCapacity = -1;
        this.evasion = -1;
        this.speed = -1;
        this.eDefense = -1;
        this.techAttack = -1;
        this.sensors = -1;
        this.currentRepairCapacity = -1;
        this.maxRepairCapacity = -1;
        this.saveTarget = -1;
        this.systemPoints = -1;
        this.limitedSystemsBonus = -1;
    }
    /**
     * Creates a non-placeholder Mech from a mech name and a frameID.
     * @param name a String representing the mech name of the new Mech.
     * @param frameID a String representing the frameID of the Frame to use to
     *     create the new Mech.
     */
    public Mech(String name, String frameID) {
        this();
        setName(name);
        setFrame(FrameDatabase.getFrame(frameID));
    }
    /**
     * Creates a non-placeholder Mech from a mech name and a FrameEnum.
     * @param name a String representing the mech name of the new Mech.
     * @param frameEnum a FrameEnum representing the FrameEnum of the Frame to
     *     use to create the new Mech.
     */
    public Mech(String name, FrameEnum frameEnum) {
        this();
        setName(name);
        setFrame(FrameDatabase.getFrame(frameEnum));
    }

    public String getName() {
        return name;
    }
    public Frame getFrame() {
        return frame;
    }
    public String getOperatorNotes() {
        return operatorNotes;
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
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New name is \"\"");
        }
        this.name = name;
    }
    public void setFrame(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New frame is null");
        }
        this.frame = frame;
        calculateAttributes(new int[4]);
        setMounts(frame.getMounts());
    }
    public void setOperatorNotes(String operatorNotes) {
        if (operatorNotes == null) {
            throw new IllegalArgumentException("New operator notes are null");
        }
        this.operatorNotes = operatorNotes;
    }
    private void setMounts(Mount[] mounts) {
        if (mounts == null) {
            throw new IllegalArgumentException("New mounts value is null");
        }
        for (Mount mount : mounts) {
            if (mount == null) {
                throw new IllegalArgumentException("New mounts value contains"
                    + " a null element");
            }
        }
        this.mounts = mounts;
    }
    public void setMount(int mountIndex, Mount mount) {
        if (mountIndex < 0) {
            throw new IllegalArgumentException("mountIndex:" + mountIndex
                + " is out of bounds for length " + getMounts().length);
        }
        if (mountIndex >= getMounts().length) {
            throw new IllegalArgumentException("mountIndex:" + mountIndex
                + " is out of bounds for length " + getMounts().length);
        }
        if (mount.getMountType().equals("")) {
            // the user was lazy and used new Mount(Weapon) instead of
            //     new Mount(mountType, Weapon)
            String mountType = getMounts()[mountIndex].getMountType();
            mount = new Mount(mountType, mount.getWeapon());
        }
        this.mounts[mountIndex] = mount;
    }
    public void setSystems(MechSystem[] systems) {
        this.systems = systems;
    }
    public void setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("New size is < 1");
        }
        this.size = size;
    }
    public void setCurrentStructure(int currentStructure) {
        if (currentStructure < 1) {
            throw new IllegalArgumentException("New current structure is"
                + " < 1");
        }
        this.currentStructure = currentStructure;
    }
    public void setMaxStructure(int maxStructure) {
        if (maxStructure < 1) {
            throw new IllegalArgumentException("New max structure is < 1");
        }
        this.maxStructure = maxStructure;
    }
    public void setCurrentHP(int currentHP) {
        if (currentHP < 1) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is < 1");
        } else if (currentHP > getMaxHP()) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is > maxHP value "
                + getMaxHP());
        } else {
            this.currentHP = currentHP;
        }
    }
    public void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New max HP is < 1");
        }
        this.maxHP = maxHP;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor is < 0");
        }
        this.armor = armor;
    }
    public void setCurrentStress(int currentStress) {
        if (currentStress < 1) {
            throw new IllegalArgumentException("New current stress is < 1");
        }
        this.currentStress = currentStress;
    }
    public void setMaxStress(int maxStress) {
        if (maxStress < 1) {
            throw new IllegalArgumentException("New max stress is < 1");
        }
        this.maxStress = maxStress;
    }
    public void setCurrentHeatCapacity(int currentHeatCapacity) {
        if (currentHeatCapacity < 0) {
            throw new IllegalArgumentException("New current heat capacity is"
                + " < 0");
        }
        this.currentHeatCapacity = currentHeatCapacity;
    }
    public void setMaxHeatCapacity(int maxHeatCapacity) {
        if (maxHeatCapacity < 1) {
            throw new IllegalArgumentException("New max heat capacity is"
                + " < 1");
        }
        this.maxHeatCapacity = maxHeatCapacity;
    }
    public void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion is < 0");
        }
        this.evasion = evasion;
    }
    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed is < 0");
        }
        this.speed = speed;
    }
    public void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException("New e-defense is < 0");
        }
        this.eDefense = eDefense;
    }
    public void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    public void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors is < 0");
        }
        this.sensors = sensors;
    }
    public void setCurrentRepairCapacity(int currentRepairCapacity) {
        if (currentRepairCapacity < 0) {
            throw new IllegalArgumentException("New current repair capacity"
                + " is < 0");
        }
        this.currentRepairCapacity = currentRepairCapacity;
    }
    public void setMaxRepairCapacity(int maxRepairCapacity) {
        if (maxRepairCapacity < 0) {
            throw new IllegalArgumentException("New max repair capacity is"
                + " < 0");
        }
        this.maxRepairCapacity = maxRepairCapacity;
    }
    public void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target is < 0");
        }
        this.saveTarget = saveTarget;
    }
    public void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points are < 0");
        }
        this.systemPoints = systemPoints;
    }
    public void setLimitedSystemsBonus(int limitedSystemsBonus) {
        if (limitedSystemsBonus < 0) {
            throw new IllegalArgumentException("New limited systems bonus is"
                + " < 0");
        }
        this.limitedSystemsBonus = limitedSystemsBonus;
    }

    public void calculateAttributes(int[] mechSkills) {
        // Calculate this mech's attributes based off of the frame template
        //     provided
        if (frame == null) {
            throw new IllegalArgumentException("calculateAttributes() was"
                + " called while frame was set to null");
        }
        // Hull
        setMaxHP(frame.getHP() + (mechSkills[0] * 2));
        setCurrentHP(maxHP);
        setMaxRepairCapacity(frame.getRepairCapacity() + (mechSkills[0] / 2));
        setCurrentRepairCapacity(getMaxRepairCapacity());

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
        
        setSize(frame.getSize());
        setArmor(frame.getArmor());
        setSensors(frame.getSensors());
        setSaveTarget(frame.getSaveTarget());
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
    public String generateOutput(String outputType, int[] mechSkills, int grit) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            return generateOutput(outputType);
        } else if (outputType.equals("full")) {
            outputString += "[ MECH ]\n";
            if (isPlaceholder()) {
                outputString += "  « N/A »\n";
                outputString += "  N/A N/A\n";
                outputString += "  H:" + mechSkills[0] + " A:" + mechSkills[1]
                    + " S:" + mechSkills[2] + " E:" + mechSkills[3]
                    + outputStats("full", grit);
            } else {
                String manufacturer = getFrame().getManufacturer();
                outputString += "  « " + getName() + " »\n";
                outputString += "  " + manufacturer + " "
                    + getFrame().outputName() + "\n";
                outputString += "  H:" + mechSkills[0] + " A:" + mechSkills[1]
                    + " S:" + mechSkills[2] + " E:" + mechSkills[3]
                    + outputStats("full", grit);
            }
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
            if (getTechAttack() >= 0) {
                outputString += "  TECH ATK:+" + getTechAttack();
            } else {
                outputString += "  TECH ATK:" + getTechAttack();
            }
            if (getLimitedSystemsBonus() >= 0) {
                outputString += " LIMITED:+" + getLimitedSystemsBonus();
            } else {
                outputString += " LIMITED:" + getLimitedSystemsBonus();
            }
            outputString += "\n";
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
            outputString += "  N/A\n";
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
            outputString += "  N/A\n";
            return outputString;
        }
        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            // output something like:
            //     "  Pattern-A Smoke Charges x4, Seismic Ripper,"
            //     " High-Stress Mag Clamps, ATHENA-Class NHP\n"
            for (int i = 0; i < systems.length; i++) {
                if (i == 0) {
                    outputString += "  ";
                }
                outputString += outputSystem(outputType, systems[i]);
                if (i != systems.length - 1) {
                    outputString += ", ";
                }
            }
            outputString += "\n";
        } else if (outputType.equals("full")) {
            // output something along the lines of:
            //       "  Ammo Case II, Pattern-A Smoke Charges,\n"
            //     + "  Seismic Ripper, High-Stress Mag Clamps\n"
            for (int i = 0; i < systems.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(systems.length, i + 2); j++) {
                    outputString += outputSystem(outputType, systems[j]);
                    if (j == i && j + 2 < systems.length) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < systems.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    public String outputSystem(String outputType, MechSystem system) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("full")
            || outputType.equals("mech build")) {
            // output something like "Pattern-A Smoke Charges"
            outputString += system.outputSystem(outputType);
        }
        if (outputType.equals("mech build") && system.isLimited()) {
            // add something like " x4"
            outputString += " x" + (system.getLimitedCharges()
                + getLimitedSystemsBonus());
        }

        return outputString;
    }
    public String outputSize() {
        if (getSize() == 1) {
            return "1/2";
        }
        if (getSize() > 1) {
            return Integer.toString(getSize() / 2);
        }
        return Integer.toString(getSize());
    }
    /**
     * A method checking whether any of the properties of this object whose
     *     placeholder value is normally not allowed are set to their
     *     placeholder value.
     * @return a boolean representing the result of the check.
     */
    public boolean hasPlaceholders() {
        if (getName().equals("")) {
            return true;
        }
        if (getFrame().isPlaceholder()) {
            return true;
        }
        if (getSize() == -1) {
            return true;
        }
        if (getCurrentHP() == -1) {
            return true;
        }
        if (getMaxHP() == -1) {
            return true;
        }
        if (getArmor() == -1) {
            return true;
        }
        if (getCurrentHeatCapacity() == -1) {
            return true;
        }
        if (getMaxHeatCapacity() == -1) {
            return true;
        }
        if (getEvasion() == -1) {
            return true;
        }
        if (getSpeed() == -1) {
            return true;
        }
        if (getEDefense() == -1) {
            return true;
        }
        if (getSensors() == -1) {
            return true;
        }
        if (getCurrentRepairCapacity() == -1) {
            return true;
        }
        if (getMaxRepairCapacity() == -1) {
            return true;
        }
        if (getSaveTarget() == -1) {
            return true;
        }
        if (getSystemPoints() == -1) {
            return true;
        }
        if (getLimitedSystemsBonus() == -1) {
            return true;
        }

        return false;
    }
    public boolean isPlaceholder() {
        if (! getName().equals("")) {
            return false;
        }
        if (! getFrame().isPlaceholder()) {
            return false;
        }
        if (! getOperatorNotes().equals("")) {
            return false;
        }
        if (getMounts().length != 0) {
            return false;
        }
        if (getSystems().length != 0) {
            return false;
        }
        if (getSize() != -1) {
            return false;
        }
        if (getCurrentStructure() != 4) {
            return false;
        }
        if (getMaxStructure() != 4) {
            return false;
        }
        if (getCurrentHP() != -1) {
            return false;
        }
        if (getArmor() != -1) {
            return false;
        }
        if (getCurrentStress() != 4) {
            return false;
        }
        if (getMaxStress() != 4) {
            return false;
        }
        if (getCurrentHeatCapacity() != -1) {
            return false;
        }
        if (getMaxHeatCapacity() != -1) {
            return false;
        }
        if (getEvasion() != -1) {
            return false;
        }
        if (getSpeed() != -1) {
            return false;
        }
        if (getEDefense() != -1) {
            return false;
        }
        if (getTechAttack() != -1) {
            return false;
        }
        if (getSensors() != -1) {
            return false;
        }
        if (getCurrentRepairCapacity() != -1) {
            return false;
        }
        if (getMaxRepairCapacity() != -1) {
            return false;
        }
        if (getSaveTarget() != -1) {
            return false;
        }
        if (getSystemPoints() != -1) {
            return false;
        }
        if (getLimitedSystemsBonus() != -1) {
            return false;
        }

        return true;
    }
}