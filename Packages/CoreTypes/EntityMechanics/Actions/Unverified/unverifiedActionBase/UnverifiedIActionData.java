package Packages.CoreTypes.EntityMechanics.Actions.Unverified.unverifiedActionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.Actions.Unverified.UnverifiedActionBase;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationType.Unverified.UnverifiedActivationType;
import Packages.CoreTypes.EntityMechanics.ActivationType.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Unverified.UnverifiedFrequency;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.Frequency;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Unverified.UnverifiedSynergyLocation;
import Packages.CoreTypes.EntityMechanics.SynergyLocationSystem.Verified.SynergyLocation;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents a single unverified action granted by a core bonus, core power,
 *     frame trait, system, talent rank, or weapon. Contains information about
 *     the action's name, activation speed, and what it does, among other
 *     properties.
 * 
 * Requires an action name, activation speed, and a detailed description to be
 *     instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class UnverifiedIActionData extends UnverifiedActionBase
    implements UnverifiedData<UnverifiedIActionData, IActionData> {
    // TODO: find a way to override UnverifiedActionBase's documentation
    /**
     * Whether this action can be used while in pilot form. Not applicable for
     *     actions that belong to a Deployable, for instance.
     * Default value: false.
     */
    // protected boolean pilot;
    // TODO: find a way to override UnverifiedActionBase's documentation
    /**
     * Whether this action can be used while in a mech. Not applicable for
     *     actions that belong to a Deployable, for instance.
     * Default value: true.
     */
    // protected boolean mech;
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
    protected int cost;
    /**
     * The default value for UnverifiedIActionData.costDefault.
     */
    protected static final int costDefault = -1;
    /**
     * Whether this action is a tech attack. Can only be true if this.activation
     *     is a tech action of some kind.
     * Default value: false.
     */
    protected boolean techAttack;
    /**
     * The default value for UnverifiedIActionData.techAttack.
     */
    protected static final boolean techAttackDefault = false;

    // Optional properties
    /**
     * The ranges associated with this action, if there are any.
     * Can be any RangeTag[] that does not contain null elements. Can be null.
     * Elements can be any RangeTag.
     */
    protected RangeTag[] range;
    /**
     * The damages associated with this action, if there are any.
     * Can be any Damage[] that does not contain null elements. Can be null.
     * Elements can be any Damage.
     */
    protected Damage[] damage;

    /**
     * A constructor using all possible properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:                 PROVIDED
     */
    public UnverifiedIActionData(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger, Callable method,
        String requiredInitialConditions, UnverifiedActivationType activation,
        UnverifiedFrequency frequency,
        UnverifiedSynergyLocation[] synergyLocations,
        // Semi-required properties
        int cost, TriState techAttack,
        // Optional properties
        RangeTag[] range, Damage[] damage
    ) {
        // ActionBase properties
        super(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, method, requiredInitialConditions, activation, frequency,
            synergyLocations);
        // Semi-required properties
        setCost(cost);
        setTechAttack(techAttack);
        // Optional properties
        setRange(range);
        setDamage(damage);
    }
    /**
     * A constructor using all possible properties except the optional
     *     properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public UnverifiedIActionData(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive, String trigger,
        UnverifiedActivationType activation, UnverifiedFrequency frequency,
        // Semi-required properties
        int cost, TriState techAttack
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, null, null, activation,
            frequency, null, cost, techAttack, null,
            null);
    }
    /**
     * A constructor using all possible properties except the optional and
     *     conditionally required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public UnverifiedIActionData(
        // ActionBase properties
        String name, String detailedDescription, TriState pilot, TriState mech,
        String[] confirm, TriState hideActive,
        UnverifiedActivationType activation,
        // Semi-required properties
        int cost, TriState techAttack
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            null, null, null,
            activation, null, null, cost,
            techAttack, null, null);
    }
    /**
     * A constructor using all possible properties except the optional and semi-
     *     required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public UnverifiedIActionData(
        // ActionBase properties
        String name, String detailedDescription, String trigger,
        UnverifiedActivationType activation, UnverifiedFrequency frequency
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, null, null,
            null, activation, null,
            null, UnverifiedIActionData.costDefault,
            TriState.UNSET, null, null);
    }
    /**
     * A constructor using only the required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    public UnverifiedIActionData(
        // ActionBase properties
        String name, String detailedDescription,
        UnverifiedActivationType activation
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, null, null,
            null, activation, null,
            null, UnverifiedIActionData.costDefault,
            TriState.UNSET, null, null);
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
    protected void setCost(int cost) {
        if (cost < 0) {
            this.cost = UnverifiedIActionData.costDefault;
            return;
        }
        this.cost = cost;
    }
    protected void setTechAttack(boolean techAttack) {
        this.techAttack = techAttack;
    }
    // Optional properties
    protected void setRange(RangeTag[] range) {
        if (range != null) {
            HelperMethods.checkObjectArray("range", range);
            range = HelperMethods.copyOf(range);
        }
        this.range = range;
    }
    protected void setDamage(Damage[] damage) {
        if (damage != null) {
            HelperMethods.checkObjectArray("damage", damage);
            damage = HelperMethods.copyOf(damage);
        }
        this.damage = damage;
    }

    @Override
    public Class<UnverifiedIActionData> getUnverifiedType() {
        return UnverifiedIActionData.class;
    }
    @Override
    public Class<IActionData> getVerifiedType() {
        return IActionData.class;
    }
    @Override
    public IActionData verify() {
        ActivationType activation = this.activation.verify();
        Frequency frequency = null;
        SynergyLocation[] synergyLocations = null;

        if (this.frequency != null) {
            frequency = this.frequency.verify();
        }
        if (this.synergyLocations != null) {
            synergyLocations =
                new SynergyLocation[this.synergyLocations.length];
            for (int i = 0; i < this.synergyLocations.length; i++) {
                synergyLocations[i] = this.synergyLocations[i].verify();
            }
        }

        return new IActionData(this.name, this.detail.toString(),
            TriState.toTriState(this.pilot), TriState.toTriState(this.mech),
            this.confirm, TriState.toTriState(this.hideActive),
            this.trigger.toString(), this.method, this.init.toString(),
            activation, frequency, synergyLocations, this.cost,
            TriState.toTriState(this.techAttack), this.range, this.damage);
    }
    public static String itemNameToActionName(String itemName) {
        HelperMethods.checkString("itemName", itemName);

        return "Activate " + itemName;
    }
    protected void setTechAttack(TriState techAttack) {
        boolean value;
        String activation;

        HelperMethods.checkObject("techAttack", techAttack);
        if (techAttack == TriState.UNSET) {
            setTechAttack(UnverifiedIActionData.techAttackDefault);
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
        setTechAttack(value);
    }
}
