package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import MainBranch.Database;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.SystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.DataTag;

public class SystemBase extends Equipment {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any SystemType. Cannot be null.
     * Default value: a SystemType representing "System".
     * 
     * If passed a SystemType that is null, sets this.type to a SystemType
     *     representing "System".
     */
    protected SystemType type;
    // typeDefault removed because the relevant value only becomes accessible at
    //     runtime
    /**
     * Must be a minimum of 0.
     * Default value: 0.
     * 
     * If passed an int that is < 0, sets this.spCost to
     *     SystemBase.spCostDefault.
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
        // Semi-required properties
        setType(type);
        setSpCost(spCost);
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
        // Semi-required properties
        setType(type);
        setSpCost(spCost);
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

    // Semi-required properties
    public SystemType getType() {
        return type;
    }
    public int getSpCost() {
        return spCost;
    }
    // Semi-required properties
    protected void setType(SystemType type) {
        if (type == null) {
            type = Database.getSystemType("System");
        }
        this.type = type;
    }
    protected void setSpCost(int spCost) {
        if (spCost < 0) {
            spCost = SystemBase.spCostDefault;
        }
        this.spCost = spCost;
    }
}
