package MainBranch.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.JSON;
import MainBranch.database.fileOperations.ResourceParser;
import MainBranch.database.fileOperations.ResourceReader;
import MainBranch.database.fileOperations.ZIPUnzipper;
import MainBranch.database.fileOperations.json.JSONException;

public class FileOperations {
    // prevent user from instantiating this class
    private FileOperations() {}

    public static String[] readResource(String resourceLocator,
        boolean external) {
        return ResourceReader.read(resourceLocator, external);
    }
    public static Object parseJSONText(String jsonData) {
        return ResourceParser.parseJSONText(jsonData);
    }
    public static void deleteDirectory(String directoryPath) {
        DirectoryDeleter.deleteDirectory(directoryPath);
    }
    private static String unzip(String zipResourceLocator, boolean external,
        String targetDirectoryPath) {
        return ZIPUnzipper.unzip(zipResourceLocator, external,
            targetDirectoryPath);
    }
    public static String unzip(String zipResourceLocator, boolean external) {
        return unzip(zipResourceLocator, external,
            "MainBranch/ProgramData/Target/");
    }
}
