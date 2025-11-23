package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import Packages.CoreTypes.EntityMechanics.StateSystem.State;

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
    public Condition(StateData data, Duration duration, String source,
        State causedBy) {
        super(data, duration, source, causedBy);
    }
    public Condition(Condition condition) {
        super(condition);
    }
}
