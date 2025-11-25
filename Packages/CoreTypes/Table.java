package Packages.CoreTypes;

import java.math.BigDecimal;
import MainBranch.HelperMethods;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;
import Packages.CoreTypes.jsonTypeTree.JSONType;

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
     * Can be any Object[] that does not contain null elements. Cannot be null.
     */
    private Object[] data;
    /**
     * The data type of this.data (i.e. and usually JSONType.STRING).
     * Can be any JSONType. Cannot be null.
     */
    private JSONType dataType;

    public Table(String name, Object[] data) {
        setName(name);
        setDataType(calculateDataType(data));
        setData(data);
        // setDataType call moved upwards so that setData knows what data type
        //     this Table contains
    }
    public Table(Table table) {
        setName(table.name);
        setData(table.data);
    }

    public String getName() {
        return name;
    }
    public Object[] getData() {
        return copyData(data);
    }
    public JSONType getDataType() {
        return dataType;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setData(Object[] data) {
        HelperMethods.checkObjectArray("data", data);
        data = copyData(data);
        this.data = data;
    }
    private void setDataType(JSONType dataType) {
        HelperMethods.checkObject("dataType", dataType);
        this.dataType = dataType;
    }

    private JSONType calculateDataType(Object[] data) {
        Object element;

        HelperMethods.checkObjectArray("data", data);
        if (data.length == 0) {
            return JSONType.STRING;
        }
        element = data[0];
        if (element instanceof Boolean) {
            return JSONType.BOOLEAN;
        } else if (element instanceof Number) {
            if (element instanceof Integer) {
                return JSONType.INTEGER;
            } else if (element instanceof BigDecimal) {
                return JSONType.BIGDECIMAL;
            } else {
                throw new IllegalStateException("data is an array of some type"
                    + " which extends Number, " + element.getClass() + ", which"
                    + " is neither Integer nor BigDecimal");
            }
        } else if (element instanceof String) {
            return JSONType.STRING;
        } else if (element instanceof JSONObject) {
            return JSONType.JSONOBJECT;
        } else if (element instanceof JSONArray) {
            return JSONType.JSONARRAY;
        } else {
            throw new IllegalStateException("data is of type: "
                + element.getClass() + "[] which is none of the following"
                + " allowed types: Boolean[], Integer[], BigDecimal[],"
                + " String[], JSONObject[], or JSONArray[]");
        }
    }
    private Object[] copyData(Object[] data) {
        Object[] copy;
        Object element;

        HelperMethods.checkObjectArray("data", data);
        if (data.length == 0) {
            return new Object[0];
        }
        if (this.dataType == null) {
            throw new IllegalStateException("Cannot copy this.data while"
                + " this.dataType is null");
        }
        copy = new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            element = data[i];
            if (this.dataType == JSONType.BOOLEAN) {
                element = HelperMethods.copyOf((Boolean) element);
            } else if (this.dataType == JSONType.INTEGER) {
                element = HelperMethods.copyOf((Integer) element);
            } else if (this.dataType == JSONType.BIGDECIMAL) {
                element = HelperMethods.copyOf((BigDecimal) element);
            } else if (this.dataType == JSONType.STRING) {
                // nothing needs to be done here, there is no wrapper class for
                //     String and Strings are immutable anyway
            } else if (this.dataType == JSONType.JSONOBJECT) {
                element = HelperMethods.copyOf((JSONObject) element);
            } else {
                // this.dataType is JSONType.JSONARRAY
                element = HelperMethods.copyOf((JSONArray) element);
            }
            data[i] = element;
        }

        return copy;
    }
    public boolean[] getBooleanData() {
        boolean[] booleanData;

        if (this.dataType != JSONType.BOOLEAN) {
            throw new IllegalStateException("Cannot create a boolean[] from"
                + " a this.data property whose elements are of type other than"
                + " Boolean");
        }
        booleanData = new boolean[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            booleanData[i] = (Boolean) this.data[i];
        }

        return booleanData;
    }
    public int[] getIntData() {
        int[] intData;

        if (this.dataType != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create an int[] from a"
                + " this.data property whose elements are of type other than"
                + " Integer");
        }
        intData = new int[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            intData[i] = (Integer) this.data[i];
        }

        return intData;
    }
    public BigDecimal[] getBigDecimalData() {
        BigDecimal[] bigDecimalData;

        if (this.dataType != JSONType.BIGDECIMAL) {
            throw new IllegalStateException("Cannot create a BigDecimal[] from"
                + " a this.data property whose elements are of type other than"
                + " BigDecimal");
        }
        bigDecimalData = new BigDecimal[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            bigDecimalData[i] = HelperMethods.copyOf((BigDecimal) this.data[i]);
        }

        return bigDecimalData;
    }
    public String[] getStringData() {
        String[] stringData;

        if (this.dataType != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a String[] from a"
                + " this.data property whose elements are of type other than"
                + " String");
        }
        stringData = new String[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            stringData[i] = (String) this.data[i];
        }

        return stringData;
    }
    public JSONObject[] getJSONObjectData() {
        JSONObject[] jsonObjectData;

        if (this.dataType != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a JSONObject[] from"
                + " a this.data property whose elements are of type other than"
                + " JSONObject");
        }
        jsonObjectData = new JSONObject[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            jsonObjectData[i] = HelperMethods.copyOf((JSONObject) this.data[i]);
        }

        return jsonObjectData;
    }
    public JSONArray[] getJSONArrayData() {
        JSONArray[] jsonArrayData;

        if (this.dataType != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a JSONArray[] from a"
                + " this.data property whose elements are of type other than"
                + " JSONArray");
        }
        jsonArrayData = new JSONArray[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            jsonArrayData[i] = HelperMethods.copyOf((JSONArray) this.data[i]);
        }

        return jsonArrayData;
    }
}
