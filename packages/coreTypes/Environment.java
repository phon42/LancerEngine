package packages.CoreTypes;

import main.HelperMethods;

/**
 * Represents a specified preset environment for a battle to take place in, such
 *     as Extreme Cold. Contains information about the environment's id, name,
 *     and description.
 * 
 * Requires an environment id, name, and description to be created.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Environment {
    /**
     * The environment's id (i.e. "env_dangerousflorafauna").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The environment's name (i.e. "Dangerous Flora or Fauna").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The environment's name (too large to provide an example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    public Environment(String id, String name, String description) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setDescription(description);
    }
    public Environment(Environment environment) {
        setID(environment.id);
        setName(environment.name);
        setDescription(environment.description);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
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
