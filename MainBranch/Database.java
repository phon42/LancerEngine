package MainBranch;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;
import MainBranch.database.DatabaseInitialization;
import MainBranch.database.ExternalLCP;
import MainBranch.database.LCPCorrection;
import MainBranch.database.DatabaseReadingPipeline.DatabaseReader;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.LCPInfo;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.RangeType;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.Modification;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.dataTag.ITagData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.dataTag.iTagData.ITagDataUnhidden;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.SystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.MechSystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.WeaponType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount.MountType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.UnverifiedCoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.Background;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.UnverifiedBackground;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserve.ReserveData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserve.reserveData.ReserveType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus.CoreBonus;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.HarmType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.FrameLicense;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;
import Packages.CoreTypes.EntityMechanics.frequency.FrequencyType;

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
    private static LCPInfo[] lcpInfo;

    // Unverified data
    /**
     * add documentation
     */
    private static UnverifiedBackground[] unverifiedBackgrounds;
    /**
     * add documentation
     */
    private static UnverifiedCoreBonus[] unverifiedCoreBonuses;
    /**
     * add documentation
     */
    private static UnverifiedDataTag[] unverifiedDataTags;

    // Verified data
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
    private static StateData[] conditions;
    /**
     * add documentation
     */
    private static CoreBonus[] coreBonuses;
    /**
     * add documentation
     */
    private static DamageType[] damageTypes;
    /**
     * add documentation
     */
    private static FrequencyType[] frequencyTypes;
    /**
     * add documentation
     */
    private static Environment[] environments;
    /**
     * Contains every frame's data for reference.
     */
    private static Frame[] frames;
    /**
     * add documentation
     */
    private static HarmType[] harmTypes;
    /**
     * add documentation
     */
    private static IActionData[] iActionData;
    /**
     * add documentation
     */
    private static ITagData[] iTagData;
    /**
     * add documentation
     */
    private static ITagDataUnhidden[] iTagDataUnhidden;
    /**
     * Contains every manufacturer for reference.
     */
    private static Manufacturer[] manufacturers;
    /**
     * Contains every mech system for reference.
     */
    private static MechSystem[] mechSystems;
    /**
     * add documentation
     */
    private static MechSystemType[] mechSystemTypes;
    /**
     * add documentation
     */
    private static Modification[] modifications;
    /**
     * add documentation
     */
    private static MountType[] mountTypes;
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
    private static RangeType[] rangeTypes;
    /**
     * add documentation
     */
    private static ReserveData[] reserves;
    /**
     * add documentation
     */
    private static ReserveType[] reserveTypes;
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
    private static StateData[] statuses;
    /**
     * add documentation
     */
    private static SynergyLocation[] synergyLocations;
    /**
     * add documentation
     */
    private static SystemType[] systemTypes;
    /**
     * add documentation
     */
    private static Table[] tables;
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
     * add documentation
     */
    private static WeaponSize[] weaponSizes;
    /**
     * add documentation
     */
    private static WeaponType[] weaponTypes;
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

    // Prevent user from instantiating this class
    private Database() {}

    public static void initialize() {
        final String begin = "=== Initializing database ======================="
            + "===============================";
        final String setupBegin = "========= Setting up default allowed values "
            + "====================================";
        final String setupDone = "========= Allowed values setup complete ====="
            + "===================================";
        final String done = "=== Database initialization complete ============="
            + "==============================";
        ExternalLCP externalLCP;
        LCPCorrection correction1;
        LCPCorrection correction2;
        Set<String> keys;
        String[] keysArray;

        System.out.println(begin);
        // Initialize the database's properties
        clear();

        open();
        System.out.println(setupBegin);

        // Add allowed activation types
        addActivationType(new ActivationType("free", "free"));
        addActivationType(new ActivationType("protocol",
            "protocol"));
        addActivationType(new ActivationType("quick", "quick"));
        addActivationType(new ActivationType("full", "full"));
        addActivationType(new ActivationType("invade", "free"));
        addActivationType(new ActivationType("full tech",
            "full tech"));
        addActivationType(new ActivationType("quick tech",
            "quick tech"));
        addActivationType(new ActivationType("reaction",
            "reaction"));
        // TODO: not sure whether to add this because it's not included in
        //     https://github.com/massif-press/lancer-data?tab=readme-ov-file#activationtype
        addActivationType(new ActivationType("downtime"));

        // Add allowed damage types
        /**
         * See pgs. 67 and 104.
         */
        addDamageType(new DamageType("Kinetic"));
        addDamageType(new DamageType("Explosive"));
        addDamageType(new DamageType("Energy"));
        addDamageType(new DamageType("Burn"));

        // Add allowed frequency types
        addFrequencyType(new FrequencyType("Unlimited"));
        addFrequencyType(new FrequencyType("X/round"));
        addFrequencyType(new FrequencyType("X/scene"));
        addFrequencyType(new FrequencyType("X/encounter"));
        addFrequencyType(new FrequencyType("X/mission"));

        // Add allowed harm types
        /**
         * See pgs. 67 and 104.
         */
        addHarmType(new HarmType("Variable"));
        addHarmType(new HarmType("Heat"));

        // addMechSystemType calls moved under addSystemType section because
        //     they reference SystemTypes

        // addMountType calls moved under addWeaponSize section because they
        //     reference WeaponSizes

        // Add allowed synergy locations
        addSynergyLocation(new SynergyLocation(
            "active_effects",
            "The Active Effects panel near the top of the Active"
                + " Mode view"
        ));
        addSynergyLocation(new SynergyLocation(
            "rest",
            "A panel near the top of the Active Mode:Rest view"
        ));
        addSynergyLocation(new SynergyLocation(
            "weapon",
            "The body of the equipped weapon item panel in a"
                + " loadout, as well as in the Skirmish/Barrage action modals"
        ));
        addSynergyLocation(new SynergyLocation(
            "system",
            "The body of the equipped system item panel in a"
                + " loadout, as well as in the Activation Action modals"
        ));
        addSynergyLocation(new SynergyLocation(
            "deployable",
            "Deployment action for deployable, deployable panel"
                + " body"
        ));
        addSynergyLocation(new SynergyLocation(
            "drone",
            "Deployment action for drone, drone panel body"
        ));
        addSynergyLocation(new SynergyLocation(
            "move",
            "Next to the move pip bar, also within the Move"
                + " menu/move Action tab"
        ));
        addSynergyLocation(new SynergyLocation(
            "boost",
            "Next to the Boost button, within the Boost Action"
                + " modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "structure",
            "Next to the structure pip tracker in the Active Mode:"
                + " Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "armor",
            "Next to the armor pip tracker in the Active Mode:"
                + " Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "hp",
            "Next to the HP pip tracker in the Active Mode: Combat"
                + " view"
        ));
        addSynergyLocation(new SynergyLocation(
            "overshield",
            "Next to the overshield pip tracker in the Active"
                + " Mode: Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "stress",
            "Next to the reactor stress pip tracker in the Active"
                + " Mode: Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "heat",
            "Next to the heat pip tracker in the Active Mode:"
                + " Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "repair",
            "Next to the repair capacity pip tracker in the Active"
                + " Mode: Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "core_power",
            "Next to the CORE power pip tracker in the Active"
                + " Mode: Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "overcharge",
            "Next to the overcharge pip tracker in the Active"
                + " Mode: Combat view"
        ));
        addSynergyLocation(new SynergyLocation(
            "ram", "Ram Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "grapple", "Grapple Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "tech_attack", "Tech Attack Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "overcharge", "Overcharge Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "skill_check", "Skill Check Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "overwatch", "Overwatch Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "improvised_attack",
            "Improvised Attack Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "disengage", "Disengage Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "dismount", "Dismount Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "stabilize", "Stabilize Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "tech", "Quick and Full Tech Attack modals"
        ));
        addSynergyLocation(new SynergyLocation(
            "lock_on", "Lock On Action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "hull", "mouseover tooltip for HULL stat"
        ));
        addSynergyLocation(new SynergyLocation(
            "agility", "mouseover tooltip for AGILITY stat"
        ));
        addSynergyLocation(new SynergyLocation(
            "systems", "mouseover tooltip for SYSTEMS stat"
        ));
        addSynergyLocation(new SynergyLocation(
            "engineering",
            "mouseover tooltip for ENGINEERING stat"
        ));
        addSynergyLocation(new SynergyLocation(
            "pilot_weapon",
            "Pilot Weapon panel and action modal"
        ));
        addSynergyLocation(new SynergyLocation(
            "cascade", "Cascade warning panel"
        ));

        // Add allowed system types
        addSystemType(new SystemType("Mech System"));
        addSystemType(new SystemType("Weapon"));

        // Add allowed mech system types
        addMechSystemType(new MechSystemType("AI"));
        addMechSystemType(new MechSystemType("Deployable"));
        addMechSystemType(new MechSystemType("Drone"));
        addMechSystemType(new MechSystemType("Flight System"));
        addMechSystemType(new MechSystemType("Shield"));
        addMechSystemType(new MechSystemType("System"));
        addMechSystemType(new MechSystemType("Tech"));

        // Add allowed weapon sizes
        addWeaponSize(new WeaponSize(0, "Aux"));
        addWeaponSize(new WeaponSize(1, "Main"));
        addWeaponSize(new WeaponSize(2, "Heavy"));
        addWeaponSize(new WeaponSize(3, "Superheavy"));
        addWeaponSize(new WeaponSize(4, "Ship-Class"));

        // Add allowed mount sizes
        addMountType(
            new MountType(0, "Aux", new WeaponSize[] {
                Database.getWeaponSize("Aux")
            }, null)
        );
        addMountType(
            new MountType(1, "Aux/Aux", new WeaponSize[] {
                Database.getWeaponSize("Aux")
            }, null)
        );
        addMountType(
            new MountType(2, "Main", new WeaponSize[] {
                Database.getWeaponSize("Main")
            }, null)
        );
        addMountType(
            new MountType(3, "Flex", new WeaponSize[] {
                Database.getWeaponSize("Aux"),
                Database.getWeaponSize("Main")
            }, null)
        );
        addMountType(
            new MountType(4, "Main/Aux", new WeaponSize[] {
                Database.getWeaponSize("Aux"),
                Database.getWeaponSize("Main")
            }, null)
        );
        addMountType(
            new MountType(5, "Heavy", new WeaponSize[] {
                Database.getWeaponSize("Heavy")
            }, null)
        );
        addMountType(
            new MountType(6, "Integrated Mount", null,
            null)
        );
        addMountType(
            new MountType(7, "Integrated Weapon", null,
            null)
        );
        addMountType(
            new MountType(8, "Integrated Weapon Core Bonus",
            new WeaponSize[] {
                Database.getWeaponSize("Aux")
            }, null)
        );
        addMountType(
            new MountType(9, "Improved Armament Core Bonus",
            new WeaponSize[] {
                Database.getWeaponSize("Aux"),
                Database.getWeaponSize("Main")
            }, null)
        );

        // Add allowed range types
        addRangeType(new RangeType(0, "Range"));
        addRangeType(new RangeType(1, "Threat"));
        addRangeType(new RangeType(2, "Line"));
        addRangeType(new RangeType(3, "Cone"));
        addRangeType(new RangeType(4, "Blast"));
        addRangeType(new RangeType(5, "Burst"));
        addRangeType(new RangeType(6, "Thrown"));

        // Add allowed weapon types
        addWeaponType(new WeaponType("CQB"));
        addWeaponType(new WeaponType("Cannon"));
        addWeaponType(new WeaponType("Launcher"));
        addWeaponType(new WeaponType("Melee"));
        addWeaponType(new WeaponType("Nexus"));
        addWeaponType(new WeaponType("Rifle"));
        addWeaponType(new WeaponType("Drone Weapon"));
        addWeaponType(new WeaponType("Spool Weapon"));
        addWeaponType(new WeaponType("???"));
        addWeaponType(new WeaponType("Special"));

        System.out.println(setupDone);
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
                "  \"detail\": \"When you BRACE, you ready your mech against incoming fire.<br>Brace<br>Reaction, 1/round<br>Trigger: You are hit by an attack and damage has been rolled.<br>Effect: You count as having RESISTANCE to all damage, burn, and heat from the triggering attack, and until the end of your next turn, all other attacks against you are made at +1 difficulty.<br>Due to the stress of bracing, you cannot take reactions until the end of your next turn and on that turn, you can only take one quick action – you cannot OVERCHARGE, move normally, take full actions, or take free actions.\",\n" + //
                "  \"frequency\": \"1/round\"\n" + //
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
                "  \"pilot\": true,\n" + //
                "  \"frequency\": \"1/round\"\n" + //
                "}"
        ));
        Database.open();
        Database.addLCPCorrection(correction1);
        Database.addLCPCorrection(correction2);
        Database.close();

        // Add any desired external LCPs
        keys = DatabaseInitialization.initializationLCPs.keySet();
        keysArray = keys.toArray(new String[keys.size()]);
        Arrays.sort(keysArray);
        for (String key : keysArray) {
            externalLCP =
                (ExternalLCP) DatabaseInitialization
                .initializationLCPs.get(key);
            DatabaseReader.readExternalLCP(externalLCP, true,
                true);
        }
        System.out.println(done);
    }
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
        verifyData();
        Database.open = false;
    }
    private static void verifyData() {
        for (UnverifiedBackground unverifiedBackground :
            Database.unverifiedBackgrounds) {
            addBackground(unverifiedBackground.verify());
        }
        Database.unverifiedBackgrounds = new UnverifiedBackground[0];
        for (UnverifiedCoreBonus unverifiedCoreBonus :
            Database.unverifiedCoreBonuses) {
            addCoreBonus(unverifiedCoreBonus.verify());
        }
        Database.unverifiedCoreBonuses = new UnverifiedCoreBonus[0];
        for (UnverifiedDataTag unverifiedDataTag :
            Database.unverifiedDataTags) {
            // TODO: figure out a way to get these to their respective objects
            unverifiedDataTag.verify();
        }
        Database.unverifiedDataTags = new UnverifiedDataTag[0];
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
        Database.lcpInfo = new LCPInfo[0];
        // Unverified data
        Database.unverifiedBackgrounds = new UnverifiedBackground[0];
        Database.unverifiedCoreBonuses = new UnverifiedCoreBonus[0];
        Database.unverifiedDataTags = new UnverifiedDataTag[0];
        // Verified data
        Database.actions = new Action[0];
        Database.activationTypes = new ActivationType[0];
        Database.backgrounds = new Background[0];
        Database.conditions = new StateData[0];
        Database.coreBonuses = new CoreBonus[0];
        Database.damageTypes = new DamageType[0];
        Database.frequencyTypes = new FrequencyType[0];
        Database.environments = new Environment[0];
        Database.frames = new Frame[0];
        Database.harmTypes = new HarmType[0];
        Database.iActionData = new IActionData[0];
        Database.iTagData = new ITagData[0];
        Database.iTagDataUnhidden = new ITagDataUnhidden[0];
        Database.manufacturers = new Manufacturer[0];
        Database.mechSystems = new MechSystem[0];
        Database.mechSystemTypes = new MechSystemType[0];
        Database.modifications = new Modification[0];
        Database.mountTypes = new MountType[0];
        Database.pilotArmor = new PilotArmor[0];
        Database.pilotGear = new PilotGear[0];
        Database.pilotWeapons = new PilotWeapon[0];
        Database.rangeTypes = new RangeType[0];
        Database.reserves = new ReserveData[0];
        Database.reserveTypes = new ReserveType[0];
        Database.rules = new Rule[0];
        Database.sitreps = new Sitrep[0];
        Database.skills = new SkillData[0];
        Database.statuses = new StateData[0];
        Database.synergyLocations = new SynergyLocation[0];
        Database.systemTypes = new SystemType[0];
        Database.tables = new Table[0];
        Database.talents = new TalentData[0];
        Database.terms = new Term[0];
        Database.weapons = new Weapon[0];
        Database.weaponSizes = new WeaponSize[0];
        Database.weaponTypes = new WeaponType[0];
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
    public static LCPInfo getLCPInfo(String lcpName) {
        HelperMethods.checkString("lcpName", lcpName);
        for (LCPInfo lcpInfoObject : Database.lcpInfo) {
            if (lcpInfoObject.getName().equals(lcpName)) {
                return lcpInfoObject;
            }
        }

        throw new NoSuchElementException("No LCP info found for LCP name: \""
            + lcpName + "\"");
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
    public static ActivationType getActivationType(String activationTypeValue) {
        HelperMethods.checkString("activationTypeValue",
            activationTypeValue);
        activationTypeValue = activationTypeValue.toLowerCase();
        for (ActivationType activationType : Database.activationTypes) {
            if (activationType.getValue().equals(activationTypeValue)) {
                return new ActivationType(activationTypeValue);
            }
        }
        throw new NoSuchElementException("No activation type found for"
            + " activation type value: " + activationTypeValue);
    }
    public static ActivationType getActivationTypeByIndex(int index) {
        if (index < 0 || index >= Database.activationTypes.length) {
            throw new IllegalArgumentException("index: " + index + " is out of"
                + " bounds for length: " + Database.activationTypes.length);
        }

        return Database.activationTypes[index];
    }
    public static Background getBackground(String backgroundID) {
        HelperMethods.checkObject("backgroundID", backgroundID);
        backgroundID = backgroundID.toLowerCase();
        for (Background background : Database.backgrounds) {
            if (backgroundID.equals(background.getID())) {
                return new Background(background);
            }
        }
        throw new NoSuchElementException("No background found for background"
            + " ID: " + backgroundID);
    }
    public static StateData getCondition(String conditionName) {
        HelperMethods.checkObject("conditionName", conditionName);
        for (StateData condition : Database.conditions) {
            if (conditionName.equals(condition.getName())) {
                return condition;
            }
        }
        throw new NoSuchElementException("No condition found for condition"
            + " name: " + conditionName);
    }
    public static CoreBonus getCoreBonus(String coreBonusID) {
        HelperMethods.checkObject("coreBonusID", coreBonusID);
        coreBonusID = coreBonusID.toLowerCase();
        for (CoreBonus coreBonus : Database.coreBonuses) {
            if (coreBonusID.equals(coreBonus.getID())) {
                return coreBonus;
            }
        }
        throw new NoSuchElementException("No core bonus found for core bonus"
            + " ID: " + coreBonusID);
    }
    public static DamageType getDamageType(String damageType) {
        HelperMethods.checkObject("damageType", damageType);
        damageType = damageType.toLowerCase();
        for (DamageType type : Database.damageTypes) {
            if (damageType.equals(type.getValue())) {
                return type;
            }
        }
        throw new NoSuchElementException("No data tag found for damage type"
            + " name: " + damageType);
    }
    public static ITagData getITagData(String iTagDataID) {
        HelperMethods.checkObject("iTagDataID", iTagDataID);
        iTagDataID = iTagDataID.toLowerCase();
        for (ITagData iTagData : Database.iTagData) {
            if (iTagDataID.equals(iTagData.getID())) {
                return iTagData;
            }
        }
        throw new NoSuchElementException("No ITagData object found for ITagData"
            + " ID: " + iTagDataID);
    }
    public static Environment getEnvironment(String environmentID) {
        HelperMethods.checkObject("environmentID", environmentID);
        environmentID = environmentID.toLowerCase();
        for (Environment environment : Database.environments) {
            if (environmentID.equals(environment.getID())) {
                return new Environment(environment);
            }
        }
        throw new NoSuchElementException("No environment found for"
            + " environment ID: " + environmentID);
    }
    public static FrequencyType getFrequencyType(String frequencyType) {
        HelperMethods.checkObject("frequencyType", frequencyType);
        for (FrequencyType type : Database.frequencyTypes) {
            if (frequencyType.equals(type.getValue())) {
                return type;
            }
        }
        throw new NoSuchElementException("No frequency type found for frequency"
            + " type: " + frequencyType);
    }
    public static FrequencyType getFrequencyTypeByRoot(String frequencyTypeRoot)
    {
        HelperMethods.checkObject("frequencyTypeRoot",
            frequencyTypeRoot);
        for (FrequencyType frequencyType : Database.frequencyTypes) {
            if (frequencyTypeRoot.equals(frequencyType.getRoot())) {
                return frequencyType;
            }
        }
        throw new NoSuchElementException("No frequency type found for frequency"
            + " type root: " + frequencyTypeRoot);
    }
    public static Frame getFrame(String frameID) {
        HelperMethods.checkObject("frameID", frameID);
        frameID = frameID.toLowerCase();
        for (Frame frame : Database.frames) {
            if (frameID.equals(frame.getID())) {
                return frame;
            }
        }
        throw new NoSuchElementException("No frame found for frame ID: "
            + frameID);
    }
    public static Frame getFrame(FrameEnum frameEnum) {
        HelperMethods.checkObject("frameEnum", frameEnum);
        for (Frame frame : Database.frames) {
            if (frameEnum.equals(frame.getFrameEnum())) {
                return frame;
            }
        }
        throw new NoSuchElementException("No frame found for frame enum: "
            + frameEnum);
    }
    public static HarmType getHarmType(String harmType) {
        HelperMethods.checkObject("harmType", harmType);
        harmType = harmType.toLowerCase();
        for (HarmType type : Database.harmTypes) {
            if (harmType.equals(type.getValue())) {
                return type;
            }
        }
        throw new NoSuchElementException("No data tag found for harm type"
            + " name: " + harmType);
    }
    public static IActionData getIActionData(String iActionDataName) {
        HelperMethods.checkObject("iActionDataName",
            iActionDataName);
        for (IActionData iActionDataObject : Database.iActionData) {
            if (iActionDataObject.getName().equals(iActionDataName)) {
                return new IActionData(iActionDataObject);
            }
        }
        throw new NoSuchElementException("No IActionData object found for"
            + " IActionData name: \"" + iActionDataName + "\"");
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
    public static MechSystem getMechSystem(String mechSystemID) {
        HelperMethods.checkObject("mechSystemID", mechSystemID);
        mechSystemID = mechSystemID.toLowerCase();
        for (MechSystem mechSystem : Database.mechSystems) {
            if (mechSystemID.equals(mechSystem.getID())) {
                return mechSystem;
            }
        }
        throw new NoSuchElementException("No mech system found for mech"
            + " system ID: " + mechSystemID);
    }
    public static MechSystem getMechSystemByName(String mechSystemName) {
        HelperMethods.checkObject("mechSystemName",
            mechSystemName);
        for (MechSystem mechSystem : Database.mechSystems) {
            if (mechSystemName.equals(mechSystem.getName())) {
                return mechSystem;
            }
        }
        throw new NoSuchElementException("No mech system found for mech"
            + " system name: " + mechSystemName);
    }
    public static Modification getModification(String modificationID) {
        HelperMethods.checkObject("modificationID",
            modificationID);
        modificationID = modificationID.toLowerCase();
        for (Modification modification : Database.modifications) {
            if (modificationID.equals(modification.getID())) {
                return modification;
            }
        }
        throw new NoSuchElementException("No modification found for"
            + " modification ID: " + modificationID);
    }
    public static MountType getMountType(String mountTypeName) {
        HelperMethods.checkObject("mountTypeName", mountTypeName);
        mountTypeName = mountTypeName.toLowerCase();
        for (MountType mountType : Database.mountTypes) {
            if (mountTypeName.equals(mountType.getName())) {
                return mountType;
            }
        }
        throw new NoSuchElementException("No mount type found for mount type"
            + " name: \"" + mountTypeName + "\"");
    }
    public static PilotArmor getPilotArmor(String pilotArmorID) {
        HelperMethods.checkObject("pilotArmorID", pilotArmorID);
        pilotArmorID = pilotArmorID.toLowerCase();
        for (PilotArmor pilotArmor : Database.pilotArmor) {
            if (pilotArmorID.equals(pilotArmor.getID())) {
                return pilotArmor;
            }
        }
        throw new NoSuchElementException("No pilot armor found for pilot"
            + " armor ID: " + pilotArmorID);
    }
    public static PilotGear getPilotGear(String pilotGearID) {
        HelperMethods.checkObject("pilotGearID", pilotGearID);
        pilotGearID = pilotGearID.toLowerCase();
        for (PilotGear pilotGear : Database.pilotGear) {
            if (pilotGearID.equals(pilotGear.getID())) {
                return pilotGear;
            }
        }
        throw new NoSuchElementException("No pilot gear found for pilot"
            + " gear ID: " + pilotGearID);
    }
    public static PilotWeapon getPilotWeapon(String pilotWeaponID) {
        HelperMethods.checkObject("pilotWeaponID", pilotWeaponID);
        pilotWeaponID = pilotWeaponID.toLowerCase();
        for (PilotWeapon pilotWeapon : Database.pilotWeapons) {
            if (pilotWeaponID.equals(pilotWeapon.getID())) {
                return pilotWeapon;
            }
        }
        throw new NoSuchElementException("No pilot weapon found for pilot"
            + " weapon ID: " + pilotWeaponID);
    }
    public static RangeType getRangeType(String rangeTypeName) {
        HelperMethods.checkObject("rangeTypeName", rangeTypeName);
        rangeTypeName = rangeTypeName.toLowerCase();
        for (RangeType rangeType : Database.rangeTypes) {
            if (rangeTypeName.equals(rangeType.getName())) {
                return rangeType;
            }
        }
        throw new NoSuchElementException("No range type found for range type"
            + " name: \"" + rangeTypeName + "\"");
    }
    public static ReserveData getReserve(String reserveID) {
        HelperMethods.checkObject("reserveID", reserveID);
        reserveID = reserveID.toLowerCase();
        for (ReserveData reserve : Database.reserves) {
            if (reserveID.equals(reserve.getID())) {
                return reserve;
            }
        }
        throw new NoSuchElementException("No reserve found for reserve ID: "
            + reserveID);
    }
    public static ReserveType getReserveType(String reserveTypeID) {
        HelperMethods.checkObject("reserveTypeID", reserveTypeID);
        reserveTypeID = reserveTypeID.toLowerCase();
        for (ReserveType reserve : Database.reserveTypes) {
            if (reserveTypeID.equals(reserve.getID())) {
                return reserve;
            }
        }
        throw new NoSuchElementException("No reserve type found for reserve"
            + " type ID: " + reserveTypeID);
    }
    public static Rule getRule(String ruleName) {
        HelperMethods.checkObject("ruleName", ruleName);
        ruleName = ruleName.toLowerCase();
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
        sitrepID = sitrepID.toLowerCase();
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
    public static StateData getState(String stateName) {
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
    public static StateData getStatus(String statusName) {
        HelperMethods.checkObject("statusName", statusName);
        for (StateData status : Database.statuses) {
            if (statusName.equals(status.getName())) {
                return status;
            }
        }
        throw new NoSuchElementException("No status found for status name: "
            + statusName);
    }
    public static SynergyLocation getSynergyLocation(
        String synergyLocationName) {
        HelperMethods.checkObject("synergyLocationName",
            synergyLocationName);
        for (SynergyLocation synergyLocation : Database.synergyLocations) {
            if (synergyLocationName.equals(synergyLocation.getValue())) {
                return synergyLocation;
            }
        }
        throw new NoSuchElementException("No synergy location found for synergy"
            + " location name: " + synergyLocationName);
    }
    public static SystemType getSystemType(String systemTypeCategory) {
        HelperMethods.checkObject("systemTypeCategory",
            systemTypeCategory);
        for (SystemType systemType : Database.systemTypes) {
            if (systemTypeCategory.equals(systemType.getCategory())) {
                return systemType;
            }
        }
        throw new NoSuchElementException("No system type found for system type"
            + " category: " + systemTypeCategory);
    }
    public static Table getTable(String tableName) {
        HelperMethods.checkObject("tableName", tableName);
        tableName = tableName.toLowerCase();
        for (Table table : Database.tables) {
            if (tableName.equals(table.getName())) {
                return new Table(table);
            }
        }
        throw new NoSuchElementException("No table found for table name: "
            + tableName);
    }
    public static ITagDataUnhidden getITagDataUnhidden(
        String iTagDataUnhiddenID) {
        HelperMethods.checkObject("iTagDataUnhiddenID",
            iTagDataUnhiddenID);
        iTagDataUnhiddenID = iTagDataUnhiddenID.toLowerCase();
        for (ITagDataUnhidden iTagDataUnhidden : Database.iTagDataUnhidden) {
            if (iTagDataUnhiddenID.equals(iTagDataUnhidden.getID())) {
                return iTagDataUnhidden;
            }
        }
        throw new NoSuchElementException("No ITagDataUnhidden object found for"
            + " ITagDataUnhidden ID: " + iTagDataUnhiddenID);
    }
    public static TalentData getTalent(String talentID) {
        HelperMethods.checkObject("talentID", talentID);
        talentID = talentID.toLowerCase();
        for (TalentData talent : Database.talents) {
            if (talentID.equals(talent.getID())) {
                return talent;
            }
        }
        throw new NoSuchElementException("No talent found for talent ID: "
            + talentID);
    }
    public static Term getTerm(String termName) {
        HelperMethods.checkObject("termName", termName);
        for (Term term : Database.terms) {
            if (termName.equals(term.getName())) {
                return term;
            }
        }
        throw new NoSuchElementException("No term found for term name: "
            + termName);
    }
    public static Weapon getWeapon(String weaponID) {
        HelperMethods.checkObject("weaponID", weaponID);
        weaponID = weaponID.toLowerCase();
        for (Weapon weapon : Database.weapons) {
            if (weaponID.equals(weapon.getID())) {
                return weapon;
            }
        }
        throw new NoSuchElementException("No weapon found for weapon ID: "
            + weaponID);
    }
    public static WeaponSize getWeaponSize(String weaponSizeName) {
        HelperMethods.checkObject("weaponSizeName",
            weaponSizeName);
        for (WeaponSize weaponSize : Database.weaponSizes) {
            if (weaponSizeName.equals(weaponSize.getName())) {
                return weaponSize;
            }
        }
        throw new NoSuchElementException("No weapon size found for weapon size"
            + " name: " + weaponSizeName);
    }
    public static WeaponType getWeaponType(String weaponTypeValue) {
        HelperMethods.checkObject("weaponTypeValue",
            weaponTypeValue);
        for (WeaponType weaponType : Database.weaponTypes) {
            if (weaponTypeValue.equals(weaponType.getValue())) {
                return weaponType;
            }
        }
        throw new NoSuchElementException("No weapon type found for weapon type"
            + " value: " + weaponTypeValue);
    }
    public static FrameLicense getFrameLicense(String frameLicenseID) {
        HelperMethods.checkObject("frameLicenseID",
            frameLicenseID);
        frameLicenseID = frameLicenseID.toLowerCase();
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
     * Adds the provided LCPInfo to Database.lcpInfo.
     * @param lcpInfo an LCPInfo which cannot be null.
     * @throws IllegalArgumentException if lcpInfo is null.
     */
    public static void addLCPInfo(LCPInfo lcpInfo) {
        checkOpen();
        HelperMethods.checkObject("lcpInfo", lcpInfo);
        lcpInfo = new LCPInfo(lcpInfo);
        Database.lcpInfo = HelperMethods.append(Database.lcpInfo, lcpInfo);
    }
    // Unverified data
    /**
     * Adds the provided UnverifiedBackground to Database.unverifiedBackgrounds.
     * @param unverifiedBackground an UnverifiedBackground which cannot be null.
     * @throws IllegalArgumentException if unverifiedBackground is null.
     */
    public static void addUnverifiedBackground(
        UnverifiedBackground unverifiedBackground) {
        checkOpen();
        HelperMethods.checkObject("unverifiedBackground",
            unverifiedBackground);
        unverifiedBackground = new UnverifiedBackground(unverifiedBackground);
        Database.unverifiedBackgrounds = HelperMethods.append(
            Database.unverifiedBackgrounds, unverifiedBackground);
    }
    /**
     * Adds the provided UnverifiedCoreBonus to Database.unverifiedCoreBonuses.
     * @param coreBonus an UnverifiedCoreBonus which cannot be null.
     * @throws IllegalArgumentException if coreBonus is null.
     */
    public static void addUnverifiedCoreBonus(
        UnverifiedCoreBonus unverifiedCoreBonus) {
        checkOpen();
        HelperMethods.checkObject("coreBonus",
            unverifiedCoreBonus);
        Database.unverifiedCoreBonuses = HelperMethods.append(
            Database.unverifiedCoreBonuses, unverifiedCoreBonus);
    }
    /**
     * Adds the provided DataTagUnverified to Database.unverifiedDataTags.
     * @param unverifiedDataTag an DataTagUnverified which cannot be null.
     * @throws IllegalArgumentException if unverifiedDataTag is null.
     */
    public static void addDataTagUnverified(UnverifiedDataTag unverifiedDataTag)
    {
        checkOpen();
        HelperMethods.checkObject("unverifiedDataTag",
            unverifiedDataTag);
        Database.unverifiedDataTags = HelperMethods.append(
            Database.unverifiedDataTags, unverifiedDataTag);
    }
    // Verified data
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
        Database.bonds = HelperMethods.append(Database.bonds, bond);
    }
    /**
     * Adds the provided StateData to Database.conditions.
     * @param condition a StateData which cannot be null.
     * @throws IllegalArgumentException if condition is null.
     */
    public static void addCondition(StateData condition) {
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
     * Adds the provided DamageType to Database.damageTypes.
     * @param damageType a DamageType which cannot be null.
     * @throws IllegalArgumentException if damageType is null.
     */
    public static void addDamageType(DamageType damageType) {
        checkOpen();
        HelperMethods.checkObject("damageType", damageType);
        Database.damageTypes = HelperMethods.append(Database.damageTypes,
            damageType);
        addHarmType(damageType);
    }
    /**
     * Adds the provided FrequencyType to Database.frequencyTypes.
     * @param frequencyType a FrequencyType which cannot be null.
     * @throws IllegalArgumentException if frequencyType is null.
     */
    public static void addFrequencyType(FrequencyType frequencyType) {
        checkOpen();
        HelperMethods.checkObject("frequencyType", frequencyType);
        Database.frequencyTypes = HelperMethods.append(Database.frequencyTypes,
            frequencyType);
    }
    /**
     * Adds the provided ITagData to Database.iTagData.
     * @param iTagData an ITagData which cannot be null.
     * @throws IllegalArgumentException if iTagData is null.
     */
    public static void addITagData(ITagData iTagData) {
        checkOpen();
        HelperMethods.checkObject("iTagData", iTagData);
        Database.iTagData = HelperMethods.append(Database.iTagData, iTagData);
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
        Database.frames = HelperMethods.append(Database.frames, frame);
    }
    /**
     * Adds the provided HarmType to Database.harmTypes.
     * @param harmType a HarmType which cannot be null.
     * @throws IllegalArgumentException if harmType is null.
     */
    public static void addHarmType(HarmType harmType) {
        checkOpen();
        HelperMethods.checkObject("harmType", harmType);
        Database.harmTypes = HelperMethods.append(Database.harmTypes, harmType);
    }
    /**
     * Adds the provided IActionData to Database.frames.
     * @param iActionData an IActionData which cannot be null.
     * @throws IllegalArgumentException if iActionData is null.
     */
    public static void addIActionData(IActionData iActionData) {
        checkOpen();
        HelperMethods.checkObject("iActionData", iActionData);
        iActionData = new IActionData(iActionData);
        Database.iActionData = HelperMethods.append(Database.iActionData,
            iActionData);
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
     * Adds the provided MechSystemType to Database.mechSystemTypes.
     * @param mechSystemType a MechSystemType which cannot be null.
     * @throws IllegalArgumentException if mechSystemType is null.
     */
    public static void addMechSystemType(MechSystemType mechSystemType) {
        checkOpen();
        HelperMethods.checkObject("mechSystemType",
            mechSystemType);
        Database.mechSystemTypes = HelperMethods.append(
            Database.mechSystemTypes, mechSystemType);
        addSystemType(mechSystemType);
    }
    /**
     * Adds the provided MountType to Database.mountTypes.
     * @param mountType a MountType which cannot be null.
     * @throws IllegalArgumentException if mountType is null.
     */
    public static void addMountType(MountType mountType) {
        checkOpen();
        HelperMethods.checkObject("mountType", mountType);
        Database.mountTypes = HelperMethods.append(Database.mountTypes,
            mountType);
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
     * Adds the provided PilotWeapon to Database.pilotWeapons.
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
     * Adds the provided RangeType to Database.rangeTypes.
     * @param rangeType a RangeType which cannot be null.
     * @throws IllegalArgumentException if rangeType is null.
     */
    public static void addRangeType(RangeType rangeType) {
        checkOpen();
        HelperMethods.checkObject("rangeType", rangeType);
        Database.rangeTypes = HelperMethods.append(Database.rangeTypes,
            rangeType);
    }
    /**
     * Adds the provided Reserve to Database.reserves.
     * @param reserve a Reserve which cannot be null.
     * @throws IllegalArgumentException if reserve is null.
     */
    public static void addReserve(ReserveData reserve) {
        checkOpen();
        HelperMethods.checkObject("reserve", reserve);
        Database.reserves = HelperMethods.append(Database.reserves, reserve);
    }
    /**
     * Adds the provided ReserveType to Database.reserveTypes.
     * @param reserveType a ReserveType which cannot be null.
     * @throws IllegalArgumentException if reserveType is null.
     */
    public static void addReserveType(ReserveType reserveType) {
        checkOpen();
        HelperMethods.checkObject("reserveType", reserveType);
        Database.reserveTypes = HelperMethods.append(Database.reserveTypes,
            reserveType);
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
     * Adds the provided StateData to Database.conditions or Database.statuses.
     * @param state a StateData which cannot be null.
     * @throws IllegalArgumentException if state is null.
     */
    public static void addState(StateData state) {
        checkOpen();
        HelperMethods.checkObject("state", state);
        if (state.isStatus()) {
            addStatus(state);
        } else {
            addCondition(state);
        }
    }
    /**
     * Adds the provided StateData to Database.statuses.
     * @param status a StateData which cannot be null.
     * @throws IllegalArgumentException if status is null.
     */
    public static void addStatus(StateData status) {
        checkOpen();
        HelperMethods.checkObject("status", status);
        Database.statuses = HelperMethods.append(Database.statuses, status);
    }
    /**
     * Adds the provided SynergyLocation to Database.synergyLocations.
     * @param synergyLocation a SynergyLocation which cannot be null.
     * @throws IllegalArgumentException if synergyLocation is null.
     */
    public static void addSynergyLocation(SynergyLocation synergyLocation) {
        checkOpen();
        HelperMethods.checkObject("synergyLocation",
            synergyLocation);
        Database.synergyLocations =
            HelperMethods.append(Database.synergyLocations, synergyLocation);
    }
    /**
     * Adds the provided SystemType to Database.systemTypes.
     * @param systemType a SystemType which cannot be null.
     * @throws IllegalArgumentException if systemType is null.
     */
    public static void addSystemType(SystemType systemType) {
        checkOpen();
        HelperMethods.checkObject("systemType", systemType);
        Database.systemTypes = HelperMethods.append(Database.systemTypes,
            systemType);
    }
    /**
     * Adds the provided MechSystem to Database.mechSystems.
     * @param mechSystem a MechSystem which cannot be null.
     * @throws IllegalArgumentException if mechSystem is null.
     */
    public static void addSystem(MechSystem mechSystem) {
        checkOpen();
        HelperMethods.checkObject("mechSystem", mechSystem);
        Database.mechSystems = HelperMethods.append(
            Database.mechSystems, mechSystem
        );
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
     * Adds the provided ITagDataUnhidden to Database.iTagDataUnhidden.
     * @param iTagDataUnhidden an ITagDataUnhidden which cannot be null.
     * @throws IllegalArgumentException if iTagDataUnhidden is null.
     */
    public static void addITagDataUnhidden(ITagDataUnhidden iTagDataUnhidden) {
        checkOpen();
        HelperMethods.checkObject("iTagDataUnhidden",
            iTagDataUnhidden);
        Database.iTagDataUnhidden =
            HelperMethods.append(Database.iTagDataUnhidden, iTagDataUnhidden);
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
    /**
     * Adds the provided WeaponSize to Database.weaponSizes.
     * @param weaponSize a WeaponSize which cannot be null.
     * @throws IllegalArgumentException if weaponSize is null.
     */
    public static void addWeaponSize(WeaponSize weaponSize) {
        checkOpen();
        HelperMethods.checkObject("weaponSize", weaponSize);
        Database.weaponSizes = HelperMethods.append(Database.weaponSizes,
            weaponSize);
    }
    /**
     * Adds the provided WeaponType to Database.weaponTypes.
     * @param weaponType a WeaponType which cannot be null.
     * @throws IllegalArgumentException if weaponType is null.
     */
    public static void addWeaponType(WeaponType weaponType) {
        checkOpen();
        HelperMethods.checkObject("weaponType", weaponType);
        Database.weaponTypes = HelperMethods.append(Database.weaponTypes,
            weaponType);
        addSystemType(weaponType);
    }
    public static void addFrameLicense(FrameLicense frameLicense) {
        checkOpen();
        HelperMethods.checkObject("frameLicense", frameLicense);
        frameLicense = new FrameLicense(frameLicense);
        Database.frameLicenses = HelperMethods.append(Database.frameLicenses,
            frameLicense);
    }
}
