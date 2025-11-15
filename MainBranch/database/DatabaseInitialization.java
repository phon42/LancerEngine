package MainBranch.database;

import java.util.HashMap;
import MainBranch.Database;

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
        // hi
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
