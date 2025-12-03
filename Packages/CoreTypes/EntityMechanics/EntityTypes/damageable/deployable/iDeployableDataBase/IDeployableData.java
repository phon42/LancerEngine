package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableDataBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.iDeployableData.DeployableStatblock;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.counterBase.CounterData;

/**
 * See pgs. 58 and 68.
 */
public class IDeployableData extends IDeployableDataBase {
    // Optional property
    /**
     * Can be any DataTag[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private DataTag[] tags;

    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size,
        DeployableStatblock statblock, ActivationType deactivation,
        ActivationType recall, ActivationType redeploy, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies, CounterData[] counters,
        // Optional property
        DataTag[] tags
    ) {
        super(name, type, detail, activation, instances, cost, pilot, mech,
            size, statblock, deactivation, recall, redeploy, actions, bonuses,
            synergies, counters);
        // Optional property
        setTags(tags);
    }
    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size,
        DeployableStatblock statblock, ActivationType deactivation,
        ActivationType recall, ActivationType redeploy, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies, CounterData[] counters
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech,
            size, statblock, deactivation, recall, redeploy, actions, bonuses,
            synergies, counters, null);
    }
    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size,
        DeployableStatblock statblock
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech, size,
            statblock, null, null, null,
            null, null, null, null,
            null);
    }
    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech, size,
            null, null, null, null,
            null, null, null, null,
            null);
    }
    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public IDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail
    ) {
        this(name, type, detail, null,
            IDeployableDataBase.instancesDefault,
            IDeployableDataBase.costDefault,
            TriState.UNSET, TriState.UNSET, null, null,
            null, null, null, null,
            null, null, null, null);
    }
    public IDeployableData(Size size, int armor) {
        super(size, armor);
    }
    public IDeployableData() {
        super();
    }

    // Optional property
    public DataTag[] getTags() {
        if (tags != null) {
            return HelperMethods.copyOf(tags);
        }

        return tags;
    }
    // Optional property
    private void setTags(DataTag[] tags) {
        HelperMethods.checkObjectArrayAlt("tags", tags);
        if (tags != null) {
            tags = HelperMethods.copyOf(tags);
        }
        this.tags = tags;
    }
}
