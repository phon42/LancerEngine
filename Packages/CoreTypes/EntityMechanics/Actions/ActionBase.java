package Packages.CoreTypes.EntityMechanics.Actions;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.TriState;

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
     * Can be any HTMLString. Cannot be null.
     * Case-sensitive.
     */
    protected HTMLString detail;

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

    // Optional property
    /**
     * A method allowing the program to perform the action automatically (unable
     *     to provide an example).
     * Can be any Callable. Can be null.
     */
    protected Callable method;

    protected ActionBase(
        // Required properties
        String name, ActivationType activation, String detailedDescription,
        // Semi-required properties
        TriState pilot, TriState mech, String[] confirm,
        // Optional properties
        Callable method) {
        HelperMethods.verifyConstructor();
        // Required properties
        setName(name);
        setActivation(activation);
        setDetail(detailedDescription);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setConfirm(confirm);
        // Optional properties
        setMethod(method);
    }
    protected ActionBase(String name, ActivationType activation,
        String detailedDescription) {
        this(name, activation, detailedDescription, TriState.UNSET,
            TriState.UNSET, null, null);
    }
    protected ActionBase(ActionBase actionBase) {
        HelperMethods.checkObject("actionBase", actionBase);
        setName(actionBase.name);
        setActivation(actionBase.activation);
        setDetail(actionBase.detail);
        setMethod(actionBase.method);
        setPilot(actionBase.pilot);
        setMech(actionBase.mech);
        setConfirm(actionBase.confirm);
        setMethod(actionBase.method);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public ActivationType getActivation() {
        return new ActivationType(activation);
    }
    public HTMLString getDetail() {
        return new HTMLString(detail);
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
    // Optional property
    public Callable getMethod() {
        if (method != null) {
            return HelperMethods.copyOf(method);
        }

        return method;
    }
    // Required properties
    protected void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    protected void setActivation(ActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        activation = new ActivationType(activation);
        this.activation = activation;
    }
    protected void setDetail(HTMLString detail) {
        HelperMethods.checkObject("detail", detail);
        detail = new HTMLString(detail);
        this.detail = detail;
    }
    protected void setDetail(String detail) {
        HelperMethods.checkString("detail", detail);
        setDetail(new HTMLString(detail));
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
    // Optional property
    protected void setMethod(Callable method) {
        this.method = method;
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
}
