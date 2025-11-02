package packages.coreTypes;

import main.HelperMethods;

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
    // TODO: fill out
    private String name;
    private Object data;

    public Rule(String name, Object data) {
        setName(name);
        setData(data);
    }
    public Rule(Rule rule) {
        this(rule.name, rule.data);
    }

    public String getName() {
        return name;
    }
    public Object getData() {
        // TODO: make a copy of data - kinda difficult because it has multiple
        //     possible types
        return data;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    public void setData(Object data) {
        HelperMethods.checkObject("data", data);
        // TODO: make a copy of data - kinda difficult because it has multiple
        //     possible types
        this.data = data;
    }
}
