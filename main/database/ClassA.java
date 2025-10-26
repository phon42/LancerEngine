package main.database;

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
import packages.coreTypes.entityMechanics.entityTypes.pilot.Background;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Bond;
import packages.coreTypes.entityMechanics.entityTypes.pilot.CoreBonus;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Reserve;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotArmor;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotGear;
import packages.coreTypes.entityMechanics.entityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;

public class ClassA { // possible name DatabaseReader
    // calls open() on Class B, calls Class B with all JSONObjects read and then
    //     some kind of add(), save(), or close() method

    // Prevent user from instantiating this class
    private ClassA() {}

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
    private static Action[] processActions(JSONArray actionsData) {
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
    private static Background[] processBackgrounds(JSONArray backgroundsData) {
        // TODO: fill out
    }
    private static Background toBackground(JSONObject backgroundData) {
        // TODO: fill out
    }
    private static Bond[] processBonds(JSONArray bondsData) {
        // TODO: fill out
    }
    private static Bond toBond(JSONObject bondData) {
        // TODO: fill out
    }
    private static CoreBonus[] processCoreBonuses(JSONArray coreBonusesData) {
        // TODO: fill out
    }
    private static CoreBonus toCoreBonus(JSONObject coreBonusData) {
        // TODO: fill out
    }
    private static Environment[] processEnvironments(JSONArray environmentsData) {
        // TODO: fill out
    }
    private static Environment toEnvironment(JSONObject environmentData) {
        // TODO: fill out
    }
    private static Frame[] processFrames(JSONArray framesData) {
        // TODO: fill out
    }
    private static Frame toFrame(JSONObject frameData) {
        // TODO: fill out
    }
    private static Manufacturer[] processManufacturers(JSONArray manufacturersData) {
        // TODO: fill out
    }
    private static Manufacturer toManufacturer(JSONObject manufacturerData) {
        // TODO: fill out
    }
    private static Modification[] processModifications(JSONArray modificationsData) {
        // TODO: fill out
    }
    private static Modification toModification(JSONObject modificationData) {
        // TODO: fill out
    }
    private static NPCFeature[] processNPCFeatures(JSONArray npcfeaturesData) {
        // TODO: fill out
    }
    private static NPCFeature toNPCFeature(JSONObject npcfeatureData) {
        // TODO: fill out
    }
    private static NPCTemplate[] processNPCTemplates(JSONArray npctemplatesData) {
        // TODO: fill out
    }
    private static NPCTemplate toNPCTemplate(JSONObject npctemplateData) {
        // TODO: fill out
    }
    private static void processPilotEquipment(JSONArray pilotEquipmentData) {
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
        ClassA.processPilotArmor(pilotArmorData);
        ClassA.processPilotWeapons(pilotWeaponData);
        ClassA.processPilotGear(pilotGearData);
    }
    private static PilotArmor[] processPilotArmor(JSONArray pilotArmorsData) {
        // TODO: fill out
    }
    private static PilotArmor toPilotArmor(JSONObject pilotArmorData) {
        // TODO: fill out
    }
    private static PilotGear[] processPilotGear(JSONArray pilotGearsData) {
        // TODO: fill out
    }
    private static PilotGear toPilotGear(JSONObject pilotGearData) {
        // TODO: fill out
    }
    private static PilotWeapon[] processPilotWeapons(JSONArray pilotArmorsData) {
        // TODO: fill out
    }
    private static PilotWeapon toPilotWeapons(JSONObject pilotWeaponsData) {
        // TODO: fill out
    }
    private static Reserve[] processReserves(JSONArray reservesData) {
        // TODO: fill out
    }
    private static Reserve toReserve(JSONObject reserveData) {
        // TODO: fill out
    }
    private static Rule[] processRules(JSONArray rulesData) {
        // TODO: fill out
    }
    private static Rule toRule(JSONObject ruleData) {
        // TODO: fill out
    }
    private static Sitrep[] processSitreps(JSONArray sitrepsData) {
        // TODO: fill out
    }
    private static Sitrep toSitrep(JSONObject sitrepData) {
        // TODO: fill out
    }
    private static Skill[] processSkills(JSONArray skillsData) {
        // TODO: fill out
    }
    private static Skill toSkill(JSONObject skillData) {
        // TODO: fill out
    }
    private static void processStates(JSONArray statesData) {
        // TODO: fill out
        ClassA.processConditions(statesData);
        ClassA.processStatuses(statesData);
    }
    private static Condition[] processConditions(JSONArray conditionsData) {
        // TODO: fill out
    }
    private static Condition toCondition(JSONObject conditionData) {
        // TODO: fill out
    }
    private static Status[] processStatuses(JSONArray StatusesData) {
        // TODO: fill out
    }
    private static Status toStatus(JSONObject statusData) {
        // TODO: fill out
    }
    private static MechSystem[] processMechSystems(JSONArray mechsystemsData) {
        // TODO: fill out
    }
    private static MechSystem toMechSystem(JSONObject mechsystemData) {
        // TODO: fill out
    }
    private static Table[] processTables(JSONArray tablesData) {
        // TODO: fill out
    }
    private static Table toTable(JSONObject tableData) {
        // TODO: fill out
    }
    private static void processLCPTags(JSONObject lcpTagsData) {
        // TODO: fill out
        DataTag[] dataTags = ClassA.processDataTags(lcpTagsData);
    }
    private static DataTag[] processDataTags(JSONArray dataTagsData) {
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
        return tagData;
    }
    private static Talent[] processTalents(JSONArray talentsData) {
        // TODO: fill out
    }
    private static Talent toTalent(JSONObject talentData) {
        // TODO: fill out
    }
    private static Term[] processTerms(JSONArray termsData) {
        // TODO: fill out
    }
    private static Term toTerm(JSONObject termData) {
        // TODO: fill out
    }
    private static Weapon[] processWeapons(JSONArray weaponsData) {
        // TODO: fill out
    }
    private static Weapon toWeapon(JSONObject weaponData) {
        // TODO: fill out
    }
}
