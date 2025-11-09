package MainBranch.database.DatabaseReadingPipeline;

import MainBranch.database.meme.ResourceReader;

public class DatabaseReader {
    // 1. DatabaseReader's DatabaseReader.read() method is called by Database,
    //        something like DatabaseReader.read(<a file path>)
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

    /**
     * Reads the provided file and saves all its contents before sending the
     *     saved data onwards to DataCaster. Can read .lcp, .zip, or individual
     *     .json files.
     * @param filePath a String which must contain a valid file path.
     */
    public static void readLocal(String filePath) {
        ResourceReader.read(filePath, false);
    }
    public static void readExternal(String url) {
        ResourceReader.read(url, true);
    }
}