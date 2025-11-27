package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.Size;
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
    // TODO: work these properties out
    private boolean availableToPilots;
    private boolean availableToMechs;

    // Semi-required property
    private int instances;
    private static final int instancesDefault = 1;

    // Conditionally required property
    /**
     * The deployable's size.
     * Required when this.type is not "Mine".
     * When required:
     *     Can be any Size. Cannot be null.
     * When not required:
     *     Must be null.
     * Use IDeployableData.getSize() to get the raw value and
     *     IDeployableData.outputSize() to obtain it properly formatted.
     */
    private Size size;

    // Optional properties
    private ActivationType activation;
    private ActivationType deactivation;
    private ActivationType recall;
    private ActivationType redeploy;
    private int cost;
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

    public IDeployableData() {
        this(new Size(2), 0);
    }
    public IDeployableData(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setStatblock(new DeployableStatblock(size, armor));
    }
    public IDeployableData(String name, String type, String detail,
        boolean availToPilots, boolean availToMechs) {
        setName(name);
        setType(type);
        setDetail(detail);
        setAvailableToPilots(availToPilots);
        setAvailableToMechs(availToMechs);
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
    public boolean isAvailableToPilots() {
        return availableToPilots;
    }
    public boolean isAvailableToMechs() {
        return availableToMechs;
    }
    // Semi-required property
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
    private void setAvailableToPilots(boolean availableToPilots) {
        this.availableToPilots = availableToPilots;
    }
    private void setAvailableToMechs(boolean availableToMechs) {
        this.availableToMechs = availableToMechs;
    }
    // Semi-required property
    // Conditionally required property
    // Optional properties
    /**
     * Sets this.size to the provided value.
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        this.size = size;
    }
    private void setStatblock(DeployableStatblock statblock) {
        this.statblock = statblock;
    }

    /**
     * A helper method which outputs the deployable's size, formatted properly
     *     so that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        return size.output();
    }
    public String outputType() {
        return HelperMethods.capitalizeFirst(type);
    }
}
