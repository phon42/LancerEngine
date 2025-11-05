package Packages.CoreTypes.EntityMechanics.Actions;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.HTMLString;

public class ActionBase {
    // Required properties
    private String name;
    private ActivationType activation;
    private HTMLString detail;
    // Optional property
    private Callable method;

    protected ActionBase(String name, ActivationType activation,
        HTMLString detailedDescription) {
        HelperMethods.verifyConstructor();
        setName(name);
        setActivation(activation);
        setDetail(detailedDescription);
    }
    protected ActionBase(ActionBase actionBase) {
        setName(actionBase.name);
        setActivation(actionBase.activation);
        setDetail(actionBase.detail);
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
