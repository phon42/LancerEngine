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
     * Can be any URL[] that is not of length 0 and does not contain null
     *     elements. Cannot be null.
     */
    private URL[] files;
    /**
     * The index in this.files at which the info.json or lcp_manifest.json file
     *     for this LCP is stored.
     * Must be between -1 and this.files.length - 1 (inclusive).
     */
    private int lcpInfoIndex;

    public ExternalLCP(URL[] files, int indexOfLCPInfoFile) {
        HelperMethods.verifyConstructor();
        setFiles(files);
        setLCPInfoIndex(indexOfLCPInfoFile);
    }

    public URL[] getFiles() {
        return HelperMethods.copyOf(files);
    }
    public int getLCPInfoIndex() {
        return lcpInfoIndex;
    }
    private void setFiles(URL[] files) {
        HelperMethods.checkObjectArray("files", files);
        if (files.length == 0) {
            throw new IllegalArgumentException("files array length is 0"
                + " which is < 1");
        }
        files = HelperMethods.copyOf(files);
        this.files = files;
    }
    private void setLCPInfoIndex(int lcpInfoIndex) {
        if (lcpInfoIndex < -1) {
            throw new IllegalArgumentException("lcpInfoIndex value: "
                + lcpInfoIndex + " is < -1");
        }
        if (lcpInfoIndex > this.files.length - 1) {
            throw new IllegalArgumentException("lcpInfoIndex value: "
                + lcpInfoIndex + " is > (this.files.length - 1) which is: "
                + (this.files.length - 1));
        }
        this.lcpInfoIndex = lcpInfoIndex;
    }

    public boolean hasLCPInfoFile() {
        return lcpInfoIndex != -1;
    }
    public URL getLCPInfoURL() {
        if (lcpInfoIndex == -1) {
            return null;
        }

        return files[lcpInfoIndex];
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
