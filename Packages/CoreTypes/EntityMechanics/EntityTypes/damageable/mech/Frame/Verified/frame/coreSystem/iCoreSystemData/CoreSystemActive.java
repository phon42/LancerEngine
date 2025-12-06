package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationType.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Duration;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

public class CoreSystemActive {
    // Required properties
    /**
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    private VueHTMLString effect;
    private ActivationType activation;

    // Optional properties
    private ActivationType deactivation;
    /**
     * Must be one of the following:
     *     "round", "next round", "scene", "encounter", "mission"
     */
    private Duration duration;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    
}
