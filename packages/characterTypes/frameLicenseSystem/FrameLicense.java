package packages.characterTypes.frameLicenseSystem;

import main.Database;
import main.HelperMethods;
import packages.characterTypes.frameLicenseSystem.frameLicense.LicenseContent;
import packages.characterTypes.mech.Equipment;
import packages.characterTypes.mech.Frame;
import packages.characterTypes.mech.equipment.MechSystem;
import packages.characterTypes.mech.equipment.Weapon;

/**
 * Represents a license (the actual thing that contains frames, mech systems,
 *     etc, not the licenses and ranks to which a pilot has access, which is
 *     represented by the License class). Contains information about what lies
 *     on each of three ranks of the license.
 * 
 * Requires nothing to be instantialized.
 * 
 * Used in Database.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class FrameLicense {
    /**
     * The license's manufacturer (i.e. "GMS").
     * Must be a valid manufacturer as defined by Database.manufacturerList.
     *     Cannot be null.
     * Case-insensitive and stored in uppercase.
     */
    private String manufacturer;
    /**
     * The license's name (i.e. "blackbeard").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String name;
    
    public FrameLicense(String manufacturer, String name) {
        setName(name);
        setManufacturer(manufacturer);
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public String getName() {
        return name;
    }
    /**
     * Sets this.manufacturer to the provided value.
     * @param manufacturer a String which must be a valid manufacturer as
     *     defined by Database.manufacturerList. Cannot be null.
     * @throws IllegalArgumentException if manufacturer is an invalid
     *     manufacturer.
     */
    private void setManufacturer(String manufacturer) {
        manufacturer = manufacturer.toUpperCase();
        if (! Database.isValidManufacturer(manufacturer)) {
            throw new IllegalArgumentException("New manufacturer value: "
                + manufacturer + " is an invalid manufacturer");
        }
        this.manufacturer = manufacturer;
    }
    /**
     * Sets this.name to the provided value.
     * @param name a String which cannot be "". Cannot be null.
     * @throws IllegalArgumentException if name is "" or null.
     */
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        name = name.toLowerCase();
        this.name = name;
    }

    /**
     * Adds a provided LicenseContent object to its appropriate place in the
     *     database.
     * @param rank an int which must be between 0 and 3 (inclusive).
     * @param content a LicenseContent which cannot be null.
     */
    private void addContent(int rank, LicenseContent content) {
        if (rank < 0) {
            throw new IllegalArgumentException("rank is < 0");
        }
        if (rank > 3) {
            throw new IllegalArgumentException("rank is > 3");
        }
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }
        if (this.manufacturer.equals("GMS")
            || this.name.equals("GMS Content")) {
            if (rank != 0) {
                throw new IllegalArgumentException("License is for a GMS"
                    + " license but rank value: " + rank + " is not 0");
            }
        }
        if (content instanceof Frame) {
            Database.addFrame((Frame) content);
        } else if (content instanceof MechSystem) {
            Database.addSystem((MechSystem) content);
        } else if (content instanceof Weapon) {
            Database.addWeapon((Weapon) content);
        } else {
            throw new IllegalArgumentException("content is of a non-accepted"
                + " type");
        }
    }
    /**
     * Helper method for addContent(int, LicenseContent). Automatically
     *     interprets the LicenseContent's destination rank.
     * - If LicenseContent.manufacturer is "GMS" or "integrated weapon",
     *       automatically calls addContent(0, LicenseContent)
     * - Otherwise, automatically gets the correct rank from the
     *       LicenseContent's LicenseContent.license property and calls
     *       addContent() with the right rank.
     * @param content a LicenseContent that cannot be null.
     */
    public void addContent(LicenseContent content) {
        // Is true if the LicenseContent is GMS content or an integrated weapon
        boolean isGMS = false;
        // Is true if (content instanceof Equipment)
        boolean isEquipment = false;

        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }
        isGMS = content.getManufacturer().equals("GMS");
        isGMS = isGMS || content.getLicense().getName().equals("GMS");
        if (content instanceof Equipment) {
            isEquipment = true;
        }
        if (isEquipment) {
            if (content instanceof Weapon) {
                isGMS = isGMS || ((Weapon) content).getLicense().getName()
                    .equals("Integrated Weapon");
            }
        }
        if (isGMS) {
            // All GMS content and integrated weapons always go to rank 0
            addContent(0, content);
        } else {
            if (isEquipment) {
                // Equipment goes to its proper place
                addContent(content.getLicense().getLevel(), content);
            } else {
                // All non-GMS frames are on rank 2
                addContent(2, content);
            }
        }
    }
}
