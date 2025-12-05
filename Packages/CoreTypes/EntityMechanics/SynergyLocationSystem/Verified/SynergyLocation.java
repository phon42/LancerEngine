package Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified;

import MainBranch.HelperMethods;

public class SynergyLocation {
    // TODO: fill out
    // Required property
    private String value;

    // Optional property
    private String description;

    public SynergyLocation(String value, String description) {
        HelperMethods.verifyConstructor();
        setValue(value);
        setDescription(description);
    }
    public SynergyLocation(String value) {
        this(value, null);
    }

    // Required property
    public String getValue() {
        return value;
    }
    // Optional property
    public String getDescription() {
        return description;
    }
    // Required property
    private void setValue(String value) {
        HelperMethods.checkString("value", value);
        this.value = value;
    }
    // Optional property
    public void setDescription(String description) {
        if (description != null) {
            HelperMethods.checkString("description", description);
        }
        this.description = description;
    }
}
