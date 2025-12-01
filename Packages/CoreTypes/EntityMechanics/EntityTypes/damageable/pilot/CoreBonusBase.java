package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.counterBase.CounterData;

public class CoreBonusBase {
    // TODO: fill out
    // Required properties
    /**
     * The ID of this core bonus (i.e. "").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String id;
    protected String name;
    protected VueHTMLString effect;
    protected VueHTMLString description;

    // Optional properties
    protected String mountedEffect;
    protected IActionData[] actions;
    protected Bonus[] bonuses;
    protected ISynergyData[] synergies;
    protected CounterData[] counters;
    protected String[] integrated;
    protected String[] specialEquipment;

    public CoreBonusBase(
        // Required properties
        String id, String name, String effect, String description,
        // Optional properties
        String mountedEffect, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, CounterData[] counters, String[] integrated,
        String[] specialEquipment
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        // Optional properties
    }
    public CoreBonusBase(
        // Required properties
        String id, String name, String effect, String description
    ) {
        this(id, name, effect, description, null, null,
            null, null, null, null,
            null);
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
}
