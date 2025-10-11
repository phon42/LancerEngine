/**
 * Represents the pilot portion of a Lancer character, i.e. the non-mech items.
 *     Contains various pieces of information such as the pilot's name,
 *     callsign, stats, licenses, and so on.
 * 
 * Requires a pilot name and pilot callsign to be instantiated.
 * 
 * Used in LancerCharacter.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Pilot {
    // TODO: add some set of inherently available actions
    // TODO: add functions for Pilot.licenseList and Pilot.talents to check if
    //     individual items are present or to get an item with a specific name,
    //     see SkillTriggersList
    // ---Dossier-------------------
    // name and callsign
    /**
     * The pilot's name (i.e. Taro Oda).
     * Can be any String other than "". Cannot be null.
     */
    private String name;
    /**
     * The pilot's callsign (i.e. Marigold).
     * Can be any String other than "". Cannot be null.
     */
    private String callsign;

    // (optional) details
    /**
     * The player name (i.e. Jacques).
     * Can be any String. Cannot be null.
     */
    private String player;
    /**
     * The pilot's status.
     * Must be one of the following values:
     *     "active", "inactive", "retired", "missing in action",
     *     "killed in action", "unknown"
     * Case-insensitive and stored in lowercase. Cannot be null.
     * Use Pilot.getStatus() to get the raw value and Pilot.outputStatus() to
     *     obtain it properly formatted.
     */
    private String status;
    /**
     * Contains an array of all possible allowed pilot statuses.
     */
    private static final String[] allowedStatuses = {
        "active", "inactive", "retired", "missing in action",
        "killed in action", "unknown"
    };
    /**
     * The pilot's background (i.e. "celebrity").
     * Case-insensitive and stored in lowercase. Can be any String. Cannot be
     *     null.
     */
    private String background;

    // further optional details
    /**
     * The pilot's biography.
     * Can be any String. Cannot be null.
     */
    private String biography;
    /**
     * The pilot's appearance.
     * Can be any String. Cannot be null.
     */
    private String appearance;
    /**
     * The pilot's player notes.
     * Can be any String. Cannot be null.
     */
    private String playerNotes;

    // ---Narrative Profile---------
    // stats
    /**
     * The pilot's grit value.
     * Must be between 0 and 6 (inclusive).
     */
    private int grit;
    /**
     * The pilot's current HP value.
     * Must be between 0 and maxHP (inclusive).
     */
    private int currentHP;
    /**
     * The pilot's max HP value.
     * Must be >= 1.
     */
    private int maxHP;
    /**
     * The pilot's armor value.
     * Must be >= 0.
     */
    private int armor;
    /**
     * The pilot's evasion value.
     * Must be >= 0.
     */
    private int evasion;
    /**
     * The pilot's speed value.
     * Must be >= 0.
     */
    private int speed;
    /**
     * The pilot's e-defense value.
     * Must be >= 0.
     */
    private int eDefense;
    
    /**
     * The pilot's skill triggers.
     * Can be any SkillTriggersList. Cannot be null.
     */
    private SkillTriggersList skillTriggers;

    /**
     * The pilot's reserves and bonuses.
     * Can be any String[]. Cannot be null or contain null elements or elements
     *     that are "". Case-insensitive and stored in lowercase.
    */
    private String[] reserves;

    /**
     * The pilot's gear loadout.
     * Can be any Loadout. Cannot be null.
    */
    private Loadout loadout;

    // ---Tactical Profile---------
    // licenses
    /**
     * The pilot's license level.
     * Must be between 0 and 12 (inclusive).
     */
    private int licenseLevel;
    /**
     * The pilot's licenses.
     * Can be any License[] that is not null or contains null elements.
     */
    private License[] licenseList;

    /**
     * The pilot's special equipment - frames, mech systems, and weapons, both
     *     normal and exotics, that the pilot gains access to for free.
     * Can be any String[]. Cannot be null or contain null elements.
     */
    private String[] specialEquipment;

    /**
     * The pilot's mech skills (Hull, Agility, Systems, Engineering).
     * Must be an int[] of length 4. Each element must be between 0 and 6
     *     (inclusive).
     */
    private int[] mechSkills;

    /**
     * The pilot's core bonuses.
     * Can be any String[] that is not null, contains null elements, or elements
     *     that are "". Case-insensitive and stored in lowercase.
     */
    private String[] coreBonuses;

    /**
     * The pilot's talents (i.e. Ace 1).
     * Can be any Talent[] that is not null or contains null elements.
     */
    private Talent[] talents;

    /**
     * Creates a new Pilot from the provided pilotName and pilotCallsign.
     */
    public Pilot(String pilotName, String pilotCallsign) {
        // ---Dossier-------------------
        setName(pilotName);
        setCallsign(pilotCallsign);
        setPlayer("");
        setStatus("active");
        setBackground("");
        setBiography("");
        setAppearance("");
        setPlayerNotes("");

        // ---Narrative Profile---------
        // setGrit() is unnecessary because licenseLevel is set later
        // setMaxHP() swapped with setCurrentHP() because the mutators may throw
        //     exceptions otherwise
        setMaxHP(8);
        setCurrentHP(8);
        setArmor(0);
        setEvasion(10);
        setSpeed(4);
        setEDefense(10);
        setSkillTriggers(new SkillTriggersList());
        setReserves(new String[0]);
        setLoadout(new Loadout());

        // ---Tactical Profile---------
        setLicenseLevel(0);
        setLicenseList(new License[0]);
        setSpecialEquipment(new String[0]);
        setMechSkills(new int[4]);
        setCoreBonuses(new String[0]);
        setTalents(new Talent[0]);
    }
    /**
     * Creates a new Pilot using every possible property.
     */
    public Pilot(String pilotName, String pilotCallsign, String player,
        String status, String background, String biography, String appearance,
        String playerNotes, int currentHP, int maxHP, int armor, int evasion,
        int speed, int eDefense, SkillTriggersList skillTriggers,
        String[] reserves, Loadout loadout, int licenseLevel,
        License[] licenseList, String[] specialEquipment, int[] mechSkills,
        String[] coreBonuses, Talent[] talents) {
        // ---Dossier-------------------
        setName(pilotName);
        setCallsign(pilotCallsign);
        setPlayer(player);
        setStatus(status);
        setBackground(background);
        setBiography(biography);
        setAppearance(appearance);
        setPlayerNotes(playerNotes);

        // ---Narrative Profile---------
        // setGrit() is unnecessary because licenseLevel is set later
        // setMaxHP() swapped with setCurrentHP() because the mutators may throw
        //     exceptions otherwise
        setMaxHP(maxHP);
        setCurrentHP(currentHP);
        setArmor(armor);
        setEvasion(evasion);
        setSpeed(speed);
        setEDefense(eDefense);
        setSkillTriggers(skillTriggers);
        setReserves(reserves);
        setLoadout(loadout);

        // ---Tactical Profile---------
        setLicenseLevel(licenseLevel);
        setLicenseList(licenseList);
        setSpecialEquipment(specialEquipment);
        setMechSkills(mechSkills);
        setCoreBonuses(coreBonuses);
        setTalents(talents);
    }

    // ---Dossier-------------------
    public String getName() {
        return name;
    }
    public String getCallsign() {
        return callsign;
    }
    public String getPlayer() {
        return player;
    }
    public String getStatus() {
        return status;
    }
    public String getBackground() {
        return background;
    }
    public String getBiography() {
        return biography;
    }
    public String getPlayerNotes() {
        return playerNotes;
    }
    public String getAppearance() {
        return appearance;
    }
    // ---Narrative Profile---------
    public int getGrit() {
        return grit;
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
    public int getEvasion() {
        return evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public SkillTriggersList getSkillTriggers() {
        return skillTriggers.copyOf();
    }
    public String[] getReserves() {
        return HelperMethods.copyOf(reserves);
    }
    public Loadout getLoadout() {
        return loadout.copyOf();
    }
    // ---Tactical Profile---------
    public int getLicenseLevel() {
        return licenseLevel;
    }
    public License[] getLicenseList() {
        return HelperMethods.copyOf(licenseList);
    }
    public String[] getSpecialEquipment() {
        return HelperMethods.copyOf(specialEquipment);
    }
    public int[] getMechSkills() {
        return HelperMethods.copyOf(mechSkills);
    }
    public String[] getCoreBonuses() {
        return HelperMethods.copyOf(coreBonuses);
    }
    public Talent[] getTalents() {
        return HelperMethods.copyOf(talents);
    }
    // ---Dossier-------------------
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot name: null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot name: \"\"");
        }
        this.name = name;
    }
    public void setCallsign(String callsign) {
        if (callsign == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot callsign: null");
        }
        if (callsign.equals("")) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot callsign: \"\"");
        }
        this.callsign = callsign;
    }
    public void setPlayer(String player) {
        if (player == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " player name: null");
        }
        this.player = player;
    }
    /**
     * Sets this.status to the provided value.
     * @param status a String which cannot be null and cannot be an invalid
     *     status, as defined by Pilot.allowedStatuses.
     * @throws IllegalArgumentException if status is null or an invalid value,
     *     as defined by Pilot.allowedStatuses.
     */
    public void setStatus(String status) {
        boolean isValidStatus = false;

        if (status == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot status: null");
        }
        status = status.toLowerCase();
        for (String allowedStatus : Pilot.allowedStatuses) {
            if (status.equals(allowedStatus)) {
                isValidStatus = true;
                break;
            }
        }
        if (isValidStatus) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot status: \"" + status + "\"");
        }
    }
    public void setBackground(String background) {
        if (background == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot background: null");
        }
        background = background.toLowerCase();
        this.background = background;
    }
    public void setBiography(String biography) {
        if (biography == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot biography: null");
        }
        this.biography = biography;
    }
    public void setAppearance(String appearance) {
        if (appearance == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot appearance: null");
        }
        this.appearance = appearance;
    }
    public void setPlayerNotes(String playerNotes) {
        if (playerNotes == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " player notes: null");
        }
        this.playerNotes = playerNotes;
    }
    // ---Narrative Profile---------
    /**
     * Sets this.grit to the provided value.
     * @param grit an int which cannot be < 0 or > 6.
     * @throws IllegalArgumentException if grit is < 0 or > 6.
     */
    private void setGrit(int grit) {
        if (grit < 0) {
            throw new IllegalArgumentException("New grit value: " + grit + " is"
                + " < 0");
        }
        if (grit > 6) {
            throw new IllegalArgumentException("New grit value: " + grit + " is"
                + " > 6");
        }
        this.grit = grit;
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
    /**
     * Sets this.skillTriggers to the provided value.
     * @param skillTriggers a SkillTriggersList which cannot be null.
     * @throws IllegalArgumentException if skillTriggers is null.
     */
    public void setSkillTriggers(SkillTriggersList skillTriggers) {
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers list value"
                + " is null");
        }
        skillTriggers = skillTriggers.copyOf();
        this.skillTriggers = skillTriggers;
    }
    /**
     * Sets this.reserves to the provided value.
     * @param reserves a String[] which cannot be null, contain null elements,
     *     or elements that are "".
     * @throws IllegalArgumentException if reserves is null, contains null
     *     values, or elements that are "".
     */
    public void setReserves(String[] reserves) {
        if (reserves == null) {
            throw new IllegalArgumentException("New reserves value is null");
        }
        for (String reserve : reserves) {
            if (reserve == null) {
                throw new IllegalArgumentException("New reserves value"
                    + " includes an element with a value of null");
            }
            if (reserve.equals("")) {
                throw new IllegalArgumentException("New reserves value"
                    + " includes an element with a value of \"\"");
            }
        }
        reserves = HelperMethods.copyOf(reserves);
        for (int i = 0; i < reserves.length; i++) {
            reserves[i] = reserves[i].toLowerCase();
        }
        this.reserves = reserves;
    }
    /**
     * Sets this.loadout to the provided value.
     * @param loadout a Loadout which cannot be null.
     * @throws IllegalArgumentException if loadout is null.
     */
    public void setLoadout(Loadout loadout) {
        if (loadout == null) {
            throw new IllegalArgumentException("New loadout value is null");
        }
        loadout = loadout.copyOf();
        this.loadout = loadout;
    }
    // ---Tactical Profile---------
    /**
     * Sets this.licenseLevel to the provided value, and automatically sets
     *     this.grit accordingly.
     * this.grit will be set to (licenseLevel + 1) / 2, rounded down.
     * @param licenseLevel an int which cannot be < 0 or > 12.
     * @throws IllegalArgumentException if licenseLevel is < 0 or > 12.
     */
    public void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < 0) {
            throw new IllegalArgumentException("New license level value: "
                + licenseLevel + " is < 0");
        }
        if (licenseLevel > 12) {
            throw new IllegalArgumentException("New license level value: "
                + licenseLevel + " is > 12");
        }
        setGrit((licenseLevel + 1) / 2);
        this.licenseLevel = licenseLevel;
    }
    /**
     * Sets this.licenseList to the provided value.
     * @param licenseList a License[] which cannot be null or contain null
     *     elements.
     * @throws IllegalArgumentException if licenseList is null or contains null
     *     elements.
     */
    public void setLicenseList(License[] licenseList) {
        if (licenseList == null) {
            throw new IllegalArgumentException("New license list value is"
                + " null");
        }
        for (License license : licenseList) {
            if (license == null) {
                throw new IllegalArgumentException("New license list"
                    + " value includes a null element");
            }
        }
        licenseList = HelperMethods.copyOf(licenseList);
        this.licenseList = licenseList;
    }
    /**
     * Sets this.specialEquipment to the provided value.
     * @param specialEquipment a String[] which cannot be null, contain null
     *     elements, or elements that are "".
     * @throws IllegalArgumentException if specialEquipment is null, contains
     *     null elements, or elements that are "".
     */
    public void setSpecialEquipment(String[] specialEquipment) {
        if (specialEquipment == null) {
            throw new IllegalArgumentException("New special equipment value"
                + " is null");
        }
        for (String equipment : specialEquipment) {
            if (equipment == null) {
                throw new IllegalArgumentException("New special equipment"
                    + " value includes an element with a value of null");
            }
            if (equipment.equals("")) {
                throw new IllegalArgumentException("New special equipment"
                    + " value includes an element with a value of \"\"");
            }
        }
        specialEquipment = HelperMethods.copyOf(specialEquipment);
        this.specialEquipment = specialEquipment;
    }
    /**
     * Sets this.mechSkills to the provided value.
     * @param mechSkills an int[] of length 4 which cannot be null or contain
     *     elements that are < 0 or > 6.
     * @throws IllegalArgumentException if mechSkills is null, of length other
     *     than 4, or contains elements that are < 0 or > 6.
     */
    public void setMechSkills(int[] mechSkills) {
        if (mechSkills == null) {
            throw new IllegalArgumentException("New mech skills value is"
                + " null");
        }
        if (mechSkills.length != 4) {
            throw new IllegalArgumentException("New mech skills value is"
                + " of invalid length");
        }
        for (int mechSkill : mechSkills) {
            if (mechSkill < 0) {
                throw new IllegalArgumentException("New mech skills value"
                    + " includes an element with a value: " + mechSkill + " of"
                    + " < 0");
            }
            if (mechSkill > 6) {
                throw new IllegalArgumentException("New mech skills value"
                    + " includes an element with a value: " + mechSkill + " of"
                    + " > 6");
            }
        }
        mechSkills = HelperMethods.copyOf(mechSkills);
        this.mechSkills = mechSkills;
    }
    /**
     * Sets this.coreBonuses to the provided value.
     * @param coreBonuses a String[] which cannot be null, contain null
     *     elements, or elements that are "".
     * @throws IllegalArgumentException if coreBonuses is null, contains null
     *     elements, or elements that are "".
     */
    public void setCoreBonuses(String[] coreBonuses) {
        if (coreBonuses == null) {
            throw new IllegalArgumentException("New core bonuses value is"
                + " invalid");
        }
        for (String coreBonus : coreBonuses) {
            if (coreBonus == null) {
                throw new IllegalArgumentException("New core bonuses"
                    + " value includes an element with a value of null");
            }
            if (coreBonus.equals("")) {
                throw new IllegalArgumentException("New core bonuses"
                    + " value includes an element with a value of \"\"");
            }
        }
        coreBonuses = HelperMethods.copyOf(coreBonuses);
        for (int i = 0; i < coreBonuses.length; i++) {
            coreBonuses[i] = coreBonuses[i].toLowerCase();
        }
        this.coreBonuses = coreBonuses;
    }
    /**
     * Sets this.talents to the provided value.
     * @param talents a Talent[] which cannot be null or contain null elements.
     * @throws IllegalArgumentException if talents is null or contains null
     *     elements.
     */
    public void setTalents(Talent[] talents) {
        if (talents == null) {
            throw new IllegalArgumentException("New talents value is"
                + " invalid");
        }
        for (Talent talent : talents) {
            if (talent == null) {
                throw new IllegalArgumentException("New talents value"
                    + " includes a null element");
            }
        }
        talents = HelperMethods.copyOf(talents);
        this.talents = talents;
    }

    /**
     * Returns a deepest copy of this Pilot object.
     * @return a Pilot deepest copy of this object.
     */
    public Pilot copyOf() {
        // don't need to make copies of these because the mutators already do so
        Pilot copy = new Pilot(this.name, this.callsign, this.player,
            this.status, this.background, this.biography, this.appearance,
            this.playerNotes, this.currentHP, this.maxHP, this.armor,
            this.evasion, this.speed, this.eDefense, this.skillTriggers,
            this.reserves, this.loadout, this.licenseLevel, this.licenseList,
            this.specialEquipment, this.mechSkills, this.coreBonuses,
            this.talents);

        return copy;
    }
    /**
     * Returns this.status, properly formatted. "active" will become "Active",
     *     and "missing in action" will become "Missing in Action".
     * @return a String containing this.status, properly formatted.
     */
    public String outputStatus() {
        if (status.equals("missing in action")) {
            return "Missing in Action";
        }
        if (status.equals("killed in action")) {
            return "Killed in Action";
        }
        // Status is one of the following: "active", "inactive", "retired",
        //     or "unknown"
        return HelperMethods.capitalizeFirst(status);
    }
    /**
     * Generates the pilot portion of the COMP/CON character output function.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the printout of information
     */
    public String generateOutput(String outputType) {
        String outputString = "";

        if (outputType.equals("mech build")) {
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ STATS ]\n";
            outputString += String.format(
                "  HULL:%d AGI:%d SYS:%d ENGI:%d\n",
                this.mechSkills[0], this.mechSkills[1], this.mechSkills[2],
                this.mechSkills[3]
            );
        } else if (outputType.equals("pilot")) {
            outputString += String.format(
                "» %s // %s «\n",
                this.name, this.callsign.toUpperCase()
            );
            outputString += "  LL" + this.licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += this.skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += this.loadout.generateOutput();
            outputString += "[ MECH SKILLS ]\n";
            outputString += String.format(
                "  GRIT:%d // H:%d A:%d S:%d E:%d\n",
                this.grit, this.mechSkills[0], this.mechSkills[1],
                this.mechSkills[2], this.mechSkills[3]
            );
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
        } else if (outputType.equals("full")) {
            outputString += String.format(
                "» %s // %s «\n",
                this.name, this.callsign.toUpperCase()
            );
            outputString += "  LL" + this.licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += this.skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += this.loadout.generateOutput();
            outputString += "***\n";
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
        }

        return outputString;
    }
    /**
     * Helper method for Pilot.generateOutput() that outputs only the licenses
     *     portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the licenses portion of the printout.
     */
    public String outputLicenses(String outputType) {
        String outputString = "";

        if (this.licenseList.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something like:
            //     "  IPS-N Blackbeard 1, IPS-N Drake 1, IPS-N Lancaster 1\n"
            outputString += "  ";
            for (int i = 0; i < this.licenseList.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += this.licenseList[i].outputLicense();
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something like:
            //     "  IPS-N Blackbeard 1, IPS-N Drake 1,\n"
            //     "  IPS-N Lancaster 1\n"
            for (int i = 0; i < this.licenseList.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, this.licenseList.length);
                    j++) {
                    outputString += this.licenseList[j].outputLicense();
                    if (j == i && i < this.licenseList.length + 1) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < this.licenseList.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * Helper method for Pilot.generateOutput() that outputs only the talents
     *     portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the talents portion of the printout.
     */
    public String outputTalents(String outputType) {
        String outputString = "";

        if (talents.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something like:
            //     "  Ace 1, Brawler 1, Bonded 1\n"
            outputString += "  ";
            for (int i = 0; i < this.talents.length; i++) {
                outputString += this.talents[i].outputName() + " "
                    + this.talents[i].getLevel();
                if (i < this.talents.length - 1) {
                    outputString += ", ";
                }
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something like:
            //     "  Ace 1, Brawler 1,\n"
            //     "  Bonded 1\n"
            for (int i = 0; i < this.talents.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, this.talents.length); j++) {
                    outputString += this.talents[j].outputName() + " "
                        + this.talents[j].getLevel();
                    if (j == i && i < this.talents.length + 1) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < this.talents.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * Helper method for Pilot.generateOutput() that outputs only the core
     *     bonuses portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the core bonuses portion of the printout.
     */
    public String outputCoreBonuses(String outputType) {
        String outputString = "";

        if (this.coreBonuses.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something of the following form:
            //     "  Auto-Stabilizing Hardpoints, Overpower Caliber,"
            //     " Improved Armament\n"
            outputString += "  ";
            for (int i = 0; i < this.coreBonuses.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += HelperMethods.toProperCase(
                    this.coreBonuses[i]);
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something of the following form:
            //     "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n"
            //     "  Improved Armament\n"
            for (int i = 0; i < this.coreBonuses.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, this.coreBonuses.length);
                    j++) {
                    outputString += HelperMethods.toProperCase(
                        this.coreBonuses[j]);
                    if (i + 1 >= this.coreBonuses.length) {
                        continue;
                    }
                    if (j == i) {
                        outputString += ", ";
                    } else {
                        outputString += ",";
                    }
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
}