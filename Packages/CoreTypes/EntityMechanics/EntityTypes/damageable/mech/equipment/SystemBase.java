package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.mechSystem.SystemType;

public class SystemBase extends Equipment {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any SystemType. Cannot be null.
     * 
     * If passed a SystemType that is null, sets this.type to a SystemType
     *     representing "System".
     */
    protected SystemType type;
    /**
     * Must be a minimum of 0.
     */
    protected int spCost;
    protected static final int spCostDefault = 0;

    /**
     * Verbose constructor for non-GMS content.
     * Takes in as parameters the semi-required properties SystemBase.type and
     *     SystemBase.spCost.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags,
        // Semi-required properties
        SystemType type, int spCost
    ) {
        // Equipment properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as parameters the semi-required properties SystemBase.type and
     *     SystemBase.spCost.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags,
        // Semi-required properties
        SystemType type, int spCost
    ) {
        // Equipment properties
        super(id, name, licenseID, licenseName, description, dataTags);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, null, -1);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public SystemBase(
        // Equipment properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags
    ) {
        this(id, name, licenseID, licenseName, description, dataTags,
            null, -1);
    }
}
