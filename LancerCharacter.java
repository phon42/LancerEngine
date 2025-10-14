// TODO: add some kind of way for Pilot and Mech's actions to bubble up
/**
 * Represents a single Lancer character, including the pilot and their mech.
 *     Contains a Pilot object and a Mech object.
 * 
 * Requires at minimum a pilot name and a pilot callsign or a Pilot object with
 *     those values.
 * 
 * Used in Main.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public final class LancerCharacter {
    /**
     * The pilot associated with this character.
     * Can be any Pilot that does not have placeholders in the following fields:
     *     - Pilot Name
     *     - Pilot Callsign
     */
    private Pilot pilot;
    /**
     * The mech associated with this character (if there is one).
     * Can be any Mech. Can be null.
     */
    private Mech mech;
    
    /**
     * Creates a new LancerCharacter from a pilotName and a pilotCallsign. Sets
     *     this.mech to null.
     */
    public LancerCharacter(String pilotName, String pilotCallsign) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(null);
    }
    /**
     * Creates a new LancerCharacter from a pilotName, a pilotCallsign, and a
     *     mech.
     */
    public LancerCharacter(String pilotName, String pilotCallsign, Mech mech) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(mech);
    }
    
    public Pilot getPilot() {
        return new Pilot(pilot);
    }
    public Mech getMech() {
        if (mech == null) {
            return mech;
        }
        return new Mech(mech);
    }
    /**
     * Sets this.pilot to the value provided.
     * @param pilot a Pilot that cannot be null or have placeholders.
     * @throws IllegalArgumentException if pilot is null.
     */
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new IllegalArgumentException("New pilot value is null");
        }
        pilot = new Pilot(pilot);
        this.pilot = pilot;
    }
    /**
     * Sets this.mech to the value provided. Calls Mech.calculateAttributes()
     *     before setting this.mech to it.
     * @param mech a Mech.
     */
    public void setMech(Mech mech) {
        if (mech != null) {
            mech.calculateAttributes(this.pilot.getMechSkills(),
                this.pilot.getCoreBonuses(), this.pilot.getTalents());
            mech = new Mech(mech);
        }
        this.mech = mech;
    }

    /*
     * Pilot - get:
     * - Identification fields
     *     - Name
     *     - Callsign
     *     - Player
     * - Fluff/flavor
     *     - Status
     *     - Background
     *     - Biography
     *     - Player notes
     *     - Appearance
     * - Stats
     *     - Grit
     *     - Current HP
     *     - Max HP
     *     - Armor
     *     - Evasion
     *     - Speed
     *     - E-defense
     * - Skill triggers
     * - Reserves
     * - Loadout
     * - License level (possibly goes up there with stats?)
     * - License list
     * - Special equipment (maybe goes with reserves)
     * - Mech skills
     * - Core bonuses
     * - Talents
     */
    public Object[] getPilotProperties() {
        Object[] pilotProperties = new Object[24];

        pilotProperties[0] = this.pilot.getName();
        pilotProperties[1] = this.pilot.getCallsign();
        pilotProperties[2] = this.pilot.getPlayer();

        pilotProperties[3] = this.pilot.getStatus();
        pilotProperties[4] = this.pilot.getBackground();
        pilotProperties[5] = this.pilot.getBiography();
        pilotProperties[6] = this.pilot.getPlayerNotes();
        pilotProperties[7] = this.pilot.getAppearance();

        pilotProperties[8] = this.pilot.getGrit();
        pilotProperties[9] = this.pilot.getCurrentHP();
        pilotProperties[10] = this.pilot.getMaxHP();
        pilotProperties[11] = this.pilot.getArmor();
        pilotProperties[12] = this.pilot.getEvasion();
        pilotProperties[13] = this.pilot.getSpeed();
        pilotProperties[14] = this.pilot.getEDefense();

        pilotProperties[15] = this.pilot.getSkillTriggers();
        pilotProperties[16] = this.pilot.getReserves();
        pilotProperties[17] = this.pilot.getLoadout();
        pilotProperties[18] = this.pilot.getLicenseLevel();
        pilotProperties[19] = this.pilot.getLicenseList();
        pilotProperties[20] = this.pilot.getSpecialEquipment();
        pilotProperties[21] = this.pilot.getMechSkills();
        pilotProperties[22] = this.pilot.getCoreBonuses();
        pilotProperties[23] = this.pilot.getTalents();

        return pilotProperties;
    }
    /*
     * Mech - get:
     * - Identification fields
     *     - Name
     *     - Frame
     * - Frame reference fields
     *     - Manufacturer
     *     - Frame name
     *     - Role(s)
     *     - Frame description
     * - Fluff/flavor
     *     - Operator notes
     * - Stats
     *     - Size
     *     - Current structure
     *     - Max structure
     *     - Current HP
     *     - Max HP
     *     - Armor
     *     - Current stress
     *     - Max stress
     *     - Current heat capacity
     *     - Max heat capacity
     *     - Evasion
     *     - Speed
     *     - E-defense
     *     - Tech attack
     *     - Sensors
     *     - Current repair capacity
     *     - Max repair capacity
     *     - Save target
     *     - System points
     *     - Limited systems bonus
     * - Traits (it's technically a frame reference field but idc)
     * - Mounts
     * - Systems
     */
    public Object[] getMechProperties() {
        Object[] mechProperties = new Object[30];

        mechProperties[0] = this.mech.getName();
        mechProperties[1] = this.mech.getFrame();

        mechProperties[2] = this.mech.getManufacturer();
        mechProperties[3] = this.mech.getFrameName();
        mechProperties[4] = this.mech.getRole();
        mechProperties[5] = this.mech.getFrameDescription();

        mechProperties[6] = this.mech.getOperatorNotes();

        mechProperties[7] = this.mech.getSize();
        mechProperties[8] = this.mech.getCurrentStructure();
        mechProperties[9] = this.mech.getMaxStructure();
        mechProperties[10] = this.mech.getCurrentHP();
        mechProperties[11] = this.mech.getMaxHP();
        mechProperties[12] = this.mech.getArmor();
        mechProperties[13] = this.mech.getCurrentStress();
        mechProperties[14] = this.mech.getMaxStress();
        mechProperties[15] = this.mech.getCurrentHeatCapacity();
        mechProperties[16] = this.mech.getMaxHeatCapacity();
        mechProperties[17] = this.mech.getEvasion();
        mechProperties[18] = this.mech.getSpeed();
        mechProperties[19] = this.mech.getEDefense();
        mechProperties[20] = this.mech.getTechAttack();
        mechProperties[21] = this.mech.getSensors();
        mechProperties[22] = this.mech.getCurrentRepairCapacity();
        mechProperties[23] = this.mech.getMaxRepairCapacity();
        mechProperties[24] = this.mech.getSaveTarget();
        mechProperties[25] = this.mech.getSystemPoints();
        mechProperties[26] = this.mech.getLimitedSystemsBonus();

        mechProperties[27] = this.mech.getTraits();
        mechProperties[28] = this.mech.getMounts();
        mechProperties[29] = this.mech.getSystems();

        return mechProperties;
    }
    /*
     * Pilot - set: [Bracketing requires special monitoring]
     * - Identification fields
     *     - Name
     *     - Callsign
     *     - Player
     * - Fluff/flavor
     *     - Status
     *     - Background
     *     - Biography
     *     - Player notes
     *     - Appearance
     * - Stats
     *     - [Current HP]
     * - [Skill triggers]
     * - [Reserves]
     * - [Loadout]
     * - License level (possibly goes up there with stats?)
     * - [License list]
     * - [Special equipment (maybe goes with reserves)]
     * - [Mech skills]
     * - [Core bonuses]
     * - [Talents]
     */
    /*
     * Mech - set: [Bracketing requires special monitoring]
     * - Identification fields
     *     - Name
     *     - Frame
     * - Fluff/flavor
     *     - Operator notes
     * - Stats
     *     - [Current structure]
     *     - Current HP
     *     - [Current stress]
     *     - Current heat capacity
     *     - Current repair capacity
     * - Mounts
     * - Systems
     */
    public void setMechProperties(Object[] newMechProperties) {
        int length = newMechProperties.length;

        if (length > 0) {
            this.mech.setName((String) newMechProperties[0]);
        }
        if (length > 1) {
            this.mech.setFrame((Frame) newMechProperties[1]);
        }
        if (length > 2) {
            this.mech.setOperatorNotes((String) newMechProperties[2]);
        }
        if (length > 3) {
            this.mech.setCurrentStructure((int) newMechProperties[3]);
        }
        if (length > 4) {
            this.mech.setCurrentHP((int) newMechProperties[4]);
        }
        if (length > 5) {
            this.mech.setCurrentStress((int) newMechProperties[5]);
        }
        if (length > 6) {
            this.mech.setCurrentHeatCapacity((int) newMechProperties[6]);
        }
        if (length > 7) {
            this.mech.setCurrentRepairCapacity((int) newMechProperties[7]);
        }
        if (length > 8) {
            for (int i = 0; i < ((Mount[]) newMechProperties[8]).length; i++) {
                this.mech.setMount(i, ((Mount[]) newMechProperties[8])[i]);
            }
        }
        if (length > 9) {
            this.mech.setSystems((MechSystem[]) newMechProperties[9]);
        }
    }
    /**
     * Performs the "Generate Statblock" function from COMP/CON, printing out
     *     details of a pilot and their mech depending on what output is
     *     demanded
     * @param outputType a String controlling which details to include in the
     *     printout
     * @return a String of the mech/pilot's statblock
     */
    public String generateStatblock(String outputType) {
        String outputString = "";
        
        boolean hasMech = true;
        if (this.mech == null) {
            hasMech = false;
        }
        
        String manufacturer = "";
        String frameName = "";
        int licenseLevel = this.pilot.getLicenseLevel();
        if (hasMech) {
            manufacturer = this.mech.getFrame().getManufacturer();
            frameName = this.mech.getFrame().outputName();
        }

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            outputString += String.format(
                "-- %s %s @ LL%d --\n",
                manufacturer, frameName, licenseLevel
            );
            outputString += this.pilot.generateOutput(outputType);
            outputString += this.mech.generateOutput(outputType,
                this.mech.getLimitedSystemsBonus());
        } else if (outputType.equals("pilot")) {
            outputString += this.pilot.generateOutput(outputType);
        } else if (outputType.equals("full")) {
            outputString += this.pilot.generateOutput(outputType);
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            outputString += this.mech.generateOutput(outputType,
                this.pilot.getMechSkills(), this.pilot.getGrit());
        }

        return outputString;
    }
}