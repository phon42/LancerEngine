package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;

/**
 * See pg. 78.
 * "Conditions are temporary effects caused by things like damage and electronic
 *     warfare, whereas statuses are usually effects that can’t easily be
 *     cleared."
 * - pg. 77
 */
/**
 * Represents a single condition on a mech or pilot. Contains information about
 *     the condition's properties, effect duration, its source, and the State
 *     which may have caused it.
 * 
 * Requires a StateData object, a duration, and either a source or a State by
 *     which it was caused to be instantiated.
 * 
 * Used in Mech and Pilot.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class Condition extends State {
    public Condition(StateData data, Duration duration, State causedBy) {
        super(data, duration, causedBy);
    }
    public Condition(StateData data, Duration duration, String source) {
        super(data, duration, source);
    }
    public Condition(Condition condition) {
        super(condition);
    }

    /**
     * Compares this Condition object and obj. If they are instances of the same
     *     class, returns true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Condition)) {
            return false;
        }
        return equals((Condition) obj);
    }
    /**
     * Compares this Condition object and condition. If they have the same
     *     property values, returns true.
     * @param condition a Condition to be compared to.
     * @return a boolean representing whether the two Conditions are the same.
     */
    public boolean equals(Condition condition) {
        if (condition == null) {
            return false;
        }
        if (! this.data.equals(condition.data)) {
            return false;
        }
        if (! this.duration.equals(condition.duration)) {
            return false;
        }
        if (this.source == null) {
            if (condition.source != null) {
                return false;
            }
        } else {
            if (condition.source == null) {
                return false;
            }
            if (! this.source.equals(condition.source)) {
                return false;
            }
        }
        if (this.causedBy == null) {
            if (condition.causedBy != null) {
                return false;
            }
        } else {
            if (condition.causedBy == null) {
                return false;
            }
            if (! this.causedBy.equals(condition.causedBy)) {
                return false;
            }
        }

        return true;
    }
}
