public class HelperFunctions {
    // TODO: fill out with a helper function that outputs a properly formatted
    //     String from an array
    // there are bits of code that perform this function all over this project,
    //     i.e. Mech.outputSystems()
    public static int[] append(int[] intArray, int newElement) {
        int[] newIntArray = new int[intArray.length + 1];

        for (int i = 0; i < newIntArray.length; i++) {
            if (i < intArray.length) {
                newIntArray[i] = intArray[i];
                continue;
            }
            newIntArray[i] = newElement;
        }
        
        return newIntArray;
    }
}
