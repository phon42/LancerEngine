/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its stats, its mounts, and
 *     its systems, among other statistics.
 * 
 * Requires a Frame object to create a Mech object. The Frame
 *     object's stats, traits, and mounts serve as the base on top of which mech
 *     skills, weapons, systems, and other modifications are added.
 * 
 * Used in LancerCharacter.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Mech implements Damageable {
    // TODO: add some set of inherently available actions
    // TODO: add some way for all possible actions from a Mech's MechSystems and
    //     Weapons to bubble up
    // TODO: add some way for a Mech to attack with its Weapons
    /**
     * The name of this mech (i.e. "Raijin") - NOT its frame name, the name
     *     given to this specific chassis.
     * Can be any String except "". Cannot be null.
     */
    private String name;
    
    // Frame properties being held here for convenience (as to not require
    //     referencing Mech.frame to get them)
    /**
     * The frame that this mech is patterned after (i.e. Swallowtail) as a Frame
     *     object.
     * Can be any Frame. Cannot be null.
     */
    private Frame frame;
    /**
     * The mech's origin frame's manufacturer (i.e. "GMS").
     * Case-insensitive and stored in uppercase. Can be any String except "".
     *     Cannot be null.
     */
    private String manufacturer;
    /**
     * The mech's origin frame's name (i.e. "everest").
     * Case-insensitive and stored in lowercase. Can be any String except "".
     *     Cannot be null.
     * Use Mech.getFrameName() to get the raw value and
     *     Mech.getFrame().outputName() to obtain it properly formatted.
     */
    private String frameName;
    // ID and frameEnum from Frame were intentionally omitted here
    /**
     * The mech's origin frame's role (i.e. "balanced"). Multiple items are 
     *     stored as seperate elements (i.e "Controller/Support" would be stored
     *     as {"controller", "support"}).
     * Must be of length 1 at minimum. Each element must be a valid role, as
     *     defined by Frame.allowedRoles.
     * Case-insensitive and stored in lowercase. Cannot be null.
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
     * Use Mech.getSize() to get the raw value and Mech.outputSize() to obtain
     *     it properly formatted.
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
     * Must be a minimum of 0.
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
     * The mech's current heat.
     * Must be a minimum of 0.
     */
    private int currentHeat;
    /**
     * The mech's max heat capacity.
     * Must be a minimum of 1.
     */
    private int maxHeatCapacity;

    // evasion and speed
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

    // e-defense and tech attack
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

    // sensors and repair capacity
    /**
     * The mech's sensors value.
     * Must be a minimum of 0.
     */
    private int sensors;
    /**
     * The number of repairs this mech currently has.
     * Must be a minimum of 0.
     */
    private int currentRepairs;
    /**
     * The mech's max repair capacity value.
     * Must be a minimum of 0.
     */
    private int maxRepairCapacity;

    // save target and system points
    /**
     * The mech's save target value.
     * Must be a minimum of 0.
     */
    private int saveTarget;
    /**
     * The mech's system points value.
     * Must be a minimum of 0.
     */
    private int systemPoints;

    /**
     * The mech's limited systems bonus value.
     * Must be a minimum of 0.
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

    // TODO: fill out core system section
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
     * Any statuses affecting the mech, i.e. "danger zone".
     * Each element must be a valid status, as defined by Mech.allowedStatuses.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String[] statuses;
    /**
     * Contains an array of allowed values for elements of Mech.statuses.
     *     Case-insensitive and stored in lowercase.
     */
    private static final String[] allowedStatuses = new String[] {"danger zone",
        "engaged", "exposed", "hidden", "invisible", "prone", "shut down"};

    /**
     * Any conditions affecting the mech, i.e. "immobilized".
     * Each element must be a valid condition, as defined by
     *     Mech.allowedConditions.
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String[] conditions;
    /**
     * Contains an array of allowed values for elements of Mech.conditions.
     *     Case-insensitive and stored in lowercase.
     */
    private static final String[] allowedConditions = new String[] {
        "immobilized", "impaired", "jammed", "lock on", "shredded", "slowed",
        "stunned"};

    /**
     * Sets up any of Mech's properties that aren't filled in by
     *     Mech.setFrame().
     */
    private Mech() {
        setOperatorNotes("");
        setSystems(new MechSystem[0]);
    }
    /**
     * Creates a Mech from a mech name and a frameID.
     * @param name a String containing the mech name of the new Mech.
     * @param frameID a String containing the frameID of the Frame to use to
     *     create the new Mech.
     */
    public Mech(String name, String frameID) {
        this();
        setName(name);
        setFrame(Database.getFrame(frameID));
    }
    /**
     * Creates a Mech from a mech name and a FrameEnum.
     * @param name a String containing the mech name of the new Mech.
     * @param frameEnum a FrameEnum containing the FrameEnum of the Frame to
     *     use to create the new Mech.
     */
    public Mech(String name, FrameEnum frameEnum) {
        this();
        setName(name);
        setFrame(Database.getFrame(frameEnum));
    }
    // TODO: remove if unused
    /**
     * Creates a new Mech given every Mech property that isn't
     *     calculated by the Mech's Frame.
     */
    public Mech(String name, Frame frame, String operatorNotes,
        int currentStructure, int currentHP, int currentStress,
        int currentHeat, int currentRepairs, MechSystem[] systems,
        String[] statuses, String[] conditions) {
        this();
        setName(name);
        setFrame(frame);
        setOperatorNotes(operatorNotes);
        setCurrentStructure(currentStructure);
        setCurrentHP(currentHP);
        setCurrentStress(currentStress);
        setCurrentHeat(currentHeat, false);
        setCurrentRepairs(currentRepairs);
        setSystems(systems);
        setStatuses(statuses);
        setConditions(conditions);
    }
    /**
     * Creates a deepest copy of the provided Mech.
     * @param mech a Mech to be copied.
     * @return a Mech deepest copy of the provided Mech.
     */
    public Mech(Mech mech) {
        // don't need to make copies of these for the ones using the mutator
        //     methods (i.e. mounts) because the mutators already do so
        // for the ones that DON'T use a mutator method (i.e. "copy.name =
        //     name"), make sure to use the proper accessor method instead
        //     of "property" if the property's type is mutable
        // max always comes before current
        setName(mech.name);
        setFrame(mech.frame);
        setManufacturer(mech.manufacturer);
        setFrameName(mech.frameName);
        setRole(mech.role);
        setFrameDescription(mech.frameDescription);
        setOperatorNotes(mech.operatorNotes);
        setSize(mech.size);
        setMaxStructure(mech.maxStructure);
        setCurrentStructure(mech.currentStructure);
        setMaxHP(mech.maxHP);
        setCurrentHP(mech.currentHP);
        setArmor(mech.armor);
        setMaxStress(mech.maxStress);
        setCurrentStress(mech.currentStress);
        setMaxHeatCapacity(mech.maxHeatCapacity);
        setCurrentHeat(mech.currentHeat, false);
        setEvasion(mech.evasion);
        setSpeed(mech.speed);
        setEDefense(mech.eDefense);
        setTechAttack(mech.techAttack);
        setSensors(mech.sensors);
        setMaxRepairCapacity(mech.maxRepairCapacity);
        setCurrentRepairs(mech.currentRepairs);
        setSaveTarget(mech.saveTarget);
        setSystemPoints(mech.systemPoints);
        setLimitedSystemsBonus(mech.limitedSystemsBonus);
        setTraits(mech.traits);
        setMounts(mech.mounts);
        setSystems(mech.systems);
        setStatuses(mech.statuses);
        setConditions(mech.conditions);
    }

    public String getName() {
        return name;
    }
    public Frame getFrame() {
        return new Frame(frame);
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getFrameName() {
        return frameName;
    }
    public String[] getRole() {
        return HelperMethods.copyOf(role);
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
    public int getCurrentHeat() {
        return currentHeat;
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
    public int getCurrentRepairs() {
        return currentRepairs;
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
        return HelperMethods.copyOf(traits);
    }
    public Mount[] getMounts() {
        return HelperMethods.copyOf(mounts);
    }
    public MechSystem[] getSystems() {
        return HelperMethods.copyOf(systems);
    }
    public String[] getStatuses() {
        return statuses;
    }
    public String[] getConditions() {
        return conditions;
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
     * @param frame a Frame which cannot be null.
     * @throws IllegalArgumentException if frame is null.
     */
    public void setFrame(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New frame is null");
        }
        frame = new Frame(frame);
        this.frame = frame;
        calculateAttributes();
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
        role = HelperMethods.copyOf(role);
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
     * Sets this.currentHeat to the provided value and adds or removes
     *     "danger zone" from this.statuses to match.
     * @param currentHeat an int which cannot be < 0 or > this.maxHeatCapacity.
     * @param modifyStatuses a boolean representing whether or not to
     *     automatically change the Mech's statuses based on currentHeat.
     * @throws IllegalArgumentException if currentHeat is < 0 or >
     *     this.maxHeatCapacity.
     */
    private void setCurrentHeat(int currentHeat, boolean modifyStatuses)
        {
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
        if (modifyStatuses) {
            if (this.currentHeat * 2 >= this.maxHeatCapacity) {
                // in danger zone
                addStatus("danger zone");
            } else {
                // not in danger zone
                removeStatus("danger zone");
            }
        }
    }
    /**
     * Helper method for setCurrentHeat(int). Allows that method to be called
     *     with a default value of true for the boolean.
     * @param currentHeat an int which cannot be < 0 or > this.maxHeatCapacity.
     */
    public void setCurrentHeat(int currentHeat) {
        setCurrentHeat(currentHeat, true);
    }
    /**
     * Sets this.maxHeatCapacity to the provided value.
     * @param maxHeatCapacity an int which cannot be < 1. Will print a warning
     *     if maxHeatCapacity is < this.currentHeat.
     * @throws IllegalArgumentException if maxHeatCapacity is < 1.
     */
    private void setMaxHeatCapacity(int maxHeatCapacity) {
        if (maxHeatCapacity < 1) {
            throw new IllegalArgumentException("New maxHeatCapacity value: "
                + maxHeatCapacity + " is < 1");
        }
        if (maxHeatCapacity < this.currentHeat) {
            System.out.println("[ WARNING ] maxHeatCapacity value provided: "
                + maxHeatCapacity + " is < currentHeat value: "
                + this.currentHeat);
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
    private void setMaxRepairCapacity(int maxRepairCapacity) {
        if (maxRepairCapacity < 0) {
            throw new IllegalArgumentException("New maxRepairCapacity value: "
                + maxRepairCapacity + " is < 0");
        }
        if (maxRepairCapacity < this.currentRepairs) {
            System.out.println("[ WARNING ] maxRepairCapacity value provided: "
                + maxRepairCapacity + " is < currentRepairs value: "
                + this.currentRepairs);
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
        traits = HelperMethods.copyOf(traits);
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
        mounts = HelperMethods.copyOf(mounts);
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
        mount = new Mount(mount);
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
        systems = HelperMethods.copyOf(systems);
        // TODO: systems[0].name <- why is this visible?
        this.systems = systems;
    }
    /**
     * Sets this.statuses to the provided value.
     * @param statuses a String[] which cannot be null or contain invalid
     *     elements as defined by Mech.allowedStatuses.
     * @throws IllegalArgumentException if statuses is null or contains invalid
     *     elements as defined by Mech.allowedStatuses.
     */
    public void setStatuses(String[] statuses) {
        boolean isValidStatus = false;
        String statusString = "";

        if (statuses == null) {
            throw new IllegalArgumentException("New statuses value is null");
        }
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == null) {
                throw new IllegalArgumentException("New statuses array"
                    + " contains a null element");
            }
            statuses[i] = statuses[i].toLowerCase();
            statusString = statuses[i];
            isValidStatus = false;
            for (String status : Mech.allowedStatuses) {
                if (statusString.equals(status)) {
                    isValidStatus = true;
                }
            }
            if (! isValidStatus) {
                throw new IllegalArgumentException("New statuses array contains"
                    + " an invalid element: \"" + statusString + "\"");
            }
        }
        statuses = HelperMethods.copyOf(statuses);
        this.statuses = statuses;
    }
    /**
     * Sets this.conditions to the provided value.
     * @param conditions a String[] which cannot be null or contain invalid
     *     elements as defined by Mech.allowedConditions.
     * @throws IllegalArgumentException if conditions is null or contains
     *     invalid elements as defined by Mech.allowedConditions.
     */
    public void setConditions(String[] conditions) {
        boolean isValidCondition = false;
        String conditionString = "";

        if (conditions == null) {
            throw new IllegalArgumentException("New conditions value is"
                + " null");
        }
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] == null) {
                throw new IllegalArgumentException("New conditions array"
                    + " contains a null element");
            }
            conditions[i] = conditions[i].toLowerCase();
            conditionString = conditions[i];
            isValidCondition = false;
            for (String condition : Mech.allowedConditions) {
                if (conditionString.equals(condition)) {
                    isValidCondition = true;
                }
            }
            if (! isValidCondition) {
                throw new IllegalArgumentException("New conditions array"
                    + " contains an invalid element: \"" + conditionString
                    + "\"");
            }
        }
        conditions = HelperMethods.copyOf(conditions);
        this.conditions = conditions;
    }

    /**
     * A helper method which outputs the mech's size, formatted properly so that
     *     it is human-readable. Used in Mech.outputStats("full", int, int[]).
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
     * Adds the provided status to this.statuses.
     * @param newStatus a String containing the new status. Must be a valid
     *     status as defined by Mech.allowedStatuses.
     * @param addDuplicate a boolean representing whether or not to add a second
     *     version of the same status if that status is already present in
     *     this.statuses.
     * @throws IllegalArgumentException if newStatus is an invalid status as
     *     defined by Mech.allowedStatuses.
     */
    public void addStatus(String newStatus, boolean addDuplicate) {
        boolean isValid = false;
        boolean containsStatus = false;

        for (String status : Mech.allowedStatuses) {
            if (newStatus.equals(status)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("newStatus value: \"" + newStatus
                + "\" is an invalid status");
        }
        for (String status : this.statuses) {
            if (status.equals(newStatus)) {
                containsStatus = true;
            }
        }
        // if (! containsStatus) || addDuplicate:
        if (! containsStatus || addDuplicate) {
            setStatuses(HelperMethods.append(this.statuses, newStatus));
        }
    }
    /**
     * A helper method for addStatus(String, boolean). Allows that method to be
     *     called with a default value of false for the boolean.
     * @param status a String containing the new status. Must be a valid
     *     status as defined by Mech.allowedStatuses.
     */
    public void addStatus(String status) {
        addStatus(status, false);
    }
    /**
     * Removes the provided status from this.statuses.
     * @param oldStatus a String containing the status to be removed. Must be a
     *     valid status as defined by Mech.allowedStatuses.
     * @param removeAll a boolean representing whether to remove all instances
     *     of a status if multiple instances are present, or just the specified
     *     one.
     * @throws IllegalArgumentException if oldStatus is an invalid status as
     *     defined by Mech.allowedStatuses.
     */
    public void removeStatus(String oldStatus, boolean removeAll) {
        boolean isValid = false;
        String[] newStatuses;

        for (String status : Mech.allowedStatuses) {
            if (oldStatus.equals(status)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("oldStatus value: \"" + oldStatus
                + "\" is an invalid status");
        }
        for (int i = 0; i < this.statuses.length; i++) {
            if (this.statuses[i].equals(oldStatus)) {
                newStatuses = new String[this.statuses.length - 1];
                for (int j = 0; j < this.statuses.length; j++) {
                    if (j == i) {
                        continue;
                    }
                    if (j < i) {
                        newStatuses[j] = this.statuses[j];
                    } else {
                        newStatuses[j - 1] = this.statuses[j];
                    }
                }
                setStatuses(newStatuses);
                break;
            }
        }
        if (removeAll) {
            removeStatus(oldStatus, removeAll);
        }
    }
    /**
     * Helper method for removeStatus(String, boolean). Allows that method to be
     *     run with a default value of false for the boolean.
     * @param oldStatus a String containing the status to be removed. Must be a
     *     valid status as defined by Mech.allowedStatuses.
     */
    public void removeStatus(String oldStatus) {
        removeStatus(oldStatus, false);
    }
    /**
     * Adds the provided condition to this.conditions.
     * @param newCondition a String containing the new condition. Must be a
     *     valid condition as defined by Mech.allowedConditions.
     * @param addDuplicate a boolean representing whether or not to add a second
     *     version of the same condition if that condition is already present in
     *     this.conditions.
     * @throws IllegalArgumentException if newCondition is an invalid condition
     *     as defined by Mech.allowedConditions.
     */
    public void addCondition(String newCondition, boolean addDuplicate) {
        boolean isValid = false;
        boolean containsCondition = false;

        for (String condition : Mech.allowedConditions) {
            if (newCondition.equals(condition)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("newCondition value: \""
                + newCondition + "\" is an invalid condition");
        }
        for (String condition : this.conditions) {
            if (condition.equals(newCondition)) {
                containsCondition = true;
            }
        }
        // if (! containsCondition) || addDuplicate:
        if (! containsCondition || addDuplicate) {
            setConditions(HelperMethods.append(this.conditions, newCondition));
        }
    }
    /**
     * A helper method for addCondition(String, boolean). Allows that method to
     *     be called with a default value of false for the boolean.
     * @param condition a String containing the new condition. Must be a valid
     *     condition as defined by Mech.allowedConditions.
     */
    public void addCondition(String condition) {
        addCondition(condition, false);
    }
    /**
     * Removes the provided condition from this.conditions.
     * @param oldCondition a String containing the condition to be removed. Must
     *     be a valid condition as defined by Mech.allowedConditions.
     * @param removeAll a boolean representing whether to remove all instances
     *     of a condition if multiple instances are present, or just the
     *     specified one.
     * @throws IllegalArgumentException if oldCondition is an invalid condition
     *     as defined by Mech.allowedConditions.
     */
    public void removeCondition(String oldCondition, boolean removeAll) {
        boolean isValid = false;
        String[] newConditions;

        for (String condition : Mech.allowedConditions) {
            if (oldCondition.equals(condition)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("oldCondition value: \""
                + oldCondition + "\" is an invalid condition");
        }
        for (int i = 0; i < this.conditions.length; i++) {
            if (this.conditions[i].equals(oldCondition)) {
                newConditions = new String[this.conditions.length - 1];
                for (int j = 0; j < this.conditions.length; j++) {
                    if (j == i) {
                        continue;
                    }
                    if (j < i) {
                        newConditions[j] = this.conditions[j];
                    } else {
                        newConditions[j - 1] = this.conditions[j];
                    }
                }
                setConditions(newConditions);
                break;
            }
        }
        if (removeAll) {
            removeCondition(oldCondition, removeAll);
        }
    }
    /**
     * Helper method for removeCondition(String, boolean). Allows that method to
     *     be run with a default value of false for the boolean.
     * @param oldCondition a String containing the condition to be removed. Must
     *     be a valid condition as defined by Mech.allowedConditions.
     */
    public void removeCondition(String oldCondition) {
        removeCondition(oldCondition, false);
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
        // TODO: add possibility for multiple core bonuses (because any of the
        //     mount core bonuses can be layered on top of something like
        //     Auto-Stabilizing Hardpoints or Overpower Caliber)
        // TODO: update to account for the Engineer talent as well as the
        //     Improved Armament and Integrated Weapon core bonuses
        if (this.frame == null) {
            throw new IllegalArgumentException("calculateAttributes() was"
                + " called while this.frame was set to null");
        }
        setMaxStructure(this.frame.getStructure());
        setCurrentStructure(this.maxStructure);
        setMaxStress(this.frame.getStress());
        setCurrentStress(this.maxStress);
        
        // Hull
        setMaxHP(this.frame.getHP() + (mechSkills[0] * 2));
        setCurrentHP(this.maxHP);
        setMaxRepairCapacity(this.frame.getRepairCapacity()
            + (mechSkills[0] / 2));
        setCurrentRepairs(this.maxRepairCapacity);

        // Agility
        setEvasion(this.frame.getEvasion() + mechSkills[1]);
        setSpeed(this.frame.getSpeed() + (mechSkills[1] / 2));

        // Systems
        setEDefense(this.frame.getEDefense() + mechSkills[2]);
        setTechAttack(this.frame.getTechAttack() + mechSkills[2]);
        setSystemPoints(this.frame.getSystemPoints() + (mechSkills[2] / 2));

        // Engineering
        // setMaxHeatCapacity() swapped with setCurrentHeat() because the
        //     mutators may throw exceptions otherwise
        setMaxHeatCapacity(this.frame.getHeatCapacity() + mechSkills[3]);
        setCurrentHeat(0, false);
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
                mounts = HelperMethods.add(mounts, 
                    new Mount("improved armament core bonus",
                    null, "",
                    "improved armament", null), 0);
            }
        }
        for (String coreBonus : coreBonuses) {
            if (coreBonus.equals("integrated weapon")) {
                mounts = HelperMethods.add(mounts, 
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
                mounts = HelperMethods.add(mounts, 
                    new Mount("integrated weapon",
                    new Weapon(weaponName, 1, 9),
                    "", "", talent), 0);
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
        setStatuses(new String[0]);
        setConditions(new String[0]);
    }
    /**
     * Deals (damageAmount) damage of type (damageType) to this Mech.
     * @param damageAmount an int containing the amount of damage to deal. Must
     *     be > 0.
     * @param damageType a String containing the type of the damage to deal.
     *     Must be a valid damage type as defined by
     *     HelperMethods.allowedDamageTypes.
     * @throws IllegalArgumentException if damageAmount is < 1.
     */
    public void receiveDamage(int damageAmount, String damageType) {
        // TODO: fill out with damage mitigation - armor, resistance etc
        boolean isValid = false;
        int remainingDamage = damageAmount;
        int damageToTake;
        int newCurrentHP;

        if (damageAmount < 1) {
            throw new IllegalArgumentException("damageAmount value: "
                + damageAmount + " is < 1");
        }
        if (damageType == null) {
            throw new IllegalArgumentException("damageType is null");
        }
        for (String allowedType : HelperMethods.allowedDamageTypes) {
            if (damageType.equals(allowedType)) {
                isValid = true;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("damageType value: \""
                + damageType + "\" is an invalid damage type");
        }
        while (remainingDamage > 0) {
            damageToTake = Math.min(remainingDamage, this.currentHP);
            newCurrentHP = this.currentHP - damageToTake;
            setCurrentHP(newCurrentHP);
            // if the amount of damage remaining to take is greater than our
            //     mech's maximum HP, we will structure no matter what its
            //     current HP is
            if (remainingDamage > this.currentHP) {
                // we're about to structure
                if (this.currentStructure > 0) {
                    receiveStructureDamage();
                } else {
                    // Structure is 0 and the mech is taking structure damage,
                    //     automatically destroyed :(
                    destroy();
                    remainingDamage = 0;
                    break;
                }
            }
            remainingDamage -= damageToTake;
        }
    }
    /**
     * Deals (heatAmount) heat to this Mech.
     * @param heatAmount an int containing the amount of heat to deal. Must be >
     *     0.
     * @throws IllegalArgumentException if heatAmount is < 1.
     */
    public void receiveHeat(int heatAmount) {
        // TODO: fill out with mitigation, resistance etc
        // basically an attempt to convert this.currentHeat into something like
        //     how HP works; stores how much heat this Mech can presently take
        //     before stressing
        int currHeat;
        int remainingHeat = heatAmount;
        int heatToTake;
        int newCurrentHeat;

        if (heatAmount < 1) {
            throw new IllegalArgumentException("heatAmount value: " + heatAmount
                + " is < 1");
        }
        while (remainingHeat > 0) {
            currHeat = this.maxHeatCapacity - this.currentHeat;
            heatToTake = Math.min(remainingHeat, currHeat);
            newCurrentHeat = this.currentHeat + heatToTake;
            setCurrentHeat(newCurrentHeat);
            // if the amount of heat remaining to take is greater than our
            //     mech's maximum heat capacity, we will stress no matter what
            //     its current heat level is
            if (remainingHeat > this.maxHeatCapacity) {
                // we're about to stress
                if (this.currentStress > 0) {
                    receiveStressDamage();
                } else {
                    // Stress is 0 and the mech is taking stress damage,
                    //     automatically destroyed :(
                    destroy();
                    remainingHeat = 0;
                    break;
                }
            }
            remainingHeat -= heatToTake;
        }
    }
    /**
     * Deals (burnAmount) burn to this Mech.
     * @param burnAmount an int containing the amount of burn to deal. Must be >
     *     0.
     * @throws IllegalArgumentException if burnAmount is < 1.
     */
    public void receiveBurn(int burnAmount) {
        // TODO: fill out
        if (burnAmount < 1) {
            throw new IllegalArgumentException("burnAmount value: " + burnAmount
                + " is < 1");
        }
    }
    /**
     * Deals (structureDamage) structure damage to this Mech.
     * @param structureDamage an int containing the number of times to deal 1
     *     structure damage. Must be > 0.
     * @throws IllegalArgumentException if structureDamage is < 1.
     */
    public void receiveStructureDamage(int structureDamage) {
        if (structureDamage < 1) {
            throw new IllegalArgumentException("structureDamage value: "
                + structureDamage + " is < 1");
        }
        for (int i = 0; i < structureDamage; i++) {
            receiveStructureDamage();
        }
    }
    /**
     * Structures this Mech once (Deals 1 structure damage to it). Automatically
     *     sets this.currentHP back to this.maxHP, as per game rules.
     */
    public void receiveStructureDamage() {
        setCurrentStructure(this.currentStructure - 1);
        setCurrentHP(this.maxHP);
    }
    /**
     * Deals (stressDamage) stress damage to this Mech.
     * @param stressDamage an int containing the number of times to deal 1
     *     stress damage. Must be > 0.
     * @throws IllegalArgumentException if stressDamage is < 1.
     */
    public void receiveStressDamage(int stressDamage) {
        if (stressDamage <= 0) {
            throw new IllegalArgumentException("stressDamage value: "
                + stressDamage + " is < 1");
        }
        for (int i = 0; i < stressDamage; i++) {
            receiveStressDamage();
        }
    }
    /**
     * Stresses this Mech once (Deals 1 stress damage to it). Automatically sets
     *     this.currentHeat back to this.maxHeatCapacity, as per game rules.
     */
    public void receiveStressDamage() {
        setCurrentStress(this.currentStress - 1);
        setCurrentHeat(0);
    }
    /**
     * Is called whenever this Mech is destroyed. Usually means that
     *     this.currentStructure or this.currentStress has been reduced to 0,
     *     and this.currentHP or this.currentHeat modified to 0 or
     *     this.maxHeatCapacity, respectively, although this is not always true
     *     - Self-Destruct, for example.
     */
    public void destroy() {
        // see pg. 74 - wrecks grant hard cover
        // TODO: fill out
        System.out.println("This Mech has been destroyed");
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
                + " Mech.generateOutput(\"full\", int) instead of"
                + " Mech.generateOutput(\"full\", int[], int); therefore, mech"
                + " skills value was not provided.");
        }

        return outputString;
    }
    /**
     * Generates the output associated with the mech portion of the COMP/CON
     *     "generate statblock" feature. Only returns something other than ""
     *     when outputType is "full".
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
                + " Mech.generateOutput(\"mech build\") instead of"
                + " Mech.generateOutput(\"mech build\", int); therefore,"
                + " limited systems bonus value was not provided.");
        } else if (outputType.equals("full")) {
            outputString += "[ MECH ]\n";
            outputString += "  « " + this.name + " »\n";
            outputString += outputStats("full", grit, mechSkills);
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
     *     Mech.outputStats(String, int, int[])
     */
    public String outputStats(String outputType) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            outputString += String.format(
                "  STRUCTURE:%d HP:%d ARMOR:%d\n",
                this.maxStructure, this.maxHP, this.armor
            );
            outputString += String.format(
                "  STRESS:%d HEATCAP:%d REPAIR:%d\n",
                this.maxStress, this.maxHeatCapacity, this.maxRepairCapacity
            );
            if (this.techAttack > 0) {
                outputString += "  TECH ATK:+" + this.techAttack;
            } else {
                outputString += "  TECH ATK:" + this.techAttack;
            }
            if (this.limitedSystemsBonus > -1) {
                outputString += " LIMITED:+" + this.limitedSystemsBonus;
            } else {
                outputString += " LIMITED:" + this.limitedSystemsBonus;
            }
            outputString += "\n";
            outputString += String.format(
                "  SPD:%d EVA:%d EDEF:%d SENSE:%d SAVE:%d\n",
                this.speed, this.evasion, this.eDefense, this.sensors,
                this.saveTarget
            );
        } else if (outputType.equals("full")) {
            throw new IllegalArgumentException("Called"
                + " Mech.outputStats(\"full\") but grit value and mech skills"
                + " array was not provided");
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
     * @param mechSkills an int[] of length 4 containing the mech skills of the
     *     Pilot associated with this Mech through the parent LancerCharacter.
     * @return a String containing the requested output.
     */
    public String outputStats(String outputType, int grit, int[] mechSkills) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("full")) {
            outputString += String.format(
                "  H:%d A:%d S:%d E:%d SIZE:%s\n",
                mechSkills[0], mechSkills[1], mechSkills[2], mechSkills[3],
                outputSize()
            );
            outputString += String.format(
                "  STRUCTURE:%d/%d HP:%d/%d ARMOR:%d\n",
                this.currentStructure, this.maxStructure, this.currentHP,
                this.maxHP, this.armor
            );
            outputString += String.format(
                "  STRESS:%d/%d HEAT:%d/%d REPAIR:%d/%d\n",
                this.currentStress, this.maxStress, this.currentHeat,
                this.maxHeatCapacity, this.currentRepairs,
                this.maxRepairCapacity
            );
            outputString += String.format(
                "  ATK BONUS:%d TECH ATK:%d LTD BONUS:%d\n",
                grit, this.techAttack, this.limitedSystemsBonus
            );
            outputString += String.format(
                "  SPD:%d EVA:%d EDEF:%d SENS:%d SAVE:%d\n",
                this.speed, this.evasion, this.eDefense, this.sensors,
                this.saveTarget
            );
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
        // "  INTEGRATED WEAPON: Nexus (Light)\n"
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
     *     something other than "" when outputType is "full".
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
                + " Mech.outputSystems(\"mech build\") but limited systems"
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
            //     " High-Stress Mag Clamps, ATHENA-Class NHP\n"
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