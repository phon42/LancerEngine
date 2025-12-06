package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable;

import java.util.NoSuchElementException;

import MainBranch.Database;
import MainBranch.HelperMethods;
import MainBranch.Roll;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.EntityTypes.Damageable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Talent;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Loadout.Loadout;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Loadout.loadout.Verified.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.SkillTriggersList.SkillTriggersList;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.SkillTriggersList.skillTriggersList.Skill;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Duration;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;
import Packages.CoreTypes.Size;

/**
 * See pgs. 58 and 74.
 */
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
     * Can be any Size. Cannot be null.
     * Use Pilot.getSize() to get the raw value and Pilot.outputSize() to obtain
     *     it properly formatted.
     */
    private Size size;
    /**
     * The pilot's grit value.
     * Must be between 0 and 6 (inclusive).
     */
    private int grit;
    /**
     * The pilot's current HP value.
     * Must be between 0 and maxHP (inclusive).
     * 
     * See pg. 48.
     */
    private int currentHP;
    /**
     * The pilot's max HP value.
     * Must be >= 1.
     * 
     * See pg. 48.
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
    private SkillTriggersList skills;

    /**
     * The pilot's reserves and bonuses.
     * Can be any String[]. Cannot be null or contain null elements or elements
     *     that are "". Case-insensitive and stored in lowercase.
     * 
     * See pgs. 50 - 52.
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
     * 
     * See pg. 30 for descriptions on what each one might do.
     */
    private int[] mechSkills;

    /**
     * The pilot's core bonuses.
     * Can be any String[] that is not null, contains null elements, or elements
     *     that are "". Case-insensitive and stored in lowercase.
     * 
     * See pg. 35.
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
    private Status[] statuses;
    /**
     * Any conditions affecting the pilot (i.e. "immobilized" as a Condition).
     * Each element must have a valid Condition.type property as defined by
     *     Condition.allowedConditions.
     * Cannot be null.
     */
    private Condition[] conditions;

    /**
     * The number of stacks of burn currently affecting the pilot (i.e. 6).
     * Must be a minimum of 0.
     */
    private int burn;

    /**
     * Creates a new Pilot using every Pilot property that isn't calculated by
     *     the Pilot's Loadout.
     */
    public Pilot(String pilotName, String pilotCallsign, String player,
        String status, String background, String biography, String appearance,
        String playerNotes, int currentHP, SkillTriggersList skills,
        String[] reserves, Loadout loadout, int licenseLevel,
        License[] licenseList, String[] specialEquipment, int[] mechSkills,
        String[] coreBonuses, Talent[] talents, Status[] statuses,
        Condition[] conditions, int burn) {
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
        // setGrit() is unnecessary because licenseLevel is set instead
        setSize(new Size(1));
        // All the pilot's stats are calculated here
        setLoadout(loadout);
        setCurrentHP(currentHP);
        setSkills(skills);
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

        // ---Extra stuff---------
        setStatuses(statuses);
        setConditions(conditions);
        setBurn(burn);
    }
    /**
     * Creates a new Pilot from the provided pilotName and pilotCallsign.
     */
    public Pilot(String pilotName, String pilotCallsign) {
        this(
            // ---Dossier-------------------
            pilotName, pilotCallsign, "", "active",
            "", "", "",
            "",

            // ---Narrative Profile---------
            // add this.grit to get this.currentHP
            6 + 0, new SkillTriggersList(), new String[0], new Loadout(),

            // ---Tactical Profile---------
            0, new License[0], new String[0], new int[4],
            new String[0], new Talent[0],

            // ---Extra stuff---------
            new Status[0], new Condition[0], 0
        );
    }
    /**
     * Creates a deepest copy of the provided Pilot.
     * @param pilot a Pilot to be copied.
     * @return a Pilot deepest copy of the provided Pilot.
     */
    public Pilot(Pilot pilot) {
        // don't need to make copies of these because the mutators already do so
        // ---Dossier-------------------
        setName(pilot.name);
        setCallsign(pilot.callsign);
        setPlayer(pilot.player);
        setStatus(pilot.status);
        setBackground(pilot.background);
        setBiography(pilot.biography);
        setAppearance(pilot.appearance);
        setPlayerNotes(pilot.playerNotes);

        // All the pilot's stats are calculated here
        setLoadout(pilot.loadout);

        // ---Narrative Profile---------
        setSize(pilot.size);
        // setMaxHP() swapped with setCurrentHP() because the mutators may throw
        //     exceptions otherwise
        setMaxHP(pilot.maxHP);
        setCurrentHP(pilot.currentHP);
        setArmor(pilot.armor);
        setEvasion(pilot.evasion);
        setSpeed(pilot.speed);
        setEDefense(pilot.eDefense);
        setSkills(pilot.skills);
        setReserves(pilot.reserves);
        // setLoadout() moved upwards so it doesn't re-set any properties that
        //     are set above

        // ---Tactical Profile---------
        setLicenseLevel(pilot.licenseLevel);
        setLicenseList(pilot.licenseList);
        setSpecialEquipment(pilot.specialEquipment);
        setMechSkills(pilot.mechSkills);
        setCoreBonuses(pilot.coreBonuses);
        setTalents(pilot.talents);

        // ---Extra stuff---------
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
    public Size getSize() {
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
    public SkillTriggersList getSkills() {
        return new SkillTriggersList(skills);
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
    public Status[] getStatuses() {
        return HelperMethods.copyOf(statuses);
    }
    public Condition[] getConditions() {
        return HelperMethods.copyOf(conditions);
    }
    public int getBurn() {
        return burn;
    }
    // ---Dossier-------------------
    public void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    public void setCallsign(String callsign) {
        HelperMethods.checkString("New callsign", callsign);
        this.callsign = callsign;
    }
    public void setPlayer(String player) {
        HelperMethods.checkObject("New player", player);
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

        HelperMethods.checkString("New status", status);
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
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        HelperMethods.checkObject("New size", size);
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
            HelperMethods.warn("maxHP value provided: " + maxHP + " is <"
                + " currentHP value: " + this.currentHP);
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
     * Sets this.skills to the provided value.
     * @param skills a SkillTriggersList which cannot be null.
     * @throws IllegalArgumentException if skills is null.
     */
    public void setSkills(SkillTriggersList skills) {
        HelperMethods.checkObject("New skill triggers list value",
            skills);
        skills = new SkillTriggersList(skills);
        this.skills = skills;
    }
    /**
     * Sets this.reserves to the provided value.
     * @param reserves a String[] which cannot be null, contain null elements,
     *     or elements that are "".
     * @throws IllegalArgumentException if reserves is null, contains null
     *     values, or elements that are "".
     */
    public void setReserves(String[] reserves) {
        HelperMethods.checkStringArray("New reserves", reserves);
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
        HelperMethods.checkObject("loadout", loadout);
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
        HelperMethods.checkObjectArray("New license list",
            licenseList);
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
        HelperMethods.checkStringArray("New special equipment",
            specialEquipment);
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
        HelperMethods.checkObject("New mech skills value",
            mechSkills);
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
        HelperMethods.checkStringArray("New core bonuses",
            coreBonuses);
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
        HelperMethods.checkObjectArray("New talents value",
            talents);
        talents = HelperMethods.copyOf(talents);
        this.talents = talents;
    }
    /**
     * Sets this.statuses to the provided value.
     * @param statuses a Status[] which cannot be null or contain null elements.
     * @throws IllegalArgumentException if statuses is null or contains null
     *     elements.
     */
    public void setStatuses(Status[] statuses) {
        HelperMethods.checkObjectArray("New statuses", statuses);
        statuses = HelperMethods.copyOf(statuses);
        this.statuses = statuses;
    }
    /**
     * Sets this.conditions to the provided value.
     * @param conditions a Condition[] which cannot be null or contain null
     *     elements.
     * @throws IllegalArgumentException if conditions is null or contains
     *     null elements.
     */
    public void setConditions(Condition[] conditions) {
        HelperMethods.checkObjectArray("New conditions",
            conditions);
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
        outputString += this.skills.generateOutput();
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
        return size.output();
    }
    /**
     * Adds the provided status to this.statuses.
     * @param newStatus a Status containing the new status. Must have a valid
     *     Status.name property as defined by Database.statuses.
     * @param addDuplicate a boolean representing whether or not to add a second
     *     version of the same status if a status of the same name is already
     *     present in this.statuses.
     * @throws IllegalArgumentException if newStatus has a Status.name property
     *     that is invalid as defined by Database.statuses.
     */
    public void addStatus(Status newStatus, boolean addDuplicate) {
        boolean containsStatus = false;

        HelperMethods.checkObject("newStatus", newStatus);
        try {
            Database.getStatus(newStatus.getData().getName());
        } catch (NoSuchElementException exception) {
            throw new IllegalArgumentException("newStatus' Status.name"
                + " property: \"" + newStatus.getData().getName() + "\" is an"
                + " invalid status");
        }
        for (Status status : this.statuses) {
            if (status.getData().getName()
                .equals(newStatus.getData().getName())) {
                containsStatus = true;
            }
        }
        // if (! containsStatus) || addDuplicate:
        if (! containsStatus || addDuplicate) {
            setStatuses(HelperMethods.append(this.statuses, newStatus));
        }
    }
    /**
     * A helper method for addStatus(Status, boolean). Allows the method to be
     *     called with a default value of false for the boolean.
     * @param status a Status containing the new status. Must have a valid
     *     Status.name property as defined by Status.allowedPilotStatuses.
     */
    public void addStatus(Status status) {
        addStatus(status, false);
    }
    /**
     * Removes the provided status from this.statuses.
     * @param oldStatus a Status containing the status to be removed. Must have
     *     a valid Status.name property as defined by Database.statuses.
     * @param removeAll a boolean representing whether to remove all statuses
     *     with the same Status.name property as oldStatus if multiple statuses
     *     of the same type are present, or just the specified one.
     * @throws IllegalArgumentException if oldStatus has an invalid Status.name
     *     property as defined by Database.statuses.
     */
    public void removeStatus(Status oldStatus, boolean removeAll) {
        boolean areSame = false;
        Status[] newStatuses;

        HelperMethods.checkObject("oldStatus", oldStatus);
        try {
            Database.getStatus(oldStatus.getData().getName());
        } catch (NoSuchElementException exception) {
            throw new IllegalArgumentException("oldStatus has an invalid"
                + " Status.name property: \"" + oldStatus.getData().getName()
                + "\"");
        }
        for (int i = 0; i < this.statuses.length; i++) {
            if (removeAll) {
                areSame =
                    this.statuses[i].getData().getName()
                    .equals(oldStatus.getData().getName());
            } else {
                areSame = this.statuses[i].equals(oldStatus);
            }
            if (areSame) {
                newStatuses = new Status[this.statuses.length - 1];
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
     * Helper method for removeStatus(Status, boolean). Allows the method to be
     *     run with a default value of false for the boolean.
     * @param oldStatus a Status containing the status to be removed. Must have
     *     a valid Status.name property as defined by
     *     Status.allowedPilotStatuses.
     */
    public void removeStatus(Status oldStatus) {
        removeStatus(oldStatus, false);
    }
    /**
     * Recursively checks whether any of this.conditions or this.status have a
     *     State.name value of stateType, or whether any of the States they
     *     caused have such a value, and so on.
     * @param stateType a String containing a State.name value to search for.
     *     Must be a valid type as defined by State.allowedTypes.
     * @return a boolean containing the result of the check.
     */
    public boolean hasState(String stateType) {
        boolean isPresent = false;

        HelperMethods.checkObject("stateType", stateType);
        try {
            Database.getState(stateType);
        } catch (NoSuchElementException exception) {
            return false;
        }
        for (Condition condition : this.conditions) {
            isPresent = isPresent
                || condition.getData().getName().equals(stateType);
            isPresent = isPresent || condition.hasState(stateType);
        }
        if (isPresent) {
            return isPresent;
        }
        for (Status status : this.statuses) {
            isPresent = isPresent
                || status.getData().getName().equals(stateType);
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
    public void addCondition(Condition newCondition, boolean addDuplicate) {
        boolean containsCondition = false;

        HelperMethods.checkObject("newCondition", newCondition);
        for (Condition condition : this.conditions) {
            if (condition.getData().getName()
                .equals(newCondition.getData().getName())) {
                containsCondition = true;
            }
        }
        // if (! containsCondition) || addDuplicate:
        if (! containsCondition || addDuplicate) {
            setConditions(HelperMethods.append(this.conditions, newCondition));
        }
    }
    /**
     * A helper method for addCondition(Condition, boolean). Allows the method
     *     to be called with a default value of false for the boolean.
     * @param condition a Condition containing the new condition.
     */
    public void addCondition(Condition condition) {
        addCondition(condition, false);
    }
    /**
     * Removes the provided condition from this.conditions.
     * @param oldCondition a Condition containing the condition to be removed.
     * @param removeAll a boolean representing whether to remove all conditions
     *     with the same Condition.name property as oldCondition if multiple
     *     conditions of the same type are present, or just the specified one.
     */
    public void removeCondition(Condition oldCondition, boolean removeAll) {
        boolean areSame = false;
        Condition[] newConditions;

        HelperMethods.checkObject("oldCondition", oldCondition);
        for (int i = 0; i < this.conditions.length; i++) {
            if (removeAll) {
                areSame =
                    this.conditions[i].getData().getName()
                    .equals(oldCondition.getData().getName());
            } else {
                areSame = this.conditions[i].equals(oldCondition);
            }
            if (areSame) {
                newConditions = new Condition[this.conditions.length - 1];
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
     * Helper method for removeCondition(Condition, boolean). Allows the method
     *     to be run with a default value of false for the boolean.
     * @param oldCondition a Condition containing the condition to be removed.
     */
    public void removeCondition(Condition oldCondition) {
        removeCondition(oldCondition, false);
    }
    /**
     * Calculates a Pilot's stats based on its Loadout.
     */
    public void calculateAttributes() {
        int[] results;
        PilotArmor pilotArmor;
        boolean[] pilotBonusesFound;
        String[] pilotBonusKeys;
        Bonus bonus;
        String bonusID;
        int index = 0;

        // From pgs. 28 and 74:
        results = new int[] {
            1, // Size
            // add this.grit to get this.maxHP
            6 + this.grit, // Max HP
            0, // Armor
            10, // Evasion
            4, // Speed
            10 // E-defense
        };
        pilotArmor = this.loadout.getPilotArmor();
        if (pilotArmor != null) {
            if (pilotArmor.getBonuses() != null) {
                pilotBonusesFound = new boolean[6];
                pilotBonusKeys = new String[] {
                    "pilot_size", "pilot_hp", "pilot_armor", "pilot_evasion",
                    "pilot_speed", "pilot_edef"
                };
                for (int i = 0; i < pilotArmor.getBonuses().length; i++) {
                    bonus = pilotArmor.getBonuses()[i];
                    bonusID = bonus.getID();
                    index = -1;
                    for (int j = 0; j < pilotBonusKeys.length; j++) {
                        if (bonusID.equals(pilotBonusKeys[i])) {
                            index = j;
                            break;
                        }
                    }
                    if (index != -1 && ! pilotBonusesFound[index]) {
                        HelperMethods.warn("Duplicate bonuses"
                            + " found in this PilotArmor object");
                    }
                    if (index != -1) {
                        if (bonus.isReplace()) {
                            results[index] =
                                ((Integer) bonus.getValue()).intValue();
                        } else {
                            results[index] +=
                                ((Integer) bonus.getValue()).intValue();
                        }
                        pilotBonusesFound[index] = true;
                    }
                    if (pilotBonusesFound[0] && pilotBonusesFound[1]
                        && pilotBonusesFound[2] && pilotBonusesFound[3]
                        && pilotBonusesFound[4] && pilotBonusesFound[5]) {
                        // all bonuses found, we can go home early
                        break;
                    }
                }
            }
            setSize(new Size(results[0]));
            // setMaxHP() swapped with setCurrentHP() because the mutators may
            //     throw exceptions otherwise
            setMaxHP(results[1]);
            setCurrentHP(this.maxHP);
            setArmor(results[2]);
            setEvasion(results[3]);
            setSpeed(results[4]);
            setEDefense(results[5]);
        }
    }
    /**
     * Deals harm to this Pilot.
     * @param harm a Harm containing the harm to deal. Must have a Harm.type
     *     value that is not "variable". Must have a Harm.diceValue of something
     *     other than "" OR a Harm.flatValue that is > 0. Cannot be null.
     * @throws IllegalArgumentException if harm is null, harm's Harm.type
     *     property is "variable", or if harm's Harm.diceValue is "" AND harm's
     *     Harm.flatValue is < 1.
     */
    public void receiveHarm(Harm harm) {
        Damage damage;

        HelperMethods.checkObject("harm", harm);
        if (harm.getType().getValue().equals("variable")) {
            throw new IllegalArgumentException("harm value has a Harm.type"
                + " value of \"variable\"");
        }
        if (harm.getDiceValue() == null && harm.getFlatValue().getValue() == 0)
        {
            throw new IllegalArgumentException("harm.diceValue is \"\" and"
                + " harm.flatValue value: " + harm.getFlatValue() + " is < 1");
        }
        damage = harm.toDamage();
        if (harm.getType().getValue().equals("heat")) {
            receiveHeat(damage);
        } else if (harm.getType().getValue().equals("burn")) {
            receiveBurn(damage);
        } else {
            receiveDamage(damage);
        }
    }
    /**
     * Deals damage to this Pilot.
     * @param damage a Damage containing the damage to deal. Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveDamage(Damage damage) {
        // See pg. 48
        // TODO: fill out with damage mitigation - armor, resistance etc - see
        //     pg. 49 - "Armor is subtracted from any damage they take, unless
        //     it’s dealt by an armor-piercing weapon (i.e. a weapon with the AP
        //     tag) or is caused by something particularly dramatic, like a long
        //     fall, immersion in lava, or exposure to a vacuum."
        int remainingDamage;
        int damageToTake;
        int newCurrentHP;

        HelperMethods.checkObject("damage", damage);
        // damage is being rolled here
        remainingDamage = damage.roll();
        damageToTake = Math.min(remainingDamage, this.currentHP);
        newCurrentHP = this.currentHP - damageToTake;
        setCurrentHP(newCurrentHP);
        // if the amount of damage they're taking is greater than our pilot's
        //     maximum HP, they will be critical no matter what their current HP
        //     is
        if (remainingDamage > this.currentHP) {
            // we're about to become critical
            becomeCritical();
        }
    }
    /**
     * Deals heat to this Pilot, converting it to Energy damage as per the rule
     *     on biological targets receiving heat.
     * @param heat a Damage containing the heat to deal which must have a
     *     Damage.type value of "heat". Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveHeat(Damage heat) {
        // See pg. 48
        // TODO: fill out with mitigation, resistance etc
        int remainingHeat;

        HelperMethods.checkObject("heat", heat);
        if (! heat.getType().getValue().equals("heat")) {
            throw new IllegalArgumentException("heat has a Damage.type value"
                + " of: \"" + heat.getType() + "\"");
        }
        // heat is being rolled here
        remainingHeat = heat.roll();
        receiveDamage(new Damage(Database.getDamageType("energy"),
            null, remainingHeat));
    }
    /**
     * Deals burn to this Pilot.
     * @param burn a Damage containing the burn to deal which must have a
     *     Damage.type value of "burn". Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveBurn(Damage burn) {
        // TODO: fill out with mitigation, resistance etc
        int burnAmount;

        HelperMethods.checkObject("burn", burn);
        if (! burn.getType().getValue().equals("burn")) {
            throw new IllegalArgumentException("burn has a Damage.type value"
                + " of: \"" + burn.getType() + "\"");
        }
        // burn is being rolled here
        burnAmount = burn.roll();
        receiveDamage(new Damage(burn.getType(), null, burnAmount));
        addBurn(burnAmount);
    }
    /**
     * Represents the Pilot taking a lethal or near-lethal hit. Is called
     *     whenever this.currentHP is reduced to 0.
     */
    public void becomeCritical() {
        // See pg. 49
        // TODO: on a roll of 2–5, you can choose for your pilot to die rather
        //     than becoming DOWN AND OUT.
        int downRoll = Roll.roll("d6");

        if (downRoll == 6) {
            // On 6, your pilot barely shrugs off the hit (or it’s a close call)
            //     – they return to 1 HP.
            setCurrentHP(1);
        } else if (downRoll == 1) {
            // On 1, your pilot’s luck has run out – they die immediately.
            destroy();
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
        // See pg. 49
        Status downed = new Status(
            Database.getStatus("down and out"),
            new Duration("until removed"), "Pilot (self)"
        );
        Condition stun = new Condition(
            Database.getStatus("stunned"),
            new Duration("source"), "down and out status"
        );

        downed.addEffect(stun);
        addStatus(downed);
        System.out.println("This Pilot has been downed");
    }
    /**
     * Is called whenever this Pilot dies.
     * 
     * "If your pilot dies, it might not be the end for them. [See pgs. 83 -
     *     84]"
     * - pg. 49
     * 
     * See pgs. 49, 83 - 84.
     */
    public void destroy() {
        // TODO: fill out
        System.out.println("This Pilot has died");
    }
    /**
     * Ends this Pilot's current turn.
     * See pgs. 60 and 67 - 68.
     */
    public void endTurn() {
        // TODO: fill out
        // burn check - see pg. 67.
        if (! Roll.evaluateCheck(Roll.check(3, this.mechSkills))) {
            // If the burn check fails
            receiveDamage(new Damage(Database.getDamageType("burn"),
                null, this.burn));
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
        boolean skills = checkSkills();
        boolean licenses = checkLicenses();
        boolean coreBonuses = checkCoreBonuses();
        boolean talents = checkTalents();

        return skills && licenses && coreBonuses && talents;
    }
    /**
     * Checks the validity (and number) of skill triggers for this Pilot.
     * @return a boolean containing the result of the check.
     */
    public boolean checkSkills() {
        // see pg. 18
        // total skill trigger level should be 4 + licenseLevel
        int totalLevel = 0;

        for (Skill skills : this.skills.getSkills())
        {
            totalLevel += skills.getLevel() / 2;
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
            outputString += this.skills.generateOutput();
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
            outputString += this.skills.generateOutput();
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
                outputString += this.licenseList[i].toString();
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
                    outputString += this.licenseList[j].toString();
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
                outputString += this.talents[i].toString();
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
                    outputString += this.talents[i].toString();
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