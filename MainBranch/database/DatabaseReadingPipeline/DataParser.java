package MainBranch.database.DatabaseReadingPipeline;

import java.nio.file.Paths;
import MainBranch.database.FileOperations;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;
import MainBranch.HelperMethods;

public class DataParser {
    // All the data being held at the moment, as JSONObject[]s:
    // ----absolutely critical data:
    private static JSONObject[] infoData;
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
    private DataParser() {}

    /**
     * Parse the provided .json data, then save its contents.
     * @param jsonPath a String which contains the file path from which the JSON
     *     data was extracted. Is assumed to be accurate. Cannot be null.
     * @param fileData a String which contains the contents of a .json file. Is
     *     assumed to be the contents of a .json file. Cannot be null.
     */
    public static void parseJSON(String jsonPath, String fileData) {
        Object data;
        String fileName;
        JSONObject[] convertedData;
        JSONArray jsonArray;

        // Check whether jsonPath or fileData is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        HelperMethods.checkObject("fileData", fileData);
        data = FileOperations.parseJSONText(fileData);
        // data can't be null because parseJSONText() throws a bunch of
        //     Exceptions to prevent that from happening
        // Extract the file name
        fileName = Paths.get(jsonPath).toFile().getName();
        // TODO: check to make sure this actually works as expected
        if (fileName.indexOf(".") != -1) {
            fileName = fileName.split("\\.")[0];
        }
        // Convert the file data to an object and then to a JSONObject[]
        if (fileName.equals("info")
            || fileName.equals("lcp_manifest")
            || fileName.equals("rules")
            || fileName.equals("tables")) {
            // going to be receiving a JSONObject
            convertedData = new JSONObject[] {(JSONObject) data};
        } else {
            // going to be receiving a JSONArray
            jsonArray = (JSONArray) data;
            convertedData = new JSONObject[jsonArray.length()];
            for (int i = 0; i < convertedData.length; i++) {
                convertedData[i] = jsonArray.getJSONObject(i);
            }
        }
        // either way, at this point we have data set to a JSONObject[] of our
        //     data
        // now we actually put that data into our static properties
        saveJSONData(fileName, convertedData);
    }
    private static void saveJSONData(String fileName, JSONObject[] data) {
        // TODO: update to APPEND to the relevant data file if there's already
        //     data there (i.e. two "frames.json" files)
        // or consider throwing an Exception
        // ----absolutely critical data:
        if (fileName.equals("info")
            || fileName.equals("lcp_manifest")) {
            DataParser.infoData = data;
        }
        // ----some critical data types:
        else if (fileName.equals("frames")) {
            DataParser.frameData = data;
        } else if (fileName.equals("systems")) {
            DataParser.systemData = data;
        } else if (fileName.equals("mods")) {
            DataParser.modificationData = data;
        } else if (fileName.equals("weapons")) {
            DataParser.weaponData = data;
        }
        // ----the rest of the critical data types:
        else if (fileName.equals("actions")) {
            DataParser.actionData = data;
        } else if (fileName.equals("core_bonuses")) {
            DataParser.coreBonusData = data;
        } else if (fileName.equals("tags")) {
            DataParser.dataTagData = data;
        } else if (fileName.equals("manufacturers")) {
            DataParser.manufacturerData = data;
        } else if (fileName.equals("npc_feature")) {
            DataParser.npcFeatureData = data;
        } else if (fileName.equals("npc_template")) {
            DataParser.npcTemplateData = data;
        } else if (fileName.equals("pilot_gear")) {
            DataParser.pilotEquipmentData = data;
        } else if (fileName.equals("reserves")) {
            DataParser.reserveData = data;
        } else if (fileName.equals("skills")) {
            DataParser.skillData = data;
        } else if (fileName.equals("statuses")) {
            DataParser.stateData = data;
        } else if (fileName.equals("talents")) {
            DataParser.talentData = data;
        }
        // ----less important
        else if (fileName.equals("environments")) {
            DataParser.environmentData = data;
        } else if (fileName.equals("sitreps")) {
            DataParser.sitrepData = data;
        }
        // ----almost unimportant
        else if (fileName.equals("backgrounds")) {
            DataParser.backgroundData = data;
        } else if (fileName.equals("bonds")) {
            DataParser.bondData = data;
        }
        // ----just for reference
        else if (fileName.equals("rules")) {
            DataParser.ruleData = data[0];
        } else if (fileName.equals("glossary")) {
            DataParser.termData = data;
        } else if (fileName.equals("tables")) {
            DataParser.tableData = data[0];
        }
    }
    public static void sendData() {
        Object[] data;

        // TODO: consider throwing an exception here if no info.json or
        //     lcp_manifest.json file is found
        data = new Object[] {
            // ----absolutely critical data:
            DataParser.infoData,
            // ----some critical data types:
            DataParser.frameData,
            DataParser.systemData,
            DataParser.modificationData,
            DataParser.weaponData,
            // ----the rest of the critical data types:
            DataParser.actionData,
            DataParser.coreBonusData,
            DataParser.dataTagData,
            DataParser.manufacturerData,
            DataParser.npcFeatureData,
            DataParser.npcTemplateData,
            DataParser.pilotEquipmentData,
            DataParser.reserveData,
            DataParser.skillData,
            DataParser.stateData,
            DataParser.talentData,
            // ----less important
            DataParser.environmentData,
            DataParser.sitrepData,
            // ----almost unimportant
            DataParser.backgroundData,
            DataParser.bondData,
            // ----just for reference
            DataParser.ruleData,
            DataParser.termData,
            DataParser.tableData
        };
        flushData();
        DataCaster.receiveData(data);
    }
    private static void flushData() {
        // ----absolutely critical data:
        DataParser.infoData = new JSONObject[0];
        // ----some of the critical data types:
        DataParser.frameData = new JSONObject[0];
        DataParser.systemData = new JSONObject[0];
        DataParser.modificationData = new JSONObject[0];
        DataParser.weaponData = new JSONObject[0];
        // ----the rest of the critical data types:
        DataParser.actionData = new JSONObject[0];
        DataParser.coreBonusData = new JSONObject[0];
        DataParser.dataTagData = new JSONObject[0];
        DataParser.manufacturerData = new JSONObject[0];
        DataParser.npcFeatureData = new JSONObject[0];
        DataParser.npcTemplateData = new JSONObject[0];
        DataParser.pilotEquipmentData = new JSONObject[0];
        DataParser.reserveData = new JSONObject[0];
        DataParser.skillData = new JSONObject[0];
        DataParser.stateData = new JSONObject[0];
        DataParser.talentData = new JSONObject[0];
        // ----less important
        DataParser.environmentData = new JSONObject[0];
        DataParser.sitrepData = new JSONObject[0];
        // ----almost unimportant
        DataParser.backgroundData = new JSONObject[0];
        DataParser.bondData = new JSONObject[0];
        // ----just for reference
        DataParser.ruleData = null;
        DataParser.termData = new JSONObject[0];
        DataParser.tableData = null;
    }
}