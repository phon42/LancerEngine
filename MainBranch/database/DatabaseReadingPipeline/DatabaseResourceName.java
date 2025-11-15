package MainBranch.database.DatabaseReadingPipeline;

import MainBranch.HelperMethods;

public class DatabaseResourceName implements Comparable<DatabaseResourceName> {
    /**
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String value;

    public DatabaseResourceName(String resourcePath) {
        setValue(resourcePath);
    }

    public String getValue() {
        return value;
    }
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }

    @Override
    public int compareTo(DatabaseResourceName o) {
        String str1;
        String str2;
        boolean isImportant1;
        boolean isImportant2;

        HelperMethods.checkObject("databaseResourceName", o);
        str1 = this.value;
        str2 = o.value;
        isImportant1 = str1.equals("info")
            || str1.equals("lcp_manifest");
        isImportant2 = str2.equals("info")
            || str2.equals("lcp_manifest");
        if (isImportant1 || isImportant2) {
            if (isImportant1 && isImportant2) {
                return str1.compareTo(str2);
            } else {
                return isImportant1 ? -1 : 1;
            }
        } else {
            return str1.compareTo(str2);
        }
    }
    public static DatabaseResourceName[] toResourceNames(String[] input) {
        DatabaseResourceName[] result;

        HelperMethods.checkStringArray("input", input);
        result = new DatabaseResourceName[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = new DatabaseResourceName(input[i]);
        }

        return result;
    }
}
