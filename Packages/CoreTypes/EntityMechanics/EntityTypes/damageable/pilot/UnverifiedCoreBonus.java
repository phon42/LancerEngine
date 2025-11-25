package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import java.util.NoSuchElementException;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.unverifiedCoreBonus.CoreBonus;

public class UnverifiedCoreBonus {
    // TODO: fill out
    // Required properties
    /**
     * The ID of this core bonus (i.e. "").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;
    protected String name;
    private String source;
    protected VueHTMLString effect;
    protected VueHTMLString description;

    // Optional properties
    protected String mountedEffect;
    protected IActionData[] actions;
    protected Bonus[] bonuses;
    protected ISynergyData[] synergies;
    protected IDeployableData[] deployables;
    protected Counter[] counters;
    protected String[] integrated;
    protected String[] specialEquipment;

    public UnverifiedCoreBonus(
        // Required properties
        String id, String name, String source, String effect,
        String description,
        // Optional properties
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        Counter[] counters, String[] integrated, String[] specialEquipment
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        // Optional properties
    }
    public UnverifiedCoreBonus(String id, String name, String source,
        String effect, String description) {
        this(id, name, source, effect, description, null,
            null, null, null, null,
            null, null, null);
    }

    // Required properties
    public String getID() {
        return id;
    }
    // Optional properties
    // Required properties
    protected void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Optional properties

    public CoreBonus toCoreBonus() {
        Manufacturer source;

        try {
            source = Database.getManufacturer(this.source);
        } catch (NoSuchElementException exception) {
            throw new IllegalStateException("Unable to convert this"
                + " UnverifiedCoreBonus with an UnverifiedCoreBonus.source"
                + " value of: \"" + this.source + "\"");
        }

        return new CoreBonus(this.id, this.name, source, this.mountedEffect,
            effect.getRawValue(), description.getRawValue(), this.actions,
            this.bonuses, this.synergies, this.deployables, this.counters,
            this.integrated, this.specialEquipment);
    }
}
