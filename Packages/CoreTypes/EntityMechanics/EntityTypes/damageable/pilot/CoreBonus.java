package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Counter;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;

public class CoreBonus {
    // TODO: fill out
    // Required properties
    /**
     * The ID of this core bonus (i.e. "").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    private String name;
    private Manufacturer source;
    private VueHTMLString effect;
    private VueHTMLString description;

    // Optional properties
    private String mountedEffect;
    private IActionData[] actions;
    private IBonusData[] bonuses;
    private ISynergyData[] synergies;
    private IDeployableData[] deployables;
    private Counter[] counters;
    private String[] integrated;
    private String[] specialEquipment;

    public CoreBonus(
        // Required properties
        String id, String name, Manufacturer source, String effect,
        String description,
        // Optional properties
        String mountedEffect, IActionData[] actions, IBonusData[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        Counter[] counters, String[] integrated, String[] specialEquipment
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        // Optional properties
    }
    public CoreBonus(String id, String name, Manufacturer source, String effect,
        String description) {
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
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Optional properties
}
