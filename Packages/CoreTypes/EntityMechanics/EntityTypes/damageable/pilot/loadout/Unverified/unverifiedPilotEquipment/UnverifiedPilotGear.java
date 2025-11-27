package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.unverifiedPilotEquipment;

import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.UnverifiedPilotEquipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotGear;

public class UnverifiedPilotGear extends UnverifiedPilotEquipment {
    public UnverifiedPilotGear(
        // PilotEquipment properties
        String id, String name, String description,
        UnverifiedDataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables
    ) {
        super(id, name, "Gear", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public UnverifiedPilotGear(String id, String name, String type) {
        super(id, name, type);
    }

    public PilotGear toPilotGear() {
        DataTag[] dataTags = null;

        if (this.dataTags == null) {
            return new PilotGear(id, id, description, dataTags, actions,
                bonuses, synergies, deployables);
        }
        dataTags = new DataTag[this.dataTags.length];
        for (int i = 0; i < dataTags.length; i++) {
            dataTags[i] = this.dataTags[i].toDataTag();
        }

        return new PilotGear(id, id, description, dataTags, actions, bonuses,
            synergies, deployables);
    }
}
