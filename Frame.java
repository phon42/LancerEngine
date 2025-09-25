/**
 * Represents a frame (a pattern or statblock that can be copied to form mechs).
 *     Stores information about the frame such as its manufacturer, frame name,
 *     role, and stats.
 */
public class Frame {
    // frame manufacturer (i.e. GMS), case-sensitive
    private String manufacturer;
    // frame name (i.e. Everest), case-sensitive
    private String frameName;
    // role (i.e. striker)
    /**
     * Must be one of the following values: "artillery", "balanced",
     *     "controller", "striker", "support".
     * Is case-insensitive and stored in all lowercase.
     */
    private String[] role;
    // the list of allowed values for the role variable
    final String[] allowedRoles = new String[] {
        "artillery", "balanced", "controller", "striker", "support"
    };
    // desc at the top of the page (i.e. "Most humans don’t...")
    /**
     * Is case-sensitive.
     */
    private String frameDescription;

    // frame attributes - size, structure, HP, etc.
    /**
     * Represents the size of the mech.
     * Size is stored as 2 * its value (Size 1/2 would be stored as int 1).
     * Allowed values for size: -1 (placeholder), 1, 2, 4, 6, 8.
     */
    private int size;

    // health and structure
    /**
     * Must be a minimum of -1 (placeholder). Cannot be 0.
     */
    private int structure;
    /**
     * Must be a minimum of -1 (placeholder). Cannot be 0.
     */
    private int HP;
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int armor;

    // heat and stress
    /**
     * Must be a minimum of -1 (placeholder). Cannot be 0.
     */
    private int stress;
    /**
     * Must be a minimum of -1 (placeholder). Cannot be 0.
     */
    private int heatCapacity;

    // evasion and speed
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int evasion;
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int speed;

    // e-defense and tech attack
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int eDefense;
    /**
     * Can be any integer.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * Must be a minimum of -1 (placeholder). Cannot be 0.
     */
    private int sensors;
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int repairCapacity;

    // save target and system points
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int saveTarget;
    /**
     * Must be a minimum of -1 (placeholder).
     */
    private int systemPoints;

    // frame traits
    private String[] traits;

    // weapon mounts
    private Mount[] mounts;

    // TODO: fill out
    // core system
    // core system description
    // core system passive
    // core system active

    public Frame() {
        // this can be changed but 90% of frames (100% of player frames) have
        //     4 structure, 4 stress
        setStructure(4);
        setStress(4);
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        if (manufacturer == null) {
            throw new IllegalArgumentException("New manufacturer value is"
                + " null");
        }
        this.manufacturer = manufacturer;
    }
    public String getFrameName() {
        return frameName;
    }
    public void setFrameName(String frameName) {
        if (frameName == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        this.frameName = frameName;
    }
    public String[] getRole() {
        return role;
    }
    public void setRole(String[] role) {
        boolean isValidRole = false;

        for (String roleString : role) {
            isValidRole = false;
            if (roleString == null) {
                throw new IllegalArgumentException("New role array contains"
                    + " a role value of null");
            }
            for (String roleMatch : allowedRoles) {
                if (roleString.equals(roleMatch)) {
                    isValidRole = true;
                }
            }
            if (! isValidRole) {
                throw new IllegalArgumentException("New role set contains an "
                    + "illegal role value: \"" + roleString + "\"");
            }
        }
        this.role = role;
    }
    public String getFrameDescription() {
        return frameDescription;
    }
    public void setFrameDescription(String frameDescription) {
        if (frameDescription == null) {
            throw new IllegalArgumentException("New frame description is"
                + " null");
        }
        this.frameDescription = frameDescription;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        if (size < -1) {
            throw new IllegalArgumentException("New frame size is < -1");
        }
        if (size == 0) {
            throw new IllegalArgumentException("New frame size is 0");
        }
        if (size > 8) {
            throw new IllegalArgumentException("New frame size is > 8");
        }
        if (size == 3 || size == 5 || size == 7) {
            throw new IllegalArgumentException("New frame size is an invalid "
                + "value: " + size);
        }
        this.size = size;
    }
    public int getStructure() {
        return structure;
    }
    public void setStructure(int structure) {
        if (structure < -1) {
            throw new IllegalArgumentException(
                "New structure value is < -1");
        }
        if (structure == 0) {
            throw new IllegalArgumentException("New structure value is 0");
        }
        this.structure = structure;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        if (HP < -1) {
            throw new IllegalArgumentException("New HP value is < -1");
        }
        if (HP == 0) {
            throw new IllegalArgumentException("New HP value is 0");
        }
        this.HP = HP;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        if (armor < -1) {
            throw new IllegalArgumentException("New armor value is < -1");
        }
        this.armor = armor;
    }
    public int getStress() {
        return stress;
    }
    public void setStress(int stress) {
        if (stress < -1) {
            throw new IllegalArgumentException("New stress value is < -1");
        }
        if (stress == 0) {
            throw new IllegalArgumentException("New stress value is 0");
        }
        this.stress = stress;
    }
    public int getHeatCapacity() {
        return heatCapacity;
    }
    public void setHeatCapacity(int heatCapacity) {
        if (heatCapacity < -1) {
            throw new IllegalArgumentException("New heat capacity value is < "
                + "-1");
        }
        if (heatCapacity == 0) {
            throw new IllegalArgumentException(
                "New heat capacity value is 0");
        }
        this.heatCapacity = heatCapacity;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int evasion) {
        if (evasion < -1) {
            throw new IllegalArgumentException("New evasion value is < -1");
        }
        this.evasion = evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        if (speed < -1) {
            throw new IllegalArgumentException("New speed value is < -1");
        }
        this.speed = speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public void setEDefense(int eDefense) {
        if (eDefense < -1) {
            throw new IllegalArgumentException(
                "New e-defense value is < -1");
        }
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
        if (sensors < -1) {
            throw new IllegalArgumentException("New sensors value is < -1");
        }
        if (sensors == 0) {
            throw new IllegalArgumentException("New sensors value is 0");
        }
        this.sensors = sensors;
    }
    public int getRepairCapacity() {
        return repairCapacity;
    }
    public void setRepairCapacity(int repairCapacity) {
        if (repairCapacity < -1) {
            throw new IllegalArgumentException("New repair capacity value is "
                + "< -1");
        }
        this.repairCapacity = repairCapacity;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public void setSaveTarget(int saveTarget) {
        if (saveTarget < -1) {
            throw new IllegalArgumentException("New save target value is < "
                + "-1");
        }
        this.saveTarget = saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    public void setSystemPoints(int systemPoints) {
        if (systemPoints < -1) {
            throw new IllegalArgumentException("New system points value is < "
                + "-1");
        }
        this.systemPoints = systemPoints;
    }
    public String[] getTraits() {
        return traits;
    }
    public void setTraits(String[] traits) {
        if (traits == null) {
            throw new IllegalArgumentException("New traits value is null");
        }
        // TODO: does this throw an error if traits.length is 0?
        for (String trait : traits) {
            if (trait == null || trait.equals("")) {
                throw new IllegalArgumentException("New traits value contains"
                    + " a value of \"\" or null");
            }
        }
        this.traits = traits;
    }
    public Mount[] getMounts() {
        return mounts;
    }
    public void setMounts(Mount[] mounts) {
        if (mounts == null) {
            throw new IllegalArgumentException("New mounts value is null");
        }
        for (Mount mount : mounts) {
            if (mount == null) {
                throw new IllegalArgumentException("New mounts list contains"
                    + " a value of null");
            }
        }
        this.mounts = mounts;
    }
}
