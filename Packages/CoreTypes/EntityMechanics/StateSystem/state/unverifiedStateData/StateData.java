package Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import Packages.CoreTypes.VueHTMLString;

public class StateData {
    // Required properties
    /**
     * This State's name (i.e. "Immobilized" or "Danger Zone").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The URL to this State's icon, represented as a String (i.e. TODO: add
     *     example).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String iconURLRaw;
    /**
     * Whether this State is a status or condition.
     * true: Status
     * false: Condition
     */
    private boolean isStatus;
    /**
     * TODO: add documentation
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    private VueHTMLString effects;
    /**
     * Whether mechs are affected by this State.
     */
    private boolean mechAffected;
    /**
     * Whether pilots are affected by this State.
     */
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

    public StateData(String name, String iconURL, boolean isStatus,
        String effects, boolean isMechAffected, boolean isPilotAffected,
        String terse, StateData[] stateEffects) {
        // Required properties
        setName(name);
        setIconURLRaw(iconURL);
        setIsStatus(isStatus);
        setEffects(effects);
        setMechAffected(isMechAffected);
        setPilotAffected(isPilotAffected);

        // Optional properties
        // setIconURL omitted because it's already set by setIconURLRaw
        setTerse(terse);
        setStateEffects(stateEffects);
    }
    public StateData(String name, String iconURL, boolean isStatus,
        String effects, boolean isMechAffected, boolean isPilotAffected) {
        this(name, iconURL, isStatus, effects, isMechAffected, isPilotAffected,
            null, null);
    }

    // Required properties
    public String getName() {
        return name;
    }
    public String getIconURLRaw() {
        return iconURLRaw;
    }
    public boolean isStatus() {
        return isStatus;
    }
    public VueHTMLString getEffects() {
        return effects;
    }
    public boolean isMechAffected() {
        return mechAffected;
    }
    public boolean isPilotAffected() {
        return pilotAffected;
    }
    // Optional properties
    public URL getIconURL() {
        return iconURL;
    }
    public String getTerse() {
        return terse;
    }
    public StateData[] getStateEffects() {
        return stateEffects;
    }
    // Required properties
    private void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    private void setIconURLRaw(String iconURLRaw) {
        HelperMethods.checkString("iconURLRaw", iconURLRaw);
        iconURLRaw = iconURLRaw.toLowerCase();
        this.iconURLRaw = iconURLRaw;
        setIconURL(iconURLRaw);
    }
    private void setIsStatus(boolean isStatus) {
        this.isStatus = isStatus;
    }
    private void setEffects(VueHTMLString effects) {
        HelperMethods.checkVueHTMLString("effects", effects);
        this.effects = effects;
    }
    private void setMechAffected(boolean mechAffected) {
        this.mechAffected = mechAffected;
    }
    private void setPilotAffected(boolean pilotAffected) {
        this.pilotAffected = pilotAffected;
    }
    // Optional properties
    private void setIconURL(URL iconURL) {
        this.iconURL = iconURL;
    }
    private void setTerse(String terse) {
        if (terse != null) {
            HelperMethods.checkString("terse", terse);
        }
        this.terse = terse;
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

    private void setEffects(String effects) {
        HelperMethods.checkString("effects", effects);
        setEffects(new VueHTMLString(effects));
    }
    private void setIconURL(String iconURL) {
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
