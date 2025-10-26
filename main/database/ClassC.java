package main.database;

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

public class ClassC {
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
    private ClassC() {}

    public static void open() { // could also be named add()
        if (ClassC.open) {
            if (ClassC.hasData) {
                // means the user had already added some data but didn't upload
                //     it
                // throw an exception if you hate your user
                // throw new IllegalStateException("Attempted to call"
                //     + " ClassC.open() when ClassC is already open. Either close"
                //     + " ClassC or flush the data before closing it");
            } else {
                // means the user just opened ClassC twice by accident lol
                // throw an exception if you REALLY hate your user
                // throw new IllegalStateException("Attempted to call"
                //     + " ClassC.open() when ClassC is already open");
            }
            close();
        } else if (ClassC.hasData) {
            throw new IllegalStateException("Attempted to call ClassC.open()"
                + " when ClassC still has some data remaining. Call"
                + " ClassC.uploadData() or ClassC.flushData() first");
        }
        ClassC.open = true;
    }
    public static void close() { // could also be named add() or save()
        if (! ClassC.open) {
            // throw an exception if you hate your user
            // throw new IllegalArgumentException("Attempted to call"
            //     + " ClassC.close() when ClassC is already closed");
        }
        ClassC.open = false;
    }
    public static void uploadData() { // TODO: find a better name
        if (ClassC.open) {
            throw new IllegalStateException("Attempted to call"
                + " ClassC.uploadData() while ClassC was still open. Call"
                + " ClassC.close() first");
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
        ClassC.frameLicenseData = new FrameLicense[0];
        ClassC.licenseContentData = new LicenseContent[0];
        ClassC.frameData = new Frame[0];
        ClassC.systemData = new MechSystem[0];
        ClassC.modificationData = new Modification[0];
        ClassC.weaponData = new Weapon[0];
        // ----the rest of the critical data types:
        ClassC.actionData = new Action[0];
        ClassC.conditionData = new Condition[0];
        ClassC.dataTagData = new DataTag[0];
        ClassC.tagData = new Tag[0];
        ClassC.manufacturerData = new Manufacturer[0];
        ClassC.npcFeatureData = new NPCFeature[0];
        ClassC.npcTemplateData = new NPCTemplate[0];
        ClassC.pilotArmorData = new PilotArmor[0];
        ClassC.pilotGearData = new PilotGear[0];
        ClassC.pilotWeapon = new PilotWeapon[0];
        ClassC.reserveData = new Reserve[0];
        ClassC.skillData = new Skill[0];
        ClassC.statusData = new Status[0];
        ClassC.talentData = new Talent[0];
        // ----less important
        ClassC.environmentData = new Environment[0];
        ClassC.sitrepData = new Sitrep[0];
        // ----almost unimportant
        ClassC.backgroundData = new Background[0];
        ClassC.bondData = new Bond[0];
        // ----just for reference
        ClassC.ruleData = new Rule[0];
        ClassC.termData = new Term[0];
        ClassC.tableData = new Table[0];
        ClassC.hasData = false;
        // TODO: remove?
        System.out.println("DATA FLUSHED SUCCESSFULLY");
    }
    public static void receiveData(Object[] data) {
        // TODO: fill out
        if (! ClassC.open) {
            throw new IllegalStateException("Attempted to call"
                + " ClassC.receiveData() while ClassC was still closed. Call"
                + " ClassC.open() first");
        }
        // ----some critical data types:
        ClassC.frameData = (Frame[]) data[0];
        ClassC.systemData = (MechSystem[]) data[1];
        ClassC.modificationData = (Modification[]) data[1];
        ClassC.weaponData = (Weapon[]) data[2];
        // ----the rest of the critical data types:
        ClassC.actionData = (Action[]) data[3];
        ClassC.conditionData = (Condition[]) data[4];
        ClassC.dataTagData = (DataTag[]) data[5];
        ClassC.tagData = (Tag[]) data[6];
        ClassC.manufacturerData = (Manufacturer[]) data[7];
        ClassC.npcFeatureData = (NPCFeature[]) data[8];
        ClassC.npcTemplateData = (NPCTemplate[]) data[9];
        ClassC.pilotArmorData = (PilotArmor[]) data[10];
        ClassC.pilotGearData = (PilotGear[]) data[11];
        ClassC.pilotWeapon = (PilotWeapon[]) data[12];
        ClassC.reserveData = (Reserve[]) data[13];
        ClassC.skillData = (Skill[]) data[14];
        ClassC.statusData = (Status[]) data[15];
        ClassC.talentData = (Talent[]) data[16];
        // ----less important
        ClassC.environmentData = (Environment[]) data[16];
        ClassC.sitrepData = (Sitrep[]) data[17];
        // ----almost unimportant
        ClassC.backgroundData = (Background[]) data[18];
        ClassC.bondData = (Bond[]) data[19];
        // ----just for reference
        ClassC.ruleData = (Rule[]) data[20];
        ClassC.termData = (Term[]) data[21];
        ClassC.tableData = (Table[]) data[22];
        // remember to mark that we have data now
        ClassC.hasData = true;
    }
    private static void compileLicenseContent() {
        // compile all the LicenseContent you have
        LicenseContent[] licenseContent;
        LicenseContent[] newLicenseContent;
        int numEquipment = ClassC.frameData.length + ClassC.systemData.length
            + ClassC.modificationData.length + ClassC.weaponData.length;
        int numValid = 0;

        licenseContent = new LicenseContent[numEquipment];
        newLicenseContent = new LicenseContent[numEquipment];
        numEquipment = ClassC.frameData.length;
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
        ClassC.licenseContentData = newLicenseContent;
    }
    private static void compileLicenses() {
        // TODO: fill out
        // take the LicenseContent that you have and organize them into
        //     discrete licenses
        ClassC.frameLicenseData = ClassC.licenseContentData;
    }
    private static void addLicenses() {
        for (int i = 0; i < ClassC.frameLicenseData.length; i++) {
            Database.addLicense(ClassC.frameLicenseData[i]);
        }
    }
}