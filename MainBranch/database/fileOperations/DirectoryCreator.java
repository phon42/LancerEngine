package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryCreator {
    // Prevent user from instantiating
    private DirectoryCreator() {}

    public static Path createDirectory(Path path) throws SecurityException {
        // Created using the following URLs:
        // Mainly used:
        // - https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
        // Partially used:
        // - https://medium.com/@AlexanderObregon/javas-files-createdirectories-method-explained-cb824678b0b7
        // - https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#createDirectory-java.nio.file.Path-java.nio.file.attribute.FileAttribute...-
        try {
            path = Files.createDirectory(path);
        } catch (FileAlreadyExistsException exception) {
            // do nothing because we've successfully created it
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to create the directory"
                + " threw an IOException with the following message: \""
                + exception.getMessage() + "\"");
        }
        path = path.toAbsolutePath();

        return path;
    }
}
