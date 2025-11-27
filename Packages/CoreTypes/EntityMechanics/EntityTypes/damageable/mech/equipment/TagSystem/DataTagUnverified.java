package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.DataTag;

public class DataTagUnverified {
    // Required property
    /**
     * The id of the data tag this ITagDataUnverified object refers to (i.e.
     *     "tg_ai").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;

    // Optional properties
    /**
     * The int value that this ITagDataUnverified object holds (example
     *     unhelpful).
     * When this.valueString is not null:
     *     Must be -1.
     * When this.valueString is null:
     *     Must be a minimum of 0.
     * Must be a minimum of -1.
     */
    private int valueInt;
    /**
     * The String value that this ITagDataUnverified object holds (i.e. "X").
     * When this.valueInt is -1:
     *     Must be null.
     * When this.valueInt is > -1:
     *     Can be any String. Cannot be null.
     * Can be any String. Can be null.
     * Case-sensitive.
     */
    private String valueString;

    public DataTagUnverified(String id, String value) {
        this(id);
        setValueString(value);
    }
    public DataTagUnverified(String id, int value) {
        this(id);
        setValueInt(value);
    }
    public DataTagUnverified(String id) {
        setID(id);
        this.valueInt = -1;
        this.valueString = null;
    }

    // Required property
    public String getID() {
        return id;
    }
    // Optional properties
    public int getValueInt() {
        return valueInt;
    }
    public String getValueString() {
        return valueString;
    }
    // Required property
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Optional properties
    private void setValueInt(int valueInt) {
        if (this.valueString == null) {
            // Must be a minimum of 0
            if (valueInt < 0) {
                throw new IllegalArgumentException("valueInt: " + valueInt
                    + " is < 0");
            }
        } else {
            // Must be -1
            if (valueInt != -1) {
                throw new IllegalArgumentException("valueInt: " + valueInt
                    + " is != -1");
            }
        }
        this.valueInt = valueInt;
    }
    private void setValueString(String valueString) {
        if (this.valueInt == -1) {
            // Must be null
            if (valueString != null) {
                throw new IllegalArgumentException("valueString: \""
                    + valueString + "\" is not null");
            }
        } else {
            // Can be any String. Cannot be null.
            HelperMethods.checkObject("valueString", valueString);
        }
        this.valueString = valueString;
    }

    public DataTag toITagData() {
        DataTag dataTag;

        try {
            dataTag = Database.getDataTag(this.id);
        } catch (NoSuchElementException exception) {
            throw new IllegalStateException("Unable to convert this"
                + " ITagDataUnverified to a ITagData because the DataTag: \""
                + this.id + "\" could not be found in Database");
        }
        if (this.valueInt > -1) {
            return new DataTag(dataTag, this.valueInt);
        }
        if (this.valueString != null) {
            return new DataTag(dataTag, this.valueString);
        }

        return new DataTag(dataTag);
    }
}
