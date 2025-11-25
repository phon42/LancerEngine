package Packages.CoreTypes.jsonTypeTree;

import java.math.BigDecimal;
import MainBranch.database.fileOperations.json.JSONArray;
import MainBranch.database.fileOperations.json.JSONObject;

public enum JSONType {
    NULL,
    BOOLEAN,
    INTEGER,
    BIGDECIMAL,
    STRING,
    JSONOBJECT,
    JSONARRAY;

    public static JSONType determineType(Object value) {
        if (value == null) {
            return JSONType.NULL;
        }
        if (value instanceof Boolean) {
            return JSONType.BOOLEAN;
        }
        if (value instanceof Integer) {
            return JSONType.INTEGER;
        }
        if (value instanceof BigDecimal) {
            return JSONType.BIGDECIMAL;
        }
        if (value instanceof String) {
            return JSONType.STRING;
        }
        if (value instanceof JSONObject) {
            return JSONType.JSONOBJECT;
        }
        if (value instanceof JSONArray) {
            return JSONType.JSONARRAY;
        }
        throw new IllegalArgumentException("value is of a type that is not"
            + " one of the accepted JSONType values");
    }
}