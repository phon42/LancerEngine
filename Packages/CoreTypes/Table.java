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
     * Can be any Object. Cannot be null.
     */
    private Object data;
    /**
     * A JSONTypeTree storing the data type of this.data (usually a JSONTypeTree
     *     representing JSONType.STRING).
     * Can be any JSONTypeTree. Cannot be null.
     */
    private JSONTypeTree type;

    public Table(String name, Object data) {
        setName(name);
        setType(JSONTypeTree.constructTree(data));
        setData(data);
        // setType call moved upwards so that setData knows what data type this
        //     Table contains
    }
    public Table(Table table) {
        setName(table.name);
        setData(table.data);
    }

    public String getName() {
        return name;
    }
    public Object getData() {
        return JSONTypeTree.copy(data, type);
    }
    public JSONTypeTree getType() {
        return type;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    private void setData(Object data) {
        HelperMethods.checkObject("data", data);
        if (type.getType() != JSONType.JSONARRAY) {
            throw new IllegalArgumentException("data must be a JSONArray or"
                + " Object[]");
        }
        data = JSONTypeTree.copy(data, type);
        this.data = data;
    }
    private void setType(JSONTypeTree type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }

    public JSONType getDataElementType() {
        JSONTypeTree[] children;

        if (this.type.getType() == JSONType.JSONARRAY) {
            children = this.type.getChildrenDescriptors();

            return children[0].getType();
        }
        throw new IllegalStateException("Table.data must be a JSONArray or"
            + " Object[]");
    }
    public boolean[] getBooleanData() {
        Object[] dataArray;
        boolean[] booleanData;
        Boolean booleanElement;

        if (getDataElementType() != JSONType.BOOLEAN) {
            throw new IllegalStateException("Cannot create a boolean[] from"
                + " a this.data property whose elements are of type other than"
                + " Boolean");
        }
        dataArray = (Object[]) this.data;
        booleanData = new boolean[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            booleanElement = (Boolean) dataArray[i];
            booleanData[i] = booleanElement.booleanValue();
        }

        return booleanData;
    }
    public int[] getIntData() {
        Object[] dataArray;
        int[] intData;
        Integer intElement;

        if (getDataElementType() != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create an int[] from a"
                + " this.data property whose elements are of type other than"
                + " Integer");
        }
        dataArray = (Object[]) this.data;
        intData = new int[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            intElement = (Integer) dataArray[i];
            intData[i] = intElement.intValue();
        }

        return intData;
    }
    public BigDecimal[] getBigDecimalData() {
        Object[] dataArray;
        BigDecimal[] bigDecimalData;
        BigDecimal bigDecimalElement;

        if (getDataElementType() != JSONType.BIGDECIMAL) {
            throw new IllegalStateException("Cannot create a BigDecimal[] from"
                + " a this.data property whose elements are of type other than"
                + " BigDecimal");
        }
        dataArray = (Object[]) this.data;
        bigDecimalData = new BigDecimal[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            bigDecimalElement = (BigDecimal) dataArray[i];
            bigDecimalElement = HelperMethods.copyOf(bigDecimalElement);
            bigDecimalData[i] = bigDecimalElement;
        }

        return bigDecimalData;
    }
    public String[] getStringData() {
        Object[] dataArray;
        String[] stringData;
        String stringElement;

        if (getDataElementType() != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a String[] from a"
                + " this.data property whose elements are of type other than"
                + " String");
        }
        dataArray = (Object[]) this.data;
        stringData = new String[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            stringElement = (String) dataArray[i];
            stringData[i] = stringElement;
        }

        return stringData;
    }
    public JSONObject[] getJSONObjectData() {
        Object[] dataArray;
        JSONObject[] jsonObjectData;
        JSONObject jsonObjectElement;

        if (getDataElementType() != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a JSONObject[] from"
                + " a this.data property whose elements are of type other than"
                + " JSONObject");
        }
        dataArray = (Object[]) this.data;
        jsonObjectData = new JSONObject[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            jsonObjectElement = (JSONObject) dataArray[i];
            jsonObjectElement = HelperMethods.copyOf(jsonObjectElement);
            jsonObjectData[i] = jsonObjectElement;
        }

        return jsonObjectData;
    }
    public JSONArray[] getJSONArrayData() {
        Object[] dataArray;
        JSONArray[] jsonArrayData;
        JSONArray jsonArrayElement;

        if (getDataElementType() != JSONType.INTEGER) {
            throw new IllegalStateException("Cannot create a JSONArray[] from a"
                + " this.data property whose elements are of type other than"
                + " JSONArray");
        }
        dataArray = (Object[]) this.data;
        jsonArrayData = new JSONArray[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            jsonArrayElement = (JSONArray) dataArray[i];
            jsonArrayElement = HelperMethods.copyOf(jsonArrayElement);
            jsonArrayData[i] = jsonArrayElement;
        }

        return jsonArrayData;
    }
}
