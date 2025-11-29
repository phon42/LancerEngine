package MainBranch.database.DatabaseReadingPipeline.CacheSystem;

import java.nio.file.Path;
import MainBranch.HelperMethods;
import MainBranch.UserPreferences;
import MainBranch.database.FileOperations;
import MainBranch.database.fileOperations.JSON;
import MainBranch.database.fileOperations.json.JSONData;
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
    public static Path getLCP(String lcpName) {
        Path cachePath;
        Path[] directoryContents;
        Path lcpInfoPath;

        HelperMethods.checkString("lcpName", lcpName);
        // The starting point - the cache directory
        cachePath = UserPreferences.getCacheDir();
        cachePath = cachePath.normalize().toAbsolutePath();
        // Now list all files under that directory
        directoryContents = FileOperations.listDirectoryContentsAsArray(
            cachePath, true);
        for (Path currElement : directoryContents) {
            if (! currElement.toFile().isDirectory()) {
                continue;
            }
            lcpInfoPath = findAndCheck(lcpName,
                "info.json", currElement);
            if (lcpInfoPath != null) {
                return lcpInfoPath;
            }
            lcpInfoPath = findAndCheck(lcpName,
                "lcp_manifest.json", currElement);
            if (lcpInfoPath != null) {
                return lcpInfoPath;
            }
        }

        return null;
    }
    private static Path findAndCheck(String lcpName,
        String fileNameToSearchFor, Path searchDirectory) {
        Path lcpInfoPath;
        JSONData[] lcpInfoJSON;
        JSONObject lcpInfoObject;
        String name;

        lcpInfoPath = findFileWithName(fileNameToSearchFor, searchDirectory);
        if (lcpInfoPath != null) {
            lcpInfoJSON = FileOperations.readAndParseResource(
                lcpInfoPath.toString(), false, false);
            lcpInfoObject = JSON.toJSONObject(lcpInfoJSON);
            try {
                name = lcpInfoObject.getString("name");
                if (lcpName.equals(name)) {
                    // we've found it!
                    return searchDirectory;
                }
            } catch (JSONException exception) {
                // found a file with the right name but it didn't have a
                //     "name" property
                // throw an exception if you want but I'm not gonna
            }
        }

        return null;
    }
    private static Path findFileWithName(String fileString,
        Path directoryToSearch) {
        Path[] files;

        files = FileOperations.getAllFilenamesInDirectory(directoryToSearch);
        for (Path file : files) {
            if (fileString.equals(file.getFileName().toString())) {
                return file;
            }
        }

        return null;
    }
    /**
     * Peeks at a given external LCP's info.json or lcp_manifest.json file,
     *     retrieves the file's "name" property, and returns it.
     */
    public static String peekLCPInfo(String url) {
        JSONData[] fileData;
        JSONObject file;

        fileData = FileOperations.readAndParseResource(url, true,
            false);
        file = JSON.toJSONObject(fileData);
        try {
            return file.getString("name");
        } catch (JSONException exception) {
            throw new IllegalStateException("Resource at URL: \"" + url + "\""
                + " did not contain a JSON file with the required \"name\""
                + " property");
        }
    }
}
