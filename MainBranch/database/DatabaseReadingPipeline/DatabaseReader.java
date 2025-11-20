package MainBranch.database.DatabaseReadingPipeline;

import java.net.URL;
import java.nio.file.Path;
import MainBranch.database.ExternalLCP;
import MainBranch.database.DatabaseReadingPipeline.CacheSystem.Cache;

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
     * @param filePath a Path which can be any file path. Is assumed not to be
     *     null.
     */
    public static void readLocal(Path filePath, boolean addToCache,
        boolean provideOutput) {
        if (provideOutput) {
            System.out.println("Reading local file at file path: \"" + filePath
                + "\"");
        }
        DataReader.read(filePath.toString(), false, addToCache,
            provideOutput);
    }
    // TODO: add documentation
    public static void readExternal(URL url, boolean addToCache,
        boolean provideOutput) {
        if (provideOutput) {
            System.out.println("Reading external resource at URL: \"" + url
                + "\"");
        }
        DataReader.read(url.toString(), true, addToCache,
            provideOutput);
    }
    public static void readExternalLCP(ExternalLCP externalLCP,
        boolean addToCache, boolean provideOutput) {
        String lcpName;
        Path cacheDirectoryPath;
        URL[] lcpFiles;
        String[] fileURLs;

        if (provideOutput) {
            System.out.println("Reading an ExternalLCP object");
        }
        // Small optimization - see whether the LCP is already cached
        if (externalLCP.hasLCPInfoFile()) {
            lcpName = Cache.peekLCPInfo(externalLCP.getLCPInfoURL());
            if (lcpName != null) {
                if (provideOutput) {
                    System.out.println("ExternalLCP object's name is: \""
                        + lcpName + "\"");
                }
                cacheDirectoryPath = Cache.getLCP(lcpName);
                if (cacheDirectoryPath != null) {
                    // Successfully found the LCP within the cache!
                    if (provideOutput) {
                        System.out.println("ExternalLCP object successfully"
                            + " found within the cache folder at local"
                            + " directory path: \"" + cacheDirectoryPath
                            + "\"");
                    }
                    DataReader.readAllInDirectory(cacheDirectoryPath,
                        false, provideOutput);
                    DataParser.sendData();
                    return;
                }
                if (provideOutput) {
                    System.out.println("ExternalLCP object could not be"
                        + " found within the cache folder");
                }
            }
        } else {
            if (provideOutput) {
                System.out.println("ExternalLCP object does not have an"
                    + " \"info.json\" or \"lcp_manifest.json\" file and is"
                    + " therefore ineligible for being found in the cache");
            }
        }
        // LCP doesn't have a name or couldn't be found in the cache - we have
        //     to keep going
        lcpFiles = externalLCP.getFiles();
        fileURLs = new String[lcpFiles.length];
        for (int i = 0; i < lcpFiles.length; i++) {
            fileURLs[i] = lcpFiles[i].toString();
        }
        DataReader.readArray(fileURLs, true, addToCache,
            provideOutput);
        DataParser.sendData();
    }
}
