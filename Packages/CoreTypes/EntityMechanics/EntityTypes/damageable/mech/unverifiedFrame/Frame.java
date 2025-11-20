package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame;

import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.UnverifiedFrame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.frame.CoreSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.frame.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.unverifiedFrame.frame.FrameStatblock;

/**
 * See pg. 32.
 * 
 * "In game terms, a FRAME is a mech’s modular base. It determines your mech’s
 *     SIZE, ARMOR, and other specifications, as well as its available weapon
 *     mounts and capacity for additional systems."
 * - pg. 32
 * 
 * A Frame object is needed to create a Mech object. Its stats,
 *     traits, and mounts serve as the base on top of which mech skills,
 *     weapons, systems, and other modifications are added.
 */
/**
 * Represents a frame (a pattern or statblock that can be copied to form mechs).
 *     Contains information about the frame such as its manufacturer, frame
 *     name, role, and stats.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in Mech to create Mech objects. Also used in Database.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public final class Frame extends UnverifiedFrame {
    /**
     * Creates a new Frame using every possible property.
     */
    public Frame(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts, CoreSystem coreSystem, FrameEnum frameEnum) {
        super(id, name, manufacturer, licenseID, license, licenseLevel,
            description, role, statblock, traits, mounts, coreSystem,
            frameEnum);
    }
    /**
     * Creates a new non-GMS Frame using every possible property except
     *     frameEnum.
     */
    public Frame(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts, CoreSystem coreSystem) {
        super(id, name, manufacturer, licenseID, license, licenseLevel,
            description, role, statblock, traits, mounts, coreSystem);
    }
    /**
     * Creates a new GMS Frame using every possible property except frameEnum.
     */
    public Frame(String id, String name, String licenseID,
        String license, String description, String[] role,
        FrameStatblock statblock, String[] traits, Mount[] mounts,
        CoreSystem coreSystem) {
        super(id, name, licenseID, license, description, role, statblock,
            traits, mounts, coreSystem);
    }
    /**
     * Creates a deepest copy of the provided Frame.
     * @param frame a Frame to be copied.
     * @return a Frame deepest copy of the provided Frame.
     */
    public Frame(Frame frame) {
        // make sure to use the proper accessor method instead of "property" if
        //     the property's type is mutable
        super(frame);
    }
}
