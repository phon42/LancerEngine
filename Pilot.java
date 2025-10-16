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
public final class Pilot implements Damageable {
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
     * Must be a valid status as defined by Pilot.allowedFlavorStatuses.
     * Case-insensitive and stored in lowercase. Cannot be null.
     * Use Pilot.getStatus() to get the raw value and Pilot.outputStatus() to
     *     obtain it properly formatted.
     */
    private String status;
    /**
     * Contains an array of valid values for this.status.
     *     Case-insensitive and stored in lowercase.
     */
    private static final String[] allowedFlavorStatuses = {
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
     * The pilot's size.
     * Size is stored as 2 * its value (i.e. Size 1/2 would be stored as int 1).
     * Must be one of the following values:
     *     1, 2, 4, 6, 8.
     * Use Pilot.getSize() to get the raw value and Pilot.outputSize() to obtain
     *     it properly formatted.
     */
    private int size;
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

    // ---Extra stuff---------
    /**
     * Any statuses affecting the pilot (i.e. "down and out" as a Status).
     * Each element must have a valid Status.type property as defined by
     *     Status.allowedPilotStatuses.
     * Cannot be null.
     */
    private State[] statuses;
    /**
     * Any conditions affecting the pilot (i.e. "immobilized" as a Condition).
     * Each element must have a valid Condition.type property as defined by
     *     Condition.allowedConditions.
     * Cannot be null.
     */
    private State[] conditions;

    /**
     * The number of stacks of burn currently affecting the pilot (i.e. 6).
     * Must be a minimum of 0.
     */
    private int burn;

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
        // From pg. 74:
        setSize(1);
        // add this.grit to get this.maxHP
        setMaxHP(6 + 0);
        // add this.grit to get this.currentHP
        setCurrentHP(6 + 0);
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

        // ---Extra stuff---------
        setStatuses(new State[0]);
        setConditions(new State[0]);
        setBurn(0);
    }
    /**
     * Creates a new Pilot using every Pilot property that isn't calculated by
     *     the Pilot's Loadout.
     */
    public Pilot(String pilotName, String pilotCallsign, String player,
        String status, String background, String biography, String appearance,
        String playerNotes, int currentHP, SkillTriggersList skillTriggers,
        String[] reserves, Loadout loadout, int licenseLevel,
        License[] licenseList, String[] specialEquipment, int[] mechSkills,
        String[] coreBonuses, Talent[] talents, State[] statuses,
        State[] conditions, int burn) {
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
        setLoadout(loadout);
        setCurrentHP(currentHP);
        setSkillTriggers(skillTriggers);
        setReserves(reserves);
        // setLoadout() moved upwards so it doesn't re-set any properties that
        //     are set above

        // ---Tactical Profile---------
        setLicenseLevel(licenseLevel);
        setLicenseList(licenseList);
        setSpecialEquipment(specialEquipment);
        setMechSkills(mechSkills);
        setCoreBonuses(coreBonuses);
        setTalents(talents);
        setStatuses(statuses);
        setConditions(conditions);
        setBurn(burn);
    }
    /**
     * Creates a deepest copy of the provided Pilot.
     * @param pilot a Pilot to be copied.
     * @return a Pilot deepest copy of the provided Pilot.
     */
    public Pilot(Pilot pilot) {
        // don't need to make copies of these because the mutators already do so
        // setMaxHP() swapped with setCurrentHP() because the mutators may throw
        //     exceptions otherwise
        setName(pilot.name);
        setCallsign(pilot.callsign);
        setPlayer(pilot.player);
        setStatus(pilot.status);
        setBackground(pilot.background);
        setBiography(pilot.biography);
        setAppearance(pilot.appearance);
        setPlayerNotes(pilot.playerNotes);

        setLoadout(pilot.loadout);

        setSize(pilot.size);
        setMaxHP(pilot.maxHP);
        setCurrentHP(pilot.currentHP);
        setArmor(pilot.armor);
        setEvasion(pilot.evasion);
        setSpeed(pilot.speed);
        setEDefense(pilot.eDefense);
        setSkillTriggers(pilot.skillTriggers);
        setReserves(pilot.reserves);
        // setLoadout() moved upwards so it doesn't re-set any properties that
        //     are set above
        setLicenseLevel(pilot.licenseLevel);
        setLicenseList(pilot.licenseList);
        setSpecialEquipment(pilot.specialEquipment);
        setMechSkills(pilot.mechSkills);
        setCoreBonuses(pilot.coreBonuses);
        setTalents(pilot.talents);
        setStatuses(pilot.statuses);
        setConditions(pilot.conditions);
        setBurn(pilot.burn);
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
    public int getSize() {
        return size;
    }
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
        return new SkillTriggersList(skillTriggers);
    }
    public String[] getReserves() {
        return HelperMethods.copyOf(reserves);
    }
    public Loadout getLoadout() {
        return new Loadout(loadout);
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
    public State[] getStatuses() {
        return HelperMethods.copyOf(statuses);
    }
    public State[] getConditions() {
        return HelperMethods.copyOf(conditions);
    }
    public int getBurn() {
        return burn;
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
     *     status, as defined by Status.allowedPilotStatuses.
     * @throws IllegalArgumentException if status is null or an invalid value,
     *     as defined by Status.allowedPilotStatuses.
     */
    public void setStatus(String status) {
        boolean isValid = false;

        if (status == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot status: null");
        }
        status = status.toLowerCase();
        for (String allowedStatus : Pilot.allowedFlavorStatuses) {
            if (status.equals(allowedStatus)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot status: \"" + status + "\"");
        }
        this.status = status;
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
     * Sets this.size to the value provided.
     * @param size an int which must be 1, 2, 4, 6, or 8.
     * @throws IllegalArgumentException if size is anything other than 1, 2, 4,
     *     6, or 8.
     */
    private void setSize(int size) {
        if (size != 1 && size != 2 && size != 4 && size != 6 && size != 8) {
            throw new IllegalArgumentException("New pilot size: " + size + " is"
                + " not one of the following valid values: 1, 2, 4, 6, 8");
        }
        this.size = size;
    }
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
        skillTriggers = new SkillTriggersList(skillTriggers);
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
        loadout = new Loadout(loadout);
        this.loadout = loadout;
        calculateAttributes();
    }
    // ---Tactical Profile---------
    /**
     * Sets this.licenseLevel to the provided value, and automatically sets
     *     this.grit accordingly.
     * this.grit will be set to (licenseLevel / 2) in Lancer division,
     *     equivalent to HelperMethods.div(licenseLevel, 2). In other words: a
     *     double divison of (licenseLevel / 2), rounded up.
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
        setGrit(HelperMethods.div(licenseLevel, 2));
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
            throw new IllegalArgumentException("New mech skills is of length: "
                + mechSkills.length + " which is not 4");
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
     * Sets this.statuses to the provided value.
     * @param statuses a State[] which cannot be null or contain elements with
     *     invalid Status.type values as defined by Status.allowedPilotStatuses.
     * @throws IllegalArgumentException if statuses is null or contains elements
     *     with invalid Status.type values as defined by
     *     Status.allowedPilotStatuses.
     */
    public void setStatuses(State[] statuses) {
        boolean isValidStatus = false;
        String statusString = "";

        if (statuses == null) {
            throw new IllegalArgumentException("New statuses value is null");
        }
        for (State status : statuses) {
            if (status == null) {
                throw new IllegalArgumentException("New statuses array"
                    + " contains a null element");
            }
            statusString = status.getType();
            for (String allowedStatus : Status.allowedPilotStatuses) {
                if (statusString.equals(allowedStatus)) {
                    isValidStatus = true;
                }
            }
            if (! isValidStatus) {
                throw new IllegalArgumentException("New statuses array contains"
                    + " an element with an invalid Status.type value: \""
                    + statusString + "\"");
            }
        }
        statuses = HelperMethods.copyOf(statuses);
        this.statuses = statuses;
    }
    /**
     * Sets this.conditions to the provided value.
     * @param conditions a State[] which cannot be null or contain elements with
     *     invalid Condition.type values as defined by
     *     Condition.allowedConditions.
     * @throws IllegalArgumentException if conditions is null or contains
     *     elements with invalid Condition.type values as defined by
     *     Condition.allowedConditions.
     */
    public void setConditions(State[] conditions) {
        boolean isValidCondition = false;
        String conditionString = "";

        if (conditions == null) {
            throw new IllegalArgumentException("New conditions value is"
                + " null");
        }
        for (State condition : conditions) {
            if (condition == null) {
                throw new IllegalArgumentException("New conditions array"
                    + " contains a null element");
            }
            conditionString = condition.getType();
            for (String allowedCondition : Condition.allowedConditions) {
                if (conditionString.equals(allowedCondition)) {
                    isValidCondition = true;
                }
            }
            if (! isValidCondition) {
                throw new IllegalArgumentException("New conditions array"
                    + " contains an element with an invalid Condition.type"
                    + " property: \"" + conditionString + "\"");
            }
        }
        conditions = HelperMethods.copyOf(conditions);
        this.conditions = conditions;
    }
    public void setBurn(int burn) {
        if (burn < 0) {
            throw new IllegalArgumentException("burn value: " + burn + " is <"
                + " 0");
        }
        this.burn = burn;
    }

    @Override
    public String toString() {
        String outputString = "";

        outputString += String.format(
            "» %s // %s (Player: ",
            this.name, this.callsign.toUpperCase()
        );
        if (player.equals("")) {
            outputString += "N/A";
        } else {
            outputString += this.player;
        }
        outputString += ") «\n";
        outputString += String.format(
            "  LL%d  Status: %s\n",
            this.licenseLevel, HelperMethods.capitalizeFirst(this.status)
        );
        outputString += "[ STATS ]\n";
        outputString += String.format(
            "  GRIT:%s // H:%s A:%s S:%s E:%s\n",
            this.grit, this.mechSkills[0], this.mechSkills[1],
            this.mechSkills[2], this.mechSkills[3]
        );
        outputString += String.format(
            "  HP:%d/%d ARMOR:%d EDEF:%d EVA:%d SPD:%d\n",
            this.currentHP, this.maxHP, this.armor, this.eDefense, this.evasion,
            this.speed
        );
        outputString += "[ SKILL TRIGGERS ]\n";
        outputString += this.skillTriggers.generateOutput();
        outputString += "[ GEAR ]\n";
        outputString += this.loadout.generateOutput();
        outputString += "[ TALENTS ]\n";
        outputString += outputTalents("pilot");
        outputString += "[ LICENSES ]\n";
        outputString += outputLicenses("pilot");
        outputString += "[ CORE BONUSES ]\n";
        outputString += outputCoreBonuses("pilot");
        // TODO: add this in
        // outputString += "[ RESERVES ]\n";
        // TODO: add in conditions and statuses
        
        return outputString;
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
     * @param newStatus a State containing the new status. Must have a valid
     *     Status.type property as defined by Status.allowedPilotStatuses.
     * @param addDuplicate a boolean representing whether or not to add a second
     *     version of the same status if a status of the same type is already
     *     present in this.statuses.
     * @throws IllegalArgumentException if newStatus has a Status.type property
     *     that is invalid as defined by Status.allowedPilotStatuses.
     */
    public void addStatus(State newStatus, boolean addDuplicate) {
        boolean isValid = false;
        boolean containsStatus = false;

        for (String status : Status.allowedPilotStatuses) {
            if (newStatus.getType().equals(status)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("newStatus' Status.type"
                + " property: \"" + newStatus.getType() + "\" is an invalid"
                + " status");
        }
        for (State status : this.statuses) {
            if (status.getType().equals(newStatus.getType())) {
                containsStatus = true;
            }
        }
        // if (! containsStatus) || addDuplicate:
        if (! containsStatus || addDuplicate) {
            setStatuses(HelperMethods.append(this.statuses, newStatus));
        }
    }
    /**
     * A helper method for addStatus(State, boolean). Allows the method to be
     *     called with a default value of false for the boolean.
     * @param status a State containing the new status. Must have a valid
     *     Status.type property as defined by Status.allowedPilotStatuses.
     */
    public void addStatus(State status) {
        addStatus(status, false);
    }
    /**
     * Removes the provided status from this.statuses.
     * @param oldStatus a State containing the status to be removed. Must have a
     *     valid Status.type property as defined by Status.allowedPilotStatuses.
     * @param removeAll a boolean representing whether to remove all statuses
     *     with the same Status.type property as oldStatus if multiple statuses
     *     of the same type are present, or just the specified one.
     * @throws IllegalArgumentException if oldStatus has an invalid Status.type
     *     property as defined by Status.allowedPilotStatuses.
     */
    public void removeStatus(State oldStatus, boolean removeAll) {
        boolean isValid = false;
        boolean areSame = false;
        State[] newStatuses;

        for (String status : Status.allowedPilotStatuses) {
            if (oldStatus.getType().equals(status)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("oldStatus has an invalid"
                + " Status.type property: \"" + oldStatus.getType() + "\"");
        }
        for (int i = 0; i < this.statuses.length; i++) {
            if (removeAll) {
                areSame = this.statuses[i].getType().equals(
                    oldStatus.getType());
            } else {
                areSame = this.statuses[i].equals(oldStatus);
            }
            if (areSame) {
                newStatuses = new State[this.statuses.length - 1];
                for (int j = 0; j < this.statuses.length; j++) {
                    if (j < i) {
                        newStatuses[j] = this.statuses[j];
                    } 
                    if (j > i) {
                        newStatuses[j - 1] = this.statuses[j];
                    }
                }
                setStatuses(newStatuses);
                break;
            }
        }
        if (areSame && removeAll) {
            removeStatus(oldStatus, removeAll);
        }
    }
    /**
     * Helper method for removeStatus(State, boolean). Allows the method to be
     *     run with a default value of false for the boolean.
     * @param oldStatus a State containing the status to be removed. Must have a
     *     valid Status.type property as defined by Status.allowedPilotStatuses.
     */
    public void removeStatus(State oldStatus) {
        removeStatus(oldStatus, false);
    }
    /**
     * Recursively checks whether any of this.conditions or this.status have a
     *     State.type value of stateType, or whether any of the States they
     *     caused have such a value, and so on.
     * @param stateType a String containing a State.type value to search for.
     *     Must be a valid type as defined by State.allowedTypes.
     * @return a boolean containing the result of the check.
     */
    public boolean hasState(String stateType) {
        boolean isPresent = false;

        for (State condition : this.conditions) {
            isPresent = isPresent || condition.getType().equals(stateType);
            isPresent = isPresent || condition.hasState(stateType);
        }
        if (isPresent) {
            return isPresent;
        }
        for (State status : this.statuses) {
            isPresent = isPresent || status.getType().equals(stateType);
            isPresent = isPresent || status.hasState(stateType);
        }

        return isPresent;
    }
    /**
     * Adds the provided condition to this.conditions.
     * @param newCondition a Condition containing the new condition.
     * @param addDuplicate a boolean representing whether or not to add a second
     *     version of the same condition if a condition of the same type is
     *     already present in this.conditions.
     */
    public void addCondition(State newCondition, boolean addDuplicate) {
        boolean containsCondition = false;

        for (State condition : this.conditions) {
            if (condition.getType().equals(newCondition.getType())) {
                containsCondition = true;
            }
        }
        // if (! containsCondition) || addDuplicate:
        if (! containsCondition || addDuplicate) {
            setConditions(HelperMethods.append(this.conditions, newCondition));
        }
    }
    /**
     * A helper method for addCondition(State, boolean). Allows the method to
     *     be called with a default value of false for the boolean.
     * @param condition a State containing the new condition.
     */
    public void addCondition(State condition) {
        addCondition(condition, false);
    }
    /**
     * Removes the provided condition from this.conditions.
     * @param oldCondition a State containing the condition to be removed.
     * @param removeAll a boolean representing whether to remove all conditions
     *     with the same Condition.type property as oldCondition if multiple
     *     conditions of the same type are present, or just the specified one.
     */
    public void removeCondition(State oldCondition, boolean removeAll) {
        boolean areSame = false;
        State[] newConditions;

        for (int i = 0; i < this.conditions.length; i++) {
            if (removeAll) {
                areSame = this.conditions[i].getType().equals(
                    oldCondition.getType());
            } else {
                areSame = this.conditions[i].equals(oldCondition);
            }
            if (areSame) {
                newConditions = new State[this.conditions.length - 1];
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
        if (areSame && removeAll) {
            removeCondition(oldCondition, removeAll);
        }
    }
    /**
     * Adds the provided amount of burn to this pilot's existing burn stack.
     * @param burnAmount an int which must be a minimum of 1.
     * @throws IllegalArgumentException if burnAmount is < 1.
     */
    public void addBurn(int burnAmount) {
        if (burnAmount < 1) {
            throw new IllegalArgumentException("burnAmount value: " + burnAmount
                + " is < 1");
        }
        setBurn(this.burn + burnAmount);
    }
    /**
     * Clears all burn from this pilot.
     */
    public void clearBurn() {
        setBurn(0);
    }
    /**
     * Helper method for removeCondition(State, boolean). Allows the method to
     *     be run with a default value of false for the boolean.
     * @param oldCondition a State containing the condition to be removed.
     */
    public void removeCondition(State oldCondition) {
        removeCondition(oldCondition, false);
    }
    /**
     * Calculates a Pilot's stats based on its Loadout.
     */
    public void calculateAttributes() {
        // TODO: finish
        if (! this.loadout.getPilotArmor().equals("")) {
            int[] armorAttributes = Database.getPilotArmorStats(
                this.loadout.getPilotArmor());
            
            // From pg. 74:
            // setMaxHP() swapped with setCurrentHP() because the mutators may
            //     throw exceptions otherwise
            // add this.grit to get this.maxHP
            setMaxHP(6 + armorAttributes[0] + this.grit);
            // add this.grit to get this.currentHP
            setCurrentHP(6 + armorAttributes[0] + this.grit);
            setArmor(0 + armorAttributes[1]);
            setEvasion(10 + armorAttributes[2]);
            setSpeed(4 + armorAttributes[3]);
            setEDefense(10 + armorAttributes[4]);
            // TODO: fill out if it's modified by the armor
            setSize(1);
        }
    }
    /**
     * Deals (damageAmount) damage of type (damageType) to this Pilot.
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
        damageToTake = Math.min(damageAmount, this.currentHP);
        newCurrentHP = this.currentHP - damageToTake;
        setCurrentHP(newCurrentHP);
        // if the amount of damage they're taking is greater than our pilot's
        //     maximum HP, they will be critical no matter what their current HP
        //     is
        if (damageAmount > this.currentHP) {
            // we're about to become critical
            becomeCritical();
        }
    }
    /**
     * Deals (heatAmount) heat to this Pilot, converting it to Energy damage as
     *     per the rule on biological targets receiving heat.
     * @param heatAmount an int containing the amount of heat to deal. Must be >
     *     0.
     * @throws IllegalArgumentException if heatAmount is < 1.
     */
    public void receiveHeat(int heatAmount) {
        // TODO: fill out with mitigation, resistance etc
        if (heatAmount < 1) {
            throw new IllegalArgumentException("heatAmount value: " + heatAmount
                + " is < 1");
        }
        receiveDamage(heatAmount, "energy");
    }
    /**
     * Deals (burnAmount) burn to this Pilot.
     * @param burnAmount an int containing the amount of burn to deal. Must be >
     *     0.
     * @throws IllegalArgumentException if burnAmount is < 1.
     */
    public void receiveBurn(int burnAmount) {
        // See pg. 67
        if (burnAmount < 1) {
            throw new IllegalArgumentException("burnAmount value: " + burnAmount
                + " is < 1");
        }
        receiveDamage(burnAmount, "burn");
        addBurn(burnAmount);
    }
    /**
     * Represents the Pilot taking a lethal or near-lethal hit. Is called
     *     whenever this.currentHP is reduced to 0. See pg. 49.
     */
    public void becomeCritical() {
        int downRoll = Roll.roll("d6");

        if (downRoll == 6) {
            // On 6, your pilot barely shrugs off the hit (or it’s a close call)
            //     – they return to 1 HP.
            setCurrentHP(1);
        } else if (downRoll == 1) {
            // On 1, your pilot’s luck has run out – they die immediately.
            die();
        } else {
            // On 2–5, your pilot gains the DOWN AND OUT status (and the STUNNED
            //     condition) and remains at 0 HP. They are unconscious, pinned,
            //     bleeding out, or otherwise unable to act. If you’re in mech
            //     combat, they are Stunned and their EVASION drops to 5. If
            //     they take any more damage – from being shot in the head, for
            //     instance – they die.
            down();
        }
    }
    /**
     * Is called whenever this Pilot is made Down And Out. Means that
     *     this.currentHP has been reduced to 0.
     */
    public void down() {
        State downed = new Status("down and out", "Pilot (self)",
            "until removed");
        State stun = new Condition("stunned", "down and out status",
            "source");
        downed.addEffect(stun);
        addStatus(downed);
        System.out.println("This Pilot has been downed");
    }
    /**
     * Is called whenever this Pilot dies.
     */
    public void die() {
        // TODO: fill out
        System.out.println("This Pilot has died");
    }
    /**
     * Ends this Pilot's current turn.
     */
    public void endTurn() {
        // TODO: fill out
        // burn check - see pg. 67.
        if (! Roll.evaluateCheck(Roll.check(3, this.mechSkills))) {
            // If the burn check fails
            receiveBurn(this.burn);
        }
    }
    /**
     * Checks the validity (and number) of talents, skill triggers, licenses,
     *     and core bonuses for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkValidity() {
        // TODO: check the validity of the number of talents, skill triggers,
        //     licenses, and core bonuses
        boolean skillTriggers = checkSkillTriggers();
        boolean licenses = checkLicenses();
        boolean coreBonuses = checkCoreBonuses();
        boolean talents = checkTalents();

        return skillTriggers && licenses && coreBonuses && talents;
    }
    /**
     * Checks the validity (and number) of skill triggers for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkSkillTriggers() {
        // see pg. 18
        // total skill trigger level should be 4 + licenseLevel
        int totalLevel = 0;

        for (SkillTrigger skillTrigger : this.skillTriggers.getSkillTriggers())
            {
            totalLevel += skillTrigger.getLevel() / 2;
        }
        // TODO: would it be helpful to have some kind of information about if
        //     the total skill trigger level is LESS than what it could possibly
        //     be?
        if (totalLevel > 4 + this.licenseLevel) {
            return false;
        }
        // TODO: compute whether this arrangement is possible RAW

        return true;
    }
    /**
     * Checks the validity (and number) of licenses for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkLicenses() {
        // see pg. 31
        // total license level should be licenseLevel (by definition)
        int totalLevel = 0;

        for (License license : this.licenseList) {
            totalLevel += license.getLevel();
        }
        // TODO: would it be helpful to have some kind of information about if
        //     the total license level is LESS than what it could possibly be?
        if (totalLevel > this.licenseLevel) {
            return false;
        }

        return true;
    }
    /**
     * Checks the validity (and number) of core bonuses for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkCoreBonuses() {
        // see pg. 35
        // core bonus number should be licenseLevel / 3
        int numCoreBonuses = this.coreBonuses.length;

        // TODO: would it be helpful to have some kind of information about if
        //     the number of core bonuses is LESS than what it could possibly
        //     be?
        if (numCoreBonuses > this.licenseLevel / 3) {
            return false;
        }
        // TODO: compute whether this arrangement is possible RAW

        return true;
    }
    /**
     * Checks the validity (and number) of talents for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkTalents() {
        // see pg. 35
        // total talent rank should be 3 + 1 per LL
        int totalLevel = 0;

        for (Talent talent : this.talents) {
            totalLevel += talent.getLevel();
        }
        // TODO: would it be helpful to have some kind of information about if
        //     the total combined talent level is LESS than what it could
        //     possibly be?
        if (totalLevel > 3 + this.licenseLevel) {
            return false;
        }
        // TODO: compute whether this arrangement is possible RAW

        return true;
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
                // TODO: fix the commas on Pilot.outputTalents("pilot")
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