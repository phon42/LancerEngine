package main;

import main.database.FrameEnum;
import main.roll.DiceExpression;
import packages.coreTypes.Environment;
import packages.coreTypes.Rule;
import packages.coreTypes.Sitrep;
import packages.coreTypes.Table;
import packages.coreTypes.Term;
import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.License;
import packages.coreTypes.entityMechanics.Manufacturer;
import packages.coreTypes.entityMechanics.RangeTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.coreTypes.entityMechanics.entityTypes.mech.Mount;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Modification;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Weapon;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Background;
import packages.coreTypes.entityMechanics.entityTypes.pilot.CoreBonus;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Reserve;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotArmor;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotGear;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;
import packages.coreTypes.entityMechanics.harmSystem.Harm;
import packages.coreTypes.entityMechanics.licenseSystem.FrameLicense;
import packages.coreTypes.entityMechanics.stateSystem.State;
import packages.coreTypes.entityMechanics.stateSystem.state.Condition;
import packages.coreTypes.entityMechanics.stateSystem.state.Status;

// TODO: let's go full crazy, add the ability to read a set of .lcp files and
//     unzip those into as much Lancer data as we want:
//         https://www.w3schools.com/java/java_files_read.asp
//         https://www.baeldung.com/java-compress-and-uncompress#unzip
/**
 * Represents nothing. Contains a set array of Frames to be used by
 *     Mech(String, String) and Mech(String, FrameEnum).
 * 
 * Cannot be instantiated. All its methods are static.
 * 
 * Used to create a Mech, or when information about a frame needs to be
 *     referenced.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public final class Database {
    /**
     * Represents whether the database is currently being edited.
     */
    private static boolean open = false;
    /**
     * add documentation
     */
    private static Action[] actions;
    /**
     * add documentation
     */
    private static Background[] backgrounds;
    /**
     * add documentation
     */
    private static Condition[] conditions;
    /**
     * add documentation
     */
    private static CoreBonus[] coreBonuses;
    /**
     * add documentation
     */
    private static DataTag[] dataTags;
    /**
     * add documentation
     */
    private static Environment[] environments;
    /**
     * Contains every frame's data for reference.
     */
    private static Frame[] frames;
    /**
     * Contains every manufacturer for reference.
     */
    private static Manufacturer[] manufacturers;
    /**
     * add documentation
     */
    private static Modification[] modifications;
    /**
     * add documentation
     */
    private static PilotArmor[] pilotArmor;
    /**
     * add documentation
     */
    private static PilotGear[] pilotGear;
    /**
     * add documentation
     */
    private static PilotWeapon[] pilotWeapons;
    /**
     * add documentation
     */
    private static Reserve[] reserves;
    /**
     * add documentation
     */
    private static Rule[] rules;
    /**
     * add documentation
     */
    private static Sitrep[] sitreps;
    /**
     * add documentation
     */
    private static Skill[] skills;
    /**
     * add documentation
     */
    private static State[] states;
    /**
     * add documentation
     */
    private static Status[] statuses;
    /**
     * Contains every mech system for reference.
     */
    private static MechSystem[] systems;
    /**
     * add documentation
     */
    private static Table[] tables;
    /**
     * add documentation
     */
    private static Tag[] tags;
    /**
     * add documentation
     */
    private static Talent[] talents;
    /**
     * add documentation
     */
    private static Term[] terms;
    /**
     * Contains every mech weapon for reference.
     */
    private static Weapon[] weapons;
    /**
     * Contains a list of every content license for reference.
     */
    private static FrameLicense[] frameLicenses;

    // TODO: add the Size 4 frame to Database.frames
    // private static Frame[] frameList = new Frame[] {
    //     new Frame(null,
    //         "GMS", "Size 4", "size_4",
    //         FrameEnum.SIZE_4, new String[] {"Balanced"}, "",
    //         1, 1, 1, 0, 1,
    //         1, 0, 0, 0,
    //         0, 0, 0,
    //         0, 0, new String[0], new Mount[0]
    //     )
    // };

    static {
        Database.actions = new Action[0];
        Database.backgrounds = new Background[0];
        Database.conditions = new Condition[0];
        Database.coreBonuses = new CoreBonus[0];
        Database.dataTags = new DataTag[0];
        Database.environments = new Environment[0];
        Database.frames = new Frame[0];
        Database.manufacturers = new Manufacturer[0];
        Database.modifications = new Modification[0];
        Database.pilotArmor = new PilotArmor[0];
        Database.pilotGear = new PilotGear[0];
        Database.pilotWeapons = new PilotWeapon[0];
        Database.reserves = new Reserve[0];
        Database.rules = new Rule[0];
        Database.sitreps = new Sitrep[0];
        Database.skills = new Skill[0];
        Database.states = new State[0];
        Database.statuses = new Status[0];
        Database.systems = new MechSystem[0];
        Database.tables = new Table[0];
        Database.tags = new Tag[0];
        Database.talents = new Talent[0];
        Database.terms = new Term[0];
        Database.weapons = new Weapon[0];
        Database.frameLicenses = new FrameLicense[0];
    }

    // Prevent user from instantiating this class
    private Database() {}

    public static boolean isOpen() {
        return Database.open;
    }
    public static void open() {
        if (Database.open) {
            // means the user just opened Database twice by accident lol
            // throw an exception if you REALLY hate your user, otherwise just
            //     call close()
            throw new IllegalStateException("Attempted to call"
                + " Database.open() when Database is already open");
        }
        Database.open = true;
    }
    public static void close() {
        if (! Database.open) {
            // user called close() when Database was already closed
            // throw an exception if you hate your user
            throw new IllegalArgumentException("Attempted to call"
                + " Database.close() when Database is already closed");
        }
        Database.open = false;
    }
    public static Action getAction(String actionName) {
        if (actionName == null) {
            throw new IllegalArgumentException("actionName is null");
        }
        for (Action action : Database.actions) {
            if (actionName.equals(action.getName())) {
                return new Action(action);
            }
        }
        throw new IllegalArgumentException("No action found for action name: "
            + actionName);
    }
    public static Background getBackground(String backgroundID) {
        if (backgroundID == null) {
            throw new IllegalArgumentException("backgroundID is null");
        }
        for (Background background : Database.backgrounds) {
            if (backgroundID.equals(background.getID())) {
                return new Background(background);
            }
        }
        throw new IllegalArgumentException("No background found for background"
            + " ID: " + backgroundID);
    }
        // name being "" is dealt with by HelperMethods.append()
        // manufacturer being "" or null is dealt with by FrameLicense()
        // so checking those is not necessary
        name = name.toLowerCase();
        Database.licenseList = HelperMethods.append(Database.licenseList, name);

        return new FrameLicense(manufacturer, name);
    }
    /**
     * Searches for a license with a name matching the provided license name.
     *     Returns whether the license was found.
     * @param licenseName a String containing the license name of the license
     *     the user wants to know whether Database.licenseList contains.
     * @return a boolean representing whether the license was found.
     * @throws IllegalArgumentException if licenseName is null or "".
     */
    public static DataTag getDataTag(String dataTagID) {
        if (dataTagID == null) {
            throw new IllegalArgumentException("dataTagID is null");
        }
        for (DataTag dataTag : Database.dataTags) {
            if (dataTagID.equals(dataTag.getID())) {
                return new DataTag(dataTag);
            }
        }
        throw new IllegalArgumentException("No data tag found for data tag ID: "
            + dataTagID);
    }
    public static Environment getEnvironment(String environmentID) {
        if (environmentID == null) {
            throw new IllegalArgumentException("environmentID is null");
        }
        for (Environment environment : Database.environments) {
            if (environmentID.equals(environment.getID())) {
                return new Environment(environment);
            }
        }
        throw new IllegalArgumentException("No environment found for"
            + " environment ID: " + environmentID);
    }
    public static Frame getFrame(String frameID) {
        if (frameID == null) {
            throw new IllegalArgumentException("frameID is null");
        }
        for (Frame frame : Database.frames) {
            if (frameID.equals(frame.getID())) {
                return new Frame(frame);
            }
        }
        throw new IllegalArgumentException("No frame found for frame ID: "
            + frameID);
    }
     * @throws IllegalArgumentException if manufacturerName is null or "".
     */
    public static boolean isValidManufacturer(String manufacturerName) {
        HelperMethods.checkString("manufacturerName",
            manufacturerName);
        for (int i = 0; i < Database.manufacturerList.length; i++) {
            if (Database.manufacturerList[i].equals(manufacturerName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * Searches for and returns a Frame matching the provided search ID.
     * @param searchID a String containing the frame ID of the Frame the user
     *     wants.
     * @return a Frame containing the Frame the user wants.
     * @throws IllegalArgumentException if searchID is null, "", or if no Frame
     *     is found matching the provided search ID.
     */
    public static Frame getFrame(String searchID) {
        String frameID;
    
        HelperMethods.checkString("searchID", searchID);
        searchID = searchID.toLowerCase();
        for (int i = 0; i < Database.frameList.length; i++) {
            frameID = Database.frameList[i].getID();
            if (frameID.equals(searchID)) {
                return new Frame(Database.frameList[i]);
            }
        }
        throw new IllegalArgumentException("No frame found for frame ID: \""
            + searchID + "\"");
    }
    /**
     * Searches for and returns a Frame matching the provided search enum.
     * @param searchEnum a FrameEnum containing the FrameEnum of the Frame the
     *     user wants.
     * @return a Frame containing the Frame the user wants.
     * @throws IllegalArgumentException if no Frame is found matching the
     *     provided search enum.
     */
    public static Frame getFrame(FrameEnum searchEnum) {
        FrameEnum frameEnum;
        
        for (int i = 0; i < Database.frameList.length; i++) {
            frameEnum = Database.frameList[i].getFrameEnum();
            if (frameEnum == searchEnum) {
                return new Frame(Database.frameList[i]);
            }
        }
        throw new IllegalArgumentException("No frame found for frame enum: "
            + searchEnum.toString());
    }
    /**
     * Searches for a Frame with a name matching the provided frame name.
     *     Returns whether the Frame was found.
     * @param frameName a String containing the frame name of the Frame the user
     *     wants to know whether Database.frameList contains.
     * @return a boolean representing whether the Frame was found.
     * @throws IllegalArgumentException if frameName is null or "".
     */
    public static boolean containsFrameName(String frameName) {
        HelperMethods.checkString("frameName", frameName);
        for (int i = 0; i < Database.frameList.length; i++) {
            if (Database.frameList[i].getName().equals(frameName)) {
                return true;
            }
        }
        
        return false;
    }
    /**
     * Searches for and returns a MechSystem matching the provided search name.
     * @param searchID a String containing the mech system name of the
     *     MechSystem the user wants. Case-sensitive.
     * @return a MechSystem containing the MechSystem the user wants.
     * @throws IllegalArgumentException if systemName is null, "", or if no
     *     MechSystem is found matching the provided mech system name.
     */
    public static MechSystem getSystem(String systemName) {
        HelperMethods.checkString("systemName", systemName);
        for (int i = 0; i < Database.systemList.length; i++) {
            if (Database.systemList[i].getName().equals(systemName)) {
                return Database.systemList[i];
            }
        }
        throw new IllegalArgumentException("No mech system found for mech"
            + " system name: \"" + systemName + "\"");
    }
    public static Table getTable(String tableName) {
        if (tableName == null) {
            throw new IllegalArgumentException("tableName is null");
        }
        for (Table table : Database.tables) {
            if (tableName.equals(table.getName())) {
                return new Table(table);
            }
        }
        throw new IllegalArgumentException("No table found for table name: "
            + tableName);
    }
    public static Tag getTag(String tagID) {
        if (tagID == null) {
            throw new IllegalArgumentException("tagID is null");
        }
        for (Tag tag : Database.tags) {
            if (tagID.equals(tag.getID())) {
                return new Tag(tag);
            }
        }
        throw new IllegalArgumentException("No tag found for tag ID: "
            + tagID);
    }
    /**
     * Searches for a piece of pilot armor given its name.
     * @param pilotArmorName a String containing the name of the pilot armor to
     *     be searched for.
     * @return an int[] of the requested pilot armor's stats.
     * @throws IllegalArgumentException if the requested pilot armor was not
     *     found.
     */
    public static int[] getPilotArmorStats(String pilotArmorName) {
        for (int i = 0; i < Database.pilotArmorNames.length; i++) {
            if (Database.pilotArmorNames[i].equals(pilotArmorName)) {
                return HelperMethods.copyOf(Database.pilotArmorStats[i]);
            }
        }

        throw new IllegalArgumentException("No pilot armor found for pilot"
            + " armor name: \"" + pilotArmorName + "\"");
    }
    public static Term getTerm(String termName) {
        if (termName == null) {
            throw new IllegalArgumentException("termName is null");
        }
        for (Term term : Database.terms) {
            if (termName.equals(term.getName())) {
                return new Term(term);
            }
        }
        throw new IllegalArgumentException("No term found for term name: "
            + termName);
    }
    public static Weapon getWeapon(String weaponID) {
        if (weaponID == null) {
            throw new IllegalArgumentException("weaponID is null");
        }
        for (Weapon weapon : Database.weapons) {
            if (weaponID.equals(weapon.getID())) {
                return new Weapon(weapon);
            }
        }
        throw new IllegalArgumentException("No weapon found for weapon ID: "
            + weaponID);
    }
    /**
     * Adds the provided Frame to Database.frames.
     * @param frame a Frame which cannot be null.
     * @throws IllegalArgumentException if frame is null.
     */
    public static void addFrame(Frame frame) {
        checkOpen();
        if (frame == null) {
            throw new IllegalArgumentException("frame is null");
        }
        Database.frames = HelperMethods.append(Database.frames, frame);
    }
    /**
     * Adds the provided MechSystem to Database.systems.
     * @param system a MechSystem which cannot be null.
     * @throws IllegalArgumentException if system is null.
     */
    public static void addSystem(MechSystem system) {
        checkOpen();
        if (system == null) {
            throw new IllegalArgumentException("system is null");
        }
        Database.systems = HelperMethods.append(Database.systems, system);
    }
    /**
     * Adds the provided Weapon to Database.weapons.
     * @param weapon a Weapon which cannot be null.
     * @throws IllegalArgumentException if weapon is null.
     */
    public static void addWeapon(Weapon weapon) {
        checkOpen();
        if (weapon == null) {
            throw new IllegalArgumentException("weapon is null");
        }
        Database.weapons = HelperMethods.append(Database.weapons, weapon);
    }
    /**
     * Checks whether the provided pilot armor is a valid pilot armor.
     * @param pilotArmor a String that cannot be null.
     * @return a boolean containing the result of the check.
     */
    public static boolean isValidPilotArmor(String pilotArmor) {
        if (pilotArmor == null) {
            throw new IllegalArgumentException("pilotArmor is null");
        }
        for (String validName : Database.pilotArmorNames) {
            if (pilotArmor.equals(validName)) {
                return true;
            }
        }
        return false;
    private static void checkOpen() {
        if (! isOpen()) {
            throw new IllegalStateException("Cannot add to Database while it"
                + " is closed");
        }
    }
}
