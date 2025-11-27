package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment;

import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.PilotEquipment;

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
    private PilotArmor(
        // PilotEquipment properties
        String id, VueHTMLString name, String type, String description,
        DataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables
    ) {
        super(id, name, type, description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    private PilotArmor(String id, VueHTMLString name, String type) {
        super(id, name, type);
    }
}
