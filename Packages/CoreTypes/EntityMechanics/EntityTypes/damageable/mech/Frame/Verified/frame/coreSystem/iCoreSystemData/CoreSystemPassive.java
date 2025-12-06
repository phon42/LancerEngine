package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.coreSystem.iCoreSystemData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

public class CoreSystemPassive {
    // TODO: fill out
    // Optional properties
    /**
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString effect;
    private IActionData[] actions;
    private IActionData[] bonuses;
    private ISynergyData[] synergies;

    public CoreSystemPassive(String name, String effect, IActionData[] actions,
        IActionData[] bonuses, ISynergyData[] synergies) {
        HelperMethods.verifyConstructor();
        setName(name);
        setEffect(effect);
        setActions(actions);
        setBonuses(bonuses);
        setSynergies(synergies);
    }

    // Optional properties
    public String getName() {
        return name;
    }
    public VueHTMLString getEffect() {
        return effect;
    }
    public IActionData[] getActions() {
        return HelperMethods.copyOf(actions);
    }
    public IActionData[] getBonuses() {
        return HelperMethods.copyOf(bonuses);
    }
    public ISynergyData[] getSynergies() {
        return HelperMethods.copyOf(synergies);
    }
    // Optional properties
    private void setName(String name) {
        HelperMethods.checkStringAlt("name", name);
        this.name = name;
    }
    private void setEffect(VueHTMLString effect) {
        this.effect = effect;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setBonuses(IActionData[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        if (bonuses != null) {
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    private void setSynergies(ISynergyData[] synergies) {
        HelperMethods.checkObjectArrayAlt("synergies", synergies);
        if (synergies != null) {
            synergies = HelperMethods.copyOf(synergies);
        }
        this.synergies = synergies;
    }

    private void setEffect(String effect) {
        if (effect == null) {
            this.effect = null;
        } else {
            setEffect(new VueHTMLString(effect));
        }
    }
}
