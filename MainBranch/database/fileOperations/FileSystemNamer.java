package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;

public class FileSystemNamer {
    // Prevent user from instantiating
    private FileSystemNamer() {}

    public static String toValidDirectoryName(String name) {
        // Created in part using
        //     https://superuser.com/questions/358855/what-characters-are-safe-in-cross-platform-file-names-for-linux-windows-and-os
        // Created in part using https://en.wikipedia.org/wiki/Control_character
        StringBuilder stringBuilder;
        int[] codePoints;
        boolean isControlCharacter;
        int[] controlCharacters = new int[] {
            0x2028, 0x2029, 0xFFF9, 0xFFFA, 0xFFFB, 0x061C, 0x200E, 0x200F,
            0x202A, 0x202B, 0x202C, 0x202D, 0x202E, 0x2066, 0x2067, 0x2068,
            0x2069
        };

        HelperMethods.checkString("name", name);
        // Invalid on all file systems
        name = name.replaceAll("/", "");
        // Invalid on Windows
        name = name.replaceAll("\\\\", "");
        name = name.replaceAll(":", "");
        name = name.replaceAll("\\*", "");
        name = name.replaceAll("\\?", "");
        name = name.replaceAll("\"", "");
        name = name.replaceAll("<", "");
        name = name.replaceAll(">", "");
        name = name.replaceAll("\\|", "");
        // remove unicode control characters as per the wikipedia article
        stringBuilder = new StringBuilder();
        codePoints = name.codePoints().toArray();
        for (int codePoint : codePoints) {
            isControlCharacter = false;
            // skip code points that are control characters
            for (int controlCharacter : controlCharacters) {
                if (codePoint == controlCharacter) {
                    isControlCharacter = true;
                }
            }
            if (isControlCharacter) {
                continue;
            }
            // filtering out specific blocks
            if (0 < codePoint && codePoint < 0xFF) {
                continue;
            }
            if (0xE0000 < codePoint && codePoint < 0xEE07F) {
                continue;
            }
            stringBuilder.appendCodePoint(codePoint);
        }
        name = stringBuilder.toString();
        // done filtering control characters!
        if (name.equals("")) {
            throw new IllegalArgumentException("Unable to convert \"\" to a"
                + " valid directory name");
        }
        // Linux & MacOS X invalid directory name
        if (name.equals("null")) {
            throw new IllegalArgumentException("Unable to convert null to a"
                + " valid directory name");
        }
        // Windows and sometimes Mac OS are case-insensitive
        name = name.toLowerCase();

        return name;
    }
}
