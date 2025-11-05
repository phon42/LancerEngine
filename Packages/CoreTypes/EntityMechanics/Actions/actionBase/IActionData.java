package Packages.CoreTypes.EntityMechanics.Actions.actionBase;

import Packages.CoreTypes.Callable;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.EntityMechanics.Synergy;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBase;
import Packages.CoreTypes.EntityMechanics.ActivationType;

public class IActionData extends ActionBase {
    // Optional properties
    private int cost;
    private boolean pilot;
    private Synergy[] synergyLocations;
    private boolean techAttack;
    private String[] log;

    public IActionData(String name, ActivationType activation,
        HTMLString detailedDescription, Callable method, int cost,
        boolean pilot, Synergy[] synergyLocations, boolean techAttack,
        String[] log) {
        super(name, activation, detailedDescription, method);
    }
    public IActionData(String name, ActivationType activation,
        HTMLString detailedDescription) {
        super(name, activation, detailedDescription);
    }
}
