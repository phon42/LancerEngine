package Packages.CoreTypes.EntityMechanics.Actions;

import java.util.HashMap;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson
 *     and https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents the shared properties of a single action of any type, be it
 *     verified or unverified data. Contains information about the action's
 *     name and a detailed description of what it does, among other properties.
 * 
 * Requires an action name and a detailed description of the action to be
 *     instantiated.
 * 
 * Used in ActionBase and UnverifiedActionBase.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class ActionBaseBase {
    // Required properties
    /**
     * The name of this action (i.e. "Move" or "Turret Attack").
     * For IActionData, might be a regular action name or something like
     *     "Activate Item Name".
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;
    /**
     * A detailed description of this action (too long to provide an example).
     * Can be any VueHTMLString. Cannot be null.
     * Case-sensitive.
     */
    protected VueHTMLString detail;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Whether this action can be used while in pilot form.
     * Default value: false.
     */
    protected boolean pilot;
    /**
     * The default value for ActionBaseBase.pilot.
     */
    protected static final boolean pilotDefault = false;
    /**
     * Whether this action can be used while in a mech.
     * Default value: true.
     */
    protected boolean mech;
    /**
     * The default value for ActionBaseBase.mech.
     */
    protected static final boolean mechDefault = true;
    /**
     * An array of lines of flavor text that can be printed out when this action
     *     is executed (i.e. a String[] containing the String "REACTOR LIMITER"
     *     " DISABLED. CORE TEMPERATURES RISING.").
     * Can be any String[] that does not contain null elements. Cannot be null.
     * Default value: new String[] {"ACTIVATION CONFIRMED."}.
     * Elements are case-sensitive.
     */
    protected String[] confirm;
    /**
     * The default value for ActionBaseBase.confirm.
     */
    protected static final String[] confirmDefault = new String[] {"ACTIVATION"
        + " CONFIRMED."};
    /**
     * Unknown purpose.
     * Is set to true for the Move and Free Action Actions in the base Lancer
     *     LCP. Unused in all other locations.
     * Default value: false.
     */
    protected boolean hideActive;
    /**
     * The default value for ActionBaseBase.hideActive.
     */
    protected static final boolean hideActiveDefault = false;

    // Conditionally required properties
    /**
     * The trigger for this action, if there is one (i.e. a VueHTMLString
     *     representing the String "You are hit by an attack and damage has"
     *     " been rolled.").
     * Required when this.activation is a reaction.
     * When required:
     *     Can be any VueHTMLString except "". Cannot be null.
     * When not required:
     *     Must be null.
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    protected VueHTMLString trigger;

    // Optional properties
    /**
     * A method allowing the program to perform the action automatically (unable
     *     to provide an example).
     * Can be any Callable. Can be null.
     */
    protected Callable method;
    /**
     * Any initial conditions required for this action, if there are any (i.e.
     *     "Requires activation of the <b>Spin Up Thrusters</b> quick action on"
     *     " your turn. If you end your turn flying, you may nominate a"
     *     " character within a Range equal to your Speed and within line of"
     *     " sight, you may use this reaction.").
     * Presence on the "Unleash SCYLLA Reaction" from the "SCYLLA-Class NHP"
     *     from Gorgon III might be a typo. Present on the Ace talent.
     * When this.activation is a reaction, can be any VueHTMLString except "".
     *     Can be null.
     * When this.activation is not a reaction, must be null.
     * Can be any VueHTMLString except "". Can be null.
     */
    protected VueHTMLString init;

    // Helper property
    /**
     * The general type of the action (i.e. "free").
     * Value is based on this.activation.type and, secondarily, this.name.
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String type;
    /**
     * Stores a HashMap helpful in calculating ActionBaseBase.type.
     */
    protected static final HashMap<String, String> typeMap = new HashMap<>();

    // TODO: create some way to modify this table
    static {
        ActionBaseBase.typeMap.put("free", "free");
        ActionBaseBase.typeMap.put("protocol", "free");
        ActionBaseBase.typeMap.put("quick", "quick");
        ActionBaseBase.typeMap.put("full", "full");
        ActionBaseBase.typeMap.put("invade", "quick");
        ActionBaseBase.typeMap.put("full tech", "full");
        ActionBaseBase.typeMap.put("quick tech", "quick");
        ActionBaseBase.typeMap.put("reaction", "reaction");
        ActionBaseBase.typeMap.put("downtime", "downtime");
    }

    /**
     * A constructor using all possible properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:                 PROVIDED
     */
    protected ActionBaseBase(
        // Required properties
        String name, String detailedDescription,
        // Semi-required properties
        TriState pilot, TriState mech, String[] confirm, TriState hideActive,
        // Conditionally required property
        String trigger,
        // Optional properties
        Callable method, String requiredInitialConditions
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setDetail(detailedDescription);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setConfirm(confirm);
        setHideActive(hideActive);
        // Conditionally required property
        setTrigger(trigger);
        // Optional properties
        setMethod(method);
        setInit(requiredInitialConditions);
    }
    /**
     * A constructor using all possible properties except the optional
     *     properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected ActionBaseBase(
        // Required properties
        String name, String detailedDescription,
        // Semi-required properties
        TriState pilot, TriState mech, String[] confirm, TriState hideActive,
        // Conditionally required property
        String trigger
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            trigger, null, null);
    }
    /**
     * A constructor using all possible properties except the optional and
     *     conditionally required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:            PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected ActionBaseBase(
        // Required properties
        String name, String detailedDescription,
        // Semi-required properties
        TriState pilot, TriState mech, String[] confirm, TriState hideActive
    ) {
        this(name, detailedDescription, pilot, mech, confirm, hideActive,
            null, null, null);
    }
    /**
     * A constructor using all possible properties except the optional and semi-
     *     required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property:     PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected ActionBaseBase(
        // Required properties
        String name, String detailedDescription,
        // Conditionally required property
        String trigger
    ) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, trigger, null,
            null);
    }
    /**
     * A constructor using only the required properties.
     * Required properties:                 PROVIDED
     * Semi-required properties:        NOT PROVIDED
     * Conditionally required property: NOT PROVIDED
     * Optional properties:             NOT PROVIDED
     */
    protected ActionBaseBase(String name, String detailedDescription) {
        this(name, detailedDescription, TriState.UNSET, TriState.UNSET,
            null, TriState.UNSET, null, null,
            null);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public VueHTMLString getDetail() {
        return detail;
    }
    // Semi-required properties
    public boolean isPilot() {
        return pilot;
    }
    public boolean isMech() {
        return mech;
    }
    public String[] getConfirm() {
        return HelperMethods.copyOf(confirm);
    }
    public boolean isHideActive() {
        return hideActive;
    }
    // Conditionally required property
    public VueHTMLString getTrigger() {
        if (trigger == null) {
            return trigger;
        }

        return trigger;
    }
    // Optional properties
    public Callable getMethod() {
        if (method != null) {
            return HelperMethods.copyOf(method);
        }

        return method;
    }
    public VueHTMLString getInit() {
        if (init == null) {
            return init;
        }

        return init;
    }
    // Helper property
    public String getType() {
        return type;
    }
    // Required properties
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    protected void setDetail(VueHTMLString detail) {
        HelperMethods.checkObject("detail", detail);
        this.detail = detail;
    }
    // Semi-optional properties
    protected void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    protected void setMech(boolean mech) {
        this.mech = mech;
    }
    protected void setConfirm(String[] confirm) {
        if (confirm == null) {
            confirm = HelperMethods.copyOf(ActionBaseBase.confirmDefault);
        } else {
            HelperMethods.checkObjectArray("confirm", confirm);
            confirm = HelperMethods.copyOf(confirm);
        }
        this.confirm = confirm;
    }
    protected void setHideActive(boolean hideActive) {
        this.hideActive = hideActive;
    }
    // Conditionally required property
    protected void setTrigger(VueHTMLString trigger) {
        if (trigger != null) {
            HelperMethods.checkVueHTMLString("trigger", trigger);
        }
        this.trigger = trigger;
    }
    // Optional properties
    protected void setMethod(Callable method) {
        this.method = method;
    }
    protected void setInit(VueHTMLString init) {
        if (init != null) {
            HelperMethods.checkVueHTMLString("init", init);
        }
        this.init = init;
    }
    // Helper property
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        this.type = type;
    }

    protected void setDetail(String detail) {
        HelperMethods.checkObject("detail", detail);
        setDetail(new VueHTMLString(detail));
    }
    protected void setPilotAndMech(TriState pilot, TriState mech) {
        HelperMethods.checkObject("pilot", pilot);
        HelperMethods.checkObject("mech", mech);
        if (pilot == TriState.UNSET) {
            setPilot(ActionBaseBase.pilotDefault);
        } else {
            setPilot(pilot.toBoolean());
        }
        if (mech == TriState.UNSET) {
            setMech(ActionBaseBase.mechDefault);
        } else {
            setMech(mech.toBoolean());
        }
    }
    protected void setTrigger(String trigger) {
        if (trigger == null) {
            this.trigger = null;
        } else {
            setTrigger(new VueHTMLString(trigger));
        }
    }
    protected void setHideActive(TriState hideActive) {
        if (hideActive == TriState.UNSET) {
            this.hideActive = ActionBaseBase.hideActiveDefault;
        } else {
            this.hideActive = hideActive.toBoolean();
        }
    }
    protected void setInit(String init) {
        if (init == null) {
            this.init = null;
        } else {
            setInit(new VueHTMLString(init));
        }
    }
    /**
     * Calculates the correct value for the helper property ActionBaseBase.type.
     * Should be overridden in every direct child.
     * @return a String containing the correct value.
     */
    protected String calculateType() {
        return "free";
    }
}
