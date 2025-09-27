/**
 * Represents the pilot portion of a Lancer character, i.e. the non-mech items.
 * Stores various pieces of information such as the pilot's name, callsign,
 *     stats, licenses, and so on.
 */
public class Pilot {
    // ---Dossier-------------------
    // name and callsign
    /**
     * Can be any String other than "". Cannot be null. Set to "" on
     *     construction.
     */
    private String name;
    /**
     * Can be any String other than "". Cannot be null. Set to "" on
     *     construction.
     */
    private String callsign;

    // (optional) details
    /**
     * Can be any String. Cannot be null.
     */
    private String player;
    /**
     * Must be one of the following:
     *     "active", "inactive", "retired", "missing in action",
     *     "killed in action", "unknown"
     * Case-insensitive and stored in lowercase. Cannot be null.
     */
    private String status;
    static final String[] allowedStatuses = {
        "active", "inactive", "retired", "missing in action",
        "killed in action", "unknown"
    };
    /**
     * Can be any String. Cannot be null.
     */
    private String background;

    // further optional details
    /**
     * Can be any String. Cannot be null.
     */
    private String biography;
    /**
     * Can be any String. Cannot be null.
     */
    private String appearance;
    /**
     * Can be any String. Cannot be null.
     */
    private String playerNotes;

    // ---Narrative Profile---------
    // stats
    /**
     * Must be between 0 and 6 (inclusive).
     */
    private int grit;
    /**
     * Must be between 1 and maxHP (inclusive).
     */
    private int currentHP;
    /**
     * Must be >= 1.
     */
    private int maxHP;
    /**
     * Must be >= 0.
     */
    private int armor;
    /**
     * Must be >= 0.
     */
    private int evasion;
    /**
     * Must be >= 0.
     */
    private int speed;
    /**
     * Must be >= 0.
     */
    private int eDefense;
    
    // Skill triggers
    /**
     * Can be any SkillTriggersList. Cannot be null.
     */
    private SkillTriggersList skillTriggers;

    // Reserves and bonuses
    /**
     * Can be any String[]. Cannot be null or contain null elements or
     *     placeholders.
    */
    private String[] reserves;

    // Gear Loadout
    /**
     * Can be any Loadout. Cannot be null.
    */
    private Loadout loadout;

    // ---Tactical Profile---------
    // licenses
    /**
     * Must be between 0 and 12 (inclusive).
     */
    private int licenseLevel;
    /**
     * Can be any License[] that does not contain null or elements with
     *     placeholders. Cannot be null.
     */
    private License[] licenseList;

    // Special equipment
    /**
     * Can be any String[]. Cannot be null or contain null elements.
     */
    private String[] specialEquipment;

    // Mech skills
    /**
     * Must be an int[] of length 4. Each element must be between 0 and 6
     *     (inclusive).
     */
    private int[] mechSkills;

    // Core bonuses
    /**
     * Can be any String[] that does not contain null elements or placeholders.
     *     Cannot be null.
     */
    private String[] coreBonuses;

    // Talents
    /**
     * Can be any Talent[] that does not contain null elements or elements with
     *     placeholders. Cannot be null.
     */
    private Talent[] talents;

    public Pilot() {
        // ---Dossier-------------------
        this.name = "";
        this.callsign = "";
        setPlayer("");
        setStatus("Active");
        setBackground("");
        setBiography("");
        setAppearance("");
        setPlayerNotes("");

        // ---Narrative Profile---------
        // setGrit() is unnecessary because licenseLevel is set later
        // setMaxHP() swapped with setCurrentHP() because the function may throw
        //     an exception otherwise
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
    public Pilot(String pilotName, String callsign) {
        this();
        setName(pilotName);
        setCallsign(callsign);
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
        return skillTriggers;
    }
    public String[] getReserves() {
        return reserves;
    }
    public Loadout getLoadout() {
        return loadout;
    }
    // ---Tactical Profile---------
    public int getLicenseLevel() {
        return licenseLevel;
    }
    public License[] getLicenseList() {
        return licenseList;
    }
    public String[] getSpecialEquipment() {
        return specialEquipment;
    }
    public int[] getMechSkills() {
        return mechSkills;
    }
    public String[] getCoreBonuses() {
        return coreBonuses;
    }
    public Talent[] getTalents() {
        return talents;
    }
    // ---Dossier-------------------
    public void setName(String name) {
        // accept a non-"" String only, do NOT accept null
        if (name == null) {
            throw new IllegalArgumentException("Invalid value provided for"
                + "pilot name: null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid value provided for"
                + " pilot name: \"\"");
        }
        this.name = name;
    }
    public void setCallsign(String callsign) {
        // accept a non-"" String only, do NOT accept null
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
        // accept a String only, do NOT accept null
        if (player == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "player name: null");
        }
        this.player = player;
    }
    public void setStatus(String status) {
        // accept a valid status only, do NOT accept null
        boolean isValidStatus = false;

        if (status == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot status: null");
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
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot status: " + status);
        }
    }
    public void setBackground(String background) {
        // accept a String only, do NOT accept null
        if (background == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot background: null");
        }
        this.background = background;
    }
    public void setBiography(String biography) {
        // accept a String only, do NOT accept null
        if (biography == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot biography: null");
        }
        this.biography = biography;
    }
    public void setAppearance(String appearance) {
        // accept a String only, do NOT accept null
        if (appearance == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot appearance: null");
        }
        this.appearance = appearance;
    }
    public void setPlayerNotes(String playerNotes) {
        // accept a String only, do NOT accept null
        if (playerNotes == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "player notes: null");
        }
        this.playerNotes = playerNotes;
    }
    // ---Narrative Profile---------
    private void setGrit(int grit) {
        if (grit < 0) {
            throw new IllegalArgumentException("New grit value is < 0");
        }
        if (grit > 6) {
            throw new IllegalArgumentException("New grit value is > 6");
        }
        this.grit = grit;
    }
    public void setCurrentHP(int currentHP) {
        if (currentHP < 1) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is < 1");
        }
        if (currentHP > maxHP) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is > maxHP value " + maxHP);
        }
        this.currentHP = currentHP;
    }
    public void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("New maxHP value is < 1");
        }
        if (getCurrentHP() > maxHP) {
            System.out.println("WARNING: currentHP is more than maxHP");
        }
        this.maxHP = maxHP;
    }
    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("New armor value is < 0");
        }
        this.armor = armor;
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
            throw new IllegalArgumentException("New e-defense value is < 0");
        }
        this.eDefense = eDefense;
    }
    public void setSkillTriggers(SkillTriggersList skillTriggers) {
        // accept any SkillTriggersList, do NOT accept null
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers list value"
                + " is null");
        }
        this.skillTriggers = skillTriggers;
    }
    public void setReserves(String[] reserves) {
        // accept any String[] that doesn't include null elements or "", do NOT
        //     accept null
        if (reserves == null) {
            throw new IllegalArgumentException("New reserves value is"
                + " invalid");
        }
        if (reserves.length != 0) {
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
        }
        this.reserves = reserves;
    }
    public void setLoadout(Loadout loadout) {
        // accept any Loadout, do NOT accept null
        if (loadout == null) {
            throw new IllegalArgumentException("New loadout value is null");
        }
        this.loadout = loadout;
    }
    // ---Tactical Profile---------
    public void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < 0) {
            throw new IllegalArgumentException("New license level value is"
                + " < 0");
        }
        if (licenseLevel > 12) {
            throw new IllegalArgumentException("New license level value"
                + " is > 12");
        }
        if (licenseLevel == -1) {
            setGrit(licenseLevel);
        } else {
            setGrit((licenseLevel + 1) / 2);
        }
        this.licenseLevel = licenseLevel;
    }
    public void setLicenseList(License[] licenseList) {
        // accept a value of a blank array or an array of valid (without
        //     placeholder values) Licenses, but no in betweens, do NOT accept
        //     null
        if (licenseList == null) {
            throw new IllegalArgumentException("New license list value is"
                + " null");
        }
        if (licenseList.length != 0) {
            for (License license : licenseList) {
                if (license == null) {
                    throw new IllegalArgumentException("New license list"
                        + " value includes a null element");
                }
            }
        }
        this.licenseList = licenseList;
    }
    public void setSpecialEquipment(String[] specialEquipment) {
        // accept a value of a blank array or an array of valid Strings (not
        //     null or a placeholder value), but no in betweens, do NOT accept
        //     null
        if (specialEquipment == null) {
            throw new IllegalArgumentException("New special equipment value"
                + " is null");
        }
        if (specialEquipment.length != 0) {
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
        }
        this.specialEquipment = specialEquipment;
    }
    public void setMechSkills(int[] mechSkills) {
        // accept a length 4 array of valid ints (between 0 and 6, inclusive),
        //     do NOT accept null
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
                    + " includes an invalid element");
            }
            if (mechSkill > 6) {
                throw new IllegalArgumentException("New mech skills value"
                    + " includes an invalid element");
            }
        }
        this.mechSkills = mechSkills;
    }
    public void setCoreBonuses(String[] coreBonuses) {
        // accept any array of Strings that does not include null elements or
        //     placeholders, do NOT accept null
        if (coreBonuses == null) {
            throw new IllegalArgumentException("New core bonuses value is"
                + " invalid");
        }
        if (coreBonuses.length != 0) {
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
        }
        this.coreBonuses = coreBonuses;
    }
    public void setTalents(Talent[] talents) {
        // accept any array of Talents, do NOT accept null
        if (talents == null) {
            throw new IllegalArgumentException("New talents value is"
                + " invalid");
        } else if (talents.length != 0) {
            for (Talent talent : talents) {
                if (talent == null) {
                    throw new IllegalArgumentException("New talents value"
                        + " includes a null element");
                }
            }
        }
        this.talents = talents;
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
        int[] mechSkills = getMechSkills();
        String name = getName();
        String callsign = getCallsign();
        int licenseLevel = getLicenseLevel();
        SkillTriggersList skillTriggers = getSkillTriggers();
        Loadout loadout = getLoadout();

        if (outputType.equals("mech build")) {
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ STATS ]\n";
            outputString += "  HULL:" + mechSkills[0] + " AGI:" + mechSkills[1]
                + " SYS:" + mechSkills[2] + " ENGI:" + mechSkills[3] + "\n";
        } else if (outputType.equals("pilot")) {
            outputString += "» " + name + " // " + callsign.toUpperCase()
                + " «\n";
            outputString += "  LL" + licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += loadout.generateOutput();
            outputString += "[ MECH SKILLS ]\n";
            outputString += "  GRIT:" + getGrit() + " // H:" + mechSkills[0]
                + " A:" + mechSkills[1] + " S:" + mechSkills[2] + " E:"
                + mechSkills[3] + "\n";
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
        } else if (outputType.equals("full")) {
            outputString += "» " + name + " // " + callsign.toUpperCase()
                + " «\n";
            outputString += "  LL" + licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += loadout.generateOutput();
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
     * Helper function for Pilot.generateOutput() that outputs only
     *     the licenses portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the licenses portion of the printout.
     */
    public String outputLicenses(String outputType) {
        String outputString = "";
        License[] licenseList = getLicenseList();

        if (licenseList.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something like:
            //     "  IPS-N Blackbeard 1, IPS-N Drake 1, IPS-N Lancaster 1\n"
            outputString += "  ";
            for (int i = 0; i < licenseList.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += licenseList[i].getName() + " "
                    + licenseList[i].getLevel();
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something like:
            //     "  IPS-N Blackbeard 1, IPS-N Drake 1,\n  IPS-N Lancaster 1\n"
            for (int i = 0; i < licenseList.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, licenseList.length); j++) {
                    outputString += licenseList[j].getName() + " "
                        + licenseList[j].getLevel();
                    if (j == i && i < licenseList.length + 1) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < licenseList.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * Helper function for Pilot.generateOutput() that outputs only the talents
     *     portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the talents portion of the printout.
     */
    public String outputTalents(String outputType) {
        String outputString = "";
        Talent[] talents = getTalents();

        if (talents.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something like:
            //     "  Ace 1, Brawler 1, Bonded 1\n"
            outputString += "  ";
            for (int i = 0; i < talents.length; i++) {
                outputString += talents[i].name + " " + talents[i].level;
                if (i < talents.length + 1) {
                    outputString += ", ";
                }
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something like:
            //     "  Ace 1, Brawler 1,\n"
            //     "  Bonded 1\n"
            for (int i = 0; i < talents.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, talents.length); j++) {
                    outputString += talents[j].name + " " + talents[j].level;
                    if (j == i && i < talents.length + 1) {
                        outputString += ", ";
                    }
                }
                if (i + 2 < talents.length) {
                    outputString += ",";
                }
                outputString += "\n";
            }
        }

        return outputString;
    }
    /**
     * Helper function for Pilot.generateOutput() that outputs only the core
     *     bonuses portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the core bonuses portion of the printout.
     */
    public String outputCoreBonuses(String outputType) {
        String outputString = "";
        String[] coreBonuses = getCoreBonuses();

        if (coreBonuses.length == 0) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType.equals("mech build")) {
            // Output something of the following form:
            //     "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            //     " Armament\n"
            outputString += "  ";
            for (int i = 0; i < coreBonuses.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += coreBonuses[i];
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something of the following form:
            //     "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n"
            //     "  Improved Armament\n"
            for (int i = 0; i < coreBonuses.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, coreBonuses.length); j++) {
                    outputString += coreBonuses[j];
                    if (i + 1 >= coreBonuses.length) {
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
        if (getCallsign().equals("")) {
            return true;
        }

        return false;
    }
}