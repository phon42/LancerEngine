package MainBranch.database.DatabaseReadingPipeline;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Modification;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.dataTag.Tag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.UnverifiedBackground;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.FrameLicense;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.LicenseContent;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.StateData;
import Packages.CoreTypes.LCPInfo;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;

public class DataCompiler {
    // some kind of method for every possible type of data, each with a method
    //     signature that includes a JSONObject parameter
    // some kind of add() or save() method for DataCaster to call once all
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
    // ----absolutely critical data:
    private static LCPInfo[] lcpInfoData;
    // ----some critical data types:
    private static FrameLicense[] frameLicenseData;
    private static LicenseContent[] licenseContentData;
    private static Frame[] frameData;
    private static MechSystem[] systemData;
    private static Modification[] modificationData;
    private static Weapon[] weaponData;
    // ----the rest of the critical data types:
    private static Action[] actionData;
    private static IActionData[] iActionDataData;
    private static ActivationType[] activationTypeData;
    private static StateData[] conditionData;
    private static CoreBonus[] coreBonusData;
    private static DataTag[] dataTagData;
    private static Tag[] tagData;
    private static Manufacturer[] manufacturerData;
    private static NPCFeature[] npcFeatureData;
    private static NPCTemplate[] npcTemplateData;
    private static PilotArmor[] pilotArmorData;
    private static PilotGear[] pilotGearData;
    private static PilotWeapon[] pilotWeapon;
    private static Reserve[] reserveData;
    private static SkillData[] skillData;
    private static StateData[] statusData;
    private static TalentData[] talentData;
    // ----less important
    private static Environment[] environmentData;
    private static Sitrep[] sitrepData;
    // ----almost unimportant
    private static UnverifiedBackground[] backgroundData;
    private static Bond[] bondData;
    // ----just for reference
    private static Rule[] ruleData;
    private static Term[] termData;
    private static Table[] tableData;

    static {
        flushData(true);
    }

    // Prevent user from instantiating this class
    private DataCompiler() {}

    public static void open() { // could also be named add()
        if (DataCompiler.open) {
            if (DataCompiler.hasData) {
                // means the user had already added some data but didn't upload
                //     it
                // throw an exception if you hate your user, otherwise
                //     just call close()
                throw new IllegalStateException("Attempted to call"
                    + " DataCompiler.open() when DataCompiler is already open."
                    + " Either close DataCompiler or flush the data before"
                    + " closing it");
            } else {
                // means the user just opened DataCompiler twice by accident lol
                // throw an exception if you REALLY hate your user, otherwise
                //     just call close()
                throw new IllegalStateException("Attempted to call"
                    + " DataCompiler.open() when DataCompiler is already open");
            }
        } else if (DataCompiler.hasData) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.open() when DataCompiler still has some data"
                + " remaining. Call DataCompiler.saveData() or"
                + " DataCompiler.flushData() first");
        }
        DataCompiler.open = true;
    }
    public static void close() { // could also be named add() or save()
        if (! DataCompiler.open) {
            // user called close() when DataCompiler was already closed
            // throw an exception if you hate your user
            throw new IllegalArgumentException("Attempted to call"
                + " DataCompiler.close() when DataCompiler is already closed");
        }
        DataCompiler.open = false;
    }
    public static void receiveData(Object[] data) {
        if (! DataCompiler.open) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.receiveData() while DataCompiler was still"
                + " closed. Call DataCompiler.open() first");
        }
        unpackData(data);
        // remember to mark that we have data now
        DataCompiler.hasData = true;
    }
    public static void unpackData(Object[] data) {
        // ----absolutely critical data:
        DataCompiler.lcpInfoData = (LCPInfo[]) data[0];
        // ----some critical data types:
        DataCompiler.frameData = (Frame[]) data[1];
        DataCompiler.systemData = (MechSystem[]) data[2];
        DataCompiler.modificationData = (Modification[]) data[3];
        DataCompiler.weaponData = (Weapon[]) data[4];
        // ----the rest of the critical data types:
        DataCompiler.actionData = (Action[]) data[5];
        DataCompiler.activationTypeData = (ActivationType[]) data[6];
        DataCompiler.conditionData = (StateData[]) data[7];
        DataCompiler.coreBonusData = (CoreBonus[]) data[8];
        DataCompiler.dataTagData = (DataTag[]) data[9];
        DataCompiler.tagData = (Tag[]) data[10];
        DataCompiler.manufacturerData = (Manufacturer[]) data[11];
        DataCompiler.npcFeatureData = (NPCFeature[]) data[12];
        DataCompiler.npcTemplateData = (NPCTemplate[]) data[13];
        DataCompiler.pilotArmorData = (PilotArmor[]) data[14];
        DataCompiler.pilotGearData = (PilotGear[]) data[15];
        DataCompiler.pilotWeapon = (PilotWeapon[]) data[16];
        DataCompiler.reserveData = (Reserve[]) data[17];
        DataCompiler.skillData = (SkillData[]) data[18];
        DataCompiler.statusData = (StateData[]) data[19];
        DataCompiler.talentData = (TalentData[]) data[20];
        // ----less important
        DataCompiler.environmentData = (Environment[]) data[21];
        DataCompiler.sitrepData = (Sitrep[]) data[22];
        // ----almost unimportant
        DataCompiler.backgroundData = (UnverifiedBackground[]) data[23];
        DataCompiler.bondData = (Bond[]) data[24];
        // ----just for reference
        DataCompiler.ruleData = (Rule[]) data[25];
        DataCompiler.termData = (Term[]) data[26];
        DataCompiler.tableData = (Table[]) data[27];
    }
    public static void saveData() {
        if (DataCompiler.open) {
            throw new IllegalStateException("Attempted to call"
                + " DataCompiler.saveData() while DataCompiler was still"
                + " open. Call DataCompiler.close() first");
        }
        // extract any data that's squirreled away inside other classes
        extractContent();
        // collect the data together
        compileContent();
        // push all the data that's been collected over to Database
        addContent();
        // then flush it
        flushData();
    }
    private static void extractContent() {
        // TODO: extract IActionData from core bonuses, mech systems, etc etc
    }
    private static void compileContent() {
        compileLicenseContent();
        compileFrameLicenses();
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
            if (licenseContent[i] != null) {
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
    private static void compileFrameLicenses() {
        // TODO: fill out
        // take the LicenseContent that you have and organize them into
        //     discrete licenses
        FrameLicense[] frameLicenses =
            new FrameLicense[licenseContentData.length];
        LicenseContent[] content = DataCompiler.licenseContentData;
        // Whether the license that the current LicenseContent element belongs
        //     to is already in frameLicenses
        FrameLicense frameLicense;
        int numLicenses = 0;
        boolean isGMS = false;
        boolean licenseFound = false;

        for (int i = 0; i < content.length; i++) {
            if (content[i] == null) {
                continue;
            }
            isGMS = content[i].getLicense() == null;
            licenseFound = false;
            for (int j = 0; j < frameLicenses.length; j++) {
                frameLicense = frameLicenses[j];
                if (isGMS) {
                    // GMS content
                    licenseFound = frameLicense.getManufacturer().getID()
                        .equals("GMS")
                        && frameLicense.getName()
                        .equals("gms content");
                } else {
                    // non-GMS content
                    licenseFound = frameLicense.getManufacturer()
                        .equals(content[i].getManufacturer())
                        && frameLicense.getName()
                        .equals(content[i].getLicense());
                }
                if (licenseFound) {
                    frameLicense.addContent(content[i]);
                    break;
                }
                if (j == frameLicenses.length - 1) {
                    if (isGMS) {
                        // GMS content
                        frameLicenses[numLicenses] =
                            new FrameLicense(
                                "gms",
                                "gms content",
                                Database.getManufacturer("GMS")
                            );
                    } else {
                        // non-GMS content
                        frameLicenses[numLicenses] = new FrameLicense(
                            content[i].getLicenseID(), content[i].getLicense(),
                            content[i].getManufacturer());
                    }
                    numLicenses++;
                }
            }
        }
        DataCompiler.frameLicenseData = frameLicenses;
    }
    private static void addContent() {
        // first we have to open Database again, this time because we're adding
        //     content
        Database.open();
        addLCPInfo();
        addFrameLicenses();
        addNonLicenseContent();
        // then we close it afterward
        Database.close();
    }
    private static void addLCPInfo() {
        for (int i = 0; i < DataCompiler.lcpInfoData.length; i++) {
            Database.addLCPInfo(DataCompiler.lcpInfoData[i]);
        }
    }
    private static void addFrameLicenses() {
        for (int i = 0; i < DataCompiler.frameLicenseData.length; i++) {
            Database.addFrameLicense(DataCompiler.frameLicenseData[i]);
        }
    }
    private static void addNonLicenseContent() {
        // add anything that wasn't added in a batch through addLicenses
        // in other words, anything except the contents of frameLicenseData,
        //     licenseContentData, and all data types contained therein
        // ----the rest of the critical data types:
        for (int i = 0; i < DataCompiler.actionData.length; i++) {
            Database.addAction(DataCompiler.actionData[i]);
        }
        for (int i = 0; i < DataCompiler.iActionDataData.length; i++) {
            Database.addIActionData(DataCompiler.iActionDataData[i]);
        }
        for (int i = 0; i < DataCompiler.activationTypeData.length; i++) {
            Database.addActivationType(DataCompiler.activationTypeData[i]);
        }
        for (int i = 0; i < DataCompiler.conditionData.length; i++) {
            Database.addCondition(DataCompiler.conditionData[i]);
        }
        for (int i = 0; i < DataCompiler.coreBonusData.length; i++) {
            Database.addCoreBonus(DataCompiler.coreBonusData[i]);
        }
        for (int i = 0; i < DataCompiler.dataTagData.length; i++) {
            Database.addDataTag(DataCompiler.dataTagData[i]);
        }
        for (int i = 0; i < DataCompiler.tagData.length; i++) {
            Database.addTag(DataCompiler.tagData[i]);
        }
        for (int i = 0; i < DataCompiler.manufacturerData.length; i++) {
            Database.addManufacturer(DataCompiler.manufacturerData[i]);
        }
        for (int i = 0; i < DataCompiler.npcFeatureData.length; i++) {
            Database.addNPCFeature(DataCompiler.npcFeatureData[i]);
        }
        for (int i = 0; i < DataCompiler.npcTemplateData.length; i++) {
            Database.addNPCTemplate(DataCompiler.npcTemplateData[i]);
        }
        for (int i = 0; i < DataCompiler.pilotArmorData.length; i++) {
            Database.addPilotArmor(DataCompiler.pilotArmorData[i]);
        }
        for (int i = 0; i < DataCompiler.pilotGearData.length; i++) {
            Database.addPilotGear(DataCompiler.pilotGearData[i]);
        }
        for (int i = 0; i < DataCompiler.pilotWeapon.length; i++) {
            Database.addPilotWeapon(DataCompiler.pilotWeapon[i]);
        }
        for (int i = 0; i < DataCompiler.reserveData.length; i++) {
            Database.addReserve(DataCompiler.reserveData[i]);
        }
        for (int i = 0; i < DataCompiler.skillData.length; i++) {
            Database.addSkill(DataCompiler.skillData[i]);
        }
        for (int i = 0; i < DataCompiler.statusData.length; i++) {
            Database.addStatus(DataCompiler.statusData[i]);
        }
        for (int i = 0; i < DataCompiler.talentData.length; i++) {
            Database.addTalent(DataCompiler.talentData[i]);
        }
        // ----less important
        for (int i = 0; i < DataCompiler.environmentData.length; i++) {
            Database.addEnvironment(DataCompiler.environmentData[i]);
        }
        for (int i = 0; i < DataCompiler.sitrepData.length; i++) {
            Database.addSitrep(DataCompiler.sitrepData[i]);
        }
        // ----almost unimportant
        for (int i = 0; i < DataCompiler.backgroundData.length; i++) {
            Database.addUnverifiedBackground(DataCompiler.backgroundData[i]);
        }
        for (int i = 0; i < DataCompiler.bondData.length; i++) {
            Database.addBond(DataCompiler.bondData[i]);
        }
        // ----just for reference
        for (int i = 0; i < DataCompiler.ruleData.length; i++) {
            Database.addRule(DataCompiler.ruleData[i]);
        }
        for (int i = 0; i < DataCompiler.tableData.length; i++) {
            Database.addTable(DataCompiler.tableData[i]);
        }
        for (int i = 0; i < DataCompiler.termData.length; i++) {
            Database.addTerm(DataCompiler.termData[i]);
        }
    }
    private static void flushData(boolean suppressAlerts) {
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
        DataCompiler.iActionDataData = new IActionData[0];
        DataCompiler.activationTypeData = new ActivationType[0];
        DataCompiler.conditionData = new StateData[0];
        DataCompiler.coreBonusData = new CoreBonus[0];
        DataCompiler.dataTagData = new DataTag[0];
        DataCompiler.tagData = new Tag[0];
        DataCompiler.manufacturerData = new Manufacturer[0];
        DataCompiler.npcFeatureData = new NPCFeature[0];
        DataCompiler.npcTemplateData = new NPCTemplate[0];
        DataCompiler.pilotArmorData = new PilotArmor[0];
        DataCompiler.pilotGearData = new PilotGear[0];
        DataCompiler.pilotWeapon = new PilotWeapon[0];
        DataCompiler.reserveData = new Reserve[0];
        DataCompiler.skillData = new SkillData[0];
        DataCompiler.statusData = new StateData[0];
        DataCompiler.talentData = new TalentData[0];
        // ----less important
        DataCompiler.environmentData = new Environment[0];
        DataCompiler.sitrepData = new Sitrep[0];
        // ----almost unimportant
        DataCompiler.backgroundData = new UnverifiedBackground[0];
        DataCompiler.bondData = new Bond[0];
        // ----just for reference
        DataCompiler.ruleData = new Rule[0];
        DataCompiler.termData = new Term[0];
        DataCompiler.tableData = new Table[0];

        DataCompiler.hasData = false;

        if (! suppressAlerts) {
            HelperMethods.alert("DataCompiler - DATA FLUSHED"
                + " SUCCESSFULLY");
        }
    }
    public static void flushData() {
        flushData(false);
    }
}