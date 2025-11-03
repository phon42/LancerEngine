package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Action;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import Packages.CoreTypes.EntityMechanics.Synergy;

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
public class Reserve {
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
     * The type of this reserve (i.e. "Bonus").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String type;
    /**
     * The label for this reserve (i.e. "Bonus").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String label;
    /**
     * The description for this reserve (i.e. "Add a Skill Point to gain or"
     *     " improve a skill, typically as part of the \"Get Focused\" Downtime"
     *     " Action.").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    // Optional properties
    /**
     * The bonuses associated with this reserve (i.e. a Bonus[] containing a
     *     Bonus which represents a singular skill point).
     * Can be any Bonus[] that is not of length 0 or contains null elements.
     *     Cannot be null.
     */
    private Bonus[] bonuses;
    /**
     * The deployables associated with this reserve (i.e. a Deployable[]
     *     containing the 'Deployable Shield (Reserve)' deployable).
     * Can be any Deployable[] that is not of length 0 or contains null
     *     elements. Cannot be null.
     */
    private Deployable[] deployables;
    /**
     * The actions associated with this reserve (i.e. an Action[] containing the
     *     Redundant Repair action).
     * Can be any Action[] that is not of length 0 or contains null elements.
     *     Cannot be null.
     */
    private Action[] actions;
    // TODO: add an example here
    /**
     * The synergies associated with this reserve (difficult to provide an
     *     example).
     * Can be any Synergy[] that is not of length 0 or contains null elements.
     *     Cannot be null.
     */
    private Synergy[] synergies;

    public Reserve(String id, String name, String type, String label,
        String description, Bonus[] bonuses, Deployable[] deployables,
        Action[] actions, Synergy[] synergies) {
        HelperMethods.verifyConstructor();
        // Required properties
        setID(id);
        setName(name);
        setType(type);
        setLabel(label);
        setDescription(description);
        // Optional properties
        setBonuses(bonuses);
        setDeployables(deployables);
        setActions(actions);
        setSynergies(synergies);
    }
    public Reserve(String id, String name, String type, String label,
        String description) {
        this(id, name, type, label, description, null,
            null, null, null);
    }
    public Reserve(Reserve reserve) {
        // Required properties
        setID(reserve.id);
        setName(reserve.name);
        setType(reserve.type);
        setLabel(reserve.label);
        setDescription(reserve.description);
        // Optional properties
        setBonuses(reserve.bonuses);
        setDeployables(reserve.deployables);
        setActions(reserve.actions);
        setSynergies(reserve.synergies);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getLabel() {
        return label;
    }
    public String getDescription() {
        return description;
    }
    // Optional properties
    public Bonus[] getBonuses() {
        if (bonuses != null) {
            return HelperMethods.copyOf(bonuses);
        }

        return bonuses;
    }
    public Deployable[] getDeployables() {
        if (deployables != null) {
            return HelperMethods.copyOf(deployables);
        }

        return deployables;
    }
    public Action[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public Synergy[] getSynergies() {
        if (synergies != null) {
            return HelperMethods.copyOf(synergies);
        }

        return synergies;
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
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        this.type = type;
    }
    private void setLabel(String label) {
        HelperMethods.checkString("label", label);
        this.label = label;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    // Optional properties
    private void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArray("bonuses", bonuses);
        if (bonuses.length == 0) {
            throw new IllegalArgumentException("bonuses is length 0");
        }
        bonuses = HelperMethods.copyOf(bonuses);
        this.bonuses = bonuses;
    }
    public void setDeployables(Deployable[] deployables) {
        HelperMethods.checkObjectArray("deployables",
            deployables);
        if (deployables.length == 0) {
            throw new IllegalArgumentException("deployables is length 0");
        }
        deployables = HelperMethods.copyOf(deployables);
        this.deployables = deployables;
    }
    public void setActions(Action[] actions) {
        HelperMethods.checkObjectArray("actions", actions);
        if (actions.length == 0) {
            throw new IllegalArgumentException("actions is length 0");
        }
        actions = HelperMethods.copyOf(actions);
        this.actions = actions;
    }
    public void setSynergies(Synergy[] synergies) {
        HelperMethods.checkObjectArray("synergies", synergies);
        if (synergies.length == 0) {
            throw new IllegalArgumentException("synergies is length 0");
        }
        synergies = HelperMethods.copyOf(synergies);
        this.synergies = synergies;
    }
}
