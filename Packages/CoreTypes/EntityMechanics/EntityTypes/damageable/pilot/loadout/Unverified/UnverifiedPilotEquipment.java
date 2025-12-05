package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;

public class UnverifiedPilotEquipment {
    // TODO: fill out
    // Required properties
    protected String id;
    protected VueHTMLString name;
    protected String type;

    // Optional properties
    /**
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    protected String description;
    protected UnverifiedDataTag[] dataTags;
    /**
     * The pilot armor's actions (i.e. an IActionData representing the
     *     "Activate Stealth Hardsuit" action).
     * Can be any IActionData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    protected IActionData[] actions;
    /**
     * The pilot armor's bonuses (i.e. a Bonus representing a +3 to pilot HP).
     * Can be any Bonus[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    protected Bonus[] bonuses;
    protected ISynergyData[] synergies;
    /**
     * Can be any UnverifiedIDeployableData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    protected UnverifiedIDeployableData[] deployables;

    protected UnverifiedPilotEquipment(
        // Required properties
        String id, String name, String type,
        // Optional properties
        String description, UnverifiedDataTag[] dataTags, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies,
        UnverifiedIDeployableData[] deployables
    ) {
        // Required properties
        setID(id);
        setName(name);
        setType(type);
        // Optional properties
        setDataTags(dataTags);
        setDescription(description);
    }
    protected UnverifiedPilotEquipment(
        // Required properties
        String id, String name, String type
    ) {
        this(id, name, type, null, null, null,
            null, null, null);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public VueHTMLString getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    // Optional properties
    public String getDescription() {
        return description;
    }
    public UnverifiedDataTag[] getDataTags() {
        if (dataTags != null) {
            return HelperMethods.copyOf(dataTags);
        }

        return dataTags;
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
    public UnverifiedIDeployableData[] getDeployables() {
        if (deployables != null) {
            HelperMethods.copyOf(deployables);
        }

        return deployables;
    }
    // Required properties
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        this.id = id;
    }
    protected void setName(VueHTMLString name) {
        HelperMethods.checkVueHTMLString("name", name);
        this.name = name;
    }
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        this.type = type;
    }
    // Optional properties
    protected void setDescription(String description) {
        if (description != null) {
            HelperMethods.checkString("description", description);
        }
        this.description = description;
    }
    protected void setDataTags(UnverifiedDataTag[] dataTags) {
        HelperMethods.checkObjectArrayAlt("dataTags", dataTags);
        if (dataTags != null) {
            dataTags = HelperMethods.copyOf(dataTags);
        }
        this.dataTags = dataTags;
    }
    protected void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    protected void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        if (bonuses != null) {
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    protected void setSynergies(ISynergyData[] synergies) {
        HelperMethods.checkObjectArrayAlt("synergies", synergies);
        if (synergies != null) {
            synergies = HelperMethods.copyOf(synergies);
        }
        this.synergies = synergies;
    }
    protected void setDeployables(UnverifiedIDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }

    protected void setName(String name) {
        setName(new VueHTMLString(name));
    }
}

