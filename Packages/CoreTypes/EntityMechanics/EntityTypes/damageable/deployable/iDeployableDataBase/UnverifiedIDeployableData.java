package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableDataBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.iDeployableData.DeployableStatblock;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.counterBase.CounterData;

/**
 * See pgs. 58 and 68.
 */
public class UnverifiedIDeployableData extends IDeployableDataBase
    implements UnverifiedData<UnverifiedIDeployableData, IDeployableData> {
    // Optional property
    /**
     * Can be any UnverifiedDataTag[] that is not of length 0 and does not
     *     contain null elements. Can be null.
     */
    private UnverifiedDataTag[] tags;

    public UnverifiedIDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size,
        DeployableStatblock statblock, ActivationType deactivation,
        ActivationType recall, ActivationType redeploy, IActionData[] actions,
        Bonus[] bonuses, ISynergyData[] synergies, CounterData[] counters,
        // Optional property
        UnverifiedDataTag[] tags
    ) {
        super(name, type, detail, activation, instances, cost, pilot, mech,
            size, statblock, deactivation, recall, redeploy, actions, bonuses,
            synergies, counters);
        // Optional property
        setTags(tags);
    }
    public UnverifiedIDeployableData(
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
    public UnverifiedIDeployableData(
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
    public UnverifiedIDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech, Size size
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech, size,
            null, null, null, null,
            null, null, null, null,
            null);
    }
    public UnverifiedIDeployableData(
        // IDeployableDataBase properties
        String name, String type, String detail, ActivationType activation,
        int instances, int cost, TriState pilot, TriState mech
    ) {
        this(name, type, detail, activation, instances, cost, pilot, mech,
            null, null, null, null,
            null, null, null, null,
            null, null);
    }
    public UnverifiedIDeployableData(
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
    public UnverifiedIDeployableData(Size size, int armor) {
        super(size, armor);
    }
    public UnverifiedIDeployableData() {
        super();
    }

    // Optional property
    public UnverifiedDataTag[] getTags() {
        if (tags != null) {
            return HelperMethods.copyOf(tags);
        }

        return tags;
    }
    // Optional property
    private void setTags(UnverifiedDataTag[] tags) {
        HelperMethods.checkObjectArrayAlt("tags", tags);
        if (tags != null) {
            tags = HelperMethods.copyOf(tags);
        }
        this.tags = tags;
    }

    public Class<UnverifiedIDeployableData> getUnverifiedType() {
        return UnverifiedIDeployableData.class;
    }
    public Class<IDeployableData> getVerifiedType() {
        return IDeployableData.class;
    }
    public IDeployableData verify() {
        DataTag[] tags = null;

        if (this.tags != null) {
            tags = HelperMethods.verifyArray(this.tags);
            tags = new DataTag[this.tags.length];
            for (int i = 0; i < this.tags.length; i++) {
                tags[i] = this.tags[i].verify();
            }
        }

        return new IDeployableData(this.name, this.type, this.detail,
            this.activation, this.instances, this.cost,
            TriState.toTriState(this.pilot), TriState.toTriState(this.mech),
            this.size, this.statblock, this.deactivation, this.recall,
            this.redeploy, this.actions, this.bonuses, this.synergies,
            this.counters, tags);
    }
}
