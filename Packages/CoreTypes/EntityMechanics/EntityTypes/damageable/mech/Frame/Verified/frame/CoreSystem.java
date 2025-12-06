package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame;

import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.Deployable;

/**
 * See pg. 33.
 */
public class CoreSystem {
    // core system
    // core system description
    // core system passive
    // core system active

    // Required properties
    private String name;
    /**
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String activeName;
    /**
     * Can be any String. Cannot be null.
     * Case-sensitive.
     */
    private String activeEffect;
    private String activation;

    // Optional properties
    private String use;
    private String deactivation;
    private String[] integrated;
    private Deployable[] deployables;
    private ISynergyData[] activeSynergies;
    private IActionData[] activeActions;
    private Bonus[] activeBonuses;
    private String passiveName;
    private String passiveEffect;
    private IActionData[] passiveActions;

    public CoreSystem(CoreSystem coreSystem) {}
}
