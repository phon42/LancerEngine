package MainBranch.database.databaseReader.FileReading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcessor {
    public static String readFile(String filePath) {
        String data = "";
        FileInputStream input;
        int i;

        try {
            input = new FileInputStream(filePath);
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File at path: \"" + filePath
                + "\" could not be located");
        }
        try {
            while ((i = input.read()) != -1) {
                data += (char) i;
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Error reading file");
        }
        try {
            input.close();
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to call"
                + " FileInputStream.close() on the file threw an IOException");
        }

        return data;
    }
}
