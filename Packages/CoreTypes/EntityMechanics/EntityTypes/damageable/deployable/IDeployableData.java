package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableData.DeployableStatblock;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;

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
     * 
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

    // Conditionally required property
    /**
     * The deployable's size.
     * Required when this.type is not "Mine".
     * When required:
     *     Can be any Size. Cannot be null.
     * When not required:
     *     Must be null.
     * Can be any Size. Can be null.
     * 
     * Use IDeployableData.getSize() to get the raw value and
     *     IDeployableData.outputSize() to obtain it properly formatted.
     */
    private Size size;

    // Optional properties
    private ActivationType activation;
    private ActivationType deactivation;
    private ActivationType recall;
    private ActivationType redeploy;
    /**
     * The statblock associated with this IDeployableData (example unhelpful).
     * Can be any DeployableStatblock. Can be null.
     */
    private DeployableStatblock statblock;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private Counter[] counter;
    private DataTag[] tags;

    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech, int instances, int cost,
        // Conditionally required property
        Size size,
        // Optional properties
        ActivationType activation, ActivationType deactivation,
        ActivationType recall, ActivationType redeploy,
        DeployableStatblock statblock, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, Counter[] counter, DataTag[] tags
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setType(type);
        setDetail(detail);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setInstances(instances);
        // Conditionally required property
        setSize(size);
        // Optional properties
    }
    public IDeployableData(
        // Required properties
        String name, String type, String detail,
        // Semi-required properties
        TriState pilot, TriState mech, int instances, int cost,
        // Conditionally required property
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
        this(name, type, detail, TriState.UNSET, TriState.UNSET, -1, 0,
            null, null, null, null,
            null, null, null, null,
            null, null, null);
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
    // Conditionally required property
    // Optional properties
    public Size getSize() {
        return size;
    }
    public DeployableStatblock getStatblock() {
        return statblock;
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
    // Conditionally required property
    private void setSize(Size size) {
        if (isMine()) {
            if (size != null) {
                throw new IllegalArgumentException("size is not null");
            }
        } else {
            HelperMethods.checkObject("size", size);
        }
        this.size = size;
    }
    // Optional properties
    private void setStatblock(DeployableStatblock statblock) {
        this.statblock = statblock;
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
