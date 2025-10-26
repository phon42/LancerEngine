package packages.coreTypes.entityMechanics.entityTypes.pilot;

import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;

/**
 * Represents a single background. Contains information about that background's
 *     id, name, description, and an array of Skills that might be applicable.
 * 
 * Requires a background id, name, description, and an array of Skills that
 *     might be applicable to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Background {
    private String id;
    private String name;
    private String description;
    private Skill[] skills;
}
