package MainBranch.database;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import MainBranch.UserPreferences;
import MainBranch.database.fileOperations.DirectoryCreator;
import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.DirectoryExplorer;
import MainBranch.database.fileOperations.FileCreator;
import MainBranch.database.fileOperations.FileWriter;
import MainBranch.database.fileOperations.ResourceParser;
import MainBranch.database.fileOperations.ResourceReader;
import MainBranch.database.fileOperations.ZIPUnzipper;

public class FileOperations {
    // prevent user from instantiating this class
    private FileOperations() {}

    public static Path toPath(String filePath) {
        Path path;

        // alternatively use Paths.get() or Path.of()
        path = FileSystems.getDefault().getPath(filePath);
        path = path.normalize().toAbsolutePath();

        return path;
    }
    public static String[] readResource(String resourceLocator,
        boolean external, boolean addToCache) {
        return ResourceReader.read(resourceLocator, external, addToCache);
    }
    public static Object parseJSONText(String jsonData) {
        return ResourceParser.parseJSONText(jsonData);
    }
    public static String createDirectory(String path) {
        return DirectoryCreator.createDirectory(path);
    }
    public static String createFile(String fileNameAndExtension,
        String targetDirectoryPath, boolean provideOutput) {
        return FileCreator.createFile(fileNameAndExtension,
            targetDirectoryPath, provideOutput);
    }
    public static String writeToFile(String filePath, String data,
        boolean provideOutput) {
        return FileWriter.writeToFile(filePath, data, provideOutput);
    }
    public static String createAndWriteToFile(String fileNameAndExtension,
        String targetDirectoryPath, String data, boolean provideOutput) {
        String filePath;

        filePath = createFile(fileNameAndExtension, targetDirectoryPath,
            provideOutput);
        filePath = writeToFile(filePath, data, provideOutput);

        return filePath;
    }
    public static void deleteDirectory(String path) {
        DirectoryDeleter.deleteDirectory(path);
    }
    public static Iterator<Path> listDirectoryContentsAsIterator(String path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsIterator(path,
            includeDirectories);
    }
    public static Path[] listDirectoryContentsAsArray(String path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsArray(path, includeDirectories);
    }
    public static ArrayList<Path> listDirectoryContentsAsArrayList(String path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsArrayList(path,
            includeDirectories);
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
        return DirectoryExplorer.getAllFilenamesInDirectory(directoryPath);
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
