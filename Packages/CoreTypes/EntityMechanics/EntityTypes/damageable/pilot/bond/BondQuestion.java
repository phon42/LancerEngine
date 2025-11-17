package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.bond;

import MainBranch.HelperMethods;

/**
 * Represents a single bond question. Contains information about the bond
 *     question's question and options.
 * 
 * Requires a bond question question and options to be instantiated.
 * 
 * Used in Bond.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class BondQuestion {
    /**
     * The bond question's question (i.e. "What gives you your powers?").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String question;
    /**
     * The bond's options to answer the question (i.e. a String[] containing "A"
     *     " high-tech stealth rig.", "A tattered patchwork of a cape stuffed"
     *     " with magician’s trinkets.", "Years of streetwise swindling.", "A"
     *     " deal you once made in a dream.")
     * Can be any String[] that is not of length 0 or contains null elements or
     *     elements that are "". Cannot be null.
     */
    private String[] options;

    public BondQuestion(String question, String[] options) {
        setQuestion(question);
        setOptions(options);
    }

    public String getQuestion() {
        return question;
    }
    public String[] getOptions() {
        return options;
    }
    private void setQuestion(String question) {
        HelperMethods.checkString("question", question);
        this.question = question;
    }
    private void setOptions(String[] options) {
        HelperMethods.checkStringArray("options", options);
        this.options = options;
    }
}
