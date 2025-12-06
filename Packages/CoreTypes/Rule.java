package Packages.CoreTypes;

import MainBranch.HelperMethods;
import Packages.CoreTypes.JSONTypeTree.JSONTypeTree;

/**
 * Represents a single "rule" - a piece of reference data from the rules.json
 *     file of a .lcp file. Contains the rule's name, data, and a tree storing
 *     the data's type.
 * 
 * Requires a rule name and data to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: None of this class' properties have allowed values of null.
 */
public class Rule {
    // TODO: fill out
    // Required properties
    private String name;
    private Object data;

    // Helper property
    private JSONTypeTree type;

    public Rule(String name, Object data) {
        // Required properties
        setName(name);
        setData(data);
        // setType not called because it's called by setData
    }
    public Rule(Rule rule) {
        this(rule.name, rule.data);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public Object getData() {
        return JSONTypeTree.copy(data, type);
    }
    // Helper property
    public JSONTypeTree getType() {
        return type;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setData(Object data) {
        HelperMethods.checkObject("data", data);
        setType(JSONTypeTree.constructTree(data));
        data = JSONTypeTree.copy(data, this.type);
        this.data = data;
    }
    // Helper property
    private void setType(JSONTypeTree type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
}
