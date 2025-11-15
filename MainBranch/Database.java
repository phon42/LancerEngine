package MainBranch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import MainBranch.database.DatabaseInitialization;
import MainBranch.database.ExternalLCP;
import MainBranch.database.LCPCorrection;
import MainBranch.database.DatabaseReadingPipeline.DatabaseReader;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Modification;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTag.Tag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frame.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.FrameLicense;
import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;

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
    private static LCPCorrection[] lcpCorrections;
    /**
     * add documentation
     */
    private static Action[] actions;
    /**
     * add documentation
     */
    private static ActivationType[] activationTypes;
    /**
     * add documentation
     */
    private static Background[] backgrounds;
    /**
     * add documentation
     */
    private static Bond[] bonds;
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
    private static NPCFeature[] npcFeatures;
    /**
     * add documentation
     */
    private static NPCTemplate[] npcTemplates;
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
    private static SkillData[] skills;
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
    private static TalentData[] talents;
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
        ExternalLCP externalLCP;
        LCPCorrection correction1;
        LCPCorrection correction2;

        // Initialize the database's properties
        clear();

        // Add allowed activation types
        open();
        addActivationType(new ActivationType("free"));
        addActivationType(new ActivationType("protocol"));
        addActivationType(new ActivationType("quick"));
        addActivationType(new ActivationType("full"));
        addActivationType(new ActivationType("invade"));
        addActivationType(new ActivationType("full tech"));
        addActivationType(new ActivationType("quick tech"));
        addActivationType(new ActivationType("reaction"));
        // TODO: not sure whether to add this because it's not included in
        //     https://github.com/massif-press/lancer-data?tab=readme-ov-file#activationtype
        addActivationType(new ActivationType("downtime"));
        close();

        // Add LCP corrections
        correction1 = new LCPCorrection("LANCER Core",
            "actions", "name",
            "Brace", new JSONObject( //
                "{\n" + //
                "  \"id\": \"act_brace\",\n" + //
                "  \"name\": \"Brace\",\n" + //
                "  \"activation\": \"Reaction\",\n" + //
                "  \"terse\": \"Brace your mech for impact, reducing damage at the cost of your next turn’s actions.\",\n" + //
                "  \"trigger\": \"You are hit by an attack and damage has been rolled.\",\n" + //
                "  \"effect\": \"You count as having RESISTANCE to all damage, burn, and heat from the triggering attack, and until the end of your next turn, all other attacks against you are made at +1 difficulty.<br>Due to the stress of bracing, you cannot take reactions until the end of your next turn and on that turn, you can only take one quick action – you cannot OVERCHARGE, move normally, take full actions, or take free actions.\",\n" + //
                "  \"detail\": \"When you BRACE, you ready your mech against incoming fire.<br>Brace<br>Reaction, 1/round<br>Trigger: You are hit by an attack and damage has been rolled.<br>Effect: You count as having RESISTANCE to all damage, burn, and heat from the triggering attack, and until the end of your next turn, all other attacks against you are made at +1 difficulty.<br>Due to the stress of bracing, you cannot take reactions until the end of your next turn and on that turn, you can only take one quick action – you cannot OVERCHARGE, move normally, take full actions, or take free actions.\"\n" + //
                "}"
        ));
        correction2 = new LCPCorrection("LANCER Core",
            "actions", "name",
            "Overwatch", new JSONObject( //
                "{\n" + //
                "  \"id\": \"act_overwatch\",\n" + //
                "  \"name\": \"Overwatch\",\n" + //
                "  \"activation\": \"Reaction\",\n" + //
                "  \"terse\": \"Attack a close-by target attempting to move.\",\n" + //
                "  \"trigger\": \"A hostile character starts any movement (including BOOST and other actions) inside one of your weapons’ THREAT.\",\n" + //
                "  \"effect\": \"Trigger OVERWATCH, immediately using that weapon to SKIRMISH against that character as a reaction, before they move.\",\n" + //
                "  \"detail\": \"When you OVERWATCH, you control and defend the space around your mech from enemy incursion through pilot skill, reflexes, or finely tuned subsystems.<br>Unless specified otherwise, all weapons default to 1 THREAT.<br>Overwatch<br>Reaction, 1/round<br>Trigger: A hostile character starts any movement (including BOOST and other actions) inside one of your weapons’ THREAT.<br>Effect: Trigger OVERWATCH, immediately using that weapon to SKIRMISH against that character as a reaction, before they move.\",\n" + //
                "  \"mech\": true,\n" + //
                "  \"pilot\": true\n" + //
                "}"
        ));
        Database.open();
        Database.addLCPCorrection(correction1);
        Database.addLCPCorrection(correction2);
        Database.close();

        // Add any desired external LCPs
        for (String key: DatabaseInitialization.initializationLCPs.keySet()) {
            externalLCP = (ExternalLCP) DatabaseInitialization
                .initializationLCPs.get(key);
            DatabaseReader.readExternalLCP(externalLCP);
        }
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
            throw new IllegalStateException("Attempted to call"
                + " Database.close() when Database is already closed");
        }
        Database.open = false;
    }
    private static void checkOpen() {
        if (! isOpen()) {
            throw new IllegalStateException("Cannot add to Database while it"
                + " is closed");
        }
    }
    public static void clear() {
        // This clears the entire database!
        Database.lcpCorrections = new LCPCorrection[0];
        Database.actions = new Action[0];
        Database.activationTypes = new ActivationType[0];
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
        Database.skills = new SkillData[0];
        Database.statuses = new Status[0];
        Database.systems = new MechSystem[0];
        Database.tables = new Table[0];
        Database.tags = new Tag[0];
        Database.talents = new TalentData[0];
        Database.terms = new Term[0];
        Database.weapons = new Weapon[0];
        Database.frameLicenses = new FrameLicense[0];
    }
    /**
     * Returns arrays of length 0 to Database.lcpCorrections.length (inclusive)
     *     but never null.
     */
    public static LCPCorrection[] getLCPCorrections(String infoName,
        String lcpManifestName, String fileName) {
        LCPCorrection[] corrections;
        LCPCorrection[] newCorrections;
        int index = 0;

        // fileName is supposed to be something like "actions"; all lowercase,
        //     should not be "", should not be null
        HelperMethods.checkString("fileName", fileName);
        fileName = fileName.toLowerCase();
        corrections = new LCPCorrection[Database.lcpCorrections.length];
        for (int i = 0; i < corrections.length; i++) {
            if (Database.lcpCorrections[i].isTargetFile(infoName,
                lcpManifestName, fileName)) {
                corrections[index] = Database.lcpCorrections[i];
                index++;
            }
        }
        newCorrections = new LCPCorrection[index];
        for (int i = 0; i < newCorrections.length; i++) {
            newCorrections[i] = corrections[i];
        }

        return newCorrections;
    }
    public static Action getAction(String actionName) {
        HelperMethods.checkObject("actionName", actionName);
        for (Action action : Database.actions) {
            if (actionName.equals(action.getName())) {
                return new Action(action);
            }
        }
        throw new NoSuchElementException("No action found for action name: "
            + actionName);
    }
    public static ActivationType getActivationTypeByIndex(int index) {
        if (index < 0 || index >= Database.activationTypes.length) {
            throw new IllegalArgumentException("index: " + index + " is out of"
                + " bounds for length: " + Database.activationTypes.length);
        }

        return new ActivationType(Database.activationTypes[index]);
    }
    public static Background getBackground(String backgroundID) {
        HelperMethods.checkObject("backgroundID", backgroundID);
        for (Background background : Database.backgrounds) {
            if (backgroundID.equals(background.getID())) {
                return new Background(background);
            }
        }
        throw new NoSuchElementException("No background found for background"
            + " ID: " + backgroundID);
    }
    public static Condition getCondition(String conditionName) {
        HelperMethods.checkObject("conditionName", conditionName);
        for (Condition condition : Database.conditions) {
            if (conditionName.equals(condition.getName())) {
                return new Condition(condition);
            }
        }
        throw new NoSuchElementException("No condition found for condition"
            + " name: " + conditionName);
    }
    public static CoreBonus getCoreBonus(String coreBonusID) {
        HelperMethods.checkObject("coreBonusID", coreBonusID);
        for (CoreBonus coreBonus : Database.coreBonuses) {
            if (coreBonusID.equals(coreBonus.getID())) {
                return new CoreBonus(coreBonus);
            }
        }
        throw new NoSuchElementException("No core bonus found for core bonus"
            + " ID: " + coreBonusID);
    }
    public static DataTag getDataTag(String dataTagID) {
        HelperMethods.checkObject("dataTagID", dataTagID);
        for (DataTag dataTag : Database.dataTags) {
            if (dataTagID.equals(dataTag.getID())) {
                return new DataTag(dataTag);
            }
        }
        throw new NoSuchElementException("No data tag found for data tag ID: "
            + dataTagID);
    }
    public static Environment getEnvironment(String environmentID) {
        HelperMethods.checkObject("environmentID", environmentID);
        for (Environment environment : Database.environments) {
            if (environmentID.equals(environment.getID())) {
                return new Environment(environment);
            }
        }
        throw new NoSuchElementException("No environment found for"
            + " environment ID: " + environmentID);
    }
    public static Frame getFrame(String frameID) {
        HelperMethods.checkObject("frameID", frameID);
        for (Frame frame : Database.frames) {
            if (frameID.equals(frame.getID())) {
                return new Frame(frame);
            }
        }
        throw new NoSuchElementException("No frame found for frame ID: "
            + frameID);
    }
    public static Frame getFrame(FrameEnum frameEnum) {
        HelperMethods.checkObject("frameEnum", frameEnum);
        for (Frame frame : Database.frames) {
            if (frameEnum.equals(frame.getFrameEnum())) {
                return new Frame(frame);
            }
        }
        throw new NoSuchElementException("No frame found for frame enum: "
            + frameEnum);
    }
    public static Manufacturer getManufacturer(String manufacturerID) {
        HelperMethods.checkObject("manufacturerID",
            manufacturerID);
        manufacturerID = manufacturerID.toUpperCase();
        for (Manufacturer manufacturer : Database.manufacturers) {
            if (manufacturerID.equals(manufacturer.getID())) {
                return new Manufacturer(manufacturer);
            }
        }
        throw new NoSuchElementException("No manufacturer found for"
            + " manufacturer ID: " + manufacturerID);
    }
    public static Modification getModification(String modificationID) {
        HelperMethods.checkObject("modificationID",
            modificationID);
        for (Modification modification : Database.modifications) {
            if (modificationID.equals(modification.getID())) {
                return new Modification(modification);
            }
        }
        throw new NoSuchElementException("No modification found for"
            + " modification ID: " + modificationID);
    }
    public static PilotArmor getPilotArmor(String pilotArmorID) {
        HelperMethods.checkObject("pilotArmorID", pilotArmorID);
        for (PilotArmor pilotArmor : Database.pilotArmor) {
            if (pilotArmorID.equals(pilotArmor.getID())) {
                return new PilotArmor(pilotArmor);
            }
        }
        throw new NoSuchElementException("No pilot armor found for pilot"
            + " armor ID: " + pilotArmorID);
    }
    public static PilotGear getPilotGear(String pilotGearID) {
        HelperMethods.checkObject("pilotGearID", pilotGearID);
        for (PilotGear pilotGear : Database.pilotGear) {
            if (pilotGearID.equals(pilotGear.getID())) {
                return new PilotGear(pilotGear);
            }
        }
        throw new NoSuchElementException("No pilot gear found for pilot"
            + " gear ID: " + pilotGearID);
    }
    public static PilotWeapon getPilotWeapon(String pilotWeaponID) {
        HelperMethods.checkObject("pilotWeaponID", pilotWeaponID);
        for (PilotWeapon pilotWeapon : Database.pilotWeapons) {
            if (pilotWeaponID.equals(pilotWeapon.getID())) {
                return new PilotWeapon(pilotWeapon);
            }
        }
        throw new NoSuchElementException("No pilot weapon found for pilot"
            + " weapon ID: " + pilotWeaponID);
    }
    public static Reserve getReserve(String reserveID) {
        HelperMethods.checkObject("reserveID", reserveID);
        for (Reserve reserve : Database.reserves) {
            if (reserveID.equals(reserve.getID())) {
                return new Reserve(reserve);
            }
        }
        throw new NoSuchElementException("No reserve found for reserve ID: "
            + reserveID);
    }
    public static Rule getRule(String ruleName) {
        HelperMethods.checkObject("ruleName", ruleName);
        for (Rule rule : Database.rules) {
            if (ruleName.equals(rule.getName())) {
                return new Rule(rule);
            }
        }
        throw new NoSuchElementException("No rule found for rule name: "
            + ruleName);
    }
    public static Sitrep getSitrep(String sitrepID) {
        HelperMethods.checkObject("sitrepID", sitrepID);
        for (Sitrep sitrep : Database.sitreps) {
            if (sitrepID.equals(sitrep.getID())) {
                return new Sitrep(sitrep);
            }
        }
        throw new NoSuchElementException("No sitrep found for sitrep ID: "
            + sitrepID);
    }
    public static SkillData getSkillDataByID(String skillDataID) {
        HelperMethods.checkString("skillDataID", skillDataID);
        skillDataID = skillDataID.toLowerCase();
        for (SkillData skillData : Database.skills) {
            if (skillDataID.equals(skillData.getID())) {
                return new SkillData(skillData);
            }
        }
        throw new NoSuchElementException("No skill found for skill data ID: "
            + skillDataID);
    }
    public static SkillData getSkillDataByName(String skillDataName) {
        HelperMethods.checkString("skillDataName", skillDataName);
        for (SkillData skillData : Database.skills) {
            if (skillDataName.equals(skillData.getName())) {
                return new SkillData(skillData);
            }
        }
        throw new NoSuchElementException("No skill found for skill data name: "
            + skillDataName);
    }
    public static State getState(String stateName) {
        HelperMethods.checkObject("stateName", stateName);
        try {
            return getCondition(stateName);
        } catch (NoSuchElementException exception) {
            try {
                return getStatus(stateName);
            } catch (NoSuchElementException exception2) {
                throw new NoSuchElementException("No state found for state"
                    + " name: " + stateName);
            }
        }
    }
    public static Status getStatus(String statusName) {
        HelperMethods.checkObject("statusName", statusName);
        for (Status status : Database.statuses) {
            if (statusName.equals(status.getName())) {
                return new Status(status);
            }
        }
        throw new NoSuchElementException("No status found for status name: "
            + statusName);
    }
    public static MechSystem getSystem(String systemID) {
        HelperMethods.checkObject("systemID", systemID);
        for (MechSystem system : Database.systems) {
            if (systemID.equals(system.getID())) {
                return new MechSystem(system);
            }
        }
        throw new NoSuchElementException("No mech system found for mech"
            + " system ID: " + systemID);
    }
    public static Table getTable(String tableName) {
        HelperMethods.checkObject("tableName", tableName);
        for (Table table : Database.tables) {
            if (tableName.equals(table.getName())) {
                return new Table(table);
            }
        }
        throw new NoSuchElementException("No table found for table name: "
            + tableName);
    }
    public static Tag getTag(String tagID) {
        HelperMethods.checkObject("tagID", tagID);
        for (Tag tag : Database.tags) {
            if (tagID.equals(tag.getID())) {
                return new Tag(tag);
            }
        }
        throw new NoSuchElementException("No tag found for tag ID: "
            + tagID);
    }
    public static TalentData getTalent(String talentID) {
        HelperMethods.checkObject("talentID", talentID);
        for (TalentData talent : Database.talents) {
            if (talentID.equals(talent.getID())) {
                return new TalentData(talent);
            }
        }
        throw new NoSuchElementException("No talent found for talent ID: "
            + talentID);
    }
    public static Term getTerm(String termName) {
        HelperMethods.checkObject("termName", termName);
        for (Term term : Database.terms) {
            if (termName.equals(term.getName())) {
                return new Term(term);
            }
        }
        throw new NoSuchElementException("No term found for term name: "
            + termName);
    }
    public static Weapon getWeapon(String weaponID) {
        HelperMethods.checkObject("weaponID", weaponID);
        for (Weapon weapon : Database.weapons) {
            if (weaponID.equals(weapon.getID())) {
                return new Weapon(weapon);
            }
        }
        throw new NoSuchElementException("No weapon found for weapon ID: "
            + weaponID);
    }
    public static FrameLicense getFrameLicense(String frameLicenseID) {
        HelperMethods.checkObject("frameLicenseID",
            frameLicenseID);
        for (FrameLicense frameLicense : Database.frameLicenses) {
            if (frameLicenseID.equals(frameLicense.getID())) {
                return new FrameLicense(frameLicense);
            }
        }
        throw new NoSuchElementException("No frame license found for frame"
            + " license ID: " + frameLicenseID);
    }
    /**
     * Adds the provided LCPCorrection to Database.lcpCorrections.
     * @param lcpCorrection a LCPCorrection which cannot be null.
     * @throws IllegalArgumentException if lcpCorrection is null.
     */
    public static void addLCPCorrection(LCPCorrection lcpCorrection) {
        checkOpen();
        HelperMethods.checkObject("lcpCorrection", lcpCorrection);
        lcpCorrection = new LCPCorrection(lcpCorrection);
        Database.lcpCorrections = HelperMethods.append(Database.lcpCorrections,
            lcpCorrection);
    }
    /**
     * Adds the provided Action to Database.actions.
     * @param action an Action which cannot be null.
     * @throws IllegalArgumentException if action is null.
     */
    public static void addAction(Action action) {
        checkOpen();
        HelperMethods.checkObject("action", action);
        action = new Action(action);
        Database.actions = HelperMethods.append(Database.actions, action);
    }
    /**
     * Adds the provided ActivationType to Database.activationTypes.
     * @param activationType an ActivationType which cannot be null.
     * @throws IllegalArgumentException if activationType is null.
     */
    public static void addActivationType(ActivationType activationType) {
        checkOpen();
        HelperMethods.checkObject("action", activationType);
        activationType = new ActivationType(activationType);
        Database.activationTypes =
            HelperMethods.append(Database.activationTypes, activationType);
    }
    /**
     * Adds the provided Background to Database.backgrounds.
     * @param background a Background which cannot be null.
     * @throws IllegalArgumentException if background is null.
     */
    public static void addBackground(Background background) {
        checkOpen();
        HelperMethods.checkObject("background", background);
        background = new Background(background);
        Database.backgrounds = HelperMethods.append(Database.backgrounds,
            background);
    }
    /**
     * Adds the provided Bond to Database.bonds.
     * @param bond a Bond which cannot be null.
     * @throws IllegalArgumentException if bond is null.
     */
    public static void addBond(Bond bond) {
        checkOpen();
        HelperMethods.checkObject("bond", bond);
        bond = new Bond(bond);
        Database.bonds = HelperMethods.append(Database.bonds, bond);
    }
    /**
     * Adds the provided Condition to Database.conditions.
     * @param condition a Condition which cannot be null.
     * @throws IllegalArgumentException if condition is null.
     */
    public static void addCondition(Condition condition) {
        checkOpen();
        HelperMethods.checkObject("condition", condition);
        condition = new Condition(condition);
        Database.conditions = HelperMethods.append(Database.conditions,
            condition);
    }
    /**
     * Adds the provided CoreBonus to Database.coreBonuses.
     * @param coreBonus a CoreBonus which cannot be null.
     * @throws IllegalArgumentException if coreBonus is null.
     */
    public static void addCoreBonus(CoreBonus coreBonus) {
        checkOpen();
        HelperMethods.checkObject("coreBonus", coreBonus);
        coreBonus = new CoreBonus(coreBonus);
        Database.coreBonuses = HelperMethods.append(Database.coreBonuses,
            coreBonus);
    }
    /**
     * Adds the provided DataTag to Database.dataTags.
     * @param dataTag a DataTag which cannot be null.
     * @throws IllegalArgumentException if dataTag is null.
     */
    public static void addDataTag(DataTag dataTag) {
        checkOpen();
        HelperMethods.checkObject("dataTag", dataTag);
        dataTag = new DataTag(dataTag);
        Database.dataTags = HelperMethods.append(Database.dataTags, dataTag);
    }
    /**
     * Adds the provided Environment to Database.environments.
     * @param environment an Environment which cannot be null.
     * @throws IllegalArgumentException if environment is null.
     */
    public static void addEnvironment(Environment environment) {
        checkOpen();
        HelperMethods.checkObject("environment", environment);
        environment = new Environment(environment);
        Database.environments = HelperMethods.append(Database.environments,
            environment);
    }
    /**
     * Adds the provided Frame to Database.frames.
     * @param frame a Frame which cannot be null.
     * @throws IllegalArgumentException if frame is null.
     */
    public static void addFrame(Frame frame) {
        checkOpen();
        HelperMethods.checkObject("frame", frame);
        frame = new Frame(frame);
        Database.frames = HelperMethods.append(Database.frames, frame);
    }
    /**
     * Adds the provided Manufacturer to Database.manufacturers.
     * @param manufacturer a Manufacturer which cannot be null.
     * @throws IllegalArgumentException if manufacturer is null.
     */
    public static void addManufacturer(Manufacturer manufacturer) {
        checkOpen();
        HelperMethods.checkObject("manufacturer", manufacturer);
        manufacturer = new Manufacturer(manufacturer);
        Database.manufacturers = HelperMethods.append(Database.manufacturers,
            manufacturer);
    }
    /**
     * Adds the provided NPCFeature to Database.npcFeatures.
     * @param npcFeature an NPCFeature which cannot be null.
     * @throws IllegalArgumentException if npcFeature is null.
     */
    public static void addNPCFeature(NPCFeature npcFeature) {
        checkOpen();
        HelperMethods.checkObject("npcFeature", npcFeature);
        npcFeature = new NPCFeature(npcFeature);
        Database.npcFeatures = HelperMethods.append(Database.npcFeatures,
            npcFeature);
    }
    /**
     * Adds the provided NPCTemplate to Database.npcTemplates.
     * @param npcTemplate an NPCTemplate which cannot be null.
     * @throws IllegalArgumentException if npcTemplate is null.
     */
    public static void addNPCTemplate(NPCTemplate npcTemplate) {
        checkOpen();
        HelperMethods.checkObject("npcTemplate", npcTemplate);
        npcTemplate = new NPCTemplate(npcTemplate);
        Database.npcTemplates = HelperMethods.append(Database.npcTemplates,
            npcTemplate);
    }
    /**
     * Adds the provided PilotArmor to Database.pilotArmor.
     * @param pilotArmor a PilotArmor which cannot be null.
     * @throws IllegalArgumentException if pilotArmor is null.
     */
    public static void addPilotArmor(PilotArmor pilotArmor) {
        checkOpen();
        HelperMethods.checkObject("pilotArmor", pilotArmor);
        pilotArmor = new PilotArmor(pilotArmor);
        Database.pilotArmor = HelperMethods.append(Database.pilotArmor,
            pilotArmor);
    }
    /**
     * Adds the provided PilotGear to Database.pilotGear.
     * @param pilotGear a PilotGear which cannot be null.
     * @throws IllegalArgumentException if pilotGear is null.
     */
    public static void addPilotGear(PilotGear pilotGear) {
        checkOpen();
        HelperMethods.checkObject("pilotGear", pilotGear);
        pilotGear = new PilotGear(pilotGear);
        Database.pilotGear = HelperMethods.append(Database.pilotGear,
            pilotGear);
    }
    /**
     * Adds the provided PilotWeapon to Database.pilotWeapon.
     * @param pilotWeapon a PilotWeapon which cannot be null.
     * @throws IllegalArgumentException if pilotWeapon is null.
     */
    public static void addPilotWeapon(PilotWeapon pilotWeapon) {
        checkOpen();
        HelperMethods.checkObject("pilotWeapon", pilotWeapon);
        pilotWeapon = new PilotWeapon(pilotWeapon);
        Database.pilotWeapons = HelperMethods.append(Database.pilotWeapons,
            pilotWeapon);
    }
    /**
     * Adds the provided Reserve to Database.reserves.
     * @param reserve a Reserve which cannot be null.
     * @throws IllegalArgumentException if reserve is null.
     */
    public static void addReserve(Reserve reserve) {
        checkOpen();
        HelperMethods.checkObject("reserve", reserve);
        reserve = new Reserve(reserve);
        Database.reserves = HelperMethods.append(Database.reserves, reserve);
    }
    /**
     * Adds the provided Rule to Database.rules.
     * @param rule a Rule which cannot be null.
     * @throws IllegalArgumentException if rule is null.
     */
    public static void addRule(Rule rule) {
        checkOpen();
        HelperMethods.checkObject("rule", rule);
        rule = new Rule(rule);
        Database.rules = HelperMethods.append(Database.rules, rule);
    }
    /**
     * Adds the provided Sitrep to Database.sitreps.
     * @param sitrep a Sitrep which cannot be null.
     * @throws IllegalArgumentException if sitrep is null.
     */
    public static void addSitrep(Sitrep sitrep) {
        checkOpen();
        HelperMethods.checkObject("sitrep", sitrep);
        sitrep = new Sitrep(sitrep);
        Database.sitreps = HelperMethods.append(Database.sitreps, sitrep);
    }
    /**
     * Adds the provided SkillData to Database.skills.
     * @param skillData a SkillData which cannot be null.
     * @throws IllegalArgumentException if skill is null.
     */
    public static void addSkill(SkillData skillData) {
        checkOpen();
        HelperMethods.checkObject("skillData", skillData);
        skillData = new SkillData(skillData);
        Database.skills = HelperMethods.append(Database.skills, skillData);
    }
    // TODO: remove?
    /**
     * Adds the provided State to Database.conditions or Database.statuses.
     * @param state a State which cannot be null.
     * @throws IllegalArgumentException if state is null.
     */
    public static void addState(State state) {
        Condition condition;
        Status status;

        checkOpen();
        HelperMethods.checkObject("state", state);
        try {
            condition = state.toCondition();
            addCondition(condition);
        } catch (IllegalStateException exception) {
            try {
                status = state.toStatus();
                addStatus(status);
            } catch (IllegalStateException exception2) {
                throw new IllegalStateException("Unable to convert the"
                    + " provided State into either a Condition or a Status");
            }
        }
    }
    /**
     * Adds the provided Status to Database.statuss.
     * @param status a Status which cannot be null.
     * @throws IllegalArgumentException if status is null.
     */
    public static void addStatus(Status status) {
        checkOpen();
        HelperMethods.checkObject("status", status);
        status = new Status(status);
        Database.statuses = HelperMethods.append(Database.statuses, status);
    }
    /**
     * Adds the provided MechSystem to Database.systems.
     * @param system a MechSystem which cannot be null.
     * @throws IllegalArgumentException if system is null.
     */
    public static void addSystem(MechSystem system) {
        checkOpen();
        HelperMethods.checkObject("system", system);
        system = new MechSystem(system);
        Database.systems = HelperMethods.append(Database.systems, system);
    }
    /**
     * Adds the provided Table to Database.tables.
     * @param table a Table which cannot be null.
     * @throws IllegalArgumentException if table is null.
     */
    public static void addTable(Table table) {
        checkOpen();
        HelperMethods.checkObject("table", table);
        table = new Table(table);
        Database.tables = HelperMethods.append(Database.tables, table);
    }
    /**
     * Adds the provided Tag to Database.tags.
     * @param tag a Tag which cannot be null.
     * @throws IllegalArgumentException if tag is null.
     */
    public static void addTag(Tag tag) {
        checkOpen();
        HelperMethods.checkObject("tag", tag);
        tag = new Tag(tag);
        Database.tags = HelperMethods.append(Database.tags, tag);
    }
    /**
     * Adds the provided TalentData to Database.talents.
     * @param talent a TalentData which cannot be null.
     * @throws IllegalArgumentException if talent is null.
     */
    public static void addTalent(TalentData talent) {
        checkOpen();
        HelperMethods.checkObject("talent", talent);
        talent = new TalentData(talent);
        Database.talents = HelperMethods.append(Database.talents, talent);
    }
    /**
     * Adds the provided Term to Database.terms.
     * @param term a Term which cannot be null.
     * @throws IllegalArgumentException if term is null.
     */
    public static void addTerm(Term term) {
        checkOpen();
        HelperMethods.checkObject("term", term);
        term = new Term(term);
        Database.terms = HelperMethods.append(Database.terms, term);
    }
    /**
     * Adds the provided Weapon to Database.weapons.
     * @param weapon a Weapon which cannot be null.
     * @throws IllegalArgumentException if weapon is null.
     */
    public static void addWeapon(Weapon weapon) {
        checkOpen();
        HelperMethods.checkObject("weapon", weapon);
        weapon = new Weapon(weapon);
        Database.weapons = HelperMethods.append(Database.weapons, weapon);
    }
    public static void addFrameLicense(FrameLicense frameLicense) {
        checkOpen();
        HelperMethods.checkObject("frameLicense", frameLicense);
        frameLicense = new FrameLicense(frameLicense);
        Database.frameLicenses = HelperMethods.append(Database.frameLicenses,
            frameLicense);
    }
}
