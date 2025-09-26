/**
 * Represents the pilot portion of a Lancer character, i.e. the non-mech items.
 * Stores various pieces of information such as the pilot's name, callsign,
 *     stats, licenses, and so on.
 */
public class Pilot {
    // ---Dossier-------------------
    // name and callsign
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String name;
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String callsign;

    // (optional) details
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String player;
    /**
     * Can be one of the following:
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
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String background;

    // further optional details
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String biography;
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String appearance;
    /**
     * Can be any String, though "" is a placeholder. Cannot be null.
     */
    private String playerNotes;

    // ---Narrative Profile---------
    // stats
    /**
     * Must be between -1 (placeholder) and 6 (inclusive).
     */
    private int grit;
    /**
     * Must be between -1 (placeholder) and maxHP (inclusive).
     */
    private int currentHP;
    /**
     * Must be >= -1 (which is a placeholder). Cannot be 0.
     */
    private int maxHP;
    /**
     * Must be >= -1 (which is a placeholder).
     */
    private int armor;
    /**
     * Must be >= -1 (which is a placeholder).
     */
    private int evasion;
    /**
     * Must be >= -1 (which is a placeholder).
     */
    private int speed;
    /**
     * Must be >= -1 (which is a placeholder).
     */
    private int eDefense;
    
    // Skill triggers
    private SkillTriggersList skillTriggers;

    // Reserves and bonuses
    private String[] reserves;

    // Gear Loadout
    private Loadout loadout;

    // ---Tactical Profile---------
    // licenses
    /**
     * Must be between -1 (placeholder) and 12 (inclusive).
     */
    private int licenseLevel;
    private License[] licenseList;

    // Special equipment
    private String[] specialEquipment;

    // Mech skills
    private int[] mechSkills;

    // Core bonuses
    private String[] coreBonuses;

    // Talents
    private Talent[] talents;

    public Pilot() {
        // ---Dossier-------------------
        setName("");
        setCallsign("");
        setPlayer("");
        setStatus("Active");
        setBackground("");
        setBiography("");
        setAppearance("");
        setPlayerNotes("");

        // ---Narrative Profile---------
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
        setMechSkills(new int[] {0, 0, 0, 0});
        setCoreBonuses(new String[0]);
        setTalents(new Talent[0]);
    }

    // ---Dossier-------------------
    public String getName() {
        return name;
    }
    public void setName(String name) {
        // accept a String only, do NOT accept null
        if (name == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot name: null");
        }
        this.name = name;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        // accept a String only, do NOT accept null
        if (callsign == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot callsign: null");
        }
        this.callsign = callsign;
    }
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        // accept a String only, do NOT accept null
        if (player == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "player name: null");
        }
        this.player = player;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        boolean isValidStatus = false;

        if (status == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot status: null");
        }
        status = status.toLowerCase();
        for (String s : Pilot.allowedStatuses) {
            if (status.equals(s)) {
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
    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        // accept a String only, do NOT accept null
        if (background == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot background: null");
        }
        this.background = background;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        // accept a String only, do NOT accept null
        if (biography == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot biography: null");
        }
        this.biography = biography;
    }
    public String getAppearance() {
        return appearance;
    }
    public void setAppearance(String appearance) {
        // accept a String only, do NOT accept null
        if (appearance == null) {
            throw new IllegalArgumentException("Invalid value provided for "
                + "pilot appearance: null");
        }
        this.appearance = appearance;
    }
    public String getPlayerNotes() {
        return playerNotes;
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
    public int getGrit() {
        return grit;
    }
    private void setGrit(int grit) {
        if (grit < -1) {
            throw new IllegalArgumentException("New grit value is < -1");
        }
        if (grit > 6) {
            throw new IllegalArgumentException("New grit value is > 6");
        }
        this.grit = grit;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int currentHP) {
        if (currentHP < -1) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is < -1");
        } else if (currentHP > maxHP) {
            throw new IllegalArgumentException("Error: currentHP value"
                + " provided: " + currentHP + " is > maxHP value " + maxHP);
        }
        this.currentHP = currentHP;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        if (maxHP < -1) {
            throw new IllegalArgumentException("New maxHP value is < -1");
        }
        if (maxHP == 0) {
            throw new IllegalArgumentException("New maxHP value is 0");
        }
        if (getCurrentHP() > maxHP) {
            System.out.println("WARNING: currentHP is more than maxHP");
        }
        this.maxHP = maxHP;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        if (armor < -1) {
            throw new IllegalArgumentException("New armor value is < -1");
        }
        this.armor = armor;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int evasion) {
        if (evasion < -1) {
            throw new IllegalArgumentException("New evasion value is < -1");
        }
        this.evasion = evasion;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        if (speed < -1) {
            throw new IllegalArgumentException("New speed value is < -1");
        }
        this.speed = speed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public void setEDefense(int eDefense) {
        if (eDefense < -1) {
            throw new IllegalArgumentException("New e-defense value is < -1");
        }
        this.eDefense = eDefense;
    }
    public SkillTriggersList getSkillTriggers() {
        return skillTriggers;
    }
    public void setSkillTriggers(SkillTriggersList skillTriggers) {
        // accept a value of a blank SkillTriggersList or a valid object, but no
        //     in betweens
        if (skillTriggers == null) {
            throw new IllegalArgumentException("New skill triggers list value"
                + " is null");
        }
        if (skillTriggers.getSkillTriggers() == null) {
            throw new IllegalArgumentException("New skill triggers list's"
                + "skill triggers list is null");
        }
        if (skillTriggers.hasPlaceholders()) {
            throw new IllegalArgumentException("New skill triggers list value"
                + " is invalid");
        }
        this.skillTriggers = skillTriggers;
    }
    public String[] getReserves() {
        return reserves;
    }
    public void setReserves(String[] reserves) {
        // accept a value of a blank array or a valid array, but no in betweens
        if (reserves == null) {
            throw new IllegalArgumentException("New reserves value is"
                + " invalid");
        } else if (reserves.length != 0) {
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
    public Loadout getLoadout() {
        return loadout;
    }
    public void setLoadout(Loadout loadout) {
        // accept a value of a blank Loadout or a Loadout object, but no in
        //     betweens
        if (loadout == null) {
            throw new IllegalArgumentException("New loadout value is null");
        }
        this.loadout = loadout;
    }

    // ---Tactical Profile---------
    public int getLicenseLevel() {
        return licenseLevel;
    }
    public void setLicenseLevel(int licenseLevel) {
        if (licenseLevel < -1) {
            throw new IllegalArgumentException("New license level value is"
                + " < -1");
        }
        if (licenseLevel > 12) {
            throw new IllegalArgumentException("New license level value"
                + " is > 12");
        }
        if (licenseLevel == -1) {
            this.licenseLevel = licenseLevel;
            setGrit(licenseLevel);
        } else {
            this.licenseLevel = licenseLevel;
            setGrit((licenseLevel + 1) / 2);
        }
    }
    public License[] getLicenseList() {
        return licenseList;
    }
    public void setLicenseList(License[] licenseList) {
        // accept a value of a blank array or an array of valid (without
        //     placeholder values) Licenses, but no in betweens
        if (licenseList == null) {
            throw new IllegalArgumentException("New license list value is"
                + " null");
        } else if (licenseList.length != 0) {
            for (License license : licenseList) {
                if (license == null) {
                    throw new IllegalArgumentException("New license list"
                        + " value includes a null element");
                }
                if (license.hasPlaceholders()) {
                    throw new IllegalArgumentException("New license list"
                        + " value includes an element with a placeholder"
                        + " value");
                }
            }
        }
        this.licenseList = licenseList;
    }
    public String[] getSpecialEquipment() {
        return specialEquipment;
    }
    public void setSpecialEquipment(String[] specialEquipment) {
        // accept a value of a blank array or an array of valid Strings (not
        //     null or a placeholder value), but no in betweens
        if (specialEquipment == null) {
            throw new IllegalArgumentException("New special equipment value"
                + " is null");
        } else if (specialEquipment.length != 0) {
            for (String equipment : specialEquipment) {
                if (equipment == null) {
                    throw new IllegalArgumentException("New special equipment"
                        + " value includes an element with a value of null");
                } else if (equipment.equals("")) {
                    throw new IllegalArgumentException("New special equipment"
                        + " value includes an element with a value of \"\"");
                }
            }
        }
        this.specialEquipment = specialEquipment;
    }
    public int[] getMechSkills() {
        return mechSkills;
    }
    public void setMechSkills(int[] mechSkills) {
        // accept a value of a blank array or a length 4 array of valid ints
        //     (between 0 and 6, inclusive), but no in betweens
        if (mechSkills == null) {
            throw new IllegalArgumentException("New mech skills value is"
                + " null");
        } else if (mechSkills.length != 4) {
            throw new IllegalArgumentException("New mech skills value is"
                + " invalid");
        } else {
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
        }
        this.mechSkills = mechSkills;
    }
    public String[] getCoreBonuses() {
        return coreBonuses;
    }
    public void setCoreBonuses(String[] coreBonuses) {
        // accept a value of a blank array or an array of valid Strings (not
        //     null or a placeholder value), but no in betweens
        if (coreBonuses == null) {
            throw new IllegalArgumentException("New core bonuses value is"
                + " invalid");
        } else if (coreBonuses.length != 0) {
            for (String coreBonus : coreBonuses) {
                if (coreBonus == null) {
                    throw new IllegalArgumentException("New core bonuses"
                        + " value includes an element with a value of null");
                } else if (coreBonus.equals("")) {
                    throw new IllegalArgumentException("New core bonuses"
                        + " value includes an element with a value of \"\"");
                }
            }
        }
        this.coreBonuses = coreBonuses;
    }
    public Talent[] getTalents() {
        return talents;
    }
    public void setTalents(Talent[] talents) {
        // accept a value of a blank Talent array or an array of valid Talents
        //     (not null and don't contain any placeholder values), but no in
        //     betweens
        if (talents == null) {
            throw new IllegalArgumentException("New talents value is"
                + " invalid");
        } else if (talents.length != 0) {
            for (Talent talent : talents) {
                if (talent == null) {
                    throw new IllegalArgumentException("New talents value"
                        + " includes a null element");
                }
                if (talent.hasPlaceholders()) {
                    throw new IllegalArgumentException("New talents value"
                        + " includes an element with a placeholder value");
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

        if (outputType.equals("mech build")) {
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ STATS ]\n";
            outputString += "  HULL:" + mechSkills[0] + " AGI:" + mechSkills[1]
                + " SYS:" + mechSkills[2] + " ENGI:" + mechSkills[3];
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
                outputString += licenseList[i].getFrame() + " "
                    + licenseList[i].getLicenseLevel();
            }
            outputString += "\n";
        } else if (outputType.equals("pilot")
            || outputType.equals("full")) {
            // Output something like:
            //     "  IPS-N Blackbeard 1, IPS-N Drake 1,\n  IPS-N Lancaster 1\n"
            for (int i = 0; i < licenseList.length; i += 2) {
                outputString += "  ";
                for (int j = i; j < Math.min(i + 2, licenseList.length); j++) {
                    outputString += licenseList[j].getFrame() + " "
                        + licenseList[j].getLicenseLevel();
                    if (j == i && i + 1 < licenseList.length) {
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
                if (i + 1 < talents.length) {
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
                for (int ii = i; ii < Math.min(i + 2, talents.length); ii++) {
                    outputString += talents[ii].name + " " + talents[ii].level;
                    if (ii == i && i + 1 < talents.length) {
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
                for (int ii = i; ii < Math.min(i + 2, coreBonuses.length);
                    ii++) {
                    outputString += coreBonuses[ii];
                    if (i + 1 >= coreBonuses.length) {
                        continue;
                    }
                    if (ii == i) {
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