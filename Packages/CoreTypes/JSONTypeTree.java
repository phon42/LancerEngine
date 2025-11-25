package Packages.CoreTypes;

import MainBranch.HelperMethods;
import Packages.CoreTypes.jsonTypeTree.JSONType;

/**
 * A class which aims to store the type of a single JSON value.
 */
public class JSONTypeTree {
    // Required properties
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

    public JSONTypeTree(JSONType type, String propertyName, int startIndex,
        int endIndex, JSONTypeTree[] childrenDescriptors) {
        this(type, propertyName, startIndex, endIndex);
        setChildrenDescriptors(childrenDescriptors);
    }
    public JSONTypeTree(JSONType type, String propertyName, int startIndex,
        int endIndex) {
        // Required properties
        this(type);
        // Optional properties
        setStartIndex(startIndex);
        setEndIndex(endIndex);
        setPropertyName(propertyName);
    }
    public JSONTypeTree(JSONType type) {
        // Required properties
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
            HelperMethods.checkObjectArray("childrenDescriptors",
                childrenDescriptors);
            if (childrenDescriptors.length == 0) {
                throw new IllegalArgumentException("childrenDescriptors"
                    + " array is of length 0");
            }
            for (int i = 0; i < childrenDescriptors.length; i++) {
                childrenDescriptors[i].verify(this.type);
            }
        }
        childrenDescriptors = HelperMethods.copyOf(childrenDescriptors);
        this.childrenDescriptors = childrenDescriptors;
    }

    public void verify(JSONType type) throws IllegalStateException {
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
}
