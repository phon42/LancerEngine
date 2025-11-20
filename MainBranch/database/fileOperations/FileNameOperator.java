package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;

public class FileNameOperator {
    public static String getName(String resourceString) {
        String[] splitString;

        HelperMethods.checkString("resourceString",
            resourceString);
        splitString = resourceString.split("\\.");
        if (splitString.length < 2) {
            throw new IllegalArgumentException("resourceString: \""
                + resourceString + "\" did not yield both a name and an"
                + " extension");
        }

        return splitString[0];
    }
    public static String getExtension(String resourceString) {
        String[] splitString;

        HelperMethods.checkString("resourceString",
            resourceString);
        splitString = resourceString.split("\\.");
        if (splitString.length < 2) {
            throw new IllegalArgumentException("resourceString: \""
                + resourceString + "\" did not yield both a name and an"
                + " extension");
        }

        // TODO: change to work with a .tar.gz resource
        return splitString[splitString.length - 1];
    }
}
