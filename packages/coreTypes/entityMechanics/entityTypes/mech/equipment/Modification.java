package packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment;

import main.HelperMethods;
import packages.CoreTypes.EntityMechanics.Action;
import packages.CoreTypes.EntityMechanics.License;
import packages.CoreTypes.EntityMechanics.Manufacturer;
import packages.CoreTypes.EntityMechanics.RangeTag;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.Equipment;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.DataTag;
import packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.dataTag.Tag;
import packages.CoreTypes.EntityMechanics.harmSystem.Damage;

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
     * The actions provided by this modification (i.e. an Action[] containing
     *     the Activate Shock Wreath action).
     * Can be any Action that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private Action[] actions;
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

    public Modification(String id, String name, Manufacturer manufacturer,
        License originLicense, String license, int licenseLevel,
        String licenseID, String description, Tag[] tags, int spCost,
        String[] allowedTypes, String effect, Damage[] addedDamage,
        String[] restrictedSizes, Action[] actions, RangeTag[] addedRange,
        DataTag[] addedDataTags) {
        super(id, name, manufacturer, originLicense, license, licenseLevel,
            licenseID, description, tags);
        setSpCost(spCost);
        setAllowedTypes(allowedTypes);
        setEffect(effect);
        setAddedDamage(addedDamage);
        setRestrictedSizes(restrictedSizes);
        setActions(actions);
        setAddedRange(addedRange);
        setAddedDataTags(addedDataTags);
    }
    public Modification(String id, String name, Manufacturer manufacturer,
        String description, int spCost, String[] allowedTypes, String effect,
        Damage[] addedDamage, String[] restrictedSizes, Action[] actions,
        RangeTag[] addedRange, DataTag[] addedDataTags) {
            this(id, name, manufacturer, null, null,
                0, null, description, null,
                spCost, allowedTypes, effect, addedDamage, restrictedSizes,
                actions, addedRange, addedDataTags);
    }
    public Modification(Modification modification) {
        super(modification.id, modification.name, modification.source,
            modification.originLicense, modification.license,
            modification.licenseLevel, modification.licenseID,
            modification.description, modification.tags);
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
    public Action[] getActions() {
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
    public void setSpCost(int spCost) {
        if (spCost < 0) {
            throw new IllegalArgumentException("spCost value: " + spCost + " is"
                + " < 0");
        }
        this.spCost = spCost;
    }
    public void setAllowedTypes(String[] allowedTypes) {
        HelperMethods.checkStringArray("allowedTypes",
            allowedTypes);
        for (int i = 0; i < allowedTypes.length; i++) {
            allowedTypes[i] = allowedTypes[i].toLowerCase();
        }
        allowedTypes = HelperMethods.copyOf(allowedTypes);
        this.allowedTypes = allowedTypes;
    }
    public void setEffect(String effect) {
        HelperMethods.checkString("effect", effect);
        this.effect = effect;
    }
    // Optional properties
    public void setAddedDamage(Damage[] addedDamage) {
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
    public void setRestrictedSizes(String[] restrictedSizes) {
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
    public void setActions(Action[] actions) {
        if (actions != null) {
            if (actions.length == 0) {
                throw new IllegalArgumentException("actions array's length is"
                    + " 0");
            }
            HelperMethods.checkObjectArray("actions",
                addedRange);
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    public void setAddedRange(RangeTag[] addedRange) {
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
    public void setAddedDataTags(DataTag[] addedDataTags) {
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
