package MainBranch.database.databaseReader;

import java.util.Set;

import MainBranch.Database;
import MainBranch.database.databaseReader.FileReading.json.JSONArray;
import MainBranch.database.databaseReader.FileReading.json.JSONException;
import MainBranch.database.databaseReader.FileReading.json.JSONObject;
import MainBranch.roll.DiceExpression;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.Action;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Modification;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTag.Tag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Damage;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Condition;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Status;

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
    private static JSONObject[] framesRaw;
    private static JSONObject[] systemsRaw;
    private static JSONObject[] modificationsRaw;
    private static JSONObject[] weaponsRaw;
    // ----the rest of the critical data types:
    private static JSONObject[] actionsRaw;
    private static JSONObject[] coreBonusesRaw;
    private static JSONObject[] dataTagsRaw;
    private static JSONObject[] manufacturersRaw;
    private static JSONObject[] npcFeaturesRaw;
    private static JSONObject[] npcTemplatesRaw;
    private static JSONObject[] pilotEquipmentRaw;
    private static JSONObject[] reservesRaw;
    private static JSONObject[] skillsRaw;
    private static JSONObject[] statesRaw;
    private static JSONObject[] talentsRaw;
    // ----less important
    private static JSONObject[] environmentsRaw;
    private static JSONObject[] sitrepsRaw;
    // ----almost unimportant
    private static JSONObject[] backgroundsRaw;
    private static JSONObject[] bondsRaw;
    // ----just for reference
    private static JSONObject rulesRaw;
    private static JSONObject[] termsRaw;
    private static JSONObject tablesRaw;
    // ----some critical data types:
    private static Frame[] framesProcessed;
    private static MechSystem[] systemsProcessed;
    private static Modification[] modificationsProcessed;
    private static Weapon[] weaponsProcessed;
    // ----the rest of the critical data types:
    private static Action[] actionsProcessed;
    private static CoreBonus[] coreBonusesProcessed;
    private static Condition[] conditionsProcessed;
    private static DataTag[] dataTagsProcessed;
    private static Tag[] tagsProcessed;
    private static Manufacturer[] manufacturersProcessed;
    private static NPCFeature[] npcFeaturesProcessed;
    private static NPCTemplate[] npcTemplatesProcessed;
    private static PilotArmor[] pilotArmorProcessed;
    private static PilotGear[] pilotGearProcessed;
    private static PilotWeapon[] pilotWeaponsProcessed;
    private static Reserve[] reservesProcessed;
    private static SkillData[] skillsProcessed;
    private static Status[] statusesProcessed;
    private static TalentData[] talentsProcessed;
    // ----less important
    private static Environment[] environmentsProcessed;
    private static Sitrep[] sitrepsProcessed;
    // ----almost unimportant
    private static Background[] backgroundsProcessed;
    private static Bond[] bondsProcessed;
    // ----just for reference
    private static Rule[] rulesProcessed;
    private static Term[] termsProcessed;
    private static Table[] tablesProcessed;

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
            DataCaster.framesRaw = (JSONObject[]) data[0];
            DataCaster.systemsRaw = (JSONObject[]) data[1];
            DataCaster.modificationsRaw = (JSONObject[]) data[2];
            DataCaster.weaponsRaw = (JSONObject[]) data[3];
            DataCaster.actionsRaw = (JSONObject[]) data[4];
            DataCaster.coreBonusesRaw = (JSONObject[]) data[5];
            DataCaster.dataTagsRaw = (JSONObject[]) data[6];
            DataCaster.manufacturersRaw = (JSONObject[]) data[7];
            DataCaster.npcFeaturesRaw = (JSONObject[]) data[8];
            DataCaster.npcTemplatesRaw = (JSONObject[]) data[9];
            DataCaster.pilotEquipmentRaw = (JSONObject[]) data[10];
            DataCaster.reservesRaw = (JSONObject[]) data[11];
            DataCaster.skillsRaw = (JSONObject[]) data[12];
            DataCaster.statesRaw = (JSONObject[]) data[13];
            DataCaster.talentsRaw = (JSONObject[]) data[14];
            // ----less important
            DataCaster.environmentsRaw = (JSONObject[]) data[15];
            DataCaster.sitrepsRaw = (JSONObject[]) data[16];
            // ----almost unimportant
            DataCaster.backgroundsRaw = (JSONObject[]) data[17];
            DataCaster.bondsRaw = (JSONObject[]) data[18];
            // ----just for reference
            DataCaster.rulesRaw = (JSONObject) data[19];
            DataCaster.termsRaw = (JSONObject[]) data[20];
            DataCaster.tablesRaw = (JSONObject) data[21];
    }
    private static void processData() {
        // then process that data
        // - first open Database so you can create new Frames, etc etc
        Database.open();
        // each Object is actually a JSONObject[] or JSONObject
        // convert those JSONObject[]s and JSONObjects to Action[],
        //     Background[], etc. etc.
        if (! (DataCaster.actionsRaw == null
            || DataCaster.actionsRaw.length < 1)) {
            processActions(DataCaster.actionsRaw);
        }
        if (! (DataCaster.backgroundsRaw == null
            || DataCaster.backgroundsRaw.length < 1)) {
            processBackgrounds(DataCaster.backgroundsRaw);
        
        }
        if (! (DataCaster.bondsRaw == null || DataCaster.bondsRaw.length < 1)) {
            processBonds(DataCaster.bondsRaw);
        }
        // TODO: fix
        if (! (DataCaster.coreBonusesRaw == null
            || DataCaster.coreBonusesRaw.length < 1)) {
            processCoreBonuses(DataCaster.coreBonusesRaw);
        
        }
        if (! (DataCaster.environmentsRaw == null
            || DataCaster.environmentsRaw.length < 1)) {
            processEnvironments(DataCaster.environmentsRaw);
        
        }
        if (! (DataCaster.framesRaw == null || DataCaster.framesRaw.length < 1))
        {
            processFrames(DataCaster.framesRaw);
        
        }
        if (! (DataCaster.manufacturersRaw == null
            || DataCaster.manufacturersRaw.length < 1)) {
            processManufacturers(DataCaster.manufacturersRaw);
        
        }
        if (! (DataCaster.modificationsRaw == null
            || DataCaster.modificationsRaw.length < 1)) {
            processModifications(DataCaster.modificationsRaw);
        
        }
        if (! (DataCaster.npcFeaturesRaw == null
            || DataCaster.npcFeaturesRaw.length < 1)) {
            processNPCFeatures(DataCaster.npcFeaturesRaw);
        
        }
        if (! (DataCaster.npcTemplatesRaw == null
            || DataCaster.npcTemplatesRaw.length < 1)) {
            processNPCTemplates(DataCaster.npcTemplatesRaw);
        
        }
        if (! (DataCaster.pilotEquipmentRaw == null
            || DataCaster.pilotEquipmentRaw.length < 1)) {
            processPilotEquipment(DataCaster.pilotEquipmentRaw);
        
        }
        if (! (DataCaster.reservesRaw == null
            || DataCaster.reservesRaw.length < 1)) {
            processReserves(DataCaster.reservesRaw);
        
        }
        if (! (DataCaster.rulesRaw == null
            || DataCaster.rulesRaw.keySet().size() < 1)) {
            processRules(DataCaster.rulesRaw);
        
        }
        if (! (DataCaster.sitrepsRaw == null
            || DataCaster.sitrepsRaw.length < 1)) {
            processSitreps(DataCaster.sitrepsRaw);
        
        }
        if (! (DataCaster.skillsRaw == null || DataCaster.skillsRaw.length < 1))
        {
            processSkills(DataCaster.skillsRaw);
        
        }
        if (! (DataCaster.statesRaw == null || DataCaster.statesRaw.length < 1))
        {
            processStates(DataCaster.statesRaw);
        
        }
        if (! (DataCaster.systemsRaw == null
            || DataCaster.systemsRaw.length < 1)) {
            processMechSystems(DataCaster.systemsRaw);
        
        }
        if (! (DataCaster.tablesRaw == null
            || DataCaster.tablesRaw.keySet().size() < 1)) {
            processTables(DataCaster.tablesRaw);
        
        }
        if (! (DataCaster.dataTagsRaw == null
            || DataCaster.dataTagsRaw.length < 1)) {
            processLCPTags(DataCaster.dataTagsRaw);
        
        }
        if (! (DataCaster.talentsRaw == null
            || DataCaster.talentsRaw.length < 1)) {
            processTalents(DataCaster.talentsRaw);
        
        }
        if (! (DataCaster.termsRaw == null || DataCaster.termsRaw.length < 1)) {
            processTerms(DataCaster.termsRaw);
        
        }
        if (! (DataCaster.weaponsRaw == null
            || DataCaster.weaponsRaw.length < 1)) {
            processWeapons(DataCaster.weaponsRaw);
        }
        // Remember to take JSONObject[] pilotEquipmentData, stateData, and
        //     split it into its constituent types while it's being casted
        // - and then close Database
        Database.close();
    }
    private static void processActions(JSONObject[] actionsData) {
        Action[] actions = new Action[actionsData.length];

        for (int i = 0; i < actions.length; i++) {
            actions[i] = toAction(actionsData[i]);
        }
        DataCaster.actionsProcessed = actions;
    }
    private static Action toAction(JSONObject actionData) {
        // Required properties
        String id;
        String name;
        String activation;
        String terse;
        String detail;
        // Optional properties
        boolean containsPilot = false;
        boolean containsMech = false;
        boolean pilot = false;
        boolean mech;
        boolean hideActive = false;
        JSONArray synergyLocationsJSON = null;
        String[] synergyLocations;
        JSONArray confirmJSON = null;
        String[] confirm;
        boolean ignoreUsed = false;
        int heatCost = 0;
        boolean techAttack = false;
        Frequency frequency;
        RangeTag[] range = null;
        JSONArray rangeJSON;
        JSONObject rangeObject;
        Damage[] damage = null;
        JSONArray damageJSON;
        JSONObject damageObject;
        Object[] damageVals;
        String trigger = null;
        String init = null;

        id = actionData.getString("id");
        name = actionData.getString("name");
        activation = actionData.getString("activation");
        terse = actionData.getString("terse");
        detail = actionData.getString("detail");
        // Get pilot and mech:
        // The possible states for "pilot" or "mech" being present:
        // A. Neither - means either "activation" is "Downtime" or (false, true)
        // B. "pilot" only - means (true, false)
        // C. "pilot" and "mech" - means (true, true)
        // Notice that as long as the "activation" case and neither property
        //     being present are filtered out, we can just use containsPilot for
        //     pilot and containsMech for mech
        // Don't need to check for properties if "activation" is "Downtime"
        //     because we know neither is present
        if (activation.equals("Downtime")) {
            pilot = true;
            mech = false;
        } else {
            // Otherwise
            // Get containsPilot and containsMech
            try {
                actionData.getBoolean("pilot");
                containsPilot = true;
            } catch (JSONException exception) {}
            try {
                actionData.getBoolean("mech");
                containsMech = true;
            } catch (JSONException exception) {}
            // Filter out the case where neither property is present
            if (! (containsPilot || containsMech)) {
                mech = true;
            } else {
                // Otherwise we can use containsPilot for pilot and containsMech
                //     for mech
                pilot = containsPilot;
                mech = containsMech;
            }
        }
        // Get hideActive
        // It's only true if it's present, and it's always true when it's
        //     present
        try {
            actionData.getBoolean("hide_active");
            hideActive = true;
        } catch (JSONException exception) {}
        // Get synergyLocations
        synergyLocations = new String[0];
        try {
            synergyLocationsJSON =
                actionData.getJSONArray("synergy_locations");
        } catch (JSONException exception) {}
        if (synergyLocationsJSON != null) {
            synergyLocations = new String[synergyLocationsJSON.length()];
            for (int i = 0; i < synergyLocations.length; i++) {
                synergyLocations[i] = synergyLocationsJSON.getString(i);
            }
        }
        // Get confirm
        confirm = new String[0];
        try {
            confirmJSON = actionData.getJSONArray("confirm");
        } catch (JSONException exception) {}
        if (confirmJSON != null) {
            confirm = new String[confirmJSON.length()];
            for (int i = 0; i < confirm.length; i++) {
                confirm[i] = confirmJSON.getString(i);
            }
        }
        // Get ignoreUsed
        try {
            actionData.getBoolean("ignore_used");
            ignoreUsed = true;
        } catch (JSONException exception) {}
        // Get heatCost
        try {
            heatCost = actionData.getInt("heat_cost");
        } catch (JSONException exception) {}
        // Get techAttack
        if (activation.equals("quick tech")
            || activation.equals("full tech")) {
            try {
                actionData.getBoolean("tech_attack");
                techAttack = true;
            } catch (JSONException exception) {
                throw new IllegalArgumentException();
            }
        }
        // Get frequency
        try {
            frequency = new Frequency(actionData.getString("frequency"));
        } catch (JSONException exception) {
            if (ignoreUsed) {
                frequency = new Frequency("unlimited");
            } else {
                frequency = new Frequency("1/round");
            }
        }
        // Get range
        try {
            rangeJSON = actionData.getJSONArray("range");
            range = new RangeTag[rangeJSON.length()];
            for (int i = 0; i < range.length; i++) {
                rangeObject = rangeJSON.getJSONObject(i);
                range[i] = new RangeTag(rangeObject.getString("type"),
                    rangeObject.getInt("value"));
            }
        } catch (JSONException exception) {}
        // Get damage
        try {
            damageJSON = actionData.getJSONArray("damage");
            damage = new Damage[damageJSON.length()];
            for (int i = 0; i < damage.length; i++) {
                damageObject = damageJSON.getJSONObject(i);
                damageVals = Damage.splitDamageString(
                    damageObject.getString("val"));
                damage[i] = new Damage(damageObject.getString("type"),
                    (DiceExpression) damageVals[0], (int) damageVals[1]);
            }
        } catch (JSONException exception) {}
        // Get trigger
        try {
            trigger = actionData.getString("trigger");
        } catch (JSONException exception) {}
        // Get init
        try {
            init = actionData.getString("init");
        } catch (JSONException exception) {}

        return new Action(id, name, activation, terse, detail, pilot, mech,
            hideActive, synergyLocations, confirm, ignoreUsed, heatCost,
            techAttack, frequency, range, damage, trigger, init);
    }
    private static void processBackgrounds(JSONObject[] backgroundsData) {
        Background[] backgrounds = new Background[backgroundsData.length];

        for (int i = 0; i < backgroundsData.length; i++) {
            backgrounds[i] = toBackground(backgroundsData[i]);
        }
        DataCaster.backgroundsProcessed = backgrounds;
    }
    private static Background toBackground(JSONObject backgroundData) {
        // TODO: fill out
        return null;
    }
    private static void processBonds(JSONObject[] bondsData) {
        Bond[] bonds = new Bond[bondsData.length];

        for (int i = 0; i < bondsData.length; i++) {
            bonds[i] = toBond(bondsData[i]);
        }
        DataCaster.bondsProcessed = bonds;
    }
    private static Bond toBond(JSONObject bondData) {
        // TODO: fill out
        return null;
    }
    private static void processCoreBonuses(JSONObject[] coreBonusesData) {
        CoreBonus[] coreBonuses = new CoreBonus[coreBonusesData.length];

        for (int i = 0; i < coreBonusesData.length; i++) {
            coreBonuses[i] = toCoreBonus(coreBonusesData[i]);
        }
        DataCaster.coreBonusesProcessed = coreBonuses;
    }
    private static CoreBonus toCoreBonus(JSONObject coreBonusData) {
        // TODO: fill out
        return null;
    }
    private static void processEnvironments(JSONObject[] environmentsData) {
        Environment[] environments = new Environment[environmentsData.length];

        for (int i = 0; i < environmentsData.length; i++) {
            environments[i] = toEnvironment(environmentsData[i]);
        }
        DataCaster.environmentsProcessed = environments;
    }
    private static Environment toEnvironment(JSONObject environmentData) {
        // TODO: fill out
        return null;
    }
    private static void processFrames(JSONObject[] framesData) {
        Frame[] frames = new Frame[framesData.length];

        for (int i = 0; i < framesData.length; i++) {
            frames[i] = toFrame(framesData[i]);
        }
        DataCaster.framesProcessed = frames;
    }
    private static Frame toFrame(JSONObject frameData) {
        // TODO: fill out
        return null;
    }
    private static void processManufacturers(JSONObject[] manufacturersData) {
        Manufacturer[] manufacturers =
            new Manufacturer[manufacturersData.length];

        for (int i = 0; i < manufacturersData.length; i++) {
            manufacturers[i] = toManufacturer(manufacturersData[i]);
        }
        DataCaster.manufacturersProcessed = manufacturers;
    }
    private static Manufacturer toManufacturer(JSONObject manufacturerData) {
        // TODO: fill out
        return null;
    }
    private static void processModifications(JSONObject[] modificationsData) {
        Modification[] modifications =
            new Modification[modificationsData.length];

        for (int i = 0; i < modificationsData.length; i++) {
            modifications[i] = toModification(modificationsData[i]);
        }
        DataCaster.modificationsProcessed = modifications;
    }
    private static Modification toModification(JSONObject modificationData) {
        // TODO: fill out
        return null;
    }
    private static void processNPCFeatures(JSONObject[] npcFeaturesData) {
        NPCFeature[] npcFeatures = new NPCFeature[npcFeaturesData.length];

        for (int i = 0; i < npcFeaturesData.length; i++) {
            npcFeatures[i] = toNPCFeature(npcFeaturesData[i]);
        }
        DataCaster.npcFeaturesProcessed = npcFeatures;
    }
    private static NPCFeature toNPCFeature(JSONObject npcFeatureData) {
        // TODO: fill out
        return null;
    }
    private static void processNPCTemplates(JSONObject[] npcTemplatesData) {
        NPCTemplate[] npcTemplates = new NPCTemplate[npcTemplatesData.length];

        for (int i = 0; i < npcTemplates.length; i++) {
            npcTemplates[i] = toNPCTemplate(npcTemplatesData[i]);
        }
        DataCaster.npcTemplatesProcessed = npcTemplates;
    }
    private static NPCTemplate toNPCTemplate(JSONObject npcTemplateData) {
        // TODO: fill out
        return null;
    }
    private static void processPilotEquipment(JSONObject[] pilotEquipmentData) {
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
    private static void processPilotArmor(JSONObject[] pilotArmorData) {
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
        DataCaster.pilotArmorProcessed = pilotArmor;
    }
    private static PilotArmor toPilotArmor(JSONObject pilotArmorData) {
        // TODO: fill out
        return null;
    }
    private static void processPilotGear(JSONObject[] pilotGearData) {
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
        DataCaster.pilotGearProcessed = pilotGear;
    }
    private static PilotGear toPilotGear(JSONObject pilotGearData) {
        // TODO: fill out
        return null;
    }
    private static void processPilotWeapons(JSONObject[] pilotWeaponsData) {
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
        DataCaster.pilotWeaponsProcessed = pilotWeapons;
    }
    private static PilotWeapon toPilotWeapon(JSONObject pilotWeaponData) {
        // TODO: fill out
        return null;
    }
    private static void processReserves(JSONObject[] reservesData) {
        Reserve[] reserves = new Reserve[reservesData.length];

        for (int i = 0; i < reserves.length; i++) {
            reserves[i] = toReserve(reservesData[i]);
        }
        DataCaster.reservesProcessed = reserves;
    }
    private static Reserve toReserve(JSONObject reserveData) {
        // TODO: fill out
        return null;
    }
    private static void processRules(JSONObject rulesData) {
        Set<String> keys = rulesData.keySet();
        Rule[] rules = new Rule[keys.size()];
        int i = 0;

        for (String key : keys) {
            rules[i] = toRule(key, (JSONArray) rulesData.get(key));
            i++;
        }
        DataCaster.rulesProcessed = rules;
    }
    private static Rule toRule(String name, JSONArray value) {
        // TODO: fill out
        return null;
    }
    private static void processSitreps(JSONObject[] sitrepsData) {
        Sitrep[] sitreps = new Sitrep[sitrepsData.length];

        for (int i = 0; i < sitreps.length; i++) {
            sitreps[i] = toSitrep(sitrepsData[i]);
        }
        DataCaster.sitrepsProcessed = sitreps;
    }
    private static Sitrep toSitrep(JSONObject sitrepData) {
        // TODO: fill out
        return null;
    }
    private static void processSkills(JSONObject[] skillsData) {
        SkillData[] skills = new SkillData[skillsData.length];

        for (int i = 0; i < skills.length; i++) {
            skills[i] = toSkill(skillsData[i]);
        }
        DataCaster.skillsProcessed = skills;
    }
    private static SkillData toSkill(JSONObject skillData) {
        // TODO: fill out
        return null;
    }
    private static void processStates(JSONObject[] statesData) {
        // TODO: fill out
        DataCaster.processConditions(statesData);
        DataCaster.processStatuses(statesData);
    }
    private static void processConditions(JSONObject[] conditionsData) {
        Condition[] conditions = new Condition[conditionsData.length];

        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = toCondition(conditionsData[i]);
        }
        DataCaster.conditionsProcessed = conditions;
    }
    private static Condition toCondition(JSONObject conditionData) {
        // TODO: fill out
        return null;
    }
    private static void processStatuses(JSONObject[] statusesData) {
        Status[] statuses = new Status[statusesData.length];

        for (int i = 0; i < statuses.length; i++) {
            statuses[i] = toStatus(statusesData[i]);
        }
        DataCaster.statusesProcessed = statuses;
    }
    private static Status toStatus(JSONObject statusData) {
        // TODO: fill out
        return null;
    }
    private static void processMechSystems(JSONObject[] mechSystemsData) {
        MechSystem[] mechSystems = new MechSystem[mechSystemsData.length];

        for (int i = 0; i < mechSystems.length; i++) {
            mechSystems[i] = toMechSystem(mechSystemsData[i]);
        }
        DataCaster.systemsProcessed = mechSystems;
    }
    private static MechSystem toMechSystem(JSONObject mechSystemData) {
        // TODO: fill out
        return null;
    }
    private static void processTables(JSONObject tablesData) {
        Set<String> keys = tablesData.keySet();
        Table[] tables = new Table[keys.size()];
        int i = 0;

        for (String key : keys) {
            tables[i] = toTable(key, (JSONArray) tablesData.get(key));
            i++;
        }
        DataCaster.tablesProcessed = tables;
    }
    private static Table toTable(String key, JSONArray value) {
        String[] data = new String[value.length()];

        for (int i = 0; i < value.length(); i++) {
            data[i] = value.getString(i);
        }

        return new Table(key, data);
    }
    private static void processLCPTags(JSONObject[] lcpTagsData) {
        processDataTags(lcpTagsData);
        processTags(DataCaster.dataTagsProcessed);
    }
    private static void processDataTags(JSONObject[] dataTagsData) {
        DataTag[] dataTags = new DataTag[dataTagsData.length];

        for (int i = 0; i < dataTags.length; i++) {
            dataTags[i] = toDataTag(dataTagsData[i]);
        }
        DataCaster.dataTagsProcessed = dataTags;
    }
    private static DataTag toDataTag(JSONObject datatagData) {
        // TODO: fill out
        return null;
    }
    private static void processTags(DataTag[] tagsData) {
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
        DataCaster.tagsProcessed = tags;
    }
    private static void processTalents(JSONObject[] talentsData) {
        TalentData[] talents = new TalentData[talentsData.length];

        for (int i = 0; i < talents.length; i++) {
            talents[i] = toTalent(talentsData[i]);
        }
        DataCaster.talentsProcessed = talents;
    }
    private static TalentData toTalent(JSONObject talentData) {
        // TODO: fill out
        return null;
    }
    private static void processTerms(JSONObject[] termsData) {
        Term[] terms = new Term[termsData.length];

        for (int i = 0; i < terms.length; i++) {
            terms[i] = toTerm(termsData[i]);
        }
        DataCaster.termsProcessed = terms;
    }
    private static Term toTerm(JSONObject termData) {
        return new Term(termData.getString("name"),
            termData.getString("description"));
    }
    private static void processWeapons(JSONObject[] weaponsData) {
        Weapon[] weapons = new Weapon[weaponsData.length];

        for (int i = 0; i < weapons.length; i++) {
            weapons[i] = toWeapon(weaponsData[i]);
        }
        DataCaster.weaponsProcessed = weapons;
    }
    private static Weapon toWeapon(JSONObject weaponData) {
        // TODO: fill out
        return null;
    }
    private static Object[] packData() {
        Object[] data = new Object[] {
            // ----some critical data types:
            DataCaster.framesProcessed,
            DataCaster.systemsProcessed,
            DataCaster.modificationsProcessed,
            DataCaster.weaponsProcessed,
            // ----the rest of the critical data types:
            DataCaster.actionsProcessed,
            DataCaster.conditionsProcessed,
            DataCaster.coreBonusesProcessed,
            DataCaster.dataTagsProcessed,
            DataCaster.tagsProcessed,
            DataCaster.manufacturersProcessed,
            DataCaster.npcFeaturesProcessed,
            DataCaster.npcTemplatesProcessed,
            DataCaster.pilotArmorProcessed,
            DataCaster.pilotGearProcessed,
            DataCaster.pilotWeaponsProcessed,
            DataCaster.reservesProcessed,
            DataCaster.skillsProcessed,
            DataCaster.statusesProcessed,
            DataCaster.talentsProcessed,
            // ----less important
            DataCaster.environmentsProcessed,
            DataCaster.sitrepsProcessed,
            // ----almost unimportant
            DataCaster.backgroundsProcessed,
            DataCaster.bondsProcessed,
            // ----just for reference
            DataCaster.rulesProcessed,
            DataCaster.termsProcessed,
            DataCaster.tablesProcessed
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
        DataCompiler.saveData();
    }
    private static void flushData() {
        // ----some critical data types:
        DataCaster.framesRaw = new JSONObject[0];
        DataCaster.systemsRaw = new JSONObject[0];
        DataCaster.modificationsRaw = new JSONObject[0];
        DataCaster.weaponsRaw = new JSONObject[0];
        // ----the rest of the critical data types:
        DataCaster.actionsRaw = new JSONObject[0];
        DataCaster.coreBonusesRaw = new JSONObject[0];
        DataCaster.dataTagsRaw = new JSONObject[0];
        DataCaster.manufacturersRaw = new JSONObject[0];
        DataCaster.npcFeaturesRaw = new JSONObject[0];
        DataCaster.npcTemplatesRaw = new JSONObject[0];
        DataCaster.pilotEquipmentRaw = new JSONObject[0];
        DataCaster.reservesRaw = new JSONObject[0];
        DataCaster.skillsRaw = new JSONObject[0];
        DataCaster.statesRaw = new JSONObject[0];
        DataCaster.talentsRaw = new JSONObject[0];
        // ----less important
        DataCaster.environmentsRaw = new JSONObject[0];
        DataCaster.sitrepsRaw = new JSONObject[0];
        // ----almost unimportant
        DataCaster.backgroundsRaw = new JSONObject[0];
        DataCaster.bondsRaw = new JSONObject[0];
        // ----just for reference
        DataCaster.rulesRaw = null;
        DataCaster.termsRaw = new JSONObject[0];
        DataCaster.tablesRaw = null;
        // ----some critical data types:
        DataCaster.framesProcessed = new Frame[0];
        DataCaster.systemsProcessed = new MechSystem[0];
        DataCaster.modificationsProcessed = new Modification[0];
        DataCaster.weaponsProcessed = new Weapon[0];
        // ----the rest of the critical data types:
        DataCaster.actionsProcessed = new Action[0];
        DataCaster.conditionsProcessed = new Condition[0];
        DataCaster.coreBonusesProcessed = new CoreBonus[0];
        DataCaster.dataTagsProcessed = new DataTag[0];
        DataCaster.tagsProcessed = new Tag[0];
        DataCaster.manufacturersProcessed = new Manufacturer[0];
        DataCaster.npcFeaturesProcessed = new NPCFeature[0];
        DataCaster.npcTemplatesProcessed = new NPCTemplate[0];
        DataCaster.pilotArmorProcessed = new PilotArmor[0];
        DataCaster.pilotGearProcessed = new PilotGear[0];
        DataCaster.pilotWeaponsProcessed = new PilotWeapon[0];
        DataCaster.reservesProcessed = new Reserve[0];
        DataCaster.skillsProcessed = new SkillData[0];
        DataCaster.statusesProcessed = new Status[0];
        DataCaster.talentsProcessed = new TalentData[0];
        // ----less important
        DataCaster.environmentsProcessed = new Environment[0];
        DataCaster.sitrepsProcessed = new Sitrep[0];
        // ----almost unimportant
        DataCaster.backgroundsProcessed = new Background[0];
        DataCaster.bondsProcessed = new Bond[0];
        // ----just for reference
        DataCaster.rulesProcessed = new Rule[0];
        DataCaster.termsProcessed = new Term[0];
        DataCaster.tablesProcessed = new Table[0];
    }
}
