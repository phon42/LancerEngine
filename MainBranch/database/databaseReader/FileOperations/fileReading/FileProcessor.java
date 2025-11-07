package MainBranch.database.databaseReader.FileOperations.fileReading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.MissingResourceException;

public class FileProcessor {
    public static String readFile(String filePath) {
        String data = "";
        FileInputStream input;
        InputStreamReader reader;
        int character;

        try {
            input = new FileInputStream(filePath);
        } catch (FileNotFoundException exception) {
            throw new MissingResourceException("File at path: \"" + filePath
                + "\" could not be located", "File", filePath);
        }
        reader = new InputStreamReader(input);
        try {
            character = reader.read();
            while (character != -1) {
                data += (char) character;
                character = reader.read();
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Error reading file");
        } finally {
            try {
                reader.close();
            } catch (IOException exception) {
                throw new IllegalStateException("Attempting to call"
                    + " InputStreamReader.close() on the file threw an"
                    + " IOException");
            }
        }

        return data;
    }
}
