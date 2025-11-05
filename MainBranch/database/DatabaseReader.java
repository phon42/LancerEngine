package MainBranch.database;

import java.nio.file.Paths;

import MainBranch.HelperMethods;
import MainBranch.database.databaseReader.DataCaster;
import MainBranch.database.databaseReader.abba.FileProcessor;
import MainBranch.database.databaseReader.abba.JSON;
import MainBranch.database.databaseReader.abba.json.JSONArray;
import MainBranch.database.databaseReader.abba.json.JSONObject;

public class DatabaseReader {
    // 1. DatabaseReader's DatabaseReader.read() method is called by Database,
    //        something like DatabaseReader.read(<a file path>)
    // 2. Determine whether the file is a .lcp, .zip, .json, and so on
    // 3. Use some black box classes JSON, ZipUnpacker, and FileReader to read
    //        the file, which will then eventually pare their contents down to
    //        JSON files and call JSON, thus returning either a JSONObject or a
    //        JSONArray
    // 4. Convert that result as follows:
    //    a. If it's a JSONObject, convert it to a JSONObject[1]
    //    a. If it's a JSONArray, convert it to a JSONObject[]
    // 5. Collect all those JSONObject[]s into one giant Object[]
    // 6. Feed that Object[] to DataCaster and thus eventually to DataCompiler
    // 7. Flush all the stored data from DatabaseReader

    // All the data being held at the moment, as JSONObject[]s:
    // ----very critical data:
    private static JSONObject[] frameData = new JSONObject[0];
    private static JSONObject[] systemData = new JSONObject[0];
    private static JSONObject[] modificationData = new JSONObject[0];
    private static JSONObject[] weaponData = new JSONObject[0];
    // ----critical data:
    private static JSONObject[] actionData = new JSONObject[0];
    private static JSONObject[] coreBonusData = new JSONObject[0];
    private static JSONObject[] dataTagData = new JSONObject[0];
    private static JSONObject[] manufacturerData = new JSONObject[0];
    private static JSONObject[] npcFeatureData = new JSONObject[0];
    private static JSONObject[] npcTemplateData = new JSONObject[0];
    private static JSONObject[] pilotEquipmentData = new JSONObject[0];
    private static JSONObject[] reserveData = new JSONObject[0];
    private static JSONObject[] skillData = new JSONObject[0];
    private static JSONObject[] stateData = new JSONObject[0];
    private static JSONObject[] talentData = new JSONObject[0];
    // ----less important data:
    private static JSONObject[] environmentData = new JSONObject[0];
    private static JSONObject[] sitrepData = new JSONObject[0];
    // ----pretty unimportant data:
    private static JSONObject[] backgroundData = new JSONObject[0];
    private static JSONObject[] bondData = new JSONObject[0];
    // ----just for reference:
    private static JSONObject ruleData = null;
    private static JSONObject[] termData = new JSONObject[0];
    private static JSONObject tableData = null;

    // Prevent user from instantiating this class
    private DatabaseReader() {}

    /**
     * Reads the provided file and saves all its contents before sending the
     *     saved data onwards to DataCaster. Can read .lcp, .zip, or individual
     *     .json files.
     * @param filePath a String which must contain a valid file path.
     */
    public static void read(String filePath) {
        // read the data
        readData(filePath);
        // once you're done, send that data along to DataCaster, which sends it
        //     along to DataCompiler
        sendData();
    }
    /**
     * Reads the provided file and saves its contents. Can read .lcp, .zip, or
     *     individual .json files. Calls DatabaseReader.readLCP(),
     *     DatabaseReader.readZIP(), or DatabaseReader.readJSON() based on what
     *     type the file is.
     * @param filePath a String which must contain a valid file path. Cannot be
     *     null.
     */
    private static void readData(String filePath) {
        String fileExtension;
        String[] fileStrings;

        // extract the file extension
        // TODO: check to make sure this actually works as expected, i.e.
        //     .tar.gz
        fileExtension = Paths.get(filePath).toFile().getName();
        if (fileExtension.indexOf(".") != -1) {
            fileStrings = fileExtension.split("\\.");
            // this is the step that needs to be changed to deal with .tar.gz
            fileExtension = fileStrings[fileStrings.length - 1];
        }
        // decide whether to use readLCP, readZIP, or readJSON directly
        if (fileExtension.equals("lcp")) {
            readLCP(filePath);
        } else if (fileExtension.equals("zip")) {
            readZIP(filePath);
        } else if (fileExtension.equals("json")) {
            readJSON(filePath);
        } else {
            if (fileExtension.equals("")) {
                throw new IllegalArgumentException("Unable to find a file"
                    + " extension in the file path: \"" + filePath + "\"");
            } else {
                throw new IllegalArgumentException("Cannot read a file with the"
                    + " following file extension: \"." + fileExtension + "\"");
            }
        }
    }
    /**
     * Reads the provided .lcp file by converting it to a .zip, then calling
     *     DatabaseReader.readZIP().
     * @param lcpPath a String which must contain a valid file path. Is assumed
     *     to be a .lcp file. Cannot be null.
     */
    private static void readLCP(String lcpPath) {
        // unpack the LCP and convert it to a ZIP
        // TODO: complete
        // then call readAllInZIP on it
        readAllInZIP(lcpPath);
    }
    /**
     * Unzips the provided .zip file before calling
     *     DatabaseReader.readAllInZIP() on its contents.
     * @param zipPath a String which must contain a valid file path. Is assumed
     *     to be a .zip file. Cannot be null.
     */
    private static void readZIP(String zipPath) {
        // unpack the zip
        // TODO: complete
        // then call readAllInZIP on it
        readAllInZIP(zipPath);
    }
    /**
     * Reads a .zip file's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    private static void readAllInZIP(String directoryPath) {
        // TODO: complete
        // call readJSON on every file within
    }
    /**
     * Reads the provided .json file, then calls DatabaseReader.parseJSONFile()
     *     to parse its data.
     * @param jsonPath a String which must contain a valid file path. Must be a
     *     .json file. Cannot be null.
     */
    private static void readJSON(String jsonPath) {
        String data = null;

        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        data = FileProcessor.readFile(jsonPath);
        // Send the data on to DataCaster.parseJSONFile()
        parseJSONFile(jsonPath, data);
    }
    /**
     * Parse the provided .json data, then save its contents.
     * @param jsonPath a String which contains the file path from which the JSON
     *     data was extracted. Is assumed to be accurate. Cannot be null.
     * @param fileData a String which contains the contents of a .json file. Is
     *     assumed to be the contents of a .json file. Cannot be null.
     */
    private static void parseJSONFile(String jsonPath, String fileData) {
        String fileName;
        JSONObject[] data;
        JSONArray jsonArray;

        // Check whether jsonPath or fileData is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        HelperMethods.checkObject("fileData", fileData);
        // Extract the file name
        // TODO: check to make sure this actually works as expected
        fileName = Paths.get(jsonPath).toFile().getName();
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
            DatabaseReader.frameData = data;
        } else if (fileName.equals("systems")) {
            DatabaseReader.systemData = data;
        } else if (fileName.equals("mods")) {
            DatabaseReader.modificationData = data;
        } else if (fileName.equals("weapons")) {
            DatabaseReader.weaponData = data;
        }
        // ----the rest of the critical data types:
        else if (fileName.equals("actions")) {
            DatabaseReader.actionData = data;
        } else if (fileName.equals("core_bonuses")) {
            DatabaseReader.coreBonusData = data;
        } else if (fileName.equals("tags")) {
            DatabaseReader.dataTagData = data;
        } else if (fileName.equals("manufacturers")) {
            DatabaseReader.manufacturerData = data;
        } else if (fileName.equals("npc_feature")) {
            DatabaseReader.npcFeatureData = data;
        } else if (fileName.equals("npc_template")) {
            DatabaseReader.npcTemplateData = data;
        } else if (fileName.equals("pilot_gear")) {
            DatabaseReader.pilotEquipmentData = data;
        } else if (fileName.equals("reserves")) {
            DatabaseReader.reserveData = data;
        } else if (fileName.equals("skills")) {
            DatabaseReader.skillData = data;
        } else if (fileName.equals("statuses")) {
            DatabaseReader.stateData = data;
        } else if (fileName.equals("talents")) {
            DatabaseReader.talentData = data;
        }
        // ----less important
        else if (fileName.equals("environments")) {
            DatabaseReader.environmentData = data;
        } else if (fileName.equals("sitreps")) {
            DatabaseReader.sitrepData = data;
        }
        // ----almost unimportant
        else if (fileName.equals("backgrounds")) {
            DatabaseReader.backgroundData = data;
        } else if (fileName.equals("bonds")) {
            DatabaseReader.bondData = data;
        }
        // ----just for reference
        else if (fileName.equals("rules")) {
            DatabaseReader.ruleData = data[0];
        } else if (fileName.equals("glossary")) {
            DatabaseReader.termData = data;
        } else if (fileName.equals("tables")) {
            DatabaseReader.tableData = data[0];
        }
    }
    private static void sendData() {
        Object[] data;

        data = new Object[] {
            DatabaseReader.frameData,
            DatabaseReader.systemData,
            DatabaseReader.modificationData,
            DatabaseReader.weaponData,
            DatabaseReader.actionData,
            DatabaseReader.coreBonusData,
            DatabaseReader.dataTagData,
            DatabaseReader.manufacturerData,
            DatabaseReader.npcFeatureData,
            DatabaseReader.npcTemplateData,
            DatabaseReader.pilotEquipmentData,
            DatabaseReader.reserveData,
            DatabaseReader.skillData,
            DatabaseReader.stateData,
            DatabaseReader.talentData,
            // ----less important
            DatabaseReader.environmentData,
            DatabaseReader.sitrepData,
            // ----almost unimportant
            DatabaseReader.backgroundData,
            DatabaseReader.bondData,
            // ----just for reference
            DatabaseReader.ruleData,
            DatabaseReader.termData,
            DatabaseReader.tableData
        };
        flushData();
        DataCaster.receiveData(data);
    }
    private static void flushData() {
        // ----some of the critical data types:
        DatabaseReader.frameData = new JSONObject[0];
        DatabaseReader.systemData = new JSONObject[0];
        DatabaseReader.modificationData = new JSONObject[0];
        DatabaseReader.weaponData = new JSONObject[0];
        // ----the rest of the critical data types:
        DatabaseReader.actionData = new JSONObject[0];
        DatabaseReader.coreBonusData = new JSONObject[0];
        DatabaseReader.dataTagData = new JSONObject[0];
        DatabaseReader.manufacturerData = new JSONObject[0];
        DatabaseReader.npcFeatureData = new JSONObject[0];
        DatabaseReader.npcTemplateData = new JSONObject[0];
        DatabaseReader.pilotEquipmentData = new JSONObject[0];
        DatabaseReader.reserveData = new JSONObject[0];
        DatabaseReader.skillData = new JSONObject[0];
        DatabaseReader.stateData = new JSONObject[0];
        DatabaseReader.talentData = new JSONObject[0];
        // ----less important
        DatabaseReader.environmentData = new JSONObject[0];
        DatabaseReader.sitrepData = new JSONObject[0];
        // ----almost unimportant
        DatabaseReader.backgroundData = new JSONObject[0];
        DatabaseReader.bondData = new JSONObject[0];
        // ----just for reference
        DatabaseReader.ruleData = null;
        DatabaseReader.termData = new JSONObject[0];
        DatabaseReader.tableData = null;
    }
}