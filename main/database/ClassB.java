package main.database;

import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.License;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Modification;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Weapon;
import packages.coreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Background;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Bond;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotArmor;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotGear;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Reserve;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.licenseSystem.FrameLicense;
import packages.coreTypes.entityMechanics.licenseSystem.frameLicense.LicenseContent;
import packages.coreTypes.entityMechanics.Manufacturer;
import packages.coreTypes.entityMechanics.NPCFeature;
import packages.coreTypes.entityMechanics.NPCTemplate;
import packages.coreTypes.entityMechanics.stateSystem.state.Condition;
import packages.coreTypes.entityMechanics.stateSystem.state.Status;
import packages.coreTypes.Environment;
import packages.coreTypes.Rule;
import packages.coreTypes.Sitrep;
import packages.coreTypes.Table;
import packages.coreTypes.Term;

public class ClassB {
    // some kind of method for every possible type of data, each with a method
    //     signature that includes a JSONObject parameter
    // some kind of add() or save() method for Class A to call once all
    //     JSONObjects are loaded
    // compile all JSONObjects into licenses and collections of data, for
    //     non-license content, and add everything to Database
    // deal with individual items and non-license stuff
    // maybe add some way of REMOVING licenses or items
    // =========================================================================
    // addLicense which adds:
    //     FrameLicense (duh)
    //         Frame
    //         <Equipment>
    //             MechSystem
    //             Modification
    //             Weapon
    // ----plus individual mutators for those types:
    // addFrame which adds Frame
    // addMechSystem which adds MechSystem
    // addModification which adds Modification
    // addWeapon which adds Weapon
    // ----the rest of the critical data types:
    // addAction which adds Action
    // addCondition which adds Condition
    // addDataTag which adds DataTag and thereby Tag
    // addManufacturer which adds Manufacturer
    // addNPCFeature which adds NPCFeature
    // addPilotArmor which adds PilotArmor
    // addPilotGear which adds PilotGear
    // addPilotWeapon which adds PilotWeapon
    // addReserve which adds Reserve
    // addSkill which adds Skill
    // addStatus which adds Status
    // addTalent which adds Talent
    // ----less important
    // addEnvironment which adds Environment
    // addSitrep which adds Sitrep
    // ----almost unimportant
    // addBackground which adds Background
    // addBond which adds Bond
    // ----just for reference
    // addRule which adds Rule
    // addTerm which adds Term
    // addTable which adds Table
    private static boolean open;
    private static boolean hasData;
    // all the data being held at the moment
    private static FrameLicense[] frameLicenseData = new FrameLicense[0];
    private static LicenseContent[] licenseContentData = new LicenseContent[0];
    private static Frame[] frameData = new Frame[0];
    private static MechSystem[] systemData = new MechSystem[0];
    private static Modification[] modificationData = new Modification[0];
    private static Weapon[] weaponData = new Weapon[0];
    // ----the rest of the critical data types:
    private static Action[] actionData = new Action[0];
    private static Condition[] conditionData = new Condition[0];
    private static DataTag[] dataTagData = new DataTag[0];
    private static Tag[] tagData = new Tag[0];
    private static Manufacturer[] manufacturerData = new Manufacturer[0];
    private static NPCFeature[] npcFeatureData = new NPCFeature[0];
    private static NPCTemplate[] npcTemplateData = new NPCTemplate[0];
    private static PilotArmor[] pilotArmorData = new PilotArmor[0];
    private static PilotGear[] pilotGearData = new PilotGear[0];
    private static PilotWeapon[] pilotWeapon = new PilotWeapon[0];
    private static Reserve[] reserveData = new Reserve[0];
    private static Skill[] skillData = new Skill[0];
    private static Status[] statusData = new Status[0];
    private static Talent[] talentData = new Talent[0];
    // ----less important
    private static Environment[] environmentData = new Environment[0];
    private static Sitrep[] sitrepData = new Sitrep[0];
    // ----almost unimportant
    private static Background[] backgroundData = new Background[0];
    private static Bond[] bondData = new Bond[0];
    // ----just for reference
    private static Rule[] ruleData = new Rule[0];
    private static Term[] termData = new Term[0];
    private static Table[] tableData = new Table[0];

    // Prevent user from instantiating this class
    private ClassB() {}

    public static void open() { // could also be named add()
        if (ClassB.open) {
            if (ClassB.hasData) {
                // means the user had already added some data but didn't upload
                //     it
                // throw an exception if you hate your user
                // throw new IllegalArgumentException("Attempted to call"
                //     + " ClassB.open() when ClassB is already open. Either close"
                //     + " ClassB or flush the data before closing it");
            } else {
                // means the user just opened ClassB twice by accident lol
                // throw an exception if you REALLY hate your user
                // throw new IllegalArgumentException("Attempted to call"
                //     + " ClassB.open() when ClassB is already open");
            }
            close();
        }
        ClassB.open = true;
    }
    public static void close() { // could also be named add() or save()
        if (! ClassB.open) {
            // throw an exception if you hate your user
            // throw new IllegalArgumentException("Attempted to call"
            //     + " ClassB.close() when ClassB is already closed");
        }
        ClassB.open = false;
        uploadData();
    }
    private static void uploadData() { // TODO: find a better name
        // push all the data that's been collected over to Database
        compileLicenseContent();
        compileLicenses();
        addLicenses();
        // then flush it
        flushData();
    }
    public static void flushData() {
        // delete all the data that's been collected
        // ----plus individual mutators for those types:
        ClassB.frameLicenseData = new FrameLicense[0];
        ClassB.licenseContentData = new LicenseContent[0];
        ClassB.frameData = new Frame[0];
        ClassB.systemData = new MechSystem[0];
        ClassB.modificationData = new Modification[0];
        ClassB.weaponData = new Weapon[0];
        // ----the rest of the critical data types:
        ClassB.actionData = new Action[0];
        ClassB.conditionData = new Condition[0];
        ClassB.dataTagData = new DataTag[0];
        ClassB.tagData = new Tag[0];
        ClassB.manufacturerData = new Manufacturer[0];
        ClassB.npcFeatureData = new NPCFeature[0];
        ClassB.npcTemplateData = new NPCTemplate[0];
        ClassB.pilotArmorData = new PilotArmor[0];
        ClassB.pilotGearData = new PilotGear[0];
        ClassB.pilotWeapon = new PilotWeapon[0];
        ClassB.reserveData = new Reserve[0];
        ClassB.skillData = new Skill[0];
        ClassB.statusData = new Status[0];
        ClassB.talentData = new Talent[0];
        // ----less important
        ClassB.environmentData = new Environment[0];
        ClassB.sitrepData = new Sitrep[0];
        // ----almost unimportant
        ClassB.backgroundData = new Background[0];
        ClassB.bondData = new Bond[0];
        // ----just for reference
        ClassB.ruleData = Rule[0];
        ClassB.termData = new Term[0];
        ClassB.tableData = new Table[0];
        ClassB.hasData = false;
    }
    private static void compileLicenseContent() {
        // compile all the LicenseContent you have
        LicenseContent[] licenseContent;
        LicenseContent[] newLicenseContent;
        int numEquipment = ClassB.frameData.length + ClassB.systemData.length
            + ClassB.modificationData.length + ClassB.weaponData.length;
        int numValid = 0;

        licenseContent = new LicenseContent[numEquipment];
        newLicenseContent = new LicenseContent[numEquipment];
        numEquipment = ClassB.frameData.length;
        for (int i = 0; i < numEquipment; i++) {
            if (licenseContent[i].hasLicense()) {
                newLicenseContent[i] = licenseContent[i];
                numValid++;
            }
        }
        licenseContent = newLicenseContent;
        newLicenseContent = new LicenseContent[numValid];
        numValid = 0;
        for (int i = 0; i < numEquipment; i++) {
            if (licenseContent[i] == null) {
                continue;
            }
            newLicenseContent[numValid] = licenseContent[i];
            numValid++;
        }
        ClassB.licenseContentData = newLicenseContent;
    }
    private static void compileLicenses() {
        // TODO: fill out
        // take the LicenseContent that you have and organize them into
        //     discrete licenses
        ClassB.frameLicenseData = newLicenseContent;
    }
    private static void addLicenses() {
        for (int i = 0; i < ClassB.frameLicenseData.length; i++) {
            Database.addLicense(ClassB.frameLicenseData[i]);
        }
    }
}