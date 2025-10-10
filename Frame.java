/**
 * A Frame object is needed to create a Mech object. Its stats,
 *     traits, and mounts serve as the base on top of which mech skills,
 *     weapons, systems, and other modifications are added.
 */
/**
 * Represents a frame (a pattern or statblock that can be copied to form mechs).
 *     Contains information about the frame such as its manufacturer, frame
 *     name, role, and stats.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in Mech to create Mech objects. Also used in Database.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Frame {
    /**
     * The frame's manufacturer (i.e. "GMS").
     * Case-insensitive and stored in uppercase. Can be any String except "".
     *     Cannot be null.
     */
    private String manufacturer;
    /**
     * The frame's name (i.e. "everest").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null.
     * Use Frame.getName() to get the raw value and Frame.outputName() to obtain
     *     it properly formatted.
     */
    private String name;
    /**
     * The frame's ID (i.e. "swallowtail_ranger").
     * Used for identifying it in Database.getFrame(String).
     *     Case-insensitive and stored in lowercase. Can be any String except
     *     "". Cannot be null.
     */
    private String ID;
    /**
     * The frame's frameEnum (i.e. FrameEnum.SWALLOWTAIL_RANGER).
     * Used for identifying it in Database.getFrame(FrameEnum). Cannot be null.
     */
    private FrameEnum frameEnum;
    /**
     * The frame's role (i.e. "balanced"). Multiple items are stored as seperate
     *     elements (i.e "Controller/Support" would be stored as {"controller",
     *     "support"}).
     * Must be of length 1 at minimum. Each element must be one of the following
     *     values:
     *     "artillery", "balanced", "controller", "striker", "support".
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String[] role;
    /**
     * Contains an array of allowed values for the elements of the role
     *     property.
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
     * Can be any Mount[] that does not contain null elements, elements with
     *     their Mount.weapon set to something other than null with a
     *     Mount.mountType of anything other than "integrated weapon", or
     *     elements with their Mount.modification, Mount.coreBonus, or
     *     Mount.talent set to anything except ""/""/null. Cannot be null.
     */
    private Mount[] mounts;

    // TODO: fill out core system section
    // core system
    // core system description
    // core system passive
    // core system active

    /**
     * Creates a new Frame using every property except Structure and Stress,
     *     which it automatically sets to 4. Helpful for player frames.
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
     * Creates a new Frame using every possible property.
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
        return HelperMethods.copyOf(role);
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
        return HelperMethods.copyOf(traits);
    }
    public Mount[] getMounts() {
        return HelperMethods.copyOf(mounts);
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
     * @throws IllegalArgumentException if frameEnum is null.
     */
    public void setFrameEnum(FrameEnum frameEnum) {
        if (frameEnum == null) {
            throw new IllegalArgumentException("New frame enum is null");
        }
        this.frameEnum = frameEnum;
    }
    /**
     * Sets this.role to the value provided.
     * @param role a String[] which must be at least of length 1, cannot be
     *     null, contain null elements, or contain any invalid values, as
     *     defined by Frame.allowedRoles.
     * @throws IllegalArgumentException if role is null, contains null elements
     *     or invalid values, as defined by Frame.allowedRoles, or has a length
     *     of 0.
     */
    public void setRole(String[] role) {
        boolean isValidRole = false;
        String roleString;

        if (role == null) {
            throw new IllegalArgumentException("New role value is null");
        }
        if (role.length == 0) {
            throw new IllegalArgumentException("New role value has a length"
                + " of 0");
        }
        for (int i = 0; i < role.length; i++) {
            if (role[i] == null) {
                throw new IllegalArgumentException("New role array contains"
                    + " a null element");
            }
            role[i] = role[i].toLowerCase();
            roleString = role[i];
            isValidRole = false;
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
        role = HelperMethods.copyOf(role);
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
     * @throws IllegalArgumentException if size is anything other than 1, 2, 4,
     *     6, or 8.
     */
    public void setSize(int size) {
        if (size != 1 && size != 2 && size != 4 && size != 6 && size != 8) {
            throw new IllegalArgumentException("New frame size: " + size + " is"
                + " not one of the following valid values: 1, 2, 4, 6, 8");
        }
        this.size = size;
    }
    public void setStructure(int structure) {
        if (structure < 1) {
            throw new IllegalArgumentException("New structure value: "
                + structure + " is < 1");
        }
        this.structure = structure;
    }
    public void setHP(int HP) {
        if (HP < 1) {
            throw new IllegalArgumentException("New HP value: " + HP + " is <"
                + " 1");
        }
        this.HP = HP;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value: " + armor
                + " is < 0");
        }
        this.armor = armor;
    }
    public void setStress(int stress) {
        if (stress < 1) {
            throw new IllegalArgumentException("New stress value: " + stress
                + " is < 1");
        }
        this.stress = stress;
    }
    public void setHeatCapacity(int heatCapacity) {
        if (heatCapacity < 1) {
            throw new IllegalArgumentException("New heat capacity value: "
                + heatCapacity + " is < 1");
        }
        this.heatCapacity = heatCapacity;
    }
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
    public void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors value: " + sensors
                + " is < 0");
        }
        this.sensors = sensors;
    }
    public void setRepairCapacity(int repairCapacity) {
        if (repairCapacity < 0) {
            throw new IllegalArgumentException("New repair capacity value is: "
                + repairCapacity + " < 0");
        }
        this.repairCapacity = repairCapacity;
    }
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
    /**
     * Sets this.traits to the provided value.
     * @param traits a String[] that cannot be null, contain null elements, or
     *     elements that are "".
     * @throws IllegalArgumentException if traits is null, contains null
     *     elements, or elements that are "".
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
                    + " element that is \"\"");
            }
        }
        traits = HelperMethods.copyOf(traits);
        this.traits = traits;
    }
    /**
     * Sets this.mounts to the provided value.
     * @param mounts a Mount[] that cannot be null, contain null elements, or
     *     elements that have their Mount.modification, Mount.coreBonus, or
     *     Mount.talent set to anything except ""/""/null.
     * @throws IllegalArgumentException if mounts is null, contains null
     *     elements, elements with their Mount.weapon set to something other
     *     than null with a Mount.mountType of anything other than
     *     "integrated weapon", or elements with their Mount.modification,
     *     Mount.coreBonus, or Mount.talent set to something other than its
     *     construction value.
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
            if (mount.getWeapon() != null) {
                if (! mount.getMountType().equals("integrated weapon")) {
                    throw new IllegalArgumentException("New mounts array"
                        + " contains an element with its Mount.weapon set to"
                        + " something other than null, but its Mount.mountType"
                        + " was not \"integrated weapon\"");
                }
            }
            if (! mount.isUnmodified()) {
                throw new IllegalArgumentException("New mounts array"
                    + " contains an element with its Mount.modification,"
                    + " Mount.coreBonus, or Mount.talent set to something other"
                    + " than its construction value");
            }
        }
        mounts = HelperMethods.copyOf(mounts);
        this.mounts = mounts;
    }

    /**
     * Returns a deepest copy of this Frame object.
     * @return a Frame deepest copy of this object.
     */
    public Frame copyOf() {
        // make sure to use the proper accessor method instead of "property" if
        //     the property's type is mutable
        return new Frame(manufacturer, name, ID, frameEnum, getRole(),
            frameDescription, size, structure, HP, armor, stress, heatCapacity,
            evasion, speed, eDefense, techAttack, sensors, repairCapacity,
            saveTarget, systemPoints, getTraits(), getMounts());
    }
    /**
     * Returns this.name, properly formatted (i.e. "swallowtail (ranger
     *     variant)" becomes "Swallowtail (Ranger Variant)" and "death's head"
     *     becomes "Death's Head").
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        return HelperMethods.toProperCase(name);
    }
}
