package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.talent.talentData;

import Packages.CoreTypes.Counter;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;

public class TalentRank {
    // Required properties
    private String name;
    private String description;

    // Optional properties
    private ISynergyData[] synergies;
    private IActionData[] actions;
    private Counter[] counters;
    private Bonus[] bonuses;
    private String[] integrated;
    private boolean exclusive;
    private String[] specialEquipment;

    public TalentRank(TalentRank talentRank) {}
}
