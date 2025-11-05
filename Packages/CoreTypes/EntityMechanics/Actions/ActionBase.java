package Packages.CoreTypes.EntityMechanics.Actions;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.HTMLString;

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
    // Optional property
    /**
     * A method allowing the program to perform the action automatically (unable
     *     to provide an example).
     * Can be any Callable. Can be null.
     */
    protected Callable method;

    protected ActionBase(String name, ActivationType activation,
        HTMLString detailedDescription, Callable method) {
        HelperMethods.verifyConstructor();
        setName(name);
        setActivation(activation);
        setDetail(detailedDescription);
        setMethod(method);
    }
    protected ActionBase(String name, ActivationType activation,
        HTMLString detailedDescription) {
        this(name, activation, detailedDescription, null);
    }
    protected ActionBase(ActionBase actionBase) {
        setName(actionBase.name);
        setActivation(actionBase.activation);
        setDetail(actionBase.detail);
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
    // Optional property
    protected void setMethod(Callable method) {
        this.method = method;
    }
}
