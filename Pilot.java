/**
 * Represents the pilot portion of a Lancer character, i.e. the non-mech items.
 * Stores various pieces of information such as the pilot's name, callsign,
 *     stats, licenses, and so on.
 */
public class Pilot {
    // ---Dossier-------------------
    // name and callsign
    private String name;
    private String callsign;

    // (optional) details
    private String player;
    private String status;
    static final String[] allowedStatuses = {
        "active", "inactive", "retired", "missing in action", "killed in action", "unknown"
    };
    private String background;

    // further optional details
    private String biography;
    private String appearance;
    private String playerNotes;

    // ---Narrative Profile---------
    // stats
    private int grit;
    private int currentHP;
    private int maxHP;
    private int armor;
    private int evasion;
    private int speed;
    private int eDefense;
    
    // Skill triggers
    private SkillTriggersList skillTriggers;

    // Reserves and bonuses
    private String[] reserves;

    // Gear Loadout
    private Loadout gearLoadout;

    // ---Tactical Profile---------
    // licenses
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
        setMaxHP(8); // swapped with currentHP because the function may throw an exception otherwise
        setCurrentHP(8);
        setArmor(0);
        setEvasion(10);
        setSpeed(4);
        setEDefense(10);
        setSkillTriggers(new SkillTriggersList());
        setReserves(new String[0]);
        setGearLoadout(new Loadout());

        // ---Tactical Profile---------
        setLicenseLevel(0);
        setLicenseList(new License[] {
        });
        setSpecialEquipment(new String[0]);
        setMechSkills(new int[] {0, 0, 0, 0});
        setCoreBonuses(new String[0]);
        setTalents(new Talent[] {
            new Talent()
        });
    }

    // ---Dossier-------------------
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        // accept a String only, do NOT accept null
        if (newName == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot name: null");
        }
        name = newName;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String newCallsign) {
        // accept a String only, do NOT accept null
        if (newCallsign == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot callsign: null");
        }
        callsign = newCallsign;
    }
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String newPlayer) {
        // accept a String only, do NOT accept null
        if (newPlayer == null) {
            throw new IllegalArgumentException("Invalid value provided for player name: null");
        }
        player = newPlayer;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String newStatus) {
        boolean isValidStatus = false;

        if (newStatus == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot status: null");
        }
        newStatus = newStatus.toLowerCase();
        for (String s : Pilot.allowedStatuses) {
            if (newStatus.equals(s)) {
                isValidStatus = true;
                break;
            }
        }
        if (isValidStatus) {
            status = newStatus;
        } else {
            throw new IllegalArgumentException("Invalid value provided for pilot status: " + newStatus);
        }
    }
    public String getBackground() {
        return background;
    }
    public void setBackground(String newBackground) {
        // accept a String only, do NOT accept null
        if (newBackground == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot background: null");
        }
        background = newBackground;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String newBiography) {
        // accept a String only, do NOT accept null
        if (newBiography == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot biography: null");
        }
        biography = newBiography;
    }
    public String getAppearance() {
        return appearance;
    }
    public void setAppearance(String newAppearance) {
        // accept a String only, do NOT accept null
        if (newAppearance == null) {
            throw new IllegalArgumentException("Invalid value provided for pilot appearance: null");
        }
        appearance = newAppearance;
    }
    public String getPlayerNotes() {
        return playerNotes;
    }
    public void setPlayerNotes(String newPlayerNotes) {
        // accept a String only, do NOT accept null
        if (newPlayerNotes == null) {
            throw new IllegalArgumentException("Invalid value provided for player notes: null");
        }
        playerNotes = newPlayerNotes;
    }

    // ---Narrative Profile---------
    public int getGrit() {
        return grit;
    }
    public void setGrit(int newGrit) {
        grit = newGrit;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int newCurrentHP) {
        if (newCurrentHP < 0) {
            throw new IllegalArgumentException(
                "Error: currentHP value provided: " + newCurrentHP + " is less than 0"
            );
        } else if (newCurrentHP > maxHP) {
            throw new IllegalArgumentException(
                "Error: currentHP value provided: " + newCurrentHP + " is greater than maxHP value " + maxHP
            );
        } else {
            currentHP = newCurrentHP;
        }
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int newMaxHP) {
        maxHP = newMaxHP;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int newArmor) {
        armor = newArmor;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int newEvasion) {
        evasion = newEvasion;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    public int getEDefense() {
        return eDefense;
    }
    public void setEDefense(int newEDefense) {
        eDefense = newEDefense;
    }
    public SkillTriggersList getSkillTriggers() {
        return skillTriggers;
    }
    public void setSkillTriggers(SkillTriggersList newSkillTriggers) {
        // accept a value of null or a valid object
        skillTriggers = newSkillTriggers;
    }
    public String[] getReserves() {
        return reserves;
    }
    public void setReserves(String[] newReserves) {
        // TODO: update to accept null
        // accept a value of null or a valid array, but no in betweens
        reserves = newReserves;
    }
    public Loadout getGearLoadout() {
        return gearLoadout;
    }
    public void setGearLoadout(Loadout newGearLoadout) {
        // accept a value of null or a valid object, but no in betweens
        gearLoadout = newGearLoadout;
    }

    // ---Tactical Profile---------
    public int getLicenseLevel() {
        return licenseLevel;
    }
    public void setLicenseLevel(int newLicenseLevel) {
        licenseLevel = newLicenseLevel;
        grit = (licenseLevel + 1) / 2;
    }
    public License[] getLicenseList() {
        return licenseList;
    }
    public void setLicenseList(License[] newLicenseList) {
        // TODO: update to accept null
        // accept a value of null or a valid array of arrays, but no in betweens
        licenseList = newLicenseList;
    }
    public String[] getSpecialEquipment() {
        return specialEquipment;
    }
    public void setSpecialEquipment(String[] newSpecialEquipment) {
        // TODO: update to accept null
        // accept a value of null or a valid array, but no in betweens
        specialEquipment = newSpecialEquipment;
    }
    public int[] getMechSkills() {
        return mechSkills;
    }
    public void setMechSkills(int[] newMechSkills) {
        // TODO: update to accept null
        // accept a value of null or a valid array, but no in betweens
        mechSkills = newMechSkills;
    }
    public String[] getCoreBonuses() {
        return coreBonuses;
    }
    public void setCoreBonuses(String[] newCoreBonuses) {
        // TODO: update to accept null
        // accept a value of null or a valid array, but no in betweens
        coreBonuses = newCoreBonuses;
    }
    public Talent[] getTalents() {
        return talents;
    }
    public void setTalents(Talent[] newTalents) {
        // TODO: update to accept null
        // accept a value of null or a valid array, but no in betweens
        talents = newTalents;
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

        if (outputType == "mech build") {
            outputString += "[ LICENSES ]\n";
            outputString += "  " + outputLicenses(outputType) + "\n";
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ STATS ]\n";
            outputString += "  HULL:" + mechSkills[0] + " AGI:" + mechSkills[1] + " SYS:" + mechSkills[2] + " ENGI:" + mechSkills[3] + "\n";
        } else if (outputType == "pilot") {
            outputString += "» " + name + " // " + callsign.toUpperCase() + " «\n";
            outputString += "  LL" + licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += "  [ MECH SKILLS ]\n";
            outputString += "  GRIT:" + grit + " // H:" + mechSkills[0] + " A:" + mechSkills[1] + " S:" + mechSkills[2] + " E:" + mechSkills[3] + "\n";
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
        } else if (outputType == "full") {
            outputString += "» " + name + " // " + callsign.toUpperCase() + " «\n";
            outputString += "  LL" + licenseLevel + "\n";
            outputString += "[ SKILL TRIGGERS ]\n";
            outputString += skillTriggers.generateOutput();
            outputString += "[ GEAR ]\n";
            outputString += "  ***\n";
            outputString += "[ TALENTS ]\n";
            outputString += outputTalents(outputType);
            outputString += "[ LICENSES ]\n";
            outputString += outputLicenses(outputType);
            outputString += "[ CORE BONUSES ]\n";
            outputString += outputCoreBonuses(outputType);
            outputString += "\n";
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

        if (licenseList == null) {
            outputString += "N/A";
            return outputString;
        }
        if (outputType == "mech build") {
            for (int i = 0; i < licenseList.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += licenseList[i].frame + " " + licenseList[i].licenseLevel;
            }
        } else if (outputType == "pilot" || outputType == "full") {
            outputString += "  ";
            for (int i = 0; i < licenseList.length; i ++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += licenseList[i].frame + " " + licenseList[i].licenseLevel;
            }
            outputString += "\n";
        }

        return outputString;
    }

    /**
     * Helper function for Pilot.generateOutput() that outputs only
     *     the talents portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the talents portion of the printout.
     */
    public String outputTalents(String outputType) {
        String outputString = "";

        if (talents == null) {
            outputString += "  \n";
            return outputString;
        }
        if (outputType == "mech build") {
            for (int i = 0; i < talents.length; i++) {
                if (i == 0) {
                    outputString += "  ";
                }
                outputString += talents[i].name + " " + talents[i].level;
                if (i + 1 < talents.length) {
                    outputString += ", ";
                }
                if (i + 1 == talents.length) {
                    outputString += "\n";
                }
            }
        } else if (outputType == "pilot") {
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
        } else if (outputType == "full") {
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
     * Helper function for Pilot.generateOutput() that outputs only
     *     the core bonuses portion of the printout.
     * @param outputType a String which can be one of the following:
     *     "mech build", "pilot", or "full", and determines how much information
     *     is printed.
     * @return a String containing the core bonuses portion of the printout.
     */
    public String outputCoreBonuses(String outputType) {
        String outputString = "";

        if (coreBonuses == null) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (outputType == "mech build") {
            outputString += "  ";
            for (int i = 0; i < coreBonuses.length; i++) {
                if (i != 0) {
                    outputString += ", ";
                }
                outputString += coreBonuses[i];
            }
            outputString += "\n";
        } else if (outputType == "pilot") {
            for (int i = 0; i < coreBonuses.length; i += 2) {
                outputString += "  ";
                for (int ii = i; ii < Math.min(i + 2, coreBonuses.length); ii++) {
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
                if (i + 2 < coreBonuses.length) {
                    outputString += "\n";
                }
            }
        } else if (outputType == "full") {
            for (int i = 0; i < coreBonuses.length; i += 2) {
                outputString += "  ";
                for (int ii = i; ii < Math.min(i + 2, coreBonuses.length); ii++) {
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
