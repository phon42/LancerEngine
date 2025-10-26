package packages.coreTypes.entityMechanics.entityTypes.pilot;

/**
 * Represents a single reserve. Contains information about the reserve's id,
 *     name, type, label, and description, with other optional properties.
 * 
 * Requires a reserve id, name, label, and description to be created.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Reserve {
    private String id;
    private String name;
    private String type;
    private String label;
    private String description;
}
