package MainBranch.database.fileOperations;

import MainBranch.HelperMethods;

public class FileNameOperator {
    public static String[] splitResourceString(String resourceString) {
        return new String[] {
            getName(resourceString), getExtension(resourceString)
        };
    }
    public static String getName(String resourceString) {
        String[] splitString;
        int nameBoundary;

        splitString = splitString(resourceString);
        nameBoundary = getExtensionBoundary(resourceString, splitString);
        splitString = HelperMethods.arraySlice(splitString, 0,
            nameBoundary);

        return String.join(".", splitString);
    }
    public static String getExtension(String resourceString) {
        String[] splitString;
        int extensionBoundary;

        splitString = splitString(resourceString);
        extensionBoundary = getExtensionBoundary(resourceString, splitString);
        splitString = HelperMethods.arraySlice(splitString, extensionBoundary);

        return String.join(".", splitString);
    }
    private static String[] splitString(String resourceString) {
        HelperMethods.checkString("resourceString",
            resourceString);

        return resourceString.split("\\.");
    }
    private static void checkResourceString(String resourceString,
        String[] splitString) throws IllegalArgumentException {
        try {
            HelperMethods.checkObjectArray("splitString",
                splitString);
            if (splitString.length < 2) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("resourceString: \""
                + resourceString + "\" did not yield both a name and an"
                + " extension");
        }
    }
    private static int getExtensionBoundary(String resourceString,
        String[] splitResourceString) {
        // reminder that .s at the beginning of the file string are used
        //     sometimes to hide the file in a file explorer
        // however, extensions like .tar.gz also exist
        int lastIndex;
        int penultimateIndex;

        checkResourceString(resourceString, splitResourceString);
        // splitResourceString must be at least of length 2
        // so neither of these can be < 0
        lastIndex = splitResourceString.length - 1;
        penultimateIndex = lastIndex - 1;
        if (splitResourceString[lastIndex].equals("gz")
            && splitResourceString[penultimateIndex].equals("tar")) {
            // the resource ends in .tar.gz
            return penultimateIndex;
        }

        return lastIndex;
    }
}
