package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.LicenseContent;

/**
 * Represents a piece of mech equipment of any kind, such as a mech system or a
 *     mech weapon. Contains information about the equipment's name, origin, and
 *     data tags.
 * 
 * Requires an equipment name to be instantiated.
 * 
 * Used in and extended by MechSystem and Weapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Equipment extends LicenseContent {
    // TODO: add some way to add actions for equipments that provide them (i.e
    //     GMS Pattern-A Jericho Deployable Cover and its Deploy Jericho
    //     Deployable Cover action for MechSystem, or IPS-N D/D 288 and its
    //     Charge D/D 288 action for Weapon)
    // TODO: add some way for DataTags to be automatically added based on an
    //     equipment's contents
    /**
     * Contains an array of all of this equipment's data tags (i.e. DataTag
     *     elements representing an "AI" or "Limited X" data tag).
     * Can be any DataTag[]. Cannot be null or contain null elements.
     */
    protected DataTag[] dataTags;

    /**
     * Verbose constructor for non-GMS content.
     */
    protected Equipment(String id, String name, Manufacturer manufacturer,
        String licenseID, String licenseName, int licenseLevel,
        String description, DataTag[] dataTags) {
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description);
        setDataTags(dataTags);
    }
    /**
     * Abbreviated constructor for GMS content.
     */
    protected Equipment(String id, String name, String licenseID,
        String licenseName, String description, DataTag[] dataTags) {
        super(id, name, licenseID, licenseName, description);
        setDataTags(dataTags);
    }
    protected Equipment(Equipment equipment) {
        super(equipment);
        setDataTags(equipment.dataTags);
    }

    /**
     * Is overridden in all of Equipment's children.
     * @throws IllegalArgumentException if dataTags is null or includes a null
     *     element.
     */
    protected void setDataTags(DataTag[] dataTags) {
        // This will throw an exception if tags is invalid
        checkDataTagsArray(dataTags);
        dataTags = HelperMethods.copyOf(dataTags);
        this.dataTags = dataTags;
    }

    /**
     * Searches for a specified data tag. Returns whether the search was
     *     successful.
     * @param dataTagName a String containing the name of the data tag to be
     *     searched for.
     * @return a boolean containing the result of the search.
     */
    public boolean hasDataTag(String dataTagName) {
        for (DataTag dataTag : this.dataTags) {
            if (dataTag.getName().equals(dataTagName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * Searches for a specified data tag. If the data tag is present, returns
     *     the value attached to the requested data tag. This value only makes
     *     sense for certain data tags, such as "Limited X", and will not make
     *     sense for others, such as "AI". Since int's default value is 0, this
     *     method will return an int whether it is valid data or not, so it is
     *     best to check the data tag name first.
     * @param dataTagName a String containing the name of the data tag to be
     *     searched for.
     * @return an int containing the value attached to the requested data tag.
     * @throws IllegalArgumentException if the requested data tag could not be
     *     found.
     */
    public int getDataTag(String dataTagName) {
        for (DataTag dataTag : this.dataTags) {
            if (dataTag.getName().equals(dataTagName)) {
                return dataTag.getValue();
            }
        }
        throw new IllegalArgumentException("Requested data tag: \""
            + dataTagName + "\" could not be found.");
    }
    /**
     * Checks a DataTag[] to see if it is a valid value for Equipment.dataTags.
     *     Throws an IllegalArgumentException if not. Used in Equipment's
     *     children's overridden setDataTags() methods.
     * @param dataTags a DataTag[] to be checked.
     * @return a boolean containing the result of the check.
     * @throws IllegalArgumentException if the DataTag[] is invalid or contains
     *     an invalid value for any number of reasons.
     */
    protected void checkDataTagsArray(DataTag[] dataTags) {
        HelperMethods.checkObjectArray("New data tags", dataTags);
    }
}