package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

public class CoreSystemPassive {
    // Optional properties
    private String passiveName;
    private VueHTMLString passiveEffect;
    private IActionData[] passiveActions;
    private IActionData[] passiveBonuses;
    private ISynergyData[] passiveSynergies;
}
