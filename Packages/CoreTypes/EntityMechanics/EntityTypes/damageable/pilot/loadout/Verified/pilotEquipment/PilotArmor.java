package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment;

import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.PilotEquipment;

/**
 * Represents a single piece of pilot armor. Contains several optional
 *     properties, and all of the inherited properties.
 * 
 * Requires a pilot armor id, name, and an array of data tags to be
 *     instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class PilotArmor extends PilotEquipment {
    public PilotArmor(
        // PilotEquipment properties
        String id, String name, String description, DataTag[] dataTags,
        IActionData[] actions, Bonus[] bonuses, ISynergyData[] synergies,
        IDeployableData[] deployables
    ) {
        super(id, name, "Armor", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public PilotArmor(String id, String name, String type) {
        super(id, name, type);
    }
}
