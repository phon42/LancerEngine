package packages.CoreTypes.EntityMechanics;

import java.util.HashMap;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.EntityTypes.Mech;
import packages.CoreTypes.EntityMechanics.EntityTypes.Pilot;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.Frame;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.Mount;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.MechSystem;
import packages.CoreTypes.EntityMechanics.EntityTypes.pilot.Loadout;
import packages.CoreTypes.EntityMechanics.EntityTypes.pilot.SkillTriggersList;
import packages.CoreTypes.EntityMechanics.EntityTypes.pilot.Talent;
import packages.CoreTypes.EntityMechanics.harmSystem.Harm;
import packages.CoreTypes.EntityMechanics.stateSystem.State;

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
    // setPilot() is private because since a LancerCharacter is based on its
    //     Pilot, not its Mech, it's a decent idea to force the user to create
    //     an entirely new LancerCharacter if they want to change
    //     LancerCharacter.pilot to the degree that using setPilot() is
    //     necessary over an indirect method like
    //     LancerCharacter.setPilotProperties()
    private void setPilot(Pilot pilot) {
        HelperMethods.checkObject("New pilot value", pilot);
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
            mech.calculateAttributes(this.pilot.getGrit(),
                this.pilot.getMechSkills(), this.pilot.getCoreBonuses(),
                this.pilot.getTalents());
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
     *     - Appearance
     *     - Player notes
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
        pilotProperties.put("appearance", this.pilot.getAppearance());
        pilotProperties.put("playerNotes", this.pilot.getPlayerNotes());

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
     *     - Current heat
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
        mechProperties.put("currentHeat", this.mech.getCurrentHeat());
        mechProperties.put("maxHeatCapacity",
            this.mech.getMaxHeatCapacity());
        mechProperties.put("evasion", this.mech.getEvasion());
        mechProperties.put("speed", this.mech.getSpeed());
        mechProperties.put("eDefense", this.mech.getEDefense());
        mechProperties.put("techAttack", this.mech.getTechAttack());
        mechProperties.put("sensors", this.mech.getSensors());
        mechProperties.put("currentRepairs",
            this.mech.getCurrentRepairs());
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
     *     - Appearance
     *     - Player notes
     * - Stats
     *     - [Current HP]
     * - [Skill triggers]
     * - [Reserves]
     * - [Loadout]
     * - [License level]
     * - [License list]
     * - [Special equipment]
     * - [Mech skills]
     * - [Core bonuses]
     * - [Talents]
     */
    public void setPilotProperties(HashMap<String, Object> pilotProperties) {
        String[] propertyNames = new String[] {"name", "callsign", "player",
            "status", "background", "biography", "appearance", "playerNotes",
            "size", "grit", "currentHP", "maxHP", "armor", "evasion", "speed",
            "eDefense", "skillTriggers", "reserves", "loadout", "licenseLevel",
            "licenseList", "specialEquipment", "mechSkills", "coreBonuses",
            "talents"};
        boolean isAllowed = false;

        HelperMethods.checkObject("pilotProperties",
            pilotProperties);
        for (String propertyName : propertyNames) {
            if (! pilotProperties.containsKey(propertyName)) {
                throw new IllegalArgumentException("pilotProperties did not"
                    + " contain property: \"" + propertyName + "\"");
            }
        }
        for (String propertyName : pilotProperties.keySet()) {
            isAllowed = false;
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyName.equals(propertyNames[i])) {
                    isAllowed = true;
                }
            }
            if (! isAllowed) {
                throw new IllegalArgumentException("pilotProperties contained"
                    + " an illegal property: \"" + propertyName + "\"");
            }
        }
        this.pilot.setName((String) pilotProperties.get("name"));
        this.pilot.setCallsign((String) pilotProperties.get("callsign"));
        this.pilot.setPlayer((String) pilotProperties.get("player"));
        this.pilot.setStatus((String) pilotProperties.get("status"));
        this.pilot.setBackground((String) pilotProperties.get(
            "background"));
        this.pilot.setBiography((String) pilotProperties.get("biography"));
        this.pilot.setAppearance((String) pilotProperties.get(
            "appearance"));
        this.pilot.setPlayerNotes((String) pilotProperties.get(
            "playerNotes"));
        this.pilot.setCurrentHP((int) pilotProperties.get("currentHP"));
        this.pilot.setSkillTriggers((SkillTriggersList) pilotProperties.get(
            "skillTriggers"));
        this.pilot.setReserves((String[]) pilotProperties.get("reserves"));
        this.pilot.setLoadout((Loadout) pilotProperties.get("loadout"));
        this.pilot.setLicenseLevel((int) pilotProperties.get(
            "licenseLevel"));
        this.pilot.setLicenseList((License[]) pilotProperties.get(
            "licenseList"));
        this.pilot.setSpecialEquipment((String[]) pilotProperties.get(
            "specialEquipment"));
        this.pilot.setMechSkills((int[]) pilotProperties.get("mechSkills"));
        this.pilot.setCoreBonuses((String[]) pilotProperties.get(
            "coreBonuses"));
        this.pilot.setTalents((Talent[]) pilotProperties.get("talents"));
    }
    // TODO: implement all these bracketed monitoring methods
    /*
     * Mech - set: [Bracketing requires special monitoring]
     * - Identification fields
     *     - Name
     *     - [Frame]
     * - Fluff/flavor
     *     - Operator notes
     * - Stats
     *     - [Current structure]
     *     - [Current HP]
     *     - [Current stress]
     *     - [Current heat]
     *     - Current repair capacity
     * - [Mounts]
     * - [Systems]
     */
    public void setMechProperties(HashMap<String, Object> mechProperties) {
        String[] propertyNames = new String[] {
            "name", "frame", "manufacturer", "frameName", "role",
            "frameDescription", "operatorNotes", "size", "currentStructure",
            "maxStructure", "currentHP", "maxHP", "armor", "currentStress",
            "maxStress", "currentHeat", "maxHeatCapacity", "evasion", "speed",
            "eDefense", "techAttack", "sensors", "currentRepairs",
            "maxRepairCapacity", "saveTarget", "systemPoints",
            "limitedSystemsBonus", "traits", "mounts", "systems"
        };
        boolean isAllowed = false;
        Mount[] mounts;

        HelperMethods.checkObject("mechProperties",
            mechProperties);
        for (String propertyName : propertyNames) {
            if (! mechProperties.containsKey(propertyName)) {
                throw new IllegalArgumentException("mechProperties did not"
                    + " contain property: \"" + propertyName + "\"");
            }
        }
        for (String propertyName : mechProperties.keySet()) {
            isAllowed = false;
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyName.equals(propertyNames[i])) {
                    isAllowed = true;
                }
            }
            if (! isAllowed) {
                throw new IllegalArgumentException("mechProperties contained an"
                    + " illegal property: \"" + propertyName + "\"");
            }
        }
        this.mech.setName((String) mechProperties.get("name"));
        this.mech.setFrame((Frame) mechProperties.get("frame"));
        this.mech.setOperatorNotes((String) mechProperties.get(
            "operatorNotes"));
        this.mech.setCurrentStructure((int) mechProperties.get(
            "currentStructure"));
        this.mech.setCurrentHP((int) mechProperties.get("currentHP"));
        this.mech.setCurrentStress((int) mechProperties.get(
            "currentStress"));
        this.mech.setCurrentHeat((int) mechProperties.get("currentHeat"));
        this.mech.setCurrentRepairs((int) mechProperties.get(
            "currentRepairs"));
        mounts = (Mount[]) mechProperties.get("mounts");
        for (int i = 0; i < mounts.length; i++) {
            this.mech.setMount(i, mounts[i]);
        }
        this.mech.setSystems((MechSystem[]) mechProperties.get("systems"));
    }
    /**
     * Calls addStatus(State, boolean) on either this.pilot or this.mech,
     *     depending on targetMech.
     * @param targetMech a boolean representing whether to call
     *     addStatus(State, boolean) on this.mech.
     */
    public void addStatus(boolean targetMech, State newStatus,
        boolean addDuplicate) {
        if (targetMech) {
            this.mech.addStatus(newStatus, addDuplicate);
        } else {
            this.pilot.addStatus(newStatus, addDuplicate);
        }
    }
    /**
     * Calls addStatus(State) on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call addStatus(State)
     *     on this.mech.
     */
    public void addStatus(boolean targetMech, State status) {
        if (targetMech) {
            this.mech.addStatus(status);
        } else {
            this.pilot.addStatus(status);
        }
    }
    /**
     * Calls removeStatus(State, boolean) on either this.pilot or this.mech,
     *     depending on targetMech.
     * @param targetMech a boolean representing whether to call
     *     removeStatus(State, boolean) on this.mech.
     */
    public void removeStatus(boolean targetMech, State oldStatus,
        boolean removeAll) {
        if (targetMech) {
            this.mech.removeStatus(oldStatus, removeAll);
        } else {
            this.pilot.removeStatus(oldStatus, removeAll);
        }
    }
    /**
     * Calls removeStatus(State) on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call
     *     removeStatus(State) on this.mech.
     */
    public void removeStatus(boolean targetMech, State oldStatus) {
        if (targetMech) {
            this.mech.removeStatus(oldStatus);
        } else {
            this.pilot.removeStatus(oldStatus);
        }
    }
    /**
     * Calls hasState() on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call hasState() on
     *     this.mech.
     */
    public boolean hasState(boolean targetMech, String statusType) {
        if (targetMech) {
            return this.mech.hasState(statusType);
        } else {
            return this.pilot.hasState(statusType);
        }
    }
    /**
     * Calls addCondition(State, boolean) on either this.pilot or this.mech,
     *     depending on targetMech.
     * @param targetMech a boolean representing whether to call
     *     addCondition(State, boolean) on this.mech.
     */
    public void addCondition(boolean targetMech, State newCondition,
        boolean addDuplicate) {
        if (targetMech) {
            this.mech.addCondition(newCondition, addDuplicate);
        } else {
            this.pilot.addCondition(newCondition, addDuplicate);
        }
    }
    /**
     * Calls addCondition(State) on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call
     *     addCondition(State) on this.mech.
     */
    public void addCondition(boolean targetMech, State condition) {
        if (targetMech) {
            this.mech.addCondition(condition);
        } else {
            this.pilot.addCondition(condition);
        }
    }
    /**
     * Calls removeCondition(State, boolean) on either this.pilot or this.mech,
     *     depending on targetMech.
     * @param targetMech a boolean representing whether to call
     *     removeCondition(State, boolean) on this.mech.
     */
    public void removeCondition(boolean targetMech, State oldCondition,
        boolean removeAll) {
        if (targetMech) {
            this.mech.removeCondition(oldCondition, removeAll);
        } else {
            this.pilot.removeCondition(oldCondition, removeAll);
        }
    }
    /**
     * Calls removeCondition(State) on either this.pilot or this.mech, depending
     *     on targetMech.
     * @param targetMech a boolean representing whether to call
     *     removeCondition(State) on this.mech.
     */
    public void removeCondition(boolean targetMech, State oldCondition) {
        if (targetMech) {
            this.mech.removeCondition(oldCondition);
        } else {
            this.pilot.removeCondition(oldCondition);
        }
    }
    /**
     * Calls receiveHarm() on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call receiveHarm()
     *     on this.mech.
     * @param harm a Harm that cannot be null.
     */
    public void receiveHarm(boolean targetMech, Harm harm) {
        HelperMethods.checkObject("harm", harm);
        if (targetMech) {
            this.mech.receiveHarm(harm);
        } else {
            this.pilot.receiveHarm(harm);
        }
    }
    /**
     * Calls destroy() on either this.pilot or this.mech, depending on
     *     targetMech.
     * @param targetMech a boolean representing whether to call destroy() on
     *     this.mech.
     */
    public void destroy(boolean targetMech) {
        if (targetMech) {
            this.mech.destroy();
        } else {
            this.pilot.destroy();
        }
    }
    /**
     * Calls this.mech.receiveStructureDamage(int).
     */
    public void mechReceiveStructureDamage(int structureDamage) {
        this.mech.receiveStructureDamage(structureDamage);
    }
    /**
     * Calls this.mech.receiveStressDamage(int).
     */
    public void mechReceiveStressDamage(int stressDamage) {
        this.mech.receiveStressDamage(stressDamage);
    }
    /**
     * Calls this.pilot.becomeCritical().
     */
    public void pilotBecomeCritical() {
        this.pilot.becomeCritical();
    }
    /**
     * Calls this.pilot.down().
     */
    public void pilotDown() {
        this.pilot.down();
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
            manufacturer = this.mech.getFrame().getSource().getID();
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
            // TODO: see if we can split some of these things out of the method
            outputString += this.mech.generateOutput(outputType,
                this.pilot.getMechSkills(), this.pilot.getGrit());
        }

        return outputString;
    }
}