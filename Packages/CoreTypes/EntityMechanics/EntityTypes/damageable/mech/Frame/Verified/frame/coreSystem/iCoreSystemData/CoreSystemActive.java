package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationType.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.Duration;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

public class CoreSystemActive {
    // Required properties
    /**
     * This core system active's name (i.e. "Power Up").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * This core system active's effect (i.e. a VueHTMLString representing "For"
     *     " the rest of this scene, you gain +1 Accuracy on all attacks,"
     *     " checks, and saves; additionally, 1/turn, you can Boost as a free"
     *     " action.").
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    private VueHTMLString effect;
    /**
     * The speed at which this core system active must be activated (i.e. an
     *     ActivationType representing a Protocol).
     * Can be any ActivationType. Cannot be null.
     */
    private ActivationType activation;

    // Optional properties
    /**
     * The speed at which this core system active must be deactivated (i.e. an
     *     ActivationType representing a Protocol).
     * Can be any ActivationType. Can be null.
     */
    private ActivationType deactivation;
    /**
     * Can only be one of the following:
     *     "round", "next round", "scene", "encounter", "mission"
     * Can be null.
     */
    private Duration duration;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;

    public CoreSystemActive(
        // Required properties
        String name, String effect, ActivationType activation,
        // Optional properties
        ActivationType deactivation, Duration duration, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies
    ) {
        // Required properties
        setName(name);
        setEffect(effect);
        setActivation(activation);
        // Optional properties
        // TODO: fill out
    }
    public CoreSystemActive(
        // Required properties
        String name, String effect, ActivationType activation
    ) {
        this(name, effect, activation, null, null,
            null, null, null);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public VueHTMLString getEffect() {
        return effect;
    }
    public ActivationType getActivation() {
        return activation;
    }
    // Optional properties
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setEffect(VueHTMLString effect) {
        HelperMethods.checkVueHTMLString("effect", effect);
        this.effect = effect;
    }
    private void setActivation(ActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        this.activation = activation;
    }
    // Optional properties

    private void setEffect(String effect) {
        HelperMethods.checkObject("effect", effect);
        setEffect(new VueHTMLString(effect));
    }
}
