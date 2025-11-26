package Packages.CoreTypes.EntityMechanics.HarmSystem.harm;

import java.util.NoSuchElementException;
import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

public class HarmType {
    protected String value;

    public HarmType(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    protected void setValue(String value) {
        HelperMethods.checkString("value", value);
        value = value.toLowerCase();
        this.value = value;
    }

    public String outputValue() {
        return HelperMethods.capitalizeFirst(value);
    }
    public DamageType toDamageType() {
        try {
            return Database.getDamageType(value);
        } catch (NoSuchElementException exception) {
            throw new IllegalStateException("Unable to convert this HarmType"
                + " object to a DamageType");
        }
    }
}
