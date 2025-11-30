package MainBranch.roll;

public class RollConditions {
    // Required property
    /**
     * The flat modifier applied directly to a roll (i.e. 2, representing a +2
     *     flat bonus to a roll).
     * Can be any int.
     */
    private int flatModifier;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * The number of accuracy dice applied to the roll.
     * Must be a minimum of 0.
     * Default value: 0.
     */
    private int accuracy;
    public static final int accuracyDefault = 0;
    /**
     * The number of difficulty dice applied to the roll.
     * Must be a minimum of 0.
     * Default value: 0.
     */
    private int difficulty;
    public static final int difficultyDefault = 0;

    // Reference property
    /**
     * Unused within the class but might be of use to a user.
     */
    public static final int flatModifierDefault = 0;

    public RollConditions(int flatModifier, int accuracy, int difficulty) {
        setFlatModifier(flatModifier);
        setAccuracy(accuracy);
        setDifficulty(difficulty);
    }
    public RollConditions(int flatModifier, int accuracy) {
        this(flatModifier, accuracy, RollConditions.difficultyDefault);
    }
    public RollConditions(int flatModifier) {
        this(flatModifier, RollConditions.accuracyDefault,
            RollConditions.difficultyDefault);
    }

    // Required property
    public int getFlatModifier() {
        return flatModifier;
    }
    // Semi-required properties
    public int getAccuracy() {
        return accuracy;
    }
    public int getDifficulty() {
        return difficulty;
    }
    // Required property
    private void setFlatModifier(int flatModifier) {
        this.flatModifier = flatModifier;
    }
    // Semi-required properties
    private void setAccuracy(int accuracy) {
        if (accuracy < 0) {
            accuracy = RollConditions.accuracyDefault;
        }
        this.accuracy = accuracy;
    }
    private void setDifficulty(int difficulty) {
        if (difficulty < 0) {
            difficulty = RollConditions.difficultyDefault;
        }
        this.difficulty = difficulty;
    }

    public RollConditions normalize() {
        int modifier = this.accuracy - this.difficulty;
        int accuracy = modifier > 0 ? modifier : 0;
        int difficulty = modifier < 0 ? - modifier : 0;

        return new RollConditions(modifier, accuracy, difficulty);
    }
}
