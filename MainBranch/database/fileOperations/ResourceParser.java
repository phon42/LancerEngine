package MainBranch.database.fileOperations;

import MainBranch.database.fileOperations.json.JSONData;
import MainBranch.database.fileOperations.json.JSONException;

public class ResourceParser {
    // prevent user from instantiating this class
    private ResourceParser() {}

    public static JSONData parseJSONText(String jsonData) {
        JSONData result;

        if (jsonData.charAt(0) == '[') {
            // it's a JSONArray (hopefully)
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
