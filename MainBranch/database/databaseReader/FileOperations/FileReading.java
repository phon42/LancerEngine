package MainBranch.database.databaseReader.FileOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.MissingResourceException;

import MainBranch.HelperMethods;
import MainBranch.database.DatabaseReader;
import MainBranch.database.databaseReader.FileOperations.fileReading.FileProcessor;
import MainBranch.database.databaseReader.FileOperations.fileReading.ZIPUnzipper;

public class FileReading {
    // prevent user from instantiating this class
    private FileReading() {}

    /**
     * Reads the provided file and saves its contents. Can read .lcp, .zip, or
     *     individual .json files. Calls DatabaseReader.readLCP(),
     *     DatabaseReader.readZIP(), or DatabaseReader.readJSON() based on what
     *     type the file is.
     * @param filePath a String which must contain a valid file path. Cannot be
     *     null.
     */
    public static void readData(String filePath) {
        String fileExtension;
        String[] fileStrings;

        // extract the file extension
        // TODO: check to make sure this actually works as expected, i.e.
        //     .tar.gz
        fileExtension = Paths.get(filePath).toFile().getName();
        if (fileExtension.indexOf(".") != -1) {
            fileStrings = fileExtension.split("\\.");
            // this is the step that needs to be changed to deal with .tar.gz
            fileExtension = fileStrings[fileStrings.length - 1];
        }
        // decide whether to use readLCP, readZIP, or readJSON directly
        if (fileExtension.equals("lcp")) {
            readLCP(filePath);
        } else if (fileExtension.equals("zip")) {
            readZIP(filePath);
        } else if (fileExtension.equals("json")) {
            readJSON(filePath);
        } else {
            if (fileExtension.equals("")) {
                throw new IllegalArgumentException("Unable to find a file"
                    + " extension in the file path: \"" + filePath + "\"");
            } else {
                throw new IllegalArgumentException("Cannot read a file with the"
                    + " following file extension: \"." + fileExtension + "\"");
            }
        }
    }
    /**
     * Reads the provided .lcp file by converting it to a .zip, then calling
     *     DatabaseReader.readZIP().
     * @param lcpPath a String which must contain a valid file path. Is assumed
     *     to be a .lcp file. Cannot be null.
     */
    private static void readLCP(String lcpPath) {
        readZIP(lcpPath);
    }
    /**
     * Unzips the provided .zip file before calling
     *     DatabaseReader.readAllInZIP() on its contents.
     * @param zipPath a String which must contain a valid file path. Is assumed
     *     to be a .zip file. Cannot be null.
     */
    private static void readZIP(String zipPath) {
        String directoryPath = null;

        // unpack the zip
        try {
            directoryPath = ZIPUnzipper.unzip(zipPath);
        } catch (FileNotFoundException exception) {
            throw new MissingResourceException(exception.getMessage(),
                "File", new File(zipPath).getName());
        } catch (IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
        // then call readAllInZIP on it
        readAllInDirectory(directoryPath);
        // then delete the zip we've created to do this
        FolderDeleter.deleteFolder(directoryPath);
    }
    /**
     * Reads a directory's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    private static void readAllInDirectory(String directoryPath) {
        // TODO: complete
        // Created using https://www.baeldung.com/java-list-directory-files
        // call readJSON on every file within
    }
    /**
     * Reads the provided .json file, then calls DatabaseReader.parseJSONFile()
     *     to parse its data.
     * @param jsonPath a String which must contain a valid file path. Must be a
     *     .json file. Cannot be null.
     */
    private static void readJSON(String jsonPath) {
        String data = null;

        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        data = FileProcessor.readFile(jsonPath);
        // Send the data on to DataCaster
        saveJSONData(jsonPath, data);
    }
    private static void saveJSONData(String filePath, String fileData) {
        // Send the data on to DataCaster through DataCaster.parseJSONFile()
        DatabaseReader.parseJSONFile(filePath, fileData);
    }
}
