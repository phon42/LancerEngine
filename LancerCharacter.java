// TODO: add some kind of way for Pilot and Mech's actions to bubble up
import java.util.HashMap;

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
    public HashMap<String, Object> getPilotProperties() {
        HashMap<String, Object> pilotProperties = new HashMap<>();

        pilotProperties.put("name", this.pilot.getName());
        pilotProperties.put("callsign", this.pilot.getCallsign());
        pilotProperties.put("player", this.pilot.getPlayer());

        pilotProperties.put("status", this.pilot.getStatus());
        pilotProperties.put("background", this.pilot.getBackground());
        pilotProperties.put("biography", this.pilot.getBiography());
        pilotProperties.put("playerNotes", this.pilot.getPlayerNotes());
        pilotProperties.put("appearance", this.pilot.getAppearance());

        pilotProperties.put("size", this.pilot.getSize());
        pilotProperties.put("grit", this.pilot.getGrit());
        pilotProperties.put("currentHP", this.pilot.getCurrentHP());
        pilotProperties.put("maxHP", this.pilot.getMaxHP());
        pilotProperties.put("armor", this.pilot.getArmor());
        pilotProperties.put("evasion", this.pilot.getEvasion());
        pilotProperties.put("speed", this.pilot.getSpeed());
        pilotProperties.put("eDefense", this.pilot.getEDefense());

        pilotProperties.put("skillTriggers", this.pilot.getSkillTriggers());
        pilotProperties.put("reserves", this.pilot.getReserves());
        pilotProperties.put("loadout", this.pilot.getLoadout());
        pilotProperties.put("licenseLevel", this.pilot.getLicenseLevel());
        pilotProperties.put("licenseList", this.pilot.getLicenseList());
        pilotProperties.put("specialEquipment",
            this.pilot.getSpecialEquipment());
        pilotProperties.put("mechSkills", this.pilot.getMechSkills());
        pilotProperties.put("coreBonuses", this.pilot.getCoreBonuses());
        pilotProperties.put("talents", this.pilot.getTalents());

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
    public HashMap<String, Object> getMechProperties() {
        HashMap<String, Object> mechProperties = new HashMap<>();

        mechProperties.put("name", this.mech.getName());
        mechProperties.put("frame", this.mech.getFrame());

        mechProperties.put("manufacturer", this.mech.getManufacturer());
        mechProperties.put("frameName", this.mech.getFrameName());
        mechProperties.put("role", this.mech.getRole());
        mechProperties.put("frameDescription",
            this.mech.getFrameDescription());

        mechProperties.put("operatorNotes", this.mech.getOperatorNotes());

        mechProperties.put("size", this.mech.getSize());
        mechProperties.put("currentStructure",
            this.mech.getCurrentStructure());
        mechProperties.put("maxStructure", this.mech.getMaxStructure());
        mechProperties.put("currentHP", this.mech.getCurrentHP());
        mechProperties.put("maxHP", this.mech.getMaxHP());
        mechProperties.put("armor", this.mech.getArmor());
        mechProperties.put("currentStress", this.mech.getCurrentStress());
        mechProperties.put("maxStress", this.mech.getMaxStress());
        mechProperties.put("currentHeatCapacity",
            this.mech.getCurrentHeatCapacity());
        mechProperties.put("maxHeatCapacity",
            this.mech.getMaxHeatCapacity());
        mechProperties.put("evasion", this.mech.getEvasion());
        mechProperties.put("speed", this.mech.getSpeed());
        mechProperties.put("eDefense", this.mech.getEDefense());
        mechProperties.put("techAttack", this.mech.getTechAttack());
        mechProperties.put("sensors", this.mech.getSensors());
        mechProperties.put("currentRepairCapacity",
            this.mech.getCurrentRepairCapacity());
        mechProperties.put("maxRepairCapacity",
            this.mech.getMaxRepairCapacity());
        mechProperties.put("saveTarget", this.mech.getSaveTarget());
        mechProperties.put("systemPoints", this.mech.getSystemPoints());
        mechProperties.put("limitedSystemsBonus",
            this.mech.getLimitedSystemsBonus());

        mechProperties.put("traits", this.mech.getTraits());
        mechProperties.put("mounts", this.mech.getMounts());
        mechProperties.put("systems", this.mech.getSystems());

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
    public void setMechProperties(HashMap<String, Object> newMechProperties) {
        String[] propertyNames = new String[] {
            "name", "frame", "manufacturer", "frameName", "role",
            "frameDescription", "operatorNotes", "size", "currentStructure",
            "maxStructure", "currentHP", "maxHP", "armor", "currentStress",
            "maxStress", "currentHeatCapacity", "maxHeatCapacity", "evasion",
            "speed", "eDefense", "techAttack", "sensors",
            "currentRepairCapacity", "maxRepairCapacity", "saveTarget",
            "systemPoints", "limitedSystemsBonus", "traits", "mounts", "systems"
        };

        if (newMechProperties == null) {
            throw new IllegalArgumentException("newMechProperties is null");
        }
        for (String propertyName : propertyNames) {
            if (! newMechProperties.containsKey(propertyName)) {
                throw new IllegalArgumentException("newMechProperties did not"
                    + " contain property: \"" + propertyName + "\"");
            }
        }
        this.mech.setName((String) newMechProperties.get("name"));
        this.mech.setFrame((Frame) newMechProperties.get("frame"));
        this.mech.setOperatorNotes((String) newMechProperties.get(
            "operatorNotes"));
        this.mech.setCurrentStructure((int) newMechProperties.get(
            "currentStructure"));
        this.mech.setCurrentHP((int) newMechProperties.get("currentHP"));
        this.mech.setCurrentStress((int) newMechProperties.get(
            "currentStress"));
        this.mech.setCurrentHeatCapacity((int) newMechProperties.get(
            "currentHeatCapacity"));
        this.mech.setCurrentRepairCapacity((int) newMechProperties.get(
            "currentRepairCapacity"));
        for (int i = 0; i < ((Mount[]) newMechProperties.get(
            "mounts")).length; i++) {
            this.mech.setMount(i, ((Mount[]) newMechProperties.get(
                "mounts"))[i]);
        }
        this.mech.setSystems((MechSystem[]) newMechProperties.get(
            "systems"));
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