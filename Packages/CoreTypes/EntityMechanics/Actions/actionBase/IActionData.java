package Packages.CoreTypes.EntityMechanics.Actions.actionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBase;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.RangeTag;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents a single action granted by a core bonus, core power, frame trait,
 *     system, talent rank, or weapon. Contains information about the action's
 *     name, activation speed, and what it does, among other properties.
 * 
 * Requires an action name, activation speed, and a detailed description to be
 *     instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class IActionData extends ActionBase {
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    // TODO: find a way to override ActionBase's documentation
    /**
     * Whether this action can be used while in pilot form. Not applicable for
     *     actions that belong to a Deployable, for instance.
     * Default value: false.
     */
    // private boolean pilot;
    // TODO: find a way to override ActionBase's documentation
    /**
     * Whether this action can be used while in a mech. Not applicable for
     *     actions that belong to a Deployable, for instance.
     * Default value: true.
     */
    // private boolean mech;
    /**
     * For actions attached to a limited system, the value of this.cost will be
     *     deducted from the number of limited charges remaining on that system
     *     when this action is used.
     * A value that is > -1 indicates that this action is attached to a limited
     *     system.
     * A value of -1 indicates that this action is not attached to a limited
     *     system.
     * Must be > -2.
     * Default value: -1.
     */
    private int cost;
    /**
     * The default value for IActionData.costDefault.
     */
    private static final int costDefault = -1;
    /**
     * Whether this action is a tech attack. Can only be true if this.activation
     *     is a tech action of some kind.
     * Default value: false.
     */
    private boolean techAttack;
    /**
     * The default value for IActionData.techAttack.
     */
    private static final boolean techAttackDefault = false;

    // Optional properties
    /**
     * The ranges associated with this action, if there are any.
     * Can be any RangeTag[] that does not contain null elements. Can be null.
     * Elements can be any RangeTag.
     */
    private RangeTag[] range;
    /**
     * The damages associated with this action, if there are any.
     * Can be any Damage[] that does not contain null elements. Can be null.
     * Elements can be any Damage.
     */
    private Damage[] damage;

    public IActionData(
        // ActionBase properties
        String actionName, ActivationType activation,
        String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, Frequency frequency,
        VueHTMLString trigger, Callable method,
        SynergyLocation[] synergyLocations,
        VueHTMLString requiredInitialConditions,
        // Semi-required properties
        int cost, TriState techAttack,
        // Optional properties
        RangeTag[] rangeTags, Damage[] damage
    ) {
        super(actionName, activation, detailedDescription, pilot, mech, confirm,
            hideActive, frequency, trigger, method, synergyLocations,
            requiredInitialConditions);
        // Semi-required properties
        setCost(cost);
        setTechAttack(techAttack);
        // Optional properties
        setRange(rangeTags);
        setDamage(damage);
    }
    public IActionData(String actionName, ActivationType activation,
        String detailedDescription) {
        super(actionName, activation, detailedDescription);
    }
    // If passed an item name instead of an action name
    public IActionData(
        // ActionBase properties
        ActivationType activation, String detailedDescription, String itemName,
        TriState pilot, TriState mech, String[] confirm, TriState hideActive,
        Frequency frequency, VueHTMLString trigger, Callable method,
        VueHTMLString requiredInitialConditions,
        // Semi-required properties
        int cost, TriState techAttack,
        // Optional properties
        SynergyLocation[] synergyLocations, RangeTag[] rangeTags,
        Damage[] damage
    ) {
        this(toActionName(itemName), activation, detailedDescription, pilot,
            mech, confirm, hideActive, frequency, trigger, method,
            synergyLocations, requiredInitialConditions, cost, techAttack,
            rangeTags, damage);
    }
    public IActionData(ActivationType activation, String detailedDescription,
        String itemName) {
        this(toActionName(itemName), activation, detailedDescription);
    }
    public IActionData(IActionData iActionData) {
        super(iActionData);
    }

    // Semi-required properties
    public int getCost() {
        return cost;
    }
    public boolean isTechAttack() {
        return techAttack;
    }
    // Optional properties
    public RangeTag[] getRange() {
        if (range == null) {
            return range;
        }

        return HelperMethods.copyOf(range);
    }
    public Damage[] getDamage() {
        if (damage == null) {
            return damage;
        }

        return HelperMethods.copyOf(damage);
    }
    // Semi-required properties
    private void setCost(int cost) {
        if (cost < 0) {
            this.cost = IActionData.costDefault;
            return;
        }
        this.cost = cost;
    }
    private void setTechAttack(TriState techAttack) {
        boolean value;
        String activation;

        HelperMethods.checkObject("techAttack", techAttack);
        if (techAttack == TriState.UNSET) {
            this.techAttack = IActionData.techAttackDefault;
            return;
        }
        value = techAttack.toBoolean();
        activation = this.activation.getValue();
        if (value) {
            // this.activation MUST be a tech attack of some kind
            if (! (activation.equals("quick tech")
                || activation.equals("full tech"))) {
                throw new IllegalArgumentException("Attempted to set"
                    + " this.techAttack to true when this.activation is: \""
                    + activation + "\" (not \"quick tech\" or \"full tech\")");
            }
        }
        this.techAttack = value;
    }
    // Optional properties
    private void setRange(RangeTag[] range) {
        if (range != null) {
            HelperMethods.checkObjectArray("range", range);
            range = HelperMethods.copyOf(range);
        }
        this.range = range;
    }
    private void setDamage(Damage[] damage) {
        if (damage != null) {
            HelperMethods.checkObjectArray("damage", damage);
            damage = HelperMethods.copyOf(damage);
        }
        this.damage = damage;
    }

    private static String toActionName(String itemName) {
        HelperMethods.checkString("itemName", itemName);
        itemName = itemName.toUpperCase();

        return "Activate " + itemName;
    }
}
