package MainBranch.database.databaseReader.fileReading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZIPUnzipper {
    private static String unzip(String filePath, String destDirPath)
        throws FileNotFoundException, IOException {
        ZipInputStream zipStream;
        ZipEntry zipEntry;
        String directoryPath = null;

        zipStream = new ZipInputStream(new FileInputStream(filePath));
        zipEntry = zipStream.getNextEntry();
        while (zipEntry != null) {
            createEntry(destDirPath, zipStream, zipEntry);
            zipEntry = zipStream.getNextEntry();
        }
        zipStream.closeEntry();
        zipStream.close();

        return directoryPath;
    }
    public static String unzip(String filePath) throws FileNotFoundException,
        IOException {
        return unzip(filePath, "MainBranch/Target/");
    }
    private static void createEntry(String destDirPath, ZipInputStream zis,
        ZipEntry zipEntry) throws IOException {
        File destDir = new File(destDirPath);
        byte[] buffer = new byte[1024];
        File newFile;
        File parent;
        FileOutputStream fos;
        int len;

        newFile = createFile(destDir, zipEntry);
        if (zipEntry.isDirectory()) {
            if (! newFile.isDirectory() && ! newFile.mkdirs()) {
                throw new IOException("Failed to create directory: \""
                    + newFile + "\"");
            }
        } else {
            // fix for Windows-created archives
            parent = newFile.getParentFile();
            if (! parent.isDirectory() && ! parent.mkdirs()) {
                throw new IOException("Failed to create directory: \""
                    + parent + "\"");
            }

            // write file content
            fos = new FileOutputStream(newFile);
            len = zis.read(buffer);
            while (len > 0) {
                fos.write(buffer, 0, len);
                len = zis.read(buffer);
            }
            fos.close();
        }
    }
    private static File createFile(File destinationDir, ZipEntry zipEntry)
        throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (! destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: \""
                + zipEntry.getName() + "\"");
        }

        return destFile;
    }
}
