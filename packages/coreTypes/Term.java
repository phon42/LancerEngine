package packages.CoreTypes;

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
    /**
     * The term's name (i.e. "Armor").
     * Can be any String except "". Cannot be null.
     */
    private String name;
    /**
     * The term's description (i.e. "A mech’s ARMOR reduces all incoming damage"
     *     " by that amount, excluding some special types of damage. ARMOR"
     *     " mostly depends on your mech’s FRAME, and never goes above four.").
     * Can be any String except "". Cannot be null.
     */
    private String description;

    public Term(String name, String description) {
        setName(name);
        setDescription(description);
    }
    public Term(Term term) {
        setName(term.name);
        setDescription(term.description);
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
