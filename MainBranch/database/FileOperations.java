package MainBranch.database;

import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.ResourceParser;
import MainBranch.database.fileOperations.ResourceReader;
import MainBranch.database.fileOperations.ZIPUnzipper;

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
    public static String[][] readAllInDirectoryIterable(String directoryPath) {
        String[][] result;

        result = new String[someLength][2];
        // index 0 is the file path
        // index 1 is the file data

        return result;
    }
    public static String[] readAllInDirectory(String directoryPath) {
        String[][] uncompactedResult;
        String[] result;

        uncompactedResult = readAllInDirectoryIterable(directoryPath);
        result = new String[uncompactedResult.length];
        for (int i = 0; i < uncompactedResult.length; i++) {
            result[i] = uncompactedResult[i][1];
        }

        return result;
    }
}
