package MainBranch.database.DatabaseReadingPipeline;

import java.net.URL;
import MainBranch.database.ExternalLCP;

public class DatabaseReader {
    // TODO: update documentation
    // 1. DatabaseReader's read() method is called by Database, something like
    //        read(<a file path>)
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
    // 7. Flush all the stored data from DatabaseReader

    // Prevent user from instantiating this class
    private DatabaseReader() {}

    // Step 0: START - input a path to the resource as a String
    // readLocal and readExternal are essentially the same method, but we're
    //     separating them to make it harder for people to pick the wrong one by
    //     accident
    /**
     * Reads a local file from the provided local file path and saves all its
     *     contents before sending the saved data onwards to DataCaster. Can
     *     read .lcp, .zip, or individual .json files.
     * @param filePath a String which must contain a valid file path.
     */
    public static void readLocal(String filePath) {
        DataReader.read(filePath, false);
    }
    // TODO: add documentation
    public static void readExternal(String url) {
        DataReader.read(url, true);
    }
    public static void readExternalLCP(ExternalLCP externalLCP) {
        // TODO: update this to count all the JSON files together, not each as
        //     its own little LCP
        URL[] lcpFiles;

        lcpFiles = externalLCP.getFiles();
        for (URL file : lcpFiles) {
            readExternal(file.toString());
        }
    }
}