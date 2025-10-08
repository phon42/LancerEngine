/**
 * Contains a single system for a mech. Stores information about that system
 *     such as its name and its tags.
 * Safety: This class has placeholder values but cannot be a placeholder. None
 *     of its properties have allowed values of null.
 */
public class MechSystem extends Equipment {
    public MechSystem(String name) {
        super(name);
    }
    public MechSystem(String name, EquipmentTag[] equipmentTags) {
        super(name, equipmentTags);
    }

    @Override
    public void setTags(EquipmentTag[] tags) {
        boolean isValid = false;

        if (tags == null) {
            throw new IllegalArgumentException("New tags value is null");
        }
        for (EquipmentTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("New tags value includes a"
                    + " null value");
            }
            isValid = false;
            for (String allowedTag : EquipmentTag.allowedSystemNames) {
                if (tag.getName().equals(allowedTag)) {
                    isValid = true;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes an"
                    + " invalid tag name for a MechSystem: \"" + tag.getName()
                    + "\"");
            }
        }
        tags = HelperFunctions.copyOf(tags);
        this.tags = tags;
    }

    @Override
    /**
     * Returns a deep copy of this object.
     * @return a MechSystem deep copy of this object.
     */
    public MechSystem copyOf() {
        MechSystem copy = new MechSystem(this.name, this.tags);

        return copy;
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
                + " MechSystem.generateOutput(\"mech build\") but limited"
                + " systems bonus value was not provided.");
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
            // output something like "Pattern-A Smoke Charges"
            outputString += this.name;
            if (this.hasTag("Limited X")) {
                // add something like " x4"
                outputString += " x" + (this.getTag("Limited X")
                    + limitedSystemsBonus);
            }
        } else {
            return outputSystem(outputType);
        }

        return outputString;
    }
}