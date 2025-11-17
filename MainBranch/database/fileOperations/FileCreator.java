package MainBranch.database.fileOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import MainBranch.database.FileOperations;

public class FileCreator {
    // Prevent user from instantiating
    private FileCreator() {}

    public static Path createFile(String fileNameAndExtension,
        Path targetDirectoryPath, boolean provideOutput) throws
        SecurityException {
        // Created using https://www.baeldung.com/java-how-to-create-a-file#jdk7
        String filePathString;
        Path filePath;

        filePathString = targetDirectoryPath.toString() + File.separator
            + fileNameAndExtension;
        filePath = FileOperations.toPath(filePathString);
        if (provideOutput) {
            System.out.println("Attempting to create file at local file path:"
                + " \"" + filePath.toString() + "\"");
        }
        try {
            filePath = Files.createFile(filePath);
        } catch (FileAlreadyExistsException exception) {
            // do nothing because we've successfully created it
            if (provideOutput) {
                System.out.println("File was already present at local file"
                    + " path: \"" + filePath + "\". Continuing");
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to create the file"
                + " threw an IOException with the following message: \""
                + exception.getMessage() + "\"");
        }
        filePath = filePath.toAbsolutePath();
        if (provideOutput) {
            System.out.println("File successfully created at local file path:"
                + " \"" + filePath + "\"");
        }

        return filePath;
    }
    public static File createFile(Path destDirPath, ZipEntry zipEntry)
        throws IOException {
        File destinationDirectory;
        File newFile;
        String destFilePath;

        destinationDirectory = destDirPath.toFile();
        newFile = new File(destinationDirectory, zipEntry.getName());
        destFilePath = newFile.getCanonicalPath();
        destDirPath = FileOperations.toPath(
            destinationDirectory.getCanonicalPath());
        if (! destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: \""
                + zipEntry.getName() + "\"");
        }

        return newFile;
    }
}
