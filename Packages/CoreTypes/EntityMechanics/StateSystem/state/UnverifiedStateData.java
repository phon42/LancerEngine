package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import java.net.URL;
import MainBranch.Database;
import MainBranch.HelperMethods;
import MainBranch.database.FileOperations;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.UnverifiedStateData;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;

public class UnverifiedStateData
    implements UnverifiedData<UnverifiedStateData, StateData> {
    // Required properties
    /**
     * This State's name (i.e. "Immobilized" or "Danger Zone").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    protected String name;
    /**
     * The URL to this State's icon, represented as a String (i.e. TODO: add
     *     example).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String iconURLRaw;
    /**
     * Whether this State is a status or condition.
     * true: Status
     * false: Condition
     */
    protected boolean isStatus;
    /**
     * TODO: add documentation
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    protected VueHTMLString effects;
    /**
     * Whether mechs are affected by this State.
     */
    protected boolean mechAffected;
    /**
     * Whether pilots are affected by this State.
     */
    protected boolean pilotAffected;

    // Optional properties
    protected URL iconURL;
    protected String terse;
    /**
     * Any States which this State causes to exist.
     * Can be any String[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     */
    private String[] stateEffects;

    public UnverifiedStateData(String name, String iconURL, boolean isStatus,
        String effects, boolean isMechAffected, boolean isPilotAffected,
        String terse, String[] stateEffects) {
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
    public UnverifiedStateData(String name, String iconURL, boolean isStatus,
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
    // getStateEffects purposefully removed
    // Required properties
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        this.name = name;
    }
    protected void setIconURLRaw(String iconURLRaw) {
        HelperMethods.checkString("iconURLRaw", iconURLRaw);
        iconURLRaw = iconURLRaw.toLowerCase();
        this.iconURLRaw = iconURLRaw;
        setIconURL(iconURLRaw);
    }
    protected void setIsStatus(boolean isStatus) {
        this.isStatus = isStatus;
    }
    protected void setEffects(VueHTMLString effects) {
        HelperMethods.checkVueHTMLString("effects", effects);
        this.effects = effects;
    }
    protected void setMechAffected(boolean mechAffected) {
        this.mechAffected = mechAffected;
    }
    protected void setPilotAffected(boolean pilotAffected) {
        this.pilotAffected = pilotAffected;
    }
    // Optional properties
    protected void setIconURL(URL iconURL) {
        this.iconURL = iconURL;
    }
    protected void setTerse(String terse) {
        if (terse != null) {
            HelperMethods.checkString("terse", terse);
        }
        this.terse = terse;
    }
    /**
     * Sets this.stateEffects to the provided value.
     * @param stateEffects a String[] which cannot be of length 0 or contain
     *     null elements.
     * @throws IllegalArgumentException if stateEffects is of length 0 or
     *     contains null elements.
     */
    protected void setStateEffects(String[] stateEffects) {
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

    @Override
    public Class<UnverifiedStateData> getUnverifiedType() {
        return UnverifiedStateData.class;
    }
    @Override
    public Class<StateData> getVerifiedType() {
        return StateData.class;
    }
    @Override
    public StateData verify() {
        StateData[] stateEffects;

        stateEffects = new StateData[this.stateEffects.length];
        for (int i = 0; i < stateEffects.length; i++) {
            stateEffects[i] = Database.getState(this.stateEffects[i]);
        }

        return new StateData(this.name, this.iconURLRaw, this.isStatus,
            this.effects.toString(), this.mechAffected, this.pilotAffected,
            terse, stateEffects);
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
        } catch (IllegalStateException exception) {}
        setIconURL(url);
    }
}
