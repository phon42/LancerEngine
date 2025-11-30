package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

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
     * Whether this deployable is available to be used by pilots.
     */
    private boolean pilot;
    /**
     * Whether this deployable is available to be used by mechs.
     */
    private boolean mech;
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
    private ActivationType activation;
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
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private CounterData[] counters;
    private DataTag[] tags;

    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech, int instances, int cost,
        // Semi- and conditionally required properties
        Size size, DeployableStatblock statblock,
        // Optional properties
        ActivationType activation, ActivationType deactivation,
        ActivationType recall, ActivationType redeploy, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies, CounterData[] counters,
        DataTag[] tags
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setType(type);
        setDetail(detail);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setInstances(instances);
        setCost(cost);
        // Semi- and conditionally required properties
        setSize(size);
        // Optional properties
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech, int instances, int cost,
        // Semi- and conditionally required property
        Size size
    ) {
        this(name, type, detail, pilot, mech, instances, cost, size,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech,
        // Semi- and conditionally required properties
        Size size, DeployableStatblock statblock
    ) {
        this(name, type, detail, pilot, mech, -1, IDeployableData.costDefault,
            size, statblock, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech, int instances, int cost
    ) {
        this(name, type, detail, pilot, mech, instances, cost, null,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail
    ) {
        this(name, type, detail, TriState.UNSET, TriState.UNSET, -1,
            IDeployableData.costDefault, null, null,
            null, null, null, null,
            null, null, null, null,
            null);
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
    public boolean isAvailableToPilots() {
        return pilot;
    }
    public boolean isAvailableToMechs() {
        return mech;
    }
    public int getInstances() {
        return instances;
    }
    public int getCost() {
        return cost;
    }
    // Semi- and conditionally required properties
    public Size getSize() {
        return size;
    }
    public DeployableStatblock getStatblock() {
        return statblock;
    }
    // Optional properties
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
    private void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    private void setMech(boolean mech) {
        this.mech = mech;
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
