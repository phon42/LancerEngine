package Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.UnverifiedStateData;

public class StateData extends UnverifiedStateData {
    // Optional property
    /**
     * Any States which this State causes to exist.
     * Can be any StateData[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private StateData[] stateEffects;

    public StateData(String name, String iconURL, boolean isStatus,
        String effects, boolean isMechAffected, boolean isPilotAffected,
        String terse, StateData[] stateEffects) {
        super(name, iconURL, isStatus, effects, isMechAffected, isPilotAffected,
            terse, null);
        // Optional property
        setStateEffects(stateEffects);
    }
    public StateData(String name, String iconURL, boolean isStatus,
        String effects, boolean isMechAffected, boolean isPilotAffected) {
        this(name, iconURL, isStatus, effects, isMechAffected, isPilotAffected,
            null, null);
    }

    // Optional property
    public StateData[] getStateEffects() {
        return stateEffects;
    }
    // Optional property
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

    protected void setEffects(String effects) {
        HelperMethods.checkString("effects", effects);
        setEffects(new VueHTMLString(effects));
    }
    protected void setIconURL(String iconURL) {
        URL url = null;

        HelperMethods.checkString("iconURL", iconURL);
        try {
            url = FileOperations.toURLCaught(iconURL);
        } catch (IllegalArgumentException exception) {}
        setIconURL(url);
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
    public StateData removeStateEffect(int index) {
        StateData removedStateEffect;
        StateData[] stateEffects;

        if (this.stateEffects.length == 0) {
            throw new IllegalArgumentException("Attempted to call"
                + " StateData.removeEffect() when this.stateEffects.length is"
                + " 0");
        }
        if (index < 0 || index > this.stateEffects.length) {
            throw new IllegalArgumentException("index value: " + index + " is"
                + " out of bounds for a StateData[] of length: "
                + this.stateEffects.length);
        }
        removedStateEffect = this.stateEffects[index];
        stateEffects = new StateData[this.stateEffects.length - 1];
        for (int i = 0; i < stateEffects.length; i++) {
            if (i < index) {
                stateEffects[i] = this.stateEffects[i];
                continue;
            }
            if (i > index) {
                stateEffects[i] = this.stateEffects[i - 1];
            }
        }
        setStateEffects(stateEffects);

        return removedStateEffect;
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
        for (StateData state : this.stateEffects) {
            if (state.getName().equals(stateName)) {
                isPresent = true;
                break;
            }
            isPresent = isPresent || state.hasState(stateName);
        }

        return isPresent;
    }
}
