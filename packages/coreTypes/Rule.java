package packages.coreTypes;

/**
 * Represents a single "rule" - a piece of reference data from the rules.json
 *     file of a .lcp file. Contains the rule's name and data.
 * 
 * Requires a rule name and data to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Rule {
    private String name;
    private Object data; 
}
