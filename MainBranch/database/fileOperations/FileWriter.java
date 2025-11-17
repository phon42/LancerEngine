package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {
    // Prevent user from instantiating
    private FileWriter() {}

    public static Path writeToFile(Path path, String data,
        boolean provideOutput) throws UnsupportedOperationException,
        SecurityException {
        // Created using
        //     https://www.baeldung.com/java-write-to-file#write-with-files-class
        byte[] dataBytes;

        if (provideOutput) {
            System.out.println("Attempting to write to file at local file path:"
                + " \"" + path + "\"");
        }
        dataBytes = data.getBytes();
        try {
            path = Files.write(path, dataBytes);
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to write to file at"
                + " path: \"" + path + "\" threw an IOException with the"
                + " following message: \"" + exception.getMessage() + "\"");
        }
        path = path.toAbsolutePath();
        if (provideOutput) {
            System.out.println("File successfully written to at local file"
                + " path: \"" + path + "\"");
        }

        return path;
    }
}
