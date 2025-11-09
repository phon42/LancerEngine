package MainBranch.database.fileOperations;

import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONException;
import MainBranch.database.fileOperations.json.JSONObject;

public class JSON {
    // black box that reads JSON, converts to JSONObject, JSONArray, some mix
    //     thereof, etc
    // just needs to be able to read a String of JSON data and convert to
    //     JSONArray or JSONObject
    public static JSONArray toJSONArray(String jsonData) throws JSONException {
        return new JSONArray(jsonData);
    }
    public static JSONObject toJSONObject(String jsonData) throws JSONException
    {
        return new JSONObject(jsonData);
    }
}
