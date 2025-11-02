package PackageSystemQ.CoreTypes;

import MainBranch.HelperMethods;

/**
 * Represents a single reference data table from an LCP. Contains information
 *     about the table's name and the table's data.
 * 
 * Requires a table name and data to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Table {
    /**
     * The table's name (i.e. "quirks").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String name;
    /**
     * The table's data (too large to provide an example of).
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     * Elements are case-sensitive.
     */
    private String[] data;

    public Table(String name, String[] data) {
        setName(name);
        setData(data);
    }
    public Table(Table table) {
        setName(table.name);
        setData(table.data);
    }

    public String getName() {
        return name;
    }
    public String[] getData() {
        return HelperMethods.copyOf(data);
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setData(String[] data) {
        HelperMethods.checkObject("data", data);
        for (String value : data) {
            if (value == null) {
                throw new IllegalArgumentException("data array contains a"
                    + " null element");
            }
            if (value.equals("")) {
                throw new IllegalArgumentException("data array contains a"
                    + " element that is \"\"");
            }
        }
        data = HelperMethods.copyOf(data);
        this.data = data;
    }
}
