package Packages.CoreTypes.EntityMechanics.Actions.actionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBase;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Damage;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Synergy;

public class Action extends ActionBase {
    // Required properties
    private String id;
    // Optional properties
    private String terse;
    private boolean pilot;
    private boolean mech;
    private Synergy[] synergyLocations;
    private String[] confirm;
    private String log;
    private boolean ignoreUsed;
    private int heatCost;

    public Action(String name, ActivationType activation,
        HTMLString detailedDescription, Callable method, String id,
        String terse, boolean pilot, boolean mech, Synergy[] synergyLocations,
        String[] confirm, String log, boolean ignoreUsed, int heatCost) {
        super(name, activation, detailedDescription, method);
        setID(id);
    }
    public Action(String name, ActivationType activation,
        HTMLString detailedDescription, String id) {
        super(name, activation, detailedDescription);
        setID(id);
        // TODO: fill out to take care of optional properties, especially the
        //     booleans
    }
    // Required properties
    public String getID() {
        return id;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
}