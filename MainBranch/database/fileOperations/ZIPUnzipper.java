package MainBranch.database.fileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import MainBranch.database.FileOperations;

public class ZIPUnzipper {
    // Created using https://www.baeldung.com/java-compress-and-uncompress#unzip
    public static Path unzip(String zipResourceLocator, boolean external,
        Path destDirPath) {
        URL url;
        InputStream inputStream;
        ZipInputStream zipStream;
        ZipEntry zipEntry;
        String originZIPName;

        // all of this is just to get a ZipInputStream
        if (external) {
            url = FileOperations.toURLCaught(zipResourceLocator);
            try {
                inputStream = url.openStream();
            } catch (IOException exception) {
                throw new IllegalArgumentException("URL: \""
                    + zipResourceLocator + "\" caused an IOException to be"
                    + " thrown");
            }
        } else {
            try {
                inputStream = new FileInputStream(zipResourceLocator);
            } catch (FileNotFoundException exception) {
                throw new IllegalStateException("File at file path: \""
                    + zipResourceLocator + "caused a FileNotFoundException to"
                    + " be thrown with the following message: \""
                    + exception.getMessage());
            }
        }
        zipStream = new ZipInputStream(inputStream);
        // end above
        // all of this is just to do the line "zis.getNextEntry()"
        try {
            zipEntry = zipStream.getNextEntry();
        } catch (IOException exception) {
            if (external) {
                throw new IllegalArgumentException("URL: \""
                    + zipResourceLocator + "\" caused an IOException to be"
                    + " thrown");
            } else {
                throw new IllegalStateException("File at file path: \""
                    + zipResourceLocator + "caused an IOException to be"
                    + " thrown");
            }
        }
        // end above
        // checking if zipStream is empty
        if (zipEntry == null) {
            try {
                zipStream.close();
            } catch (IOException exception) {
                if (external) {
                    throw new IllegalArgumentException("URL: \""
                        + zipResourceLocator + "\" caused an IOException to be"
                        + " thrown");
                } else {
                    throw new IllegalStateException("File at file path: \""
                        + zipResourceLocator + "caused an IOException to be"
                        + " thrown");
                }
            }
            if (external) {
                throw new IllegalStateException("URL: \"" + zipResourceLocator
                    + "\" did not contain any content");
            } else {
                throw new IllegalStateException("file at file path: \""
                    + zipResourceLocator + "\" did not contain any content");
            }
        }
        // end above
        // getting the target directory path (designated in the original example
        //     as "destDir")
        if (external) {
            originZIPName =
                FileOperations.toURLCaught(zipResourceLocator).getPath();
            // TODO: check that this works
            originZIPName = originZIPName.replaceAll("/",
                "");
            try {
                originZIPName = originZIPName.split("\\.")[0];
            } catch (IndexOutOfBoundsException exception) {
                throw new IllegalStateException(".zip URL: \""
                    + zipResourceLocator + "\" refers to a path without"
                    + " \".zip\" at the end");
            }
        } else {
            originZIPName = new File(zipResourceLocator).getName();
            try {
                originZIPName = originZIPName.split("\\.")[0];
            } catch (IndexOutOfBoundsException exception) {
                throw new IllegalStateException(".zip file path: \""
                    + zipResourceLocator + "\" refers to a file without"
                    + " \".zip\" at the end");
            }
        }
        destDirPath = destDirPath.resolve(originZIPName);
        // end above
        // not included in the original
        destDirPath = FileOperations.createDirectory(destDirPath);
        // while loop, same as original
        while (zipEntry != null) {
            // the code inside the while loop has been replaced with
            //     a single method, createEntry()
            try {
                // replaces the entire while loop
                createEntry(destDirPath, zipEntry, zipStream);
            } catch (FileSystemException exception) {
                throw new IllegalStateException("Failed to create entry under"
                    + " directory path: \"" + destDirPath + "\"");
            }
            try {
                zipStream.closeEntry();
            } catch (IOException exception) {
                throw new IllegalStateException("Attempting to close the"
                    + " already-read entry caused an IOException to be thrown");
            }
            try {
                zipEntry = zipStream.getNextEntry();
            } catch (IOException exception) {
                throw new IllegalStateException("Attempting to read the next"
                    + " entry caused an IOException to be thrown");
            }
        }
        // while loop done, closing the ZipInputStream now
        try {
            zipStream.closeEntry();
            zipStream.close();
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to close the"
                + " ZipInputStream caused an IOException to be thrown");
        }

        return destDirPath;
    }
    private static void createEntry(String destDirPath, ZipEntry zipEntry,
        ZipInputStream zipStream) throws FileSystemException {
        File newFile;
        File parent;

        try {
            newFile = createFile(destDirPath, zipEntry);
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to create a new file"
                + " under destination directory path: \"" + destDirPath + "\""
                + " caused an IOException to be thrown");
        }
        if (zipEntry.isDirectory()) {
            if (! newFile.isDirectory() && ! newFile.mkdirs()) {
                throw new FileSystemException("Failed to create directory: \""
                    + newFile + "\"");
            }
        } else {
            // fix for Windows-created archives
            parent = newFile.getParentFile();
            if (! parent.isDirectory() && ! parent.mkdirs()) {
                throw new FileSystemException("Failed to create directory: \""
                    + parent + "\"");
            }

            // write file content to new file
            try {
                writeToFile(newFile, zipStream);
            } catch (IOException | IllegalArgumentException exception) {
                throw new IllegalStateException("Attempt to write to the"
                    + " newly created file was unsuccessful");
            }
        }
    }
    private static File createFile(String destDirPath, ZipEntry zipEntry)
        throws IOException {
        File destinationDirectory;
        File newFile;
        String destFilePath;

        destinationDirectory = new File(destDirPath);
        newFile = new File(destinationDirectory, zipEntry.getName());
        destFilePath = newFile.getCanonicalPath();
        destDirPath = destinationDirectory.getCanonicalPath();
        if (! destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: \""
                + zipEntry.getName() + "\"");
        }

        return newFile;
    }
    private static void writeToFile(File file, ZipInputStream zipStream)
        throws IOException {
        FileOutputStream stream;
        int length;
        byte[] buffer = new byte[1024];

        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("Destination file to write to"
                + " could not be found");
        }
        length = zipStream.read(buffer);
        while (length > 0) {
            stream.write(buffer, 0, length);
            length = zipStream.read(buffer);
        }
        stream.close();
    }
}
