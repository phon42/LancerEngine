package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase;

import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;

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
 * Safety: At least one of this class' properties has an allowed value of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public final class Frame extends UnverifiedFrame {
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The manufacturer of this frame (i.e. a Manufacturer that represents GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    // protected Manufacturer manufacturer;

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
}
