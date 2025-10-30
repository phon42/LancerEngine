package main.database.databaseReader.FileReading;

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
    // TODO: replace
    public static String readFile2(String filePath) {
        String data = "[\n  {\n    " + //
            "\"id\": \"act_move\",\n" + //
            "    \"name\": \"Move\",\n" + //
            "    \"activation\": \"Move\",\n" + //
            "    \"terse\": \"Move your character up to its Speed in any direction.\",\n" + //
            "    \"detail\": \"On their turn, characters can always move spaces equal to their SPEED, in addition to any other actions. This is called a standard move to distinguish it from movement granted by systems or talents.<br>A character only counts as moving if they move 1 or more spaces.<br>Characters can move into any adjacent space, even diagonally, as long as the space isn’t occupied by an obstruction (and is one that they would be able to move in – characters can't move straight up unless they can fly, for example). There are several factors that can affect movement, which are detailed here.\",\n" + //
            "    \"pilot\": true,\n" + //
            "    \"mech\": true,\n" + //
            "    \"hide_active\": true\n" + //
            "  },]";
        return data;
    }
}
