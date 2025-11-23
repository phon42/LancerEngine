package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import java.net.URL;
import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString;

public class StateData {
    // Required properties
    /**
     * This State's name (i.e. "Immobilized" or "Danger Zone").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    private String iconURLRaw;
    /**
     * Whether this State is a status or condition.
     * true: Status
     * false: Condition
     * Can be any boolean.
     */
    private boolean status;
    private VueHTMLString effects;
    private boolean mechAffected;
    private boolean pilotAffected;

    // Optional properties
    private URL iconURL;
    private String terse;
    /**
     * Any States which this State causes to exist.
     * Can be any StateData[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private StateData[] stateEffects;

    public String getName() {
        return name;
    }
    public StateData[] getStateEffects() {
        return stateEffects;
    }
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    /**
     * Sets this.stateEffects to the provided value.
     * @param stateEffects a StateData[] which cannot be of length 0 or contain
     *     null elements.
     * @throws IllegalArgumentException if stateEffects is of length 0 or
     *     contains null elements.
     */
    protected void setStateEffects(StateData[] stateEffects) {
        if (stateEffects != null) {
            HelperMethods.checkObjectArray("New state effects",
                stateEffects);
            if (stateEffects.length == 0) {
                throw new IllegalArgumentException("stateEffects array is of"
                    + " length 0");
            }
            stateEffects = HelperMethods.copyOf(stateEffects);
        }
        this.stateEffects = stateEffects;
    }
}
