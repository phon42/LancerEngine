package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class DirectoryExplorer {
    // Prevent user from instantiating
    private DirectoryExplorer() {}

    public static Stream<Path> listContents(String path) throws
        SecurityException {
        // Created using
        //     https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#walk-java.nio.file.Path-int-java.nio.file.FileVisitOption...-
        Path directoryPath;
        Stream<Path> stream;

        directoryPath = FileSystems.getDefault().getPath(path);
        directoryPath = directoryPath.normalize().toAbsolutePath();
        try {
            stream = Files.walk(directoryPath, 1);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("maxDepth argument of"
                + " Files.walk(Path, int) is negative");
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to walk the directory"
                + " threw an IOException with the following message: \""
                + exception.getMessage() + "\"");
        }

        return stream;
    }
    public static Iterator<Path> listContentsAsIterator(String path) throws
        SecurityException {
        Stream<Path> stream;

        stream = listContents(path);
        return stream.iterator();
    }
    public static Path[] listContentsAsArray(String path) throws
        SecurityException {
        ArrayList<Path> arrayList;
        Path[] array;

        arrayList = listContentsAsArrayList(path);
        array = new Path[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

        return array;
    }
    public static ArrayList<Path> listContentsAsArrayList(String path) throws
        SecurityException {
        Iterator<Path> iterator;
        ArrayList<Path> arrayList;

        iterator = listContentsAsIterator(path);
        arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            arrayList.add(iterator.next());
        }

        return arrayList;
    }
}
