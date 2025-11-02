package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout;

import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.dataTag.Tag;

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
    protected String id;
    protected String name;
    protected String type;
    protected DataTag[] dataTags;
    protected Tag[] tags;
    /**
     * Optional
     * Case-sensitive.
     */
    protected String description;
    /**
     * Optional
     * Case-sensitive.
     */
    protected String effect;

    protected PilotEquipment(String id, String name, String type,
        DataTag[] dataTags, String description, String effect) {
        setID(id);
        setName(name);
        setType(type);
        setDataTags(dataTags);
        setTags(DataTag.toTags(dataTags));
        setDescription(description);
        setEffect(effect);
    }

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
    public String getDescription() {
        return description;
    }
    public String getEffect() {
        return effect;
    }
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
    private void setDescription(String description) {
        this.description = description;
    }
    private void setEffect(String effect) {
        this.effect = effect;
    }
}
