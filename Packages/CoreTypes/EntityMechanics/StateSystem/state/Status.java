package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import Packages.CoreTypes.EntityMechanics.StateSystem.State;
import Packages.CoreTypes.EntityMechanics.StateSystem.state.unverifiedStateData.StateData;

/**
 * See pg. 77.
 * "Conditions are temporary effects caused by things like damage and electronic
 *     warfare, whereas statuses are usually effects that can’t easily be
 *     cleared."
 * - pg. 77
 */
/**
 * Represents a single status on a mech or pilot. Contains information about
 *     the status' properties, effect duration, its source, and the State
 *     which may have caused it.
 * 
 * Requires a StateData object, a duration, and either a source or a State by
 *     which it was caused to be instantiated.
 * 
 * Used in Mech and Pilot.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class Status extends State {
    public Status(StateData data, Duration duration, State causedBy) {
        super(data, duration, causedBy);
    }
    public Status(StateData data, Duration duration, String source) {
        super(data, duration, source);
    }
    public Status(Status status) {
        super(status);
    }

    /**
     * Compares this Status object and obj. If they are instances of the same
     *     class, returns true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Status)) {
            return false;
        }
        return equals((Status) obj);
    }
    /**
     * Compares this Status object and status. If they have the same property
     *     values, returns true.
     * @param status a Status to be compared to.
     * @return a boolean representing whether the two Statuses are the same.
     */
    public boolean equals(Status status) {
        if (status == null) {
            return false;
        }
        if (! this.data.equals(status.data)) {
            return false;
        }
        if (! this.duration.equals(status.duration)) {
            return false;
        }
        if (this.source == null) {
            if (status.source != null) {
                return false;
            }
        } else {
            if (status.source == null) {
                return false;
            }
            if (! this.source.equals(status.source)) {
                return false;
            }
        }
        if (this.causedBy == null) {
            if (status.causedBy != null) {
                return false;
            }
        } else {
            if (status.causedBy == null) {
                return false;
            }
            if (! this.causedBy.equals(status.causedBy)) {
                return false;
            }
        }

        return true;
    }
}
