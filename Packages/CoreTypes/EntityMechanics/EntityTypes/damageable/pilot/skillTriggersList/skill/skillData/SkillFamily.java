package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.skill.skillData;

import MainBranch.HelperMethods;

/**
 * Yes, you can add methods into enum classes!
 * https://stackoverflow.com/questions/18883646/java-enum-methods-return-opposite-direction-enum
 * More information that might help if you have to debug or fix something:
 * https://stackoverflow.com/questions/26755755/how-to-call-enum-individual-methods
 */
public enum SkillFamily {
    STR,
    CON,
    DEX,
    INT,
    CHA;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
    public static SkillFamily fromString(String input) {
        HelperMethods.checkString("input", input);
        input = input.toUpperCase();

        return SkillFamily.valueOf(input);
    }
}
