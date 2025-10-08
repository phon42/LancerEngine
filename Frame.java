/**
 * Represents a frame (a pattern or statblock that can be copied to form mechs).
 *     Stores information about the frame such as its manufacturer, frame name,
 *     role, and stats.
 * A Frame object is needed to create a non-placeholder Mech object. Its stats,
 *     traits, and mounts serve as the base on top of which mech skills,
 *     weapons, systems, and other modifications are added.
 * Safety: This class has placeholder values and can be a placeholder, but in
 *     most cases should either have no placeholder values or be a placeholder,
 *     which must be checked for. None of its properties have allowed values of
 *     null.
 */
public class Frame {
    /**
     * The frame's manufacturer (i.e. "GMS").
     * Case-insensitive and stored in uppercase. Can be any String except "".
     *     Cannot be null. Is set to "" on construction.
     */
    private String manufacturer;
    /**
     * The frame's name (i.e. "everest").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null. Is set to "" on construction.
     * Use Frame.getName() to get the raw value and Frame.outputName() to obtain
     *     it properly formatted.
     */
    private String name;
    /**
     * The frame's ID (i.e. "swallowtail_ranger").
     * Used for identifying it in FrameDatabase.getFrame(String).
     *     Case-insensitive and stored in lowercase. Can be any String except
     *     "". Cannot be null. Is set to "" on construction.
     */
    private String ID;
    /**
     * The frame's frameEnum (i.e. FrameEnum.SWALLOWTAIL_RANGER).
     * Used for identifying it in FrameDatabase.getFrame(FrameEnum). Cannot be
     *     null. Is set to null on construction.
     */
    private FrameEnum frameEnum;
    /**
     * The frame's role (i.e. "balanced"). Multiple items are stored as seperate
     *     elements (i.e "Controller/Support" would be stored as {"controller",
     *     "support"}).
     * Each element must be one of the following values:
     *     "artillery", "balanced", "controller", "striker", "support".
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String[] role;
    /**
     * An array of allowed values for the elements of the role property.
     */
    public static final String[] allowedRoles = new String[] {
        "artillery", "balanced", "controller", "defender", "striker", "support"
    };
    /**
     * The description at the top of a frame's page on COMP/CON (i.e. "Most
     *     humans don’t...").
     * Can be any String. Cannot be null.
     */
    private String frameDescription;

    // frame attributes - size, structure, HP, etc.
    /**
     * The frame's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Is set to -1 on construction.
     */
    private int size;

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
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
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
     * Is set to -1 at construction, but must be a minimum of 1 otherwise.
     */
    private int heatCapacity;

    // evasion and speed
    /**
     * The frame's evasion value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int evasion;
    /**
     * The frame's speed value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int speed;

    // e-defense and tech attack
    /**
     * The frame's e-defense value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int eDefense;
    /**
     * The frame's tech attack value.
     * Can be any integer.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * The frame's sensors value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int sensors;
    /**
     * The frame's max repair capacity value.
     * Is set to -1 at construction ,but must be a minimum of 0 otherwise.
     */
    private int repairCapacity;

    // save target and system points
    /**
     * The frame's save target value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int saveTarget;
    /**
     * The frame's system points value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int systemPoints;

    /**
     * The frame's traits (i.e. {"Initiative", "Replaceable Parts"}).
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     */
    private String[] traits;

    /**
     * The frame's weapon mounts.
     * Can be any Mount[] that does not contain null elements or elements that
     *     are placeholders. Cannot be null.
     */
    private Mount[] mounts;

    // TODO: fill out
    // core system
    // core system description
    // core system passive
    // core system active

    /**
     * Creates a new placeholder Frame.
     */
    public Frame() {
        this.manufacturer = "";
        this.name = "";
        this.ID = "";
        // this.frameEnum not set due to no possible placeholder value
        this.role = new String[0];
        this.frameDescription = "";
        this.size = -1;

        // these properties can be modified later on but 90% of frames (100% of
        //     player frames) have 4 structure, 4 stress
        this.structure = 4;
        this.stress = 4;

        this.HP = -1;
        this.armor = -1;
        this.heatCapacity = -1;
        this.evasion = -1;
        this.speed = -1;
        this.eDefense = -1;
        setTechAttack(-1);
        this.sensors = -1;
        this.repairCapacity = -1;
        this.saveTarget = -1;
        this.systemPoints = -1;
        this.traits = new String[0];
        this.mounts = new Mount[0];
    }
    /**
     * Creates a new non-placeholder Frame using every property except Structure
     *     and Stress, which it automatically sets to 4. Helpful for player
     *     frames.
     */
    public Frame(String manufacturer, String name, String frameID,
        FrameEnum frameEnum, String[] role, String frameDescription, int size,
        int hp, int armor, int heatCapacity, int evasion, int speed,
        int eDefense, int techAttack, int sensors, int repairCapacity,
        int saveTarget, int systemPoints, String[] traits, Mount[] mounts) {
        this(manufacturer, name, frameID, frameEnum, role, frameDescription,
            size, 4, hp, armor, 4, heatCapacity, evasion,
            speed, eDefense, techAttack, sensors, repairCapacity, saveTarget,
            systemPoints, traits, mounts);
    }
    /**
     * Creates a new non-placeholder Frame using every possible property.
     */
    public Frame(String manufacturer, String name, String frameID,
        FrameEnum frameEnum, String[] role, String frameDescription, int size,
        int structure, int hp, int armor, int stress, int heatCapacity,
        int evasion, int speed, int eDefense, int techAttack, int sensors,
        int repairCapacity, int saveTarget, int systemPoints, String[] traits,
        Mount[] mounts) {
        setManufacturer(manufacturer);
        setName(name);
        setID(frameID);
        setFrameEnum(frameEnum);
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
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public FrameEnum getFrameEnum() {
        return frameEnum;
    }
    public String[] getRole() {
        return HelperFunctions.copyOf(role);
    }
    public String getFrameDescription() {
        return frameDescription;
    }
    public int getSize() {
        return size;
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
    public String[] getTraits() {
        return HelperFunctions.copyOf(traits);
    }
    public Mount[] getMounts() {
        return HelperFunctions.copyOf(mounts);
    }
    public void setManufacturer(String manufacturer) {
        if (manufacturer == null) {
            throw new IllegalArgumentException("New manufacturer value is"
                + " null");
        }
        if (manufacturer.equals("")) {
            throw new IllegalArgumentException("New manufacturer value is"
                + " \"\"");
        }
        manufacturer = manufacturer.toUpperCase();
        this.manufacturer = manufacturer;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        name = name.toLowerCase();
        this.name = name;
    }
    public void setID(String ID) {
        if (ID == null) {
            throw new IllegalArgumentException("New frame ID is null");
        }
        if (ID.equals("")) {
            throw new IllegalArgumentException("New frame ID is \"\"");
        }
        ID = ID.toLowerCase();
        this.ID = ID;
    }
    /**
     * Sets this.frameEnum to the provided value.
     * @param frameEnum a FrameEnum which cannot be null.
     */
    public void setFrameEnum(FrameEnum frameEnum) {
        if (frameEnum == null) {
            throw new IllegalArgumentException("New frame enum is null");
        }
        this.frameEnum = frameEnum;
    }
    /**
     * Sets this.role to the value provided.
     * @param role a String[] which cannot be null, contain null elements, or
     *     contain any invalid values, as defined by Frame.allowedRoles.
     */
    public void setRole(String[] role) {
        boolean isValidRole = false;
        String roleString;

        if (role == null) {
            throw new IllegalArgumentException("New role value is null");
        }
        for (int i = 0; i < role.length; i++) {
            role[i] = role[i].toLowerCase();
            roleString = role[i];
            isValidRole = false;
            if (roleString == null) {
                throw new IllegalArgumentException("New role array contains"
                    + " a null element");
            }
            for (String roleMatch : Frame.allowedRoles) {
                if (roleString.equals(roleMatch)) {
                    isValidRole = true;
                    break;
                }
            }
            if (! isValidRole) {
                throw new IllegalArgumentException("New role array contains an"
                    + " invalid role value: \"" + roleString + "\"");
            }
        }
        role = HelperFunctions.copyOf(role);
        this.role = role;
    }
    public void setFrameDescription(String frameDescription) {
        if (frameDescription == null) {
            throw new IllegalArgumentException("New frame description is"
                + " null");
        }
        this.frameDescription = frameDescription;
    }
    /**
     * Sets this.size to the value provided.
     * @param size an int which must be 1, 2, 4, 6, or 8.
     */
    public void setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("New frame size is < 1");
        }
        if (size > 8) {
            throw new IllegalArgumentException("New frame size is > 8");
        }
        if (size == 3 || size == 5 || size == 7) {
            throw new IllegalArgumentException("New frame size is an invalid"
                + " value: " + size);
        }
        this.size = size;
    }
    public void setStructure(int structure) {
        if (structure < 1) {
            throw new IllegalArgumentException(
                "New structure value is < 1");
        }
        this.structure = structure;
    }
    public void setHP(int HP) {
        if (HP < 1) {
            throw new IllegalArgumentException("New HP value is < 1");
        }
        this.HP = HP;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value is < 0");
        }
        this.armor = armor;
    }
    public void setStress(int stress) {
        if (stress < 1) {
            throw new IllegalArgumentException("New stress value is < 1");
        }
        this.stress = stress;
    }
    public void setHeatCapacity(int heatCapacity) {
        if (heatCapacity < 1) {
            throw new IllegalArgumentException("New heat capacity value is <"
                + " 1");
        }
        this.heatCapacity = heatCapacity;
    }
    public void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion value is < 0");
        }
        this.evasion = evasion;
    }
    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed value is < 0");
        }
        this.speed = speed;
    }
    public void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException(
                "New e-defense value is < 0");
        }
        this.eDefense = eDefense;
    }
    public void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    public void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors value is < 0");
        }
        this.sensors = sensors;
    }
    public void setRepairCapacity(int repairCapacity) {
        if (repairCapacity < 0) {
            throw new IllegalArgumentException("New repair capacity value is"
                + " < 0");
        }
        this.repairCapacity = repairCapacity;
    }
    public void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target value is <"
                + " 0");
        }
        this.saveTarget = saveTarget;
    }
    public void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points value is <"
                + " 0");
        }
        this.systemPoints = systemPoints;
    }
    /**
     * Sets this.traits to the provided value.
     * @param traits a String[] that cannot be null, contain null elements, or
     *     elements that are "".
     */
    public void setTraits(String[] traits) {
        if (traits == null) {
            throw new IllegalArgumentException("New traits value is null");
        }
        for (String trait : traits) {
            if (trait == null) {
                throw new IllegalArgumentException("New traits value contains"
                    + " a null element");
            }
            if (trait.equals("")) {
                throw new IllegalArgumentException("New traits value contains"
                    + " an \"\" element");
            }
        }
        traits = HelperFunctions.copyOf(traits);
        this.traits = traits;
    }
    /**
     * Sets this.mounts to the provided value.
     * @param mounts a Mount[] that cannot be null or contain null elements.
     */
    public void setMounts(Mount[] mounts) {
        if (mounts == null) {
            throw new IllegalArgumentException("New mounts value is null");
        }
        for (Mount mount : mounts) {
            if (mount == null) {
                throw new IllegalArgumentException("New mounts array contains"
                    + " a null element");
            }
        }
        mounts = HelperFunctions.copyOf(mounts);
        this.mounts = mounts;
    }

    /**
     * A method checking whether any of the properties of this object whose
     *     placeholder value is normally not allowed are set to their
     *     placeholder value.
     * @return a boolean containing the result of the check.
     */
    public boolean hasPlaceholders() {
        if (getManufacturer().equals("")) {
            return true;
        }
        if (getID().equals("") && getFrameEnum() == null) {
            if (getID().equals("") && getFrameEnum() != null) {
                throw new IllegalArgumentException("Frame.ID is set but"
                    + " Frame.frameEnum is not");
            } else if (!(getID().equals(""))
                && getFrameEnum() == null) {
                throw new IllegalArgumentException("Frame.frameEnum is set but"
                    + " Frame.ID is not");
            }
            return true;
        }
        if (this.role.getClass() == String[].class && this.role.length == 0) {
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
    /**
     * A method checking whether every property of this object is set to its
     *     placeholder value.
     * @return a boolean containing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! getManufacturer().equals("")) {
            return false;
        }
        if (! getName().equals("")) {
            return false;
        }
        if (! getID().equals("")) {
            return false;
        }
        if (getFrameEnum() != null) {
            return false;
        }
        if (this.role.getClass() != String[].class || this.role.length != 0) {
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
        if (this.traits.getClass() != String[].class
            || this.traits.length != 0) {
            return false;
        }
        if (this.mounts.getClass() != Mount[].class
            || this.mounts.length != 0) {
            return false;
        }

        return true;
    }
    /**
     * Returns a deepest copy of this object.
     * @return a Frame deepest copy of this object.
     */
    public Frame copyOf() {
        // don't need to make copies of these for the ones using the mutator
        //     methods (i.e. mounts) because the mutators already do so
        Frame copy = new Frame();
        
        copy.manufacturer = this.manufacturer;
        copy.name = this.name;
        copy.ID = this.ID;
        copy.frameEnum = this.frameEnum;
        copy.role = this.role;
        copy.setFrameDescription(this.frameDescription);
        copy.size = this.size;
        copy.setStructure(this.structure);
        copy.HP = this.HP;
        copy.armor = this.armor;
        copy.setStress(this.stress);
        copy.heatCapacity = this.heatCapacity;
        copy.evasion = this.evasion;
        copy.speed = this.speed;
        copy.eDefense = this.eDefense;
        copy.setTechAttack(this.techAttack);
        copy.sensors = this.sensors;
        copy.repairCapacity = this.repairCapacity;
        copy.saveTarget = this.saveTarget;
        copy.systemPoints = this.systemPoints;
        copy.setTraits(this.traits);
        copy.setMounts(this.mounts);

        return copy;
    }
    /**
     * Returns this.name, properly formatted (i.e. "swallowtail (ranger
     *     variant)" becomes "Swallowtail (Ranger Variant)" and "death's head"
     *     becomes "Death's Head").
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        return HelperFunctions.toProperCase(name);
    }
}
