package main.database.databaseReader;

import java.util.Set;
import main.Database;
import main.database.databaseReader.json.JSONArray;
import main.database.databaseReader.json.JSONException;
import main.database.databaseReader.json.JSONObject;
import main.roll.DiceExpression;
import packages.coreTypes.Environment;
import packages.coreTypes.Rule;
import packages.coreTypes.Sitrep;
import packages.coreTypes.Table;
import packages.coreTypes.Term;
import packages.coreTypes.entityMechanics.Action;
import packages.coreTypes.entityMechanics.Frequency;
import packages.coreTypes.entityMechanics.Manufacturer;
import packages.coreTypes.entityMechanics.NPCFeature;
import packages.coreTypes.entityMechanics.NPCTemplate;
import packages.coreTypes.entityMechanics.RangeTag;
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
    private static JSONObject[] frameRaw;
    private static JSONObject[] systemRaw;
    private static JSONObject[] modificationRaw;
    private static JSONObject[] weaponRaw;
    // ----the rest of the critical data types:
    private static JSONObject[] actionRaw;
    private static JSONObject[] coreBonusRaw;
    private static JSONObject[] dataTagRaw;
    private static JSONObject[] manufacturerRaw;
    private static JSONObject[] npcFeatureRaw;
    private static JSONObject[] npcTemplateRaw;
    private static JSONObject[] pilotEquipmentRaw;
    private static JSONObject[] reserveRaw;
    private static JSONObject[] skillRaw;
    private static JSONObject[] stateRaw;
    private static JSONObject[] talentRaw;
    // ----less important
    private static JSONObject[] environmentRaw;
    private static JSONObject[] sitrepRaw;
    // ----almost unimportant
    private static JSONObject[] backgroundRaw;
    private static JSONObject[] bondRaw;
    // ----just for reference
    private static JSONObject ruleRaw;
    private static JSONObject[] termRaw;
    private static JSONObject tableRaw;
    // ----some critical data types:
    private static Frame[] frameProcessed;
    private static MechSystem[] systemProcessed;
    private static Modification[] modificationProcessed;
    private static Weapon[] weaponProcessed;
    // ----the rest of the critical data types:
    private static Action[] actionProcessed;
    private static CoreBonus[] coreBonusProcessed;
    private static Condition[] conditionProcessed;
    private static DataTag[] dataTagProcessed;
    private static Tag[] tagProcessed;
    private static Manufacturer[] manufacturerProcessed;
    private static NPCFeature[] npcFeatureProcessed;
    private static NPCTemplate[] npcTemplateProcessed;
    private static PilotArmor[] pilotArmorProcessed;
    private static PilotGear[] pilotGearProcessed;
    private static PilotWeapon[] pilotWeaponProcessed;
    private static Reserve[] reserveProcessed;
    private static Skill[] skillProcessed;
    private static Status[] statusProcessed;
    private static Talent[] talentProcessed;
    // ----less important
    private static Environment[] environmentProcessed;
    private static Sitrep[] sitrepProcessed;
    // ----almost unimportant
    private static Background[] backgroundProcessed;
    private static Bond[] bondProcessed;
    // ----just for reference
    private static Rule[] ruleProcessed;
    private static Term[] termProcessed;
    private static Table[] tableProcessed;

    static {
        flushData();
    }
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
    // rules is one object with properties that could be int, an object, an
    //     array, an array of objects
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
        // unpack the Object[], transforming each element from Object to a
        //     JSONObject[] or JSONObject, then putting it in its respective
        //     property (i.e. DataCaster.frameData).
        unpackData(data);
        // then process that data
        // Database is being opened and closed here
        processData();
        // now pack that data back up
        data = packData();
        // then flush the data
        flushData();
        // once done, pass that data on to DataCompiler
        // DataCompiler is being opened and closed here
        passData(data);
    }
    private static void unpackData(Object[] data) {
        // TODO: fill out
        // unpack the Object[], transforming each element from Object to a
        //     JSONObject[] or JSONObject, then putting it in its respective
        //     property (i.e. DataCaster.frameRaw).
            DataCaster.frameRaw = (JSONObject[]) data[0];
            DataCaster.systemRaw = (JSONObject[]) data[1];
            DataCaster.modificationRaw = (JSONObject[]) data[2];
            DataCaster.weaponRaw = (JSONObject[]) data[3];
            DataCaster.actionRaw = (JSONObject[]) data[4];
            DataCaster.coreBonusRaw = (JSONObject[]) data[5];
            DataCaster.dataTagRaw = (JSONObject[]) data[6];
            DataCaster.manufacturerRaw = (JSONObject[]) data[7];
            DataCaster.npcFeatureRaw = (JSONObject[]) data[8];
            DataCaster.npcTemplateRaw = (JSONObject[]) data[9];
            DataCaster.pilotEquipmentRaw = (JSONObject[]) data[10];
            DataCaster.reserveRaw = (JSONObject[]) data[11];
            DataCaster.skillRaw = (JSONObject[]) data[12];
            DataCaster.stateRaw = (JSONObject[]) data[13];
            DataCaster.talentRaw = (JSONObject[]) data[14];
            // ----less important
            DataCaster.environmentRaw = (JSONObject[]) data[15];
            DataCaster.sitrepRaw = (JSONObject[]) data[16];
            // ----almost unimportant
            DataCaster.backgroundRaw = (JSONObject[]) data[17];
            DataCaster.bondRaw = (JSONObject[]) data[18];
            // ----just for reference
            DataCaster.ruleRaw = (JSONObject) data[19];
            DataCaster.termRaw = (JSONObject[]) data[20];
            DataCaster.tableRaw = (JSONObject) data[21];
    }
    private static void processData() {
        // then process that data
        // - first open Database so you can create new Frames, etc etc
        Database.open();
        // each Object is actually a JSONObject[] or JSONObject
        // convert those JSONObject[]s and JSONObjects to Action[],
        //     Background[], etc. etc.
        if (! (DataCaster.actionRaw == null || DataCaster.actionRaw.length < 1))
        {
            processActions(DataCaster.actionRaw);
        }
        if (! (DataCaster.backgroundRaw == null
            || DataCaster.backgroundRaw.length < 1)) {
            processBackgrounds(DataCaster.backgroundRaw);
        
        }
        if (! (DataCaster.bondRaw == null || DataCaster.bondRaw.length < 1)) {
            processBonds(DataCaster.bondRaw);
        }
        // TODO: fix
        if (! (DataCaster.coreBonusRaw == null
            || DataCaster.coreBonusRaw.length < 1)) {
            processCoreBonuses(DataCaster.coreBonusRaw);
        
        }
        if (! (DataCaster.environmentRaw == null
            || DataCaster.environmentRaw.length < 1)) {
            processEnvironments(DataCaster.environmentRaw);
        
        }
        if (! (DataCaster.frameRaw == null || DataCaster.frameRaw.length < 1)) {
            processFrames(DataCaster.frameRaw);
        
        }
        if (! (DataCaster.manufacturerRaw == null
            || DataCaster.manufacturerRaw.length < 1)) {
            processManufacturers(DataCaster.manufacturerRaw);
        
        }
        if (! (DataCaster.modificationRaw == null
            || DataCaster.modificationRaw.length < 1)) {
            processModifications(DataCaster.modificationRaw);
        
        }
        if (! (DataCaster.npcFeatureRaw == null
            || DataCaster.npcFeatureRaw.length < 1)) {
            processNPCFeatures(DataCaster.npcFeatureRaw);
        
        }
        if (! (DataCaster.npcTemplateRaw == null
            || DataCaster.npcTemplateRaw.length < 1)) {
            processNPCTemplates(DataCaster.npcTemplateRaw);
        
        }
        if (! (DataCaster.pilotEquipmentRaw == null
            || DataCaster.pilotEquipmentRaw.length < 1)) {
            processPilotEquipment(DataCaster.pilotEquipmentRaw);
        
        }
        if (! (DataCaster.reserveRaw == null
            || DataCaster.reserveRaw.length < 1)) {
            processReserves(DataCaster.reserveRaw);
        
        }
        if (! (DataCaster.ruleRaw == null
            || DataCaster.ruleRaw.keySet().size() < 1)) {
            processRules(DataCaster.ruleRaw);
        
        }
        if (! (DataCaster.sitrepRaw == null || DataCaster.sitrepRaw.length < 1))
        {
            processSitreps(DataCaster.sitrepRaw);
        
        }
        if (! (DataCaster.skillRaw == null || DataCaster.skillRaw.length < 1)) {
            processSkills(DataCaster.skillRaw);
        
        }
        if (! (DataCaster.stateRaw == null || DataCaster.stateRaw.length < 1)) {
            processStates(DataCaster.stateRaw);
        
        }
        if (! (DataCaster.systemRaw == null || DataCaster.systemRaw.length < 1))
        {
            processMechSystems(DataCaster.systemRaw);
        
        }
        if (! (DataCaster.tableRaw == null
            || DataCaster.tableRaw.keySet().size() < 1)) {
            processTables(DataCaster.tableRaw);
        
        }
        if (! (DataCaster.dataTagRaw == null
            || DataCaster.dataTagRaw.length < 1)) {
            processLCPTags(DataCaster.dataTagRaw);
        
        }
        if (! (DataCaster.talentRaw == null || DataCaster.talentRaw.length < 1))
        {
            processTalents(DataCaster.talentRaw);
        
        }
        if (! (DataCaster.termRaw == null || DataCaster.termRaw.length < 1)) {
            processTerms(DataCaster.termRaw);
        
        }
        if (! (DataCaster.weaponRaw == null || DataCaster.weaponRaw.length < 1))
        {
            processWeapons(DataCaster.weaponRaw);
        }
        // Remember to take JSONObject[] pilotEquipmentData, stateData, and
        //     split it into its constituent types while it's being casted
        // - and then close Database
        Database.close();
    }
    private static Action[] processActions(JSONObject[] actionsData) {
        Action[] actions = new Action[actionsData.length];

        for (int i = 0; i < actions.length; i++) {
            actions[i] = toAction(actionsData[i]);
        }

        return actions;
    }
    private static Action toAction(JSONObject actionData) {
        // TODO: fill out
        // Required properties
        String id = actionData.getString("id");
        String name = actionData.getString("name");
        String activation = actionData.getString("activation");
        String terse = actionData.getString("terse");
        String detail = actionData.getString("detail");
        // Optional properties
    private boolean pilot;
    private boolean mech;
    private boolean hideActive;
    private String[] synergyLocations;
    private String[] confirm;
    private boolean ignoreUsed;

        return new Action(id, name, activation, terse, detail);
    }
    private static Background[] processBackgrounds(
        JSONObject[] backgroundsData) {
        Background[] backgrounds = new Background[backgroundsData.length];

        for (int i = 0; i < backgroundsData.length; i++) {
            backgrounds[i] = toBackground(backgroundsData[i]);
        }

        return backgrounds;
    }
    private static Background toBackground(JSONObject backgroundData) {
        // TODO: fill out
    }
    private static Bond[] processBonds(JSONObject[] bondsData) {
        Bond[] bonds = new Bond[bondsData.length];

        for (int i = 0; i < bondsData.length; i++) {
            bonds[i] = toBond(bondsData[i]);
        }

        return bonds;
    }
    private static Bond toBond(JSONObject bondData) {
        // TODO: fill out
    }
    private static CoreBonus[] processCoreBonuses(
        JSONObject[] coreBonusesData) {
        CoreBonus[] coreBonuses = new CoreBonus[coreBonusesData.length];

        for (int i = 0; i < coreBonusesData.length; i++) {
            coreBonuses[i] = toCoreBonus(coreBonusesData[i]);
        }

        return coreBonuses;
    }
    private static CoreBonus toCoreBonus(JSONObject coreBonusData) {
        // TODO: fill out
    }
    private static Environment[] processEnvironments(
        JSONObject[] environmentsData) {
        Environment[] environments = new Environment[environmentsData.length];

        for (int i = 0; i < environmentsData.length; i++) {
            environments[i] = toEnvironment(environmentsData[i]);
        }

        return environments;
    }
    private static Environment toEnvironment(JSONObject environmentData) {
        // TODO: fill out
    }
    private static Frame[] processFrames(JSONObject[] framesData) {
        Frame[] frames = new Frame[framesData.length];

        for (int i = 0; i < framesData.length; i++) {
            frames[i] = toFrame(framesData[i]);
        }

        return frames;
    }
    private static Frame toFrame(JSONObject frameData) {
        // TODO: fill out
    }
    private static Manufacturer[] processManufacturers(
        JSONObject[] manufacturersData) {
        Manufacturer[] manufacturers =
            new Manufacturer[manufacturersData.length];

        for (int i = 0; i < manufacturersData.length; i++) {
            manufacturers[i] = toManufacturer(manufacturersData[i]);
        }

        return manufacturers;
    }
    private static Manufacturer toManufacturer(JSONObject manufacturerData) {
        // TODO: fill out
    }
    private static Modification[] processModifications(
        JSONObject[] modificationsData) {
        Modification[] modifications =
            new Modification[modificationsData.length];

        for (int i = 0; i < modificationsData.length; i++) {
            modifications[i] = toModification(modificationsData[i]);
        }

        return modifications;
    }
    private static Modification toModification(JSONObject modificationData) {
        // TODO: fill out
    }
    private static NPCFeature[] processNPCFeatures(JSONObject[] npcFeaturesData)
    {
        NPCFeature[] npcFeatures = new NPCFeature[npcFeaturesData.length];

        for (int i = 0; i < npcFeaturesData.length; i++) {
            npcFeatures[i] = toNPCFeature(npcFeaturesData[i]);
        }

        return npcFeatures;
    }
    private static NPCFeature toNPCFeature(JSONObject npcFeatureData) {
        // TODO: fill out
    }
    private static NPCTemplate[] processNPCTemplates(
        JSONObject[] npcTemplatesData) {
        NPCTemplate[] npcTemplates = new NPCTemplate[npcTemplatesData.length];

        for (int i = 0; i < npcTemplates.length; i++) {
            npcTemplates[i] = toNPCTemplate(npcTemplatesData[i]);
        }

        return npcTemplates;
    }
    private static NPCTemplate toNPCTemplate(JSONObject npcTemplateData) {
        // TODO: fill out
    }
    private static void processPilotEquipment(JSONObject[] pilotEquipmentData) {
        // TODO: fill out
        JSONObject[] pilotArmorData = new JSONObject[pilotEquipmentData.length];
        JSONObject[] pilotWeaponData = new JSONObject[
            pilotEquipmentData.length];
        JSONObject[] pilotGearData = new JSONObject[pilotEquipmentData.length];
        String type;

        for (int i = 0; i < pilotEquipmentData.length; i++) {
            // skip null elements
            if (pilotEquipmentData[i] == null) {
                continue;
            }
            type = pilotEquipmentData[i].getString("type");
            // add to the appropriate array
            if (type.equals("Armor")) {
                pilotArmorData[i] = pilotEquipmentData[i];
            } else if (type.equals("Weapon")) {
                pilotWeaponData[i] = pilotEquipmentData[i];
            } else if (type.equals("Gear")) {
                pilotGearData[i] = pilotEquipmentData[i];
            }
        }
        DataCaster.processPilotArmor(pilotArmorData);
        DataCaster.processPilotWeapons(pilotWeaponData);
        DataCaster.processPilotGear(pilotGearData);
    }
    private static PilotArmor[] processPilotArmor(JSONObject[] pilotArmorData)
    {
        PilotArmor[] pilotArmor;
        int index = 0;

        // Counting the number of non-null elements
        for (int i = 0; i < pilotArmorData.length; i++) {
            if (pilotArmorData[i] == null) {
                continue;
            }
            index++;
        }
        // Removing null elements
        pilotArmor = new PilotArmor[index];
        index = 0;
        for (int i = 0; i < pilotArmorData.length; i++) {
            if (pilotArmorData[i] == null) {
                continue;
            }
            // Convert the valid elements
            pilotArmor[index] = toPilotArmor(pilotArmorData[i]);
            index++;
        }

        return pilotArmor;
    }
    private static PilotArmor toPilotArmor(JSONObject pilotArmorData) {
        // TODO: fill out
    }
    private static PilotGear[] processPilotGear(JSONObject[] pilotGearData) {
        PilotGear[] pilotGear;
        int index = 0;

        // Counting the number of non-null elements
        for (int i = 0; i < pilotGearData.length; i++) {
            if (pilotGearData[i] == null) {
                continue;
            }
            index++;
        }
        // Removing null elements
        pilotGear = new PilotGear[index];
        index = 0;
        for (int i = 0; i < pilotGearData.length; i++) {
            if (pilotGearData[i] == null) {
                continue;
            }
            // Convert the valid elements
            pilotGear[index] = toPilotGear(pilotGearData[i]);
            index++;
        }

        return pilotGear;
    }
    private static PilotGear toPilotGear(JSONObject pilotGearData) {
        // TODO: fill out
    }
    private static PilotWeapon[] processPilotWeapons(
        JSONObject[] pilotWeaponsData) {
        PilotWeapon[] pilotWeapons;
        int index = 0;

        // Counting the number of non-null elements
        for (int i = 0; i < pilotWeaponsData.length; i++) {
            if (pilotWeaponsData[i] == null) {
                continue;
            }
            index++;
        }
        // Removing null elements
        pilotWeapons = new PilotWeapon[index];
        index = 0;
        for (int i = 0; i < pilotWeaponsData.length; i++) {
            if (pilotWeaponsData[i] == null) {
                continue;
            }
            // Convert the valid elements
            pilotWeapons[index] = toPilotWeapon(pilotWeaponsData[i]);
            index++;
        }

        return pilotWeapons;
    }
    private static PilotWeapon toPilotWeapon(JSONObject pilotWeaponData) {
        // TODO: fill out
    }
    private static Reserve[] processReserves(JSONObject[] reservesData) {
        Reserve[] reserves = new Reserve[reservesData.length];

        for (int i = 0; i < reserves.length; i++) {
            reserves[i] = toReserve(reservesData[i]);
        }

        return reserves;
    }
    private static Reserve toReserve(JSONObject reserveData) {
        // TODO: fill out
    }
    private static Rule[] processRules(JSONObject[] rulesData) {
        // TODO: fill out
    }
    private static Rule toRule(String name, JSONArray value) {
        // TODO: fill out
    }
    private static Sitrep[] processSitreps(JSONObject[] sitrepsData) {
        Sitrep[] sitreps = new Sitrep[sitrepsData.length];

        for (int i = 0; i < sitreps.length; i++) {
            sitreps[i] = toSitrep(sitrepsData[i]);
        }

        return sitreps;
    }
    private static Sitrep toSitrep(JSONObject sitrepData) {
        // TODO: fill out
    }
    private static Skill[] processSkills(JSONObject[] skillsData) {
        Skill[] skills = new Skill[skillsData.length];

        for (int i = 0; i < skills.length; i++) {
            skills[i] = toSkill(skillsData[i]);
        }

        return skills;
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
    private static Status[] processStatuses(JSONObject[] statusesData) {
        Status[] statuses = new Status[statusesData.length];

        for (int i = 0; i < statuses.length; i++) {
            statuses[i] = toStatus(statusesData[i]);
        }

        return statuses;
    }
    private static Status toStatus(JSONObject statusData) {
        // TODO: fill out
    }
    private static MechSystem[] processMechSystems(JSONObject[] mechSystemsData)
    {
        MechSystem[] mechSystems = new MechSystem[mechSystemsData.length];

        for (int i = 0; i < mechSystems.length; i++) {
            mechSystems[i] = toMechSystem(mechSystemsData[i]);
        }

        return mechSystems;
    }
    private static MechSystem toMechSystem(JSONObject mechSystemData) {
        // TODO: fill out
    }
    private static Table[] processTables(JSONObject tablesData) {
        Set<String> keys = tablesData.keySet();
        Table[] tables = new Table[keys.size()];
        int i = 0;

        for (String key : keys) {
            tables[i] = toTable(key, (JSONArray) tablesData.get(key));
            i++;
        }

        return tables;
    }
    private static Table toTable(JSONObject tableData) {
        // TODO: fill out
    }
    private static void processLCPTags(JSONObject[] lcpTagsData) {
        // TODO: fill out
        DataTag[] dataTags = processDataTags(lcpTagsData);

        DataCaster.dataTagProcessed = dataTags;
        DataCaster.tagProcessed = processTags(dataTags);
    }
    private static DataTag[] processDataTags(JSONObject[] dataTagsData) {
        DataTag[] dataTags = new DataTag[dataTagsData.length];

        for (int i = 0; i < dataTags.length; i++) {
            dataTags[i] = toDataTag(dataTagsData[i]);
        }

        return dataTags;
    }
    private static DataTag toDataTag(JSONObject datatagData) {
        // TODO: fill out
    }
    private static Tag[] processTags(DataTag[] tagsData) {
        int numTags = 0;
        Tag[] tags;

        for (int i = 0; i < tagsData.length; i++) {
            if (! tagsData[i].isHidden()) {
                numTags++;
            }
        }
        tags = new Tag[numTags];
        for (int i = 0; i < tagsData.length; i++) {
            if (! tagsData[i].isHidden()) {
                tags[numTags] = tagsData[i].toTag();
                numTags++;
            }
        }

        return tags;
    }
    private static Talent[] processTalents(JSONObject[] talentsData) {
        Talent[] talents = new Talent[talentsData.length];

        for (int i = 0; i < talents.length; i++) {
            talents[i] = toTalent(talentsData[i]);
        }

        return talents;
    }
    private static Talent toTalent(JSONObject talentData) {
        // TODO: fill out
    }
    private static Term[] processTerms(JSONObject[] termsData) {
        Term[] terms = new Term[termsData.length];

        for (int i = 0; i < terms.length; i++) {
            terms[i] = toTerm(termsData[i]);
        }

        return terms;
    }
    private static Term toTerm(JSONObject termData) {
        // TODO: fill out
    }
    private static Weapon[] processWeapons(JSONObject[] weaponsData) {
        Weapon[] weapons = new Weapon[weaponsData.length];

        for (int i = 0; i < weapons.length; i++) {
            weapons[i] = toWeapon(weaponsData[i]);
        }

        return weapons;
    }
    private static Weapon toWeapon(JSONObject weaponData) {
        // TODO: fill out
    }
    private static Object[] packData() {
        Object[] data = new Object[] {
            // ----some critical data types:
            DataCaster.frameProcessed,
            DataCaster.systemProcessed,
            DataCaster.modificationProcessed,
            DataCaster.weaponProcessed,
            // ----the rest of the critical data types:
            DataCaster.actionProcessed,
            DataCaster.conditionProcessed,
            DataCaster.coreBonusProcessed,
            DataCaster.dataTagProcessed,
            DataCaster.tagProcessed,
            DataCaster.manufacturerProcessed,
            DataCaster.npcFeatureProcessed,
            DataCaster.npcTemplateProcessed,
            DataCaster.pilotArmorProcessed,
            DataCaster.pilotGearProcessed,
            DataCaster.pilotWeaponProcessed,
            DataCaster.reserveProcessed,
            DataCaster.skillProcessed,
            DataCaster.statusProcessed,
            DataCaster.talentProcessed,
            // ----less important
            DataCaster.environmentProcessed,
            DataCaster.sitrepProcessed,
            // ----almost unimportant
            DataCaster.backgroundProcessed,
            DataCaster.bondProcessed,
            // ----just for reference
            DataCaster.ruleProcessed,
            DataCaster.termProcessed,
            DataCaster.tableProcessed
        };

        return data;
    }
    private static void passData(Object[] data) {
        // 1. first open DataCompiler so it can receive
        DataCompiler.open();
        // 2. pass the data on
        DataCompiler.receiveData(data);
        // 3. and then close DataCompiler
        DataCompiler.close();
        // 4. and tell DataCompiler to upload it
        DataCompiler.uploadData();
    }
    private static void flushData() {
        // TODO: fill out
        // ----some critical data types:
        frameRaw = new JSONObject[0];
        systemRaw = new JSONObject[0];
        modificationRaw = new JSONObject[0];
        weaponRaw = new JSONObject[0];
        // ----the rest of the critical data types:
        actionRaw = new JSONObject[0];
        coreBonusRaw = new JSONObject[0];
        dataTagRaw = new JSONObject[0];
        manufacturerRaw = new JSONObject[0];
        npcFeatureRaw = new JSONObject[0];
        npcTemplateRaw = new JSONObject[0];
        pilotEquipmentRaw = new JSONObject[0];
        reserveRaw = new JSONObject[0];
        skillRaw = new JSONObject[0];
        stateRaw = new JSONObject[0];
        talentRaw = new JSONObject[0];
        // ----less important
        environmentRaw = new JSONObject[0];
        sitrepRaw = new JSONObject[0];
        // ----almost unimportant
        backgroundRaw = new JSONObject[0];
        bondRaw = new JSONObject[0];
        // ----just for reference
        ruleRaw = null;
        termRaw = new JSONObject[0];
        tableRaw = null;
        // ----some critical data types:
        frameProcessed = new Frame[0];
        systemProcessed = new MechSystem[0];
        modificationProcessed = new Modification[0];
        weaponProcessed = new Weapon[0];
        // ----the rest of the critical data types:
        actionProcessed = new Action[0];
        conditionProcessed = new Condition[0];
        coreBonusProcessed = new CoreBonus[0];
        dataTagProcessed = new DataTag[0];
        tagProcessed = new Tag[0];
        manufacturerProcessed = new Manufacturer[0];
        npcFeatureProcessed = new NPCFeature[0];
        npcTemplateProcessed = new NPCTemplate[0];
        pilotArmorProcessed = new PilotArmor[0];
        pilotGearProcessed = new PilotGear[0];
        pilotWeaponProcessed = new PilotWeapon[0];
        reserveProcessed = new Reserve[0];
        skillProcessed = new Skill[0];
        statusProcessed = new Status[0];
        talentProcessed = new Talent[0];
        // ----less important
        environmentProcessed = new Environment[0];
        sitrepProcessed = new Sitrep[0];
        // ----almost unimportant
        backgroundProcessed = new Background[0];
        bondProcessed = new Bond[0];
        // ----just for reference
        ruleProcessed = new Rule[0];
        termProcessed = new Term[0];
        tableProcessed = new Table[0];
    }
}
