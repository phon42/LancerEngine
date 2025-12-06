package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.coreBonusBase.Unverified;

import java.util.NoSuchElementException;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.Counter.counterBase.CounterData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.Unverified.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.CoreBonusBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.coreBonusBase.Verified.CoreBonus;

public class UnverifiedCoreBonus extends CoreBonusBase
    implements UnverifiedData<UnverifiedCoreBonus, CoreBonus> {
    // Required property
    private String source;
    // Optional property
    private UnverifiedIDeployableData[] deployables;

    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, String[] integrated,
        String[] specialEquipment,
        // Required property
        String source,
        // Optional property
        UnverifiedIDeployableData[] deployables
    ) {
        // CoreBonusBase properties
        super(id, name, effect, description, mountedEffect, actions, bonuses,
            synergies, counters, integrated, specialEquipment);
        // Required property
        setSource(source);
        // Optional property
        setDeployables(deployables);
    }
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, String[] integrated,
        String[] specialEquipment,
        // Required property
        String source
    ) {
        this(id, name, effect, description, mountedEffect, actions, bonuses,
            synergies, counters, integrated, specialEquipment, source,
            null);
    }
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Required property
        String source,
        // Optional property
        UnverifiedIDeployableData[] deployables
    ) {
        this(id, name, effect, description, null, null,
            null, null, null, null,
            null, source, deployables);
    }
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Required property
        String source
    ) {
        this(id, name, effect, description, source, null);
    }

    // Required property
    public String getSource() {
        return source;
    }
    // Optional property
    public UnverifiedIDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    // Required property
    private void setSource(String source) {
        HelperMethods.checkString("source", source);
        this.source = source;
    }
    // Optional property
    private void setDeployables(UnverifiedIDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }

    @Override
    public Class<UnverifiedCoreBonus> getUnverifiedType() {
        return UnverifiedCoreBonus.class;
    }
    @Override
    public Class<CoreBonus> getVerifiedType() {
        return CoreBonus.class;
    }
    @Override
    public CoreBonus verify() {
        Manufacturer source;
        IDeployableData[] deployables = null;

        try {
            source = Database.getManufacturer(this.source);
        } catch (NoSuchElementException exception) {
            throw new IllegalStateException("Unable to convert this"
                + " UnverifiedCoreBonus with an UnverifiedCoreBonus.source"
                + " value of: \"" + this.source + "\"");
        }
        if (this.deployables != null) {
            deployables = HelperMethods.verifyArray(this.deployables);
        }

        return new CoreBonus(this.id, this.name, this.mountedEffect,
            effect.getRawValue(), description.getRawValue(), this.actions,
            this.bonuses, this.synergies, this.counters,
            this.integrated, this.specialEquipment, source, deployables);
    }
}
