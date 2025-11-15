package MainBranch.database;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import MainBranch.Database;

/**
 * https://stackoverflow.com/a/18277622 was used in the creation of this file.
 * The following command was used on this file:
 *     git update-index --assume-unchanged path/to/file.txt
 * It will no longer recognize any changes made to it.
 * To undo this change, use:
 *     git update-index --no-assume-unchanged path/to/file.txt
 */
public class DatabaseInitialization {
    public static final HashMap<String, ExternalLCP> initializationLCPs =
        new HashMap<>();

    static {
        ExternalLCP baseLancer;
        ExternalLCP dustgrave;
        ExternalLCP ktb;
        ExternalLCP longRim;
        ExternalLCP osr;
        ExternalLCP ows;
        ExternalLCP sotw;
        ExternalLCP ssmr;
        ExternalLCP wallflower;

        // Define all external LCPs
        Database.open();
        Database.close();

        DatabaseInitialization.initializationLCPs.put("baseLancer",
            baseLancer);
        DatabaseInitialization.initializationLCPs.put("dustgrave",
            dustgrave);
        DatabaseInitialization.initializationLCPs.put("ktb", ktb);
        DatabaseInitialization.initializationLCPs.put("longRim", longRim);
        DatabaseInitialization.initializationLCPs.put("osr", osr);
        DatabaseInitialization.initializationLCPs.put("ows", ows);
        DatabaseInitialization.initializationLCPs.put("sotw", sotw);
        DatabaseInitialization.initializationLCPs.put("ssmr", ssmr);
        DatabaseInitialization.initializationLCPs.put("wallflower",
            wallflower);
    }
    // Prevent user from instantiating
    private DatabaseInitialization() {}
}
