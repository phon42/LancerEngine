package MainBranch.database.databaseReader.FileOperations.fileReading;

import MainBranch.database.databaseReader.FileOperations.fileReading.json.JSONArray;
import MainBranch.database.databaseReader.FileOperations.fileReading.json.JSONObject;

public class JSON {
    // black box that reads JSON, converts to JSONObject, JSONArray, some mix
    //     thereof, etc
    // just needs to be able to read a String of JSON data and convert to
    //     JSONArray or JSONObject
    public static JSONArray toJSONArray(String jsonData) {
        return new JSONArray(jsonData);
    }
    public static JSONObject toJSONObject(String jsonData) {
        return new JSONObject(jsonData);
    }
}
