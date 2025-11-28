package Packages.CoreTypes.EntityMechanics.Actions;

import java.util.HashMap;
import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Frequency;
import Packages.CoreTypes.EntityMechanics.SynergyLocation;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.TriState;

/**
 * See pgs. 61 - 64, 68 - 76, and 107 for more information.
 * See also https://github.com/massif-press/lancer-data/blob/master/README.md#actions-actionsjson
 *     and https://github.com/massif-press/lancer-data/blob/master/README.md#item-actions.
 */
/**
 * Represents a single action of any type. Contains information about the
 *     action's name, activation speed, and a detailed description of what it
 *     does, among other properties.
 * 
 * Requires an action name, activation speed, and a detailed description to be
 *     instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class ActionBase {
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
     * The activation type of this action (i.e. an ActivationType representing a
     *     free action).
     * Can be any ActivationType. Cannot be null.
     */
    protected ActivationType activation;
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
     * The default value for Action.pilot.
     */
    private static final boolean pilotDefault = false;
    /**
     * Whether this action can be used while in a mech.
     * Default value: true.
     */
    protected boolean mech;
    /**
     * The default value for ActionBase.mech.
     */
    private static final boolean mechDefault = true;
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
     * The default value for ActionBase.confirm.
     */
    private static final String[] confirmDefault = new String[] {"ACTIVATION"
        + " CONFIRMED."};
    /**
     * Unknown purpose.
     * Is set to true for the Move and Free Action Actions in the base Lancer
     *     LCP. Unused in all other locations.
     * Default value: false.
     */
    protected boolean hideActive;
    /**
     * The default value for ActionBase.hideActive.
     */
    private static final boolean hideActiveDefault = false;

    // Conditionally required properties
    /**
     * The frequency with which this action can be used, if there is one (i.e. a
     *     Frequency that represents "1/round").
     * Required when this.activation is a reaction.
     * When required:
     *     Can be any Frequency. Cannot be null.
     * When not required:
     *     Default value: 1/round if this.ignoreUsed is false and unlimited if
     *         it's true.
     *     Must be a Frequency that is either 1/round or unlimited. Cannot be
     *         null.
     * Can be any Frequency with the restrictions described above. Cannot be
     *     null.
     */
    protected Frequency frequency;
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
     * An array of locations that this action is connected to (i.e. a
     *     SynergyLocation[] containing a SynergyLocation representing
     *     "overcharge").
     * Can be any SynergyLocation[] that does not contain null elements. Can be
     *     null.
     */
    private SynergyLocation[] synergyLocations;
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
     * Stores a HashMap helpful in calculating ActionBase.type.
     */
    private static final HashMap<String, String> typeMap = new HashMap<>();

    static {
        ActionBase.typeMap.put("free", "free");
        ActionBase.typeMap.put("protocol", "free");
        ActionBase.typeMap.put("quick", "quick");
        ActionBase.typeMap.put("full", "full");
        ActionBase.typeMap.put("invade", "quick");
        ActionBase.typeMap.put("full tech", "full");
        ActionBase.typeMap.put("quick tech", "quick");
        ActionBase.typeMap.put("reaction", "reaction");
        ActionBase.typeMap.put("downtime", "downtime");
    }

    protected ActionBase(
        // Required properties
        String name, ActivationType activation, String detailedDescription,
        // Semi-required properties
        TriState pilot, TriState mech, String[] confirm, TriState hideActive,
        // Conditionally required properties
        Frequency frequency, String trigger,
        // Optional properties
        Callable method, SynergyLocation[] synergyLocations,
        String requiredInitialConditions
    ) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setActivation(activation);
        setDetail(detailedDescription);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setConfirm(confirm);
        setHideActive(hideActive);
        // Conditionally required properties
        setFrequency(calculateFrequency(frequency));
        setTrigger(trigger);
        // Optional properties
        setMethod(method);
        setSynergyLocations(synergyLocations);
        setInit(requiredInitialConditions);
        // Verify everything
        verifyProperties();
        // Helper property
        setType(calculateType());
    }
    protected ActionBase(String name, ActivationType activation,
        String detailedDescription) {
        this(name, activation, detailedDescription, TriState.UNSET,
            TriState.UNSET, null, TriState.UNSET, null,
            null, null, null,
            null);
    }
    protected ActionBase(ActionBase actionBase) {
        HelperMethods.checkObject("actionBase", actionBase);
        // Required properties
        setName(actionBase.name);
        setActivation(actionBase.activation);
        setDetail(actionBase.detail);
        // Semi-required properties
        setMethod(actionBase.method);
        setPilot(actionBase.pilot);
        setMech(actionBase.mech);
        setConfirm(actionBase.confirm);
        setHideActive(actionBase.hideActive);
        // Conditionally required properties
        setFrequency(actionBase.frequency);
        setTrigger(actionBase.trigger);
        // Optional properties
        setMethod(actionBase.method);
        setSynergyLocations(actionBase.synergyLocations);
        setInit(actionBase.init);
        // Verify everything
        verifyProperties();
        // Helper property
        setType(calculateType());
    }

    // Required properties
    public String getName() {
        return name;
    }
    public ActivationType getActivation() {
        return activation;
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
    // Conditionally required properties
    public Frequency getFrequency() {
        return new Frequency(frequency);
    }
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
    public SynergyLocation[] getSynergyLocations() {
        if (synergyLocations == null) {
            return synergyLocations;
        }

        return HelperMethods.copyOf(synergyLocations);
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
    protected void setActivation(ActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        this.activation = activation;
    }
    protected void setDetail(VueHTMLString detail) {
        HelperMethods.checkObject("detail", detail);
        this.detail = detail;
    }
    protected void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        setDetail(new VueHTMLString(detail));
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
            confirm = HelperMethods.copyOf(ActionBase.confirmDefault);
        } else {
            HelperMethods.checkObjectArray("confirm", confirm);
            confirm = HelperMethods.copyOf(confirm);
        }
        this.confirm = confirm;
    }
    protected void setHideActive(boolean hideActive) {
        this.hideActive = hideActive;
    }
    // Conditionally required properties
    protected void setFrequency(Frequency frequency) {
        HelperMethods.checkObject("frequency", frequency);
        frequency = new Frequency(frequency);
        this.frequency = frequency;
    }
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
    private void setSynergyLocations(SynergyLocation[] synergyLocations) {
        if (synergyLocations == null) {
            this.synergyLocations = synergyLocations;
            return;
        }
        HelperMethods.checkObjectArray("synergyLocations",
            synergyLocations);
        synergyLocations = HelperMethods.copyOf(synergyLocations);
        this.synergyLocations = synergyLocations;
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

    protected void setPilotAndMech(TriState pilot, TriState mech) {
        HelperMethods.checkObject("pilot", pilot);
        HelperMethods.checkObject("mech", mech);
        if (pilot == TriState.UNSET) {
            setPilot(ActionBase.pilotDefault);
        } else {
            setPilot(pilot.toBoolean());
        }
        if (mech == TriState.UNSET) {
            setMech(ActionBase.mechDefault);
        } else {
            setMech(mech.toBoolean());
        }
    }
    protected void setTrigger(String trigger) {
        if (trigger == null) {
            this.trigger = null;
        }
        setTrigger(new VueHTMLString(trigger));
    }
    protected void setHideActive(TriState hideActive) {
        if (hideActive == TriState.UNSET) {
            this.hideActive = ActionBase.hideActiveDefault;
        } else {
            this.hideActive = hideActive.toBoolean();
        }
    }
    protected void setInit(String init) {
        if (init == null) {
            this.init = null;
        }
        setInit(new VueHTMLString(init));
    }
    protected void verifyProperties() {
        if (this.activation.getType().equals("reaction")) {
            // do nothing about this.frequency, because this.frequency cannot be
            //     null.
            if (this.trigger == null) {
                throw new IllegalStateException("this.trigger cannot be null"
                    + " when this.activation is a reaction");
            }
            // do nothing further about this.trigger because it cannot be ""
        } else {
            if (this.trigger != null) {
                throw new IllegalStateException("this.trigger must be null"
                    + " when this.activation is not a reaction");
            }
        }
    }
    /**
     * Calculates the correct value for the helper property ActionBase.type.
     * @return a String containing the correct value.
     */
    protected String calculateType() {
        String activationType;
        String result;

        activationType = this.activation.getType();
        if (this.name.equals("Move")
            || this.name.equals("Boost")) {
            result = "move";
        } else {
            if (ActionBase.typeMap.containsKey(activationType)) {
                result = ActionBase.typeMap.get(activationType);
            } else {
                throw new IllegalStateException("this.activation.type is: \""
                    + activationType + "\" which could not be found within"
                    + " ActionBase.typeMap");
            }
        }

        return result;
    }
    protected Frequency calculateFrequency(Frequency frequency) {
        if (this.activation.getType().equals("reaction")) {
            // this.frequency is required; therefore, it can be any frequency
            //     but cannot be null
            return frequency;
        }

        return frequency;
    }
}
