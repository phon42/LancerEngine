package packages.CoreTypes.entityMechanics.licenseSystem;

import main.Database;
import main.HelperMethods;
import packages.CoreTypes.entityMechanics.Manufacturer;
import packages.CoreTypes.entityMechanics.entityTypes.mech.Equipment;
import packages.CoreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.CoreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.CoreTypes.entityMechanics.entityTypes.mech.equipment.Weapon;
import packages.CoreTypes.entityMechanics.licenseSystem.frameLicense.LicenseContent;

/**
 * See pg. 31.
 */
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
     * The license's id, stolen from the frame it contains (i.e.
     *     "mf_blackbeard").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The license's name (i.e. "blackbeard").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String name;
    /**
     * The license's manufacturer (i.e. a Manufacturer representing GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    private Manufacturer manufacturer;
    
    public FrameLicense(String id, String name, Manufacturer manufacturer) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setManufacturer(manufacturer);
    }
    public FrameLicense(FrameLicense frameLicense) {
        setID(frameLicense.id);
        setName(frameLicense.name);
        setManufacturer(frameLicense.manufacturer);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Manufacturer getManufacturer() {
        return new Manufacturer(manufacturer);
    }
    private void setID(String id) {
        HelperMethods.checkString("New id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        name = name.toLowerCase();
        this.name = name;
    }
    /**
     * Sets this.manufacturer to the provided value.
     * @param manufacturer a Manufacturer which can be any Manufacturer. Cannot
     *     be null.
     * @throws IllegalArgumentException if manufacturer is null.
     */
    private void setManufacturer(Manufacturer manufacturer) {
        HelperMethods.checkObject("manufacturer", manufacturer);
        manufacturer = new Manufacturer(manufacturer);
        this.manufacturer = manufacturer;
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
        HelperMethods.checkObject("content", content);
        if (this.manufacturer.getID().equals("GMS")
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

        HelperMethods.checkObject("content", content);
        isGMS = content.getSource().getID().equals("GMS");
        isGMS = isGMS || content.getOriginLicense() == null;
        if (content instanceof Equipment) {
            isEquipment = true;
        }
        if (isEquipment) {
            if (content instanceof Weapon) {
                isGMS = isGMS || ((Weapon) content).getLicense().equals(
                    "Integrated Weapon");
            }
        }
        if (isGMS) {
            // All GMS content and integrated weapons always go to rank 0
            addContent(0, content);
        } else {
            if (isEquipment) {
                // Equipment goes to its proper place
                addContent(content.getLicenseLevel(), content);
            } else {
                // All non-GMS frames are on rank 2
                addContent(2, content);
            }
        }
    }
}
