package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

public class CoreSystemData {
    // Required property
    private String name;

    // Optional property
    private VueHTMLString description;

    public CoreSystemData(String name, String description) {
        setName(name);
        setDescription(description);
    }
    public CoreSystemData(String name) {
        this(name, null);
    }

    // Required property
    public String getName() {
        return name;
    }
    // Optional property
    public VueHTMLString getDescription() {
        return description;
    }
    // Required property
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    // Optional property
    private void setDescription(VueHTMLString description) {
        HelperMethods.checkVueHTMLStringAlt("description",
            description);
        this.description = description;
    }

    private void setDescription(String description) {
        if (description == null) {
            this.description = null;
        } else {
            setDescription(new VueHTMLString(description));
        }
    }
}
