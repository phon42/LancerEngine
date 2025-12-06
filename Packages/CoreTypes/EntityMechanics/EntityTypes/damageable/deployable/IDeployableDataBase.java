package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.Counter.counterBase.CounterData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationType.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.DeployableStatblock;

public class IDeployableDataBase {
    // Required properties
    protected String name;
    protected String type;
    protected String detail;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any ActivationType. Cannot be null.
     * Default value: an ActivationType representing "Quick".
     */
    protected ActivationType activation;
    /**
     * The number of copies of the deployable that are spawned per activation.
     * Must be a minimum of 1.
     * Default value: 1.
     */
    protected int instances;
    public static final int instancesDefault = 1;
    /**
     * The number of limited charges activating this deployable costs.
     * If the parent item is a limited-use item, this amount will be deducted
     *     from the parent item's number of limited-use charges upon this
     *     deployable being activated.
     * Likewise, the recall action for this deployable refunds (this.cost)
     *     limited charges, while the deactivation action refunds none.
     * Can be any int.
     * Default value: 0.
     */
    protected int cost;
    public static final int costDefault = 0;
    /**
     * Whether this deployable is available to be used by pilots.
     */
    protected boolean pilot;
    /**
     * Whether this deployable is available to be used by mechs.
     */
    protected boolean mech;

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
     * Use IDeployableDataBase.getSize() to get the raw value and
     *     IDeployableDataBase.outputSize() to obtain it properly formatted.
     */
    protected Size size;
    protected static final Size sizeDefault = new Size(2);
    /**
     * The statblock associated with this deployable (example unhelpful).
     * Required when this.type is not "Mine".
     * When required:
     *     Can be any DeployableStatblock. Cannot be null.
     *     Default value: The default statblock for a deployable.
     * When not required:
     *     Must be null.
     *     No default value.
     * Can be any DeployableStatblock. Can be null.
     */
    protected DeployableStatblock statblock;
    protected static final DeployableStatblock statblockDefault =
        new DeployableStatblock(IDeployableDataBase.sizeDefault);

    // Optional properties
    /**
     * Can be any ActivationType. Can be null.
     */
    protected ActivationType deactivation;
    /**
     * Can be any ActivationType. Can be null.
     */
    protected ActivationType recall;
    /**
     * Can be any ActivationType. Can be null.
     */
    protected ActivationType redeploy;
    /**
     * Can be any IActionData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    protected IActionData[] actions;
    /**
     * Can be any Bonus[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    protected Bonus[] bonuses;
    /**
     * Can be any ISynergyData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    protected ISynergyData[] synergies;
    /**
     * Can be any CounterData[] that is not of length 0 and does not contain
     *     null elements. Can be null.
     */
    protected CounterData[] counters;

    public IDeployableDataBase(
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
        ISynergyData[] synergies, CounterData[] counters
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
    }
    public IDeployableDataBase(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech,
        // Semi- and conditionally required properties
        Size size, DeployableStatblock statblock
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech, size,
            statblock, null, null, null,
            null, null, null, null);
    }
    public IDeployableDataBase(
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
            null, null, null, null);
    }
    public IDeployableDataBase(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        ActivationType activation, int instances, int cost, TriState pilot,
        TriState mech
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech,
            null, null, null, null,
            null, null, null, null,
            null);
    }
    public IDeployableDataBase(
        // Required properties
        String name, String type, String detail
    ) {
        this(name, type, detail, null,
            IDeployableDataBase.instancesDefault,
            IDeployableDataBase.costDefault, TriState.UNSET, TriState.UNSET,
            null, null, null, null,
            null, null, null, null,
            null);
    }
    public IDeployableDataBase(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setStatblock(new DeployableStatblock(size, armor));
    }
    public IDeployableDataBase() {
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
    // Required properties
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        this.type = type;
    }
    protected void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        this.detail = detail;
    }
    // Semi-required properties
    protected void setActivation(ActivationType activation) {
        if (activation == null) {
            activation =
                Database.getActivationType("Quick");
        }
        this.activation = activation;
    }
    protected void setInstances(int instances) {
        if (instances < 1) {
            instances = IDeployableDataBase.instancesDefault;
        }
        this.instances = instances;
    }
    protected void setCost(int cost) {
        this.cost = cost;
    }
    protected void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    protected void setMech(boolean mech) {
        this.mech = mech;
    }
    // Semi- and conditionally required properties
    protected void setSize(Size size) {
        if (! isMine()) {
            // Required
            // Can be any Size. Cannot be null.
            if (size == null) {
                size = IDeployableDataBase.sizeDefault;
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
    protected void setStatblock(DeployableStatblock statblock) {
        if (! isMine()) {
            // Required
            // Can be any DeployableStatblock. Cannot be null.
            if (statblock == null) {
                statblock = IDeployableDataBase.statblockDefault;
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
    protected void setDeactivation(ActivationType deactivation) {
        this.deactivation = deactivation;
    }
    protected void setRecall(ActivationType recall) {
        this.recall = recall;
    }
    protected void setRedeploy(ActivationType redeploy) {
        this.redeploy = redeploy;
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
    protected void setCounters(CounterData[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        if (counters != null) {
            counters = HelperMethods.copyOf(counters);
        }
        this.counters = counters;
    }

    public String outputType() {
        return HelperMethods.capitalizeFirst(type);
    }
    protected void setPilotAndMech(TriState pilot, TriState mech) {
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
    protected boolean isMine() {
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
