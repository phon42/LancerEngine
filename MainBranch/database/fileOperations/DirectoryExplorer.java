package MainBranch.database.fileOperations;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class DirectoryExplorer {
    // Prevent user from instantiating
    private DirectoryExplorer() {}

    public static String[] getAllFilenamesInDirectory(String directoryPath) {
        // Created in part using
        //     https://www.baeldung.com/java-list-directory-files#walking
        Set<String> fileList;
        SimpleFileVisitor<Path> visitor;
        String[] directoryContents;
        int index = 0;

        fileList = new HashSet<>();
        visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file,
                BasicFileAttributes attrs) {
                if (! Files.isDirectory(file)) {
                    fileList.add(file.normalize().toString());
                }

                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(Paths.get(directoryPath), visitor);
        } catch (IOException exception) {
            throw new IllegalStateException("Attempting to traverse directory at"
                + " path: \"" + directoryPath + "\" caused an IOException to be"
                + " thrown");
        }
        directoryContents = new String[fileList.size()];
        for (String file : fileList) {
            directoryContents[index] = file;
            index++;
        }

        return directoryContents;
    }
    public static Path[] listContentsAsArray(String path,
        boolean includeDirectories) throws SecurityException {
        ArrayList<Path> arrayList;
        Path[] array;

        arrayList = listContentsAsArrayList(path, includeDirectories);
        array = new Path[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

        return array;
    }
    public static ArrayList<Path> listContentsAsArrayList(String path,
        boolean includeDirectories) throws SecurityException {
        Iterator<Path> iterator;
        Path originalPath;
        ArrayList<Path> arrayList;
        Path currPath;
        boolean isDirectory;

        iterator = getContentsIterator(path);
        originalPath = FileSystems.getDefault().getPath(path);
        originalPath = originalPath.normalize().toAbsolutePath();
        originalPath = originalPath.getFileName();
        arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            currPath = iterator.next();
            isDirectory = currPath.toFile().isDirectory();
            if (currPath.getFileName().equals(originalPath)) {
                // skip
            } else if (isDirectory && ! includeDirectories) {
                // skip
            } else {
                arrayList.add(iterator.next());
            }
        }
        arrayList.sort(null);

        return arrayList;
    }
    private static Iterator<Path> getContentsIterator(String path) throws
        SecurityException {
        Stream<Path> stream;
        Iterator<Path> iterator;

        stream = getContentsStream(path);
        iterator = stream.iterator();

        return iterator;
    }
    private static Stream<Path> getContentsStream(String path) throws
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
    public static Iterator<Path> listContentsAsIterator(String path,
        boolean includeDirectories) throws SecurityException {
        return listContentsAsArrayList(path, includeDirectories).iterator();
    }
}
