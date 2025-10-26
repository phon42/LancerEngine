package packages.coreTypes;

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
    private String name;
    private Object[] data;
}
