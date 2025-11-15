package MainBranch.database.DatabaseReadingPipeline.CacheSystem;

import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import MainBranch.database.fileOperations.json.JSONException;
import MainBranch.database.fileOperations.json.JSONObject;

public class Cache {
    // Prevent user from instantiating
    private Cache() {}

    /**
     * Attempts to find an LCP within the local cache directory with the
     *     provided name. If found, returns the LCP's local directory path in
     *     the cache directory. Otherwise, returns null.
     */
    public static String getLCP(String lcpName) {
        // TODO: fill out
        HelperMethods.checkString("lcpName", lcpName);

        return null;
    }
    /**
     * Peeks at a given external LCP's info.json or lcp_manifest.json file,
     *     retrieves the file's "name" property, and returns it.
     */
    public static String peekLCPInfo(String url) {
        String[] fileData;
        Object fileObject;
        JSONObject file;

        fileData = FileOperations.readResource(url, true,
            false);
        if (fileData.length > 1) {
            throw new IllegalStateException("URL: \"" + url + "\" contained"
                + " more than 1 file");
        }
        fileObject = FileOperations.parseJSONText(fileData[0]);
        if (! (fileObject instanceof JSONObject)) {
            throw new IllegalStateException("URL: \"" + url + "\" did not yield"
                + " a JSONObject");
        }
        file = (JSONObject) fileObject;
        try {
            return file.getString("name");
        } catch (JSONException exception) {
            throw new IllegalStateException("Resource at URL: \"" + url + "\""
                + " did not contain a JSON file with the required \"name\""
                + " property");
        }
    }
}
