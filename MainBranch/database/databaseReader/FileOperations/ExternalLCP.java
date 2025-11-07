package MainBranch.database.databaseReader.FileOperations;

import java.net.URL;
import MainBranch.HelperMethods;

public class ExternalLCP {
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
}
