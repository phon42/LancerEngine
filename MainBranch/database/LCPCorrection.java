package MainBranch.database;

import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONException;
import MainBranch.database.fileOperations.json.JSONObject;

public class LCPCorrection {
    /**
     * The value of the "name" property in EITHER the info.json file (is always
     *     "LANCER Core") OR the lcp_manifest.json file (i.e. "LANCER:"
     *     " Dustgrave") of the LCP this is meant for.
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
     * (TODO: update documentation)
     * The property of the target JSONObject which can be used to recognize
     *     whether this LCPCorrection should replace a given JSONObject (i.e.
     *     "name").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String jsonProperty;
    /**
     * TODO: add documentation
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String jsonValue;
    /**
     * The JSONObject within the LCP this LCPCorrection replaces (i.e. a
     *     JSONObject containing the data for an Action representing the
     *     Brace reaction).
     * Can be any JSONObject. Cannot be null.
     */
    private JSONObject replacement;

    public LCPCorrection(String lcpName, String fileName,
        String targetFileTargetProperty, String targetFileTargetValue,
        JSONObject replacement) {
        setLCPName(lcpName);
        setFileName(fileName);
        setJSONProperty(targetFileTargetProperty);
        setJSONValue(targetFileTargetValue);
        setReplacement(replacement);
    }
    public LCPCorrection(LCPCorrection lcpCorrection) {
        setLCPName(lcpCorrection.lcpName);
        setFileName(lcpCorrection.fileName);
        setJSONProperty(lcpCorrection.jsonProperty);
        setJSONValue(lcpCorrection.jsonValue);
        setReplacement(lcpCorrection.replacement);
    }

    public String getLCPName() {
        return lcpName;
    }
    public String getFileName() {
        return fileName;
    }
    public String getJSONProperty() {
        return jsonProperty;
    }
    public String getJSONValue() {
        return jsonValue;
    }
    public JSONObject getReplacement() {
        return new JSONObject(this.replacement.toString());
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
    private void setJSONProperty(String jsonProperty) {
        HelperMethods.checkString("jsonProperty", jsonProperty);
        this.jsonProperty = jsonProperty;
    }
    public void setJSONValue(String jsonValue) {
        HelperMethods.checkObject("jsonValue", jsonValue);
        this.jsonValue = jsonValue;
    }
    private void setReplacement(JSONObject replacement) {
        HelperMethods.checkObject("replacement", replacement);
        replacement = new JSONObject(replacement.toString());
        this.replacement = replacement;
    }

    public boolean isTargetFile(String lcpInfoName, String lcpManifestName,
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
    public boolean isTargetObject(JSONObject object) {
        String value;

        try {
            value = object.getString(this.jsonProperty);
            return value.equals(this.jsonValue);
        } catch (JSONException exception) {
            return false;
        }
    }
}
