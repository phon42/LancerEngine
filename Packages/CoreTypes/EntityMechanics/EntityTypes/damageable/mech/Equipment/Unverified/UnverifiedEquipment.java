package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Unverified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.licenseContentBase.UnverifiedLicenseContent;

/**
 * Represents a piece of mech equipment of any kind, such as a mech system or a
 *     mech weapon. Contains information about the equipment's name, origin, and
 *     data tags.
 * 
 * Requires an equipment name to be instantiated.
 * 
 * Used in and extended by UnverifiedMechSystem and UnverifiedWeapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class UnverifiedEquipment extends UnverifiedLicenseContent {
    // TODO: add some way to add actions for equipments that provide them (i.e
    //     GMS Pattern-A Jericho Deployable Cover and its Deploy Jericho
    //     Deployable Cover action for MechSystem, or IPS-N D/D 288 and its
    //     Charge D/D 288 action for Weapon)
    // TODO: add some way for DataTags to be automatically added based on an
    //     equipment's contents
    // Optional property
    /**
     * Contains an array of all of this equipment's data tags (i.e. DataTag
     *     elements representing an "AI" or "Limited X" data tag).
     * Can be any DataTag[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    protected DataTag[] dataTags;

    /**
     * Verbose constructor for non-GMS content.
     */
    protected UnverifiedEquipment(
        // UnverifiedLicenseContent properties
        String id, String name, String manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        // Optional property
        DataTag[] dataTags
    ) {
        // UnverifiedLicenseContent properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description);
        // Optional property
        setDataTags(dataTags);
    }
    /**
     * Abbreviated constructor for GMS content.
     */
    protected UnverifiedEquipment(
        // UnverifiedLicenseContent properties
        String id, String name, String licenseID, String licenseName,
        String description,
        // Optional property
        DataTag[] dataTags
    ) {
        // UnverifiedLicenseContent properties
        super(id, name, licenseID, licenseName, description);
        // Optional property
        setDataTags(dataTags);
    }

    // getDataTags purposefully removed
    /**
     * Is overridden in all of UnverifiedEquipment's children.
     * Is overridden in all of Equipment's children.
     * @throws IllegalArgumentException if dataTags is of length 0 or includes
     *     null elements.
     */
    protected void setDataTags(DataTag[] dataTags) {
        HelperMethods.checkObjectArrayAlt("New data tags",
            dataTags);
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
            if (dataTag.getData().getName().equals(dataTagName)) {
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
    public Object getDataTag(String dataTagName) {
        for (DataTag dataTag : this.dataTags) {
            if (dataTag.getData().getName().equals(dataTagName)) {
                return dataTag.getValue();
            }
        }
        throw new IllegalArgumentException("Requested data tag: \""
            + dataTagName + "\" could not be found.");
    }
}