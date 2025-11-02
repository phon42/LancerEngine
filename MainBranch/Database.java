package MainBranch;

import MainBranch.roll.DiceExpression;
import Packages.CoreTypes.Environment;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Sitrep;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.EntityMechanics.Action;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
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
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skillTrigger.Skill;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.HarmSystem.damage.Harm;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.FrameLicense;
import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;

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
        Database.talents = new TalentData[0];
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
        HelperMethods.checkObject("actionName", actionName);
        for (Action action : Database.actions) {
            if (actionName.equals(action.getName())) {
                return new Action(action);
            }
        }
        throw new IllegalArgumentException("No action found for action name: "
            + actionName);
    }
    public static Background getBackground(String backgroundID) {
        HelperMethods.checkObject("backgroundID", backgroundID);
        for (Background background : Database.backgrounds) {
            if (backgroundID.equals(background.getID())) {
                return new Background(background);
            }
        }
        throw new IllegalArgumentException("No background found for background"
            + " ID: " + backgroundID);
    }
    public static Condition getCondition(String conditionName) {
        HelperMethods.checkObject("conditionName", conditionName);
        for (Condition condition : Database.conditions) {
            if (conditionName.equals(condition.getName())) {
                return new Condition(condition);
            }
        }
        throw new IllegalArgumentException("No condition found for condition"
            + " name: " + conditionName);
    }
    public static CoreBonus getCoreBonus(String coreBonusID) {
        HelperMethods.checkObject("coreBonusID", coreBonusID);
        for (CoreBonus coreBonus : Database.coreBonuses) {
            if (coreBonusID.equals(coreBonus.getID())) {
                return new CoreBonus(coreBonus);
            }
        }
        throw new IllegalArgumentException("No core bonus found for core bonus"
            + " ID: " + coreBonusID);
    }
    public static DataTag getDataTag(String dataTagID) {
        HelperMethods.checkObject("dataTagID", dataTagID);
        for (DataTag dataTag : Database.dataTags) {
            if (dataTagID.equals(dataTag.getID())) {
                return new DataTag(dataTag);
            }
        }
        throw new IllegalArgumentException("No data tag found for data tag ID: "
            + dataTagID);
    }
    public static Environment getEnvironment(String environmentID) {
        HelperMethods.checkObject("environmentID", environmentID);
        for (Environment environment : Database.environments) {
            if (environmentID.equals(environment.getID())) {
                return new Environment(environment);
            }
        }
        throw new IllegalArgumentException("No environment found for"
            + " environment ID: " + environmentID);
    }
    public static Frame getFrame(String frameID) {
        HelperMethods.checkObject("frameID", frameID);
        for (Frame frame : Database.frames) {
            if (frameID.equals(frame.getID())) {
                return new Frame(frame);
            }
        }
        throw new IllegalArgumentException("No frame found for frame ID: "
            + frameID);
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
        throw new IllegalArgumentException("No manufacturer found for"
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
        throw new IllegalArgumentException("No modification found for"
            + " modification ID: " + modificationID);
    }
    public static PilotArmor getPilotArmor(String pilotArmorID) {
        HelperMethods.checkObject("pilotArmorID", pilotArmorID);
        for (PilotArmor pilotArmor : Database.pilotArmor) {
            if (pilotArmorID.equals(pilotArmor.getID())) {
                return new PilotArmor(pilotArmor);
            }
        }
        throw new IllegalArgumentException("No pilot armor found for pilot"
            + " armor ID: " + pilotArmorID);
    }
    public static PilotGear getPilotGear(String pilotGearID) {
        HelperMethods.checkObject("pilotGearID", pilotGearID);
        for (PilotGear pilotGear : Database.pilotGear) {
            if (pilotGearID.equals(pilotGear.getID())) {
                return new PilotGear(pilotGear);
            }
        }
        throw new IllegalArgumentException("No pilot gear found for pilot"
            + " gear ID: " + pilotGearID);
    }
    public static PilotWeapon getPilotWeapon(String pilotWeaponID) {
        HelperMethods.checkObject("pilotWeaponID", pilotWeaponID);
        for (PilotWeapon pilotWeapon : Database.pilotWeapons) {
            if (pilotWeaponID.equals(pilotWeapon.getID())) {
                return new PilotWeapon(pilotWeapon);
            }
        }
        throw new IllegalArgumentException("No pilot weapon found for pilot"
            + " weapon ID: " + pilotWeaponID);
    }
    public static Reserve getReserve(String reserveID) {
        HelperMethods.checkObject("reserveID", reserveID);
        for (Reserve reserve : Database.reserves) {
            if (reserveID.equals(reserve.getID())) {
                return new Reserve(reserve);
            }
        }
        throw new IllegalArgumentException("No reserve found for reserve ID: "
            + reserveID);
    }
    public static Rule getRule(String ruleName) {
        HelperMethods.checkObject("ruleName", ruleName);
        for (Rule rule : Database.rules) {
            if (ruleName.equals(rule.getName())) {
                return new Rule(rule);
            }
        }
        throw new IllegalArgumentException("No rule found for rule name: "
            + ruleName);
    }
    public static Sitrep getSitrep(String sitrepID) {
        HelperMethods.checkObject("sitrepID", sitrepID);
        for (Sitrep sitrep : Database.sitreps) {
            if (sitrepID.equals(sitrep.getID())) {
                return new Sitrep(sitrep);
            }
        }
        throw new IllegalArgumentException("No sitrep found for sitrep ID: "
            + sitrepID);
    }
    public static Skill getSkill(String skillID) {
        HelperMethods.checkObject("skillID", skillID);
        for (Skill skill : Database.skills) {
            if (skillID.equals(skill.getID())) {
                return new Skill(skill);
            }
        }
        throw new IllegalArgumentException("No skill found for skill ID: "
            + skillID);
    }
    public static State getState(String stateName) {
        HelperMethods.checkObject("stateName", stateName);
        for (State state : Database.states) {
            if (stateName.equals(state.getName())) {
                return new State(state);
            }
        }
        throw new IllegalArgumentException("No state found for state name: "
            + stateName);
    }
    public static Status getStatus(String statusName) {
        HelperMethods.checkObject("statusName", statusName);
        for (Status status : Database.statuses) {
            if (statusName.equals(status.getName())) {
                return new Status(status);
            }
        }
        throw new IllegalArgumentException("No status found for status name: "
            + statusName);
    }
    public static MechSystem getSystem(String systemID) {
        HelperMethods.checkObject("systemID", systemID);
        for (MechSystem system : Database.systems) {
            if (systemID.equals(system.getID())) {
                return new MechSystem(system);
            }
        }
        throw new IllegalArgumentException("No mech system found for mech"
            + " system ID: " + systemID);
    }
    public static Table getTable(String tableName) {
        HelperMethods.checkObject("tableName", tableName);
        for (Table table : Database.tables) {
            if (tableName.equals(table.getName())) {
                return new Table(table);
            }
        }
        throw new IllegalArgumentException("No table found for table name: "
            + tableName);
    }
    public static Tag getTag(String tagID) {
        HelperMethods.checkObject("tagID", tagID);
        for (Tag tag : Database.tags) {
            if (tagID.equals(tag.getID())) {
                return new Tag(tag);
            }
        }
        throw new IllegalArgumentException("No tag found for tag ID: "
            + tagID);
    }
    public static TalentData getTalent(String talentID) {
        HelperMethods.checkObject("talentID", talentID);
        for (TalentData talent : Database.talents) {
            if (talentID.equals(talent.getID())) {
                return new TalentData(talent);
            }
        }
        throw new IllegalArgumentException("No talent found for talent ID: "
            + talentID);
    }
    public static Term getTerm(String termName) {
        HelperMethods.checkObject("termName", termName);
        for (Term term : Database.terms) {
            if (termName.equals(term.getName())) {
                return new Term(term);
            }
        }
        throw new IllegalArgumentException("No term found for term name: "
            + termName);
    }
    public static Weapon getWeapon(String weaponID) {
        HelperMethods.checkObject("weaponID", weaponID);
        for (Weapon weapon : Database.weapons) {
            if (weaponID.equals(weapon.getID())) {
                return new Weapon(weapon);
            }
        }
        throw new IllegalArgumentException("No weapon found for weapon ID: "
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
        throw new IllegalArgumentException("No frame license found for frame"
            + " license ID: " + frameLicenseID);
    }
    /**
     * Adds the provided Action to Database.actions.
     * @param action a Action which cannot be null.
     * @throws IllegalArgumentException if action is null.
     */
    public static void addAction(Action action) {
        checkOpen();
        HelperMethods.checkObject("action", action);
        Database.actions = HelperMethods.append(Database.actions, action);
    }
    /**
     * Adds the provided Background to Database.backgrounds.
     * @param background a Background which cannot be null.
     * @throws IllegalArgumentException if background is null.
     */
    public static void addBackground(Background background) {
        checkOpen();
        HelperMethods.checkObject("background", background);
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
        Database.dataTags = HelperMethods.append(Database.dataTags, dataTag);
    }
    /**
     * Adds the provided Environment to Database.environments.
     * @param environment a Environment which cannot be null.
     * @throws IllegalArgumentException if environment is null.
     */
    public static void addEnvironment(Environment environment) {
        checkOpen();
        HelperMethods.checkObject("environment", environment);
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
        Database.sitreps = HelperMethods.append(Database.sitreps, sitrep);
    }
    /**
     * Adds the provided Skill to Database.skills.
     * @param skill a Skill which cannot be null.
     * @throws IllegalArgumentException if skill is null.
     */
    public static void addSkill(Skill skill) {
        checkOpen();
        HelperMethods.checkObject("skill", skill);
        Database.skills = HelperMethods.append(Database.skills, skill);
    }
    /**
     * Adds the provided State to Database.states.
     * @param state a State which cannot be null.
     * @throws IllegalArgumentException if state is null.
     */
    public static void addState(State state) {
        checkOpen();
        HelperMethods.checkObject("state", state);
        Database.states = HelperMethods.append(Database.states, state);
    }
    /**
     * Adds the provided Status to Database.statuss.
     * @param status a Status which cannot be null.
     * @throws IllegalArgumentException if status is null.
     */
    public static void addStatus(Status status) {
        checkOpen();
        HelperMethods.checkObject("status", status);
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
        Database.weapons = HelperMethods.append(Database.weapons, weapon);
    }
    public static void addFrameLicense(FrameLicense frameLicense) {
        checkOpen();
        HelperMethods.checkObject("frameLicense", frameLicense);
        Database.frameLicenses = HelperMethods.append(Database.frameLicenses,
            frameLicense);
    }
    private static void checkOpen() {
        if (! isOpen()) {
            throw new IllegalStateException("Cannot add to Database while it"
                + " is closed");
        }
    }
}
