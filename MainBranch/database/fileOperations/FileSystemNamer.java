package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;

public class FileSystemNamer {
    // Prevent user from instantiating
    private FileSystemNamer() {}

    public static String toValidDirectoryName(String name) {
        // Created in part using
        //     https://superuser.com/questions/358855/what-characters-are-safe-in-cross-platform-file-names-for-linux-windows-and-os
        HelperMethods.checkString("name", name);
        // Invalid on all file systems
        name = name.replaceAll("/", "");
        // Invalid on Windows
        name = name.replaceAll("\\", "");
        name = name.replaceAll(":", "");
        name = name.replaceAll("*", "");
        name = name.replaceAll("?", "");
        name = name.replaceAll("\"", "");
        name = name.replaceAll("<", "");
        name = name.replaceAll(">", "");
        name = name.replaceAll("|", "");
        // TODO: remove unicode control characters
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
