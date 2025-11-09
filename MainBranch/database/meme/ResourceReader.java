package MainBranch.database.meme;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import MainBranch.database.FileOperations;
import MainBranch.HelperMethods;

public class ResourceReader {
    // prevent user from instantiating this class
    private ResourceReader() {}

    public static String[] read(String resourceLocator, boolean external) {
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
            return readLCP(resourceLocator, external);
        } else if (extension.equals("zip")) {
            return readZIP(resourceLocator, external);
        } else if (extension.equals("json")) {
            return new String[] {readJSON(resourceLocator, external)};
        } else {
            throw new IllegalStateException("this should in theory be"
                + " impossible");
        }
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
    private static String[] readLCP(String lcpPath, boolean external) {
        return readZIP(lcpPath, external);
    }
    /**
     * Unzips the provided .zip file before calling
     *     DatabaseReader.readAllInZIP() on its contents.
     * @param zipPath a String which must contain a valid file path. Is assumed
     *     to be a .zip file. Cannot be null.
     */
    private static String[] readZIP(String zipPath, boolean external) {
        // A directory of the .zip's contents will be created locally. This
        //     variable stores the file path to that directory.
        String unzippedDirectoryPath;
        String[] zipContents;

        // unzip the zip
        try {
            unzippedDirectoryPath = FileOperations.unzip(zipPath);
        } catch (FileNotFoundException exception) {
            throw new MissingResourceException(exception.getMessage(),
                "File", new File(zipPath).getName());
        } catch (IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
        // then call readAllInDirectory on it
        zipContents = FileOperations.readAllInDirectory(unzippedDirectoryPath);
        // then delete the directory we've created to do this
        FileOperations.deleteDirectory(unzippedDirectoryPath);

        return zipContents;
    }
    /**
     * Reads a directory's contents by calling DatabaseReader.readJSON() on
     *     every file within.
     * @param directoryPath a String which must contain a valid directory path.
     *     Is assumed to be a directory. Cannot be null.
     */
    public static String[] readAllInDirectory(String directoryPath) {
        // TODO: complete
        // Created using https://www.baeldung.com/java-list-directory-files
        // call readJSON on every file within
        String[] directoryContents;

        return directoryContents;
    }
    /**
     * Reads the provided .json file, then calls DatabaseReader.parseJSONFile()
     *     to parse its data.
     * @param jsonPath a String which must contain a valid file path. Must be a
     *     .json file. Cannot be null.
     */
    private static String readJSON(String jsonPath, boolean external) {
        // Check whether jsonPath is null
        HelperMethods.checkObject("jsonPath", jsonPath);
        // Check whether jsonPath actually corresponds to:
        // 1. A valid file path
        // 2. A valid *JSON* file path.
        // TODO: complete
        // Parse the .json file

        return readFile(jsonPath, external);
    }
    private static String readFile(String fileLocator, boolean external) {
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
