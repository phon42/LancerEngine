package MainBranch.database.DatabaseReadingPipeline;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import MainBranch.database.DatabaseReadingPipeline.dataReader.DatabaseResourceInfo;

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
            readJSON(resourceLocator, external, addToCache);
        }
        // once you're done, send that data along to DataCaster, which sends it
        //     along to DataCompiler
        DataParser.sendData();
    }
    public static void readArray(String[] resourceLocators, boolean external,
        boolean addToCache) {
        String[] resourceNames;
        String[] extensions;
        String[] resourceInfo;
        DatabaseResourceInfo[] resources;

        // TODO: add the ability to add all of these files to the cache under
        //     the same folder
        HelperMethods.checkStringArray("resourceLocators",
            resourceLocators);
        resourceNames = new String[resourceLocators.length];
        extensions = new String[resourceLocators.length];
        for (int i = 0; i < resourceLocators.length; i++) {
            resourceInfo = getResourceInfo(resourceLocators[i],
                external);
            resourceNames[i] = resourceInfo[0];
            extensions[i] = resourceInfo[1];
            if (! extensions[i].equals("json")) {
                throw new IllegalArgumentException("resourceLocators contained"
                    + " an element with the following resource extension: \""
                    + extensions[i] + "\" which is not .json");
            }
        }
        resources = DatabaseResourceInfo.toResourceInfo(resourceNames,
            resourceLocators);
        Arrays.sort(resources);
        if (provideOutput) {
            System.out.println("Reading elements of the provided array");
        }
        for (int i = 0; i < resourceLocators.length; i++) {
            readJSON(resources[i].getPath(), external, addToCache);
        }
        DataParser.sendData();
    }
    private static String[] getResourceInfo(String resourcePath,
        boolean external) {
        String resourceString;
        String name;
        String extension;

        resourceString = getResourceString(resourcePath, external);
        name = getName(resourceString);
        extension = getExtension(resourceString);
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
                url = new URL(resourcePath);
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
    private static String getName(String resourceString) {
        String[] splitString;

        HelperMethods.checkString("resourceString",
            resourceString);
        splitString = resourceString.split("\\.");
        if (splitString.length < 2) {
            throw new IllegalArgumentException("resourceString: \""
                + resourceString + "\" did not yield both a name and an"
                + " extension");
        }

        return splitString[0];
    }
    private static String getExtension(String resourceString) {
        String[] splitString;

        HelperMethods.checkString("resourceString",
            resourceString);
        splitString = resourceString.split("\\.");
        if (splitString.length < 2) {
            throw new IllegalArgumentException("resourceString: \""
                + resourceString + "\" did not yield both a name and an"
                + " extension");
        }

        // TODO: change to work with a .tar.gz resource
        return splitString[splitString.length - 1];
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
        String unzippedDirectoryPath;

        // TODO: add a small optimization to peek at the file's info file and
        //     see if that LCP has been cached
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
            if (external) {
                System.out.println("External resource unzipped to local"
                    + " directory path: \"" + unzippedDirectoryPath + "\"");
            } else {
                System.out.println("Local resource unzipped to local directory"
                    + " path: \"" + unzippedDirectoryPath + "\"");
            }
        }
        // then call readAllInDirectory on it
        readAllInDirectory(unzippedDirectoryPath, addToCache, provideOutput);
        if (! addToCache) {
            if (provideOutput) {
                System.out.println("Deleting local directory at local path: \""
                    + unzippedDirectoryPath + "\"");
            }
            // then delete the directory we've created to do this
            FileOperations.deleteDirectory(unzippedDirectoryPath);
        }
    }
    /**
     * Reads a directory's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    public static void readAllInDirectory(String directoryPath,
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
        boolean addToCache) {
        String[] data = null;

        // TODO: add the ability to cache JSON files
        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
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
    }
}
