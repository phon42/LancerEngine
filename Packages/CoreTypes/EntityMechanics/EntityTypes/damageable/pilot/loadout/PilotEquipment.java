package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout;

import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.dataTag.Tag;

/**
 * Represents a single piece of pilot equipment of any kind (be that armor,
 *     weapon, or gear). Contains information about that pilot equipment's id,
 *     name, type, data tags, and tags.
 * 
 * Cannot be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public class PilotEquipment {
    // TODO: fill out
    // Required properties
    protected String id;
    protected String name;
    protected String type;
    protected DataTag[] dataTags;
    protected Tag[] tags;

    // Optional properties
    /**
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    protected String description;
    /**
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    protected String effect;

    protected PilotEquipment(
        // Required properties
        String id, String name, String type, DataTag[] dataTags,
        // Optional properties
        String description, String effect
    ) {
        // Required properties
        setID(id);
        setName(name);
        setType(type);
        setDataTags(dataTags);
        setTags(DataTag.toTags(dataTags));
        // Optional properties
        setDescription(description);
        setEffect(effect);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public DataTag[] getDataTags() {
        return dataTags;
    }
    public Tag[] getTags() {
        return tags;
    }
    // Optional properties
    public String getDescription() {
        return description;
    }
    public String getEffect() {
        return effect;
    }
    // Required properties
    protected void setID(String id) {
        this.id = id;
    }
    protected void setName(String name) {
        this.name = name;
    }
    protected void setType(String type) {
        this.type = type;
    }
    protected void setDataTags(DataTag[] dataTags) {
        this.dataTags = dataTags;
    }
    protected void setTags(Tag[] tags) {
        this.tags = tags;
    }
    // Optional properties
    private void setDescription(String description) {
        this.description = description;
    }
    private void setEffect(String effect) {
        this.effect = effect;
    }
}
