/**
 * Represents a single mech. Contains information about that mech's origin frame
 *     (the stat block after which it is patterned), its stats, its mounts, and
 *     its systems, among other statistics.
 * A Frame object is required to create a non-placeholder Mech object. The Frame
 *     object's stats, traits, and mounts serve as the base on top of which mech
 *     skills, weapons, systems, and other modifications are added.
 */
public class Mech {
    // TODO: get an example mech name from the core rulebook for this
    /**
     * The name of this mech (NOT its frame name, the name given to this
     *     specific chassis).
     * Can be any String except "". Cannot be null. Is set to "" on construction
     *     from Mech().
     */
    private String name;
    
    /**
     * The frame that this mech is patterned after (i.e. Swallowtail) as a Frame
     *     object.
     * Can be any Frame except a placeholder Frame. Is set to a placeholder
     *     Frame on construction from Mech().
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
     * Use Frame.getName() to get the raw value and Frame.outputName() to obtain
     *     it properly formatted.
     */
    private String frameName;
    // ID and frameEnum from Frame were intentionally omitted here
    /**
     * The mech's origin frame's role (i.e. "balanced"). Multiple items are 
     *     stored as seperate elements (i.e "Controller/Support" would be stored
     *     as {"controller", "support"}).
     * Each element must be one of the following values:
     *     "artillery", "balanced", "controller", "striker", "support".
     * Case-insensitive and stored in lowercase. Cannot be null. Is set to
     *     "" at construction.
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
     * Is set to -1 on construction, but must be a minimum of 0 otherwise.
     */
    private int currentHP;
    /**
     * The mech's max HP value.
     * Is set to -1 on construction, but must be a minimum of 1 otherwise.
     */
    private int maxHP;
    /**
     * The mech's armor value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
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
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int currentHeatCapacity;
    /**
     * The mech's max heat capacity.
     * Is set to -1 at construction, but must be a minimum of 1 otherwise.
     */
    private int maxHeatCapacity;

    // evasion and speed
    /**
     * The mech's evasion value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int evasion;
    /**
     * The mech's speed value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int speed;

    // e-defense and tech attack
    /**
     * The mech's e-defense value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int eDefense;
    /**
     * The mech's tech attack value.
     * Can be any integer.
     */
    private int techAttack;

    // sensors and repair capacity
    /**
     * The mech's sensors value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int sensors;
    /**
     * The mech's current repair capacity value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int currentRepairCapacity;
    /**
     * The mech's max repair capacity value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int maxRepairCapacity;

    // save target and system points
    /**
     * The mech's save target value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int saveTarget;
    /**
     * The mech's system points value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int systemPoints;

    /**
     * The mech's limited systems bonus value.
     * Is set to -1 at construction, but must be a minimum of 0 otherwise.
     */
    private int limitedSystemsBonus;

    /**
     * The mech's origin frame's traits (i.e.
     *     {"Initiative", "Replaceable Parts"}).
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
        this.frameDescription = "";
        this.operatorNotes = "";
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
        this.traits = new String[0];
        this.mounts = new Mount[0];
        this.systems = new MechSystem[0];
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
    public Mech(String name, Frame frame, String operatorNotes, Mount[] mounts,
        MechSystem[] systems, int size, int currentStructure, int maxStructure,
        int currentHP, int maxHP, int armor, int currentStress, int maxStress,
        int currentHeatCapacity, int maxHeatCapacity, int evasion, int speed,
        int eDefense, int techAttack, int sensors, int currentRepairCapacity,
        int maxRepairCapacity, int saveTarget, int systemPoints,
        int limitedSystemsBonus) {
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
        this.frameDescription = "";
        this.traits = new String[0];
        setName(name);
        setFrame(frame);
        setOperatorNotes(operatorNotes);
        setSize(size);
        setMaxStructure(maxStructure);
        setCurrentStructure(currentStructure);
        setMaxHP(maxHP);
        setCurrentHP(currentHP);
        setArmor(armor);
        setMaxStress(maxStress);
        setCurrentStress(currentStress);
        setMaxHeatCapacity(maxHeatCapacity);
        setCurrentHeatCapacity(currentHeatCapacity);
        setEvasion(evasion);
        setSpeed(speed);
        setEDefense(eDefense);
        setTechAttack(techAttack);
        setSensors(sensors);
        setMaxRepairCapacity(maxRepairCapacity);
        setCurrentRepairCapacity(currentRepairCapacity);
        setSaveTarget(saveTarget);
        setSystemPoints(systemPoints);
        setLimitedSystemsBonus(limitedSystemsBonus);
        setMounts(mounts);
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
        return role;
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
        return traits;
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
    public void setFrame(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("New frame is null");
        }
        frame = frame.copyOf();
        this.frame = frame;
        calculateAttributes();
        setMounts(frame.getMounts());
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
    public void setFrameName(String frameName) {
        if (frameName == null) {
            throw new IllegalArgumentException("New frame name is null");
        }
        if (frameName.equals("")) {
            throw new IllegalArgumentException("New frame name is \"\"");
        }
        frameName = frameName.toLowerCase();
        this.frameName = frameName;
    }
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
                }
            }
            if (! isValidRole) {
                throw new IllegalArgumentException("New role array contains an "
                    + "invalid role value: \"" + roleString + "\"");
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
    private void setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("New size is < 1");
        }
        this.size = size;
    }
    public void setCurrentStructure(int currentStructure) {
        if (currentStructure < 0) {
            throw new IllegalArgumentException("New currentStructure value is"
                + " < 0");
        }
        if (getMaxStructure() < currentStructure) {
            throw new IllegalArgumentException("currentStructure value"
                + " provided: " + currentStructure + " is > maxStructure value:"
                + " " + getMaxStructure());
        }
        this.currentStructure = currentStructure;
    }
    private void setMaxStructure(int maxStructure) {
        if (maxStructure < 1) {
            throw new IllegalArgumentException("New maxStructure value is <"
                + " 1");
        }
        if (maxStructure < getCurrentStructure()) {
            System.out.println("[ WARNING ] maxStructure value provided: "
                + maxStructure + " is < currentStructure value: "
                + getCurrentStructure());
        }
        this.maxStructure = maxStructure;
    }
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            throw new IllegalArgumentException("New currentHP value provided is"
                + " < 0");
        }
        if (getMaxHP() < currentHP) {
            throw new IllegalArgumentException("currentHP value provided: "
                + currentHP + " is > maxHP value: " + getMaxHP());
        }
        this.currentHP = currentHP;
    }
    private void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New maxHP value is < 1");
        }
        if (maxHP < getCurrentHP()) {
            System.out.println("[ WARNING ] maxHP value provided: " + maxHP
                + " is < currentHP value: " + getCurrentHP());
        }
        this.maxHP = maxHP;
    }
    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor is < 0");
        }
        this.armor = armor;
    }
    public void setCurrentStress(int currentStress) {
        if (currentStress < 0) {
            throw new IllegalArgumentException("New currentStress value is <"
                + " 0");
        }
        if (getMaxStress() < currentStress) {
            throw new IllegalArgumentException("currentStress value provided: "
                + currentStress + " is > maxStress value: " + getMaxStress());
        }
        this.currentStress = currentStress;
    }
    private void setMaxStress(int maxStress) {
        if (maxStress < 1) {
            throw new IllegalArgumentException("New maxStress value is < 1");
        }
        if (maxStress < getCurrentStress()) {
            System.out.println("[ WARNING ] maxStress value provided: "
                + maxStress + " is < currentStress value: "
                + getCurrentStress());
        }
        this.maxStress = maxStress;
    }
    public void setCurrentHeatCapacity(int currentHeatCapacity) {
        if (currentHeatCapacity < 0) {
            throw new IllegalArgumentException("New currentHeatCapacity value"
                + " is < 0");
        }
        if (getMaxHeatCapacity() < currentHeatCapacity) {
            throw new IllegalArgumentException("currentHeatCapacity value"
                + " provided: " + currentHeatCapacity + " is > maxHeatCapacity"
                + " value: " + getMaxHeatCapacity());
        }
        this.currentHeatCapacity = currentHeatCapacity;
    }
    private void setMaxHeatCapacity(int maxHeatCapacity) {
        if (maxHeatCapacity < 1) {
            throw new IllegalArgumentException("New maxHeatCapacity value is"
                + " < 1");
        }
        if (maxHeatCapacity < getCurrentHeatCapacity()) {
            System.out.println("[ WARNING ] maxHeatCapacity value provided: "
                + maxHeatCapacity + " is < currentHeatCapacity value: "
                + getCurrentHeatCapacity());
        }
        this.maxHeatCapacity = maxHeatCapacity;
    }
    private void setEvasion(int evasion) {
        if (evasion < 0) {
            throw new IllegalArgumentException("New evasion is < 0");
        }
        this.evasion = evasion;
    }
    private void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("New speed is < 0");
        }
        this.speed = speed;
    }
    private void setEDefense(int eDefense) {
        if (eDefense < 0) {
            throw new IllegalArgumentException("New e-defense is < 0");
        }
        this.eDefense = eDefense;
    }
    private void setTechAttack(int techAttack) {
        this.techAttack = techAttack;
    }
    private void setSensors(int sensors) {
        if (sensors < 0) {
            throw new IllegalArgumentException("New sensors is < 0");
        }
        this.sensors = sensors;
    }
    public void setCurrentRepairCapacity(int currentRepairCapacity) {
        if (currentRepairCapacity < 0) {
            throw new IllegalArgumentException("New currentRepairCapacity value"
                + " is < 0");
        }
        if (getMaxRepairCapacity() < currentRepairCapacity) {
            throw new IllegalArgumentException("currentRepairCapacity value"
                + " provided: " + currentRepairCapacity + " is >"
                + " maxRepairCapacity value: " + getMaxRepairCapacity());
        }
        this.currentRepairCapacity = currentRepairCapacity;
    }
    private void setMaxRepairCapacity(int maxRepairCapacity) {
        if (maxRepairCapacity < 0) {
            throw new IllegalArgumentException("New maxRepairCapacity value is"
                + " < 0");
        }
        if (maxRepairCapacity < getCurrentRepairCapacity()) {
            System.out.println("[ WARNING ] maxRepairCapacity value provided: "
                + maxRepairCapacity + " is < currentRepairCapacity value: "
                + getCurrentRepairCapacity());
        }
        this.maxRepairCapacity = maxRepairCapacity;
    }
    private void setSaveTarget(int saveTarget) {
        if (saveTarget < 0) {
            throw new IllegalArgumentException("New save target is < 0");
        }
        this.saveTarget = saveTarget;
    }
    private void setSystemPoints(int systemPoints) {
        if (systemPoints < 0) {
            throw new IllegalArgumentException("New system points are < 0");
        }
        this.systemPoints = systemPoints;
    }
    private void setLimitedSystemsBonus(int limitedSystemsBonus) {
        if (limitedSystemsBonus < 0) {
            throw new IllegalArgumentException("New limited systems bonus is"
                + " < 0");
        }
        this.limitedSystemsBonus = limitedSystemsBonus;
    }
    private void setTraits(String[] traits) {
        this.traits = traits;
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
        mounts = HelperFunctions.copyOf(mounts);
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
        mount = mount.copyOf();
        this.mounts[mountIndex] = mount;
    }
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
     */
    public void calculateAttributes(int[] mechSkills, String[] coreBonuses,
        Talent[] talents) {
        // TODO: update to account for the Engineer talent as well as the
        //     Improved Armament and Integrated Weapon core bonuses
        if (frame == null) {
            throw new IllegalArgumentException("calculateAttributes() was"
                + " called while frame was set to null");
        }
        setMaxStructure(frame.getStructure());
        setCurrentStructure(currentStructure);
        setMaxStress(frame.getStress());
        setCurrentStress(currentStress);
        
        // Hull
        setMaxHP(frame.getHP() + (mechSkills[0] * 2));
        setCurrentHP(getMaxHP());
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
        // setMaxHeatCapacity() swapped with setCurrentHeatCapacity() because
        //     the mutators may throw exceptions otherwise
        setMaxHeatCapacity(frame.getHeatCapacity() + mechSkills[3]);
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
                    new Weapon(), "",
                    "improved armament", null), 0);
            }
        }
        for (String coreBonus : coreBonuses) {
            if (coreBonus.equals("integrated weapon")) {
                mounts = HelperFunctions.add(mounts, 
                    new Mount("integrated weapon core bonus",
                    new Weapon(), "",
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
        
        setManufacturer(frame.getManufacturer());
        setFrameName(frame.getName());
        setRole(frame.getRole());
        setFrameDescription(frame.getFrameDescription());
        setSize(frame.getSize());
        setArmor(frame.getArmor());
        setSensors(frame.getSensors());
        setSaveTarget(frame.getSaveTarget());
        setTraits(frame.getTraits());
        setMounts(frame.getMounts());
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
     */
    public String generateOutput(String outputType, int[] mechSkills,
        int grit) {
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
    /**
     * A helper method which generates a line of text containing output about
     *     the mech's systems used in Mech.generateOutput(). Only returns
     *     something other than "" when outputType is "mech build" or "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing the requested output.
     */
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
            //     "  High-Stress Mag Clamps, ATHENA-Class NHP\n"
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
            //       "  Pattern-A Smoke Charges, Seismic Ripper,\n"
            //       "  High-Stress Mag Clamps, ATHENA-Class NHP\n"
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
    /**
     * A helper method which generates a snippet of text containing output about
     *     a given system of this Mech object used in Mech.outputSystems(). Only
     *     returns something other than "" when outputType is "mech build" or
     *     "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @param system a MechSystem containing the system to generate output for.
     * @return a String containing the requested output.
     */
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
    /**
     * A helper method which outputs the mech's size, formatted properly so that
     *     it is human-readable. Used in Mech.outputStats("full", int).
     * @return a String containing the requested output.
     */
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
        if (this.frame.isPlaceholder()) {
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
    /**
     * A method checking whether every property of this object is set to its
     *     placeholder value.
     * @return a boolean representing the result of the check.
     */
    public boolean isPlaceholder() {
        if (! getName().equals("")) {
            return false;
        }
        if (! this.frame.isPlaceholder()) {
            return false;
        }
        if (! getOperatorNotes().equals("")) {
            return false;
        }
        if (this.mounts.length != 0) {
            return false;
        }
        if (this.systems.length != 0) {
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
        if (this.techAttack != -1) {
            return false;
        }
        if (this.sensors != -1) {
            return false;
        }
        if (this.currentRepairCapacity != -1) {
            return false;
        }
        if (this.maxRepairCapacity != -1) {
            return false;
        }
        if (this.saveTarget != -1) {
            return false;
        }
        if (this.systemPoints != -1) {
            return false;
        }
        if (this.limitedSystemsBonus != -1) {
            return false;
        }

        return true;
    }
    /**
     * Returns a deepest copy of this object.
     * @return a Mech deepest copy of this object.
     */
    public Mech copyOf() {
        // don't need to make copies of these for the ones using the mutator
        //     methods (i.e. mounts) because the mutators already do so
        Mech copy = new Mech();
        
        copy.name = this.name;
        copy.frame = this.frame.copyOf();
        copy.setOperatorNotes(this.operatorNotes);
        copy.setMounts(this.mounts);
        copy.setSystems(this.systems);
        copy.size = this.size;
        copy.currentStructure = this.currentStructure;
        copy.maxStructure = this.maxStructure;
        copy.currentHP = this.currentHP;
        copy.maxHP = this.maxHP;
        copy.armor = this.armor;
        copy.currentStress = this.currentStress;
        copy.maxStress = this.maxStress;
        copy.currentHeatCapacity = this.currentHeatCapacity;
        copy.maxHeatCapacity = this.maxHeatCapacity;
        copy.evasion = this.evasion;
        copy.speed = this.speed;
        copy.eDefense = this.eDefense;
        copy.setTechAttack(this.techAttack);
        copy.sensors = this.sensors;
        copy.currentRepairCapacity = this.currentRepairCapacity;
        copy.maxRepairCapacity = this.maxRepairCapacity;
        copy.saveTarget = this.saveTarget;
        copy.systemPoints = this.systemPoints;
        copy.limitedSystemsBonus = this.limitedSystemsBonus;

        return copy;
    }
}