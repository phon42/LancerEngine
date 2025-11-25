package MainBranch.database.DatabaseReadingPipeline;

import java.util.HashMap;
import java.util.Set;
import MainBranch.Database;
import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONException;
import MainBranch.database.fileOperations.json.JSONObject;
import MainBranch.database.LCPCorrection;
import Packages.CoreTypes.Rule;
import Packages.CoreTypes.Table;
import Packages.CoreTypes.Term;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.BattlefieldMechanics.Environment;
import Packages.CoreTypes.BattlefieldMechanics.Sitrep;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.NPCFeature;
import Packages.CoreTypes.EntityMechanics.NPCTemplate;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.Action;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Modification;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.Weapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.dataTag.Tag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.UnverifiedCoreBonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background.backgroundBase.UnverifiedBackground;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond.BondPower;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond.BondQuestion;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.SkillData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.skillData.SkillFamily;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.TalentData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus.CoreBonus;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.StateData;
import Packages.CoreTypes.lcpInfo.LCPDependency;
import Packages.CoreTypes.lcpInfo.Version;
import Packages.CoreTypes.lcpInfo.lcpDependency.SemverVersion;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.LCPInfo;

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

    /**
     * The info.json "name" property's value for this LCP, if there is one
     *     (should always be "LANCER Core" if it's set to anything).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private static String infoName;
    /**
     * The info.json "name" property's value for this LCP, if there is one (i.e.
     *     "LANCER: Dustgrave").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private static String lcpManifestName;
    // all the data being held at the moment
    // ----absolutely critical data:
    private static JSONObject[] infoRaw;
    private static JSONObject[] lcpManifestsRaw;
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
    // ----absolutely critical data:
    private static LCPInfo[] lcpInfoProcessed;
    // ----some critical data types:
    private static Frame[] framesProcessed;
    private static MechSystem[] systemsProcessed;
    private static Modification[] modificationsProcessed;
    private static Weapon[] weaponsProcessed;
    // ----the rest of the critical data types:
    private static Action[] actionsProcessed;
    private static ActivationType[] activationTypesProcessed;
    private static UnverifiedCoreBonus[] coreBonusesProcessed;
    private static StateData[] conditionsProcessed;
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
    private static StateData[] statusesProcessed;
    private static TalentData[] talentsProcessed;
    // ----less important
    private static Environment[] environmentsProcessed;
    private static Sitrep[] sitrepsProcessed;
    // ----almost unimportant
    private static UnverifiedBackground[] backgroundsProcessed;
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
        // unpack the Object[], transforming each element from Object to a
        //     JSONObject[] or JSONObject, then putting it in its respective
        //     property (i.e. DataCaster.frameRaw).
        // ----absolutely critical data:
        DataCaster.infoRaw = (JSONObject[]) data[0];
        DataCaster.lcpManifestsRaw = (JSONObject[]) data[1];
        // ----some critical data types:
        DataCaster.framesRaw = (JSONObject[]) data[2];
        DataCaster.systemsRaw = (JSONObject[]) data[3];
        DataCaster.modificationsRaw = (JSONObject[]) data[4];
        DataCaster.weaponsRaw = (JSONObject[]) data[5];
        // ----the rest of the critical data types:
        DataCaster.actionsRaw = (JSONObject[]) data[6];
        DataCaster.coreBonusesRaw = (JSONObject[]) data[7];
        DataCaster.dataTagsRaw = (JSONObject[]) data[8];
        DataCaster.manufacturersRaw = (JSONObject[]) data[9];
        DataCaster.npcFeaturesRaw = (JSONObject[]) data[10];
        DataCaster.npcTemplatesRaw = (JSONObject[]) data[11];
        DataCaster.pilotEquipmentRaw = (JSONObject[]) data[12];
        DataCaster.reservesRaw = (JSONObject[]) data[13];
        DataCaster.skillsRaw = (JSONObject[]) data[14];
        DataCaster.statesRaw = (JSONObject[]) data[15];
        DataCaster.talentsRaw = (JSONObject[]) data[16];
        // ----less important
        DataCaster.environmentsRaw = (JSONObject[]) data[17];
        DataCaster.sitrepsRaw = (JSONObject[]) data[18];
        // ----almost unimportant
        DataCaster.backgroundsRaw = (JSONObject[]) data[19];
        DataCaster.bondsRaw = (JSONObject[]) data[20];
        // ----just for reference
        DataCaster.rulesRaw = (JSONObject) data[21];
        DataCaster.termsRaw = (JSONObject[]) data[22];
        DataCaster.tablesRaw = (JSONObject) data[23];
    }
    private static void processData() {
        // TODO: could improve this further by considering that the LCP has to
        //     have EITHER an info.json OR an lcp_manifest.json file so really
        //     only one boolean variable is needed
        boolean hasInfo;
        boolean hasLCPManifest;

        // then process that data
        // - first open Database so you can create new Frames, etc etc
        Database.open();
        // each Object is actually a JSONObject[] or JSONObject
        // convert those JSONObject[]s and JSONObjects to Action[],
        //     Background[], etc. etc.
        hasInfo = ! (DataCaster.infoRaw == null
            || DataCaster.infoRaw.length < 1);
        hasLCPManifest = ! (DataCaster.lcpManifestsRaw == null
            || DataCaster.lcpManifestsRaw.length < 1);
        if (hasInfo || hasLCPManifest) {
            processLCPInfo(DataCaster.infoRaw, DataCaster.lcpManifestsRaw);
        }
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
    private static void processLCPInfo(JSONObject[] infoData,
        JSONObject[] lcpManifestData) {
        boolean infoValid;
        boolean lcpManifestValid;

        infoValid = infoData != null && infoData.length >= 1;
        lcpManifestValid = lcpManifestData != null
            && lcpManifestData.length >= 1;
        if (infoValid) {
            try {
                DataCaster.infoName = infoData[0].getString("name");
                processInfo(infoData);
            } catch (JSONException exception) {
                infoValid = false;
            }
        }
        if (lcpManifestValid) {
            try {
                DataCaster.lcpManifestName =
                    lcpManifestData[0].getString("name");
                processLCPManifests(lcpManifestData);
            } catch (JSONException exception) {
                lcpManifestValid = false;
            }
        }
        if (! (infoValid || lcpManifestValid)) {
            throw new IllegalStateException("Neither a valid info.json file"
                + " nor a valid lcp_manifest.json file could be found within"
                + " the provided LCP data .json files");
        }
    }
    private static void processInfo(JSONObject[] infoData) {
        processLCPInfoFile("info", infoData);
    }
    private static void processLCPManifests(JSONObject[] lcpManifestData) {
        processLCPInfoFile("lcp_manifest", lcpManifestData);
    }
    private static void processLCPInfoFile(String fileName,
        JSONObject[] lcpInfoData) {
        LCPInfo[] lcpInfo;

        lcpInfoData = performCorrections(fileName, lcpInfoData);
        lcpInfo = new LCPInfo[lcpInfoData.length];
        for (int i = 0; i < lcpInfoData.length; i++) {
            lcpInfo[i] = toLCPInfo(fileName, lcpInfoData[i]);
        }
        DataCaster.lcpInfoProcessed = lcpInfo;
    }
    private static LCPInfo toLCPInfo(String fileName, JSONObject lcpInfoData) {
        // Required properties
        String name = null;
        String author = null;
        String description = null;
        String version = null;
        // Semi-required property
        TriState active;
        // Optional properties
        String itemPrefix = null;
        String imageURL = null;
        String releaseDate = null;
        String website = null;
        JSONArray dependenciesArray;
        LCPDependency[] dependencies = null;
        // Result object
        LCPInfo result;

        if (! (fileName.equals("info")
            || fileName.equals("lcp_manifest"))) {
            throw new IllegalArgumentException("fileName: \"" + fileName + "\""
                + " must be either \"info\" or \"lcp_manifest\"");
        }
        // At this point, we know that the file name is valid
        // Required properties
        try {
            name = lcpInfoData.getString("name");
        } catch (JSONException exception) {
            DataCaster.throwLCPInfoException("name");
        }
        try {
            author = lcpInfoData.getString("author");
        } catch (JSONException exception) {
            DataCaster.throwLCPInfoException("author");
        }
        try {
            description = lcpInfoData.getString("description");
        } catch (JSONException exception) {
            DataCaster.throwLCPInfoException("description");
        }
        try {
            version = lcpInfoData.getString("version");
        } catch (JSONException exception) {
            DataCaster.throwLCPInfoException("version");
        }
        // Semi-required property
        try {
            active = TriState.toTriState(lcpInfoData.getBoolean("active"));
        } catch (JSONException exception) {
            active = TriState.UNSET;
        }
        // Optional properties
        try {
            itemPrefix = lcpInfoData.getString("itemPrefix");
        } catch (JSONException exception) {}
        try {
            imageURL = lcpInfoData.getString("imageURL");
        } catch (JSONException exception) {}
        try {
            website = lcpInfoData.getString("website");
        } catch (JSONException exception) {}
        try {
            dependenciesArray = lcpInfoData.getJSONArray("dependencies");
            dependencies = DataCaster.toLCPDependencies(dependenciesArray);
        } catch (JSONException exception) {
        } catch (IllegalArgumentException exception) {}
        // Now we construct the actual object
        if (fileName.equals("info")) {
            try {
                new Version(version);
            } catch (IllegalArgumentException exception) {
                // version is something like "July 2020 Release", in other
                //     words, a release date instead of a version
                releaseDate = version;
                version = "1.0";
            }
            result = new LCPInfo(name, author, description, version, active,
                itemPrefix, imageURL, website, dependencies, releaseDate);
        } else {
            // fileName is "lcp_manifest"
            result = new LCPInfo(name, author, description, version, active,
                itemPrefix, imageURL, website, dependencies);
        }

        return result;
    }
    private static void throwLCPInfoException(String propertyName) throws
        IllegalStateException {
        throw new IllegalStateException("lcpInfoData does not contain a \""
            + propertyName + "\" property, which is required for an LCPInfo"
            + " object");
    }
    private static LCPDependency[] toLCPDependencies(
        JSONArray dependenciesArray) throws IllegalArgumentException {
        LCPDependency[] dependencies;
        JSONObject jsonObject;

        dependencies = new LCPDependency[dependenciesArray.length()];
        for (int i = 0; i < dependencies.length; i++) {
            try {
                jsonObject = dependenciesArray.getJSONObject(i);
            } catch (JSONException exception) {
                throw new IllegalArgumentException("dependenciesArray caused a"
                    + " JSONException to be thrown with the following message:"
                    + " \"" + exception.getMessage() + "\"");
            }
            try {
                dependencies[i] = DataCaster.toLCPDependency(jsonObject);
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException("dependenciesArray caused"
                    + " an IllegalArgumentException to be thrown with the"
                    + " following message: \"" + exception.getMessage() + "\"");
            }
        }

        return dependencies;
    }
    private static LCPDependency toLCPDependency(JSONObject dependencyData)
        throws IllegalArgumentException {
        // Required properties
        String name;
        String versionString;
        SemverVersion version;
        // Optional property
        String link = null;
        // Result object
        LCPDependency result;

        // Required properties
        try {
            name = dependencyData.getString("name");
        } catch (JSONException exception) {
            throw new IllegalArgumentException("Could not find the required"
                + " property \"name\" in the provided JSONObject");
        }
        try {
            versionString = dependencyData.getString("version");
        } catch (JSONException exception) {
            throw new IllegalArgumentException("Could not find the required"
                + " property \"version\" in the provided JSONObject");
        }
        // Optional property
        try {
            link = dependencyData.getString("link");
        } catch (JSONException exception) {}
        // Calculate property value
        version = new SemverVersion(versionString);
        // Create the actual object
        result = new LCPDependency(name, version, link);

        return result;
    }
    private static void processActions(JSONObject[] actionsData) {
        Action[] actions = new Action[actionsData.length];

        actionsData = performCorrections("actions", actionsData);
        for (int i = 0; i < actions.length; i++) {
            actions[i] = toAction(actionsData[i]);
        }
        DataCaster.actionsProcessed = actions;
    }
    private static Action toAction(JSONObject actionData) {
        // Required properties - ActionBase
        String name;
        String activationString;
        ActivationType activation;
        String detail;
        // Required properties - Action
        String id;
        // Semi-required properties - ActionBase
        TriState pilot;
        TriState mech;
        JSONArray confirmArray;
        String[] confirm = null;
        TriState hideActive;
        // Semi-required properties - Action
        TriState ignoreUsed;
        int heatCost = -1;
        // Conditionally required properties - ActionBase
        String frequencyString;
        Frequency frequency = null;
        String triggerString;
        VueHTMLString trigger = null;
        // Optional properties - ActionBase
        JSONArray synergyLocationsArray;
        String synergyLocationsString;
        SynergyLocation[] synergyLocations = null;
        String initString;
        VueHTMLString init = null;
        // Optional properties - Action
        String terse;
        String log;

        HelperMethods.checkObject("actionData", actionData);
        try {
            // Required properties - ActionBase
            name = actionData.getString("name");
            activationString = actionData.getString("activation");
            detail = actionData.getString("detail");
            // Required properties - Action
            id = actionData.getString("id");
        } catch (JSONException exception) {
            throw new IllegalStateException("actionData threw a"
                + " JSONException during the required properties section of the"
                + " object parsing, which is not allowed");
        }
        activation = new ActivationType(activationString);
        addActivationType(activation);
        // Semi-required properties - ActionBase
        try {
            pilot = TriState.toTriState(actionData.getBoolean("pilot"));
        } catch (JSONException exception) {
            pilot = TriState.UNSET;
        }
        try {
            mech = TriState.toTriState(actionData.getBoolean("mech"));
        } catch (JSONException exception) {
            mech = TriState.UNSET;
        }
        try {
            confirmArray = actionData.getJSONArray("confirm");
            confirm = new String[confirmArray.length()];
            for (int i = 0; i < confirm.length; i++) {
                confirm[i] = confirmArray.getString(i);
            }
        } catch (JSONException exception) {}
        try {
            hideActive = TriState.toTriState(
                actionData.getBoolean("hideActive"));
        } catch (JSONException exception) {
            hideActive = TriState.UNSET;
        }
        // Semi-required properties - Action
        try {
            ignoreUsed = TriState.toTriState(
                actionData.getBoolean("ignoreUsed"));
        } catch (JSONException exception) {
            ignoreUsed = TriState.UNSET;
        }
        try {
            heatCost = actionData.getInt("heatCost");
        } catch (JSONException exception) {}
        // Conditionally required properties - ActionBase
        try {
            frequencyString = actionData.getString("frequency");
            frequency = new Frequency(frequencyString);
        } catch (JSONException exception) {}
        try {
            triggerString = actionData.getString("trigger");
            trigger = new VueHTMLString(triggerString);
        } catch (JSONException exception) {}
        // Optional properties - ActionBase
        try {
            synergyLocationsArray =
                actionData.getJSONArray("synergy_locations");
            synergyLocations =
                new SynergyLocation[synergyLocationsArray.length()];
            for (int i = 0; i < synergyLocations.length; i++) {
                synergyLocationsString = synergyLocationsArray.getString(i);
                synergyLocations[i] =
                    new SynergyLocation(synergyLocationsString);
            }
        } catch (JSONException exception) {}
        try {
            initString = actionData.getString("init");
            init = new VueHTMLString(initString);
        } catch (JSONException exception) {}
        // Optional properties - Action
        terse = getOptionalString(actionData, "terse");
        log = getOptionalString(actionData, "log");

        return new Action(name, activation, detail, pilot, mech, confirm,
            hideActive, frequency, trigger, null, synergyLocations,
            init, id, ignoreUsed, heatCost, terse, log);
    }
    private static void processBackgrounds(JSONObject[] backgroundsData) {
        UnverifiedBackground[] backgrounds =
            new UnverifiedBackground[backgroundsData.length];

        backgroundsData = performCorrections("backgrounds",
            backgroundsData);
        for (int i = 0; i < backgroundsData.length; i++) {
            backgrounds[i] = toBackground(backgroundsData[i]);
        }
        DataCaster.backgroundsProcessed = backgrounds;
    }
    private static UnverifiedBackground toBackground(JSONObject backgroundData)
    {
        // Required properties
        String id;
        String name;
        String description;
        // Optional property
        JSONArray skillsArray;
        String[] skills;

        // Required properties
        try {
            id = backgroundData.getString("id");
            name = backgroundData.getString("name");
            description = backgroundData.getString("description");
        } catch (JSONException exception) {
            throw new IllegalStateException("backgroundData threw a"
                + " JSONException during the required properties section of the"
                + " object parsing, which is not allowed");
        }
        // Optional property
        try {
            skillsArray = backgroundData.getJSONArray("skills");
            skills = new String[skillsArray.length()];
            for (int i = 0; i < skills.length; i++) {
                skills[i] = skillsArray.getString(i);
            }
        } catch (JSONException exception) {}

        return new UnverifiedBackground(id, name, description,
            null);
    }
    private static void processBonds(JSONObject[] bondsData) {
        Bond[] bonds = new Bond[bondsData.length];

        bondsData = performCorrections("bonds", bondsData);
        for (int i = 0; i < bondsData.length; i++) {
            bonds[i] = toBond(bondsData[i]);
        }
        DataCaster.bondsProcessed = bonds;
    }
    private static Bond toBond(JSONObject bondData) {
        // Required properties
        String id;
        String name;
        //     majorIdeals
        JSONArray majorIdealsArray;
        String[] majorIdeals;
        //     minorIdeals
        JSONArray minorIdealsArray;
        String[] minorIdeals;
        //     questions
        JSONArray questionsArray;
        JSONObject questionsObject;
        JSONArray questionsArray2;
        String[] questionsStrings;
        BondQuestion[] questions;
        //     powers
        JSONArray powersArray;
        JSONObject powersObject;
        TriState powersMaster;
        TriState powersVeteran;
        String powersPrerequisite = null;
        Frequency powersFrequency = null;
        BondPower[] powers;

        try {
            // Required properties
            id = bondData.getString("id");
            name = bondData.getString("name");
            //     majorIdeals
            majorIdealsArray = bondData.getJSONArray("major_ideals");
            majorIdeals = new String[majorIdealsArray.length()];
            for (int i = 0; i < majorIdeals.length; i++) {
                majorIdeals[i] = majorIdealsArray.getString(i);
            }
            //     minorIdeals
            minorIdealsArray = bondData.getJSONArray("major_ideals");
            minorIdeals = new String[minorIdealsArray.length()];
            for (int i = 0; i < minorIdeals.length; i++) {
                minorIdeals[i] = minorIdealsArray.getString(i);
            }
            //     questions
            questionsArray = bondData.getJSONArray("questions");
            questions = new BondQuestion[questionsArray.length()];
            for (int i = 0; i < questions.length; i++) {
                questionsObject = questionsArray.getJSONObject(i);
                questionsArray2 = questionsObject.getJSONArray("options");
                questionsStrings = new String[questionsArray2.length()];
                for (int j = 0; j < questionsStrings.length; j++) {
                    questionsStrings[j] = questionsArray2.getString(j);
                }
                questions[i] = new BondQuestion(
                    questionsObject.getString("question"),
                    questionsStrings);
            }
            //     powers
            powersArray = bondData.getJSONArray("powers");
            powers = new BondPower[powersArray.length()];
            for (int i = 0; i < powers.length; i++) {
                powersObject = powersArray.getJSONObject(i);
                try {
                    powersMaster = TriState.toTriState(
                        powersObject.getBoolean("master"));
                } catch (JSONException exception) {
                    powersMaster = TriState.UNSET;
                }
                try {
                    powersVeteran = TriState.toTriState(
                        powersObject.getBoolean("veteran"));
                } catch (JSONException exception) {
                    powersVeteran = TriState.UNSET;
                }
                try {
                    powersPrerequisite =
                        powersObject.getString("prerequisite");
                } catch (JSONException exception) {}
                try {
                    powersFrequency =
                        new Frequency(powersObject.getString("frequency"));
                } catch (JSONException exception) {}
                powers[i] = new BondPower(powersObject.getString("name"),
                    powersObject.getString("description"), powersMaster,
                    powersVeteran, powersPrerequisite, powersFrequency);
            }
        } catch (JSONException exception) {
            throw new IllegalStateException("bondData threw a JSONException"
                + " during the required properties section of the object"
                + " parsing, which is not allowed");
        }

        return new Bond(id, name, majorIdeals, minorIdeals, questions, powers);
    }
    private static void processCoreBonuses(JSONObject[] coreBonusesData) {
        UnverifiedCoreBonus[] coreBonuses =
            new CoreBonus[coreBonusesData.length];

        coreBonusesData = performCorrections("core_bonuses",
            coreBonusesData);
        for (int i = 0; i < coreBonusesData.length; i++) {
            coreBonuses[i] = toCoreBonus(coreBonusesData[i]);
        }
        DataCaster.coreBonusesProcessed = coreBonuses;
    }
    private static UnverifiedCoreBonus toCoreBonus(JSONObject coreBonusData) {
        // Required properties
        String id;
        String name;
        String source;
        String effect;
        String description;
        // Optional properties
        String mountedEffect;
        JSONArray actionsArray;
        IActionData[] actions = null;
        JSONArray bonusesArray;
        IBonusData[] bonuses = null;
        JSONArray synergiesArray;
        ISynergyData[] synergies = null;
        JSONArray deployablesArray;
        IDeployableData[] deployables = null;
        JSONArray countersArray;
        Counter[] counters = null;
        JSONArray integratedArray;
        String[] integrated = null;
        JSONArray specialEquipmentArray;
        String[] specialEquipment = null;

        // Required properties
        try {
            id = coreBonusData.getString("id");
            name = coreBonusData.getString("name");
            source = coreBonusData.getString("source");
            effect = coreBonusData.getString("effect");
            description = coreBonusData.getString("description");
        } catch (JSONException exception) {
            throw new IllegalStateException("coreBonusData threw a"
                + " JSONException during the required properties section of the"
                + " object parsing, which is not allowed");
        }
        // Optional properties
        mountedEffect = getOptionalString(coreBonusData,
            "mounted_effect");
        try {
            actionsArray = coreBonusData.getJSONArray("actions");
            try {
                actions = new IActionData[actionsArray.length()];
                for (int i = 0; i < actions.length; i++) {
                    actions[i] = toIActionData(actionsArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " actionsArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            bonusesArray = coreBonusData.getJSONArray("bonuses");
            try {
                bonuses = new IBonusData[bonusesArray.length()];
                for (int i = 0; i < bonuses.length; i++) {
                    bonuses[i] = toIBonusData(bonusesArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " bonusesArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            synergiesArray = coreBonusData.getJSONArray("synergies");
            try {
                synergies = new ISynergyData[synergiesArray.length()];
                for (int i = 0; i < synergies.length; i++) {
                    synergies[i] =
                        toISynergyData(synergiesArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " synergiesArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            deployablesArray = coreBonusData.getJSONArray("deployables");
            try {
                deployables = new IDeployableData[deployablesArray.length()];
                for (int i = 0; i < deployables.length; i++) {
                    deployables[i] =
                        toIDeployableData(deployablesArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " deployablesArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            countersArray = coreBonusData.getJSONArray("counters");
            try {
                counters = new Counter[countersArray.length()];
                for (int i = 0; i < counters.length; i++) {
                    counters[i] = toCounter(countersArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " countersArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            integratedArray = coreBonusData.getJSONArray("integrated");
            try {
                integrated = new String[integratedArray.length()];
                for (int i = 0; i < integrated.length; i++) {
                    integrated[i] = integratedArray.getString(i);
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " integratedArray threw a JSONException");
            }
        } catch (JSONException exception) {}
        try {
            specialEquipmentArray = coreBonusData.getJSONArray(
                "special_equipment");
            try {
                specialEquipment = new String[specialEquipmentArray.length()];
                for (int i = 0; i < specialEquipment.length; i++) {
                    specialEquipment[i] = specialEquipmentArray.getString(i);
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to process"
                    + " specialEquipmentArray threw a JSONException");
            }
        } catch (JSONException exception) {}

        return new CoreBonus(id, name, source, mountedEffect, name,
            mountedEffect, actions, null, synergies, null,
            counters, integrated, specialEquipment);
    }
    private static void processEnvironments(JSONObject[] environmentsData) {
        Environment[] environments = new Environment[environmentsData.length];

        environmentsData = performCorrections("environments",
            environmentsData);
        for (int i = 0; i < environmentsData.length; i++) {
            environments[i] = toEnvironment(environmentsData[i]);
        }
        DataCaster.environmentsProcessed = environments;
    }
    private static Environment toEnvironment(JSONObject environmentData) {
        String id;
        String name;
        String description;

        try {
            id = environmentData.getString("id");
            name = environmentData.getString("name");
            description = environmentData.getString("description");
        } catch (JSONException exception) {
            throw new IllegalStateException("environmentData threw a"
                + " JSONException during the required properties section of the"
                + " object parsing, which is not allowed");
        }

        return new Environment(id, name, description);
    }
    private static void processFrames(JSONObject[] framesData) {
        Frame[] frames = new Frame[framesData.length];

        framesData = performCorrections("frames", framesData);
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

        manufacturersData = performCorrections("manufacturers",
            manufacturersData);
        for (int i = 0; i < manufacturersData.length; i++) {
            manufacturers[i] = toManufacturer(manufacturersData[i]);
        }
        DataCaster.manufacturersProcessed = manufacturers;
    }
    private static Manufacturer toManufacturer(JSONObject manufacturerData) {
        // Required properties
        String id;
        String name;
        String logo;
        String light;
        String dark;
        String quote;
        // Optional properties
        String logoURL;
        String description;

        // Required properties
        try {
            id = manufacturerData.getString("id");
            name = manufacturerData.getString("name");
            logo = manufacturerData.getString("logo");
            light = manufacturerData.getString("light");
            dark = manufacturerData.getString("dark");
            quote = manufacturerData.getString("quote");
        } catch (JSONException exception) {
            throw new IllegalStateException("manufacturerData threw a"
                + " JSONException during the required properties section of the"
                + " object parsing, which is not allowed");
        }
        // Optional properties
        logoURL = getOptionalString(manufacturerData, "logo_url");
        description = getOptionalString(manufacturerData,
            "description");

        return new Manufacturer(id, name, logo, light, dark, quote, logoURL,
            description);
    }
    private static void processModifications(JSONObject[] modificationsData) {
        Modification[] modifications =
            new Modification[modificationsData.length];

        modificationsData = performCorrections("mods",
            modificationsData);
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

        npcFeaturesData = performCorrections("npcFeatures",
            npcFeaturesData);
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

        npcTemplatesData = performCorrections("npcTemplates",
            npcTemplatesData);
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

        pilotEquipmentData = performCorrections("pilot_gear",
            pilotEquipmentData);
        for (int i = 0; i < pilotEquipmentData.length; i++) {
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

        reservesData = performCorrections("reserves", reservesData);
        for (int i = 0; i < reserves.length; i++) {
            reserves[i] = toReserve(reservesData[i]);
        }
        DataCaster.reservesProcessed = reserves;
    }
    private static Reserve toReserve(JSONObject reserveData) {
        // TODO: fill out
        return null;
    }
    private static void processRules(JSONObject rulesObject) {
        Set<String> keys;
        JSONObject[] rulesData;
        HashMap<String, Object> map;
        int i = 0;
        Rule[] rules;

        // Trying to convert a JSONObject to a JSONObject[] where each element
        //     is a JSONObject of the form:
        // {
        //     "property_name": <the key name from rulesObject as a String>
        //     "value": <whatever the value for that key is>
        // }
        // 1. Get the keys of the object we were given
        keys = rulesObject.keySet();
        // 2. Set up the JSONObject[]. We know that we will end up with an array
        //     of length (keys.size()).
        rulesData = new JSONObject[keys.size()];
        i = 0;
        for (String key : keys) {
            // 3. Create a new HashMap of the form:
            // {
            //     "property_name": <the key as a String>,
            //     "value": <the value associated with that key>
            // }
            map = new HashMap<String, Object>();
            map.put("property_name", key);
            map.put("value", rulesObject.get(key));
            // 4. Turn that HashMap into a JSONObject:
            rulesData[i] = new JSONObject(map);
            i++;
            // 5. (Repeat until the array is complete)
        }
        // (rulesData is now a JSONObject[] of the correct form)
        // Perform corrections
        rulesData = performCorrections("rules", rulesData);
        // Create an array of the correct size
        rules = new Rule[keys.size()];
        // Now actually do the conversions
        for (i = 0; i < keys.size(); i++) {
            rules[i] = toRule(rulesData[i]);
        }
        DataCaster.rulesProcessed = rules;
    }
    private static Rule toRule(JSONObject ruleData) {
        // TODO: fill out
        return null;
    }
    private static void processSitreps(JSONObject[] sitrepsData) {
        Sitrep[] sitreps = new Sitrep[sitrepsData.length];

        sitrepsData = performCorrections("sitreps", sitrepsData);
        for (int i = 0; i < sitreps.length; i++) {
            sitreps[i] = toSitrep(sitrepsData[i]);
        }
        DataCaster.sitrepsProcessed = sitreps;
    }
    private static Sitrep toSitrep(JSONObject sitrepData) {
        // Required properties
        String id;
        String name;
        String description;
        // Optional properties
        String pcVictory;
        String enemyVictory;
        String noVictory;
        String deployment;
        String objective;
        String extraction;
        String controlZone;

        // Required properties
        try {
            id = sitrepData.getString("id");
            name = sitrepData.getString("name");
            description = sitrepData.getString("description");
        } catch (JSONException exception) {
            throw new IllegalStateException("sitrepData threw a JSONException"
                + " during the required properties section of the object"
                + " parsing, which is not allowed");
        }
        // Optional properties
        pcVictory = getOptionalString(sitrepData, "pc_victory");
        enemyVictory = getOptionalString(sitrepData,
            "enemy_victory");
        noVictory = getOptionalString(sitrepData, "no_victory");
        deployment = getOptionalString(sitrepData, "deployment");
        objective = getOptionalString(sitrepData, "objective");
        extraction = getOptionalString(sitrepData, "extraction");
        controlZone = getOptionalString(sitrepData,
            "control_zone");

        return new Sitrep(id, name, description, pcVictory, enemyVictory,
            noVictory, deployment, objective, extraction, controlZone);
    }
    private static void processSkills(JSONObject[] skillsData) {
        SkillData[] skills = new SkillData[skillsData.length];

        skillsData = performCorrections("skills", skillsData);
        for (int i = 0; i < skills.length; i++) {
            skills[i] = toSkill(skillsData[i]);
        }
        DataCaster.skillsProcessed = skills;
    }
    private static SkillData toSkill(JSONObject skillData) {
        String id;
        String name;
        String description;
        String detail;
        String family;

        try {
            id = skillData.getString("id");
            name = skillData.getString("name");
            description = skillData.getString("description");
            detail = skillData.getString("detail");
            family = skillData.getString("family");
        } catch (JSONException exception) {
            throw new IllegalStateException("skillData threw a JSONException"
                + " during the required properties section of the object"
                + " parsing, which is not allowed");
        }

        return new SkillData(id, name, description, detail,
            SkillFamily.fromString(family));
    }
    private static void processStates(JSONObject[] statesData) {
        StateData[] states = new StateData[statesData.length];

        statesData = performCorrections("states", statesData);
        for (int i = 0; i < states.length; i++) {
            states[i] = toState(statesData[i]);
        }
        DataCaster.processConditions(states);
        DataCaster.processStatuses(states);
    }
    private static StateData toState(JSONObject stateData) {
        // Required properties
        String name;
        String iconURL;
        String type;
        boolean isStatus = false;
        String effects;
        String exclusive;
        boolean mechAffected = true;
        boolean pilotAffected = true;

        // Optional properties
        String terse;
        JSONArray stateEffectsArray;
        int length;
        StateData[] stateEffects = null;

        // Required properties
        try {
            name = stateData.getString("name");
            // TODO: the LCP documentation page states that statuses.json
            //     objects should have an "icon_url" property that is required;
            //     however, objects in actual LCPs seem to instead have "icon"
            //     properties which do not resemble a URL but could conceivably
            //     be used as locators
            iconURL = stateData.getString("icon");
            type = stateData.getString("type");
            if (type.equals("Condition")) {
            } else if (type.equals("Status")) {
                isStatus = true;
            } else {
                throw new IllegalStateException("stateData's \"type\" property"
                    + " had a value of: \"" + type + "\" which is neither"
                    + " \"Condition\" nor \"Status\"");
            }
            effects = stateData.getString("effects");
        } catch (JSONException exception) {
            throw new IllegalStateException("stateData threw a JSONException"
                + " during the required properties section of the object"
                + " parsing, which is not allowed");
        }
        exclusive = getOptionalString(stateData, "exclusive");
        if (exclusive == null) {
        } else if (exclusive.equals("Mech")) {
            pilotAffected = false;
        } else if (exclusive.equals("Pilot")) {
            mechAffected = false;
        }
        // Optional properties
        terse = getOptionalString(stateData, "terse");
        try {
            stateEffectsArray = stateData.getJSONArray("state_effects");
            try {
                length = stateEffectsArray.length();
                stateEffects = new StateData[length];
                for (int i = 0; i < length; i++) {
                    stateEffects[i] = toState(
                        stateEffectsArray.getJSONObject(i));
                }
            } catch (JSONException exception) {
                throw new IllegalStateException("Attempting to parse one of"
                    + " the elements of the \"state_effects\" property of"
                    + " stateData caused a JSONException to be thrown");
            }
        } catch (JSONException exception) {}

        return new StateData(name, iconURL, isStatus, effects, mechAffected,
            pilotAffected, terse, stateEffects);
    }
    private static void processConditions(StateData[] conditionsData) {
        StateData[] conditions;
        int numElements = 0;

        for (int i = 0; i < conditionsData.length; i++) {
            if (conditionsData[i].isStatus()) {
                continue;
            }
            numElements++;
        }
        numElements = 0;
        conditions = new StateData[numElements];
        for (int i = 0; i < conditions.length; i++) {
            if (conditionsData[i].isStatus()) {
                continue;
            }
            conditions[numElements] = conditionsData[i];
            numElements++;
        }
        DataCaster.conditionsProcessed = conditions;
    }
    private static void processStatuses(StateData[] statusesData) {
        StateData[] statuses;
        int numElements = 0;

        for (int i = 0; i < statusesData.length; i++) {
            if (! statusesData[i].isStatus()) {
                continue;
            }
            numElements++;
        }
        numElements = 0;
        statuses = new StateData[numElements];
        for (int i = 0; i < statuses.length; i++) {
            if (! statusesData[i].isStatus()) {
                continue;
            }
            statuses[numElements] = statusesData[i];
            numElements++;
        }
        DataCaster.statusesProcessed = statuses;
    }
    private static void processMechSystems(JSONObject[] mechSystemsData) {
        MechSystem[] mechSystems = new MechSystem[mechSystemsData.length];

        mechSystemsData = performCorrections("systems",
            mechSystemsData);
        for (int i = 0; i < mechSystems.length; i++) {
            mechSystems[i] = toMechSystem(mechSystemsData[i]);
        }
        DataCaster.systemsProcessed = mechSystems;
    }
    private static MechSystem toMechSystem(JSONObject mechSystemData) {
        // TODO: fill out
        return null;
    }
    private static void processTables(JSONObject tablesObject) {
        Set<String> keys;
        JSONObject[] tablesData;
        HashMap<String, Object> map;
        int i = 0;
        JSONArray tableArray;
        Object[] table;
        Table[] tables;

        // Trying to convert a JSONObject to a JSONObject[] where each element
        //     is a JSONObject of the form:
        // {
        //     "property_name": <the key name from rulesObject as a String>
        //     "value": <the array which is the value for that key>
        // }
        // 1. Get the keys of the object we were given
        keys = tablesObject.keySet();
        // 2. Set up the JSONObject[]. We know that we will end up with an array
        //     of length (keys.size()).
        tablesData = new JSONObject[keys.size()];
        i = 0;
        for (String key : keys) {
            // 3. Create a new HashMap of the form:
            // {
            //     "property_name": <the key as a String>,
            //     "value": <the array which is the value for that key>
            // }
            map = new HashMap<String, Object>();
            map.put("property_name", key);
            // 4. Convert the value from a JSONArray to an Object[]:
            tableArray = tablesObject.getJSONArray(key);
            table = new Object[tableArray.length()];
            for (int j = 0; j < tableArray.length(); j++) {
                table[j] = tableArray.get(j);
            }
            map.put("value", table);
            // 5. Turn that HashMap into a JSONObject:
            tablesData[i] = new JSONObject(map);
            i++;
            // 6. (Repeat until the array is complete)
        }
        // (tablesData is now a JSONObject[] of the correct form)
        // Perform corrections
        tablesData = performCorrections("tables", tablesData);
        // Create an array of the correct size
        tables = new Table[keys.size()];
        // Now actually do the conversions
        for (i = 0; i < keys.size(); i++) {
            tables[i] = toTable(tablesData[i]);
        }
        DataCaster.tablesProcessed = tables;
    }
    private static Table toTable(JSONObject table) {
        String propertyName;
        JSONArray dataArray;
        Object[] data;

        propertyName = table.getString("property_name");
        dataArray = (JSONArray) table.get("value");
        data = new Object[dataArray.length()];
        for (int i = 0; i < dataArray.length(); i++) {
            data[i] = dataArray.get(i);
        }

        return new Table(propertyName, data);
    }
    private static void processLCPTags(JSONObject[] lcpTagsData) {
        processDataTags(lcpTagsData);
        processTags(DataCaster.dataTagsProcessed);
    }
    private static void processDataTags(JSONObject[] dataTagsData) {
        DataTag[] dataTags = new DataTag[dataTagsData.length];

        dataTagsData = performCorrections("tags", dataTagsData);
        for (int i = 0; i < dataTags.length; i++) {
            dataTags[i] = toDataTag(dataTagsData[i]);
        }
        DataCaster.dataTagsProcessed = dataTags;
    }
    private static DataTag toDataTag(JSONObject dataTagData) {
        String id;
        String name;
        String description;
        boolean hidden = false;
        boolean filterIgnore = false;

        try {
            id = dataTagData.getString("id");
            name = dataTagData.getString("name");
            description = dataTagData.getString("description");
        } catch (JSONException exception) {
            throw new IllegalStateException("dataTagData threw a JSONException"
                + " during the required properties section of the object"
                + " parsing, which is not allowed");
        }
        try {
            hidden = dataTagData.getBoolean("hidden");
        } catch (JSONException exception) {}
        try {
            filterIgnore = dataTagData.getBoolean("filter_ignore");
        } catch (JSONException exception) {}

        return new DataTag(id, name, description, filterIgnore, hidden);
    }
    private static void processTags(DataTag[] tagsData) {
        Tag[] tags;
        int numTags = 0;
        Tag[] newTags;

        tags = new Tag[tagsData.length];
        for (int i = 0; i < tagsData.length; i++) {
            try {
                tags[i] = tagsData[i].toTag();
                numTags++;
            } catch (IllegalArgumentException exception) {}
        }
        newTags = new Tag[numTags];
        numTags = 0;
        for (int i = 0; i < tags.length; i++) {
            if (tags[i] == null) {
                continue;
            }
            newTags[numTags] = tags[i];
            numTags++;
        }
        DataCaster.tagsProcessed = newTags;
    }
    private static void processTalents(JSONObject[] talentsData) {
        TalentData[] talents = new TalentData[talentsData.length];

        talentsData = performCorrections("talents", talentsData);
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
        Term[] terms;

        termsData = performCorrections("glossary", termsData);
        terms = new Term[termsData.length];
        for (int i = 0; i < termsData.length; i++) {
            terms[i] = toTerm(termsData[i]);
        }
        DataCaster.termsProcessed = terms;
    }
    private static Term toTerm(JSONObject termData) {
        String name;
        String description;

        name = termData.getString("name");
        description = termData.getString("description");

        return new Term(name, description);
    }
    private static void processWeapons(JSONObject[] weaponsData) {
        Weapon[] weapons = new Weapon[weaponsData.length];

        weaponsData = performCorrections("weapons", weaponsData);
        for (int i = 0; i < weapons.length; i++) {
            weapons[i] = toWeapon(weaponsData[i]);
        }
        DataCaster.weaponsProcessed = weapons;
    }
    private static Weapon toWeapon(JSONObject weaponData) {
        // TODO: fill out
        return null;
    }
    private static void addActivationType(ActivationType activationType) {
        DataCaster.activationTypesProcessed =
            HelperMethods.append(DataCaster.activationTypesProcessed,
                activationType);
    }
    private static JSONObject[] performCorrections(String fileName,
        JSONObject[] data) {
        LCPCorrection[] corrections;

        corrections = Database.getLCPCorrections(DataCaster.infoName,
            DataCaster.lcpManifestName, fileName);
        for (LCPCorrection correction : corrections) {
            for (int i = 0; i < data.length; i++) {
                if (correction.isTargetObject(data[i])) {
                    data[i] = correction.getReplacement();
                    // TODO: decide whether to break here or not; the difference
                    //     is replacing the first object vs replacing every
                    //     object that fits the search parameters
                }
            }
        }
        return data;
    }
    private static String getOptionalString(JSONObject originObject,
        String propertyName) {
        HelperMethods.checkObject("originObject", originObject);
        HelperMethods.checkString("propertyName", propertyName);
        try {
            return originObject.getString(propertyName);
        } catch (JSONException exception) {
            // do nothing 'cause it's an optional property
        }

        return null;
    }
    private static Object[] packData() {
        Object[] data = new Object[] {
            // ----absolutely critical data:
            DataCaster.lcpInfoProcessed,
            // ----some critical data types:
            DataCaster.framesProcessed,
            DataCaster.systemsProcessed,
            DataCaster.modificationsProcessed,
            DataCaster.weaponsProcessed,
            // ----the rest of the critical data types:
            DataCaster.actionsProcessed,
            DataCaster.activationTypesProcessed,
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
        DataCaster.infoName = null;
        DataCaster.lcpManifestName = null;
        // ----absolutely critical data:
        DataCaster.infoRaw = new JSONObject[0];
        DataCaster.lcpManifestsRaw = new JSONObject[0];
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
        // ----absolutely critical data:
        DataCaster.lcpInfoProcessed = new LCPInfo[0];
        // ----some critical data types:
        DataCaster.framesProcessed = new Frame[0];
        DataCaster.systemsProcessed = new MechSystem[0];
        DataCaster.modificationsProcessed = new Modification[0];
        DataCaster.weaponsProcessed = new Weapon[0];
        // ----the rest of the critical data types:
        DataCaster.actionsProcessed = new Action[0];
        DataCaster.activationTypesProcessed = new ActivationType[0];
        DataCaster.conditionsProcessed = new StateData[0];
        DataCaster.coreBonusesProcessed = new UnverifiedCoreBonus[0];
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
        DataCaster.statusesProcessed = new StateData[0];
        DataCaster.talentsProcessed = new TalentData[0];
        // ----less important
        DataCaster.environmentsProcessed = new Environment[0];
        DataCaster.sitrepsProcessed = new Sitrep[0];
        // ----almost unimportant
        DataCaster.backgroundsProcessed = new UnverifiedBackground[0];
        DataCaster.bondsProcessed = new Bond[0];
        // ----just for reference
        DataCaster.rulesProcessed = new Rule[0];
        DataCaster.termsProcessed = new Term[0];
        DataCaster.tablesProcessed = new Table[0];
    }
}
