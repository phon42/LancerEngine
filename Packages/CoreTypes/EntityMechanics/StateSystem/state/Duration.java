package Packages.CoreTypes.EntityMechanics.StateSystem.state;

import MainBranch.HelperMethods;

public class Duration {
    /**
     * The duration of this State (i.e. "1 round").
     * Must be a valid String as defined by State.allowedDurations.
     * Case-insensitive and stored in lowercase.
     */
    protected String duration;
    /**
     * Contains an array of possible values for this.duration.
     * - "source" - The life time of the source 
     * Case-insensitive and stored in lowercase.
     */
    protected static final String[] allowedDurations = new String[] {"1 round",
        "1 turn", "permanent", "until removed", "source"};

    public String getDuration() {
        return duration;
    }
    /**
     * Sets this.duration to the provided value.
     * @param duration a String which cannot be null and cannot be an invalid
     *     duration, as defined by State.allowedDurations.
     * @throws IllegalArgumentException if status is duration or an invalid
     *     value as defined by State.allowedDurations.
     */
    protected void setDuration(String duration) {
        HelperMethods.checkString("New duration", duration);
        duration = duration.toLowerCase();
        for (String allowedDuration : State.allowedDurations) {
            if (duration.equals(allowedDuration)) {
                this.duration = duration;
                return;
            }
        }
        throw new IllegalArgumentException("New duration value is an invalid"
            + " value: \"" + duration + "\"");
    }
}
