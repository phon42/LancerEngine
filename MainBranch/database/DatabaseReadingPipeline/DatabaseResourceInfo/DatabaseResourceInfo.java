package MainBranch.database.DatabaseReadingPipeline.DatabaseResourceInfo;

import MainBranch.HelperMethods;

public class DatabaseResourceInfo implements Comparable<DatabaseResourceInfo> {
    /**
     * The name of this resource (i.e. "info", representing an info.json file).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String name;
    /**
     * The path to this resource (unable to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String path;

    public DatabaseResourceInfo(String resourceName, String path) {
        setName(resourceName);
        setPath(path);
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setPath(String path) {
        HelperMethods.checkString("path", path);
        path = path.toLowerCase();
        this.path = path;
    }

    @Override
    public int compareTo(DatabaseResourceInfo o) {
        String str1;
        String str2;
        boolean isImportant1;
        boolean isImportant2;
        boolean isSkill1;
        boolean isSkill2;

        HelperMethods.checkObject("databaseResourceInfo", o);
        str1 = this.name;
        str2 = o.name;
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
            isSkill1 = str1.equals("skills");
            isSkill2 = str2.equals("skills");
            if (isSkill1 || isSkill2) {
                return (isSkill1 ? -1 : 1) - (isSkill2 ? -1 : 1);
            }

            return str1.compareTo(str2);
        }
    }
    public static DatabaseResourceInfo[] toResourceInfo(String[] names,
        String[] paths) {
        DatabaseResourceInfo[] result;

        HelperMethods.checkStringArray("names", names);
        HelperMethods.checkStringArray("paths", paths);
        if (names.length != paths.length) {
            throw new IllegalStateException("The provided resource name and"
                + " resource path arrays must be of the same length");
        }
        result = new DatabaseResourceInfo[names.length];
        for (int i = 0; i < names.length; i++) {
            result[i] = new DatabaseResourceInfo(names[i], paths[i]);
        }

        return result;
    }
}
