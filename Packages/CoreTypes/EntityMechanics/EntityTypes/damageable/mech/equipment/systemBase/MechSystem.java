package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.SystemBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.counterBase.CounterData;
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
public final class MechSystem extends SystemBase {
    // Optional properties
    private VueHTMLString description;
    private VueHTMLString effect;
    private IActionData[] actions;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private IDeployableData[] deployables;
    private CounterData[] counters;
    private String[] integrated;
    private String[] specialEquipment;

    // Reference properties
    /**
     * Contains an array of allowed values for MechSystem.tags' Tag.name values.
     *     Case-sensitive.
     */
    public static final String[] allowedNames = new String[] {"AI",
        "Unique", "Limited X", "Grenade", "Mine", "Quick Action", "Deployable",
        "Drone", "Shield", "Heat X (Self)", "Protocol", "Overshield",
        "Full Action", "Quick Tech", "Invade", "Reaction", "Full Tech",
        "X/Round", "Danger Zone", "X/Turn"};

    /**
     * Verbose constructor for non-GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     */
    public MechSystem(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags, int spCost
    ) {
        // SystemBase properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, null, spCost);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     */
    public MechSystem(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags, int spCost
    ) {
        // SystemBase properties
        super(id, name, licenseID, licenseName, description, dataTags,
            null, spCost);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public MechSystem(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, -1);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public MechSystem(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags
    ) {
        this(id, name, licenseID, licenseName, description, dataTags, -1);
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
        String dataTagName;

        HelperMethods.checkObjectArray("New data tags", dataTags);
        for (DataTag dataTag : dataTags) {
            isValid = false;
            dataTagName = dataTag.getData().getName();
            for (String allowedDataTag : MechSystem.allowedNames) {
                if (dataTagName.equals(allowedDataTag)) {
                    isValid = true;
                    break;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New data tags array"
                    + " includes a DataTag with an invalid data tag name for a"
                    + " MechSystem: \"" + dataTagName + "\"");
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
        int limitedVal;

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            // output something like "Pattern-A Smoke Charges x4"
            // start with something like "Pattern-A Smoke Charges"
            outputString += this.name;
            if (this.hasDataTag("Limited X")) {
                // add something like " x4"
                limitedVal = (Integer) getDataTag("Limited X");
                outputString += " x" + (limitedVal + limitedSystemsBonus);
            }
        } else {
            return outputSystem(outputType);
        }

        return outputString;
    }
}