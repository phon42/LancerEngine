package MainBranch.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.JSON;
import MainBranch.database.fileOperations.ResourceReader;
import MainBranch.database.fileOperations.ZIPUnzipper;
import MainBranch.database.fileOperations.json.JSONException;

public class FileOperations {
    // prevent user from instantiating this class
    private FileOperations() {}

    public static String[] readResource(String resourceLocator,
        boolean external) {
        return ResourceReader.read(resourceLocator, external);
    }
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
    public static void deleteDirectory(String directoryPath) {
        DirectoryDeleter.deleteDirectory(directoryPath);
    }
    private static String unzip(String zipResourceLocator, boolean external,
        String targetDirectoryPath) {
        return ZIPUnzipper.unzip(zipResourceLocator, external,
            targetDirectoryPath);
    }
    public static String unzip(String zipResourceLocator, boolean external) {
        return unzip(zipResourceLocator, external,
            "MainBranch/ProgramData/Target/");
    }
}
