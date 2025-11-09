package MainBranch.database.DatabaseReadingPipeline;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;

public class DataReader {
    // prevent user from instantiating this class
    private DataReader() {}

    public static void read(String resourceLocator, boolean external) {
        String extension;

        // we take in a String
        // and turn it into an InputStream of some kind (specifically, a
        //     FileInputStream if it's local)
        // as well as a String representing the file's extension
        if (external) {
            extension = getExternalExtension(resourceLocator);
        } else {
            extension = getLocalExtension(resourceLocator);
        }
        // if extension is "" or could not be found, the above methods will
        //     throw an exception
        // decide whether to use readLCP, readZIP (each of which will eventually
        //     call readJSON), or readJSON directly, which sends its data along
        //     to DataParser after resolving
        if (extension.equals("lcp")) {
            readLCP(resourceLocator, external);
        } else if (extension.equals("zip")) {
            readZIP(resourceLocator, external);
        } else if (extension.equals("json")) {
            readJSON(resourceLocator, external);
        }
        // once you're done, send that data along to DataCaster, which sends it
        //     along to DataCompiler
        DataParser.sendData();
    }
    /**
     * Reads the provided file and saves its contents. Can read .lcp, .zip, or
     *     individual .json files. Calls DatabaseReader.readLCP(),
     *     DatabaseReader.readZIP(), or DatabaseReader.readJSON() based on what
     *     type the file is.
     * @param filePath a String which must contain a valid file path. Cannot be
     *     null.
     */
    private static String getLocalExtension(String filePath) {
        String fileExtension;
        String[] fileStrings;

        HelperMethods.checkString("filePath", filePath);
        // extract the file extension
        // TODO: check to make sure this actually works as expected, i.e.
        //     with a .tar.gz resource
        fileExtension = Paths.get(filePath).toFile().getName();
        if (fileExtension.indexOf(".") != -1) {
            fileStrings = fileExtension.split("\\.");
            // this is the step that needs to be changed to deal with .tar.gz
            fileExtension = fileStrings[fileStrings.length - 1];
        }
        if (! (fileExtension.equals("lcp")
            || fileExtension.equals("zip")
            || fileExtension.equals("json"))) {
            if (fileExtension.equals("")) {
                throw new IllegalArgumentException("Unable to find a file"
                    + " extension in the file path: \"" + filePath + "\"");
            } else {
                throw new IllegalArgumentException("Cannot read a file with the"
                    + " following file extension: \"." + fileExtension + "\"");
            }
        }

        return fileExtension;
    }
    private static String getExternalExtension(String url) {
        // Created in part using
        //     https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        URL resource;
        String pageExtension;
        String[] pathArray;

        try {
            resource = new URL(url);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("url: \"" + url + "\" caused a"
                + " MalformedURLException to be thrown");
        }
        pageExtension = resource.getFile();
        pathArray = pageExtension.split("/");
        pageExtension = pathArray[pathArray.length - 1];
        try {
            pathArray = pageExtension.split("\\.");
            pageExtension = pathArray[pathArray.length - 1];
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Unable to find a file extension"
                + " in the URL: \"" + url + "\"");
        }
        if (pageExtension == null) {
            throw new IllegalArgumentException("Unable to find a resource"
                    + " extension in the URL: \"" + url + "\"");
        }
        if (! (pageExtension.equals("lcp")
            || pageExtension.equals("zip")
            || pageExtension.equals("json"))) {
            if (pageExtension.equals("")) {
                throw new IllegalArgumentException("Unable to find a resource"
                    + " extension in the URL: \"" + url + "\"");
            } else {
                throw new IllegalArgumentException("Cannot read a resource with"
                    + " the following resource extension: \"." + pageExtension
                    + "\"");
            }
        }

        return pageExtension;
    }
    /**
     * Reads the provided .lcp file by converting it to a .zip, then calling
     *     DatabaseReader.readZIP().
     * @param lcpPath a String which must contain a valid file path. Is assumed
     *     to be a .lcp file. Cannot be null.
     */
    private static void readLCP(String lcpPath, boolean external) {
        readZIP(lcpPath, external);
    }
    /**
     * Unzips the provided .zip file before calling
     *     DatabaseReader.readAllInZIP() on its contents.
     * @param zipPath a String which must contain a valid file path. Is assumed
     *     to be a .zip file. Cannot be null.
     */
    private static void readZIP(String zipPath, boolean external) {
        // A directory of the .zip's contents will be created locally. This
        //     variable stores the file path to that directory.
        String unzippedDirectoryPath;

        // unpack the zip
        unzippedDirectoryPath = FileOperations.unzip(zipPath, external);
        // then call readAllInDirectory on it
        readAllInDirectory(unzippedDirectoryPath);
        // then delete the directory we've created to do this
        FileOperations.deleteDirectory(unzippedDirectoryPath);
    }
    /**
     * Reads a directory's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    private static void readAllInDirectory(String directoryPath) {
        Iterable<Object> fileData;
        String[] castedFile;

        // call readJSON on every file within
        fileData = FileOperations.readAllInDirectoryIterable(directoryPath);
        for (Object jsonFile : fileData) {
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
    private static void readJSON(String jsonPath, boolean external) {
        String[] data = null;

        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        data = FileOperations.readResource(jsonPath, external);
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
