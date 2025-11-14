package MainBranch.database;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import MainBranch.HelperMethods;

public class ExternalLCP {
    /**
     * An array of (specifically .json) files that are all part of a single LCP,
     *     all of which are hosted externally.
     */
    private URL[] files;

    public ExternalLCP(URL[] files) {
        HelperMethods.verifyConstructor();
        setFiles(files);
    }

    public URL[] getFiles() {
        return HelperMethods.copyOf(files);
    }
    private void setFiles(URL[] files) {
        HelperMethods.checkObjectArray("files", files);
        files = HelperMethods.copyOf(files);
        this.files = files;
    }

    public String[] getFilesAsStrings() {
        String[] fileStrings;

        fileStrings = new String[this.files.length];
        for (int i = 0; i < this.files.length; i++) {
            fileStrings[i] = this.files[i].toString();
        }

        return fileStrings;
    }
    public Iterator<URL> getFilesAsIterator() {
        List<URL> list;

        list = Arrays.asList(getFiles());

        return list.iterator();
    }
    public Iterator<String> getFilesAsStringIterator() {
        List<String> list;

        list = Arrays.asList(getFilesAsStrings());

        return list.iterator();
    }
}
