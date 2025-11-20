package MainBranch.database;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import MainBranch.HelperMethods;
import MainBranch.UserPreferences;
import MainBranch.database.fileOperations.DirectoryCreator;
import MainBranch.database.fileOperations.DirectoryDeleter;
import MainBranch.database.fileOperations.DirectoryExplorer;
import MainBranch.database.fileOperations.FileCreator;
import MainBranch.database.fileOperations.FileNameOperator;
import MainBranch.database.fileOperations.FileSystemNamer;
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
    public static URL toURL(String urlString) throws URISyntaxException,
        MalformedURLException {
        URI uri;
        URL url;

        uri = new URI(urlString);
        url = uri.toURL();

        return url;
    }
    public static URL toURLCaught(String urlString) throws
        IllegalArgumentException {
        URL url;

        try {
            url = FileOperations.toURL(urlString);
        } catch (URISyntaxException exception) {
            throw new IllegalArgumentException("URL: \"" + urlString + "\""
                + " caused a URISyntaxException to be thrown");
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("URL: \"" + urlString + "\""
                + " caused a MalformedURLException to be thrown");
        }

        return url;
    }
    public static String[] readResource(String resourceLocator,
        boolean external, boolean addToCache) {
        return ResourceReader.read(resourceLocator, external, addToCache);
    }
    public static Object parseJSONText(String jsonData) {
        return ResourceParser.parseJSONText(jsonData);
    }
    public static Object[] readAndParseResource(String resourceLocator,
        boolean external, boolean addToCache) {
        String[] resourceData;
        Object[] parsedJSON;

        resourceData = readResource(resourceLocator, external, addToCache);
        parsedJSON = new Object[resourceData.length];
        for (int i = 0; i < resourceData.length; i++) {
            if (resourceData[i] == null) {
                parsedJSON[i] = null;
            } else {
                parsedJSON[i] = parseJSONText(resourceData[i]);
            }
        }

        return parsedJSON;
    }
    public static Path createDirectory(Path path) {
        return DirectoryCreator.createDirectory(path);
    }
    public static Path createFile(String fileNameAndExtension,
        Path targetDirectoryPath, boolean provideOutput) {
        return FileCreator.createFile(fileNameAndExtension,
            targetDirectoryPath, provideOutput);
    }
    public static Path writeToFile(Path filePath, String data,
        boolean provideOutput) {
        return FileWriter.writeToFile(filePath, data, provideOutput);
    }
    public static Path createAndWriteToFile(String fileNameAndExtension,
        Path targetDirectoryPath, String data, boolean provideOutput) {
        Path filePath;

        filePath = createFile(fileNameAndExtension, targetDirectoryPath,
            provideOutput);
        filePath = writeToFile(filePath, data, provideOutput);

        return filePath;
    }
    public static Path deleteDirectory(Path path) {
        return DirectoryDeleter.deleteDirectory(path);
    }
    public static Iterator<Path> listDirectoryContentsAsIterator(Path path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsIterator(path,
            includeDirectories);
    }
    public static Path[] listDirectoryContentsAsArray(Path path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsArray(path, includeDirectories);
    }
    public static ArrayList<Path> listDirectoryContentsAsArrayList(Path path,
        boolean includeDirectories) {
        return DirectoryExplorer.listContentsAsArrayList(path,
            includeDirectories);
    }
    private static Path unzip(String zipResourceLocator, boolean external,
        Path targetDirectoryPath) {
        return ZIPUnzipper.unzip(zipResourceLocator, external,
            targetDirectoryPath);
    }
    public static Path unzip(String zipResourceLocator, boolean external,
        boolean addToCache) {
        if (addToCache) {
            return unzip(zipResourceLocator, external,
                UserPreferences.getTargetDir());
        } else {
            return unzip(zipResourceLocator, external,
                UserPreferences.getCacheDir());
        }
    }
    public static Path[] getAllFilenamesInDirectory(Path directoryPath) {
        return DirectoryExplorer.getAllFilenamesInDirectory(directoryPath);
    }
    public static String[] readAllInDirectory(Path directoryPath,
        boolean addToCache) {
        return ResourceReader.readAllInDirectory(directoryPath, addToCache);
    }
    public static String[][] readAllInDirectoryIterable(Path directoryPath,
        boolean addToCache) {
        Path[] filePaths;
        String[] fileData;
        // index 0 is the file path
        // index 1 is the file data
        String[][] result;

        filePaths = FileOperations.getAllFilenamesInDirectory(directoryPath);
        fileData = ResourceReader.readAllInDirectory(directoryPath, addToCache);
        result = new String[fileData.length][2];
        for (int i = 0; i < fileData.length; i++) {
            result[i][0] = filePaths[i].toString();
            result[i][1] = fileData[i];
        }

        return result;
    }
    public static String toValidDirectoryName(String name) {
        return FileSystemNamer.toValidDirectoryName(name);
    }
    public static String[] splitResourceString(String resourceString) {
        return FileNameOperator.splitResourceString(resourceString);
    }
    public static String getName(String resourceString) {
        return FileNameOperator.getName(resourceString);
    }
    public static String getExtension(String resourceString) {
        return FileNameOperator.getExtension(resourceString);
    }
    public static String getResourceString(String resourcePath,
        boolean external) {
        URL url;
        String filePath;
        String[] pathArray;

        HelperMethods.checkString("resourcePath", resourcePath);
        if (external) {
            // Created in part using
            //     https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
            url = FileOperations.toURLCaught(resourcePath);
            filePath = url.getFile();
            pathArray = filePath.split("/");

            return pathArray[pathArray.length - 1];
        } else {
            return Paths.get(resourcePath).toFile().getName();
        }

    }
}
