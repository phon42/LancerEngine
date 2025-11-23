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
    /**
     * Adds a provided StateData effect to this.stateEffects.
     * @param stateEffects a StateData which cannot be null.
     * @throws IllegalArgumentException if effect is null.
     */
    public void addEffect(StateData stateEffects) {
        HelperMethods.checkObject("stateEffects", stateEffects);
        setEffects(HelperMethods.append(this.stateEffects, stateEffects));
    }
    public StateData removeEffect(int index) {
        StateData removedEffect;
        StateData[] effects;

        if (this.effects.length == 0) {
            throw new IllegalArgumentException("Attempted to call"
                + " State.removeEffect() when this.effects.length is 0");
        }
        if (index < 0 || index > this.effects.length) {
            throw new IllegalArgumentException("index value: " + index + " is"
                + " out of bounds for a State[] of length: "
                + this.effects.length);
        }
        removedEffect = this.effects[index];
        effects = new State[this.effects.length - 1];
        for (int i = 0; i < effects.length; i++) {
            if (i < index) {
                effects[i] = this.effects[i];
                continue;
            }
            if (i > index) {
                effects[i] = this.effects[i - 1];
            }
        }
        setEffects(effects);

        return removedEffect;
    }
    /**
     * Recursively checks whether any of the States this State has caused, or
     *     any of the States they have caused, and so on, are of State.name
     *     stateName.
     * @param stateName a String containing a State.name value to search for.
     *     Cannot be "" or null.
     * @return a boolean containing the result of the check.
     */
    public boolean hasState(String stateName) {
        boolean isPresent = false;

        HelperMethods.checkObject("stateName", stateName);
        // TODO: fill out once Database has an array of States set up
        // stateName is a valid State
        for (State state : this.effects) {
            if (state.getName().equals(stateName)) {
                isPresent = true;
                break;
            }
            isPresent = isPresent || state.hasState(stateName);
        }

        return isPresent;
    }
}
