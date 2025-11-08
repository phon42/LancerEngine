package MainBranch.database.databaseReader.FileOperations;

import java.nio.file.Paths;
import MainBranch.HelperMethods;
import MainBranch.database.databaseReader.DataCaster;
import MainBranch.database.databaseReader.FileOperations.fileReading.JSON;
import MainBranch.database.databaseReader.FileOperations.fileReading.json.JSONArray;
import MainBranch.database.databaseReader.FileOperations.fileReading.json.JSONObject;

public class FileParser {
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
    private FileParser() {}

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
            FileParser.frameData = data;
        } else if (fileName.equals("systems")) {
            FileParser.systemData = data;
        } else if (fileName.equals("mods")) {
            FileParser.modificationData = data;
        } else if (fileName.equals("weapons")) {
            FileParser.weaponData = data;
        }
        // ----the rest of the critical data types:
        else if (fileName.equals("actions")) {
            FileParser.actionData = data;
        } else if (fileName.equals("core_bonuses")) {
            FileParser.coreBonusData = data;
        } else if (fileName.equals("tags")) {
            FileParser.dataTagData = data;
        } else if (fileName.equals("manufacturers")) {
            FileParser.manufacturerData = data;
        } else if (fileName.equals("npc_feature")) {
            FileParser.npcFeatureData = data;
        } else if (fileName.equals("npc_template")) {
            FileParser.npcTemplateData = data;
        } else if (fileName.equals("pilot_gear")) {
            FileParser.pilotEquipmentData = data;
        } else if (fileName.equals("reserves")) {
            FileParser.reserveData = data;
        } else if (fileName.equals("skills")) {
            FileParser.skillData = data;
        } else if (fileName.equals("statuses")) {
            FileParser.stateData = data;
        } else if (fileName.equals("talents")) {
            FileParser.talentData = data;
        }
        // ----less important
        else if (fileName.equals("environments")) {
            FileParser.environmentData = data;
        } else if (fileName.equals("sitreps")) {
            FileParser.sitrepData = data;
        }
        // ----almost unimportant
        else if (fileName.equals("backgrounds")) {
            FileParser.backgroundData = data;
        } else if (fileName.equals("bonds")) {
            FileParser.bondData = data;
        }
        // ----just for reference
        else if (fileName.equals("rules")) {
            FileParser.ruleData = data[0];
        } else if (fileName.equals("glossary")) {
            FileParser.termData = data;
        } else if (fileName.equals("tables")) {
            FileParser.tableData = data[0];
        }
    }
    public static void sendData() {
        Object[] data;

        data = new Object[] {
            FileParser.frameData,
            FileParser.systemData,
            FileParser.modificationData,
            FileParser.weaponData,
            FileParser.actionData,
            FileParser.coreBonusData,
            FileParser.dataTagData,
            FileParser.manufacturerData,
            FileParser.npcFeatureData,
            FileParser.npcTemplateData,
            FileParser.pilotEquipmentData,
            FileParser.reserveData,
            FileParser.skillData,
            FileParser.stateData,
            FileParser.talentData,
            // ----less important
            FileParser.environmentData,
            FileParser.sitrepData,
            // ----almost unimportant
            FileParser.backgroundData,
            FileParser.bondData,
            // ----just for reference
            FileParser.ruleData,
            FileParser.termData,
            FileParser.tableData
        };
        flushData();
        DataCaster.receiveData(data);
    }
    private static void flushData() {
        // ----some of the critical data types:
        FileParser.frameData = new JSONObject[0];
        FileParser.systemData = new JSONObject[0];
        FileParser.modificationData = new JSONObject[0];
        FileParser.weaponData = new JSONObject[0];
        // ----the rest of the critical data types:
        FileParser.actionData = new JSONObject[0];
        FileParser.coreBonusData = new JSONObject[0];
        FileParser.dataTagData = new JSONObject[0];
        FileParser.manufacturerData = new JSONObject[0];
        FileParser.npcFeatureData = new JSONObject[0];
        FileParser.npcTemplateData = new JSONObject[0];
        FileParser.pilotEquipmentData = new JSONObject[0];
        FileParser.reserveData = new JSONObject[0];
        FileParser.skillData = new JSONObject[0];
        FileParser.stateData = new JSONObject[0];
        FileParser.talentData = new JSONObject[0];
        // ----less important
        FileParser.environmentData = new JSONObject[0];
        FileParser.sitrepData = new JSONObject[0];
        // ----almost unimportant
        FileParser.backgroundData = new JSONObject[0];
        FileParser.bondData = new JSONObject[0];
        // ----just for reference
        FileParser.ruleData = null;
        FileParser.termData = new JSONObject[0];
        FileParser.tableData = null;
    }
}
