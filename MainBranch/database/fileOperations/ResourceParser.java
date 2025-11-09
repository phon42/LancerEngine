package MainBranch.database.fileOperations;

import MainBranch.database.fileOperations.json.JSONException;

public class ResourceParser {
    // prevent user from instantiating this class
    private ResourceParser() {}

    public static Object parseJSONText(String jsonData) {
        Object result;

        if (jsonData.charAt(0) == '[') {
            // it's a JSONArray (hopefully)
            // if it throws a JSONException, it's fine, just let it go off, it's
            //     a RuntimeException
            try {
                result = JSON.toJSONArray(jsonData);
                return result;
            } catch (JSONException exception) {
                throw new IllegalArgumentException("jsonData contained \"[\""
                    + " but was not a JSONArray, causing a JSONException with"
                    + " the following message: \"" + exception.getMessage()
                    + "\"");
            }
        }
        try {
            result = JSON.toJSONObject(jsonData);
            return result;
        } catch (JSONException exception) {
            throw new IllegalArgumentException("jsonData did not contain \"[\""
                + " but was not a JSONObject, causing a JSONException with the"
                + " following message: \"" + exception.getMessage()
                + "\"");
        }
    }
}
