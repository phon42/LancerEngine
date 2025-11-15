package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {
    // Prevent user from instantiating
    private FileWriter() {}

    public static String writeToFile(String filePath, String data) throws
        UnsupportedOperationException, SecurityException {
        // Created using
        //     https://www.baeldung.com/java-write-to-file#write-with-files-class
        Path path;
        byte[] dataBytes;

        path = FileSystems.getDefault().getPath(filePath);
        path = path.normalize().toAbsolutePath();
        filePath = path.toString();
        dataBytes = data.getBytes();
        try {
            Files.write(path, dataBytes);
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to write to file at"
                + " path: \"" + filePath + "\" threw an IOException with the"
                + " following message: \"" + exception.getMessage() + "\"");
        }

        return filePath;
    }
}
