package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;

public class FileSystemNamer {
    // Prevent user from instantiating
    private FileSystemNamer() {}

    public static String toValidDirectoryName(String name) {
        // Created in part using
        //     https://superuser.com/questions/358855/what-characters-are-safe-in-cross-platform-file-names-for-linux-windows-and-os
        HelperMethods.checkString("name", name);

        return name;
    }
}
