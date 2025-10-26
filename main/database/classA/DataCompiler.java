package main.database.classA;

import main.Database;
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

public class DataCompiler {
    // some kind of method for every possible type of data, each with a method
    //     signature that includes a JSONObject parameter
    // some kind of add() or save() method for Class B to call once all
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
    private static boolean open = false;
    private static boolean hasData = false;
    // all the data being held at the moment
    // ----some critical data types:
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
    private DataCompiler() {}

    public static void open() { // could also be named add()
        if (DataCompiler.open) {
            if (DataCompiler.hasData) {
                // means the user had already added some data but didn't upload
                //     it
                // throw an exception if you hate your user
                // throw new IllegalStateException("Attempted to call"
                //     + " DataCompiler.open() when DataCompiler is already open."
                //     + " Either close DataCompiler or flush the data before"
                //     + " closing it");
            } else {
                // means the user just opened DataCompiler twice by accident lol
                // throw an exception if you REALLY hate your user
                // throw new IllegalStateException("Attempted to call"
                //     + " DataCompiler.open() when DataCompiler is already open");
            }
            close();
        } else if (DataCompiler.hasData) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.open() when DataCompiler still has some data"
                + " remaining. Call DataCompiler.uploadData() or"
                + " DataCompiler.flushData() first");
        }
        DataCompiler.open = true;
    }
    public static void close() { // could also be named add() or save()
        if (! DataCompiler.open) {
            // throw an exception if you hate your user
            // throw new IllegalArgumentException("Attempted to call"
            //     + " DataCompiler.close() when DataCompiler is already closed");
        }
        DataCompiler.open = false;
    }
    public static void uploadData() { // TODO: find a better name
        if (DataCompiler.open) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.uploadData() while DataCompiler was still"
                + " open. Call DataCompiler.close() first");
        }
        // push all the data that's been collected over to Database
        compileLicenseContent();
        compileLicenses();
        addLicenses();
        // then flush it
        flushData();
    }
    public static void flushData() {
        // delete all the data that's been collected
        // ----some critical data types:
        DataCompiler.frameLicenseData = new FrameLicense[0];
        DataCompiler.licenseContentData = new LicenseContent[0];
        DataCompiler.frameData = new Frame[0];
        DataCompiler.systemData = new MechSystem[0];
        DataCompiler.modificationData = new Modification[0];
        DataCompiler.weaponData = new Weapon[0];
        // ----the rest of the critical data types:
        DataCompiler.actionData = new Action[0];
        DataCompiler.conditionData = new Condition[0];
        DataCompiler.dataTagData = new DataTag[0];
        DataCompiler.tagData = new Tag[0];
        DataCompiler.manufacturerData = new Manufacturer[0];
        DataCompiler.npcFeatureData = new NPCFeature[0];
        DataCompiler.npcTemplateData = new NPCTemplate[0];
        DataCompiler.pilotArmorData = new PilotArmor[0];
        DataCompiler.pilotGearData = new PilotGear[0];
        DataCompiler.pilotWeapon = new PilotWeapon[0];
        DataCompiler.reserveData = new Reserve[0];
        DataCompiler.skillData = new Skill[0];
        DataCompiler.statusData = new Status[0];
        DataCompiler.talentData = new Talent[0];
        // ----less important
        DataCompiler.environmentData = new Environment[0];
        DataCompiler.sitrepData = new Sitrep[0];
        // ----almost unimportant
        DataCompiler.backgroundData = new Background[0];
        DataCompiler.bondData = new Bond[0];
        // ----just for reference
        DataCompiler.ruleData = new Rule[0];
        DataCompiler.termData = new Term[0];
        DataCompiler.tableData = new Table[0];
        DataCompiler.hasData = false;
        // TODO: remove?
        System.out.println("DATA FLUSHED SUCCESSFULLY");
    }
    public static void receiveData(Object[] data) {
        // TODO: fill out
        if (! DataCompiler.open) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.receiveData() while DataCompiler was still"
                + " closed. Call DataCompiler.open() first");
        }
        // ----some critical data types:
        DataCompiler.frameData = (Frame[]) data[0];
        DataCompiler.systemData = (MechSystem[]) data[1];
        DataCompiler.modificationData = (Modification[]) data[1];
        DataCompiler.weaponData = (Weapon[]) data[2];
        // ----the rest of the critical data types:
        DataCompiler.actionData = (Action[]) data[3];
        DataCompiler.conditionData = (Condition[]) data[4];
        DataCompiler.dataTagData = (DataTag[]) data[5];
        DataCompiler.tagData = (Tag[]) data[6];
        DataCompiler.manufacturerData = (Manufacturer[]) data[7];
        DataCompiler.npcFeatureData = (NPCFeature[]) data[8];
        DataCompiler.npcTemplateData = (NPCTemplate[]) data[9];
        DataCompiler.pilotArmorData = (PilotArmor[]) data[10];
        DataCompiler.pilotGearData = (PilotGear[]) data[11];
        DataCompiler.pilotWeapon = (PilotWeapon[]) data[12];
        DataCompiler.reserveData = (Reserve[]) data[13];
        DataCompiler.skillData = (Skill[]) data[14];
        DataCompiler.statusData = (Status[]) data[15];
        DataCompiler.talentData = (Talent[]) data[16];
        // ----less important
        DataCompiler.environmentData = (Environment[]) data[16];
        DataCompiler.sitrepData = (Sitrep[]) data[17];
        // ----almost unimportant
        DataCompiler.backgroundData = (Background[]) data[18];
        DataCompiler.bondData = (Bond[]) data[19];
        // ----just for reference
        DataCompiler.ruleData = (Rule[]) data[20];
        DataCompiler.termData = (Term[]) data[21];
        DataCompiler.tableData = (Table[]) data[22];
        // remember to mark that we have data now
        DataCompiler.hasData = true;
    }
    private static void compileLicenseContent() {
        // compile all the LicenseContent you have
        LicenseContent[] licenseContent;
        LicenseContent[] newLicenseContent;
        int numEquipment = DataCompiler.frameData.length
            + DataCompiler.systemData.length
            + DataCompiler.modificationData.length
            + DataCompiler.weaponData.length;
        int numValid = 0;

        licenseContent = new LicenseContent[numEquipment];
        newLicenseContent = new LicenseContent[numEquipment];
        numEquipment = DataCompiler.frameData.length;
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
        DataCompiler.licenseContentData = newLicenseContent;
    }
    private static void compileLicenses() {
        // TODO: fill out
        // take the LicenseContent that you have and organize them into
        //     discrete licenses
        DataCompiler.frameLicenseData = DataCompiler.licenseContentData;
    }
    private static void addLicenses() {
        for (int i = 0; i < DataCompiler.frameLicenseData.length; i++) {
            Database.addLicense(DataCompiler.frameLicenseData[i]);
        }
    }
}