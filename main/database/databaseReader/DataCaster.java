package main.database.databaseReader;

import main.database.databaseReader.json.JSONObject;
import packages.coreTypes.Environment;
import packages.coreTypes.Rule;
import packages.coreTypes.Sitrep;
import packages.coreTypes.Table;
import packages.coreTypes.Term;
import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.Manufacturer;
import packages.coreTypes.entityMechanics.NPCFeature;
import packages.coreTypes.entityMechanics.NPCTemplate;
import packages.coreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Modification;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.Weapon;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.DataTag;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.tagSystem.Tag;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Background;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Bond;
import packages.coreTypes.entityMechanics.entityTypes.pilot.CoreBonus;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Reserve;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotArmor;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotGear;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;
import packages.coreTypes.entityMechanics.stateSystem.state.Condition;
import packages.coreTypes.entityMechanics.stateSystem.state.Status;

public class DataCaster {
    // receive JSON data as an Object[] containing JSONObject[]s and JSONObjects
    //     from DatabaseReader
    // transform it into the appropriate data type
    // - remember to split apart the DataTag data into Tag
    // then pass it on to DataCompiler
    // - first open DataCompiler
    // - then send the data
    // - then close DataCompiler
    // then flush the data afterwards

    // all the data being held at the moment
    // ----some critical data types:
    private static JSONObject[] frameData = new JSONObject[0];
    private static JSONObject[] systemData = new JSONObject[0];
    private static JSONObject[] modificationData = new JSONObject[0];
    private static JSONObject[] weaponData = new JSONObject[0];
    // ----the rest of the critical data types:
    private static JSONObject[] actionData = new JSONObject[0];
    private static JSONObject[] dataTagData = new JSONObject[0];
    private static JSONObject[] manufacturerData = new JSONObject[0];
    private static JSONObject[] npcFeatureData = new JSONObject[0];
    private static JSONObject[] npcTemplateData = new JSONObject[0];
    private static JSONObject[] pilotEquipmentData = new JSONObject[0];
    private static JSONObject[] reserveData = new JSONObject[0];
    private static JSONObject[] skillData = new JSONObject[0];
    private static JSONObject[] stateData = new JSONObject[0];
    private static JSONObject[] talentData = new JSONObject[0];
    // ----less important
    private static JSONObject[] environmentData = new JSONObject[0];
    private static JSONObject[] sitrepData = new JSONObject[0];
    // ----almost unimportant
    private static JSONObject[] backgroundData = new JSONObject[0];
    private static JSONObject[] bondData = new JSONObject[0];
    // ----just for reference
    private static JSONObject ruleData = null;
    private static JSONObject[] termData = new JSONObject[0];
    private static JSONObject tableData = null;

    // Prevent user from instantiating this class
    private DataCaster() {}

    // use a class called JSON
    // Check Iterable for documentation on JSONArray - supposedly you can use
    //     get() - line 330
    // length() - 633
    // line 679 for JSONObject - use get() (also for String) and getBoolean(),
    //     getInt(), etc
    // Actions is an array of Actions
    // Backgrounds is an array of Backgrounds
    // bonds
    // CB is an array of CB
    // env
    // frames is arr
    // manufac
    // mods
    // npc features
    // npc templates
    // pilot gear - they're all together, mind
    // reserves
    // rules is one object with properties that could be int, an object, an array, an array of objects
    // sitreps
    // skills
    // statuses - all mixed together
    // systems
    // tables is one object with properties that are all arrays
    // tags
    // talents
    // terms (glossary)
    // weapons
    public static void receiveData(Object[] data) {
        Object[] newData;

        // TODO: fill out, using all the DataCaster.processX() methods
        // unpack the Object[] into an array of data
        // TODO: fill out
        // then process that data
        // TODO: fill out
        // each Object is actually a JSONObject[] or JSONObject
        // convert those JSONObject[]s and JSONObjects to Action[],
        //     Background[], etc. etc.
        // once done, pass that data on to DataCompiler
        // - first open DataCompiler so it can receive
        DataCompiler.open();
        // - pass the data on
        DataCompiler.receiveData(newData);
        // - and then close DataCompiler
        DataCompiler.close();
        // and tell DataCompiler to upload it
        DataCompiler.uploadData();
    }
    private static Action[] processActions(JSONObject[] actionsData) {
        Action[] actions = new Action[actionsData.length()];

        for (int i = 0; i < actions.length; i++) {
            actions[i] = toAction(actionsData.getJSONObject(i));
        }

        return actions;
    }
    private static Action toAction(JSONObject actionData) {
        // TODO: fill out
        // Required properties
        String id = (String) actionData.get("id");
        String name = (String) actionData.get("name");
        String activation = (String) actionData.get("activation");
        String terse = (String) actionData.get("terse");
        String detail = (String) actionData.get("detail");
        // Optional properties
    private boolean pilot;
    private boolean mech;
    private boolean hideActive;
    private String[] synergyLocations;
    private String[] confirm;
    private boolean ignoreUsed;

        return new Action(id, name, activation, terse, detail);
    }
    private static Background[] processBackgrounds(JSONObject[] backgroundsData) {
        // TODO: fill out
    }
    private static Background toBackground(JSONObject backgroundData) {
        // TODO: fill out
    }
    private static Bond[] processBonds(JSONObject[] bondsData) {
        // TODO: fill out
    }
    private static Bond toBond(JSONObject bondData) {
        // TODO: fill out
    }
    private static CoreBonus[] processCoreBonuses(JSONObject[] coreBonusesData) {
        // TODO: fill out
    }
    private static CoreBonus toCoreBonus(JSONObject coreBonusData) {
        // TODO: fill out
    }
    private static Environment[] processEnvironments(JSONObject[] environmentsData) {
        // TODO: fill out
    }
    private static Environment toEnvironment(JSONObject environmentData) {
        // TODO: fill out
    }
    private static Frame[] processFrames(JSONObject[] framesData) {
        // TODO: fill out
    }
    private static Frame toFrame(JSONObject frameData) {
        // TODO: fill out
    }
    private static Manufacturer[] processManufacturers(JSONObject[] manufacturersData) {
        // TODO: fill out
    }
    private static Manufacturer toManufacturer(JSONObject manufacturerData) {
        // TODO: fill out
    }
    private static Modification[] processModifications(JSONObject[] modificationsData) {
        // TODO: fill out
    }
    private static Modification toModification(JSONObject modificationData) {
        // TODO: fill out
    }
    private static NPCFeature[] processNPCFeatures(JSONObject[] npcfeaturesData) {
        // TODO: fill out
    }
    private static NPCFeature toNPCFeature(JSONObject npcfeatureData) {
        // TODO: fill out
    }
    private static NPCTemplate[] processNPCTemplates(JSONObject[] npctemplatesData) {
        // TODO: fill out
    }
    private static NPCTemplate toNPCTemplate(JSONObject npctemplateData) {
        // TODO: fill out
    }
    private static void processPilotEquipment(JSONObject[] pilotEquipmentData) {
        // TODO: fill out
        JSONObject[] pilotArmorData = new JSONObject[pilotEquipmentData.length()];
        JSONObject[] pilotWeaponData = new JSONObject[pilotEquipmentData.length()];
        JSONObject[] pilotGearData = new JSONObject[pilotEquipmentData.length()];
        String type;

        for (int i = 0; i < pilotEquipmentData.length(); i++) {
            type = pilotEquipmentData[i].get("type");
            if (type.equals("Armor")) {
                pilotArmorData[i] = pilotEquipmentData[i];
            } else if (type.equals("Weapon")) {
                pilotWeaponData[i] = pilotEquipmentData[i];
            } else if (type.equals("Gear")) {
                pilotGearData[i] = pilotEquipmentData[i];
            }
        }
        // TODO: remove null elements
        DataCaster.processPilotArmor(pilotArmorData);
        DataCaster.processPilotWeapons(pilotWeaponData);
        DataCaster.processPilotGear(pilotGearData);
    }
    private static PilotArmor[] processPilotArmor(JSONObject[] pilotArmorsData) {
        // TODO: fill out
    }
    private static PilotArmor toPilotArmor(JSONObject pilotArmorData) {
        // TODO: fill out
    }
    private static PilotGear[] processPilotGear(JSONObject[] pilotGearsData) {
        // TODO: fill out
    }
    private static PilotGear toPilotGear(JSONObject pilotGearData) {
        // TODO: fill out
    }
    private static PilotWeapon[] processPilotWeapons(JSONObject[] pilotArmorsData) {
        // TODO: fill out
    }
    private static PilotWeapon toPilotWeapons(JSONObject pilotWeaponsData) {
        // TODO: fill out
    }
    private static Reserve[] processReserves(JSONObject[] reservesData) {
        // TODO: fill out
    }
    private static Reserve toReserve(JSONObject reserveData) {
        // TODO: fill out
    }
    private static Rule[] processRules(JSONObject[] rulesData) {
        // TODO: fill out
    }
    private static Rule toRule(JSONObject ruleData) {
        // TODO: fill out
    }
    private static Sitrep[] processSitreps(JSONObject[] sitrepsData) {
        // TODO: fill out
    }
    private static Sitrep toSitrep(JSONObject sitrepData) {
        // TODO: fill out
    }
    private static Skill[] processSkills(JSONObject[] skillsData) {
        // TODO: fill out
    }
    private static Skill toSkill(JSONObject skillData) {
        // TODO: fill out
    }
    private static void processStates(JSONObject[] statesData) {
        // TODO: fill out
        DataCaster.processConditions(statesData);
        DataCaster.processStatuses(statesData);
    }
    private static Condition[] processConditions(JSONObject[] conditionsData) {
        // TODO: fill out
    }
    private static Condition toCondition(JSONObject conditionData) {
        // TODO: fill out
    }
    private static Status[] processStatuses(JSONObject[] StatusesData) {
        // TODO: fill out
    }
    private static Status toStatus(JSONObject statusData) {
        // TODO: fill out
    }
    private static MechSystem[] processMechSystems(JSONObject[] mechsystemsData) {
        // TODO: fill out
    }
    private static MechSystem toMechSystem(JSONObject mechsystemData) {
        // TODO: fill out
    }
    private static Table[] processTables(JSONObject[] tablesData) {
        // TODO: fill out
    }
    private static Table toTable(JSONObject tableData) {
        // TODO: fill out
    }
    private static void processLCPTags(JSONObject[] lcpTagsData) {
        // TODO: fill out
        DataTag[] dataTags = processDataTags(lcpTagsData);
        Tag tags = processTags(dataTags);
    }
    private static DataTag[] processDataTags(JSONObject[] dataTagsData) {
        // TODO: fill out
    }
    private static DataTag toDataTag(JSONObject datatagData) {
        // TODO: fill out
    }
    private static Tag[] processTags(DataTag[] tagsData) {
        // TODO: fill out
    }
    private static Tag toTag(DataTag tagData) {
        // TODO: fill out
        if (tagData.isHidden()) {
            return null;
        }
        return tagData.toTag();
    }
    private static Talent[] processTalents(JSONObject[] talentsData) {
        // TODO: fill out
    }
    private static Talent toTalent(JSONObject talentData) {
        // TODO: fill out
    }
    private static Term[] processTerms(JSONObject[] termsData) {
        // TODO: fill out
    }
    private static Term toTerm(JSONObject termData) {
        // TODO: fill out
    }
    private static Weapon[] processWeapons(JSONObject[] weaponsData) {
        // TODO: fill out
    }
    private static Weapon toWeapon(JSONObject weaponData) {
        // TODO: fill out
    }
}
