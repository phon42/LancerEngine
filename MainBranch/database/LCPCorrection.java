package MainBranch.database;

import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONObject;

public class LCPCorrection {
    /**
     * The value of the "name" property in the info.json file of the LCP this is
     *     meant for (i.e. "LANCER Core") OR the "name" property in the
     *     lcp_manifest.json file of the LCP this is meant for (i.e. "LANCER:"
     *     " Dustgrave").
     * Note that the info.json file is ONLY present in the base Lancer LCP,
     *     while the lcp_manifest.json file is NOT present in the base Lancer
     *     LCP.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * An array of the JSONObjects within the LCP this LCPCorrection replaces
     *     (i.e. a JSONObject containing the data for an Action representing the
     *     Brace reaction).
     * Can be any JSONObject[] that does not contain null elements. Cannot be
     *     null.
     */
    private JSONObject[] replacements;

    public LCPCorrection(String lcpName, JSONObject[] replacements) {
        setName(lcpName);
        setReplacements(replacements);
    }

    public String getName() {
        return name;
    }
    public JSONObject[] getReplacements() {
        return HelperMethods.copyOf(replacements);
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setReplacements(JSONObject[] replacements) {
        HelperMethods.checkObjectArray("replacements",
            replacements);
        replacements = HelperMethods.copyOf(replacements);
        this.replacements = replacements;
    }

    public boolean isLCP(String lcpInfoName, String lcpManifestName) {
        if (lcpInfoName != null) {
            return name.equals(lcpInfoName);
        }
        if (lcpManifestName != null) {
            return name.equals(lcpManifestName);
        }
        throw new IllegalStateException("Cannot attempt to match an"
            + " LCPCorrection when both the provided info.json and"
            + " lcp_manifest.json \"name\" values are null");
    }
}
