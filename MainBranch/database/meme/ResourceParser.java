package MainBranch.database.meme;

import java.nio.file.Paths;
import MainBranch.HelperMethods;
import MainBranch.database.DatabaseReadingPipeline.DataCaster;
import MainBranch.database.meme.json.JSONArray;
import MainBranch.database.meme.json.JSONObject;

public class ResourceParser {
    // All the data being held at the moment, as JSONObject[]s:
    // ----very critical data:
    private static JSONObject[] frameData;
    private static JSONObject[] systemData;
    private static JSONObject[] modificationData;
    private static JSONObject[] weaponData;
    // ----critical data:
    private static JSONObject[] actionData;
    private static JSONObject[] coreBonusData;
    private static JSONObject[] dataTagData;
    private static JSONObject[] manufacturerData;
    private static JSONObject[] npcFeatureData;
    private static JSONObject[] npcTemplateData;
    private static JSONObject[] pilotEquipmentData;
    private static JSONObject[] reserveData;
    private static JSONObject[] skillData;
    private static JSONObject[] stateData;
    private static JSONObject[] talentData;
    // ----less important data:
    private static JSONObject[] environmentData;
    private static JSONObject[] sitrepData;
    // ----pretty unimportant data:
    private static JSONObject[] backgroundData;
    private static JSONObject[] bondData;
    // ----just for reference:
    private static JSONObject ruleData;
    private static JSONObject[] termData;
    private static JSONObject tableData;

    static {
        flushData();
    }

    // prevent user from instantiating this class
    private ResourceParser() {}

    /**
     * Parse the provided .json data, then save its contents.
     * @param jsonPath a String which contains the file path from which the JSON
     *     data was extracted. Is assumed to be accurate. Cannot be null.
     * @param fileData a String which contains the contents of a .json file. Is
     *     assumed to be the contents of a .json file. Cannot be null.
     */
    public static void parseJSON(String jsonPath, String fileData) {
        String fileName;
        JSONObject[] data;
        JSONArray jsonArray;

        // Check whether jsonPath or fileData is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        HelperMethods.checkObject("fileData", fileData);
        // Extract the file name
        fileName = Paths.get(jsonPath).toFile().getName();
        // TODO: check to make sure this actually works as expected
        if (fileName.indexOf(".") != -1) {
            fileName = fileName.split("\\.")[0];
        }
        // Convert the file data to an object and then to a JSONObject[]
        if (fileName.equals("rules")
            || fileName.equals("tables")) {
            // going to be receiving a JSONObject
            data = new JSONObject[] {JSON.toJSONObject(fileData)};
        } else {
            // going to be receiving a JSONArray
            jsonArray = JSON.toJSONArray(fileData);
            data = new JSONObject[jsonArray.length()];
            for (int i = 0; i < data.length; i++) {
                data[i] = jsonArray.getJSONObject(i);
            }
        }
        // either way, at this point we have data set to a JSONObject[] of our
        //     data
        // now we actually put that data into our static properties
        saveJSONData(fileName, data);
    }
    private static void saveJSONData(String fileName, JSONObject[] data) {
        // ----some critical data types:
        if (fileName.equals("frames")) {
            ResourceParser.frameData = data;
        } else if (fileName.equals("systems")) {
            ResourceParser.systemData = data;
        } else if (fileName.equals("mods")) {
            ResourceParser.modificationData = data;
        } else if (fileName.equals("weapons")) {
            ResourceParser.weaponData = data;
        }
        // ----the rest of the critical data types:
        else if (fileName.equals("actions")) {
            ResourceParser.actionData = data;
        } else if (fileName.equals("core_bonuses")) {
            ResourceParser.coreBonusData = data;
        } else if (fileName.equals("tags")) {
            ResourceParser.dataTagData = data;
        } else if (fileName.equals("manufacturers")) {
            ResourceParser.manufacturerData = data;
        } else if (fileName.equals("npc_feature")) {
            ResourceParser.npcFeatureData = data;
        } else if (fileName.equals("npc_template")) {
            ResourceParser.npcTemplateData = data;
        } else if (fileName.equals("pilot_gear")) {
            ResourceParser.pilotEquipmentData = data;
        } else if (fileName.equals("reserves")) {
            ResourceParser.reserveData = data;
        } else if (fileName.equals("skills")) {
            ResourceParser.skillData = data;
        } else if (fileName.equals("statuses")) {
            ResourceParser.stateData = data;
        } else if (fileName.equals("talents")) {
            ResourceParser.talentData = data;
        }
        // ----less important
        else if (fileName.equals("environments")) {
            ResourceParser.environmentData = data;
        } else if (fileName.equals("sitreps")) {
            ResourceParser.sitrepData = data;
        }
        // ----almost unimportant
        else if (fileName.equals("backgrounds")) {
            ResourceParser.backgroundData = data;
        } else if (fileName.equals("bonds")) {
            ResourceParser.bondData = data;
        }
        // ----just for reference
        else if (fileName.equals("rules")) {
            ResourceParser.ruleData = data[0];
        } else if (fileName.equals("glossary")) {
            ResourceParser.termData = data;
        } else if (fileName.equals("tables")) {
            ResourceParser.tableData = data[0];
        }
    }
    public static void sendData() {
        Object[] data;

        data = new Object[] {
            ResourceParser.frameData,
            ResourceParser.systemData,
            ResourceParser.modificationData,
            ResourceParser.weaponData,
            ResourceParser.actionData,
            ResourceParser.coreBonusData,
            ResourceParser.dataTagData,
            ResourceParser.manufacturerData,
            ResourceParser.npcFeatureData,
            ResourceParser.npcTemplateData,
            ResourceParser.pilotEquipmentData,
            ResourceParser.reserveData,
            ResourceParser.skillData,
            ResourceParser.stateData,
            ResourceParser.talentData,
            // ----less important
            ResourceParser.environmentData,
            ResourceParser.sitrepData,
            // ----almost unimportant
            ResourceParser.backgroundData,
            ResourceParser.bondData,
            // ----just for reference
            ResourceParser.ruleData,
            ResourceParser.termData,
            ResourceParser.tableData
        };
        flushData();
        DataCaster.receiveData(data);
    }
    private static void flushData() {
        // ----some of the critical data types:
        ResourceParser.frameData = new JSONObject[0];
        ResourceParser.systemData = new JSONObject[0];
        ResourceParser.modificationData = new JSONObject[0];
        ResourceParser.weaponData = new JSONObject[0];
        // ----the rest of the critical data types:
        ResourceParser.actionData = new JSONObject[0];
        ResourceParser.coreBonusData = new JSONObject[0];
        ResourceParser.dataTagData = new JSONObject[0];
        ResourceParser.manufacturerData = new JSONObject[0];
        ResourceParser.npcFeatureData = new JSONObject[0];
        ResourceParser.npcTemplateData = new JSONObject[0];
        ResourceParser.pilotEquipmentData = new JSONObject[0];
        ResourceParser.reserveData = new JSONObject[0];
        ResourceParser.skillData = new JSONObject[0];
        ResourceParser.stateData = new JSONObject[0];
        ResourceParser.talentData = new JSONObject[0];
        // ----less important
        ResourceParser.environmentData = new JSONObject[0];
        ResourceParser.sitrepData = new JSONObject[0];
        // ----almost unimportant
        ResourceParser.backgroundData = new JSONObject[0];
        ResourceParser.bondData = new JSONObject[0];
        // ----just for reference
        ResourceParser.ruleData = null;
        ResourceParser.termData = new JSONObject[0];
        ResourceParser.tableData = null;
    }
}
