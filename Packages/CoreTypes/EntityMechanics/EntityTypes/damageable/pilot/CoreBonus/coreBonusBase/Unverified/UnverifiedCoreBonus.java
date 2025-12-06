package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.coreBonusBase.Unverified;

import java.util.NoSuchElementException;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.Counter.counterBase.CounterData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.Actions.Unverified.unverifiedActionBase.UnverifiedIActionData;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.Unverified.UnverifiedIDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.CoreBonusBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.coreBonusBase.Verified.CoreBonus;

public class UnverifiedCoreBonus extends CoreBonusBase
    implements UnverifiedData<UnverifiedCoreBonus, CoreBonus> {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * The manufacturer of this core bonus as a String (i.e. "GMS").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in uppercase.
     * Default value: "GMS".
     */
    private String source;
    private static final String sourceDefault = "GMS";

    // Optional properties
    private UnverifiedIActionData[] actions;
    private UnverifiedBonus[] bonuses;
    private UnverifiedISynergyData[] synergies;
    private UnverifiedIDeployableData[] deployables;
    private UnverifiedCounterData[] counters;

    /**
     * Verbose constructor for non-GMS content.
     * Accepts all possible properties.
     */
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, String[] integrated, String[] specialEquipment,
        // Semi-required property
        String source,
        // Optional properties
        UnverifiedIActionData[] actions, UnverifiedBonus[] bonuses,
        UnverifiedISynergyData[] synergies,
        UnverifiedIDeployableData[] deployables,
        UnverifiedCounterData[] counters
    ) {
        // CoreBonusBase properties
        super(id, name, effect, description, mountedEffect, integrated,
            specialEquipment);
        // Semi-required property
        setSource(source);
        // Optional properties
        setDeployables(deployables);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Accepts all other properties.
     */
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, String[] integrated, String[] specialEquipment,
        // Optional properties
        UnverifiedIActionData[] actions, UnverifiedBonus[] bonuses,
        UnverifiedISynergyData[] synergies,
        UnverifiedIDeployableData[] deployables,
        UnverifiedCounterData[] counters
    ) {
        // CoreBonusBase properties
        super(id, name, effect, description, mountedEffect, integrated,
            specialEquipment);
        // Semi-required property
        setSource(source);
        // Optional properties
        setDeployables(deployables);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Accepts all required properties as well as the semi-required property
     *     CoreBonus.source.
     */
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Semi-required property
        String source
    ) {
        this(id, name, effect, description, null,
            null, null, source, null,
            null, null, null, null);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Accepts only CoreBonusBase's required properties.
     */
    public UnverifiedCoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description
    ) {
        this(id, name, effect, description, null,
            null, null, null, null,
            null, null, null, null);
    }

    // Semi-required property
    public String getSource() {
        return source;
    }
    // Optional properties
    public UnverifiedIDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    // Semi-required property
    private void setSource(String source) {
        if (source == null) {
            source = UnverifiedCoreBonus.sourceDefault;
        } else {
            HelperMethods.checkString("source", source);
            source = source.toUpperCase();
        }
        this.source = source;
    }
    // Optional properties
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
