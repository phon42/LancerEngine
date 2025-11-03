package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frame.CoreSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frame.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frame.FrameStatblock;
import Packages.CoreTypes.EntityMechanics.LicenseSystem.frameLicense.LicenseContent;

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
public final class Frame extends LicenseContent {
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The frame's ID (i.e. "swallowtail_ranger").
     * Used for identifying it in Database.getFrame(String).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    // private String id;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The frame's name (i.e. "Everest").
     * Case-sensitive. Can be any String except "". Cannot be null.
     * Use Frame.getName() to get the raw value and Frame.outputName() to obtain
     *     it properly formatted.
     */
    // private String name;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The manufacturer of this frame (i.e. a Manufacturer that represents GMS).
     * Can be any Manufacturer. Cannot be null.
     */
    // protected Manufacturer manufacturer;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The origin license for this frame (i.e. a License representing 'Everest,
     *     rank 0' or 'Blackbeard, rank I'). Uses a License to represent an
     *     ACTUAL license instead of the frame name and rank to which a pilot
     *     holds a license.
     * LicenseContent.originLicense.id property is always determined by
     *     this.licenseID.
     * LicenseContent.originLicense.level property is always determined by
     *     this.licenseLevel.
     * For GMS license content, is set to "<the frame ID>" 0.
     * Can be any License. Cannot be null.
     */
    // protected License originLicense;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The ID of the license that this frame originates from (i.e.
     *     "mf_standard_pattern_i_everest" or "mf_blackbeard").
     * For GMS license content, is set to "mf_standard_pattern_i_everest".
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String licenseID;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The name of the license that this license content originates from (i.e.
     *     "Blackbeard").
     * For GMS content, is set to "GMS".
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String license;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The license level for this license content (i.e. 1).
     * For GMS license content, is set to 0. For non-GMS license content, must
     *     be a minimum of 1.
     * Must be a minimum of 0.
     */
    protected int licenseLevel;
    // TODO: figure out a way to override the documentation from LicenseContent
    /**
     * The description at the top of a frame's page on COMP/CON (i.e. "Most
     *     humans don’t...").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    // private String description;
    /**
     * The frame's role (i.e. "balanced"). Multiple items are stored as seperate
     *     elements (i.e "Controller/Support" would be stored as {"controller",
     *     "support"}).
     * Must be of length 1 at minimum. Each element must be one of the following
     *     values:
     *     "artillery", "balanced", "controller", "striker", "support".
     * Case-insensitive and stored in lowercase. Cannot be null.
     * 
     * See pg. 116.
     */
    private String[] role;
    /**
     * Contains an array of allowed values for the elements of the role
     *     property. Case-insensitive and stored in lowercase.
     */
    public static final String[] allowedRoles = new String[] {
        "artillery", "balanced", "controller", "defender", "striker", "support"
    };

    // frame attributes - size, structure, HP, etc. - see pgs. 33 - 34.
    private FrameStatblock statblock;

    /**
     * The frame's traits (i.e. {"Initiative", "Replaceable Parts"}).
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     */
    private String[] traits;

    /**
     * The frame's weapon mounts.
     * Can be any Mount[] that does not contain null elements, elements with
     *     their Mount.weapon set to something other than null with a
     *     Mount.mountType of anything other than "integrated weapon", or
     *     elements with their Mount.modification, Mount.coreBonus, or
     *     Mount.talent set to anything except ""/""/null. Cannot be null.
     */
    private Mount[] mounts;

    private CoreSystem coreSystem;

    // Optional property
    /**
     * The frame's frameEnum (i.e. FrameEnum.SWALLOWTAIL_RANGER).
     * Used for identifying it in Database.getFrame(FrameEnum). Can be null.
     */
    private FrameEnum frameEnum;

    /**
     * Creates a new Frame using every possible property.
     */
    public Frame(String id, String name, Manufacturer manufacturer,
        License originLicense, String license, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts, FrameEnum frameEnum) {
        super(id, name, manufacturer, originLicense, license, description);
        setFrameEnum(frameEnum);
        setRole(role);
        setStats(statblock);
        setTraits(traits);
        setMounts(mounts);
        setFrameEnum(frameEnum);
    }
    /**
     * Creates a new Frame using every possible property except frameEnum.
     */
    public Frame(String id, String name, Manufacturer manufacturer,
        License originLicense, String license, String description,
        String[] role, FrameStatblock statblock, String[] traits,
        Mount[] mounts) {
        super(id, name, manufacturer, originLicense, license, description);
        setFrameEnum(frameEnum);
        setRole(role);
        setStats(statblock);
        setTraits(traits);
        setMounts(mounts);
    }
    /**
     * Creates a deepest copy of the provided Frame.
     * @param frame a Frame to be copied.
     * @return a Frame deepest copy of the provided Frame.
     */
    public Frame(Frame frame) {
        // make sure to use the proper accessor method instead of "property" if
        //     the property's type is mutable
        super(frame.id, frame.name, frame.source, frame.originLicense,
            frame.license, frame.description);
        setFrameEnum(frame.frameEnum);
        setRole(frame.role);
        setStats(frame.statblock);
        setTraits(frame.traits);
        setMounts(frame.mounts);
        setCoreSystem(frame.coreSystem);
        setFrameEnum(frame.frameEnum);
    }

    public String getID() {
        return id;
    }
    public FrameEnum getFrameEnum() {
        return frameEnum;
    }
    public String[] getRole() {
        return HelperMethods.copyOf(role);
    }
    public FrameStatblock getStatblock() {
        return new FrameStatblock(statblock);
    }
    public String[] getTraits() {
        return HelperMethods.copyOf(traits);
    }
    public Mount[] getMounts() {
        return HelperMethods.copyOf(mounts);
    }
    public CoreSystem getCoreSystem() {
        return new CoreSystem(coreSystem);
    }
    /**
     * Sets this.frameEnum to the provided value.
     * @param frameEnum a FrameEnum which cannot be null.
     * @throws IllegalArgumentException if frameEnum is null.
     */
    private void setFrameEnum(FrameEnum frameEnum) {
        HelperMethods.checkObject("New frame enum", frameEnum);
        this.frameEnum = frameEnum;
    }
    /**
     * Sets this.role to the value provided.
     * @param role a String[] which must be at least of length 1, cannot be
     *     null, contain null elements, or contain any invalid values, as
     *     defined by Frame.allowedRoles.
     * @throws IllegalArgumentException if role is null, contains null elements
     *     or invalid values, as defined by Frame.allowedRoles, or has a length
     *     of 0.
     */
    private void setRole(String[] role) {
        boolean isValidRole = false;
        String roleString;

        HelperMethods.checkObjectArray("New role", role);
        if (role.length == 0) {
            throw new IllegalArgumentException("New role value has a length"
                + " of 0");
        }
        for (int i = 0; i < role.length; i++) {
            role[i] = role[i].toLowerCase();
            roleString = role[i];
            isValidRole = false;
            for (String roleMatch : Frame.allowedRoles) {
                if (roleString.equals(roleMatch)) {
                    isValidRole = true;
                    break;
                }
            }
            if (! isValidRole) {
                throw new IllegalArgumentException("New role array contains an"
                    + " invalid role value: \"" + roleString + "\"");
            }
        }
        role = HelperMethods.copyOf(role);
        this.role = role;
    }
    private void setStats(FrameStatblock statblock) {
        HelperMethods.checkObject("New statblock", statblock);
        statblock = new FrameStatblock(statblock);
        this.statblock = statblock;
    }
    /**
     * Sets this.traits to the provided value.
     * @param traits a String[] that cannot be null, contain null elements, or
     *     elements that are "".
     * @throws IllegalArgumentException if traits is null, contains null
     *     elements, or elements that are "".
     */
    private void setTraits(String[] traits) {
        HelperMethods.checkStringArray("New traits", traits);
        traits = HelperMethods.copyOf(traits);
        this.traits = traits;
    }
    /**
     * Sets this.mounts to the provided value.
     * @param mounts a Mount[] that cannot be null, contain null elements, or
     *     elements that have their Mount.modification, Mount.coreBonus, or
     *     Mount.talent set to anything except ""/""/null.
     * @throws IllegalArgumentException if mounts is null, contains null
     *     elements, elements with their Mount.weapon set to something other
     *     than null with a Mount.mountType of anything other than
     *     "integrated weapon", or elements with their Mount.modification,
     *     Mount.coreBonus, or Mount.talent set to something other than its
     *     construction value.
     */
    private void setMounts(Mount[] mounts) {
        HelperMethods.checkObjectArray("New mounts", mounts);
        for (Mount mount : mounts) {
            if (mount.getWeapon() != null) {
                if (! mount.getMountType().equals(
                    "integrated mount")) {
                    throw new IllegalArgumentException("New mounts array"
                        + " contains an element with its Mount.weapon set to"
                        + " something other than null, but its Mount.mountType"
                        + " was not \"integrated mount\"");
                }
            }
            if (! mount.isUnmodified()) {
                throw new IllegalArgumentException("New mounts array"
                    + " contains an element with its Mount.modification,"
                    + " Mount.coreBonus, or Mount.talent set to something other"
                    + " than its construction value");
            }
        }
        mounts = HelperMethods.copyOf(mounts);
        this.mounts = mounts;
    }
    private void setCoreSystem(CoreSystem coreSystem) {
        HelperMethods.checkObject("New coreSystem", coreSystem);
        coreSystem = new CoreSystem(coreSystem);
        this.coreSystem = coreSystem;
    }

    /**
     * Returns this.name, properly formatted (i.e. "swallowtail (ranger
     *     variant)" becomes "Swallowtail (Ranger Variant)" and "death's head"
     *     becomes "Death's Head").
     * @return a String containing this.name, properly formatted.
     */
    public String outputName() {
        return HelperMethods.toProperCase(name);
    }
}
