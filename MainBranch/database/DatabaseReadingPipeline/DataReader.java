package MainBranch.database.DatabaseReadingPipeline;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import MainBranch.HelperMethods;
import MainBranch.UserPreferences;
import MainBranch.database.FileOperations;
import MainBranch.database.DatabaseReadingPipeline.DatabaseResourceInfo.DatabaseResourceInfo;
import MainBranch.database.fileOperations.FileNameOperator;
import MainBranch.database.fileOperations.json.JSONException;
import MainBranch.database.fileOperations.json.JSONObject;

public class DataReader {
    // prevent user from instantiating this class
    private DataReader() {}

    public static void read(String resourceLocator, boolean external,
        boolean addToCache, boolean provideOutput) {
        String extension;

        // we take in a String
        // and turn it into an InputStream of some kind (specifically, a
        //     FileInputStream if it's local)
        // as well as a String representing the file's extension
        extension = getResourceInfo(resourceLocator, external)[1];
        // if extension is "" or could not be found, the above methods will
        //     throw an exception
        // decide whether to use readLCP, readZIP (each of which will eventually
        //     call readJSON), or readJSON directly, which sends its data along
        //     to DataParser after resolving
        if (extension.equals("lcp")) {
            if (provideOutput) {
                if (external) {
                    System.out.println("Reading external .lcp resource at URL:"
                        + " \"" + resourceLocator + "\"");
                } else {
                    System.out.println("Reading local .lcp file at file path:"
                        + " \"" + resourceLocator + "\"");
                }
            }
            readLCP(resourceLocator, external, addToCache, provideOutput);
        } else if (extension.equals("zip")) {
            if (provideOutput) {
                if (external) {
                    System.out.println("Reading external .zip resource at URL:"
                        + " \"" + resourceLocator + "\"");
                } else {
                    System.out.println("Reading local .zip file at file path:"
                        + " \"" + resourceLocator + "\"");
                }
            }
            readZIP(resourceLocator, external, addToCache, provideOutput);
        } else if (extension.equals("json")) {
            if (provideOutput) {
                if (external) {
                    System.out.println("Reading external .json resource at URL:"
                        + " \"" + resourceLocator + "\"");
                } else {
                    System.out.println("Reading local .json file at file path:"
                        + " \"" + resourceLocator + "\"");
                }
            }
            readJSON(resourceLocator, external, addToCache, provideOutput);
        }
        // once you're done, send that data along to DataCaster, which sends it
        //     along to DataCompiler
        DataParser.sendData();
    }
    private static void readArray(String[] resourceLocators, boolean external,
        boolean addToCache, Path targetFolderPath, boolean provideOutput) {
        String[] resourceNames;
        String[] resourceInfo;
        DatabaseResourceInfo[] resources;
        Object[] lcpInfoData;
        String cacheName = null;

        HelperMethods.checkStringArray("resourceLocators",
            resourceLocators);
        HelperMethods.checkObject("targetFolderPath",
            targetFolderPath);
        resourceNames = new String[resourceLocators.length];
        for (int i = 0; i < resourceLocators.length; i++) {
            resourceInfo = getResourceInfo(resourceLocators[i],
                external);
            resourceNames[i] = resourceInfo[0];
            if (! resourceInfo[1].equals("json")) {
                throw new IllegalArgumentException("resourceLocators contained"
                    + " an element with the following resource extension: \""
                    + resourceInfo[1] + "\" which is not .json");
            }
        }
        resources = DatabaseResourceInfo.toResourceInfo(resourceNames,
            resourceLocators);
        Arrays.sort(resources);
        if (provideOutput) {
            System.out.println("Reading elements of the provided array");
        }
        if (addToCache) {
            for (int i = 0; i < resources.length; i++) {
                if ("info".equals(resources[i].getName())) {
                    lcpInfoData = FileOperations.readAndParseResource(
                        resources[i].getPath(), external, false);
                    if (lcpInfoData.length != 1) {
                        throw new IllegalStateException("JSON file yielded"
                            + " either no data or the data of more than 1"
                            + " file");
                    }
                    if (lcpInfoData[0] instanceof JSONObject) {
                        try {
                            cacheName = ((JSONObject) lcpInfoData[0])
                                .getString("name");
                            continue;
                        } catch (JSONException exception) {}
                    }
                }
                if ("lcp_manifest".equals(resources[i].getName())) {
                    lcpInfoData = FileOperations.readAndParseResource(
                        resources[i].getPath(), external, false);
                    if (lcpInfoData.length != 1) {
                        throw new IllegalStateException("JSON file yielded"
                            + " either no data or the data of more than 1"
                            + " file");
                    }
                    if (lcpInfoData[0] instanceof JSONObject) {
                        try {
                            cacheName = ((JSONObject) lcpInfoData[0])
                                .getString("name");
                            continue;
                        } catch (JSONException exception) {}
                    }
                }
            }
            if (cacheName != null) {
                cacheName = FileOperations.toValidDirectoryName(cacheName);
                targetFolderPath = targetFolderPath.resolve(cacheName);
                FileOperations.createDirectory(targetFolderPath);
            } else {
                addToCache = false;
            }
        }
        for (int i = 0; i < resources.length; i++) {
            readJSON(resources[i].getPath(), external, addToCache,
                targetFolderPath, provideOutput);
        }
    }
    public static void readArray(String[] resourceLocators, boolean external,
        boolean addToCache, boolean provideOutput) {
        readArray(resourceLocators, external, addToCache,
            UserPreferences.getCacheDir(), provideOutput);
    }
    private static String[] getResourceInfo(String resourcePath,
        boolean external) {
        String resourceString;
        String name;
        String extension;

        resourceString = getResourceString(resourcePath, external);
        name = FileNameOperator.getName(resourceString);
        extension = FileNameOperator.getExtension(resourceString);
        if (! (extension.equals("lcp")
            || extension.equals("zip")
            || extension.equals("json"))) {
            if (extension.equals("")) {
                if (external) {
                    throw new IllegalArgumentException("Unable to find a"
                        + " resource extension in the URL: \"" + resourcePath
                        + "\"");
                } else {
                    throw new IllegalArgumentException("Unable to find a file"
                        + " extension in the file path: \"" + resourcePath
                        + "\"");
                }
            } else {
                if (external) {
                    throw new IllegalArgumentException("Cannot read a resource"
                        + " with the following resource extension: \"."
                        + extension + "\"");
                } else {
                    throw new IllegalArgumentException("Cannot read a file with"
                        + " the following file extension: \"." + extension
                        + "\"");
                }
            }
        }

        return new String[] {name, extension};
    }
    private static String getResourceString(String resourcePath,
        boolean external) {
        URL url;
        String filePath;
        String[] pathArray;

        HelperMethods.checkString("resourcePath", resourcePath);
        if (external) {
            // Created in part using
            //     https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
            try {
                url = FileOperations.toURL(resourcePath);
            } catch (URISyntaxException exception) {
                throw new IllegalArgumentException("URL: \"" + resourcePath
                    + "\" caused a URISyntaxException to be thrown");
            } catch (MalformedURLException exception) {
                throw new IllegalArgumentException("URL: \"" + resourcePath
                    + "\" caused a MalformedURLException to be thrown");
            }
            filePath = url.getFile();
            pathArray = filePath.split("/");

            return pathArray[pathArray.length - 1];
        } else {
            return Paths.get(resourcePath).toFile().getName();
        }

    }
    /**
     * Reads the provided .lcp file by converting it to a .zip, then calling
     *     DatabaseReader.readZIP().
     * @param lcpPath a String which must contain a valid file path. Is assumed
     *     to be a .lcp file. Cannot be null.
     */
    private static void readLCP(String lcpLocator, boolean external,
        boolean addToCache, boolean provideOutput) {
        readZIP(lcpLocator, external, addToCache, provideOutput);
    }
    /**
     * Unzips the provided .zip file before calling
     *     DatabaseReader.readAllInZIP() on its contents.
     * @param zipPath a String which must contain a valid file path. Is assumed
     *     to be a .zip file. Cannot be null.
     */
    private static void readZIP(String zipLocator, boolean external,
        boolean addToCache, boolean provideOutput) {
        // A directory of the .zip's contents will be created locally. This
        //     variable stores the file path to that directory.
        Path unzippedDirectoryPath;

        // unpack the zip
        if (provideOutput) {
            if (external) {
                System.out.println("Unzipping external resource at URL: \""
                    + zipLocator + "\"");
            } else {
                System.out.println("Unzipping local file at file path: \""
                    + zipLocator + "\"");
            }
        }
        unzippedDirectoryPath = FileOperations.unzip(zipLocator, external,
            addToCache);
        if (provideOutput) {
            System.out.println((external ? "External" : "Local") + " resource"
                + " unzipped to local directory path: \""
                + unzippedDirectoryPath + "\"");
        }
        // then call readAllInDirectory on it
        readAllInDirectory(unzippedDirectoryPath, false,
            provideOutput);
        if (provideOutput) {
            System.out.println("Deleting local directory at local path: \""
                + unzippedDirectoryPath + "\"");
        }
        // then delete the directory we've created to do this
        FileOperations.deleteDirectory(unzippedDirectoryPath);
    }
    /**
     * Reads a directory's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    public static void readAllInDirectory(Path directoryPath,
        boolean addToCache, boolean provideOutput) {
        String[][] fileData;
        String[] castedFile;

        if (provideOutput) {
            System.out.println("Reading all files under local directory at"
                + " path: \"" + directoryPath + "\"");
        }
        // call readJSON on every file within
        fileData = FileOperations.readAllInDirectoryIterable(directoryPath,
            addToCache);
        for (String[] jsonFile : fileData) {
            castedFile = (String[]) jsonFile;
            DataParser.parseJSON(castedFile[0], castedFile[1]);
        }
    }
    /**
     * Reads the provided .json file, then calls DatabaseReader.parseJSONFile()
     *     to parse its data.
     * @param jsonPath a String which must contain a valid file path. Must be a
     *     .json file. Cannot be null.
     */
    private static void readJSON(String jsonPath, boolean external,
        boolean addToCache, Path targetFolderPath, boolean provideOutput) {
        String[] data = null;
        String[] resourceInfo;
        String resourceString;

        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        if (addToCache) {
            HelperMethods.checkObject("targetFolderPath",
                targetFolderPath);
        }
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        data = FileOperations.readResource(jsonPath, external, addToCache);
        if (data.length > 1) {
            throw new IllegalStateException("Resource path: \"" + jsonPath
                + "\" lead to a .json file which resulted in more than one"
                + " file");
        } else if (data.length == 0) {
            throw new IllegalStateException("Resource path: \"" + jsonPath
                + "\" lead to a .json file which yielded zero files' worth of"
                + " data");
        }
        // Send the data on to FileParser
        DataParser.parseJSON(jsonPath, data[0]);
        if (addToCache) {
            resourceInfo = getResourceInfo(jsonPath, external);
            resourceString = resourceInfo[0] + "." + resourceInfo[1];
            FileOperations.createAndWriteToFile(resourceString,
                targetFolderPath, data[0], provideOutput);
        }
    }
    private static void readJSON(String jsonPath, boolean external,
        boolean addToCache, boolean provideOutput) {
        if (addToCache) {
            throw new IllegalStateException("Cannot call"
                + " DataReader.readJSON(String, boolean, true) because reading"
                + " a JSON file and storing it in the cache afterwards requires"
                + " a target directory path");
        }
        readJSON(jsonPath, external, addToCache, null,
            provideOutput);
    }
}
