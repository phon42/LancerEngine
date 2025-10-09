// TODO: change Mech.frame to allow null and alter documentation to compensate
/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its stats, its mounts, and
 *     its systems, among other statistics.
 * 
 * Requires a Frame object to create a non-placeholder Mech object. The Frame
 *     object's stats, traits, and mounts serve as the base on top of which mech
 *     skills, weapons, systems, and other modifications are added.
 * 
 * Used in LancerCharacter.
 * 
 * Safety: This class has placeholder values and can be a placeholder, but in
 *     most cases should either have no placeholder values or be a placeholder,
 *     which must be checked for. None of its properties have allowed values of
 *     null.
 */
public class Mech {
    /**
     * The name of this mech (i.e. "Raijin") - NOT its frame name, the name
     *     given to this specific chassis.
     * Can be any String except "". Cannot be null. Is set to "" on construction
     *     from Mech().
     */
    private String name;
    
    // Frame properties being held here for convenience (as to not require
    //     referencing Mech.frame to get them)
    /**
     * The frame that this mech is patterned after (i.e. Swallowtail) as a Frame
     *     object.
     * Must either be a Frame without any placeholder values OR a placeholder
     *     Frame.
     */
    private Frame frame;
    /**
     * The mech's origin frame's manufacturer (i.e. "GMS").
     * Case-insensitive and stored in uppercase. Can be any String except "".
     *     Cannot be null. Is set to "" on construction.
     */
    private String manufacturer;
    /**
     * The mech's origin frame's name (i.e. "everest").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null. Is set to "" on construction.
     * Use Mech.getFrameName() to get the raw value and
     *     Mech.getFrame().outputName() to obtain it properly formatted.
     */
    private String frameName;
    // ID and frameEnum from Frame were intentionally omitted here
    /**
     * The mech's origin frame's role (i.e. "balanced"). Multiple items are 
     *     stored as seperate elements (i.e "Controller/Support" would be stored
     *     as {"controller", "support"}).
     * Must be of length 1 at minimum. Each element must be one of the following
     *     values:
     *     "artillery", "balanced", "controller", "striker", "support".
     * Case-insensitive and stored in lowercase. Cannot be null. Is set to a new
     *     String[0] on construction.
     */
    private String[] role;
    /**
     * The description at the top of the mech's origin frame's page on COMP/CON
     *     (i.e. "Most humans don’t...").
     * Can be any String. Cannot be null.
     */
    private String frameDescription;

    /**
     * The operator notes attached to this mech.
     * Can be any String. Cannot be null.
     */
    private String operatorNotes;
    
    // frame attributes - size, structure, HP, etc.
    /**
     * The mech's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Is set to -1 on construction.
     */
    private int size;

    // health and structure
    /**
     * The mech's current structure value.
     * Must be a minimum of 0.
     */
    private int currentStructure;
    /**
     * The mech's max structure value.
     * Must be a minimum of 1.
     */
    private int maxStructure;
    /**
     * The mech's current HP value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int currentHP;
    /**
     * The mech's max HP value.
     * Must be a minimum of 1. Is set to -1 on construction.
     */
    private int maxHP;
    /**
     * The mech's armor value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int armor;

    // heat and stress
    /**
     * The mech's current stress value.
     * Must be a minimum of 0.
     */
    private int currentStress;
    /**
     * The mech's max stress value.
     * Must be a minimum of 1.
     */
    private int maxStress;
    /**
     * The mech's current heat capacity.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int currentHeatCapacity;
    /**
     * The mech's max heat capacity.
     * Must be a minimum of 1. Is set to -1 on construction.
     */
    private int maxHeatCapacity;

    // evasion and speed
    /**
     * The mech's evasion value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int evasion;
    /**
     * The mech's speed value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int speed;

    // e-defense and tech attack
    /**
     * The mech's e-defense value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int eDefense;
    /**
     * The mech's tech attack value.
     * Can be any int.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * The mech's sensors value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int sensors;
    /**
     * The mech's current repair capacity value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int currentRepairCapacity;
    /**
     * The mech's max repair capacity value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int maxRepairCapacity;

    // save target and system points
    /**
     * The mech's save target value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int saveTarget;
    /**
     * The mech's system points value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int systemPoints;

    /**
     * The mech's limited systems bonus value.
     * Must be a minimum of 0. Is set to -1 on construction.
     */
    private int limitedSystemsBonus;

    /**
     * The mech's origin frame's traits (i.e. {"Initiative",
     *     "Replaceable Parts"}).
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     */
    private String[] traits;
    
    /**
     * The mech's weapon mounts.
     * Can be any Mount[] that does not contain null. Cannot be null.
     */
    private Mount[] mounts;

    // TODO: fill out
    // core system
    // core system description
    // core system passive
    // core system active

    /**
     * The mech's systems.
     * Can be any MechSystem[] that does not contain null. Cannot be null.
     */
    private MechSystem[] systems;

    /**
     * Creates a placeholder Mech.
     */
    public Mech() {
        // setMaxStructure() swapped with setCurrentStructure() and
        //     setMaxStress() swapped with setCurrentStress() because the
        //     mutators may throw exceptions otherwise
        this.name = "";
        this.frame = new Frame();
        this.manufacturer = "";
        this.frameName = "";
        this.role = new String[0];
        setFrameDescription("");
        setOperatorNotes("");
        this.size = -1;
        setMaxStructure(4);
        setCurrentStructure(4);
        this.currentHP = -1;
        this.maxHP = -1;
        this.armor = -1;
        setMaxStress(4);
        setCurrentStress(4);
        this.currentHeatCapacity = -1;
        this.maxHeatCapacity = -1;
        this.evasion = -1;
        this.speed = -1;
        this.eDefense = -1;
        setTechAttack(-1);
        this.sensors = -1;
        this.currentRepairCapacity = -1;
        this.maxRepairCapacity = -1;
        this.saveTarget = -1;
        this.systemPoints = -1;
        this.limitedSystemsBonus = -1;
        setTraits(new String[0]);
        setMounts(new Mount[0]);
        setSystems(new MechSystem[0]);
    }
    /**
     * Creates a non-placeholder Mech from a mech name and a frameID.
     * @param name a String containing the mech name of the new Mech.
     * @param frameID a String containing the frameID of the Frame to use to
     *     create the new Mech.
     */
    public Mech(String name, String frameID) {
        this();
        setName(name);
        setFrame(FrameDatabase.getFrame(frameID));
    }
    /**
     * Creates a non-placeholder Mech from a mech name and a FrameEnum.
     * @param name a String containing the mech name of the new Mech.
     * @param frameEnum a FrameEnum containing the FrameEnum of the Frame to
     *     use to create the new Mech.
     */
    public Mech(String name, FrameEnum frameEnum) {
        this();
        setName(name);
        setFrame(FrameDatabase.getFrame(frameEnum));
    }
    // TODO: remove if unused
    /**
     * Creates a non-placeholder Mech given every Mech property that isn't
     *     calculated by the Mech's Frame.
     */
    public Mech(String name, Frame frame, String operatorNotes,
        int currentStructure, int currentHP, int currentStress,
        int currentHeatCapacity, int currentRepairCapacity,
        MechSystem[] systems) {
        // Swapped max and current property mutators because they throw
        //     exceptions otherwise
        // manufacturer and other such frame-related properties are set at the
        //     top instead of their normal places because setFrame() will
        //     automatically populate them if provided with a non-placeholder
        //     frame and setting them to their placeholder values would override
        //     that
        this.manufacturer = "";
        this.frameName = "";
        this.role = new String[0];
        setFrameDescription("");
        setTraits(new String[0]);

        setName(name);
        setFrame(frame);
        setOperatorNotes(operatorNotes);
        setCurrentStructure(currentStructure);
        setCurrentHP(currentHP);
        setCurrentStress(currentStress);
        setCurrentHeatCapacity(currentHeatCapacity);
        setCurrentRepairCapacity(currentRepairCapacity);
        setSystems(systems);
    }

    public String getName() {
        return name;
    }
    public Frame getFrame() {
        return frame.copyOf();
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getFrameName() {
        return frameName;
    }
    public String[] getRole() {
        return HelperFunctions.copyOf(role);
    }
    public String getFrameDescription() {
        return frameDescription;
    }
    public String getOperatorNotes() {
        return operatorNotes;
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
    public String[] getTraits() {
        return HelperFunctions.copyOf(traits);
    }
    public Mount[] getMounts() {
        return HelperFunctions.copyOf(mounts);
    }
    public MechSystem[] getSystems() {
        return HelperFunctions.copyOf(systems);
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
    /**
     * Sets this.frame to the provided value.
     * @param frame a Frame which cannot be null. Must be either a Frame without
     *     any placeholder values OR a placeholder Frame.
     * @throws IllegalArgumentException if frame is null or has placeholder
     *     values but is not a placeholder Frame.
     */
    public void setFrame(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New frame is null");
        }
        if (frame.hasPlaceholders()) {
            if (! frame.isPlaceholder()) {
                throw new IllegalArgumentException("New frame has placeholder"
                    + " values but is not a placeholder Frame");
            }
        }
        frame = frame.copyOf();
        this.frame = frame;
        calculateAttributes();
        setMounts(this.frame.getMounts());
    }
    private void setManufacturer(String manufacturer) {
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
    private void setFrameName(String frameName) {
        if (frameName == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (frameName.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        frameName = frameName.toLowerCase();
        this.frameName = frameName;
    }
    /**
     * Sets this.role to the provided value.
     * @param role a String[] which cannot be null, be of length 0, contain null
     *     elements, or invalid elements, as defined by Frame.allowedRoles.
     * @throws IllegalArgumentException if role is null, of length 0, contains
     *     null elements, or invalid elements, as defined by Frame.allowedRoles.
     */
    private void setRole(String[] role) {
        boolean isValidRole = false;
        String roleString;

        if (role == null) {
            throw new IllegalArgumentException("New role value is null");
        }
        if (role.length == 0) {
            throw new IllegalArgumentException("New role array has a length"
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
                }
            }
            if (! isValidRole) {
                throw new IllegalArgumentException("New role array contains an"
                    + " invalid element: \"" + roleString + "\"");
            }
        }
        role = HelperFunctions.copyOf(role);
        this.role = role;
    }
    private void setFrameDescription(String frameDescription) {
        if (frameDescription == null) {
            throw new IllegalArgumentException("New frame description is"
                + " null");
        }
        this.frameDescription = frameDescription;
    }
    public void setOperatorNotes(String operatorNotes) {
        if (operatorNotes == null) {
            throw new IllegalArgumentException("New operator notes are null");
        }
        this.operatorNotes = operatorNotes;
    }
    /**
     * Sets this.size to the provided value.
     * @param size an int which must be 1, 2, 4, 6, or 8.
     * @throws IllegalArgumentException if size is not 1, 2, 4, 6, or 8.
     */
    private void setSize(int size) {
        if (size != 1 && size != 2 && size != 4 && size != 6 && size != 8) {
            throw new IllegalArgumentException("New size: " + size + " is not"
                + " one of the following valid values: 1, 2, 4, 6, or 8");
        }
        this.size = size;
    }
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
    private void setMaxStructure(int maxStructure) {
        if (maxStructure < 1) {
            throw new IllegalArgumentException("New maxStructure value: "
                + maxStructure + " is < 1");
        }
        if (maxStructure < this.currentStructure) {
            System.out.println("[ WARNING ] maxStructure value provided: "
                + maxStructure + " is < currentStructure value: "
                + this.currentStructure);
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
    private void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New maxHP value: " + maxHP
                + " is < 1");
        }
        if (maxHP < this.currentHP) {
            System.out.println("[ WARNING ] maxHP value provided: " + maxHP
                + " is < currentHP value: " + this.currentHP);
        }
        this.maxHP = maxHP;
    }
    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value: " + armor
                + " is < 0");
        }
        this.armor = armor;
    }
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
    private void setMaxStress(int maxStress) {
        if (maxStress < 1) {
            throw new IllegalArgumentException("New maxStress value: "
                + maxStress + " is < 1");
        }
        if (maxStress < this.currentStress) {
            System.out.println("[ WARNING ] maxStress value provided: "
                + maxStress + " is < currentStress value: "
                + this.currentStress);
        }
        this.maxStress = maxStress;
    }
    /**
     * Sets this.currentHeatCapacity to the provided value.
     * @param currentHeatCapacity an int which cannot be < 0 or >
     *     this.maxHeatCapacity.
     * @throws IllegalArgumentException if currentHeatCapacity is < 0 or >
     *     this.maxHeatCapacity.
     */
    public void setCurrentHeatCapacity(int currentHeatCapacity) {
        if (currentHeatCapacity < 0) {
            throw new IllegalArgumentException("New currentHeatCapacity value: "
                + currentHeatCapacity + " is < 0");
        }
        if (this.maxHeatCapacity < currentHeatCapacity) {
            throw new IllegalArgumentException("currentHeatCapacity value"
                + " provided: " + currentHeatCapacity + " is > maxHeatCapacity"
                + " value: " + this.maxHeatCapacity);
        }
        this.currentHeatCapacity = currentHeatCapacity;
    }
    /**
     * Sets this.maxHeatCapacity to the provided value.
     * @param maxHeatCapacity an int which cannot be < 1. Will print a warning
     *     if maxHeatCapacity is < this.currentHeatCapacity.
     * @throws IllegalArgumentException if maxHeatCapacity is < 1.
     */
    private void setMaxHeatCapacity(int maxHeatCapacity) {
        if (maxHeatCapacity < 1) {
            throw new IllegalArgumentException("New maxHeatCapacity value: "
                + maxHeatCapacity + " is < 1");
        }
        if (maxHeatCapacity < this.currentHeatCapacity) {
            System.out.println("[ WARNING ] maxHeatCapacity value provided: "
                + maxHeatCapacity + " is < currentHeatCapacity value: "
                + this.currentHeatCapacity);
        }
        this.maxHeatCapacity = maxHeatCapacity;
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
    /**
     * Sets this.currentRepairCapacity to the provided value.
     * @param currentRepairCapacity an int which cannot be < 0 or >
     *     this.maxRepairCapacity.
     * @throws IllegalArgumentException if currentRepairCapacity is < 0 or >
     *     this.maxRepairCapacity.
     */
    public void setCurrentRepairCapacity(int currentRepairCapacity) {
        if (currentRepairCapacity < 0) {
            throw new IllegalArgumentException("New currentRepairCapacity"
                + " value:" + currentRepairCapacity + " is < 0");
        }
        if (this.maxRepairCapacity < currentRepairCapacity) {
            throw new IllegalArgumentException("currentRepairCapacity value"
                + " provided: " + currentRepairCapacity + " is >"
                + " maxRepairCapacity value: " + this.maxRepairCapacity);
        }
        this.currentRepairCapacity = currentRepairCapacity;
    }
    /**
     * Sets this.maxRepairCapacity to the provided value.
     * @param maxRepairCapacity an int which cannot be < 0. Will print a warning
     *     if maxRepairCapacity is < this.currentRepairCapacity.
     * @throws IllegalArgumentException if maxRepairCapacity is < 0.
     */
    private void setMaxRepairCapacity(int maxRepairCapacity) {
        if (maxRepairCapacity < 0) {
            throw new IllegalArgumentException("New maxRepairCapacity value: "
                + maxRepairCapacity + " is < 0");
        }
        if (maxRepairCapacity < this.currentRepairCapacity) {
            System.out.println("[ WARNING ] maxRepairCapacity value provided: "
                + maxRepairCapacity + " is < currentRepairCapacity value: "
                + this.currentRepairCapacity);
        }
        this.maxRepairCapacity = maxRepairCapacity;
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
    private void setLimitedSystemsBonus(int limitedSystemsBonus) {
        if (limitedSystemsBonus < 0) {
            throw new IllegalArgumentException("New limited systems bonus"
                + " value: " + limitedSystemsBonus + " is < 0");
        }
        this.limitedSystemsBonus = limitedSystemsBonus;
    }
    /**
     * Sets this.traits to the provided value.
     * @param traits a String[] which cannot be null, include null elements, or
     *     elements that are "".
     * @throws IllegalArgumentException if traits is null, includes null
     *     elements, or elements that are "".
     */
    private void setTraits(String[] traits) {
        if (traits == null) {
            throw new IllegalArgumentException("New traits value is null");
        }
        for (String trait : traits) {
            if (trait == null) {
                throw new IllegalArgumentException("New traits array contains"
                    + " a null element");
            }
            if (trait.equals("")) {
                throw new IllegalArgumentException("New traits array contains"
                    + " an element that is \"\"");
            }
        }
        traits = HelperFunctions.copyOf(traits);
        this.traits = traits;
    }
    /**
     * Sets this.mounts to the provided value.
     * @param mounts a Mount[] which cannot be null or contain null elements.
     * @throws IllegalArgumentException if mounts is null or contains null
     *     elements.
     */
    private void setMounts(Mount[] mounts) {
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
     * Sets this.mounts[mountIndex] to the provided Mount. mount.mountType need
     *     not be correct as long as mountIndex is valid.
     * @param mountIndex an int which must be a valid index for this.mounts.
     * @param mount a Mount which cannot be null
     * @throws IllegalArgumentException if mountIndex is out of bounds or mount
     *     is null.
     */
    public void setMount(int mountIndex, Mount mount) {
        if (mountIndex < 0) {
            throw new IllegalArgumentException("mountIndex:" + mountIndex
                + " is out of bounds for length " + this.mounts.length);
        }
        if (mountIndex >= this.mounts.length) {
            throw new IllegalArgumentException("mountIndex:" + mountIndex
                + " is out of bounds for length " + this.mounts.length);
        }
        if (mount == null) {
            throw new IllegalArgumentException("New mount is null");
        }
        if (! mount.getMountType().equals(
            this.mounts[mountIndex].getMountType())) {
            // the user was lazy and used new Mount(Weapon) instead of
            //     new Mount(mountType, Weapon)
            String mountType = this.mounts[mountIndex].getMountType();
            mount = new Mount(mountType, mount.getWeapon());
        }
        mount = mount.copyOf();
        this.mounts[mountIndex] = mount;
    }
    /**
     * Sets this.systems to the provided value.
     * @param systems a MechSystem[] which cannot be null or contain null
     *     elements.
     * @throws IllegalArgumentException if systems is null or contains null
     *     elements.
     */
    public void setSystems(MechSystem[] systems) {
        if (systems == null) {
            throw new IllegalArgumentException("New mech systems value is"
                + " null");
        }
        for (MechSystem system : systems) {
            if (system == null) {
                throw new IllegalArgumentException("New mech systems array"
                    + " contains a null element");
            }
        }
        systems = HelperFunctions.copyOf(systems);
        this.systems = systems;
    }

    /**
     * A method checking whether any of the properties of this object whose
     *     placeholder value is normally not allowed are set to their
     *     placeholder value.
     * @return a boolean representing the result of the check.
     */
    public boolean hasPlaceholders() {
        if (name.equals("")) {
            return true;
        }
        if (frame.isPlaceholder()) {
            return true;
        }
        if (manufacturer.equals("")) {
            return true;
        }
        if (frameName.equals("")) {
            return true;
        }
        if (role.length == 0) {
            return true;
        }
        if (size == -1) {
            return true;
        }
        if (currentHP == -1) {
            return true;
        }
        if (maxHP == -1) {
            return true;
        }
        if (armor == -1) {
            return true;
        }
        if (currentHeatCapacity == -1) {
            return true;
        }
        if (maxHeatCapacity == -1) {
            return true;
        }
        if (evasion == -1) {
            return true;
        }
        if (speed == -1) {
            return true;
        }
        if (eDefense == -1) {
            return true;
        }
        if (sensors == -1) {
            return true;
        }
        if (currentRepairCapacity == -1) {
            return true;
        }
        if (maxRepairCapacity == -1) {
            return true;
        }
        if (saveTarget == -1) {
            return true;
        }
        if (systemPoints == -1) {
            return true;
        }
        if (limitedSystemsBonus == -1) {
            return true;
        }

        return false;
    }
    /**
     * A method checking whether every property of this object is set to its
     *     placeholder value.
     * @return a boolean representing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! name.equals("")) {
            return false;
        }
        if (! frame.isPlaceholder()) {
            return false;
        }
        if (! manufacturer.equals("")) {
            return false;
        }
        if (! frameName.equals("")) {
            return false;
        }
        if (role.length != 0) {
            return false;
        }
        if (! frameDescription.equals("")) {
            return false;
        }
        if (! operatorNotes.equals("")) {
            return false;
        }
        if (size != -1) {
            return false;
        }
        if (currentStructure != 4) {
            return false;
        }
        if (maxStructure != 4) {
            return false;
        }
        if (currentHP != -1) {
            return false;
        }
        if (maxHP != -1) {
            return false;
        }
        if (armor != -1) {
            return false;
        }
        if (currentStress != 4) {
            return false;
        }
        if (maxStress != 4) {
            return false;
        }
        if (currentHeatCapacity != -1) {
            return false;
        }
        if (maxHeatCapacity != -1) {
            return false;
        }
        if (evasion != -1) {
            return false;
        }
        if (speed != -1) {
            return false;
        }
        if (eDefense != -1) {
            return false;
        }
        if (techAttack != -1) {
            return false;
        }
        if (sensors != -1) {
            return false;
        }
        if (currentRepairCapacity != -1) {
            return false;
        }
        if (maxRepairCapacity != -1) {
            return false;
        }
        if (saveTarget != -1) {
            return false;
        }
        if (systemPoints != -1) {
            return false;
        }
        if (limitedSystemsBonus != -1) {
            return false;
        }
        if (traits.length != 0) {
            return false;
        }
        if (mounts.length != 0) {
            return false;
        }
        if (systems.length != 0) {
            return false;
        }

        return true;
    }
    /**
     * Returns a deepest copy of this Mech object.
     * @return a Mech deepest copy of this object.
     */
    public Mech copyOf() {
        // don't need to make copies of these for the ones using the mutator
        //     methods (i.e. mounts) because the mutators already do so
        // for the ones that DON'T use a mutator method (i.e. "copy.name =
        //     name"), make sure to use the proper accessor method instead
        //     of "property" if the property's type is mutable
        Mech copy = new Mech();
        
        copy.name = name;
        copy.frame = getFrame();
        copy.manufacturer = manufacturer;
        copy.frameName = frameName;
        copy.role = getRole();
        copy.setFrameDescription(frameDescription);
        copy.setOperatorNotes(operatorNotes);
        copy.size = size;
        copy.setCurrentStructure(currentStructure);
        copy.setMaxStructure(maxStructure);
        copy.currentHP = currentHP;
        copy.maxHP = maxHP;
        copy.armor = armor;
        copy.setCurrentStress(currentStress);
        copy.setMaxStress(maxStress);
        copy.currentHeatCapacity = currentHeatCapacity;
        copy.maxHeatCapacity = maxHeatCapacity;
        copy.evasion = evasion;
        copy.speed = speed;
        copy.eDefense = eDefense;
        copy.setTechAttack(techAttack);
        copy.sensors = sensors;
        copy.currentRepairCapacity = currentRepairCapacity;
        copy.maxRepairCapacity = maxRepairCapacity;
        copy.saveTarget = saveTarget;
        copy.systemPoints = systemPoints;
        copy.limitedSystemsBonus = limitedSystemsBonus;
        copy.setTraits(traits);
        copy.setMounts(mounts);
        copy.setSystems(systems);

        return copy;
    }
    /**
     * A helper method which outputs the mech's size, formatted properly so that
     *     it is human-readable. Used in Mech.outputStats("full", int).
     * @return a String containing the requested output.
     */
    public String outputSize() {
        if (size == 1) {
            return "1/2";
        }
        if (size > 1) {
            return Integer.toString(size / 2);
        }
        return Integer.toString(size);
    }
    /**
     * Sets all of this Mech object's stat properties to their correct values,
     *     calculated based off of the Mech's frame property. Called when
     *     Mech.setFrame(Frame) is called.
     */
    public void calculateAttributes() {
        calculateAttributes(new int[4], new String[0], new Talent[0]);
    }
    /**
     * Sets all of this Mech object's stat properties to their correct values,
     *     calculated based off of the Mech's frame property as well as the
     *     provided int[]. Called when LancerCharacter.setMech(Mech) is called
     *     using a non-placeholder Mech as well as when Mech.setFrame(Frame) is
     *     called through calculateAttributes().
     * @param mechSkills an int[4] containing the mech skills of the Pilot
     *     associated with this Mech through the parent LancerCharacter.
     * @param coreBonuses a String[] containing the core bonuses of the Pilot
     *     associated with this Mech through the parent LancerCharacter.
     * @param talents a String[] containing the talents of the Pilot associated
     *     with this Mech through the parent LancerCharacter.
     * @throws IllegalArgumentException if this.frame is null.
     */
    public void calculateAttributes(int[] mechSkills, String[] coreBonuses,
        Talent[] talents) {
        // TODO: update to account for the Engineer talent as well as the
        //     Improved Armament and Integrated Weapon core bonuses
        if (this.frame == null) {
            throw new IllegalArgumentException("calculateAttributes() was"
                + " called while this.frame was set to null");
        }
        setMaxStructure(this.frame.getStructure());
        setCurrentStructure(this.currentStructure);
        setMaxStress(this.frame.getStress());
        setCurrentStress(this.currentStress);
        
        // Hull
        setMaxHP(this.frame.getHP() + (mechSkills[0] * 2));
        setCurrentHP(this.maxHP);
        setMaxRepairCapacity(this.frame.getRepairCapacity()
            + (mechSkills[0] / 2));
        setCurrentRepairCapacity(this.maxRepairCapacity);

        // Agility
        setEvasion(this.frame.getEvasion() + mechSkills[1]);
        setSpeed(this.frame.getSpeed() + (mechSkills[1] / 2));

        // Systems
        setEDefense(this.frame.getEDefense() + mechSkills[2]);
        setTechAttack(this.frame.getTechAttack() + mechSkills[2]);
        setSystemPoints(this.frame.getSystemPoints() + (mechSkills[2] / 2));

        // Engineering
        // setMaxHeatCapacity() swapped with setCurrentHeatCapacity() because
        //     the mutators may throw exceptions otherwise
        setMaxHeatCapacity(this.frame.getHeatCapacity() + mechSkills[3]);
        setCurrentHeatCapacity(0);
        setLimitedSystemsBonus(mechSkills[3] / 2);
        
        // Order of these properties within the mech's weapon mounts is:
        // Prototype Weapon (from Engineer talent)
        // Integrated Weapon (from Integrated Weapon core bonus)
        // Improved Armament (from Improved Armament core bonus)
        // Therefore, we will add these mounts to index 0 in the OPPOSITE order
        //     - so that Improved Armament ends up at index 2, Integrated Weapon
        //     at index 1, and Prototype Weapon at index 0.
        // TODO: make the improved armament calculate whether it should be added
        //     instead of just adding itself
        Mount[] mounts = this.frame.getMounts();
        for (String coreBonus : coreBonuses) {
            if (coreBonus.equals("improved armament")) {
                mounts = HelperFunctions.add(mounts, 
                    new Mount("improved armament core bonus",
                    null, "",
                    "improved armament", null), 0);
            }
        }
        for (String coreBonus : coreBonuses) {
            if (coreBonus.equals("integrated weapon")) {
                mounts = HelperFunctions.add(mounts, 
                    new Mount("integrated weapon core bonus",
                    null, "",
                    "integrated weapon", null), 0);
            }
        }
        for (Talent talent : talents) {
            if (talent.getName().equals("engineer")) {
                String weaponName = "Prototype Weapon ";
                for (int i = 0; i < talent.getLevel(); i++) {
                    weaponName += "I";
                }
                mounts = HelperFunctions.add(mounts, 
                    new Mount("integrated weapon",
                    new Weapon(weaponName), "", "",
                    talent), 0);
            }
        }
        
        setManufacturer(this.frame.getManufacturer());
        setFrameName(this.frame.getName());
        setRole(this.frame.getRole());
        setFrameDescription(this.frame.getFrameDescription());
        setSize(this.frame.getSize());
        setArmor(this.frame.getArmor());
        setSensors(this.frame.getSensors());
        setSaveTarget(this.frame.getSaveTarget());
        setTraits(this.frame.getTraits());
        setMounts(mounts);
    }
    /**
     * Generates the output associated with the mech portion of the COMP/CON
     *     "generate statblock" feature. Only returns something other than ""
     *     when outputType is "mech build".
     * @param outputType a String containing the type of statblock to
     *     generate.
     * @return a String containing the requested output.
     * @throws IllegalArgumentException when outputType is "full" because that
     *     output type requires additional information only obtainable through
     *     Mech.generateOutput(String, int[], int)
     */
    public String generateOutput(String outputType, int limitedSystemsBonus) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            outputString += outputStats("mech build");
            outputString += "[ WEAPONS ]\n";
            outputString += outputWeapons("mech build");
            outputString += "[ SYSTEMS ]\n";
            outputString += outputSystems("mech build",
                this.limitedSystemsBonus);
        } else if (outputType.equals("full")) {
            throw new IllegalArgumentException("Called"
                + " Mech.generateOutput(\"full\") but mech skills value was not"
                + " provided.");
        }

        return outputString;
    }
    /**
     * Generates the output associated with the mech portion of the COMP/CON
     *     "generate statblock" feature. Only returns something other than ""
     *     when outputType is "mech build" or "full".
     * @param outputType a String containing the type of statblock to
     *     generate.
     * @param mechSkills an int[4] containing the mech skills of the Pilot
     *     associated with this Mech through the parent LancerCharacter.
     * @param grit an int containing the grit stat of the Pilot associated with
     *     this Mech through the parent LancerCharacter.
     * @return a String containing the requested output.
     * @throws IllegalArgumentException when outputType is "mech build" because
     *     that output type requires additional information only obtainable
     *     through Mech.generateOutput(String, int)
     */
    public String generateOutput(String outputType, int[] mechSkills,
        int grit) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            throw new IllegalArgumentException("Called"
                + " Mech.generateOutput(\"mech build\") but limited systems"
                + " bonus value was not provided.");
        } else if (outputType.equals("full")) {
            outputString += "[ MECH ]\n";
            if (isPlaceholder()) {
                outputString += "  « N/A »\n";
                outputString += "  N/A N/A\n";
                outputString += "  H:" + mechSkills[0] + " A:" + mechSkills[1]
                    + " S:" + mechSkills[2] + " E:" + mechSkills[3]
                    + outputStats("full", grit);
            } else {
                outputString += "  « " + this.name + " »\n";
                outputString += "  " + this.manufacturer + " "
                    + this.frame.outputName() + "\n";
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
    /**
     * A helper method which generates a mech stat block used in
     *     Mech.generateOutput(). Only returns something other than "" when
     *     outputType is "mech build".
     * @param outputType a String containing the type of stat block to
     *     generate.
     * @return a String containing the requested output.
     * @throws IllegalArgumentException when outputType is "full" because that
     *     output type requires additional information only obtainable through
     *     Mech.outputStats(String, int)
     */
    public String outputStats(String outputType) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            outputString += "  STRUCTURE:" + this.maxStructure + " HP:"
                + this.maxHP + " ARMOR:" + this.armor + "\n";
            outputString += "  STRESS:" + this.maxStress + " HEATCAP:"
                + this.maxHeatCapacity + " REPAIR:" + this.maxRepairCapacity
                + "\n";
            if (this.techAttack >= 0) {
                outputString += "  TECH ATK:+" + this.techAttack;
            } else {
                outputString += "  TECH ATK:" + this.techAttack;
            }
            if (this.limitedSystemsBonus >= 0) {
                outputString += " LIMITED:+" + this.limitedSystemsBonus;
            } else {
                outputString += " LIMITED:" + this.limitedSystemsBonus;
            }
            outputString += "\n";
            outputString += "  SPD:" + this.speed + " EVA:" + this.evasion
                + " EDEF:" + this.eDefense + " SENSE:" + this.sensors + " SAVE:"
                + this.saveTarget + "\n";
        } else if (outputType.equals("full")) {
            throw new IllegalArgumentException("Called"
                + " Mech.outputStats(\"full\") but grit value was not"
                + " provided");
        }

        return outputString;
    }
    /**
     * A helper method which generates a mech stat block used in
     *     Mech.generateOutput(). Only returns something other than "" when
     *     outputType is "mech build" or "full".
     * @param outputType a String containing the type of stat block to
     *     generate.
     * @param grit an int containing the grit stat of the Pilot associated with
     *     this Mech through the parent LancerCharacter.
     * @return a String containing the requested output.
     */
    public String outputStats(String outputType, int grit) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("full")) {
            outputString += " SIZE:" + outputSize() + "\n";
            outputString += "  STRUCTURE:" + this.currentStructure + "/"
                + this.maxStructure + " HP:" + this.currentHP + "/" + this.maxHP
                + " ARMOR:" + this.armor + "\n";
            outputString += "  STRESS:" + this.currentStress + "/"
                + this.maxStress + " HEAT:" + this.currentHeatCapacity + "/"
                + this.maxHeatCapacity + " REPAIR:" + this.currentRepairCapacity
                + "/" + this.maxRepairCapacity + "\n";
            outputString += "  ATK BONUS:" + grit + " TECH ATK:"
                + this.techAttack + " LTD BONUS:" + this.limitedSystemsBonus
                + "\n";
            outputString += "  SPD:" + this.speed + " EVA:" + this.evasion
                + " EDEF:" + this.eDefense + " SENS:" + this.sensors + " SAVE:"
                + this.saveTarget + "\n";
        } else {
            return outputStats(outputType);
        }

        return outputString;
    }
    /**
     * A helper method which generates a line of text containing output about
     *     the mech's mounts used in Mech.generateOutput(). Only returns
     *     something other than "" when outputType is "mech build" or "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing the requested output.
     */
    public String outputWeapons(String outputType) {
        // output something along the lines of:
        // "  MAIN MOUNT: Vulture DMR // Overpower Caliber\n"
        // or "  INTEGRATED WEAPON: Nexus (Light)\n"
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (this.mounts.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")
            || outputType.equals("full")) {
            for (int i = 0; i < this.mounts.length; i++) {
                outputString += "  ";
                outputString += this.mounts[i].outputWeapon(outputType);
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * A helper method which generates a line of text containing output about
     *     the mech's systems used in Mech.generateOutput(). Only returns
     *     something other than "" when outputType is "mech build" or "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing the requested output.
     * @throws IllegalArgumentException when outputType is "mech build" because
     *     that output type requires additional information only obtainable
     *     through Mech.outputSystems(String, int)
     */
    public String outputSystems(String outputType) {
        String outputString = "";

        if (this.systems.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            throw new IllegalArgumentException("Called"
                + " Mech.generateOutput(\"mech build\") but limited systems"
                + " bonus value was not provided.");
        } else if (outputType.equals("full")) {
            // output something along the lines of:
            //       "  Pattern-A Smoke Charges, Seismic Ripper,\n"
            //       "  High-Stress Mag Clamps, ATHENA-Class NHP\n"
            for (int i = 0; i < this.systems.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(this.systems.length, i + 2); j++) {
                    outputString += this.systems[j].outputSystem(outputType);
                    if (j == i && j + 2 < this.systems.length) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < this.systems.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * A helper method which generates a line of text containing output about
     *     the mech's systems used in Mech.generateOutput(). Only returns
     *     something other than "" when outputType is "mech build" or "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @param limitedSystemsBonus an int containing the limited systems bonus of
     *     the Mech calling this method.
     * @return a String containing the requested output.
     */
    public String outputSystems(String outputType, int limitedSystemsBonus) {
        String outputString = "";

        if (this.systems.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            // output something like:
            //     "  Pattern-A Smoke Charges x4, Seismic Ripper,"
            //     "  High-Stress Mag Clamps, ATHENA-Class NHP\n"
            for (int i = 0; i < this.systems.length; i++) {
                if (i == 0) {
                    outputString += "  ";
                }
                outputString += this.systems[i].outputSystem(outputType,
                    limitedSystemsBonus);
                if (i != this.systems.length - 1) {
                    outputString += ", ";
                }
            }
            outputString += "\n";
        } else if (outputType.equals("full")) {
            return outputSystems(outputType);
        }

        return outputString;
    }
}