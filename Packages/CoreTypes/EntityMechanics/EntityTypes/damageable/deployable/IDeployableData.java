package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableData.DeployableStatblock;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.counterBase.CounterData;

/**
 * See pgs. 58 and 68.
 */
public class IDeployableData {
    // Required properties
    private String name;
    private String type;
    private String detail;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any ActivationType. Cannot be null.
     * Default value: an ActivationType representing "Quick".
     */
    private ActivationType activation;
    /**
     * The number of copies of the deployable that are spawned per activation.
     * Must be a minimum of 1.
     * Default value: 1.
     */
    private int instances;
    private static final int instancesDefault = 1;
    /**
     * The number of limited charges activating this deployable costs.
     * If the parent item is a limited-use item, this amount will be deducted
     *     from the parent item's number of limited-use charges upon this
     *     deployable being activated.
     * Likewise, the recall action for this deployable refunds (this.cost)
     *     limited charges, while the deactivation action refunds none.
     * Can be any int.
     * Default value: 1.
     */
    private int cost;
    private static final int costDefault = 1;
    /**
     * Whether this deployable is available to be used by pilots.
     */
    private boolean pilot;
    /**
     * Whether this deployable is available to be used by mechs.
     */
    private boolean mech;

    // Semi- and conditionally required properties
    /**
     * The deployable's size.
     * Required when this.type is not "Mine".
     * When required:
     *     Can be any Size. Cannot be null.
     *     Default value: Size 1.
     * When not required:
     *     Must be null.
     *     No default value.
     * Can be any Size. Can be null.
     * 
     * Use IDeployableData.getSize() to get the raw value and
     *     IDeployableData.outputSize() to obtain it properly formatted.
     */
    private Size size;
    private static final Size sizeDefault = new Size(2);
    /**
     * The statblock associated with this IDeployableData (example unhelpful).
     * Required when this.type is not "Mine".
     * When required:
     *     Can be any DeployableStatblock. Cannot be null.
     *     Default value: The default statblock for a deployable.
     * When not required:
     *     Must be null.
     *     No default value.
     * Can be any DeployableStatblock. Can be null.
     */
    private DeployableStatblock statblock;
    private static final DeployableStatblock statblockDefault =
        new DeployableStatblock(IDeployableData.sizeDefault, 0);

    // Optional properties
    /**
     * Can be any ActivationType. Can be null.
     */
    private ActivationType deactivation;
    /**
     * Can be any ActivationType. Can be null.
     */
    private ActivationType recall;
    /**
     * Can be any ActivationType. Can be null.
     */
    private ActivationType redeploy;
    /**
     * Can be any IActionData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    private IActionData[] actions;
    /**
     * Can be any Bonus[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private Bonus[] bonuses;
    /**
     * Can be any ISynergyData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    private ISynergyData[] synergies;
    /**
     * Can be any CounterData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    private CounterData[] counters;
    /**
     * Can be any DataTag[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private DataTag[] tags;

    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech,
        // Semi- and conditionally required properties
        Size size, DeployableStatblock statblock,
        // Optional properties
        ActivationType deactivation, ActivationType recall,
        ActivationType redeploy, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, DataTag[] tags
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setType(type);
        setDetail(detail);
        // Semi-required properties
        setActivation(deactivation);
        setInstances(instances);
        setCost(cost);
        setPilotAndMech(pilot, mech);
        // Semi- and conditionally required properties
        setSize(size);
        // Optional properties
        setDeactivation(deactivation);
        setRecall(recall);
        setRedeploy(redeploy);
        setActions(actions);
        setBonuses(bonuses);
        setSynergies(synergies);
        setCounters(counters);
        setTags(tags);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech,
        // Semi- and conditionally required properties
        Size size, DeployableStatblock statblock
    ) {
        this(name, type, detail, activation, -1, IDeployableData.costDefault,
            pilot, mech, size, statblock, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech,
        // Semi- and conditionally required properties
        Size size
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech, size,
            null, null, null, null,
            null, null, null, null,
            null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail
    ) {
        this(name, type, detail, null, -1,
            IDeployableData.costDefault, TriState.UNSET, TriState.UNSET,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setStatblock(new DeployableStatblock(size, armor));
    }
    public IDeployableData() {
        this(new Size(2), 0);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDetail() {
        return detail;
    }
    // Semi-required properties
    public ActivationType getActivation() {
        return activation;
    }
    public int getInstances() {
        return instances;
    }
    public int getCost() {
        return cost;
    }
    public boolean isPilot() {
        return pilot;
    }
    public boolean isMech() {
        return mech;
    }
    // Semi- and conditionally required properties
    public Size getSize() {
        return size;
    }
    public DeployableStatblock getStatblock() {
        return statblock;
    }
    // Optional properties
    public ActivationType getDeactivation() {
        return deactivation;
    }
    public ActivationType getRecall() {
        return recall;
    }
    public ActivationType getRedeploy() {
        return redeploy;
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
    public CounterData[] getCounters() {
        if (counters != null) {
            return HelperMethods.copyOf(counters);
        }

        return counters;
    }
    public DataTag[] getTags() {
        if (tags != null) {
            return HelperMethods.copyOf(tags);
        }

        return tags;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        this.type = type;
    }
    private void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        this.detail = detail;
    }
    // Semi-required properties
    private void setActivation(ActivationType activation) {
        if (activation == null) {
            activation =
                Database.getActivationType("Quick");
        }
        this.activation = activation;
    }
    private void setInstances(int instances) {
        if (instances < 1) {
            instances = IDeployableData.instancesDefault;
        }
        this.instances = instances;
    }
    private void setCost(int cost) {
        this.cost = cost;
    }
    private void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    private void setMech(boolean mech) {
        this.mech = mech;
    }
    // Semi- and conditionally required properties
    private void setSize(Size size) {
        if (! isMine()) {
            // Required
            // Can be any Size. Cannot be null.
            if (size == null) {
                size = IDeployableData.sizeDefault;
            }
        } else {
            // Not required
            // Must be null.
            if (size != null) {
                throw new IllegalArgumentException("size is not null");
            }
        }
        this.size = size;
    }
    private void setStatblock(DeployableStatblock statblock) {
        if (! isMine()) {
            // Required
            // Can be any DeployableStatblock. Cannot be null.
            if (statblock == null) {
                statblock = IDeployableData.statblockDefault;
            }
        } else {
            // Not required
            // Must be null.
            if (statblock != null) {
                throw new IllegalArgumentException("statblock is not null");
            }
        }
        this.statblock = statblock;
    }
    // Optional properties
    private void setDeactivation(ActivationType deactivation) {
        this.deactivation = deactivation;
    }
    private void setRecall(ActivationType recall) {
        this.recall = recall;
    }
    private void setRedeploy(ActivationType redeploy) {
        this.redeploy = redeploy;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
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
        HelperMethods.checkObjectArrayAlt("synergies", synergies);
        if (synergies != null) {
            synergies = HelperMethods.copyOf(synergies);
        }
        this.synergies = synergies;
    }
    private void setCounters(CounterData[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        if (counters != null) {
            counters = HelperMethods.copyOf(counters);
        }
        this.counters = counters;
    }
    private void setTags(DataTag[] tags) {
        HelperMethods.checkObjectArrayAlt("tags", tags);
        if (tags != null) {
            tags = HelperMethods.copyOf(tags);
        }
        this.tags = tags;
    }

    public String outputType() {
        return HelperMethods.capitalizeFirst(type);
    }
    private void setPilotAndMech(TriState pilot, TriState mech) {
        if (pilot == TriState.TRUE) {
            if (mech == TriState.UNSET) {
                setPilot(true);
                setMech(false);

                return;
            }
        } else if (pilot == TriState.UNSET) {
            if (mech == TriState.UNSET) {
                setPilot(false);
                setMech(true);

                return;
            }
        }
        throw new IllegalStateException("Unexpected case");
    }
    private boolean isMine() {
        return this.type.equals("Mine");
    }
    /**
     * A helper method which outputs the deployable's size, formatted properly
     *     so that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        return size.output();
    }
}
