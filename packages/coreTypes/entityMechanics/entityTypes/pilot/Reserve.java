package packages.CoreTypes.EntityMechanics.entityTypes.pilot;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.Action;
import packages.CoreTypes.EntityMechanics.Bonus;
import packages.CoreTypes.EntityMechanics.entityTypes.Deployable;

/**
 * Represents a single reserve. Contains information about the reserve's id,
 *     name, type, label, and description, with other optional properties.
 * 
 * Requires a reserve id, name, label, and description to be created.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Reserve {
    // TODO: fill out
    // Required properties
    private String id;
    private String name;
    private String type;
    private String label;
    private String description;
    // Optional properties
    private Bonus[] bonuses;
    private Deployable[] deployables;
    private Action[] actions;
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

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    private void setID(String id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
}
