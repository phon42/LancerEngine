package main.database;

import main.database.classA.DataCaster;
import main.database.classA.JSON;
import main.database.classA.json.JSONArray;
import main.database.classA.json.JSONObject;

public class ClassA { // possible name DatabaseReader
    // 1. ClassA's ClassA.read() method is called by Database something like
    //        ClassA.read(<a file path>)
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
    // 7. Flush all the stored data from ClassA

    // All the data being held at the moment, as JSONObject[]s:
    // ----very critical data:
    private static JSONObject[] frameData = new JSONObject[0];
    private static JSONObject[] systemData = new JSONObject[0];
    private static JSONObject[] modificationData = new JSONObject[0];
    private static JSONObject[] weaponData = new JSONObject[0];
    // ----critical data:
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
    private ClassA() {}

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
     *     individual .json files. Calls ClassA.readLCP(), ClassA.readZIP(), or
     *     ClassA.readJSON() based on what type the file is.
     * @param filePath a String which must contain a valid file path. Cannot be
     *     null.
     */
    private static void readData(String filePath) {
        String[] fileStrings;
        String fileExtension;

        // extract the file extension
        // TODO: check to make sure these actually work as expected
        fileStrings = filePath.split("/");
        fileExtension = fileStrings[fileStrings.length - 1];
        fileStrings = fileExtension.split(".");
        fileExtension = fileStrings[1];
        // decide whether to use readLCP, readZIP, or readJSON directly
        if (fileExtension.equals("lcp")) {
            readLCP(filePath);
        } else if (fileExtension.equals("zip")) {
            readZIP(filePath);
        } else if (fileExtension.equals("json")) {
            readJSON(filePath);
        } else {
            throw new IllegalArgumentException("Cannot read a file with the"
                + " following file extension: \"." + fileExtension + "\"");
        }
    }
    /**
     * Reads the provided .lcp file by converting it to a .zip, then calling
     *     ClassA.readZIP().
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
     * Unzips the provided .zip file before calling ClassA.readAllInZIP() on its
     *     contents.
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
     * Reads a .zip file's contents by calling ClassA.readJSON() on every file
     *     within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    private static void readAllInZIP(String directoryPath) {
        // TODO: complete
        // call readJSON on every file within
    }
    /**
     * Reads the provided .json file, then calls ClassA.parseJSONFile() to parse
     *     its data.
     * @param jsonPath a String which must contain a valid file path. Must be a
     *     .json file. Cannot be null.
     */
    private static void readJSON(String jsonPath) {
        String data;

        // Check whether jsonPath is null
        if (jsonPath == null) {
            throw new IllegalArgumentException("jsonPath is null");
        }
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        // TODO: complete
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
        String[] fileStrings;
        String fileName;
        JSONObject[] data;
        JSONArray jsonArray;

        // Check whether jsonPath or fileData is null
        if (jsonPath == null) {
            throw new IllegalArgumentException("jsonPath is null");
        }
        if (fileData == null) {
            throw new IllegalArgumentException("fileData is null");
        }
        // Extract the file name
        // TODO: check to make sure these actually work as expected
        fileStrings = jsonPath.split("/");
        fileName = fileStrings[fileStrings.length - 1];
        fileStrings = fileName.split(".");
        fileName = fileStrings[0];
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
            ClassA.frameData = data;
        } else if (fileName.equals("systems")) {
            ClassA.systemData = data;
        } else if (fileName.equals("mods")) {
            ClassA.modificationData = data;
        } else if (fileName.equals("weapons")) {
            ClassA.weaponData = data;
        }
        // ----the rest of the critical data types:
        else if (fileName.equals("actions")) {
            ClassA.actionData = data;
        } else if (fileName.equals("tags")) {
            ClassA.dataTagData = data;
        } else if (fileName.equals("manufacturers")) {
            ClassA.manufacturerData = data;
        // TODO: fill out
        } else if (fileName.equals("frames")) {
            ClassA.npcFeatureData = data;
        // TODO: fill out
        } else if (fileName.equals("frames")) {
            ClassA.npcTemplateData = data;
        } else if (fileName.equals("pilot_gear")) {
            ClassA.pilotEquipmentData = data;
        } else if (fileName.equals("reserves")) {
            ClassA.reserveData = data;
        } else if (fileName.equals("skills")) {
            ClassA.skillData = data;
        } else if (fileName.equals("statuses")) {
            ClassA.stateData = data;
        } else if (fileName.equals("talents")) {
            ClassA.talentData = data;
        }
        // ----less important
        else if (fileName.equals("environments")) {
            ClassA.environmentData = data;
        } else if (fileName.equals("sitreps")) {
            ClassA.sitrepData = data;
        }
        // ----almost unimportant
        else if (fileName.equals("backgrounds")) {
            ClassA.backgroundData = data;
        } else if (fileName.equals("bonds")) {
            ClassA.bondData = data;
        }
        // ----just for reference
        else if (fileName.equals("rules")) {
            ClassA.ruleData = data[0];
        } else if (fileName.equals("glossary")) {
            ClassA.termData = data;
        } else if (fileName.equals("tables")) {
            ClassA.tableData = data[0];
        }
    }
    private static void sendData() {
        Object[] data;

        data = new Object[] {
            ClassA.frameData,
            ClassA.systemData,
            ClassA.modificationData,
            ClassA.weaponData,
            ClassA.actionData,
            ClassA.dataTagData,
            ClassA.manufacturerData,
            ClassA.npcFeatureData,
            ClassA.npcTemplateData,
            ClassA.pilotEquipmentData,
            ClassA.reserveData,
            ClassA.skillData,
            ClassA.stateData,
            ClassA.talentData,
            // ----less important
            ClassA.environmentData,
            ClassA.sitrepData,
            // ----almost unimportant
            ClassA.backgroundData,
            ClassA.bondData,
            // ----just for reference
            ClassA.ruleData,
            ClassA.termData,
            ClassA.tableData
        };
        DataCaster.receiveData(data);
        flushData();
    }
    private static void flushData() {
        // ----some of the critical data types:
        ClassA.frameData = new JSONObject[0];
        ClassA.systemData = new JSONObject[0];
        ClassA.modificationData = new JSONObject[0];
        ClassA.weaponData = new JSONObject[0];
        // ----the rest of the critical data types:
        ClassA.actionData = new JSONObject[0];
        ClassA.dataTagData = new JSONObject[0];
        ClassA.manufacturerData = new JSONObject[0];
        ClassA.npcFeatureData = new JSONObject[0];
        ClassA.npcTemplateData = new JSONObject[0];
        ClassA.pilotEquipmentData = new JSONObject[0];
        ClassA.reserveData = new JSONObject[0];
        ClassA.skillData = new JSONObject[0];
        ClassA.stateData = new JSONObject[0];
        ClassA.talentData = new JSONObject[0];
        // ----less important
        ClassA.environmentData = new JSONObject[0];
        ClassA.sitrepData = new JSONObject[0];
        // ----almost unimportant
        ClassA.backgroundData = new JSONObject[0];
        ClassA.bondData = new JSONObject[0];
        // ----just for reference
        ClassA.ruleData = null;
        ClassA.termData = new JSONObject[0];
        ClassA.tableData = null;
    }
}