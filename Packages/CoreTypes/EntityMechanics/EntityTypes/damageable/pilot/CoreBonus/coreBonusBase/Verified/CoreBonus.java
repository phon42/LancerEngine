package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.coreBonusBase.Verified;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter.counterBase.CounterData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable.deployable.iDeployableDataBase.Verified.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.CoreBonus.CoreBonusBase;

public class CoreBonus extends CoreBonusBase {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * The manufacturer of this core bonus (i.e. a Manufacturer representing
     *     GMS).
     * Can be any Manufacturer. Cannot be null.
     * Default value: a Manufacturer representing GMS.
     */
    private Manufacturer source;
    // sourceDefault removed because the relevant value only becomes accessible
    //     at runtime

    // Optional properties
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private IDeployableData[] deployables;
    private CounterData[] counters;

    /**
     * Verbose constructor for non-GMS content.
     * Accepts all possible properties.
     */
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, String[] integrated, String[] specialEquipment,
        // Semi-required property
        Manufacturer source,
        // Optional properties
        IActionData[] actions, Bonus[] bonuses, ISynergyData[] synergies,
        IDeployableData[] deployables, CounterData[] counters
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
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        String mountedEffect, String[] integrated, String[] specialEquipment,
        // Optional properties
        IActionData[] actions, Bonus[] bonuses, ISynergyData[] synergies,
        IDeployableData[] deployables, CounterData[] counters
    ) {
        this(id, name, effect, description, mountedEffect, integrated,
            specialEquipment, null, actions, bonuses, synergies,
            deployables, counters);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Accepts all required properties as well as the semi-required property
     *     CoreBonus.source.
     */
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description,
        // Semi-required property
        Manufacturer source
    ) {
        this(id, name, effect, description, null,
            null, null, source, null,
            null, null, null, null);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Accepts only CoreBonusBase's required properties.
     */
    public CoreBonus(
        // CoreBonusBase properties
        String id, String name, String effect, String description
    ) {
        this(id, name, effect, description, null,
            null, null, null, null,
            null, null, null, null);
    }

    // Semi-required property
    public Manufacturer getSource() {
        return source;
    }
    // Optional properties
    public IDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    // Semi-required property
    private void setSource(Manufacturer source) {
        if (source == null) {
            source = Database.getManufacturer("GMS");
        }
        this.source = source;
    }
    // Optional properties
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
}
