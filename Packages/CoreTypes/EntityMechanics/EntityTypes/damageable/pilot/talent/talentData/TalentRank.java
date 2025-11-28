package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.talentData;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;

public class TalentRank {
    // Required properties
    private String name;
    private String description;

    // Semi-required property
    private boolean exclusive;
    private static final boolean exclusiveDefault = true;

    // Optional properties
    private ISynergyData[] synergies;
    private IActionData[] actions;
    private Counter[] counters;
    private Bonus[] bonuses;
    private String[] integrated;
    private String[] specialEquipment;

    public TalentRank(String name, String description, TriState exclusive,
        ISynergyData[] synergies, IActionData[] actions, Counter[] counters,
        Bonus[] bonuses, String[] integrated, String[] specialEquipment[]) {
        // Required properties
        setName(name);
        setDescription(description);
        // Semi-required property
        setExclusive(exclusive);
        // Optional properties
        setSynergies(synergies);
        setActions(actions);
        setCounters(counters);
        setBonuses(bonuses);
        setIntegrated(integrated);
        setSpecialEquipment(integrated);
    }
    public TalentRank(String name, String description, TriState exclusive) {
        this(name, description, exclusive, null, null,
            null, null, null,
            null);
    }
    public TalentRank(String name, String description) {
        this(name, description, TriState.UNSET, null, null,
            null, null, null,
            null);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Semi-required property
    public boolean isExclusive() {
        return exclusive;
    }
    // Optional properties
    public ISynergyData[] getSynergies() {
        if (synergies != null) {
            return HelperMethods.copyOf(synergies);
        }

        return synergies;
    }
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public Counter[] getCounters() {
        if (counters != null) {
            return HelperMethods.copyOf(counters);
        }

        return counters;
    }
    public Bonus[] getBonuses() {
        if (bonuses != null) {
            return HelperMethods.copyOf(bonuses);
        }

        return bonuses;
    }
    public String[] getIntegrated() {
        if (integrated != null) {
            return HelperMethods.copyOf(integrated);
        }

        return integrated;
    }
    public String[] getSpecialEquipment() {
        if (specialEquipment != null) {
            return HelperMethods.copyOf(specialEquipment);
        }

        return specialEquipment;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    // Semi-required property
    private void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }
    // Optional properties
    private void setSynergies(ISynergyData[] synergies) {
        HelperMethods.checkObjectArrayAlt("synergies", synergies);
        synergies = HelperMethods.copyOf(synergies);
        this.synergies = synergies;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        actions = HelperMethods.copyOf(actions);
        this.actions = actions;
    }
    private void setCounters(Counter[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        counters = HelperMethods.copyOf(counters);
        this.counters = counters;
    }
    private void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        bonuses = HelperMethods.copyOf(bonuses);
        this.bonuses = bonuses;
    }
    private void setIntegrated(String[] integrated) {
        HelperMethods.checkStringArrayAlt("integrated",
            integrated);
        integrated = HelperMethods.copyOf(integrated);
        this.integrated = integrated;
    }
    private void setSpecialEquipment(String[] specialEquipment) {
        HelperMethods.checkStringArrayAlt("specialEquipment",
            specialEquipment);
        specialEquipment = HelperMethods.copyOf(specialEquipment);
        this.specialEquipment = specialEquipment;
    }

    private void setExclusive(TriState exclusive) {
        HelperMethods.checkObject("exclusive", exclusive);
        if (exclusive == TriState.UNSET) {
            setExclusive(TalentRank.exclusiveDefault);
        } else {
            setExclusive(exclusive.toBoolean());
        }
    }
}
