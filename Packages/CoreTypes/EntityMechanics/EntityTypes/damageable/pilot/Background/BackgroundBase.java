package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Background;

import MainBranch.HelperMethods;

/**
 * From https://github.com/massif-press/lancer-data/blob/master/README.md#backgrounds-backgroundsjson.
 */
/**
 * Represents a single background. Contains information about that background's
 *     id, name, description, and an array of SkillDatas that might be
 *     applicable.
 * 
 * Requires a background id, name, and description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class BackgroundBase {
    // Required properties
    /**
     * The ID of this background (i.e. "pbg_celebrity").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of this background (i.e. "Celebrity").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The description of this background (too large to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    public BackgroundBase(String id, String name, String description) {
        // Required properties
        setID(id);
        setName(name);
        setDescription(description);
    }
    public BackgroundBase(BackgroundBase background) {
        // Required properties
        setID(background.id);
        setName(background.name);
        setDescription(background.description);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
}
