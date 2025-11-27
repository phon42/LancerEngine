package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Deployable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTagUnverified.dataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.ISynergyData;

/**
 * Represents a single system for a mech. Contains information about that system
 *     such as its name and tags.
 * 
 * Requires a system name to be instantiated.
 * 
 * Used in Mech.
 * 
 * Safety: This class has placeholder values but cannot be a placeholder. At
 *     least one of its properties has an allowed value of null.
 */
public final class MechSystem extends Equipment {
    /**
     * Contains an array of allowed values for MechSystem.tags' Tag.name values.
     *     Case-sensitive.
     */
    public static final String[] allowedNames = new String[] {"AI",
        "Unique", "Limited X", "Grenade", "Mine", "Quick Action", "Deployable",
        "Drone", "Shield", "Heat X (Self)", "Protocol", "Overshield",
        "Full Action", "Quick Tech", "Invade", "Reaction", "Full Tech",
        "X/Round", "Danger Zone", "X/Turn"};
    // Required properties
    private int spCost;
    // Optional properties
    private String effect;
    private IActionData[] actions;
    private Deployable[] deployables;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;

    /**
     * Verbose constructor for non-GMS content.
     */
    public MechSystem(String id, String name, Manufacturer manufacturer,
        String licenseID, String licenseName, int licenseLevel,
        String description, DataTag[] dataTags, int spCost,
        String effect, IActionData[] actions, Deployable[] deployables,
        Bonus[] bonuses, ISynergyData[] synergies) {
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags);
        // Required properties
        setSpCost(spCost);
        // Optional properties
        setEffect(effect);
        setActions(actions);
        setDeployables(deployables);
        setBonuses(bonuses);
        setSynergy(synergies);
    }
    /**
     * Abbreviated constructor for GMS content.
     */
    public MechSystem(String id, String name, String licenseID,
        String licenseName, String description, DataTag[] dataTags, int spCost,
        String effect, IActionData[] actions, Deployable[] deployables,
        Bonus[] bonuses, ISynergyData[] synergies) {
        super(id, name, licenseID, licenseName, description, dataTags);
        // Required properties
        setSpCost(spCost);
        // Optional properties
        setEffect(effect);
        setActions(actions);
        setDeployables(deployables);
        setBonuses(bonuses);
        setSynergy(synergies);
    }
    /**
     * Creates a deep copy of the provided MechSystem.
     * @param mechSystem a MechSystem to be copied.
     * @return a MechSystem deep copy of the provided MechSystem.
     */
    public MechSystem(MechSystem mechSystem) {
        super(mechSystem);
        // Required properties
        setSpCost(mechSystem.spCost);
        // Optional properties
        setEffect(mechSystem.effect);
        setActions(mechSystem.actions);
        setDeployables(mechSystem.deployables);
        setBonuses(mechSystem.bonuses);
        setSynergy(mechSystem.synergies);
    }

    /**
     * Sets this.dataTags to the provided value.
     * @param dataTags a DataTag[] which cannot be null, contain null elements,
     *     or contain elements with invalid DataTag.name values as defined by
     *     MechSystem.allowedNames.
     * @throws IllegalArgumentException if dataTags is null, contains a null
     *     element, or an element with an invalid DataTag.name value, as defined
     *     by MechSystem.allowedNames.
     */
    @Override
    protected void setDataTags(DataTag[] dataTags) {
        boolean isValid;

        HelperMethods.checkObjectArray("New data tags", dataTags);
        for (DataTag dataTag : dataTags) {
            isValid = false;
            for (String allowedDataTag : MechSystem.allowedNames) {
                if (dataTag.getName().equals(allowedDataTag)) {
                    isValid = true;
                    break;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New data tags array"
                    + " includes a DataTag with an invalid data tag name for a"
                    + " MechSystem: \"" + dataTag.getName() + "\"");
            }
        }
        dataTags = HelperMethods.copyOf(dataTags);
        this.dataTags = dataTags;
    }

    /**
     * A helper method which generates a snippet of text containing output about
     *     a given system of this Mech object used in Mech.outputSystems(). Only
     *     returns something other than "" when outputType is "full".
     * @param outputType a String containing the type of output to
     *     generate.
     * @return a String containing the requested output.
     * @throws IllegalArgumentException when outputType is "mech build" because
     *     that output type requires additional information only obtainable
     *     through MechSystem.outputSystem(String, int)
     */
    public String outputSystem(String outputType) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            throw new IllegalArgumentException("Called"
                + " MechSystem.outputSystem(\"mech build\") but limited systems"
                + " bonus value was not provided.");
        } else if (outputType.equals("full")) {
            // output something like "Pattern-A Smoke Charges"
            outputString += this.name;
        }

        return outputString;
    }
    /**
     * A helper method which generates a snippet of text containing output about
     *     a given system of this Mech object used in Mech.outputSystems(). Only
     *     returns something other than "" when outputType is "mech build" or
     *     "full".
     * @param outputType a String containing the type of output to generate.
     * @param limitedSystemsBonus an int containing the limited systems bonus of
     *     the Mech calling this method.
     * @return a String containing the requested output.
     */
    public String outputSystem(String outputType, int limitedSystemsBonus) {
        String outputString = "";

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            // output something like "Pattern-A Smoke Charges x4"
            // start with something like "Pattern-A Smoke Charges"
            outputString += this.name;
            if (this.hasDataTag("Limited X")) {
                // add something like " x4"
                outputString += " x" + (getDataTag("Limited X")
                    + limitedSystemsBonus);
            }
        } else {
            return outputSystem(outputType);
        }

        return outputString;
    }
}