package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreator {
    // Prevent user from instantiating
    private FileCreator() {}

    public static String createFile(String fileNameAndExtension,
        String targetDirectoryPath, String data) {
        String filePathString;
        Path filePath;

        filePathString = targetDirectoryPath + fileNameAndExtension;
        filePath = Paths.get(filePathString);
        try {
            filePath = Files.createFile(filePath);
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to create the file"
                + " threw an IOException with the following method: \""
                + exception.getMessage() + "\"");
        }
        filePath = filePath.toAbsolutePath();

        return filePath.toString();
    }
}
