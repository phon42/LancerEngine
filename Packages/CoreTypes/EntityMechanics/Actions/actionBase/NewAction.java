package Packages.CoreTypes.EntityMechanics.Actions.actionBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Callable;
import Packages.CoreTypes.EntityMechanics.Actions.ActionBase;
import Packages.CoreTypes.EntityMechanics.ActivationType;
import Packages.CoreTypes.EntityMechanics.Synergy;
import Packages.CoreTypes.HTMLString;
import Packages.CoreTypes.TriState;

/**
 * Represents a single action; one of the actions available to every pilot
 *     or mech. Contains information about the action's id, name, activation
 *     speed, and what it does.
 * 
 * Requires an action name, activation speed, detailed description, and id to be
 *     instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class NewAction extends ActionBase {
    // Required properties
    /**
     * The ID of the action (i.e. "act_move").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Whether or not this action can be used by a Pilot.
     * Default value: false.
     */
    private boolean pilot;
    /**
     * The default value for Action.pilot.
     */
    private static final boolean pilotDefault = false;
    /**
     * Whether or not this action can be used by a Mech.
     * Default value: true.
     */
    private boolean mech;
    /**
     * The default value for Action.mech.
     */
    private static final boolean mechDefault = true;
    /**
     * An array of lines of flavor text that can be printed out when this action
     *     is executed (i.e. a String[] containing the String "REACTOR LIMITER"
     *     " DISABLED. CORE TEMPERATURES RISING."").
     * Can be any String[] that does not contain null elements. Cannot be null.
     * Default value: new String[] {"ACTIVATION CONFIRMED."}.
     * Elements are case-sensitive.
     */
    private String[] confirm;
    /**
     * The default value for Action.confirm.
     */
    private static final String[] confirmDefault = new String[] {"ACTIVATION"
        + " CONFIRMED."};
    /**
     * Whether or not to ignore the 1 use/round limit on most actions.
     * Default value: false.
     */
    private boolean ignoreUsed;

    /**
     * The default value for Action.ignoreUsed.
     */
    private static final boolean ignoreUsedDefault = false;
    /**
     * The heat cost associated with this action, if there is one.
     * Must be > -1.
     * Default value: 0.
     */
    private int heatCost;
    /**
     * The default value for Action.heatCost.
     */
    private static final int heatCostDefault = 0;
    // Optional properties
    /**
     * A very short description of what this action does (i.e. "Move your
     *     character up to its Speed in any direction.").
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String terse;
    /**
     * An array of locations that this action is connected to (i.e. a Synergy[]
     *     containing a Synergy representing "overcharge").
     * Can be any Synergy[] that does not contain null elements. Can be null.
     */
    private Synergy[] synergyLocations;
    // TODO: add example
    /**
     * An optional String that will be written to the LancerCharacter's combat
     *     log when the player commits this action (i.e. INSERT EXAMPLE).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String log;

    public NewAction(
        // ActionBase properties
        String name, ActivationType activation, HTMLString detailedDescription,
        Callable method,
        // Action required properties
        String id,
        // Action semi-required properties
        TriState pilot, TriState mech, String[] confirm, TriState ignoreUsed,
        int heatCost,
        // Action optional properties
        String terse, Synergy[] synergyLocations, String log) {
        super(name, activation, detailedDescription, method);
        // Required properties
        setID(id);
        // Semi-required properties
        setPilotAndMech(pilot, mech);
        setConfirm(confirm);
        setIgnoreUsed(ignoreUsed);
        setHeatCost(heatCost);
        // Optional properties
        setTerse(terse);
        setSynergyLocations(synergyLocations);
        setLog(log);
    }
    public NewAction(String name, ActivationType activation,
        HTMLString detailedDescription, String id) {
        super(name, activation, detailedDescription);
        // Required properties
        setID(id);
        // Semi-required properties
        setPilotAndMech(TriState.UNSET, TriState.UNSET);
        setConfirm(null);
        setIgnoreUsed(TriState.UNSET);
        setHeatCost(-1);
    }
    public NewAction(NewAction action) {
        super(action);
        // Required properties
        setID(action.id);
        // Semi-required properties
        setPilot(action.pilot);
        setMech(action.mech);
        setConfirm(action.confirm);
        setIgnoreUsed(TriState.toTriState(action.ignoreUsed));
        setHeatCost(action.heatCost);
        // Optional properties
        setTerse(action.terse);
        setSynergyLocations(action.synergyLocations);
        setLog(action.log);
    }

    // Required properties
    public String getID() {
        return id;
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
    public boolean isIgnoreUsed() {
        return ignoreUsed;
    }
    public int getHeatCost() {
        return heatCost;
    }
    // Optional properties
    public String getTerse() {
        return terse;
    }
    public Synergy[] getSynergyLocations() {
        if (synergyLocations == null) {
            return synergyLocations;
        }

        return HelperMethods.copyOf(synergyLocations);
    }
    public String getLog() {
        return log;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    // Semi-required properties
    private void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    private void setMech(boolean mech) {
        this.mech = mech;
    }
    private void setConfirm(String[] confirm) {
        if (confirm == null) {
            confirm = HelperMethods.copyOf(NewAction.confirmDefault);
        } else {
            HelperMethods.checkObjectArray("confirm", confirm);
            confirm = HelperMethods.copyOf(confirm);
        }
        this.confirm = confirm;
    }
    private void setIgnoreUsed(TriState ignoreUsed) {
        HelperMethods.checkObject("ignoreUsed", ignoreUsed);
        if (ignoreUsed == TriState.UNSET) {
            this.ignoreUsed = NewAction.ignoreUsedDefault;
            return;
        }
        this.ignoreUsed = ignoreUsed.toBoolean();
    }
    private void setHeatCost(int heatCost) {
        if (heatCost < 0) {
            this.heatCost = NewAction.heatCostDefault;
            return;
        }
        this.heatCost = heatCost;
    }
    // Optional properties
    private void setTerse(String terse) {
        if (terse == null) {
            this.terse = terse;
            return;
        }
        if (terse.equals("")) {
            throw new IllegalArgumentException("terse is \"\"");
        }
        this.terse = terse;
    }
    private void setSynergyLocations(Synergy[] synergyLocations) {
        if (synergyLocations == null) {
            this.synergyLocations = synergyLocations;
            return;
        }
        HelperMethods.checkObjectArray("synergyLocations",
            synergyLocations);
        synergyLocations = HelperMethods.copyOf(synergyLocations);
        this.synergyLocations = synergyLocations;
    }
    private void setLog(String log) {
        if (log == null) {
            this.log = log;
            return;
        }
        HelperMethods.checkString("log", log);
        this.log = log;
    }

    private void setPilotAndMech(TriState pilot, TriState mech) {
        HelperMethods.checkObject("pilot", pilot);
        HelperMethods.checkObject("mech", mech);
        if (pilot == TriState.UNSET) {
            setPilot(NewAction.pilotDefault);
        } else {
            setPilot(pilot.toBoolean());
        }
        if (mech == TriState.UNSET) {
            setMech(NewAction.mechDefault);
        } else {
            setMech(mech.toBoolean());
        }
    }
}