package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.iTagDataUnverified.iTagData.DataTag;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;

/**
 * Represents a single weapon modification. Contains information about the mod
 *     such as its system point cost, the types of weapon it can be applied to,
 *     and its effect, as well as all inherited properties.
 * 
 * Requires a weapon mod id, name, system points cost, an array of the types of
 *     weapon it can be applied to, its manufacturer, the source license, the
 *     source license's rank, effect, description, and the source license's id
 *     to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Modification extends Equipment {
    // Required properties
    /**
     * The modification's system points cost (i.e. 2).
     * Must be a minimum of 0.
     */
    private int spCost;
    /**
     * The array of weapon types to which this modification can be applied (i.e.
     *     a String[] containing "melee").
     * Can be any String[] that does not contain null elements or elements that
     *     are "". Cannot be null.
     * Elements are case-insensitive and stored in lowercase.
     */
    private String[] allowedTypes;
    /**
     * This modification's effect (i.e. "On a hit with the weapon this mod is"
     *     " applied to, expend a charge as a free action to activate its"
     *     " detonator and deal +1d6 explosive bonus damage.").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String effect;
    // Optional properties
    // TODO: might actually be Harm[] if a variable damage type is allowed
    /**
     * The damage the modification adds directly to the weapon it's applied to
     *     on a hit (i.e. a Damage[] containing Explosive 1d6).
     * Can be any Damage[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private Damage[] addedDamage;
    /**
     * The weapon sizes this modification cannot be applied to (i.e. a String[]
     *     containing "superheavy").
     * Can be any String[] that is not of length 0 and does not contain null
     *     elements or elements that are "". Can be null.
     * Elements are case-insensitive and stored in lowercase.
     */
    private String[] restrictedSizes;
    /**
     * The actions provided by this modification (i.e. an IActionData[]
     *     containing the Activate Shock Wreath action).
     * Can be any IActionData that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private IActionData[] actions;
    /**
     * The range the modification adds directly to the weapon it's applied to
     *     (i.e. a RangeTag[] containing Range 5)
     * Can be any RangeTag that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private RangeTag[] addedRange;
    /**
     * The data tags the modification adds to the weapon it's applied to (i.e. a
     *     DataTag[] containing Limited 3 and Unique).
     * Can be any DataTag that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private DataTag[] addedDataTags;

    /**
     * Verbose constructor for non-GMS content.
     */
    public Modification(String id, String name, Manufacturer manufacturer,
        String licenseID, String licenseName, int licenseLevel,
        String description, DataTag[] dataTags, int spCost,
        String[] allowedTypes, String effect, Damage[] addedDamage,
        String[] restrictedSizes, IActionData[] actions, RangeTag[] addedRange,
        DataTag[] addedDataTags) {
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags);
        setSpCost(spCost);
        setAllowedTypes(allowedTypes);
        setEffect(effect);
        setAddedDamage(addedDamage);
        setRestrictedSizes(restrictedSizes);
        setActions(actions);
        setAddedRange(addedRange);
        setAddedDataTags(addedDataTags);
    }
    /**
     * Abbreviated constructor for GMS content.
     */
    public Modification(String id, String name, String licenseID,
        String licenseName, String description, DataTag[] dataTags, int spCost,
        String[] allowedTypes, String effect, Damage[] addedDamage,
        String[] restrictedSizes, IActionData[] actions, RangeTag[] addedRange,
        DataTag[] addedDataTags) {
        super(id, name, licenseID, licenseName, description, dataTags);
        setSpCost(spCost);
        setAllowedTypes(allowedTypes);
        setEffect(effect);
        setAddedDamage(addedDamage);
        setRestrictedSizes(restrictedSizes);
        setActions(actions);
        setAddedRange(addedRange);
        setAddedDataTags(addedDataTags);
    }
    public Modification(Modification modification) {
        super(modification);
        setSpCost(modification.spCost);
        setAllowedTypes(modification.allowedTypes);
        setEffect(modification.effect);
        setAddedDamage(modification.addedDamage);
        setRestrictedSizes(modification.restrictedSizes);
        setActions(modification.actions);
        setAddedRange(modification.addedRange);
        setAddedDataTags(modification.addedDataTags);
    }

    // Required properties
    public int getSpCost() {
        return spCost;
    }
    public String[] getAllowedTypes() {
        return HelperMethods.copyOf(allowedTypes);
    }
    public String getEffect() {
        return effect;
    }
    // Optional properties
    public Damage[] getAddedDamage() {
        if (addedDamage != null) {
            return HelperMethods.copyOf(addedDamage);
        }

        return addedDamage;
    }
    public String[] getRestrictedSizes() {
        if (restrictedSizes != null) {
            return HelperMethods.copyOf(restrictedSizes);
        }

        return restrictedSizes;
    }
    public IActionData[] getActions() {
        if (actions != null) {
            return HelperMethods.copyOf(actions);
        }

        return actions;
    }
    public RangeTag[] getAddedRange() {
        if (addedRange != null) {
            return HelperMethods.copyOf(addedRange);
        }

        return addedRange;
    }
    public DataTag[] getAddedDataTags() {
        if (addedDataTags != null) {
            return HelperMethods.copyOf(addedDataTags);
        }

        return addedDataTags;
    }
    // Required properties
    private void setSpCost(int spCost) {
        if (spCost < 0) {
            throw new IllegalArgumentException("spCost value: " + spCost + " is"
                + " < 0");
        }
        this.spCost = spCost;
    }
    private void setAllowedTypes(String[] allowedTypes) {
        HelperMethods.checkStringArray("allowedTypes",
            allowedTypes);
        for (int i = 0; i < allowedTypes.length; i++) {
            allowedTypes[i] = allowedTypes[i].toLowerCase();
        }
        allowedTypes = HelperMethods.copyOf(allowedTypes);
        this.allowedTypes = allowedTypes;
    }
    private void setEffect(String effect) {
        HelperMethods.checkString("effect", effect);
        this.effect = effect;
    }
    // Optional properties
    private void setAddedDamage(Damage[] addedDamage) {
        if (addedDamage != null) {
            if (addedDamage.length == 0) {
                throw new IllegalArgumentException("addedDamage array's"
                    + " length is 0");
            }
            HelperMethods.checkObjectArray("addedDamage",
                addedDamage);
            addedDamage = HelperMethods.copyOf(addedDamage);
        }
        this.addedDamage = addedDamage;
    }
    private void setRestrictedSizes(String[] restrictedSizes) {
        if (restrictedSizes != null) {
            if (restrictedSizes.length == 0) {
                throw new IllegalArgumentException("restrictedSizes array's"
                    + " length is 0");
            }
            HelperMethods.checkStringArray("restrictedSizes",
                restrictedSizes);
            restrictedSizes = HelperMethods.copyOf(restrictedSizes);
        }
        this.restrictedSizes = restrictedSizes;
    }
    private void setActions(IActionData[] actions) {
        if (actions != null) {
            if (actions.length == 0) {
                throw new IllegalArgumentException("actions array's length is"
                    + " 0");
            }
            HelperMethods.checkObjectArray("actions", actions);
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setAddedRange(RangeTag[] addedRange) {
        if (addedRange != null) {
            if (addedRange.length == 0) {
                throw new IllegalArgumentException("addedRange array's length"
                    + " is 0");
            }
            HelperMethods.checkObjectArray("addedRange",
                addedRange);
            addedRange = HelperMethods.copyOf(addedRange);
        }
        this.addedRange = addedRange;
    }
    private void setAddedDataTags(DataTag[] addedDataTags) {
        if (addedDataTags != null) {
            if (addedDataTags.length == 0) {
                throw new IllegalArgumentException("addedDataTags array's"
                    + " length is 0");
            }
            HelperMethods.checkObjectArray("addedDataTags",
                addedDataTags);
            addedDataTags = HelperMethods.copyOf(addedDataTags);
        }
        this.addedDataTags = addedDataTags;
    }
}
