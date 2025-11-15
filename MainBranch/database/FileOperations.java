package MainBranch.database;

import MainBranch.UserPreferences;
import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.FileCreator;
import MainBranch.database.fileOperations.ResourceParser;
import MainBranch.database.fileOperations.ResourceReader;
import MainBranch.database.fileOperations.ZIPUnzipper;

public class FileOperations {
    // prevent user from instantiating this class
    private FileOperations() {}

    public static String[] readResource(String resourceLocator,
        boolean external, boolean addToCache) {
        return ResourceReader.read(resourceLocator, external, addToCache);
    }
    public static Object parseJSONText(String jsonData) {
        return ResourceParser.parseJSONText(jsonData);
    }
    public static String createFile(String fileNameAndExtension,
        String targetDirectoryPath, String data) {
        return FileCreator.createFile(fileNameAndExtension,
            targetDirectoryPath);
    }
    public static String writeToFile(String filePath, String data) {
        return FileWriter.writeToFile(filePath, data);
    }
    public static void deleteDirectory(String directoryPath) {
        DirectoryDeleter.deleteDirectory(directoryPath);
    }
    private static String unzip(String zipResourceLocator, boolean external,
        String targetDirectoryPath) {
        return ZIPUnzipper.unzip(zipResourceLocator, external,
            targetDirectoryPath);
    }
    public static String unzip(String zipResourceLocator, boolean external,
        boolean addToCache) {
        if (addToCache) {
            return unzip(zipResourceLocator, external,
                UserPreferences.getTargetDir());
        } else {
            return unzip(zipResourceLocator, external,
                UserPreferences.getCacheDir());
        }
        
    }
    public static String[] getAllFilenamesInDirectory(String directoryPath) {
        return ResourceReader.getAllFilenamesInDirectory(directoryPath);
    }
    public static String[] readAllInDirectory(String directoryPath,
        boolean addToCache) {
        return ResourceReader.readAllInDirectory(directoryPath, addToCache);
    }
    public static String[][] readAllInDirectoryIterable(String directoryPath,
        boolean addToCache) {
        String[] fileNames;
        // index 0 is the file path
        // index 1 is the file data
        String[][] result;

        fileNames = ResourceReader.readAllInDirectory(directoryPath, addToCache);
        result = new String[fileNames.length][2];
        for (int i = 0; i < fileNames.length; i++) {
            result[i][0] = fileNames[i];
            result[i][1] = readResource(fileNames[i], false,
                addToCache)[0];
        }

        return result;
    }
}
