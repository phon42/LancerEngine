package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.unverifiedPilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.deployable.iDeployableDataBase.Unverified.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.UnverifiedPilotEquipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotGear;

public class UnverifiedPilotGear extends UnverifiedPilotEquipment
    implements UnverifiedData<UnverifiedPilotGear, PilotGear> {
    public UnverifiedPilotGear(
        // PilotEquipment properties
        String id, String name, String description,
        UnverifiedDataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, UnverifiedIDeployableData[] deployables
    ) {
        super(id, name, "Gear", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public UnverifiedPilotGear(String id, String name, String type) {
        super(id, name, type);
    }

    @Override
    public Class<UnverifiedPilotGear> getUnverifiedType() {
        return UnverifiedPilotGear.class;
    }
    @Override
    public Class<PilotGear> getVerifiedType() {
        return PilotGear.class;
    }
    @Override
    public PilotGear verify() {
        DataTag[] dataTags = null;
        IDeployableData[] deployables = null;

        if (this.dataTags != null) {
            dataTags = HelperMethods.verifyArray(this.dataTags);
        }
        if (this.deployables != null) {
            deployables = HelperMethods.verifyArray(this.deployables);
        }

        return new PilotGear(this.id, this.name.toString(), this.description,
            dataTags, this.actions, this.bonuses, this.synergies, deployables);
    }
}
