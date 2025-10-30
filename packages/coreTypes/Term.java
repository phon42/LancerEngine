package packages.coreTypes;

import main.HelperMethods;

/**
 * From /lib/glossary.json.
 */
/**
 * Represents a single term from the glossary.json file of a .lcp file. Contains
 *     that term's name and description.
 * 
 * Requires a term name and description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Term {
    private String name;
    private String description;

    public Term(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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
