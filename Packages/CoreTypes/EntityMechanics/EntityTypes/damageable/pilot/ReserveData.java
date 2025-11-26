package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserveData.ReserveType;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;

/**
 * Represents a single reserve. Contains information about the reserve's id,
 *     name, type, label, and description, with other optional properties.
 * 
 * Requires a reserve id, name, type, label, and description to be created.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class ReserveData {
    // Required properties
    /**
     * The id for this reserve (i.e. "reserve_skill").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of this reserve (i.e. "Skill Point").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The type of this reserve (i.e. a ReserveType representing a bonus
     *     reserve).
     * Can be any ReserveType. Cannot be null.
     */
    private ReserveType type;
    /**
     * The label for this reserve (i.e. "Bonus").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String label;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    private boolean consumable;
    private static final boolean consumableDefault = false;

    // Optional properties
    /**
     * The description for this reserve (i.e. a VueHTMLString representing "Add"
     *     " a Skill Point to gain or improve a skill, typically as part of the"
     *     " \"Get Focused\" Downtime Action.").
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString description;
    /**
     * The actions associated with this reserve (i.e. an IActionData[]
     *     containing the Redundant Repair action).
     * Can be any IActionData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IActionData[] actions;
    /**
     * The bonuses associated with this reserve (i.e. a Bonus[] containing a
     *     Bonus which represents a singular skill point).
     * Can be any Bonus[] that is not of length 0 or contains null elements.
     *     Can be null.
     */
    private Bonus[] bonuses;
    // TODO: add an example here
    /**
     * The synergies associated with this reserve (difficult to provide an
     *     example).
     * Can be any Synergy[] that is not of length 0 or contains null elements.
     *     Can be null.
     */
    private ISynergyData[] synergies;
    /**
     * The deployables associated with this reserve (i.e. an IDeployableData[]
     *     containing the 'Deployable Shield (Reserve)' deployable).
     * Can be any IDeployableData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IDeployableData[] deployables;
    private Counter[] counters;
    private String[] integrated;
    private String[] specialEquipment;

    public ReserveData(
        // Required properties
        String id, String name, ReserveType type, String label,
        // Semi-required property
        TriState consumable,
        // Optional properties
        VueHTMLString description, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        Counter[] counters, String[] integrated, String[] specialEquipment
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setID(id);
        setName(name);
        setType(type);
        setLabel(label);
        // Semi-required property
        setConsumable(consumable);
        // Optional properties
        setDescription(description);
        setActions(actions);
        setBonuses(bonuses);
        setSynergies(synergies);
        setDeployables(deployables);
        setCounters(counters);
        setIntegrated(integrated);
        setSpecialEquipment(specialEquipment);
    }
    public ReserveData(
        // Required properties
        String id, String name, ReserveType type, String label,
        // Semi-required property
        TriState consumable
    ) {
        this(id, name, type, label, consumable, null, null,
            null, null, null, null,
            null, null);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ReserveType getType() {
        return type;
    }
    public String getLabel() {
        return label;
    }
    // Semi-required property
    public boolean isConsumable() {
        return consumable;
    }
    // Optional properties
    public VueHTMLString getDescription() {
        return description;
    }
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public Bonus[] getBonuses() {
        if (bonuses != null) {
            return HelperMethods.copyOf(bonuses);
        }

        return bonuses;
    }
    public ISynergyData[] getSynergies() {
        if (synergies != null) {
            return HelperMethods.copyOf(synergies);
        }

        return synergies;
    }
    public IDeployableData[] getDeployables() {
        if (deployables != null) {
            return HelperMethods.copyOf(deployables);
        }

        return deployables;
    }
    public Counter[] getCounters() {
        if (counters != null) {
            return HelperMethods.copyOf(counters);
        }

        return counters;
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
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setType(ReserveType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    private void setLabel(String label) {
        HelperMethods.checkString("label", label);
        this.label = label;
    }
    // Semi-required property
    private void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }
    // Optional properties
    private void setDescription(VueHTMLString description) {
        if (description != null) {
            HelperMethods.checkVueHTMLString("description",
                description);
        }
        this.description = description;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        this.actions = actions;
    }
    private void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        if (bonuses != null) {
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    private void setSynergies(ISynergyData[] synergies) {
        HelperMethods.checkObjectArray("synergies", synergies);
        if (synergies.length == 0) {
            throw new IllegalArgumentException("synergies is length 0");
        }
        synergies = HelperMethods.copyOf(synergies);
        this.synergies = synergies;
    }
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        this.deployables = deployables;
    }
    private void setCounters(Counter[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        if (counters != null) {
            counters = HelperMethods.copyOf(counters);
        }
        this.counters = counters;
    }
    private void setIntegrated(String[] integrated) {
        HelperMethods.checkStringArrayAlt("integrated",
            integrated);
        if (integrated != null) {
            integrated = HelperMethods.copyOf(integrated);
        }
        this.integrated = integrated;
    }
    private void setSpecialEquipment(String[] specialEquipment) {
        HelperMethods.checkStringArrayAlt("specialEquipment",
            specialEquipment);
        if (specialEquipment != null) {
            specialEquipment = HelperMethods.copyOf(specialEquipment);
        }
        this.specialEquipment = specialEquipment;
    }

    private void setConsumable(TriState consumable) {
        if (consumable == TriState.UNSET) {
            setConsumable(ReserveData.consumableDefault);
        } else {
            setConsumable(consumable.toBoolean());
        }
    }
}
