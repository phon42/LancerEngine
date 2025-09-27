/**
 * Represents a frame (a pattern or statblock that can be copied to form mechs).
 *     Stores information about the frame such as its manufacturer, frame name,
 *     role, and stats.
 */
public class Frame {
    // frame manufacturer (i.e. GMS), case-sensitive
    private String manufacturer;
    // frame name (i.e. Everest), case-sensitive
    private String name;
    // role (i.e. striker)
    /**
     * Is set to "" at construction but must be one of the following values
     *     otherwise: "artillery", "balanced", "controller", "striker",
     *     "support".
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
     * Is set to -1 at construction but must be a minimum of 1 otherwise.
     */
    private int structure;
    /**
     * Must be a minimum of 1.
     */
    private int HP;
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int armor;

    // heat and stress
    /**
     * Is set to 4 at construction. Must be a minimum of 1.
     */
    private int stress;
    /**
     * Is set to -1 at construction but must be a minimum of 1 otherwise.
     */
    private int heatCapacity;

    // evasion and speed
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int evasion;
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int speed;

    // e-defense and tech attack
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int eDefense;
    /**
     * Set to -1 at construction. Can be any integer.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int sensors;
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int repairCapacity;

    // save target and system points
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
     */
    private int saveTarget;
    /**
     * Is set to -1 at construction but must be a minimum of 0 otherwise.
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
        this.manufacturer = "";
        this.name = "";
        this.role = new String[0];
        this.frameDescription = "";
        this.size = -1;

        // this can be changed but 90% of frames (100% of player frames) have
        //     4 structure, 4 stress
        this.structure = 4;
        this.stress = 4;

        this.HP = -1;
        this.armor = -1;
        this.heatCapacity = -1;
        this.evasion = -1;
        this.speed = -1;
        this.eDefense = -1;
        this.techAttack = -1;
        this.sensors = -1;
        this.repairCapacity = -1;
        this.saveTarget = -1;
        this.systemPoints = -1;
        this.traits = new String[0];
        this.mounts = new Mount[0];
    }
    public Frame(String manufacturer, String name, String[] role,
        String frameDescription, int size, int hp,
        int armor, int heatCapacity, int evasion, int speed, int eDefense,
        int techAttack, int sensors, int repairCapacity, int saveTarget,
        int systemPoints, String[] traits, Mount[] mounts) {
        this(manufacturer, name, role, frameDescription, size, 4, hp,
            armor, 4, heatCapacity, evasion, speed, eDefense, techAttack,
            sensors, repairCapacity, saveTarget, systemPoints, traits, mounts);
    }
    public Frame(String manufacturer, String name, String[] role,
        String frameDescription, int size, int structure, int hp,
        int armor, int stress, int heatCapacity, int evasion, int speed,
        int eDefense, int techAttack, int sensors, int repairCapacity,
        int saveTarget, int systemPoints, String[] traits, Mount[] mounts) {
        setManufacturer(manufacturer);
        setName(name);
        setRole(role);
        setFrameDescription(frameDescription);
        setSize(size);
        setStructure(structure);
        setHP(hp);
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
        setTraits(traits);
        setMounts(mounts);
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        this.name = name;
    }
    public String[] getRole() {
        return role;
    }
    public void setRole(String[] role) {
        boolean isValidRole = false;
        String roleString;

        for (int i = 0; i < role.length; i++) {
            role[i] = role[i].toLowerCase();
            roleString = role[i];
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
        if (frameDescription.equals("")) {
            throw new IllegalArgumentException("New frame description is"
                + " \"\"");
        }
        this.frameDescription = frameDescription;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("New frame size is < 1");
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
        if (structure < 1) {
            throw new IllegalArgumentException(
                "New structure value is < 1");
        }
        this.structure = structure;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        if (HP < 1) {
            throw new IllegalArgumentException("New HP value is < 1");
        }
        this.HP = HP;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value is < 0");
        }
        this.armor = armor;
    }
    public int getStress() {
        return stress;
    }
    public void setStress(int stress) {
        if (stress < 1) {
            throw new IllegalArgumentException("New stress value is < -1");
        }
        this.stress = stress;
    }
    public int getHeatCapacity() {
        return heatCapacity;
    }
    public void setHeatCapacity(int heatCapacity) {
        if (heatCapacity < 1) {
            throw new IllegalArgumentException("New heat capacity value is < "
                + "1");
        }
        this.heatCapacity = heatCapacity;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion value is < 0");
        }
        this.evasion = evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed value is < 0");
        }
        this.speed = speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException(
                "New e-defense value is < 0");
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
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors value is < 0");
        }
        this.sensors = sensors;
    }
    public int getRepairCapacity() {
        return repairCapacity;
    }
    public void setRepairCapacity(int repairCapacity) {
        if (repairCapacity < 0) {
            throw new IllegalArgumentException("New repair capacity value is "
                + "< 0");
        }
        this.repairCapacity = repairCapacity;
    }
    public int getSaveTarget() {
        return saveTarget;
    }
    public void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target value is < "
                + "0");
        }
        this.saveTarget = saveTarget;
    }
    public int getSystemPoints() {
        return systemPoints;
    }
    public void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points value is < "
                + "0");
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

    public boolean hasPlaceholders() {
        if (getManufacturer().equals("")) {
            return true;
        }
        if (getRole().getClass() == String[].class && getRole().length == 0) {
            return true;
        }
        if (getFrameDescription().equals("")) {
            return true;
        }
        if (getSize() == -1) {
            return true;
        }

        if (getHP() == -1) {
            return true;
        }
        if (getArmor() == -1) {
            return true;
        }
        if (getHeatCapacity() == -1) {
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
        if (getRepairCapacity() == -1) {
            return true;
        }
        if (getSaveTarget() == -1) {
            return true;
        }
        if (getSystemPoints() == -1) {
            return true;
        }

        return false;
    }
    public boolean isPlaceholder() {
        if (! getManufacturer().equals("")) {
            return false;
        }
        if (! getName().equals("")) {
            return false;
        }
        if (getRole().getClass() != String[].class || getRole().length != 0) {
            return false;
        }
        if (! getFrameDescription().equals("")) {
            return false;
        }
        if (getSize() != -1) {
            return false;
        }

        if (getStructure() != 4) {
            return false;
        }
        if (getStress() != 4) {
            return false;
        }

        if (getHP() != -1) {
            return false;
        }
        if (getArmor() != -1) {
            return false;
        }
        if (getHeatCapacity() != -1) {
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
        if (getRepairCapacity() != -1) {
            return false;
        }
        if (getSaveTarget() != -1) {
            return false;
        }
        if (getSystemPoints() != -1) {
            return false;
        }
        if (getTraits().getClass() != String[].class
            || getTraits().length != 0) {
            return false;
        }
        if (getMounts().getClass() != Mount[].class
            || getMounts().length != 0) {
            return false;
        }

        return true;
    }
}
