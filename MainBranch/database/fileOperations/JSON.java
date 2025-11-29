package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONData;
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
    public static JSONArray toJSONArray(JSONData[] input)
        throws IllegalStateException {
        JSONArray[] result;

        HelperMethods.checkObjectArray("input", input);
        result = toJSONArrayArray(input);
        if (result.length != 1) {
            throw new IllegalStateException("result array's length: "
                + result.length + " != 1");
        }

        return result[0];
    }
    public static JSONArray[] toJSONArrayArray(JSONData[] input)
        throws IllegalStateException {
        JSONArray[] result;

        HelperMethods.checkObjectArray("input", input);
        result = new JSONArray[input.length];
        for (int i = 0; i < input.length; i++) {
            if (! (input[i] instanceof JSONArray)) {
                throw new IllegalStateException("input array contains an"
                    + " element that is not a JSONArray");
            }
            result[i] = (JSONArray) input[i];
        }

        return result;
    }
    public static JSONObject toJSONObject(JSONData[] input)
        throws IllegalStateException {
        JSONObject[] result;

        HelperMethods.checkObjectArray("input", input);
        result = toJSONObjectArray(input);
        if (result.length != 1) {
            throw new IllegalStateException("result array's length: "
                + result.length + " != 1");
        }

        return result[0];
    }
    public static JSONObject[] toJSONObjectArray(JSONData[] input)
        throws IllegalStateException {
        JSONObject[] result;

        HelperMethods.checkObjectArray("input", input);
        result = new JSONObject[input.length];
        for (int i = 0; i < input.length; i++) {
            if (! (input[i] instanceof JSONObject)) {
                throw new IllegalStateException("input array contains an"
                    + " element that is not a JSONObject");
            }
            result[i] = (JSONObject) input[i];
        }

        return result;
    }
}
