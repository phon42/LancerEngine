package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.frameBase;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.FrameBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.Frame;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Frame.Verified.frame.CoreSystem;

public class UnverifiedFrame extends FrameBase
    implements UnverifiedData<UnverifiedFrame, Frame> {
    /**
     * Creates a new UnverifiedFrame using every possible property.
     */
    public UnverifiedFrame(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts, CoreSystem coreSystem, FrameEnum frameEnum) {
        // TODO: replace the manufacturer variable with a constant Manufacturer
        //     which will be verified later
        super(id, name, manufacturer, licenseID, license, licenseLevel,
            description, role, statblock, traits, mounts, coreSystem,
            frameEnum);
    }
    /**
     * Creates a new non-GMS UnverifiedFrame using every possible property
     *     except frameEnum.
     */
    public UnverifiedFrame(String id, String name, Manufacturer manufacturer,
        String licenseID, String license, int licenseLevel, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts, CoreSystem coreSystem) {
        this(id, name, manufacturer, licenseID, license, licenseLevel,
            description, role, statblock, traits, mounts, coreSystem,
            null);
    }
    /**
     * Creates a new GMS UnverifiedFrame using every possible property except
     *     frameEnum.
     */
    public UnverifiedFrame(String id, String name, String licenseID,
        String license, String description, String[] role,
        FrameStatblock statblock, String[] traits, Mount[] mounts,
        CoreSystem coreSystem) {
        // TODO: replace the manufacturer variable with a constant Manufacturer
        //     which will be verified later
        super(id, name, licenseID, license, description, role, statblock,
            traits, mounts, coreSystem);
    }

    @Override
    public Class<UnverifiedFrame> getUnverifiedType() {
        return UnverifiedFrame.class;
    }
    @Override
    public Class<Frame> getVerifiedType() {
        return Frame.class;
    }
    @Override
    public Frame verify() {
        // TODO: fill out
        return null;
    }
}
