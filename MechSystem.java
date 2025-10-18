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

    /**
     * Creates a MechSystem given a system name.
     * @param systemName a String which cannot be null or "".
     * @param systemManufacturer a String which must be a valid manufacturer
     *     name as defined by Database.manufacturerList. Cannot be null.
     * @param systemLicense a License which can be any License. Cannot be null.
     */
    public MechSystem(String systemName, String systemManufacturer,
        License systemLicense) {
        super(systemName, systemManufacturer, systemLicense);
    }
    /**
     * Creates a MechSystem given a system name and array of system tags.
     * @param systemName a String which cannot be null or "".
     * @param systemManufacturer a String which must be a valid manufacturer
     *     name as defined by Database.manufacturerList. Cannot be null.
     * @param systemLicense a License which can be any License. Cannot be null.
     * @param systemTags a Tag[] which cannot be null, contain null elements,
     *     or contain elements with invalid Tag.name values, as defined by
     *     MechSystem.allowedNames.
     */
    public MechSystem(String systemName, String systemManufacturer,
        License systemLicense, Tag[] systemTags) {
        super(systemName, systemManufacturer, systemLicense);
        setTags(systemTags);
    }
    /**
     * Creates a deep copy of the provided MechSystem.
     * @param mechSystem a MechSystem to be copied.
     * @return a MechSystem deep copy of the provided MechSystem.
     */
    public MechSystem(MechSystem mechSystem) {
        this(mechSystem.name, mechSystem.manufacturer, mechSystem.license,
            mechSystem.tags);
    }

    /**
     * Sets this.tags to the provided value.
     * @param tags a Tag[] which cannot be null, contain null elements, or
     *     contain elements with invalid Tag.name values as defined by
     *     MechSystem.allowedNames.
     * @throws IllegalArgumentException if tags is null, contains a null
     *     element, or an element with an invalid Tag.name value, as defined by
     *     MechSystem.allowedNames.
     */
    @Override
    protected void setTags(Tag[] tags) {
        boolean isValid = false;

        // Throws an IllegalArgumentException if tags is null or contains null
        //     elements
        checkTagsArray(tags);
        for (Tag tag : tags) {
            isValid = false;
            for (String allowedTag : MechSystem.allowedNames) {
                if (tag.getName().equals(allowedTag)) {
                    isValid = true;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes an"
                    + " Tag with an invalid tag name for a MechSystem: \""
                    + tag.getName() + "\"");
            }
        }
        tags = HelperMethods.copyOf(tags);
        this.tags = tags;
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
     * @param outputType a String containing the type of output to
     *     generate.
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
            if (this.hasTag("Limited X")) {
                // add something like " x4"
                outputString += " x" + (getTag("Limited X")
                    + limitedSystemsBonus);
            }
        } else {
            return outputSystem(outputType);
        }

        return outputString;
    }
}