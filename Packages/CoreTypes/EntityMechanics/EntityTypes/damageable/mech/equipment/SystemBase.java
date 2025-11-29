package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;

public class SystemBase extends Equipment {
    /**
     * Verbose constructor for non-GMS content.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags
    ) {
        // Equipment properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags);
    }
    /**
     * Abbreviated constructor for GMS content.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags
    ) {
        // Equipment properties
        super(id, name, licenseID, licenseName, description, dataTags);
    }
}
