package main.database;

import main.database.classA.ClassB;
import main.database.classA.JSON;
import main.database.classA.json.JSONArray;
import main.database.classA.json.JSONObject;

public class ClassA { // possible name DatabaseReader
    // database calls ClassA.read(whatever)
    // ClassA determines whether it's a .lcp, a .zip, a .json, and so on
    // uses some black box classes JSON, ZipUnpacker, and FileReader to read the
    //     file
    // convert those JSONArrays to 
    // feed all data to ClassB and thus to ClassC
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
    private ClassA() {}

    // needs to be able to read an entire LCP, a ZIP, or just individual .json
    //     files
    public static void read(String filePath) {
        // read the data
        readData(filePath);
        // once you're done, send that data along to ClassB, which sends it
        //     along to ClassC
        sendData();
    }
    private static void readData(String filePath) {
        // do some stuff - decide whether to use readLCP, readZIP, or
        //     readJSONFile directly
        // TODO: fill out
    }
    private static void readLCP(String lcpPath) {
        // unpack the LCP and convert it to a ZIP
        // TODO: complete
        // then call readAllInZIP on it
        readAllInZIP(lcpPath);
    }
    private static void readZIP(String zipPath) {
        // unpack the zip
        // TODO: complete
        // then call readAllInZIP on it
        readAllInZIP(zipPath);
    }
    private static void readAllInZIP(String zipPath) {
        // TODO: complete
        // call readJSONFile on every file within
    }
    private static void readJSONFile(String jsonPath, String fileData) {
        String[] fileStrings;
        String fileName;
        JSONObject[] data;
        JSONArray jsonArray;

        // TODO: check to make sure these actually work as expected
        fileStrings = jsonPath.split("/");
        fileName = fileStrings[fileStrings.length - 1];
        fileStrings = fileName.split(".");
        fileName = fileStrings[0];
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
        ClassB.receiveData(data);
        flushData();
    }
    private static void flushData() {
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