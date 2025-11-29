package Packages.CoreTypes.EntityMechanics.NPCs;

/**
 * Represents a single NPC feature. Contains information about the feature's
 *     origin, whether it is locked, its type, effect, and several optional
 *     properties.
 * 
 * Requires an NPC feature id, name, origin, locked boolean, type, and effect to
 *     be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class NPCFeature extends NPCBase {
    // TODO: fill out
    // Required properties
    private Object origin;
    private boolean locked;
    private String type;
    private String effect;

    public NPCFeature() {}
}
