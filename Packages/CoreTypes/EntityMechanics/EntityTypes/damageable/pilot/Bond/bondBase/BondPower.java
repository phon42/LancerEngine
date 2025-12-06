package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Bond.bondBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.Frequency;

/**
 * Represents a single bond power. Contains information about the bond power's
 *     name and description.
 * 
 * Requires a bond power name and description to be instantiated.
 * 
 * Used in Bond.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class BondPower {
    // Required properties
    /**
     * This bond power's name (i.e. "Masquerade").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * This bond power's description (i.e. "You can always tell if someone is"
     *     " lying, though you don’t know the exact nature or extent of their"
     *     " lie.").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) property
    /**
     * Unknown purpose.
     * Default value: false.
     */
    private boolean master;
    /**
     * Default value for BondPower.master.
     */
    private static final boolean masterDefault = false;
    /**
     * Unknown purpose.
     * Default value: false.
     */
    private boolean veteran;
    /**
     * Default value for BondPower.veteran.
     */
    private static final boolean veteranDefault = false;

    // Optional properties
    /**
     * The prerequisite for taking this bond power (i.e. "If you have two or"
     *     " more powers from this bond, you can choose a power from any other"
     *     " bond instead of one from this bond when you would gain a power."
     *     " You can do this twice. If you have at least one veteran power,"
     *     " gain the Boon of Chaos.")
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String prerequisite;
    /**
     * The frequency with which this bond power can be used (i.e. a Frequency
     *     representing 2/session).
     * Can be any Frequency. Can be null.
     */
    private Frequency frequency;

    public BondPower(String name, String description, TriState master,
        TriState veteran, String prerequisite, Frequency frequency) {
        // Required properties
        setName(name);
        setDescription(description);
        // Semi-required properties
        setMaster(master);
        setVeteran(veteran);
        // Optional properties
        setPrerequisite(prerequisite);
        setFrequency(frequency);
    }
    public BondPower(String name, String description) {
        this(name, description, TriState.UNSET, TriState.UNSET,
            null, null);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Semi-required properties
    public boolean isMaster() {
        return master;
    }
    public boolean isVeteran() {
        return veteran;
    }
    // Optional properties
    public String getPrerequisite() {
        return prerequisite;
    }
    public Frequency getFrequency() {
        return frequency;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    // Semi-required properties
    private void setMaster(TriState master) {
        if (master == TriState.UNSET) {
            this.master = BondPower.masterDefault;
        }
        this.master = master.toBoolean();
    }
    private void setVeteran(TriState veteran) {
        if (veteran == TriState.UNSET) {
            this.veteran = BondPower.veteranDefault;
        }
        this.veteran = veteran.toBoolean();
    }
    // Optional properties
    private void setPrerequisite(String prerequisite) {
        if (prerequisite != null) {
            HelperMethods.checkString("prerequisite",
                prerequisite);
        }
        this.prerequisite = prerequisite;
    }
    private void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
}
