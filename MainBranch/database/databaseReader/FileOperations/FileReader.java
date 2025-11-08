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

public class FileReader {
    // prevent user from instantiating this class
    private FileReader() {}

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
        // decide whether to use readLCP, readZIP, or readJSON directly
        if (extension.equals("lcp")) {
            readLCP(resourceLocator, external);
        } else if (extension.equals("zip")) {
            readZIP(resourceLocator, external);
        } else if (extension.equals("json")) {
            readJSON(resourceLocator, external);
        }
        // once you're done, send that data along to DataCaster, which sends it
        //     along to DataCompiler
        FileParser.sendData();
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

        return fileExtension;
    }
    private static String getExternalExtension(String url) {
        // https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        URL resource;
        String pageExtension;

        try {
            resource = new URL(url);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("url: \"" + url + "\" caused a"
                + " MalformedURLException to be thrown");
        }
        pageExtension = resource.getPath();
        pageExtension = pageExtension.replaceAll("/", "");
        try {
            pageExtension = pageExtension.split("\\.")[0];
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Unable to find a file extension"
                + " in the URL: \"" + url + "\"");
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
        try {
            unzippedDirectoryPath = ZIPUnzipper.unzip(zipPath);
        } catch (FileNotFoundException exception) {
            throw new MissingResourceException(exception.getMessage(),
                "File", new File(zipPath).getName());
        } catch (IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
        // then call readAllInDirectory on it
        readAllInDirectory(unzippedDirectoryPath);
        // then delete the directory we've created to do this
        FolderDeleter.deleteFolder(unzippedDirectoryPath);
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
    private static void readJSON(String jsonPath, boolean external) {
        String data = null;

        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file
        data = readFile(jsonPath, external);
        // Send the data on to FileParser
        FileParser.parseJSON(jsonPath, data);
    }
    public static String readFile(String fileLocator, boolean external) {
        String data;

        if (external) {
            data = readExternalFile(fileLocator);
        } else {
            data = readLocalFile(fileLocator);
        }

        return data;
    }
    private static String readLocalFile(String filePath) {
        String data = "";
        FileInputStream input;
        InputStreamReader reader;
        int character;

        try {
            input = new FileInputStream(filePath);
        } catch (FileNotFoundException exception) {
            throw new MissingResourceException("File at path: \"" + filePath
                + "\" could not be located", "File", filePath);
        }
        reader = new InputStreamReader(input);
        try {
            character = reader.read();
            while (character != -1) {
                data += (char) character;
                character = reader.read();
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Error reading file");
        } finally {
            try {
                reader.close();
            } catch (IOException exception) {
                throw new IllegalStateException("Attempting to call"
                    + " InputStreamReader.close() on the file threw an"
                    + " IOException");
            }
        }

        return data;
    }
}
