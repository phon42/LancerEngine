package packages.coreTypes.entityMechanics;

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
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class NPCFeature {
    // TODO: fill out
    private String id;
    private String name;
    private Object origin;
    private boolean locked;
    private String type;
    private String effect;
}
