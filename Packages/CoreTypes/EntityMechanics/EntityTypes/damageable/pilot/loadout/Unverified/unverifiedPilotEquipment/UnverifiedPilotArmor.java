package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.unverifiedPilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.UnverifiedPilotEquipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotArmor;

public class UnverifiedPilotArmor extends UnverifiedPilotEquipment
    implements UnverifiedData<UnverifiedPilotArmor, PilotArmor> {
    public UnverifiedPilotArmor(
        // PilotEquipment properties
        String id, String name, String description,
        UnverifiedDataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, UnverifiedIDeployableData[] deployables
    ) {
        super(id, name, "Armor", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public UnverifiedPilotArmor(String id, String name, String type) {
        super(id, name, type);
    }

    @Override
    public Class<UnverifiedPilotArmor> getUnverifiedType() {
        return UnverifiedPilotArmor.class;
    }
    @Override
    public Class<PilotArmor> getVerifiedType() {
        return PilotArmor.class;
    }
    @Override
    public PilotArmor verify() {
        DataTag[] dataTags = null;
        IDeployableData[] deployables = null;

        if (this.dataTags != null) {
            dataTags = HelperMethods.verifyArray(this.dataTags);
        }
        if (this.deployables != null) {
            deployables = HelperMethods.verifyArray(this.deployables);
        }

        return new PilotArmor(this.id, this.name.toString(), this.description,
            dataTags, this.actions, this.bonuses, this.synergies, deployables);
    }
}
