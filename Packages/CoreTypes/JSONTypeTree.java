package Packages.CoreTypes;

import java.util.Set;
import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.jsonTypeTree.JSONType;

/**
 * A class which aims to store the type of a single JSON value.
 */
public class JSONTypeTree {
    // Required property
    /**
     * The type of the current level of the JSON value (i.e. JSONType.BOOLEAN).
     * Can be any JSONType. Cannot be null.
     */
    private JSONType type;

    // Optional properties
    /**
     * The property name attached to the parent JSON object that refers to this
     *     value (example unhelpful).
     * If the parent's JSONTypeTree.type value is JSONType.JSONOBJECT:
     *     Can be any String. Cannot be null.
     * Otherwise:
     *     Must be null.
     * Can be any String. Can be null.
     * Case-sensitive.
     */
    private String propertyName;
    /**
     * The start index comprising the index range over which this.type is
     *     correct for the parent JSON array (example unhelpful).
     * If the parent's JSONTypeTree.type value is JSONType.JSONARRAY:
     *     Must be a minimum of 0.
     * Otherwise:
     *     Must be -1.
     * Must be a minimum of -1.
     */
    private int startIndex;
    /**
     * The end index comprising the index range over which this.type is correct
     *     for the parent JSON array (example unhelpful).
     * If the parent's JSONTypeTree.type value is JSONType.JSONARRAY:
     *     Must be a minimum of 0.
     * Otherwise:
     *     Must be -1.
     * Must be a minimum of -1.
     */
    private int endIndex;
    /**
     * TODO: add documentation
     */
    private JSONTypeTree[] childrenDescriptors;

    public JSONTypeTree(JSONType type, int startIndex, int endIndex,
        JSONTypeTree[] childrenDescriptors) {
        this(type, startIndex, endIndex);
        setChildrenDescriptors(childrenDescriptors);
    }
    public JSONTypeTree(JSONType type, String propertyName,
        JSONTypeTree[] childrenDescriptors) {
        this(type, propertyName);
        setChildrenDescriptors(childrenDescriptors);
    }
    public JSONTypeTree(JSONType type, int startIndex, int endIndex) {
        // Required property
        this(type);
        // Optional properties
        setStartIndex(startIndex);
        setEndIndex(endIndex);
    }
    public JSONTypeTree(JSONType type, String propertyName) {
        // Required property
        this(type);
        // Optional property
        setPropertyName(propertyName);
    }
    public JSONTypeTree(JSONType type, JSONTypeTree[] childrenDescriptors) {
        // Required property
        this(type);
        setChildrenDescriptors(childrenDescriptors);
    }
    public JSONTypeTree(JSONType type) {
        // Required property
        setType(type);
    }

    // Required properties
    public JSONType getType() {
        return type;
    }
    // Optional properties
    public String getPropertyName() {
        return propertyName;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public int getEndIndex() {
        return endIndex;
    }
    public JSONTypeTree[] getChildrenDescriptors() {
        if (childrenDescriptors == null) {
            return childrenDescriptors;
        }

        return HelperMethods.copyOf(childrenDescriptors);
    }
    // Required properties
    private void setType(JSONType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    // Optional properties
    private void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    private void setStartIndex(int startIndex) {
        if (startIndex < -1) {
            throw new IllegalStateException("startIndex is: " + startIndex
                + " is < -1");
        }
        this.startIndex = startIndex;
    }
    private void setEndIndex(int endIndex) {
        if (endIndex < -1) {
            throw new IllegalStateException("endIndex is: " + startIndex + " is"
                + " < -1");
        }
        this.endIndex = endIndex;
    }
    private void setChildrenDescriptors(JSONTypeTree[] childrenDescriptors) {
        if (childrenDescriptors != null) {
            HelperMethods.checkObjectArrayAlt(
                "childrenDescriptors", childrenDescriptors);
            for (int i = 0; i < childrenDescriptors.length; i++) {
                childrenDescriptors[i].verify(this.type);
            }
            childrenDescriptors = HelperMethods.copyOf(childrenDescriptors);
        }
        this.childrenDescriptors = childrenDescriptors;
    }

    public JSONTypeTree addPropertyName(String propertyName) {
        if (this.startIndex != -1 || this.endIndex != -1
            || this.propertyName != null) {
            throw new IllegalStateException("Unable to add a property name"
                + " to a JSONTypeTree which has a start and end index or"
                + " already has a property name");
        }
        if (this.childrenDescriptors == null) {
            return new JSONTypeTree(this.type, propertyName);
        } else {
            return new JSONTypeTree(this.type, propertyName,
                this.childrenDescriptors);
        }
    }
    public JSONTypeTree addIndices(int startIndex, int endIndex) {
        if (this.propertyName != null || this.startIndex != -1
            || this.endIndex != -1) {
            throw new IllegalStateException("Unable to add a property name"
                + " to a JSONTypeTree which has a property name or already has"
                + " a start and end index");
        }
        if (this.childrenDescriptors == null) {
            return new JSONTypeTree(this.type, startIndex, endIndex);
        } else {
            return new JSONTypeTree(this.type, startIndex, endIndex,
                this.childrenDescriptors);
        }
    }
    public void verifyAll() throws IllegalStateException {
        if (this.childrenDescriptors != null) {
            for (int i = 0; i < this.childrenDescriptors.length; i++) {
                this.childrenDescriptors[i].verify(this.type);
                this.childrenDescriptors[i].verifyAll();
            }
        }
    }
    private void verify(JSONType type) throws IllegalStateException {
        HelperMethods.checkObject("type", type);
        if (type == JSONType.JSONOBJECT) {
            if (this.propertyName == null) {
                throw new IllegalStateException("this.propertyName is null");
            }
            arrayErrors();
        } else if (type == JSONType.JSONARRAY) {
            objectErrors();
            if (this.startIndex == -1) {
                throw new IllegalStateException("this.startIndex is -1");
            }
            if (this.endIndex == -1) {
                throw new IllegalStateException("this.endIndex is -1");
            }
        } else {
            objectErrors();
            arrayErrors();
            if (this.childrenDescriptors != null) {
                throw new IllegalStateException("this.childrenDescriptors is"
                    + " not null");
            }
        }
    }
    private void objectErrors() throws IllegalStateException {
        if (propertyName != null) {
            throw new IllegalStateException("this.propertyName is: \""
                + propertyName + "\" which is not null");
        }
    }
    private void arrayErrors() throws IllegalStateException {
        if (startIndex != -1) {
            throw new IllegalStateException("this.startIndex is: " + startIndex
                + " which is not -1");
        }
        if (endIndex != -1) {
            throw new IllegalStateException("this.endIndex is: " + endIndex
                + " which is not -1");
        }
    }
    public static JSONTypeTree constructTree(Object value) {
        JSONType type;
        JSONTypeTree tree;

        type = JSONType.determineType(value);
        if (type != JSONType.JSONOBJECT && type != JSONType.JSONARRAY) {
            tree = new JSONTypeTree(type);

            return tree;
        }
        // here's the complicated part
        // for objects:
        if (type == JSONType.JSONOBJECT) {
            return constructObjectTree((JSONObject) value);
        }

        // for arrays:
        return constructArrayTree((JSONArray) value);
    }
    private static JSONTypeTree constructObjectTree(JSONObject value) {
        Set<String> keys;
        JSONTypeTree[] properties;
        int index = 0;

        keys = value.keySet();
        if (keys.size() == 0) {
            return new JSONTypeTree(JSONType.JSONOBJECT);
        }
        properties = new JSONTypeTree[keys.size()];
        for (String key : keys) {
            properties[index] = constructTree(value.get(key));
            properties[index] = properties[index].addPropertyName(key);
            index++;
        }

        return new JSONTypeTree(JSONType.JSONOBJECT, properties);
    }
    private static JSONTypeTree constructArrayTree(JSONArray value) {
        int length;
        JSONTypeTree[] elements;

        length = value.length();
        if (length == 0) {
            return new JSONTypeTree(JSONType.JSONARRAY);
        }
        elements = new JSONTypeTree[length];
        for (int i = 0; i < length; i++) {
            elements[i] = constructTree(value.get(i));
            elements[i] = elements[i].addIndices(i, i + 1);
        }

        return new JSONTypeTree(JSONType.JSONARRAY, elements);
    }
    /**
     * Creates a copy of original using type as a guide on how to copy it.
     */
    public static Object copy(Object original, JSONTypeTree type) {
        // TODO: fill out
        return original;
    }
}
