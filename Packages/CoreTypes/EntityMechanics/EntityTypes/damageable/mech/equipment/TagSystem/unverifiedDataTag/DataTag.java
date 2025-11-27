package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag;

import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.dataTag.ITagData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.dataTag.iTagData.ITagDataUnhidden;

public class DataTag {
    // Required property
    /**
     * The data tag this DataTag object refers to (i.e. a ITagData representing
     *     the AI tag).
     * Can be any ITagData. Cannot be null.
     */
    private ITagData data;

    // Optional properties
    /**
     * The int value that this DataTag object holds (example unhelpful).
     * When this.valueString is not null:
     *     Must be -1.
     * When this.valueString is null:
     *     Must be a minimum of 0.
     * When this.data.acceptsValue is TriState.FALSE, one of either
     *     this.valueInt or this.valueString must be a minimum of 0 or a
     *     non-null String, respectively.
     * Must be a minimum of -1.
     */
    private int valueInt;
    /**
     * The String value that this DataTag object holds (i.e. "X").
     * When this.valueInt is -1:
     *     Must be null.
     * When this.valueInt is > -1:
     *     Can be any String. Cannot be null.
     * When this.data.acceptsValue is TriState.FALSE, one of either
     *     this.valueInt or this.valueString must be a minimum of 0 or a
     *     non-null String, respectively.
     * Can be any String. Can be null.
     * Case-sensitive.
     */
    private String valueString;

    public DataTag(ITagData data, String value) {
        setData(data);
        this.valueInt = -1;
        this.valueString = null;
        setValueString(value);
        verify();
    }
    public DataTag(ITagData data, int value) {
        setData(data);
        this.valueInt = -1;
        this.valueString = null;
        setValueInt(value);
        verify();
    }
    public DataTag(ITagData data) {
        setData(data);
        this.valueInt = -1;
        this.valueString = null;
        verify();
    }

    // Required property
    public ITagData getData() {
        return data;
    }
    // Optional properties
    public int getValueInt() {
        return valueInt;
    }
    public String getValueString() {
        return valueString;
    }
    // Required property
    private void setData(ITagData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
    // Optional properties
    private void setValueInt(int valueInt) {
        if (this.valueString != null) {
            // Must be -1
            if (valueInt != -1) {
                throw new IllegalArgumentException("valueInt: " + valueInt
                    + " is != -1");
            }
        } else {
            // Must be a minimum of 0
            if (valueInt < 0) {
                throw new IllegalArgumentException("valueInt: " + valueInt
                    + " is < 0");
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

    public Object getValue() {
        if (this.valueString == null) {
            return this.valueInt;
        } else if (this.valueInt == -1) {
            return this.valueString;
        } else {
            // you can throw a RuntimeException here if you want
            // I'm not gonna
            return null;
        }
    }
    private void verify() throws IllegalStateException {
        if (data.acceptsValue() == TriState.TRUE) {
            if (valueInt == -1 && valueString == null) {
                throw new IllegalStateException("this.data requires a value"
                    + " of some kind and both this.valueInt is -1 and"
                    + " this.valueString is null");
            }
        } else if (data.acceptsValue() == TriState.FALSE) {
            if (valueInt != -1) {
                throw new IllegalStateException("this.data does not accept a"
                    + " value, but this.valueInt is: " + valueInt + " which is"
                    + " not -1");
            }
            if (valueString != null) {
                throw new IllegalStateException("this.data does not accept a"
                    + " value, but this.valueString is: \"" + valueString + "\""
                    + " which is not null");
            }
        }
        // otherwise this.data.acceptsValue is TriState.UNSET in which case we
        //     don't do any verification
    }
    public Tag toTag() throws IllegalArgumentException {
        ITagDataUnhidden data;

        if (this.data.isHidden()) {
            throw new IllegalArgumentException("Unable to convert a DataTag"
                + " with a DataTag.data.hidden value of true to a Tag");
        }
        data = this.data.toUnhiddenTag();
        if (this.valueString == null) {
            return new Tag(data, this.valueInt);
        } else if (this.valueInt != -1) {
            return new Tag(data, this.valueString);
        } else {
            return new Tag(data);
        }
    }
    public static Tag[] toTags(DataTag[] dataTags) {
        int numValidTags = 0;
        Tag[] tags;

        HelperMethods.checkObject("dataTags", dataTags);
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            numValidTags++;
        }
        tags = new Tag[numValidTags];
        numValidTags = 0;
        for (int i = 0; i < dataTags.length; i++) {
            if (dataTags[i] == null) {
                continue;
            }
            tags[numValidTags] = dataTags[i].toTag();
            numValidTags++;
        }

        return tags;
    }
}
