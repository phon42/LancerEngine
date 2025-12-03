package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.unverifiedPilotEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Unverified.UnverifiedPilotEquipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;

public class UnverifiedPilotWeapon extends UnverifiedPilotEquipment
    implements UnverifiedData<UnverifiedPilotWeapon, PilotWeapon> {
    // Optional properties
    private String effect;
    /**
     * Can be any RangeTag[] that does not contain null elements. Cannot be
     *     null.
     */
    private RangeTag[] range;
    /**
     * Can be any Harm[] that does not contain null elements. Cannot be null.
     */
    private Harm[] damage;

    public UnverifiedPilotWeapon(
        // PilotEquipment properties
        String id, String name, String description,
        UnverifiedDataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, UnverifiedIDeployableData[] deployables,
        // Optional properties
        String effect, RangeTag[] range, Harm[] damage
    ) {
        super(id, name, "Weapon", description, dataTags, actions, bonuses,
            synergies, deployables);
        setEffect(effect);
        setRange(range);
        setDamage(damage);
    }
    public UnverifiedPilotWeapon(
        // PilotEquipment properties
        String id, String name,
        // Optional properties
        String effect, RangeTag[] range, Harm[] damage
    ) {
        this(id, name, null, null, null,
            null, null, null, effect, range,
            damage);
    }
    public UnverifiedPilotWeapon(
        // PilotEquipment properties
        String id, String name, String description,
        UnverifiedDataTag[] dataTags, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, UnverifiedIDeployableData[] deployables
    ) {
        super(id, name, "Weapon", description, dataTags, actions, bonuses,
            synergies, deployables);
    }
    public UnverifiedPilotWeapon(String id, String name) {
        super(id, name, "Weapon");
    }

    // Optional properties
    public String getEffect() {
        return effect;
    }
    public RangeTag[] getRange() {
        return HelperMethods.copyOf(range);
    }
    public Harm[] getDamage() {
        return HelperMethods.copyOf(damage);
    }
    // Optional properties
    private void setEffect(String effect) {
        if (effect != null) {
            HelperMethods.checkString("effect", effect);
        }
        this.effect = effect;
    }
    private void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    private void setDamage(Harm[] damage) {
        HelperMethods.checkObjectArray("damage", damage);
        damage = HelperMethods.copyOf(damage);
        this.damage = damage;
    }

    @Override
    public Class<UnverifiedPilotWeapon> getUnverifiedType() {
        return UnverifiedPilotWeapon.class;
    }
    @Override
    public Class<PilotWeapon> getVerifiedType() {
        return PilotWeapon.class;
    }
    @Override
    public PilotWeapon verify() {
        DataTag[] dataTags = null;
        IDeployableData[] deployables = null;

        if (this.dataTags != null) {
            dataTags = HelperMethods.verifyArray(this.dataTags);
        }
        if (this.deployables != null) {
            deployables = HelperMethods.verifyArray(this.deployables);
        }

        return new PilotWeapon(this.id, this.name.toString(), this.description,
            dataTags, this.actions, this.bonuses, this.synergies, deployables);
    }
}
