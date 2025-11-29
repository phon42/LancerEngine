package Packages.CoreTypes.EntityMechanics.NPCs.npcBase;

import Packages.CoreTypes.EntityMechanics.NPCs.NPCBase;

/**
 * Represents a single NPC template. Contains information about its features and
 *     power.
 * 
 * Requires an npc template id, name, description, base features array, optional
 *     features array, and power to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class NPCTemplate extends NPCBase {
    // TODO: fill out
    // Required properties
    private String description;
    private String[] baseFeatures;
    private String[] optionalFeatures;
    private int power;

    public NPCTemplate() {}
}
