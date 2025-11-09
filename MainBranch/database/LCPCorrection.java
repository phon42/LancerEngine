package MainBranch.database;

import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONObject;

public class LCPCorrection {
    /**
     * The value of the "name" property in the info.json file of the LCP this is
     *     meant for (is always "LANCER Core") OR the "name" property in the
     *     lcp_manifest.json file of the LCP this is meant for (i.e. "LANCER:"
     *     " Dustgrave").
     * Note that the info.json file is ONLY present in the base Lancer LCP,
     *     while the lcp_manifest.json file is NOT present in the base Lancer
     *     LCP.
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String lcpName;
    /**
     * The name of the file this LCPCorrection is meant to correct (i.e.
     *     "actions").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String fileName;
    /**
     * An array of the JSONObjects within the LCP this LCPCorrection replaces
     *     (i.e. a JSONObject containing the data for an Action representing the
     *     Brace reaction).
     * Can be any JSONObject[] that does not contain null elements. Cannot be
     *     null.
     */
    private JSONObject[] replacements;

    public LCPCorrection(String lcpName, String fileName,
        JSONObject[] replacements) {
        setLCPName(lcpName);
        setFileName(fileName);
        setReplacements(replacements);
    }
    public LCPCorrection(LCPCorrection lcpCorrection) {
        setLCPName(lcpCorrection.lcpName);
        setFileName(lcpCorrection.fileName);
        setReplacements(lcpCorrection.replacements);
    }

    public String getLCPName() {
        return lcpName;
    }
    public String getFileName() {
        return fileName;
    }
    public JSONObject[] getReplacements() {
        return HelperMethods.copyOf(replacements);
    }
    private void setLCPName(String lcpName) {
        HelperMethods.checkString("lcpName", lcpName);
        this.lcpName = lcpName;
    }
    private void setFileName(String fileName) {
        HelperMethods.checkString("fileName", fileName);
        fileName = fileName.toLowerCase();
        this.fileName = fileName;
    }
    private void setReplacements(JSONObject[] replacements) {
        HelperMethods.checkObjectArray("replacements",
            replacements);
        replacements = HelperMethods.copyOf(replacements);
        this.replacements = replacements;
    }

    public boolean isTarget(String lcpInfoName, String lcpManifestName,
        String fileName) {
        if (lcpInfoName != null) {
            if (! this.lcpName.equals(lcpInfoName)) {
                return false;
            }
        } else if (lcpManifestName != null) {
            if (! this.lcpName.equals(lcpManifestName)) {
                return false;
            }
        } else {
            throw new IllegalStateException("Cannot attempt to match an"
                + " LCPCorrection when both the provided info.json and"
                + " lcp_manifest.json \"name\" values are null");
        }

        return this.fileName.equals(fileName);
    }
}
