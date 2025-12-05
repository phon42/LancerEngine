package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Unverified.unverifiedEquipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Unverified.UnverifiedEquipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.UnverifiedDataTag;

public class UnverifiedSystemBase extends UnverifiedEquipment {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any String except "". Cannot be null.
     * Default value: "System".
     * Case-sensitive.
     */
    protected String type;
    protected static final String typeDefault = "System";
    // typeDefault removed because the relevant value only becomes accessible at
    //     runtime
    /**
     * Must be a minimum of 0.
     * Default value: 0.
     * 
     * If passed an int that is < 0, sets this.spCost to
     *     UnverifiedSystemBase.spCostDefault.
     */
    protected int spCost;
    protected static final int spCostDefault = 0;

    /**
     * Verbose constructor for non-GMS content.
     * Takes in as parameters the semi-required properties
     *     UnverifiedSystemBase.type and UnverifiedSystemBase.spCost.
     */
    public UnverifiedSystemBase(
        // UnverifiedEquipment properties
        String id, String name, String manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        UnverifiedDataTag[] dataTags,
        // Semi-required properties
        String type, int spCost
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
     * Takes in as parameters the semi-required properties
     *     UnverifiedSystemBase.type and UnverifiedSystemBase.spCost.
     */
    public UnverifiedSystemBase(
        // UnverifiedEquipment properties
        String id, String name, String licenseID, String licenseName,
        String description, UnverifiedDataTag[] dataTags,
        // Semi-required properties
        String type, int spCost
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
    public UnverifiedSystemBase(
        // UnverifiedEquipment properties
        String id, String name, String manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        UnverifiedDataTag[] dataTags
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, null, -1);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public UnverifiedSystemBase(
        // UnverifiedEquipment properties
        String id, String name, String licenseID, String licenseName,
        String description, UnverifiedDataTag[] dataTags
    ) {
        this(id, name, licenseID, licenseName, description, dataTags,
            null, -1);
    }

    // Semi-required properties
    public String getType() {
        return type;
    }
    public int getSpCost() {
        return spCost;
    }
    // Semi-required properties
    protected void setType(String type) {
        if (type == null) {
            type = UnverifiedSystemBase.typeDefault;
        }
        HelperMethods.checkString("type", type);
        this.type = type;
    }
    protected void setSpCost(int spCost) {
        if (spCost < 0) {
            spCost = UnverifiedSystemBase.spCostDefault;
        }
        this.spCost = spCost;
    }
    
}
