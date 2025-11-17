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
        int length = 0;
        boolean isControlCharacter;
        int[] controlCharacters = new int[] {
            0x2028, 0x2029, 0xFFF9, 0xFFFA, 0xFFFB, 0x061C, 0x200E, 0x200F,
            0x202A, 0x202B, 0x202C, 0x202D, 0x202E, 0x2066, 0x2067, 0x2068,
            0x2069
        };
        String[] invalidNames = new String[] {
            "CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5",
            "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4",
            "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
        };

        HelperMethods.checkString("name", name);
        // Invalid on Windows, Mac, Linux
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
        // Invalid on UNIX file systems
        // remove unicode control characters as per the wikipedia article
        stringBuilder = new StringBuilder();
        codePoints = name.codePoints().toArray();
        for (int codePoint : codePoints) {
            if (length >= 255) {
                break;
            }
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
            length++;
        }
        name = stringBuilder.toString();
        // done filtering control characters!
        if (name.equals("")) {
            throw new IllegalArgumentException("Unable to convert \"\" to a"
                + " valid directory name");
        }
        // Invalid on UNIX file systems
        if (name.equals(".")) {
            throw new IllegalArgumentException("Unable to convert \".\" to a"
                + " valid directory name");
        }
        if (name.equals("..")) {
            throw new IllegalArgumentException("Unable to convert \"..\" to a"
                + " valid directory name");
        }
        // Windows and sometimes Mac OS are case-insensitive
        name = name.toLowerCase();
        // Windows reserved names
        for (String invalidName : invalidNames) {
            name = name.replaceAll(invalidName.toLowerCase(), "");
        }

        return name;
    }
}
